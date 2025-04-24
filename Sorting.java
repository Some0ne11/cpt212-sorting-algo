import java.util.ArrayList;
import java.util.Arrays;

public class Sorting{
    public static void Sorting(int [] S)
    {
        // Assume all elements of array are positive integer with same length
        // Convert the first element of array into a string to find the length of the string,
        // and get the number of digits in the original number
        int k = Integer.toString(S[0]).length();

        // 1. Initialize
        // Create two arrays Array1 and Array2 of size 10 (for digits 0 to 9)
        ArrayList<Integer>[] Array1 = new ArrayList[10];
        ArrayList<Integer>[] Array2 = new ArrayList[10];
        for (int i=0; i<10; i++){
            Array1[i] = new ArrayList<>();
            Array2[i] = new ArrayList<>();        
        }

        // 2. Iteration
        // Move numbers from S to Array1 based on least significant digit
        for (int x:S){
            int i = x % 10; 
            Array1[i].add(x);
        }
        printBuckets(Array1, 1);
        // Assign the roles of source and destination to Array1 and Array2
        ArrayList<Integer>[] source = Array1;
        ArrayList<Integer>[] destination = Array2;

        for (int i=1; i<k; i++){
            int divisor = (int)Math.pow(10,i);

            // For each number in the source array, move the numbers from source to destination 
            // based on their ith digit
            for (ArrayList<Integer> bucket:source){
                for(int x:bucket){
                    int digit = x/divisor % 10; // get the ith digit
                    destination[digit].add(x);// Move to destination

                }
            }
            
            printBuckets(destination, (i % 2 == 0) ? 1 : 2);

            // Clear the source for the next iteration use
            for(ArrayList<Integer> bucket:source){
                bucket.clear();
            }

            // Swap the roles of the source and destination arrays
            ArrayList<Integer>[] temp = source;
            source = destination;
            destination = temp;
        }

        // 3. Reorder
        // Move the sorted numbers from final destination back to original array S
        int index = 0;
        for (ArrayList<Integer> bucket:source){
            for(int x:bucket){
                S[index++] = x;
            }
        }
    }

    // Helper function to print out the content of arrays
    public static void printBuckets(ArrayList<Integer>[] buckets, int round) {
        System.out.println("Array " + round + ":");
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("  Bucket " + i + ": " + buckets[i]);
        }
        System.out.println();
    }


    public static void main(String[]args){
        int[] S = {275, 87, 426, 61, 409, 170, 677, 503};
        System.out.println("Original array: " + Arrays.toString(S));
        Sorting(S);
        System.out.println("Sorted array: " + Arrays.toString(S));
    }
    
}
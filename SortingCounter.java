import java.util.ArrayList;
import java.util.Arrays;

public class SortingCounter {
    public static void SortingCount(int[] S) {
        int counter = 0;        // Counter for primitive operations

        // Get max and digit length
        int max = Arrays.stream(S).max().getAsInt();            // stream + max + getAsInt
        counter += S.length + 1 + 1;                            // estimate: stream traverses once + max + getAsInt

        int k = Integer.toString(max).length();                 // string conversion + length
        counter += (int)(Math.log10(max) + 1);                  // estimate for length of number

        // 1. Initialize
        // Create two arrays Array1 and Array2 of size 10 (for digits 0 to 9)
        ArrayList<Integer>[] Array1 = new ArrayList[10];
        counter++;

        ArrayList<Integer>[] Array2 = new ArrayList[10];
        counter++;

        for (int i=0; i<10; i++){
            Array1[i] = new ArrayList<>();
            counter++;
            Array2[i] = new ArrayList<>();
            counter++;

            counter += 2;               // loop check + increment
        }

        // 2. Iteration
        // Move numbers from S to Array1 based on least significant digit
        for (int x:S){
            int i = x % 10;
            counter++;
            Array1[i].add(x);           // add()
            counter++;                  // loop iteration
        }
        printBuckets(Array1, 1);

        // Assign the roles of source and destination to Array1 and Array2
        ArrayList<Integer>[] source = Array1;
        counter++;
        ArrayList<Integer>[] destination = Array2;
        counter++;

        for (int i=1; i<k; i++){
            int divisor = (int)Math.pow(10,i);      // exponentiation
            counter += 2;

            // For each number in the source array, move the numbers from source to destination
            // based on their ith digit
            for (ArrayList<Integer> bucket:source){
                for(int x:bucket){
                    int digit = x/divisor % 10;     // arithmetic
                    counter += 2;

                    destination[digit].add(x);      // Move to destination
                    counter++;
                    counter++;                      // inner loop iteration

                }
                counter++;                          // outer loop iteration
            }

            printBuckets(destination, (i % 2 == 0) ? 1 : 2);

            // Clear the source for the next iteration use
            for(ArrayList<Integer> bucket:source){
                bucket.clear();
                counter++;
                counter++;      // loop iteration
            }

            // Swap the roles of the source and destination arrays
            ArrayList<Integer>[] temp = source;
            source = destination;
            destination = temp;
            counter += 3;
        }

        // 3. Reorder
        // Move the sorted numbers from final destination back to original array S
        int index = 0;
        for (ArrayList<Integer> bucket:source){
            for(int x:bucket){
                S[index++] = x;
                counter += 2;           // assignment + increment
                counter++;              // inner loop
            }
            counter++;                  // outer loop
        }

        System.out.println("Total primitive operations: " + counter);
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
        SortingCount(S);
        System.out.println("Sorted array: " + Arrays.toString(S));
    }
}

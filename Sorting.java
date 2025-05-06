import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Sorting{
    public static void Sorting(int [] S)
    {
        int passes = 0;
        // Assume all elements of array are positive integer with same length
        // Find the maximum value in the array, convert it into a string to find the length of the string,
        // and get the number of digits in the original number
        int max = Arrays.stream(S).max().getAsInt();
        int k = Integer.toString(max).length();

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
        for (int x:S){                                      // equivalent to: for (int i = 0; i < S.length; i++){int x = S[i];}
            int i = x % 10; 
            Array1[i].add(x);
        }
        passes++;
        printBuckets(Array1, 1, passes);
        // Assign the roles of source and destination to Array1 and Array2
        ArrayList<Integer>[] source = Array1;
        ArrayList<Integer>[] destination = Array2;

        for (int i=1; i<k; i++){
            passes++;
            int divisor = (int)Math.pow(10,i);

            // For each number in the source array, move the numbers from source to destination 
            // based on their ith digit
            for (ArrayList<Integer> bucket:source){         // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
                for(int x:bucket){                          // equivalent to: for (int x =0; x<bucket; x++)
                    int digit = x/divisor % 10;             // get the i-th digit
                    destination[digit].add(x);              // Move to destination

                }
            }
            
            printBuckets(destination, (i % 2 == 0) ? 1 : 2, passes);

            // Clear the source for the next iteration use
            for(ArrayList<Integer> bucket:source){          // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
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
        for (ArrayList<Integer> bucket:source){             // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
            for(int x:bucket){                              // equivalent to: for (int x =0; x<bucket; x++)
                S[index++] = x;
            }
        }
    }

    // Helper function to print out the content of arrays
    public static void printBuckets(ArrayList<Integer>[] buckets, int round, int passes) {
        System.out.println("=== Pass "+passes+" ===");
        System.out.println("Array " + round + ":");
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("  Bucket " + i + ": " + buckets[i]);
        }
        System.out.println();
    }

    // Main body executions
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        try {
            // Ask for array size
            System.out.print("Enter number of elements: ");
            int n = scanner.nextInt();

            if (n <= 0) {
                throw new IllegalArgumentException("Array size must be a positive number.");
            }

            int[] S = new int[n];                           // S = {275, 87, 426, 61, 409, 170, 677, 503}

            // Get user input for each element with validation
            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Enter non-negative number " + (i + 1) + ": ");
                    int input = scanner.nextInt();

                    if (input < 0) {
                        System.out.println("Please enter nonnegative integers.");
                    } else {
                        S[i] = input;
                        break;
                    }
                }
            }

            System.out.println("Original array: " + Arrays.toString(S));
            Sorting(S);
            System.out.println("Sorted array: " + Arrays.toString(S));
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid integers only.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

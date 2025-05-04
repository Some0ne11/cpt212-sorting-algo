import java.util.ArrayList;
import java.util.Arrays;

public class SortingCounter {
    public static void SortingCount(int[] S) {

        int passes = 0;                                     // To calculate the number of passes
        int counter = 0;                                    // Counter for primitive operations

        // Get max and digit length
        int max = Arrays.stream(S).max().getAsInt();        // stream + max + getAsInt
        counter += 4;                                       // <counter> assignment + 3 method calls

        int k = Integer.toString(max).length();             // string conversion + length
        counter += 3;                                       // <counter> assignment + 2 method calls

        // 1. Initialize
        // Create two arrays Array1 and Array2 of size 10 (for digits 0 to 9)
        ArrayList<Integer>[] Array1 = new ArrayList[10];
        ArrayList<Integer>[] Array2 = new ArrayList[10];
        counter += 2;                                       // <counter> 2 assignment

        for (int i=0; i<10; i++){
            counter += 3;                                   // <counter> for loop (1 assignment, 1 compare, 1 arithmetic)

            Array1[i] = new ArrayList<>();
            counter += 2;                                   // <counter> array lookup + assignment

            Array2[i] = new ArrayList<>();
            counter += 2;                                   // <counter> array lookup + assignment
        }

        // 2. Iteration
        // Move numbers from S to Array1 based on least significant digits

        // loop below equivalent to:
        for (int x:S){                                      // equivalent to: for (int i = 0; i < S.length; i++){int x = S[i];}
            counter += 5;                                   /* <counter> for loop (1 assignment, 1 comparison, 1 arithmetic +
                                                                                    assignment + array lookup S[]) */
            int i = x % 10;
            counter += 2;                                   // <counter> assignment + arithmetic (modulo)

            Array1[i].add(x);
            counter += 2;                                   // <counter> array lookup + method call add()
        }
        passes++;
        counter += 1;                                       // <counter> function call printBuckets()
        printBuckets(Array1, 1, passes);

        // Assign the roles of source and destination to Array1 and Array2
        ArrayList<Integer>[] source = Array1;
        counter += 1;                                       // <counter> assignment
        ArrayList<Integer>[] destination = Array2;
        counter += 1;                                       // <counter> assignment

        for (int i=1; i<k; i++){
            counter += 3;                                   // <counter> for loop (1 assignment, 1 comparison, 1 op)
            passes++;
            int divisor = (int)Math.pow(10,i);
            counter += 2;                                   // <counter> assignment + method call pow()

            // For each number in the source array, move the numbers from source to destination
            // based on their ith digit
            for (ArrayList<Integer> bucket:source){         // equivalent to: for (int bucket =0; bucket<source.length; i++)
                for(int x:bucket){                          // equivalent to: for (int x =0; x<bucket; x++)
                    int digit = x/divisor % 10;             // get the i-th digit
                    counter += 3;                           // <counter> assignment + 2 arithmetics (division and modulo)

                    destination[digit].add(x);              // Move to destination
                    counter += 1;                           // <counter> array lookup + method call add()
                    counter += 1;                           // TODO recheck this counter inner loop iteration

                }
                counter++;                                  // TODO not sure how to count outer loop iteration
            }

            printBuckets(destination, (i % 2 == 0) ? 1 : 2, passes);

            // Clear the source for the next iteration use
            for(ArrayList<Integer> bucket:source){          // TODO add counter for this loop
                bucket.clear();
                counter += 1;                               // <counter> method call clear()
                counter++;                                  // loop iteration
            }

            // Swap the roles of the source and destination arrays
            ArrayList<Integer>[] temp = source;
            source = destination;
            destination = temp;
            counter += 3;                                   // <counter> 3 assignments
        }

        // 3. Reorder
        // Move the sorted numbers from final destination back to original array S
        int index = 0;
        counter += 1;                                       // <counter> 1 assignment

        for (ArrayList<Integer> bucket:source){
            for(int x:bucket){
                S[index++] = x;
                counter += 3;                               // <counter> assignment + increment + array lookup
                counter++;                                  // TODO recheck this counter inner loop
            }
            counter++;                                      // TODO recheck this counter outer loop
        }

        System.out.println("Total primitive operations: " + counter);
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
        int[] S = {275, 87, 426, 61, 409, 170, 677, 503};
        // TODO make it as user input

        System.out.println("Original array: " + Arrays.toString(S));
        SortingCount(S);
        System.out.println("Sorted array: " + Arrays.toString(S));
    }
}

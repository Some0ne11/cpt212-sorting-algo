import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SortingCounter {
    public static void SortingCount(int[] S) {

        int passes = 0;                                     // To calculate the number of passes (won't be counted for our total primitive operations)
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
            Array2[i] = new ArrayList<>();
            counter += 4;                                   // <counter> 2 array lookup + 2 assignments
        }

        // 2. Iteration
        // Move numbers from S to Array1 based on least significant digits
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
        ArrayList<Integer>[] destination = Array2;
        counter += 2;                                       // <counter> 2 assignment

        for (int i=1; i<k; i++){
            counter += 3;                                   // <counter> for loop (1 assignment, 1 comparison, 1 arithmetic)
            passes++;
            int divisor = (int)Math.pow(10,i);
            counter += 3;                                   // <counter> assignment + method call pow() + type casting

            // For each number in the source array, move the numbers from source to destination
            // based on their ith digit
            for (ArrayList<Integer> bucket:source){         // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
                counter += 3;                               // <counter> for loop (1 assignment, 1 comparison, 1 arithmetic)
                for(int x:bucket){                          // equivalent to: for (int x =0; x<bucket; x++)
                    counter += 3;                           // <counter> for loop (1 assignment, 1 comparison, 1 arithmetic)

                    int digit = x/divisor % 10;             // get the i-th digit
                    counter += 3;                           // <counter> assignment + 2 arithmetics (division and modulo)

                    destination[digit].add(x);              // Move to destination
                    counter += 2;                           // <counter> array lookup + method call add()
                }
            }
            counter += 4;                                   // <counter> function call + arithmetic + comparison + ternary op
            printBuckets(destination, (i % 2 == 0) ? 1 : 2, passes);

            // Clear the source for the next iteration use
            for(ArrayList<Integer> bucket:source){          // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
                counter += 3;                               // <counter> for loop (1 assignment, 1 comparison, 1 arithmetic)
                bucket.clear();
                counter += 1;                               // <counter> method call clear()
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

        for (ArrayList<Integer> bucket:source){             // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
            counter += 3;                                   // <counter> for loop (1 assignment, 1 comparison, 1 arithmetic)
            for(int x:bucket){                              // equivalent to: for (int x =0; x<bucket; x++)
                counter += 3;                               // <counter> for loop (1 assignment, 1 comparison, 1 arithmetic)
                S[index++] = x;
                counter += 3;                               // <counter> assignment + increment + array lookup
            }
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
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        while (true) {
            try {
                System.out.print("Enter number of elements: ");
                n = scanner.nextInt();
                if (n <= 0) {
                    throw new IllegalArgumentException("Array size must be a positive number.");
                }
                break; // valid input, break the loop
            } catch (InputMismatchException e) {
                System.out.println("Please enter valid integers only.");
                scanner.next(); // clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        try {

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
            SortingCount(S);
            System.out.println("Number of elements (n): " + n);
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

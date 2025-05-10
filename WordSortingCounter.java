import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WordSortingCounter {
    public static void sortWordsCount(String[] words) {

        int counter = 0;                                                    // Counter for primitive operations

        // Find the maximum word length, L
        int maxLength = Arrays.stream(words).mapToInt(String::length).max().getAsInt();
        counter += 5;                                                      // <counter> 4 methods calls + 1 assignment

        // ===== 1. Initialization =====
        // Buckets for characters: 0 (space) to 26 (a-z), using Arrays.
        ArrayList<String>[] array1 = new ArrayList[27];
        ArrayList<String>[] array2 = new ArrayList[27];
        counter += 2;                                                       // <counter> 2 assignment

        for (int i = 0; i < 27; i++) {
            counter += 3;                                                   // <counter> for loop (1 assignment, 1 compare, 1 arithmetic)

            array1[i] = new ArrayList<>();
            array2[i] = new ArrayList<>();
            counter += 4;                                                   // <counter> 2 array lookup + 2 assignments
        }

        // Assign the roles of source and destination to array1 and array2
        ArrayList<String>[] source = array1;
        ArrayList<String>[] destination = array2;
        counter += 2;                                                       // <counter> 2 assignment

        // ===== 2. Iteration over each character from right to left =====
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            counter += 4;                                                   // <counter> for loop (1 assignment and 1 arithmetic, 1 compare, 1 arithmetic)

            for (String word : words) {                                     // equivalent to: for (int i = 0; i < words.length; i++)
                counter += 3;                                               // <counter> for loop (1 assignment, 1 compare, 1 arithmetic)

                char c = (pos < word.length()) ? word.charAt(pos) : ' ';    // equivalent to: char c = (pos < words[i].length()) ? words[i].charAt(pos) : ' ';
                counter += 7;                                               // <counter> assignment + 2 array lookups + 2 methods call + 1 compare + 1 ternary ops

                int index = (c == ' ') ? 0 : c - 'a' + 1;
                counter += 5;                                               // <counter> assignment + compare + 2 arithmetic + 1 ternary ops
                destination[index].add(word);                               // equivalent to: destination[index].add(words[i]);
                counter += 3;                                               // <counter> 2 array lookup + 1 method call
            }

            counter += 2;                                                   // <counter> function call and arithmetic
            printBuckets(destination, (maxLength - pos));

            // Clear source buckets
            for (ArrayList<String> bucket : source) {                       // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
                counter += 3;                                               // <counter> for loop (1 assignment, 1 compare, 1 arithmetic)
                bucket.clear();
                counter += 1;                                               // <counter> method call clear()
            }

            // Swap source and destination
            ArrayList<String>[] temp = source;
            source = destination;
            destination = temp;
            counter += 3;                                                   // <counter> 3 assignments
        }

        // ===== 3. Reorder: copy back sorted values  =====
        int idx = 0;
        counter += 1;                                                       // <counter> assignment

        for (ArrayList<String> bucket : source) {                           // equivalent to: for (int bucket =0; bucket<source.length; bucket++)
            counter += 3;                                                   // <counter> for loop (1 assignment, 1 compare, 1 arithmetic)
            for (String word : bucket) {
                counter += 3;                                               // <counter> for loop (1 assignment, 1 compare, 1 arithmetic)
                words[idx++] = word;
                counter += 3;                                               // <counter> assignment + increment + array lookup
            }
        }
        System.out.println("Total primitive operations: " + counter);
    }

    // Prints current pass bucket contents (Helper function)
    public static void printBuckets(ArrayList<String>[] buckets, int pass) {
        System.out.println("=== Pass " + pass + " ===");
        System.out.println("Array " + ((pass % 2 == 1) ? 1 : 2) + ":");
        for (int i = 0; i < buckets.length; i++) {
            if (!buckets[i].isEmpty()) {
                System.out.print("  Bucket " + i + ": ");
                for (String word : buckets[i]) {
                    System.out.print(word + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    // Main body executions
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter number of words: ");
            int n = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            if (n <= 0) {
                throw new IllegalArgumentException("Word count must be a positive number.");
            }

            String[] words = new String[n];

            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Enter lowercase alphabetic word " + (i + 1) + ": ");
                    String input = scanner.nextLine().trim();

                    if (input.matches("[a-z]+")) {
                        words[i] = input;
                        break;
                    } else {
                        System.out.println("Please enter only lowercase letters (a-z) with no spaces.");
                    }
                }
            }

            System.out.println("Original: " + Arrays.toString(words));
            sortWordsCount(words);
            System.out.println("Number of elements (n): " + n);
            System.out.println("Sorted:   " + Arrays.toString(words));
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid input.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

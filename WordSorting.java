import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class WordSorting {

    public static void sortWords(String[] words) {
        // ===== 1. Initialization =====

        // Find the maximum word length
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }

        // Buckets for characters: 0 (space) to 26 (a-z)
        ArrayList<String>[] array1 = new ArrayList[27];
        ArrayList<String>[] array2 = new ArrayList[27];
        for (int i = 0; i < 27; i++) {
            array1[i] = new ArrayList<>();
            array2[i] = new ArrayList<>();
        }

        ArrayList<String>[] source = array1;
        ArrayList<String>[] destination = array2;

        // ===== 2. Iteration over each character from right to left =====
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            for (String word : words) {
                char c = (pos < word.length()) ? word.charAt(pos) : ' ';
                int index = (c == ' ') ? 0 : c - 'a' + 1;
                destination[index].add(word);
            }

            printBuckets(destination, (maxLength - pos));

            // Clear source buckets
            for (ArrayList<String> bucket : source) {
                bucket.clear();
            }

            // Swap source and destination
            ArrayList<String>[] temp = source;
            source = destination;
            destination = temp;
        }

        // ===== 3. Copy back sorted values  =====
        int idx = 0;
        for (ArrayList<String> bucket : source) {
            for (String word : bucket) {
                words[idx++] = word;
            }
        }
    }

    // Prints current pass bucket contents
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

    // Main with user input (similar to number sorter)
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
            sortWords(words);
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

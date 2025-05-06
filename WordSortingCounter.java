import java.util.ArrayList;
import java.util.Arrays;

public class WordSortingCounter {

    public static void sortWords(String[] words) {
        int counter = 0; // Primitive operation counter
        int passes = 0;  // Count how many passes (i.e., character positions processed)

        // ===== 1. Initialization =====

        // Step 1: Find the maximum length of all words
        int maxLength = 0;
        counter++; // assignment
        for (String word : words) {
            counter++; // loop comparison
            counter++; // word.length()
            if (word.length() > maxLength) {
                counter++; // comparison
                maxLength = word.length();
                counter++; // assignment
            }
            counter++; // loop increment
        }

        // Step 2: Pad all words with trailing spaces
        for (int i = 0; i < words.length; i++) {
            counter += 2; // loop control
            while (words[i].length() < maxLength) {
                counter++; // comparison
                words[i] = words[i] + " ";
                counter++; // assignment
            }
            counter++; // loop increment
        }

        // Create two arrays of buckets (0 for space, 1-26 for a-z)
        ArrayList<String>[] array1 = new ArrayList[27];
        ArrayList<String>[] array2 = new ArrayList[27];
        counter += 2;

        for (int i = 0; i < 27; i++) {
            array1[i] = new ArrayList<>();
            array2[i] = new ArrayList<>();
            counter += 4; // 2 assignments, loop check, increment
        }

        ArrayList<String>[] source = array1;
        ArrayList<String>[] destination = array2;
        counter += 2;

        // ===== 2. Iteration (Sorting passes by character positions) =====
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            passes++;
            counter += 2; // loop condition and decrement

            // Distribute words into destination buckets
            for (String word : words) {
                counter++; // loop comparison
                char c = word.charAt(pos);
                counter++; // charAt

                int index = (c == ' ') ? 0 : c - 'a' + 1;
                counter += 2; // ternary + arithmetic

                destination[index].add(word);
                counter++; // add()
                counter++; // loop increment
            }

            // Optional debug print — can remove for cleaner op count
            printBuckets(destination, (maxLength - pos));

            // ===== 3. Reorder =====
            int idx = 0;
            counter++; // assignment

            for (ArrayList<String> bucket : destination) {
                for (String w : bucket) {
                    words[idx++] = w;
                    counter += 3; // assignment + array access + increment
                }
                bucket.clear();
                counter++; // clear
                counter++; // outer loop
            }

            // Swap source and destination
            ArrayList<String>[] temp = source;
            source = destination;
            destination = temp;
            counter += 3;
        }

        // ===== Final cleanup =====
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
            counter += 3; // loop control, method call, assignment
        }

        // ===== Results =====
        System.out.println("Total passes: " + passes);
        System.out.println("Total primitive operations: " + counter);
    }

    // Print buckets for debugging
    public static void printBuckets(ArrayList<String>[] buckets, int pass) {
        System.out.println("Array " + pass + ":");
        for (int i = 0; i < buckets.length; i++) {
            if (!buckets[i].isEmpty()) {
                System.out.print("  Bucket " + i + ": ");
                for (String word : buckets[i]) {
                    System.out.print(word + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {
                "cows", "dogs", "sea", "rug", "row", "mob", "box", "tab",
                "bar", "ear", "tar", "dig", "big", "tea", "now", "fox"
        };

        System.out.println("Original: " + Arrays.toString(words));
        sortWords(words);
        System.out.println("Sorted:   " + Arrays.toString(words));
    }
}

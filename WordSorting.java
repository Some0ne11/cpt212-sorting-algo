import java.util.ArrayList;
import java.util.Arrays;

public class WordSorting {

    public static void sortWords(String[] words) {

        // ===== 1st Step: Initialization =====
        // Find the maximum length of all words
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }

        // Pad all words with trailing spaces so all have the same length
        for (int i = 0; i < words.length; i++) {
            while (words[i].length() < maxLength) {
                words[i] = words[i] + " ";
            }
        }

        // Create two arrays of buckets (for radix sort): 0 for space, 1–26 for a–z
        ArrayList<String>[] array1 = new ArrayList[27];
        ArrayList<String>[] array2 = new ArrayList[27];
        for (int i = 0; i < 27; i++) {
            array1[i] = new ArrayList<>();
            array2[i] = new ArrayList<>();
        }

        // Source and destination buckets to be used alternately
        ArrayList<String>[] source = array1;
        ArrayList<String>[] destination = array2;

        // ===== 2nd Step: Iteration (from rightmost to leftmost character) =====
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            // Place words into appropriate buckets based on current character
            for (String word : words) {
                char c = word.charAt(pos);
                int index = (c == ' ') ? 0 : c - 'a' + 1;
                destination[index].add(word);
            }

            // Debugging output: view how words are distributed at each position
            printBuckets(destination, (maxLength - pos));

            // ===== 3rd Step: Reorder (flatten buckets into array) =====
            int idx = 0;
            for (ArrayList<String> bucket : destination) {
                for (String w : bucket) {
                    words[idx++] = w;
                }
                bucket.clear(); // clear for next iteration
            }

            // Swap the buckets for the next round
            ArrayList<String>[] temp = source;
            source = destination;
            destination = temp;
        }

        // ===== Final Step: Cleanup =====
        // Trim the trailing spaces after sorting
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
    }

    // Helper function to print bucket contents
    public static void printBuckets(ArrayList<String>[] buckets, int pass) {
        System.out.println("Array " + pass + ":");
        for (int i = 0; i < buckets.length; i++) {
            if (!buckets[i].isEmpty()) {
                System.out.print("Bucket " + i + ": ");
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
import java.util.ArrayList;
import java.util.Arrays;

public class WordSorting {

    public static void sortWords(String[] words) {
        // Step 1: Find the maximum length of all words
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }

        // Step 2: Pad all words with trailing spaces so all have the same length
        for (int i = 0; i < words.length; i++) {
            while (words[i].length() < maxLength) {
                words[i] = words[i] + " ";
            }
        }

        // Step 3: Create two arrays of buckets for 0 (space) and a-z (1 to 26)
        ArrayList<String>[] array1 = new ArrayList[27];
        ArrayList<String>[] array2 = new ArrayList[27];
        for (int i = 0; i < 27; i++) {
            array1[i] = new ArrayList<>();
            array2[i] = new ArrayList<>();
        }

        ArrayList<String>[] source = array1;
        ArrayList<String>[] destination = array2;

        // Step 4: Perform radix sort from rightmost to leftmost character
        for (int pos = maxLength - 1; pos >= 0; pos--) {
            // Distribute words into destination buckets based on current character
            for (String word : words) {
                char c = word.charAt(pos);
                int index = (c == ' ') ? 0 : c - 'a' + 1; // 'a' maps to 1, 'b' to 2, ..., 'z' to 26
                destination[index].add(word);
            }

            // Print buckets for debug
            printBuckets(destination, (maxLength - pos));

            // Flatten destination back into words[]
            int idx = 0;
            for (ArrayList<String> bucket : destination) {
                for (String w : bucket) {
                    words[idx++] = w;
                }
                bucket.clear(); // clear bucket for next round
            }

            // Swap source and destination for next pass
            ArrayList<String>[] temp = source;
            source = destination;
            destination = temp;
        }

        // Step 5: Trim trailing spaces from all words
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
    }

    // Print buckets during sorting
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
        String[] words = {"cows", "dogs", "sea", "rug", "row", "mob", "box", "tab", "bar", "ear", "tar", "dig", "big", "tea", "now", "fox"};
        System.out.println("Original: " + Arrays.toString(words));
        sortWords(words);
        System.out.println("Sorted:   " + Arrays.toString(words));
    }
}

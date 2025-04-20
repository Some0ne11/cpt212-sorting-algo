import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test1 {
    public static void main(String[] args) {
        //int[] numbers = {275, 87, 426, 61, 409, 170, 677, 503};
        int[] numbers = {180, 381, 902, 190, 30, 40, 70, 600};
        System.out.println("Original array: " + Arrays.toString(numbers));
        int[] sorted = radixSort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(sorted));
    }
    
    public static int[] radixSort(int[] array) {
        // 1. Initialize
        System.out.println("\n1. Initialize");
        System.out.println("Example: " + Arrays.toString(array));
        
        // Create the two bucket arrays
        List<Integer>[] array1 = new ArrayList[10];
        List<Integer>[] array2 = new ArrayList[10];
        
        // Initialize the bucket arrays
        for (int i = 0; i < 10; i++) {
            array1[i] = new ArrayList<>();
            array2[i] = new ArrayList<>();
        }
        
        // Print initial state
        System.out.println("Array1: " + Arrays.toString(array1));
        System.out.println("Array2: " + Arrays.toString(array2));
        
        // 2. Iteration
        System.out.println("\n2. Iteration");
        
        // First Pass - distribute by ones digit
        System.out.println("\nFirst Pass");
        for (int num : array) {
            int digit = (num / 1) % 10;  // Get ones digit
            array1[digit].add(num);
        }
        
        // Display array1 after first pass
        System.out.println("Array1:");
        for (int i = 0; i < 10; i++) {
            if (!array1[i].isEmpty()) {
                System.out.print(i + ": ");
                for (int num : array1[i]) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
        
        // Second Pass - distribute by tens digit
        System.out.println("\nSecond Pass");
        // Clear array2 first (though it's already empty)
        for (int i = 0; i < 10; i++) {
            array2[i].clear();
        }
        
        // Move from array1 to array2 based on tens digit
        for (int i = 0; i < 10; i++) {
            for (int num : array1[i]) {
                int digit = (num / 10) % 10;  // Get tens digit
                array2[digit].add(num);
            }
        }
        
        // Display array2 after second pass
        System.out.println("Array2:");
        for (int i = 0; i < 10; i++) {
            if (!array2[i].isEmpty()) {
                System.out.print(i + ": ");
                for (int num : array2[i]) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
        
        // Third Pass - distribute by hundreds digit
        System.out.println("\nThird Pass");
        // Clear array1
        for (int i = 0; i < 10; i++) {
            array1[i].clear();
        }
        
        // Move from array2 to array1 based on hundreds digit
        for (int i = 0; i < 10; i++) {
            for (int num : array2[i]) {
                int digit = (num / 100) % 10;  // Get hundreds digit
                array1[digit].add(num);
            }
        }
        
        // Display array1 after third pass
        System.out.println("Array1:");
        for (int i = 0; i < 10; i++) {
            if (!array1[i].isEmpty()) {
                System.out.print(i + ": ");
                for (int num : array1[i]) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
        
        // 3. Reorder - Collect all numbers in order
        System.out.println("\n3. Reorder");
        int[] result = new int[array.length];
        int index = 0;
        
        for (int i = 0; i < 10; i++) {
            for (int num : array1[i]) {
                result[index++] = num;
            }
        }
        
        System.out.println("Sorted list: " + Arrays.toString(result));
        
        return result;
    }
}
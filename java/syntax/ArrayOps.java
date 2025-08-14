package syntax;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayOps {

    public static void arrayBasics() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY BASICS");
        System.out.println("---------------------");

        // Array declaration and initialization
        int[] arr1 = new int[5]; // Default values are 0
        int[] arr2 = {1, 2, 3, 4, 5}; // Direct initialization
        int[] arr3 = new int[]{1, 2, 3, 4, 5}; // Alternative syntax

        // 2D arrays
        int[][] matrix = new int[3][4]; // 3 rows, 4 columns
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        // Array length
        System.out.println("arr2.length: " + arr2.length);

        // Accessing elements
        System.out.println("arr2[0]: " + arr2[0]);
        System.out.println("arr2[arr2.length - 1]: " + arr2[arr2.length - 1]);

        // Iterating through arrays
        System.out.println("Iterating with for loop:");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        System.out.println("Iterating with enhanced for loop:");
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void arraySorting() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY SORTING");
        System.out.println("---------------------");

        int[] arr = {5, 2, 8, 1, 9, 3};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Sort in ascending order
        Arrays.sort(arr);
        System.out.println("Sorted ascending: " + Arrays.toString(arr));

        // Sort in descending order (using Integer array)
        Integer[] arrInteger = {5, 2, 8, 1, 9, 3};
        Arrays.sort(arrInteger, Collections.reverseOrder());
        System.out.println("Sorted descending: " + Arrays.toString(arrInteger));

        // Sort specific range
        int[] arr2 = {5, 2, 8, 1, 9, 3};
        Arrays.sort(arr2, 1, 4); // Sort from index 1 to 3 (exclusive)
        System.out.println("Partial sort: " + Arrays.toString(arr2));

        // Custom comparator for objects
        String[] strings = {"banana", "apple", "cherry"};
        Arrays.sort(strings, (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("Sorted by length: " + Arrays.toString(strings));
    }

    public static void arraySearching() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY SEARCHING");
        System.out.println("---------------------");

        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("Array: " + Arrays.toString(arr));

        // Binary search (array must be sorted)
        int target = 7;
        int index = Arrays.binarySearch(arr, target);
        System.out.println("Binary search for " + target + ": " + index);

        // Linear search
        int linearIndex = linearSearch(arr, target);
        System.out.println("Linear search for " + target + ": " + linearIndex);

        // Check if element exists
        boolean contains = Arrays.asList(arr).contains(target);
        System.out.println("Contains " + target + ": " + contains);
    }

    public static void arrayManipulation() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY MANIPULATION");
        System.out.println("---------------------");

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original: " + Arrays.toString(arr));

        // Copy array
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("Copy: " + Arrays.toString(copy));

        // Copy with different size
        int[] copyLarger = Arrays.copyOf(arr, arr.length + 2);
        System.out.println("Larger copy: " + Arrays.toString(copyLarger));

        // Copy range
        int[] copyRange = Arrays.copyOfRange(arr, 1, 4);
        System.out.println("Range copy: " + Arrays.toString(copyRange));

        // Fill array
        int[] filled = new int[5];
        Arrays.fill(filled, 42);
        System.out.println("Filled: " + Arrays.toString(filled));

        // Fill range
        int[] partialFill = {1, 2, 3, 4, 5};
        Arrays.fill(partialFill, 1, 4, 0);
        System.out.println("Partial fill: " + Arrays.toString(partialFill));
    }

    public static void arrayConversion() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY CONVERSION");
        System.out.println("---------------------");

        // Array to List
        Integer[] arr = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(arr);
        System.out.println("Array to List: " + list);

        // List to Array
        List<Integer> list2 = Arrays.asList(6, 7, 8, 9, 10);
        Integer[] arr2 = list2.toArray(new Integer[0]);
        System.out.println("List to Array: " + Arrays.toString(arr2));

        // String to char array
        String str = "hello";
        char[] charArray = str.toCharArray();
        System.out.println("String to char array: " + Arrays.toString(charArray));

        // char array to String
        String newStr = new String(charArray);
        System.out.println("char array to String: " + newStr);
    }

    public static void arrayAdvanced() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY ADVANCED TECHNIQUES");
        System.out.println("---------------------");

        // Two pointers technique
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("Two pointers - reverse array:");
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));

        // Sliding window
        int[] windowArr = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 4;
        int maxSum = maxSumSubarrayOfSizeK(windowArr, k);
        System.out.println("Max sum of subarray of size " + k + ": " + maxSum);

        // Prefix sum
        int[] prefixArr = {1, 2, 3, 4, 5};
        int[] prefixSum = calculatePrefixSum(prefixArr);
        System.out.println("Prefix sum: " + Arrays.toString(prefixSum));

        // Kadane's algorithm
        int[] kadaneArr = {-2, -3, 4, -1, -2, 1, 5, -3};
        int maxSubarraySum = kadaneAlgorithm(kadaneArr);
        System.out.println("Max subarray sum (Kadane): " + maxSubarraySum);
    }

    // Helper methods
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static int maxSumSubarrayOfSizeK(int[] arr, int k) {
        int maxSum = 0;
        int currentSum = 0;
        
        // Calculate sum of first window
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }
        maxSum = currentSum;
        
        // Slide the window
        for (int i = k; i < arr.length; i++) {
            currentSum = currentSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    public static int[] calculatePrefixSum(int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        
        return prefixSum;
    }

    public static int kadaneAlgorithm(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }

    public static void main(String[] args) {
        arrayBasics();
        arraySorting();
        arraySearching();
        arrayManipulation();
        arrayConversion();
        arrayAdvanced();
    }
} 
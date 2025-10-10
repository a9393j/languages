package syntax;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ArrayOps - Comprehensive array operations and algorithms demonstration
 * 
 * This class demonstrates various array operations, algorithms, and techniques
 * commonly used in programming interviews and real-world applications.
 * 
 * Key Learning Areas:
 * - Array basics: declaration, initialization, and iteration
 * - Sorting algorithms: built-in and custom sorting techniques
 * - Searching algorithms: linear and binary search implementations
 * - Array manipulation: copying, filling, and range operations
 * - Advanced techniques: two pointers, sliding window, prefix sum, Kadane's algorithm
 * 
 * @author Learning Java Arrays
 * @version 1.0
 */
public class ArrayOps {

    /**
     * Demonstrates fundamental array operations and concepts
     * 
     * This method covers:
     * - Array declaration and initialization techniques
     * - Multi-dimensional arrays (2D arrays)
     * - Array length and element access
     * - Different iteration methods (traditional and enhanced for loops)
     * 
     * Expected Output:
     * ------------------------------------------
     * ARRAY BASICS
     * ---------------------
     * arr2.length: 5
     * arr2[0]: 1
     * arr2[arr2.length - 1]: 5
     * Iterating with for loop:
     * 1 2 3 4 5 
     * Iterating with enhanced for loop:
     * 1 2 3 4 5 
     */
    public static void arrayBasics() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY BASICS");
        System.out.println("---------------------");

        // Array declaration and initialization - three different ways
        int[] arr1 = new int[5]; // Default values are 0
        int[] arr2 = {1, 2, 3, 4, 5}; // Direct initialization (most common)
        int[] arr3 = new int[]{1, 2, 3, 4, 5}; // Alternative syntax

        // 2D arrays - rectangular arrays
        int[][] matrix = new int[3][4]; // 3 rows, 4 columns (all zeros)
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; // Jagged array

        // Array length property - fixed size after creation
        System.out.println("arr2.length: " + arr2.length);

        // Accessing elements by index (0-based indexing)
        System.out.println("arr2[0]: " + arr2[0]); // First element
        System.out.println("arr2[arr2.length - 1]: " + arr2[arr2.length - 1]); // Last element

        // Traditional for loop iteration - gives access to index
        System.out.println("Iterating with for loop:");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        // Enhanced for loop (for-each) - cleaner syntax, no index access
        System.out.println("Iterating with enhanced for loop:");
        for (int num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * Demonstrates various array sorting techniques and algorithms
     * 
     * This method covers:
     * - Built-in Arrays.sort() method (uses Dual-Pivot QuickSort - O(n log n))
     * - Ascending and descending order sorting
     * - Partial array sorting (range-based sorting)
     * - Custom comparator sorting for objects
     * 
     * Sorting Algorithm Used:
     * - Arrays.sort() uses Dual-Pivot QuickSort algorithm
     * - Time Complexity: O(n log n) average case
     * - Space Complexity: O(log n) for recursion stack
     * - For primitive arrays: optimized implementation
     * - For object arrays: stable TimSort algorithm
     * 
     * Expected Output:
     * ------------------------------------------
     * ARRAY SORTING
     * ---------------------
     * Original array: [5, 2, 8, 1, 9, 3]
     * Sorted ascending: [1, 2, 3, 5, 8, 9]
     * Sorted descending: [9, 8, 5, 3, 2, 1]
     * Partial sort: [5, 1, 2, 8, 9, 3]
     * Sorted by length: [apple, banana, cherry]
     */
    public static void arraySorting() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY SORTING");
        System.out.println("---------------------");

        int[] arr = {5, 2, 8, 1, 9, 3};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Sort in ascending order using Dual-Pivot QuickSort
        Arrays.sort(arr);
        System.out.println("Sorted ascending: " + Arrays.toString(arr));

        // Sort in descending order (requires Integer array for Collections.reverseOrder())
        Integer[] arrInteger = {5, 2, 8, 1, 9, 3};
        Arrays.sort(arrInteger, Collections.reverseOrder());
        System.out.println("Sorted descending: " + Arrays.toString(arrInteger));

        // Sort specific range (from index 1 to 3, exclusive of end index)
        int[] arr2 = {5, 2, 8, 1, 9, 3};
        Arrays.sort(arr2, 1, 4); // Sort indices 1, 2, 3
        System.out.println("Partial sort: " + Arrays.toString(arr2));

        // Custom comparator for objects - sort strings by length
        String[] strings = {"banana", "apple", "cherry"};
        Arrays.sort(strings, (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("Sorted by length: " + Arrays.toString(strings));
    }

    /**
     * Demonstrates various array searching algorithms and techniques
     * 
     * This method covers:
     * - Binary Search: O(log n) - requires sorted array
     * - Linear Search: O(n) - works on any array
     * - Contains check: O(n) - using Arrays.asList()
     * 
     * Searching Algorithms:
     * - Binary Search: Divide and conquer approach, halves search space each iteration
     * - Linear Search: Sequential search through each element
     * - Arrays.binarySearch(): Returns index if found, negative insertion point if not found
     * 
     * Expected Output:
     * ------------------------------------------
     * ARRAY SEARCHING
     * ---------------------
     * Array: [1, 3, 5, 7, 9, 11, 13, 15]
     * Binary search for 7: 3
     * Linear search for 7: 3
     * Contains 7: true
     */
    public static void arraySearching() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY SEARCHING");
        System.out.println("---------------------");

        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15}; // Sorted array for binary search
        System.out.println("Array: " + Arrays.toString(arr));

        // Binary search (array must be sorted) - O(log n) time complexity
        int target = 7;
        int index = Arrays.binarySearch(arr, target);
        System.out.println("Binary search for " + target + ": " + index);

        // Linear search - O(n) time complexity, works on any array
        int linearIndex = linearSearch(arr, target);
        System.out.println("Linear search for " + target + ": " + linearIndex);

        // Check if element exists using Arrays.asList() - O(n) time complexity
        boolean contains = Arrays.asList(arr).contains(target);
        System.out.println("Contains " + target + ": " + contains);
    }

    /**
     * Demonstrates array manipulation operations and utilities
     * 
     * This method covers:
     * - Array copying: full copy, resized copy, range copy
     * - Array filling: full fill, partial fill
     * - Memory management and array operations
     * 
     * Array Operations:
     * - Arrays.copyOf(): Creates new array with specified length
     * - Arrays.copyOfRange(): Copies specified range of array
     * - Arrays.fill(): Fills array with specified value
     * - All operations create new arrays (defensive copying)
     * 
     * Expected Output:
     * ------------------------------------------
     * ARRAY MANIPULATION
     * ---------------------
     * Original: [1, 2, 3, 4, 5]
     * Copy: [1, 2, 3, 4, 5]
     * Larger copy: [1, 2, 3, 4, 5, 0, 0]
     * Range copy: [2, 3, 4]
     * Filled: [42, 42, 42, 42, 42]
     * Partial fill: [1, 0, 0, 0, 5]
     */
    public static void arrayManipulation() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY MANIPULATION");
        System.out.println("---------------------");

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original: " + Arrays.toString(arr));

        // Copy entire array with same size
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("Copy: " + Arrays.toString(copy));

        // Copy array with larger size (extra elements filled with default values)
        int[] copyLarger = Arrays.copyOf(arr, arr.length + 2);
        System.out.println("Larger copy: " + Arrays.toString(copyLarger));

        // Copy specific range (from index 1 to 3, exclusive of end index)
        int[] copyRange = Arrays.copyOfRange(arr, 1, 4);
        System.out.println("Range copy: " + Arrays.toString(copyRange));

        // Fill entire array with same value
        int[] filled = new int[5];
        Arrays.fill(filled, 42);
        System.out.println("Filled: " + Arrays.toString(filled));

        // Fill specific range (from index 1 to 3, exclusive of end index)
        int[] partialFill = {1, 2, 3, 4, 5};
        Arrays.fill(partialFill, 1, 4, 0);
        System.out.println("Partial fill: " + Arrays.toString(partialFill));
    }

    /**
     * Demonstrates array conversion operations between different data types
     * 
     * This method covers:
     * - Array to List conversion using Arrays.asList()
     * - List to Array conversion using toArray()
     * - String to char array conversion
     * - char array to String conversion
     * 
     * Conversion Operations:
     * - Arrays.asList(): Creates fixed-size list backed by array
     * - toArray(): Converts collection to array
     * - String.toCharArray(): Converts string to character array
     * - String constructor: Creates string from character array
     * 
     * Expected Output:
     * ------------------------------------------
     * ARRAY CONVERSION
     * ---------------------
     * Array to List: [1, 2, 3, 4, 5]
     * List to Array: [6, 7, 8, 9, 10]
     * String to char array: [h, e, l, l, o]
     * char array to String: hello
     */
    public static void arrayConversion() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY CONVERSION");
        System.out.println("---------------------");

        // Array to List conversion (requires wrapper type for primitives)
        Integer[] arr = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(arr);
        System.out.println("Array to List: " + list);

        // List to Array conversion
        List<Integer> list2 = Arrays.asList(6, 7, 8, 9, 10);
        Integer[] arr2 = list2.toArray(new Integer[0]); // Size 0 for automatic sizing
        System.out.println("List to Array: " + Arrays.toString(arr2));

        // String to char array conversion
        String str = "hello";
        char[] charArray = str.toCharArray();
        System.out.println("String to char array: " + Arrays.toString(charArray));

        // char array to String conversion
        String newStr = new String(charArray);
        System.out.println("char array to String: " + newStr);
    }

    /**
     * Demonstrates advanced array algorithms and techniques
     * 
     * This method covers:
     * - Two Pointers Technique: O(n) - efficient array manipulation
     * - Sliding Window Algorithm: O(n) - finding optimal subarrays
     * - Prefix Sum Technique: O(n) - range sum queries
     * - Kadane's Algorithm: O(n) - maximum subarray sum
     * 
     * Advanced Algorithms:
     * 1. Two Pointers: Uses two indices moving towards each other
     *    - Time Complexity: O(n)
     *    - Space Complexity: O(1)
     *    - Use cases: reversing, palindrome checking, pair finding
     * 
     * 2. Sliding Window: Maintains a window of fixed/variable size
     *    - Time Complexity: O(n)
     *    - Space Complexity: O(1)
     *    - Use cases: substring problems, subarray optimization
     * 
     * 3. Prefix Sum: Precomputes cumulative sums for range queries
     *    - Time Complexity: O(n) preprocessing, O(1) per query
     *    - Space Complexity: O(n)
     *    - Use cases: range sum queries, subarray problems
     * 
     * 4. Kadane's Algorithm: Dynamic programming for maximum subarray
     *    - Time Complexity: O(n)
     *    - Space Complexity: O(1)
     *    - Use cases: maximum subarray sum, stock trading problems
     * 
     * Expected Output:
     * ------------------------------------------
     * ARRAY ADVANCED TECHNIQUES
     * ---------------------
     * Two pointers - reverse array:
     * [6, 5, 4, 3, 2, 1]
     * Max sum of subarray of size 4: 17
     * Prefix sum: [1, 3, 6, 10, 15]
     * Max subarray sum (Kadane): 7
     */
    public static void arrayAdvanced() {
        System.out.println("------------------------------------------");
        System.out.println("ARRAY ADVANCED TECHNIQUES");
        System.out.println("---------------------");

        // Two pointers technique - efficient array reversal
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("Two pointers - reverse array:");
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));

        // Sliding window algorithm - find maximum sum of subarray of size k
        int[] windowArr = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 4;
        int maxSum = maxSumSubarrayOfSizeK(windowArr, k);
        System.out.println("Max sum of subarray of size " + k + ": " + maxSum);

        // Prefix sum technique - precompute cumulative sums
        int[] prefixArr = {1, 2, 3, 4, 5};
        int[] prefixSum = calculatePrefixSum(prefixArr);
        System.out.println("Prefix sum: " + Arrays.toString(prefixSum));

        // Kadane's algorithm - find maximum subarray sum
        int[] kadaneArr = {-2, -3, 4, -1, -2, 1, 5, -3};
        int maxSubarraySum = kadaneAlgorithm(kadaneArr);
        System.out.println("Max subarray sum (Kadane): " + maxSubarraySum);
    }

    // ========================================================================
    // HELPER METHODS - ALGORITHM IMPLEMENTATIONS
    // ========================================================================

    /**
     * Linear Search Algorithm
     * 
     * Searches for target element sequentially through the array.
     * 
     * Time Complexity: O(n) - worst case scans entire array
     * Space Complexity: O(1) - constant extra space
     * 
     * @param arr Array to search in
     * @param target Element to find
     * @return Index of target if found, -1 otherwise
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index when found
            }
        }
        return -1; // Return -1 if not found
    }

    /**
     * Two Pointers Technique - Array Reversal
     * 
     * Reverses array in-place using two pointers moving towards each other.
     * 
     * Algorithm:
     * 1. Initialize left pointer at start, right pointer at end
     * 2. Swap elements at both pointers
     * 3. Move pointers towards center
     * 4. Repeat until pointers meet
     * 
     * Time Complexity: O(n) - visits each element once
     * Space Complexity: O(1) - constant extra space
     * 
     * @param arr Array to reverse (modified in-place)
     */
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            // Swap elements at left and right pointers
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;   // Move left pointer right
            right--;  // Move right pointer left
        }
    }

    /**
     * Sliding Window Algorithm - Maximum Sum of Subarray of Size K
     * 
     * Finds the maximum sum of any contiguous subarray of size k.
     * 
     * Algorithm:
     * 1. Calculate sum of first window of size k
     * 2. Slide window by removing leftmost element and adding rightmost element
     * 3. Keep track of maximum sum encountered
     * 
     * Time Complexity: O(n) - each element visited at most twice
     * Space Complexity: O(1) - constant extra space
     * 
     * @param arr Input array
     * @param k Size of subarray
     * @return Maximum sum of subarray of size k
     */
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
            // Remove leftmost element and add rightmost element
            currentSum = currentSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    /**
     * Prefix Sum Technique
     * 
     * Precomputes cumulative sums for efficient range sum queries.
     * 
     * Algorithm:
     * 1. Initialize prefixSum[0] = arr[0]
     * 2. For each i: prefixSum[i] = prefixSum[i-1] + arr[i]
     * 3. Range sum from i to j = prefixSum[j] - prefixSum[i-1]
     * 
     * Time Complexity: O(n) preprocessing, O(1) per query
     * Space Complexity: O(n) - stores prefix sum array
     * 
     * @param arr Input array
     * @return Prefix sum array where prefixSum[i] = sum of arr[0..i]
     */
    public static int[] calculatePrefixSum(int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0]; // Base case
        
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        
        return prefixSum;
    }

    /**
     * Kadane's Algorithm - Maximum Subarray Sum
     * 
     * Finds the maximum sum of any contiguous subarray using dynamic programming.
     * 
     * Algorithm:
     * 1. Initialize maxSoFar and maxEndingHere with first element
     * 2. For each element, decide whether to start new subarray or extend current
     * 3. maxEndingHere = max(arr[i], maxEndingHere + arr[i])
     * 4. maxSoFar = max(maxSoFar, maxEndingHere)
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - constant extra space
     * 
     * @param arr Input array (may contain negative numbers)
     * @return Maximum sum of any contiguous subarray
     */
    public static int kadaneAlgorithm(int[] arr) {
        int maxSoFar = arr[0];      // Maximum sum found so far
        int maxEndingHere = arr[0]; // Maximum sum ending at current position
        
        for (int i = 1; i < arr.length; i++) {
            // Either start new subarray or extend current one
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            // Update global maximum
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }

    /**
     * Main method - Entry point for the ArrayOps demonstration
     * 
     * This method runs all the array operation demonstrations in sequence,
     * providing a comprehensive overview of array techniques and algorithms.
     * 
     * Execution Order:
     * 1. Array Basics - Declaration, initialization, and iteration
     * 2. Array Sorting - Built-in sorting methods and algorithms
     * 3. Array Searching - Linear and binary search techniques
     * 4. Array Manipulation - Copying, filling, and range operations
     * 5. Array Conversion - Converting between arrays and other data types
     * 6. Array Advanced - Advanced algorithms and optimization techniques
     * 
     * Learning Path:
     * - Start with basic array operations to understand fundamentals
     * - Progress to sorting and searching algorithms
     * - Learn manipulation and conversion techniques
     * - Master advanced algorithms for competitive programming
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("🚀 WELCOME TO ARRAY OPERATIONS LEARNING");
        System.out.println("================================================");
        System.out.println("This program demonstrates various array operations,");
        System.out.println("algorithms, and techniques with detailed explanations");
        System.out.println("and expected outputs to help you learn.");
        System.out.println("================================================");
        System.out.println();
        
        // Run all array operation demonstrations
        arrayBasics();
        arraySorting();
        arraySearching();
        arrayManipulation();
        arrayConversion();
        arrayAdvanced();
        
        System.out.println("🎉 ARRAY OPERATIONS DEMONSTRATION COMPLETE!");
        System.out.println("================================================");
        System.out.println("Key Takeaways:");
        System.out.println("• Understanding array fundamentals and operations");
        System.out.println("• Learning efficient sorting and searching algorithms");
        System.out.println("• Mastering advanced techniques like sliding window");
        System.out.println("• Applying algorithms to solve real-world problems");
        System.out.println("================================================");
    }
} 
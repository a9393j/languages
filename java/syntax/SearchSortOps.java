package syntax;

import java.util.Arrays;

/**
 * SearchSortOps - A comprehensive collection of search and sort algorithms
 * 
 * This class demonstrates various sorting and searching algorithms with detailed
 * implementations and educational comments to help understand the concepts.
 * 
 * Key Learning Points:
 * - Understanding different sorting algorithms and when to use each
 * - Learning search strategies for different data scenarios
 * - Analyzing time and space complexity trade-offs
 * - Implementing optimization techniques
 */
public class SearchSortOps {

    /**
     * Demonstrates various sorting algorithms with performance analysis
     * 
     * Algorithm Overview:
     * - Bubble Sort: O(n²) - Simple but inefficient, good for learning
     * - Selection Sort: O(n²) - Simple, good for small datasets
     * - Insertion Sort: O(n²) - Efficient for small arrays, adaptive
     * - Merge Sort: O(n log n) - Stable, predictable performance
     * - Quick Sort: O(n log n) average, O(n²) worst case - Fast in practice
     * - Heap Sort: O(n log n) - In-place, good for memory-constrained systems
     * - Counting Sort: O(n + k) - Linear time for small range integers
     */
    public static void sortingAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("SORTING ALGORITHMS");
        System.out.println("---------------------");

        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Bubble Sort - O(n²) time complexity, simple but inefficient
        int[] bubbleArr = arr.clone();
        bubbleSort(bubbleArr);
        System.out.println("Bubble Sort: " + Arrays.toString(bubbleArr));

        // Selection Sort - O(n²) time complexity, minimizes swaps
        int[] selectionArr = arr.clone();
        selectionSort(selectionArr);
        System.out.println("Selection Sort: " + Arrays.toString(selectionArr));

        // Insertion Sort - O(n²) time complexity, adaptive for nearly sorted arrays
        int[] insertionArr = arr.clone();
        insertionSort(insertionArr);
        System.out.println("Insertion Sort: " + Arrays.toString(insertionArr));

        // Merge Sort - O(n log n) time complexity, stable and predictable
        int[] mergeArr = arr.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(mergeArr));

        // Quick Sort - O(n log n) average, O(n²) worst case, fastest in practice
        int[] quickArr = arr.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(quickArr));

        // Heap Sort - O(n log n) time complexity, in-place sorting
        int[] heapArr = arr.clone();
        heapSort(heapArr);
        System.out.println("Heap Sort: " + Arrays.toString(heapArr));

        // Counting Sort - O(n + k) time complexity, linear for small range integers
        int[] countingArr = {4, 2, 1, 4, 1, 4, 2, 1, 3};
        countingSort(countingArr);
        System.out.println("Counting Sort: " + Arrays.toString(countingArr));
    }

    /**
     * Demonstrates various searching algorithms with performance analysis
     * 
     * Search Algorithm Overview:
     * - Linear Search: O(n) - Works on any array, simple implementation
     * - Binary Search: O(log n) - Requires sorted array, very efficient
     * - Jump Search: O(√n) - Compromise between linear and binary search
     * - Interpolation Search: O(log log n) average - Best for uniformly distributed data
     */
    public static void searchingAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("SEARCHING ALGORITHMS");
        System.out.println("---------------------");

        // Test array must be sorted for binary search variants
        int[] arr = {11, 12, 22, 25, 34, 64, 90};
        System.out.println("Sorted array: " + Arrays.toString(arr));

        int target = 25;
        System.out.println("Searching for: " + target);

        // Linear Search - O(n) time complexity, works on any array
        int linearIndex = linearSearch(arr, target);
        System.out.println("Linear Search index: " + linearIndex);

        // Binary Search - O(log n) time complexity, requires sorted array
        int binaryIndex = binarySearch(arr, target);
        System.out.println("Binary Search index: " + binaryIndex);

        // Binary Search Recursive - Same complexity, uses recursion
        int binaryRecursiveIndex = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Binary Search Recursive index: " + binaryRecursiveIndex);

        // Jump Search - O(√n) time complexity, compromise approach
        int jumpIndex = jumpSearch(arr, target);
        System.out.println("Jump Search index: " + jumpIndex);

        // Interpolation Search - O(log log n) average, best for uniform data
        int interpolationIndex = interpolationSearch(arr, target);
        System.out.println("Interpolation Search index: " + interpolationIndex);
    }

    /**
     * Demonstrates advanced searching techniques for complex scenarios
     * 
     * Advanced Search Techniques:
     * - First/Last Occurrence: Modified binary search for duplicate handling
     * - Peak Finding: Finding local maxima in unsorted arrays
     * - Rotated Array Search: Finding minimum in rotated sorted arrays
     */
    public static void advancedSearching() {
        System.out.println("------------------------------------------");
        System.out.println("ADVANCED SEARCHING");
        System.out.println("---------------------");

        // Find first and last occurrence of duplicates using modified binary search
        int[] arr = {1, 2, 2, 2, 3, 4, 4, 4, 5};
        int target = 2;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);

        int firstIndex = findFirstOccurrence(arr, target);
        int lastIndex = findLastOccurrence(arr, target);
        System.out.println("First occurrence: " + firstIndex);
        System.out.println("Last occurrence: " + lastIndex);

        // Find peak element in unsorted array using binary search approach
        int[] peakArr = {1, 3, 20, 4, 1, 0};
        System.out.println("Peak array: " + Arrays.toString(peakArr));
        int peakIndex = findPeakElement(peakArr);
        System.out.println("Peak element index: " + peakIndex);

        // Find minimum in rotated sorted array (circular buffer scenario)
        int[] rotatedArr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Rotated array: " + Arrays.toString(rotatedArr));
        int minIndex = findMinInRotatedArray(rotatedArr);
        System.out.println("Minimum element index: " + minIndex);
    }

    /**
     * Demonstrates advanced sorting optimizations and hybrid approaches
     * 
     * Optimization Strategies:
     * - Median-of-Three Pivot: Reduces worst-case scenarios in Quick Sort
     * - Random Pivot: Provides probabilistic guarantees against bad inputs
     * - Hybrid Sorting: Combines best of multiple algorithms
     */
    public static void sortingOptimizations() {
        System.out.println("------------------------------------------");
        System.out.println("SORTING OPTIMIZATIONS");
        System.out.println("---------------------");

        // Quick Sort with different pivot strategies for better performance
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Quick Sort with median-of-three pivot selection
        int[] quickMedianArr = arr.clone();
        quickSortMedian(quickMedianArr, 0, quickMedianArr.length - 1);
        System.out.println("Quick Sort (median pivot): " + Arrays.toString(quickMedianArr));

        // Quick Sort with random pivot selection for probabilistic guarantees
        int[] quickRandomArr = arr.clone();
        quickSortRandom(quickRandomArr, 0, quickRandomArr.length - 1);
        System.out.println("Quick Sort (random pivot): " + Arrays.toString(quickRandomArr));

        // Hybrid Sort combining Quick Sort and Insertion Sort for small arrays
        int[] hybridArr = arr.clone();
        hybridSort(hybridArr, 0, hybridArr.length - 1);
        System.out.println("Hybrid Sort: " + Arrays.toString(hybridArr));
    }

    // ========================================================================
    // SORTING ALGORITHMS IMPLEMENTATION
    // ========================================================================

    /**
     * Bubble Sort - Simple comparison-based sorting algorithm
     * 
     * Time Complexity: O(n²) - Two nested loops
     * Space Complexity: O(1) - In-place sorting
     * Stability: Stable - Equal elements maintain relative order
     * 
     * Best for: Educational purposes, very small arrays
     * Worst for: Large datasets, production use
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        // Outer loop: number of passes needed
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Optimization flag for early termination
            
            // Inner loop: compare and swap adjacent elements
            // After i passes, last i elements are already sorted
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements if they are in wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // Early termination: if no swaps occurred, array is sorted
            if (!swapped) break;
        }
    }

    /**
     * Selection Sort - Simple in-place comparison sorting algorithm
     * 
     * Time Complexity: O(n²) - Always performs n²/2 comparisons
     * Space Complexity: O(1) - In-place sorting
     * Stability: Not stable - Equal elements may change relative order
     * 
     * Best for: Small arrays, minimizing number of swaps
     * Worst for: Large datasets, when stability is required
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // Process each position in the array
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // Assume current position has minimum
            
            // Find the actual minimum in unsorted portion
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // Update minimum index
                }
            }
            
            // Swap the found minimum with the current position
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * Insertion Sort - Adaptive, stable comparison sorting algorithm
     * 
     * Time Complexity: O(n²) worst/average, O(n) best (nearly sorted)
     * Space Complexity: O(1) - In-place sorting
     * Stability: Stable - Equal elements maintain relative order
     * 
     * Best for: Small arrays, nearly sorted arrays, online sorting
     * Worst for: Large datasets, reverse sorted arrays
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        // Start from second element (index 1)
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // Current element to insert
            
            // Find the correct position for key in sorted portion
            int j = i - 1;
            
            // Shift elements greater than key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            // Insert key in its correct position
            arr[j + 1] = key;
        }
    }

    /**
     * Merge Sort - Divide and conquer, stable sorting algorithm
     * 
     * Time Complexity: O(n log n) - Guaranteed performance
     * Space Complexity: O(n) - Requires extra space for merging
     * Stability: Stable - Equal elements maintain relative order
     * 
     * Best for: Large datasets, guaranteed performance, stable sorting
     * Worst for: Memory-constrained systems, small arrays
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // Base case: if subarray has 1 or fewer elements, it's already sorted
        if (left < right) {
            // Calculate middle point (avoids integer overflow)
            int mid = left + (right - left) / 2;
            
            // Recursively sort left and right halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    /**
     * Helper method to merge two sorted subarrays into a single sorted array
     * This is the "combine" step of the divide-and-conquer approach
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // Calculate sizes of the two subarrays
        int n1 = mid - left + 1; // Size of left subarray
        int n2 = right - mid;     // Size of right subarray

        // Create temporary arrays to hold the two halves
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Merge the temporary arrays back into the main array
        int i = 0, j = 0, k = left; // i for leftArr, j for rightArr, k for main array
        
        // Compare elements from both arrays and place smaller one in main array
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from leftArr (if any)
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements from rightArr (if any)
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    /**
     * Quick Sort - Efficient, in-place sorting algorithm
     * 
     * Time Complexity: O(n log n) average, O(n²) worst case
     * Space Complexity: O(log n) - Recursion stack space
     * Stability: Not stable - Equal elements may change relative order
     * 
     * Best for: General-purpose sorting, large datasets
     * Worst for: Already sorted arrays with poor pivot selection
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int pi = partition(arr, low, high);
            
            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Partition method for Quick Sort - places pivot in correct position
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose last element as pivot
        int i = low - 1;        // Index of smaller element

        // Move all elements smaller than pivot to the left
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap elements
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot in its correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Return pivot index
    }

    /**
     * Heap Sort - In-place sorting using heap data structure
     * 
     * Time Complexity: O(n log n) - Guaranteed performance
     * Space Complexity: O(1) - In-place sorting
     * Stability: Not stable - Equal elements may change relative order
     * 
     * Best for: Memory-constrained systems, guaranteed performance
     * Worst for: When stability is required
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap from array
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    /**
     * Heapify method for Heap Sort - maintains heap property
     */
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;     // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            // Swap root with largest
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /**
     * Counting Sort - Non-comparison based sorting for integers
     * 
     * Time Complexity: O(n + k) where k is the range of input
     * Space Complexity: O(n + k) - Requires extra space
     * Stability: Stable - Equal elements maintain relative order
     * 
     * Best for: Sorting integers with known, small range
     * Worst for: Large range integers, floating point numbers
     */
    public static void countingSort(int[] arr) {
        int n = arr.length;
        
        // Find the range of input elements
        int max = Arrays.stream(arr).max().orElse(0);
        int min = Arrays.stream(arr).min().orElse(0);
        int range = max - min + 1;

        // Create count array and output array
        int[] count = new int[range];
        int[] output = new int[n];

        // Store count of each element
        for (int i = 0; i < n; i++) {
            count[arr[i] - min]++;
        }

        // Change count[i] so that it contains position of element in output
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy the output array back to original array
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // ========================================================================
    // SEARCHING ALGORITHMS IMPLEMENTATION
    // ========================================================================

    /**
     * Linear Search - Simple search algorithm
     * 
     * Time Complexity: O(n) - Linear time
     * Space Complexity: O(1) - Constant space
     * 
     * Best for: Small arrays, unsorted arrays
     * Worst for: Large arrays, when array is sorted
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index if found
            }
        }
        return -1; // Return -1 if not found
    }

    /**
     * Binary Search - Efficient search on sorted arrays
     * 
     * Time Complexity: O(log n) - Logarithmic time
     * Space Complexity: O(1) - Constant space
     * 
     * Best for: Large sorted arrays
     * Worst for: Unsorted arrays
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid integer overflow

            if (arr[mid] == target) {
                return mid; // Found target
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Target not found
    }

    /**
     * Binary Search Recursive - Recursive version of binary search
     * 
     * Time Complexity: O(log n) - Same as iterative
     * Space Complexity: O(log n) - Recursion stack space
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1; // Base case: not found

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid; // Found target
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right); // Search right
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1); // Search left
        }
    }

    /**
     * Jump Search - Search algorithm that jumps ahead by fixed steps
     * 
     * Time Complexity: O(√n) - Square root of n
     * Space Complexity: O(1) - Constant space
     * 
     * Best for: Sorted arrays, when jumping is cheaper than linear search
     */
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.sqrt(n); // Optimal jump size
        int prev = 0;

        // Jump ahead until we find a value greater than target
        while (prev < n && arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) return -1;
        }

        // Linear search in the identified block
        while (prev < Math.min(step, n)) {
            if (arr[prev] == target) {
                return prev;
            }
            prev++;
        }

        return -1; // Target not found
    }

    /**
     * Interpolation Search - Improved binary search for uniformly distributed data
     * 
     * Time Complexity: O(log log n) average, O(n) worst case
     * Space Complexity: O(1) - Constant space
     * 
     * Best for: Uniformly distributed sorted arrays
     * Worst for: Non-uniformly distributed data
     */
    public static int interpolationSearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right && target >= arr[left] && target <= arr[right]) {
            if (left == right) {
                if (arr[left] == target) return left;
                return -1;
            }

            // Interpolation formula for better position estimation
            int pos = left + (((right - left) / (arr[right] - arr[left])) * (target - arr[left]));

            if (arr[pos] == target) {
                return pos; // Found target
            } else if (arr[pos] < target) {
                left = pos + 1; // Search right half
            } else {
                right = pos - 1; // Search left half
            }
        }

        return -1; // Target not found
    }

    // ========================================================================
    // ADVANCED SEARCHING TECHNIQUES
    // ========================================================================

    /**
     * Find first occurrence of target in sorted array with duplicates
     * Uses modified binary search to find leftmost occurrence
     */
    public static int findFirstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;        // Record this occurrence
                right = mid - 1;     // Continue searching in left half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    /**
     * Find last occurrence of target in sorted array with duplicates
     * Uses modified binary search to find rightmost occurrence
     */
    public static int findLastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;        // Record this occurrence
                left = mid + 1;      // Continue searching in right half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    /**
     * Find peak element in unsorted array
     * A peak element is greater than its neighbors
     */
    public static int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[mid + 1]) {
                right = mid; // Peak is in left half
            } else {
                left = mid + 1; // Peak is in right half
            }
        }

        return left; // Return peak index
    }

    /**
     * Find minimum element in rotated sorted array
     * Useful for circular buffers and rotated data structures
     */
    public static int findMinInRotatedArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[right]) {
                left = mid + 1; // Minimum is in right half
            } else {
                right = mid; // Minimum is in left half
            }
        }

        return left; // Return minimum index
    }

    // ========================================================================
    // SORTING OPTIMIZATIONS
    // ========================================================================

    /**
     * Quick Sort with median-of-three pivot selection
     * Reduces probability of worst-case scenarios
     */
    public static void quickSortMedian(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionMedian(arr, low, high);
            quickSortMedian(arr, low, pi - 1);
            quickSortMedian(arr, pi + 1, high);
        }
    }

    /**
     * Partition method using median-of-three pivot selection
     */
    private static int partitionMedian(int[] arr, int low, int high) {
        // Choose median of three as pivot
        int mid = low + (high - low) / 2;
        int pivot = medianOfThree(arr, low, mid, high);
        
        // Move pivot to end
        int temp = arr[pivot];
        arr[pivot] = arr[high];
        arr[high] = temp;

        return partition(arr, low, high);
    }

    /**
     * Find median of three elements for better pivot selection
     */
    private static int medianOfThree(int[] arr, int low, int mid, int high) {
        int a = arr[low], b = arr[mid], c = arr[high];
        
        if (a < b) {
            if (b < c) return mid;
            else if (a < c) return high;
            else return low;
        } else {
            if (a < c) return low;
            else if (b < c) return high;
            else return mid;
        }
    }

    /**
     * Quick Sort with random pivot selection
     * Provides probabilistic guarantees against bad inputs
     */
    public static void quickSortRandom(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionRandom(arr, low, high);
            quickSortRandom(arr, low, pi - 1);
            quickSortRandom(arr, pi + 1, high);
        }
    }

    /**
     * Partition method using random pivot selection
     */
    private static int partitionRandom(int[] arr, int low, int high) {
        // Choose random pivot
        int randomIndex = low + (int) (Math.random() * (high - low + 1));
        
        // Move random pivot to end
        int temp = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp;

        return partition(arr, low, high);
    }

    /**
     * Hybrid Sort combining Quick Sort and Insertion Sort
     * Uses Insertion Sort for small subarrays (≤10 elements)
     */
    public static void hybridSort(int[] arr, int low, int high) {
        if (high - low + 1 <= 10) {
            // Use insertion sort for small arrays
            insertionSortRange(arr, low, high);
        } else {
            // Use quick sort for larger arrays
            if (low < high) {
                int pi = partition(arr, low, high);
                hybridSort(arr, low, pi - 1);
                hybridSort(arr, pi + 1, high);
            }
        }
    }

    /**
     * Insertion Sort for a specific range of array
     */
    private static void insertionSortRange(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Main method - Entry point for the SearchSortOps demonstration
     * 
     * This method runs all the algorithm demonstrations in sequence,
     * providing a comprehensive overview of sorting and searching techniques.
     */
    public static void main(String[] args) {
        System.out.println("🚀 WELCOME TO SEARCH & SORT ALGORITHMS LEARNING");
        System.out.println("================================================");
        System.out.println("This program demonstrates various algorithms with");
        System.out.println("detailed explanations and practical examples.");
        System.out.println("================================================");
        System.out.println();
        
        // Run all algorithm demonstrations
        sortingAlgorithms();
        searchingAlgorithms();
        advancedSearching();
        sortingOptimizations();
        
        System.out.println("🎉 ALGORITHM DEMONSTRATION COMPLETE!");
        System.out.println("================================================");
        System.out.println("Key Takeaways:");
        System.out.println("• Understanding time/space complexity trade-offs");
        System.out.println("• Choosing the right algorithm for the job");
        System.out.println("• Implementing optimizations for better performance");
        System.out.println("• Learning from simple to advanced techniques");
        System.out.println("================================================");
    }
} 
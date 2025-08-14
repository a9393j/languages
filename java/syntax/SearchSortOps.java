package syntax;

import java.util.Arrays;

public class SearchSortOps {

    public static void sortingAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("SORTING ALGORITHMS");
        System.out.println("---------------------");

        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Bubble Sort
        int[] bubbleArr = arr.clone();
        bubbleSort(bubbleArr);
        System.out.println("Bubble Sort: " + Arrays.toString(bubbleArr));

        // Selection Sort
        int[] selectionArr = arr.clone();
        selectionSort(selectionArr);
        System.out.println("Selection Sort: " + Arrays.toString(selectionArr));

        // Insertion Sort
        int[] insertionArr = arr.clone();
        insertionSort(insertionArr);
        System.out.println("Insertion Sort: " + Arrays.toString(insertionArr));

        // Merge Sort
        int[] mergeArr = arr.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(mergeArr));

        // Quick Sort
        int[] quickArr = arr.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(quickArr));

        // Heap Sort
        int[] heapArr = arr.clone();
        heapSort(heapArr);
        System.out.println("Heap Sort: " + Arrays.toString(heapArr));

        // Counting Sort
        int[] countingArr = {4, 2, 1, 4, 1, 4, 2, 1, 3};
        countingSort(countingArr);
        System.out.println("Counting Sort: " + Arrays.toString(countingArr));
    }

    public static void searchingAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("SEARCHING ALGORITHMS");
        System.out.println("---------------------");

        int[] arr = {11, 12, 22, 25, 34, 64, 90};
        System.out.println("Sorted array: " + Arrays.toString(arr));

        int target = 25;
        System.out.println("Searching for: " + target);

        // Linear Search
        int linearIndex = linearSearch(arr, target);
        System.out.println("Linear Search index: " + linearIndex);

        // Binary Search
        int binaryIndex = binarySearch(arr, target);
        System.out.println("Binary Search index: " + binaryIndex);

        // Binary Search Recursive
        int binaryRecursiveIndex = binarySearchRecursive(arr, target, 0, arr.length - 1);
        System.out.println("Binary Search Recursive index: " + binaryRecursiveIndex);

        // Jump Search
        int jumpIndex = jumpSearch(arr, target);
        System.out.println("Jump Search index: " + jumpIndex);

        // Interpolation Search
        int interpolationIndex = interpolationSearch(arr, target);
        System.out.println("Interpolation Search index: " + interpolationIndex);
    }

    public static void advancedSearching() {
        System.out.println("------------------------------------------");
        System.out.println("ADVANCED SEARCHING");
        System.out.println("---------------------");

        // Find first and last occurrence
        int[] arr = {1, 2, 2, 2, 3, 4, 4, 4, 5};
        int target = 2;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);

        int firstIndex = findFirstOccurrence(arr, target);
        int lastIndex = findLastOccurrence(arr, target);
        System.out.println("First occurrence: " + firstIndex);
        System.out.println("Last occurrence: " + lastIndex);

        // Find peak element
        int[] peakArr = {1, 3, 20, 4, 1, 0};
        System.out.println("Peak array: " + Arrays.toString(peakArr));
        int peakIndex = findPeakElement(peakArr);
        System.out.println("Peak element index: " + peakIndex);

        // Find minimum in rotated sorted array
        int[] rotatedArr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Rotated array: " + Arrays.toString(rotatedArr));
        int minIndex = findMinInRotatedArray(rotatedArr);
        System.out.println("Minimum element index: " + minIndex);
    }

    public static void sortingOptimizations() {
        System.out.println("------------------------------------------");
        System.out.println("SORTING OPTIMIZATIONS");
        System.out.println("---------------------");

        // Quick Sort with different pivot strategies
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Quick Sort with median-of-three pivot
        int[] quickMedianArr = arr.clone();
        quickSortMedian(quickMedianArr, 0, quickMedianArr.length - 1);
        System.out.println("Quick Sort (median pivot): " + Arrays.toString(quickMedianArr));

        // Quick Sort with random pivot
        int[] quickRandomArr = arr.clone();
        quickSortRandom(quickRandomArr, 0, quickRandomArr.length - 1);
        System.out.println("Quick Sort (random pivot): " + Arrays.toString(quickRandomArr));

        // Hybrid Sort (Quick Sort + Insertion Sort for small arrays)
        int[] hybridArr = arr.clone();
        hybridSort(hybridArr, 0, hybridArr.length - 1);
        System.out.println("Hybrid Sort: " + Arrays.toString(hybridArr));
    }

    // Sorting Algorithms
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
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

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    public static void countingSort(int[] arr) {
        int n = arr.length;
        int max = Arrays.stream(arr).max().orElse(0);
        int min = Arrays.stream(arr).min().orElse(0);
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[n];

        for (int i = 0; i < n; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Searching Algorithms
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;

        while (prev < n && arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) return -1;
        }

        while (prev < Math.min(step, n)) {
            if (arr[prev] == target) {
                return prev;
            }
            prev++;
        }

        return -1;
    }

    public static int interpolationSearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right && target >= arr[left] && target <= arr[right]) {
            if (left == right) {
                if (arr[left] == target) return left;
                return -1;
            }

            int pos = left + (((right - left) / (arr[right] - arr[left])) * (target - arr[left]));

            if (arr[pos] == target) {
                return pos;
            } else if (arr[pos] < target) {
                left = pos + 1;
            } else {
                right = pos - 1;
            }
        }

        return -1;
    }

    // Advanced Searching
    public static int findFirstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching in left half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static int findLastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching in right half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int findMinInRotatedArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // Sorting Optimizations
    public static void quickSortMedian(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionMedian(arr, low, high);
            quickSortMedian(arr, low, pi - 1);
            quickSortMedian(arr, pi + 1, high);
        }
    }

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

    public static void quickSortRandom(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionRandom(arr, low, high);
            quickSortRandom(arr, low, pi - 1);
            quickSortRandom(arr, pi + 1, high);
        }
    }

    private static int partitionRandom(int[] arr, int low, int high) {
        // Choose random pivot
        int randomIndex = low + (int) (Math.random() * (high - low + 1));
        
        // Move random pivot to end
        int temp = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp;

        return partition(arr, low, high);
    }

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

    public static void main(String[] args) {
        sortingAlgorithms();
        searchingAlgorithms();
        advancedSearching();
        sortingOptimizations();
    }
} 
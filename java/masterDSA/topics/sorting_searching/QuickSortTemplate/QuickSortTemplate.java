/**
 * Quick Sort Template
 * 
 * Quick sort is a divide-and-conquer algorithm that works by selecting a 'pivot' element 
 * from the array and partitioning the other elements into two sub-arrays, according to 
 * whether they are less than or greater than the pivot.
 * 
 * LeetCode: (No direct LeetCode equivalent)
 * Difficulty: Medium
 * Tags: sorting, divide-and-conquer
 * 
 * Approach:
 * - Choose pivot element
 * - Partition array around pivot
 * - Recursively sort sub-arrays
 * 
 * Pseudocode:
 * 1. Choose pivot element
 * 2. Partition array: elements < pivot on left, elements > pivot on right
 * 3. Recursively sort left and right sub-arrays
 */

package masterDSA.topics.sorting_searching.QuickSortTemplate.QuickSortTemplate;

import java.util.*;

public class QuickSortTemplate {
    
    /**
     * Quick sort implementation - O(n log n) average time, O(n^2) worst case, O(log n) space
     * Most efficient approach for this problem
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        // Choose pivot (using last element)
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Alternative implementation with different pivot selection - O(n log n) average time, O(n^2) worst case, O(log n) space
     * Uses middle element as pivot
     */
    public static void quickSortMiddlePivot(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortMiddlePivotHelper(arr, 0, arr.length - 1);
    }
    
    private static void quickSortMiddlePivotHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionMiddlePivot(arr, low, high);
            quickSortMiddlePivotHelper(arr, low, pivotIndex - 1);
            quickSortMiddlePivotHelper(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partitionMiddlePivot(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        swap(arr, mid, high); // Move pivot to end
        
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Testing Quick Sort Template:");
        
        System.out.println("Original array: " + Arrays.toString(arr1));
        quickSort(arr1);
        System.out.println("Quick sort result: " + Arrays.toString(arr1));
        
        System.out.println("Original array: " + Arrays.toString(arr2));
        quickSortMiddlePivot(arr2);
        System.out.println("Middle pivot result: " + Arrays.toString(arr2));
    }
}
/**
 * Kth Largest Element in an Array
 * 
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in sorted order, not the kth distinct element.
 * 
 * LeetCode: https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Difficulty: Medium
 * Tags: heap, quickselect
 * 
 * Approach:
 * - Min heap: Keep only k largest elements
 * - Quickselect: Use partitioning to find kth largest
 * - Sorting: Sort array and return kth element
 * 
 * Pseudocode:
 * Min Heap:
 * 1. Create min heap of size k
 * 2. Add elements to heap
 * 3. If heap size > k, remove minimum
 * 4. Return root of heap
 */

package masterDSA.topics.heaps.KthLargestElementInAnArray.KthLargestElementInAnArray;

import java.util.*;

public class KthLargestElementInAnArray {
    
    /**
     * Min heap approach - O(n log k) time, O(k) space
     * Most efficient approach for this problem
     */
    public static int findKthLargest_minHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            
            // Keep only k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        return minHeap.peek();
    }
    
    /**
     * Quickselect approach - O(n) average time, O(1) space
     * Alternative approach using partitioning
     */
    public static int findKthLargest_quickSelect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private static int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
        int pivotIndex = partition(nums, left, right);
        
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }
    
    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        swap(nums, i, right);
        return i;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Sorting approach - O(n log n) time, O(1) space
     * Simple but less efficient
     */
    public static int findKthLargest_sorting(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        
        System.out.println("Testing Kth Largest Element in an Array:");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        
        int result1 = findKthLargest_minHeap(nums, k);
        System.out.println("Min heap result: " + result1);
        
        int result2 = findKthLargest_quickSelect(nums, k);
        System.out.println("Quickselect result: " + result2);
        
        int result3 = findKthLargest_sorting(nums, k);
        System.out.println("Sorting result: " + result3);
    }
}
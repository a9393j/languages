package masterDSA.topics.heaps;
import java.util.*;


/**
 * Kth Largest Element in an Array
 * 
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * LeetCode: https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Difficulty: Medium
 * Tags: heap, quickselect
 * 
 * Approach:
 * - Min heap: Maintain a min heap of size k
 * - Max heap: Use max heap and pop k-1 elements
 * - Quick select: Use partition algorithm for O(n) average time
 * - Sorting: Sort array and return kth element
 * 
 * Pseudocode:
 * Min Heap:
 * 1. Create min heap of size k
 * 2. For each element:
 *    a. If heap size < k, add element
 *    b. Else if element > heap.peek(), replace top element
 * 3. Return heap.peek()
 */
public class KthLargestElementInAnArray {
    
    /**
     * Min heap approach - O(n log k) time, O(k) space
     * Maintain a min heap of size k
     */
    public static int findKthLargest_minHeap(int[] nums, int k) {
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        
        return minHeap.peek();
    }
    
    /**
     * Max heap approach - O(n log n) time, O(n) space
     * Use max heap and pop k-1 elements
     */
    public static int findKthLargest_maxHeap(int[] nums, int k) {
        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>((a, b) -> b - a);
        
        for (int num : nums) {
            maxHeap.offer(num);
        }
        
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        
        return maxHeap.peek();
    }
    
    /**
     * Quick select approach - O(n) average time, O(1) space
     * Use partition algorithm similar to quicksort
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
     * Sort array and return kth element
     */
    public static int findKthLargest_sorting(int[] nums, int k) {
        java.util.Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    /**
     * Counting sort approach - O(n + m) time, O(m) space where m is range
     * Use counting sort for small range of values
     */
    public static int findKthLargest_countingSort(int[] nums, int k) {
        int min = java.util.Arrays.stream(nums).min().getAsInt();
        int max = java.util.Arrays.stream(nums).max().getAsInt();
        
        int[] count = new int[max - min + 1];
        
        for (int num : nums) {
            count[num - min]++;
        }
        
        int kthLargest = 0;
        for (int i = count.length - 1; i >= 0; i--) {
            kthLargest += count[i];
            if (kthLargest >= k) {
                return i + min;
            }
        }
        
        return -1;
    }
    
    /**
     * Helper method to print array
     */
    public static void printArray(int[] nums) {
        System.out.println(java.util.Arrays.toString(nums));
    }
    
    /**
     * Helper method to find kth smallest element
     */
    public static int findKthSmallest(int[] nums, int k) {
        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>((a, b) -> b - a);
        
        for (int num : nums) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }
        
        return maxHeap.peek();
    }
    
    /**
     * Helper method to find top k elements
     */
    public static java.util.List<Integer> findTopK(int[] nums, int k) {
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        
        return new java.util.ArrayList<>(minHeap);
    }
    
    public static void main(String[] args) {
        // Test case: nums = [3,2,1,5,6,4], k = 2
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        
        System.out.println("Testing Kth Largest Element in an Array:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("k = " + k1);
        
        int result1 = findKthLargest_minHeap(nums1, k1);
        System.out.println("Min heap result: " + result1);
        
        int result2 = findKthLargest_maxHeap(nums1, k1);
        System.out.println("Max heap result: " + result2);
        
        int result3 = findKthLargest_quickSelect(nums1, k1);
        System.out.println("Quick select result: " + result3);
        
        int result4 = findKthLargest_sorting(nums1, k1);
        System.out.println("Sorting result: " + result4);
        
        int result5 = findKthLargest_countingSort(nums1, k1);
        System.out.println("Counting sort result: " + result5);
        
        // Expected: 5
        
        System.out.println();
        
        // Test case: nums = [3,2,3,1,2,4,5,5,6], k = 4
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        System.out.println("k = " + k2);
        
        int result6 = findKthLargest_minHeap(nums2, k2);
        System.out.println("Min heap result: " + result6);
        
        // Expected: 4
        
        System.out.println();
        
        // Test helper methods
        int[] nums3 = {3, 2, 1, 5, 6, 4};
        
        int kthSmallest = findKthSmallest(nums3, 2);
        System.out.println("2nd smallest: " + kthSmallest);
        
        java.util.List<Integer> topK = findTopK(nums3, 3);
        System.out.println("Top 3 elements: " + topK);
        
        // Expected: 2nd smallest = 2, Top 3 = [4, 5, 6]
    }
}

/**
 * Search in Rotated Sorted Array
 * 
 * There is an integer array nums sorted in ascending order (with distinct values). Prior to 
 * being passed to your function, nums is possibly rotated at an unknown pivot index k 
 * (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], 
 * nums[0], nums[1], ..., nums[k-1]].
 * 
 * LeetCode: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Difficulty: Medium
 * Tags: binary-search, rotated-array
 * 
 * Approach:
 * - Use binary search with rotation handling
 * - Check which half is sorted
 * - Search in appropriate half
 * 
 * Pseudocode:
 * 1. Use binary search
 * 2. Check if left half is sorted
 * 3. If target in sorted half, search there
 * 4. Otherwise, search in other half
 */

import java.util.*;

public class SearchInRotatedSortedArray {
    
    /**
     * Binary search approach - O(log n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static int search_binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Alternative approach using pivot finding - O(log n) time, O(1) space
     * Find pivot first, then search in appropriate half
     */
    public static int search_pivotFirst(int[] nums, int target) {
        int pivot = findPivot(nums);
        
        if (pivot == -1) {
            // Array is not rotated
            return binarySearch(nums, target, 0, nums.length - 1);
        }
        
        if (nums[pivot] == target) {
            return pivot;
        }
        
        if (target >= nums[0]) {
            // Search in left half
            return binarySearch(nums, target, 0, pivot - 1);
        } else {
            // Search in right half
            return binarySearch(nums, target, pivot + 1, nums.length - 1);
        }
    }
    
    private static int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (mid > left && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }
            
            if (nums[left] >= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return -1;
    }
    
    private static int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        
        int[] nums3 = {1};
        int target3 = 0;
        
        System.out.println("Testing Search in Rotated Sorted Array:");
        
        System.out.println("Array: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("Binary search result: " + search_binarySearch(nums1, target1));
        System.out.println("Pivot first result: " + search_pivotFirst(nums1, target1));
        
        System.out.println("Array: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("Binary search result: " + search_binarySearch(nums2, target2));
        
        System.out.println("Array: " + Arrays.toString(nums3) + ", Target: " + target3);
        System.out.println("Binary search result: " + search_binarySearch(nums3, target3));
    }
}
package masterDSA.topics.sorting_searching.FindFirstAndLastPositionOfElementInSortedArray;
import java.util.*;


/**
 * Find First and Last Position of Element in Sorted Array
 * 
 * Given an array of integers nums sorted in non-decreasing order, find the starting 
 * and ending position of a given target value.
 * 
 * LeetCode: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Difficulty: Medium
 * Tags: binary-search, array
 * 
 * Approach:
 * - Two binary searches: Find first and last occurrence separately
 * - Single binary search: Find first occurrence, then search for last
 * - Template approach: Use binary search template
 * 
 * Pseudocode:
 * Two Binary Searches:
 * 1. Find first occurrence using binary search
 * 2. Find last occurrence using binary search
 * 3. Return [first, last] or [-1, -1] if not found
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    
    /**
     * Two binary searches approach - O(log n) time, O(1) space
     * Find first and last occurrence separately
     */
    public static int[] searchRange_twoBinarySearches(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstOccurrence(nums, target);
        int last = findLastOccurrence(nums, target);
        
        return new int[]{first, last};
    }
    
    private static int findFirstOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    private static int findLastOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching right
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Single binary search approach - O(log n) time, O(1) space
     * Find first occurrence, then search for last
     */
    public static int[] searchRange_singleBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstOccurrence(nums, target);
        
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        int last = findLastOccurrence(nums, target);
        
        return new int[]{first, last};
    }
    
    /**
     * Template approach - O(log n) time, O(1) space
     * Use binary search template
     */
    public static int[] searchRange_template(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstTemplate(nums, target);
        int last = findLastTemplate(nums, target);
        
        return new int[]{first, last};
    }
    
    private static int findFirstTemplate(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        if (left < nums.length && nums[left] == target) {
            return left;
        }
        
        return -1;
    }
    
    private static int findLastTemplate(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        if (left > 0 && nums[left - 1] == target) {
            return left - 1;
        }
        
        return -1;
    }
    
    /**
     * Helper method to count occurrences of target
     */
    public static int countOccurrences(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int first = findFirstOccurrence(nums, target);
        if (first == -1) {
            return 0;
        }
        
        int last = findLastOccurrence(nums, target);
        return last - first + 1;
    }
    
    /**
     * Helper method to find all occurrences
     */
    public static java.util.List<Integer> findAllOccurrences(int[] nums, int target) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int first = findFirstOccurrence(nums, target);
        if (first == -1) {
            return result;
        }
        
        int last = findLastOccurrence(nums, target);
        
        for (int i = first; i <= last; i++) {
            result.add(i);
        }
        
        return result;
    }
    
    /**
     * Helper method to find kth occurrence
     */
    public static int findKthOccurrence(int[] nums, int target, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return -1;
        }
        
        int first = findFirstOccurrence(nums, target);
        if (first == -1) {
            return -1;
        }
        
        int last = findLastOccurrence(nums, target);
        int totalOccurrences = last - first + 1;
        
        if (k > totalOccurrences) {
            return -1;
        }
        
        return first + k - 1;
    }
    
    /**
     * Helper method to find occurrences in range
     */
    public static int[] searchRangeInRange(int[] nums, int target, int start, int end) {
        if (nums == null || nums.length == 0 || start < 0 || end >= nums.length || start > end) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstOccurrenceInRange(nums, target, start, end);
        int last = findLastOccurrenceInRange(nums, target, start, end);
        
        return new int[]{first, last};
    }
    
    private static int findFirstOccurrenceInRange(int[] nums, int target, int start, int end) {
        int left = start;
        int right = end;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    private static int findLastOccurrenceInRange(int[] nums, int target, int start, int end) {
        int left = start;
        int right = end;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find closest occurrences
     */
    public static int[] findClosestOccurrences(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int left = 0;
        int right = nums.length - 1;
        int closestLeft = -1;
        int closestRight = -1;
        
        // Find closest element <= target
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                closestLeft = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        left = 0;
        right = nums.length - 1;
        
        // Find closest element >= target
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] >= target) {
                closestRight = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return new int[]{closestLeft, closestRight};
    }
    
    /**
     * Helper method to find occurrences with minimum distance
     */
    public static int[] findOccurrencesWithMinDistance(int[] nums, int target, int minDistance) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstOccurrence(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        int last = findLastOccurrence(nums, target);
        
        // Check if distance is sufficient
        if (last - first + 1 < minDistance) {
            return new int[]{-1, -1};
        }
        
        return new int[]{first, last};
    }
    
    /**
     * Helper method to find occurrences with maximum distance
     */
    public static int[] findOccurrencesWithMaxDistance(int[] nums, int target, int maxDistance) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstOccurrence(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        int last = findLastOccurrence(nums, target);
        
        // Check if distance is within limit
        if (last - first + 1 > maxDistance) {
            return new int[]{-1, -1};
        }
        
        return new int[]{first, last};
    }
    
    /**
     * Helper method to find occurrences with exact distance
     */
    public static int[] findOccurrencesWithExactDistance(int[] nums, int target, int exactDistance) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstOccurrence(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        int last = findLastOccurrence(nums, target);
        
        // Check if distance matches exactly
        if (last - first + 1 != exactDistance) {
            return new int[]{-1, -1};
        }
        
        return new int[]{first, last};
    }
    
    public static void main(String[] args) {
        // Test case: nums = [5,7,7,8,8,10], target = 8
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        
        System.out.println("Testing Find First and Last Position of Element in Sorted Array:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("Target: " + target1);
        
        int[] result1 = searchRange_twoBinarySearches(nums1, target1);
        System.out.println("Two binary searches result: " + java.util.Arrays.toString(result1));
        
        int[] result2 = searchRange_singleBinarySearch(nums1, target1);
        System.out.println("Single binary search result: " + java.util.Arrays.toString(result2));
        
        int[] result3 = searchRange_template(nums1, target1);
        System.out.println("Template result: " + java.util.Arrays.toString(result3));
        
        // Expected: [3, 4]
        
        System.out.println();
        
        // Test case: nums = [5,7,7,8,8,10], target = 6
        int target2 = 6;
        
        System.out.println("Target: " + target2);
        
        int[] result4 = searchRange_twoBinarySearches(nums1, target2);
        System.out.println("Two binary searches result: " + java.util.Arrays.toString(result4));
        
        // Expected: [-1, -1]
        
        System.out.println();
        
        // Test case: nums = [], target = 0
        int[] nums2 = {};
        int target3 = 0;
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        System.out.println("Target: " + target3);
        
        int[] result5 = searchRange_twoBinarySearches(nums2, target3);
        System.out.println("Two binary searches result: " + java.util.Arrays.toString(result5));
        
        // Expected: [-1, -1]
        
        System.out.println();
        
        // Test helper methods
        int count = countOccurrences(nums1, 8);
        System.out.println("Count of occurrences of 8: " + count);
        
        java.util.List<Integer> allOccurrences = findAllOccurrences(nums1, 8);
        System.out.println("All occurrences of 8: " + allOccurrences);
        
        int kthOccurrence = findKthOccurrence(nums1, 8, 2);
        System.out.println("2nd occurrence of 8: " + kthOccurrence);
        
        int[] rangeInRange = searchRangeInRange(nums1, 8, 1, 4);
        System.out.println("Range of 8 in [1,4]: " + java.util.Arrays.toString(rangeInRange));
        
        int[] closest = findClosestOccurrences(nums1, 6);
        System.out.println("Closest occurrences of 6: " + java.util.Arrays.toString(closest));
        
        int[] minDistance = findOccurrencesWithMinDistance(nums1, 8, 2);
        System.out.println("Occurrences with min distance 2: " + java.util.Arrays.toString(minDistance));
        
        int[] maxDistance = findOccurrencesWithMaxDistance(nums1, 8, 3);
        System.out.println("Occurrences with max distance 3: " + java.util.Arrays.toString(maxDistance));
        
        int[] exactDistance = findOccurrencesWithExactDistance(nums1, 8, 2);
        System.out.println("Occurrences with exact distance 2: " + java.util.Arrays.toString(exactDistance));
        
        // Expected: Count = 2, All occurrences = [3, 4], 2nd occurrence = 4, Range in [1,4] = [3, 4], Closest occurrences = [2, 3], Min distance 2 = [3, 4], Max distance 3 = [3, 4], Exact distance 2 = [3, 4]
    }
}

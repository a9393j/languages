package masterDSA.topics.sorting_searching;
import java.util.*;


/**
 * Search in Rotated Sorted Array
 * 
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k.
 * 
 * LeetCode: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Difficulty: Medium
 * Tags: binary-search, array
 * 
 * Approach:
 * - Binary search with rotation detection
 * - Find pivot point first, then search
 * - Modified binary search for rotated array
 * 
 * Pseudocode:
 * Modified Binary Search:
 * 1. Find mid element
 * 2. If nums[mid] == target, return mid
 * 3. If left half is sorted and target in range, search left
 * 4. If right half is sorted and target in range, search right
 * 5. Otherwise, search the other half
 */
public class SearchInRotatedSortedArray {
    
    /**
     * Modified binary search approach - O(log n) time, O(1) space
     * Use binary search with rotation detection
     */
    public static int search_modifiedBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
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
     * Find pivot first approach - O(log n) time, O(1) space
     * Find pivot point first, then search
     */
    public static int search_findPivotFirst(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int pivot = findPivot(nums);
        
        if (pivot == -1) {
            // Array is not rotated
            return binarySearch(nums, target, 0, nums.length - 1);
        }
        
        if (nums[pivot] == target) {
            return pivot;
        }
        
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        } else {
            return binarySearch(nums, target, pivot + 1, nums.length - 1);
        }
    }
    
    private static int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
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
     * Recursive approach - O(log n) time, O(log n) space
     * Use recursion with rotation detection
     */
    public static int search_recursive(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        return searchHelper(nums, target, 0, nums.length - 1);
    }
    
    private static int searchHelper(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            return mid;
        }
        
        // Check if left half is sorted
        if (nums[left] <= nums[mid]) {
            // Left half is sorted
            if (target >= nums[left] && target < nums[mid]) {
                return searchHelper(nums, target, left, mid - 1);
            } else {
                return searchHelper(nums, target, mid + 1, right);
            }
        } else {
            // Right half is sorted
            if (target > nums[mid] && target <= nums[right]) {
                return searchHelper(nums, target, mid + 1, right);
            } else {
                return searchHelper(nums, target, left, mid - 1);
            }
        }
    }
    
    /**
     * Helper method to find minimum element in rotated array
     */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return nums[left];
    }
    
    /**
     * Helper method to find maximum element in rotated array
     */
    public static int findMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[left]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        
        return nums[left];
    }
    
    /**
     * Helper method to find pivot index
     */
    public static int findPivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Helper method to check if array is rotated
     */
    public static boolean isRotated(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        return nums[0] > nums[nums.length - 1];
    }
    
    /**
     * Helper method to find rotation count
     */
    public static int findRotationCount(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int pivot = findPivotIndex(nums);
        return pivot;
    }
    
    /**
     * Helper method to search in rotated array with duplicates
     */
    public static boolean searchWithDuplicates(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Handle duplicates
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
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
        
        return false;
    }
    
    /**
     * Helper method to find peak element in rotated array
     */
    public static int findPeakInRotated(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                return mid;
            }
            
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Helper method to find valley element in rotated array
     */
    public static int findValleyInRotated(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[mid + 1]) {
                return mid;
            }
            
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Helper method to restore rotated array
     */
    public static int[] restoreRotatedArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        int pivot = findPivotIndex(nums);
        if (pivot == 0) {
            return nums; // Already sorted
        }
        
        int[] result = new int[nums.length];
        System.arraycopy(nums, pivot, result, 0, nums.length - pivot);
        System.arraycopy(nums, 0, result, nums.length - pivot, pivot);
        
        return result;
    }
    
    /**
     * Helper method to find median in rotated array
     */
    public static double findMedianInRotated(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int[] sorted = restoreRotatedArray(nums);
        int n = sorted.length;
        
        if (n % 2 == 1) {
            return sorted[n / 2];
        } else {
            return (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0;
        }
    }
    
    public static void main(String[] args) {
        // Test case: nums = [4,5,6,7,0,1,2], target = 0
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        
        System.out.println("Testing Search in Rotated Sorted Array:");
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Target: " + target1);
        
        int result1 = search_modifiedBinarySearch(nums1, target1);
        System.out.println("Modified binary search result: " + result1);
        
        int result2 = search_findPivotFirst(nums1, target1);
        System.out.println("Find pivot first result: " + result2);
        
        int result3 = search_recursive(nums1, target1);
        System.out.println("Recursive result: " + result3);
        
        // Expected: 4
        
        System.out.println();
        
        // Test case: nums = [4,5,6,7,0,1,2], target = 3
        int target2 = 3;
        
        System.out.println("Target: " + target2);
        
        int result4 = search_modifiedBinarySearch(nums1, target2);
        System.out.println("Modified binary search result: " + result4);
        
        // Expected: -1
        
        System.out.println();
        
        // Test case: nums = [1], target = 0
        int[] nums2 = {1};
        int target3 = 0;
        
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Target: " + target3);
        
        int result5 = search_modifiedBinarySearch(nums2, target3);
        System.out.println("Modified binary search result: " + result5);
        
        // Expected: -1
        
        System.out.println();
        
        // Test helper methods
        int min = findMin(nums1);
        System.out.println("Minimum element: " + min);
        
        int max = findMax(nums1);
        System.out.println("Maximum element: " + max);
        
        int pivotIndex = findPivotIndex(nums1);
        System.out.println("Pivot index: " + pivotIndex);
        
        boolean rotated = isRotated(nums1);
        System.out.println("Is rotated: " + rotated);
        
        int rotationCount = findRotationCount(nums1);
        System.out.println("Rotation count: " + rotationCount);
        
        int[] nums3 = {2, 5, 6, 0, 0, 1, 2};
        boolean searchWithDup = searchWithDuplicates(nums3, 0);
        System.out.println("Search with duplicates: " + searchWithDup);
        
        int peak = findPeakInRotated(nums1);
        System.out.println("Peak element index: " + peak);
        
        int valley = findValleyInRotated(nums1);
        System.out.println("Valley element index: " + valley);
        
        int[] restored = restoreRotatedArray(nums1);
        System.out.println("Restored array: " + Arrays.toString(restored));
        
        double median = findMedianInRotated(nums1);
        System.out.println("Median: " + median);
        
        // Expected: Minimum = 0, Maximum = 7, Pivot index = 4, Is rotated = true, Rotation count = 4, Search with duplicates = true, Peak element index = 3, Valley element index = 4, Restored array = [0, 1, 2, 4, 5, 6, 7], Median = 4.0
    }
}

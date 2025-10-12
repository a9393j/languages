package masterDSA.topics.patterns_misc;
import java.util.*;


/**
 * Modified Binary Search Pattern
 * 
 * Problem Statement:
 * The modified binary search pattern extends the basic binary search to handle
 * variations like finding first/last occurrence, search in rotated arrays,
 * and other specialized search scenarios.
 * 
 * LeetCode Link: https://leetcode.com/tag/binary-search/
 * Difficulty: Medium
 * Tags: Array, Binary Search
 * 
 * Approach Summary:
 * 1. Find first occurrence of target
 * 2. Find last occurrence of target
 * 3. Search in rotated sorted array
 * 4. Find peak element
 * 5. Search in 2D matrix
 * 6. Find square root
 * 
 * Pseudocode:
 * 1. Initialize left = 0, right = n - 1
 * 2. While left <= right:
 *    - Calculate mid = left + (right - left) / 2
 *    - Compare mid with target
 *    - Adjust left or right based on comparison
 * 3. Return result based on problem requirements
 * 
 * Time Complexity: O(log n) - logarithmic time
 * Space Complexity: O(1) - constant space
 */
public class ModifiedBinarySearchPattern {
    
    /**
     * Find first occurrence of target
     * 
     * @param nums Sorted array
     * @param target Target value
     * @return Index of first occurrence, -1 if not found
     */
    public static int findFirst(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching in left half
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Find last occurrence of target
     * 
     * @param nums Sorted array
     * @param target Target value
     * @return Index of last occurrence, -1 if not found
     */
    public static int findLast(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching in right half
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Search in rotated sorted array
     * 
     * @param nums Rotated sorted array
     * @param target Target value
     * @return Index of target, -1 if not found
     */
    public static int searchRotated(int[] nums, int target) {
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
            
            // Check which half is sorted
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
     * Find minimum in rotated sorted array
     * 
     * @param nums Rotated sorted array
     * @return Minimum element
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
     * Find peak element
     * 
     * @param nums Array of integers
     * @return Index of peak element
     */
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    /**
     * Search in 2D matrix
     * 
     * @param matrix 2D matrix sorted row-wise and column-wise
     * @param target Target value
     * @return True if target exists
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * Find square root
     * 
     * @param x Non-negative integer
     * @return Square root (truncated)
     */
    public static int sqrt(int x) {
        if (x < 2) {
            return x;
        }
        
        int left = 1;
        int right = x / 2;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            
            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    /**
     * Find square root with precision
     * 
     * @param x Non-negative integer
     * @param precision Number of decimal places
     * @return Square root with specified precision
     */
    public static double sqrtPrecision(int x, int precision) {
        if (x < 0) {
            return -1;
        }
        
        double left = 0;
        double right = x;
        double result = 0;
        
        // Binary search for integer part
        while (left <= right) {
            double mid = left + (right - left) / 2;
            double square = mid * mid;
            
            if (Math.abs(square - x) < 0.0001) {
                result = mid;
                break;
            } else if (square < x) {
                left = mid + 0.0001;
                result = mid;
            } else {
                right = mid - 0.0001;
            }
        }
        
        return Math.round(result * Math.pow(10, precision)) / Math.pow(10, precision);
    }
    
    /**
     * Find k closest elements
     * 
     * @param arr Sorted array
     * @param k Number of closest elements
     * @param x Target value
     * @return List of k closest elements
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.length == 0 || k <= 0) {
            return result;
        }
        
        int left = 0;
        int right = arr.length - k;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Compare distances from x
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        
        return result;
    }
    
    /**
     * Find first and last position of element
     * 
     * @param nums Sorted array
     * @param target Target value
     * @return Array containing first and last position
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        
        return result;
    }
    
    /**
     * Find insertion position
     * 
     * @param nums Sorted array
     * @param target Target value
     * @return Index where target should be inserted
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
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
        
        return left;
    }
    
    /**
     * Find single element in sorted array
     * 
     * @param nums Sorted array with one single element
     * @return Single element
     */
    public static int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Ensure mid is even
            if (mid % 2 == 1) {
                mid--;
            }
            
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        
        return nums[left];
    }
    
    /**
     * Find median of two sorted arrays
     * 
     * @param nums1 First sorted array
     * @param nums2 Second sorted array
     * @return Median of combined arrays
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int left = 0;
        int right = m;
        
        while (left <= right) {
            int partitionX = left + (right - left) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;
            
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Helper method to print array
     */
    private static void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
    
    /**
     * Demonstration method showing various modified binary search patterns
     */
    public static void runExample() {
        System.out.println("=== Modified Binary Search Pattern Examples ===");
        
        // Find first occurrence
        int[] nums1 = {1, 2, 2, 2, 3, 4, 5};
        int target1 = 2;
        int first = findFirst(nums1, target1);
        System.out.println("First occurrence of " + target1 + ": " + first);
        
        // Find last occurrence
        int[] nums2 = {1, 2, 2, 2, 3, 4, 5};
        int target2 = 2;
        int last = findLast(nums2, target2);
        System.out.println("Last occurrence of " + target2 + ": " + last);
        
        // Search in rotated array
        int[] nums3 = {4, 5, 6, 7, 0, 1, 2};
        int target3 = 0;
        int rotated = searchRotated(nums3, target3);
        System.out.println("Target " + target3 + " found at index: " + rotated);
        
        // Find minimum in rotated array
        int[] nums4 = {4, 5, 6, 7, 0, 1, 2};
        int min = findMin(nums4);
        System.out.println("Minimum in rotated array: " + min);
        
        // Find peak element
        int[] nums5 = {1, 2, 3, 1};
        int peak = findPeakElement(nums5);
        System.out.println("Peak element index: " + peak);
        
        // Search in 2D matrix
        int[][] matrix = {{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}, {10, 13, 14, 17}};
        int target4 = 5;
        boolean found = searchMatrix(matrix, target4);
        System.out.println("Target " + target4 + " found in matrix: " + found);
        
        // Find square root
        int x = 8;
        int sqrt = sqrt(x);
        System.out.println("Square root of " + x + ": " + sqrt);
        
        // Find square root with precision
        int x2 = 8;
        double sqrtPrecise = sqrtPrecision(x2, 2);
        System.out.println("Square root of " + x2 + " (2 decimal places): " + sqrtPrecise);
        
        // Find k closest elements
        int[] nums6 = {1, 2, 3, 4, 5};
        int k = 4;
        int x3 = 3;
        List<Integer> closest = findClosestElements(nums6, k, x3);
        System.out.println("K closest elements to " + x3 + ": " + closest);
        
        // Find first and last position
        int[] nums7 = {5, 7, 7, 8, 8, 10};
        int target5 = 8;
        int[] range = searchRange(nums7, target5);
        System.out.println("First and last position of " + target5 + ": " + Arrays.toString(range));
        
        // Find insertion position
        int[] nums8 = {1, 3, 5, 6};
        int target6 = 5;
        int insertPos = searchInsert(nums8, target6);
        System.out.println("Insert position for " + target6 + ": " + insertPos);
        
        // Find single element
        int[] nums9 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        int single = singleNonDuplicate(nums9);
        System.out.println("Single element: " + single);
        
        // Find median of two sorted arrays
        int[] nums10 = {1, 3};
        int[] nums11 = {2};
        double median = findMedianSortedArrays(nums10, nums11);
        System.out.println("Median of two sorted arrays: " + median);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

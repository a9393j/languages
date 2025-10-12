package masterDSA.topics.sorting_searching;
import java.util.*;


/**
 * Binary Search
 * 
 * Given an array of integers nums which is sorted in ascending order, and an integer target, 
 * write a function to search target in nums. If target exists, then return its index. 
 * Otherwise, return -1.
 * 
 * LeetCode: https://leetcode.com/problems/binary-search/
 * Difficulty: Easy
 * Tags: binary-search, array
 * 
 * Approach:
 * - Iterative: Use while loop with left and right pointers
 * - Recursive: Use recursion with left and right parameters
 * - Template: Use binary search template for different problems
 * 
 * Pseudocode:
 * Iterative:
 * 1. Initialize left = 0, right = n - 1
 * 2. While left <= right:
 *    a. Calculate mid = left + (right - left) / 2
 *    b. If nums[mid] == target, return mid
 *    c. If nums[mid] < target, left = mid + 1
 *    d. Else right = mid - 1
 * 3. Return -1
 */
public class BinarySearch {
    
    /**
     * Iterative approach - O(log n) time, O(1) space
     * Use while loop with left and right pointers
     */
    public static int search_iterative(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
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
        
        return -1;
    }
    
    /**
     * Recursive approach - O(log n) time, O(log n) space
     * Use recursion with left and right parameters
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
        } else if (nums[mid] < target) {
            return searchHelper(nums, target, mid + 1, right);
        } else {
            return searchHelper(nums, target, left, mid - 1);
        }
    }
    
    /**
     * Template approach - O(log n) time, O(1) space
     * Use binary search template for different problems
     */
    public static int search_template(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return nums[left] == target ? left : -1;
    }
    
    /**
     * Helper method to find first occurrence of target
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
                right = mid - 1; // Continue searching left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find last occurrence of target
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
     * Helper method to find insertion position
     */
    public static int findInsertPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    /**
     * Helper method to find floor value
     */
    public static int findFloor(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                result = nums[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find ceiling value
     */
    public static int findCeiling(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] >= target) {
                result = nums[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find peak element
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
     * Helper method to find minimum in rotated sorted array
     */
    public static int findMinInRotated(int[] nums) {
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
     * Helper method to find maximum in rotated sorted array
     */
    public static int findMaxInRotated(int[] nums) {
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
     * Helper method to find square root
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
     * Helper method to find perfect square
     */
    public static boolean isPerfectSquare(int num) {
        if (num < 1) {
            return false;
        }
        
        int left = 1;
        int right = num;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            
            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * Helper method to find two sum in sorted array
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[]{-1, -1};
    }
    
    /**
     * Helper method to find three sum in sorted array
     */
    public static java.util.List<java.util.List<Integer>> threeSum(int[] nums, int target) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicates
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == target) {
                    result.add(java.util.Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test case: nums = [-1,0,3,5,9,12], target = 9
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        
        System.out.println("Testing Binary Search:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("Target: " + target1);
        
        int result1 = search_iterative(nums1, target1);
        System.out.println("Iterative result: " + result1);
        
        int result2 = search_recursive(nums1, target1);
        System.out.println("Recursive result: " + result2);
        
        int result3 = search_template(nums1, target1);
        System.out.println("Template result: " + result3);
        
        // Expected: 4
        
        System.out.println();
        
        // Test case: nums = [-1,0,3,5,9,12], target = 2
        int target2 = 2;
        
        System.out.println("Target: " + target2);
        
        int result4 = search_iterative(nums1, target2);
        System.out.println("Iterative result: " + result4);
        
        // Expected: -1
        
        System.out.println();
        
        // Test helper methods
        int[] nums2 = {1, 2, 2, 2, 3, 4, 5};
        int target3 = 2;
        
        int first = findFirst(nums2, target3);
        System.out.println("First occurrence of " + target3 + ": " + first);
        
        int last = findLast(nums2, target3);
        System.out.println("Last occurrence of " + target3 + ": " + last);
        
        int insertPos = findInsertPosition(nums2, 6);
        System.out.println("Insert position for 6: " + insertPos);
        
        int floor = findFloor(nums2, 3);
        System.out.println("Floor of 3: " + floor);
        
        int ceiling = findCeiling(nums2, 3);
        System.out.println("Ceiling of 3: " + ceiling);
        
        int[] nums3 = {1, 2, 3, 1};
        int peak = findPeakElement(nums3);
        System.out.println("Peak element index: " + peak);
        
        int[] nums4 = {4, 5, 6, 7, 0, 1, 2};
        int min = findMinInRotated(nums4);
        System.out.println("Minimum in rotated array: " + min);
        
        int max = findMaxInRotated(nums4);
        System.out.println("Maximum in rotated array: " + max);
        
        int sqrt = sqrt(8);
        System.out.println("Square root of 8: " + sqrt);
        
        boolean perfectSquare = isPerfectSquare(16);
        System.out.println("Is 16 a perfect square: " + perfectSquare);
        
        int[] nums5 = {2, 7, 11, 15};
        int[] twoSum = twoSum(nums5, 9);
        System.out.println("Two sum indices: " + java.util.Arrays.toString(twoSum));
        
        int[] nums6 = {-1, 0, 1, 2, -1, -4};
        java.util.Arrays.sort(nums6);
        java.util.List<java.util.List<Integer>> threeSum = threeSum(nums6, 0);
        System.out.println("Three sum: " + threeSum);
        
        // Expected: First occurrence = 1, Last occurrence = 3, Insert position = 7, Floor = 2, Ceiling = 3, Peak element index = 2, Minimum = 0, Maximum = 7, Square root = 2, Perfect square = true, Two sum = [0, 1], Three sum = [[-1, -1, 2], [-1, 0, 1]]
    }
}

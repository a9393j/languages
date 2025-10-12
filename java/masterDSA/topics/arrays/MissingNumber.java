package masterDSA.topics.arrays;
import java.util.*;


/**
 * Missing Number
 * 
 * Given an array nums containing n distinct numbers in the range [0, n], 
 * return the only number in the range that is missing from the array.
 * 
 * LeetCode: https://leetcode.com/problems/missing-number/
 * Difficulty: Easy
 * Tags: xor, math
 * 
 * Approach:
 * - Mathematical approach: Use sum formula to find expected sum, subtract actual sum
 * - XOR approach: XOR all numbers from 0 to n with all numbers in array
 * - Sorting approach: Sort array and find first missing number
 * - Hash set approach: Store all numbers in set, find missing one
 * 
 * Pseudocode:
 * Mathematical:
 * 1. Calculate expected sum = n * (n + 1) / 2
 * 2. Calculate actual sum of array elements
 * 3. Return expected sum - actual sum
 * 
 * XOR:
 * 1. Initialize result = 0
 * 2. XOR result with all numbers from 0 to n
 * 3. XOR result with all numbers in array
 * 4. Return result
 */
public class MissingNumber {
    
    /**
     * Mathematical approach - O(n) time, O(1) space
     * Use sum formula to find expected sum, subtract actual sum
     */
    public static int missingNumber_math(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2; // Sum of numbers from 0 to n
        
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }
    
    /**
     * XOR approach - O(n) time, O(1) space
     * XOR all numbers from 0 to n with all numbers in array
     */
    public static int missingNumber_xor(int[] nums) {
        int result = 0;
        
        // XOR with all numbers from 0 to n
        for (int i = 0; i <= nums.length; i++) {
            result ^= i;
        }
        
        // XOR with all numbers in array
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
    
    /**
     * Sorting approach - O(n log n) time, O(1) space
     * Sort array and find first missing number
     */
    public static int missingNumber_sorting(int[] nums) {
        java.util.Arrays.sort(nums);
        
        // Check if 0 is missing
        if (nums[0] != 0) {
            return 0;
        }
        
        // Check for missing number in middle
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                return nums[i - 1] + 1;
            }
        }
        
        // If no missing number found, it must be n
        return nums.length;
    }
    
    /**
     * Hash set approach - O(n) time, O(n) space
     * Store all numbers in set, find missing one
     */
    public static int missingNumber_hashSet(int[] nums) {
        java.util.Set<Integer> numSet = new java.util.HashSet<>();
        
        // Add all numbers to set
        for (int num : nums) {
            numSet.add(num);
        }
        
        // Find missing number
        for (int i = 0; i <= nums.length; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        
        return -1; // Should never reach here
    }
    
    /**
     * Binary search approach - O(n log n) time, O(1) space
     * Sort array and use binary search to find missing number
     */
    public static int missingNumber_binarySearch(int[] nums) {
        java.util.Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == mid) {
                // Missing number is to the right
                left = mid + 1;
            } else {
                // Missing number is to the left
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * For each number from 0 to n, check if it exists in array
     */
    public static int missingNumber_bruteForce(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            boolean found = false;
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return i;
            }
        }
        
        return -1; // Should never reach here
    }
    
    public static void main(String[] args) {
        // Test case: nums = [3,0,1]
        int[] nums1 = {3, 0, 1};
        
        System.out.println("Testing Missing Number:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        
        int result1 = missingNumber_math(nums1);
        System.out.println("Math result: " + result1);
        
        int result2 = missingNumber_xor(nums1);
        System.out.println("XOR result: " + result2);
        
        int result3 = missingNumber_sorting(nums1);
        System.out.println("Sorting result: " + result3);
        
        int result4 = missingNumber_hashSet(nums1);
        System.out.println("Hash set result: " + result4);
        
        int result5 = missingNumber_binarySearch(nums1);
        System.out.println("Binary search result: " + result5);
        
        int result6 = missingNumber_bruteForce(nums1);
        System.out.println("Brute force result: " + result6);
        
        // Expected: 2
        
        System.out.println();
        
        // Test case: nums = [0,1]
        int[] nums2 = {0, 1};
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        
        int result7 = missingNumber_math(nums2);
        System.out.println("Math result: " + result7);
        
        // Expected: 2
        
        System.out.println();
        
        // Test case: nums = [9,6,4,2,3,5,7,0,1]
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        
        int result8 = missingNumber_math(nums3);
        System.out.println("Math result: " + result8);
        
        // Expected: 8
    }
}

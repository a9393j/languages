package masterDSA.topics.arrays.TwoSum;
import java.util.*;


/**
 * Two Sum
 * 
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 
 * LeetCode: https://leetcode.com/problems/two-sum/
 * Difficulty: Easy
 * Tags: hashmap, two-pointers
 * 
 * Approach:
 * - Hashmap approach: Store each number and its index in a hashmap
 * - For each number, check if target - current_number exists in hashmap
 * - Two pointers approach: Sort array with indices, use two pointers from start and end
 * 
 * Pseudocode:
 * Hashmap:
 * 1. Create hashmap to store number -> index
 * 2. For each number in array:
 *    a. If target - current_number exists in hashmap, return indices
 *    b. Else store current_number -> index in hashmap
 * 
 * Two Pointers:
 * 1. Create array of {value, original_index} pairs
 * 2. Sort by value
 * 3. Use two pointers from start and end
 * 4. If sum == target, return original indices
 * 5. If sum < target, move left pointer right
 * 6. If sum > target, move right pointer left
 */
public class TwoSum {
    
    /**
     * Hashmap approach - O(n) time, O(n) space
     * Most efficient for this problem since we need indices
     */
    public static int[] twoSum_hashmap(int[] nums, int target) {
        // HashMap to store number -> index mapping
        java.util.Map<Integer, Integer> numToIndex = new java.util.HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // Check if complement exists in hashmap
            if (numToIndex.containsKey(complement)) {
                // Found the pair, return indices
                return new int[]{numToIndex.get(complement), i};
            }
            
            // Store current number and its index
            numToIndex.put(nums[i], i);
        }
        
        // Should never reach here given problem constraints
        return new int[]{};
    }
    
    /**
     * Two pointers approach - O(n log n) time, O(n) space
     * Useful when you need to understand the two-pointer technique
     * Note: This approach modifies the array (sorts it)
     */
    public static int[] twoSum_twoPointers(int[] nums, int target) {
        // Create array of {value, original_index} pairs
        int[][] numsWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numsWithIndex[i][0] = nums[i]; // value
            numsWithIndex[i][1] = i;       // original index
        }
        
        // Sort by value
        java.util.Arrays.sort(numsWithIndex, (a, b) -> Integer.compare(a[0], b[0]));
        
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int sum = numsWithIndex[left][0] + numsWithIndex[right][0];
            
            if (sum == target) {
                // Return original indices in correct order
                int leftIndex = numsWithIndex[left][1];
                int rightIndex = numsWithIndex[right][1];
                return new int[]{Math.min(leftIndex, rightIndex), Math.max(leftIndex, rightIndex)};
            } else if (sum < target) {
                left++; // Need larger sum, move left pointer right
            } else {
                right--; // Need smaller sum, move right pointer left
            }
        }
        
        return new int[]{};
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * Check all possible pairs
     */
    public static int[] twoSum_bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
    
    public static void main(String[] args) {
        // Test case: nums = [2,7,11,15], target = 9
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        
        System.out.println("Testing Two Sum:");
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Target: " + target);
        
        int[] result1 = twoSum_hashmap(nums, target);
        System.out.println("Hashmap result: " + java.util.Arrays.toString(result1));
        
        int[] result2 = twoSum_twoPointers(nums, target);
        System.out.println("Two pointers result: " + java.util.Arrays.toString(result2));
        
        int[] result3 = twoSum_bruteForce(nums, target);
        System.out.println("Brute force result: " + java.util.Arrays.toString(result3));
        
        // Expected: [0, 1] (indices of 2 and 7)
    }
}

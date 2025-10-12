package masterDSA.topics.arrays;
import java.util.*;


/**
 * Four Sum
 * 
 * Given an array nums of n integers, return an array of all the unique quadruplets 
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 * - 0 <= a, b, c, d < n
 * - a, b, c, and d are distinct
 * - nums[a] + nums[b] + nums[c] + nums[d] == target
 * 
 * LeetCode: https://leetcode.com/problems/4sum/
 * Difficulty: Medium
 * Tags: sorting, k-sum
 * 
 * Approach:
 * - Sort the array to enable two-pointer technique and avoid duplicates
 * - Fix two numbers and use two pointers to find the other two
 * - Skip duplicates to avoid duplicate quadruplets
 * - Generalizes the 3Sum approach
 * 
 * Pseudocode:
 * 1. Sort the array
 * 2. For each index i from 0 to n-4:
 *    a. Skip duplicates for the first number
 *    b. For each index j from i+1 to n-3:
 *       c. Skip duplicates for the second number
 *       d. Use two pointers (left=j+1, right=n-1)
 *       e. While left < right:
 *          - Calculate sum = nums[i] + nums[j] + nums[left] + nums[right]
 *          - If sum == target: add quadruplet, skip duplicates, move both pointers
 *          - If sum < target: move left pointer right
 *          - If sum > target: move right pointer left
 */
public class FourSum {
    
    /**
     * Two pointers approach - O(n³) time, O(1) space (excluding output)
     * Sort array first, fix two numbers and use two pointers for the other two
     */
    public static java.util.List<java.util.List<Integer>> fourSum_twoPointers(int[] nums, int target) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        
        // Sort array to enable two-pointer technique and handle duplicates
        java.util.Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for (int j = i + 1; j < nums.length - 2; j++) {
                // Skip duplicates for the second number
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                int left = j + 1;
                int right = nums.length - 1;
                long targetSum = (long) target - nums[i] - nums[j]; // Use long to prevent overflow
                
                while (left < right) {
                    long sum = (long) nums[left] + nums[right];
                    
                    if (sum == targetSum) {
                        // Found a valid quadruplet
                        result.add(java.util.Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Skip duplicates for left pointer
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // Skip duplicates for right pointer
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        
                        // Move both pointers
                        left++;
                        right--;
                    } else if (sum < targetSum) {
                        left++; // Need larger sum
                    } else {
                        right--; // Need smaller sum
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * Hashmap approach - O(n³) time, O(n) space
     * Fix three numbers and use hashmap to find the fourth
     */
    public static java.util.List<java.util.List<Integer>> fourSum_hashmap(int[] nums, int target) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.Set<java.util.List<Integer>> seen = new java.util.HashSet<>();
        
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                java.util.Map<Integer, Integer> numToIndex = new java.util.HashMap<>();
                
                for (int k = j + 1; k < nums.length; k++) {
                    int complement = target - nums[i] - nums[j] - nums[k];
                    
                    if (numToIndex.containsKey(complement)) {
                        // Found a valid quadruplet
                        java.util.List<Integer> quadruplet = java.util.Arrays.asList(nums[i], nums[j], nums[k], complement);
                        java.util.List<Integer> sortedQuadruplet = new java.util.ArrayList<>(quadruplet);
                        sortedQuadruplet.sort(Integer::compareTo);
                        
                        if (seen.add(sortedQuadruplet)) {
                            result.add(quadruplet);
                        }
                    }
                    
                    numToIndex.put(nums[k], k);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Brute force approach - O(n⁴) time, O(1) space
     * Check all possible quadruplets
     */
    public static java.util.List<java.util.List<Integer>> fourSum_bruteForce(int[] nums, int target) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.Set<java.util.List<Integer>> seen = new java.util.HashSet<>();
        
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            java.util.List<Integer> quadruplet = java.util.Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            java.util.List<Integer> sortedQuadruplet = new java.util.ArrayList<>(quadruplet);
                            sortedQuadruplet.sort(Integer::compareTo);
                            
                            if (seen.add(sortedQuadruplet)) {
                                result.add(quadruplet);
                            }
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test case: nums = [1,0,-1,0,-2,2], target = 0
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        
        System.out.println("Testing Four Sum:");
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Target: " + target);
        
        java.util.List<java.util.List<Integer>> result1 = fourSum_twoPointers(nums, target);
        System.out.println("Two pointers result: " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = fourSum_hashmap(nums, target);
        System.out.println("Hashmap result: " + result2);
        
        java.util.List<java.util.List<Integer>> result3 = fourSum_bruteForce(nums, target);
        System.out.println("Brute force result: " + result3);
        
        // Expected: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    }
}

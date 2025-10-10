package masterDSA.topics.arrays.ThreeNumberSum3Sum;
import java.util.*;


/**
 * Three Number Sum (3Sum)
 * 
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that 
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * The solution set must not contain duplicate triplets.
 * 
 * LeetCode: https://leetcode.com/problems/3sum/
 * Difficulty: Medium
 * Tags: sorting, two-pointers
 * 
 * Approach:
 * - Sort the array to enable two-pointer technique and avoid duplicates
 * - Fix one number and use two pointers to find the other two
 * - Skip duplicates to avoid duplicate triplets
 * 
 * Pseudocode:
 * 1. Sort the array
 * 2. For each index i from 0 to n-3:
 *    a. Skip duplicates for the first number
 *    b. Use two pointers (left=i+1, right=n-1)
 *    c. While left < right:
 *       - Calculate sum = nums[i] + nums[left] + nums[right]
 *       - If sum == 0: add triplet, skip duplicates, move both pointers
 *       - If sum < 0: move left pointer right
 *       - If sum > 0: move right pointer left
 */
public class ThreeNumberSum3Sum {
    
    /**
     * Two pointers approach - O(n²) time, O(1) space (excluding output)
     * Sort array first, then fix one number and use two pointers for the other two
     */
    public static java.util.List<java.util.List<Integer>> threeSum_twoPointers(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        
        // Sort array to enable two-pointer technique and handle duplicates
        java.util.Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i]; // We want nums[left] + nums[right] = -nums[i]
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    // Found a valid triplet
                    result.add(java.util.Arrays.asList(nums[i], nums[left], nums[right]));
                    
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
                } else if (sum < target) {
                    left++; // Need larger sum
                } else {
                    right--; // Need smaller sum
                }
            }
        }
        
        return result;
    }
    
    /**
     * Hashmap approach - O(n²) time, O(n) space
     * Fix two numbers and use hashmap to find the third
     */
    public static java.util.List<java.util.List<Integer>> threeSum_hashmap(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.Set<java.util.List<Integer>> seen = new java.util.HashSet<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            java.util.Map<Integer, Integer> numToIndex = new java.util.HashMap<>();
            
            for (int j = i + 1; j < nums.length; j++) {
                int complement = -(nums[i] + nums[j]);
                
                if (numToIndex.containsKey(complement)) {
                    // Found a valid triplet
                    java.util.List<Integer> triplet = java.util.Arrays.asList(nums[i], nums[j], complement);
                    java.util.List<Integer> sortedTriplet = new java.util.ArrayList<>(triplet);
                    sortedTriplet.sort(Integer::compareTo);
                    
                    if (seen.add(sortedTriplet)) {
                        result.add(triplet);
                    }
                }
                
                numToIndex.put(nums[j], j);
            }
        }
        
        return result;
    }
    
    /**
     * Brute force approach - O(n³) time, O(1) space
     * Check all possible triplets
     */
    public static java.util.List<java.util.List<Integer>> threeSum_bruteForce(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.Set<java.util.List<Integer>> seen = new java.util.HashSet<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        java.util.List<Integer> triplet = java.util.Arrays.asList(nums[i], nums[j], nums[k]);
                        java.util.List<Integer> sortedTriplet = new java.util.ArrayList<>(triplet);
                        sortedTriplet.sort(Integer::compareTo);
                        
                        if (seen.add(sortedTriplet)) {
                            result.add(triplet);
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test case: nums = [-1,0,1,2,-1,-4]
        int[] nums = {-1, 0, 1, 2, -1, -4};
        
        System.out.println("Testing Three Sum:");
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        java.util.List<java.util.List<Integer>> result1 = threeSum_twoPointers(nums);
        System.out.println("Two pointers result: " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = threeSum_hashmap(nums);
        System.out.println("Hashmap result: " + result2);
        
        java.util.List<java.util.List<Integer>> result3 = threeSum_bruteForce(nums);
        System.out.println("Brute force result: " + result3);
        
        // Expected: [[-1,-1,2],[-1,0,1]]
    }
}

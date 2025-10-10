/**
 * Three Number Sum (3Sum)
 * 
 * Problem Statement: TODO - Add problem statement here
 * 
 * LeetCode: https://leetcode.com/problems/3sum/
 * Difficulty: Medium
 * Tags: sorting, two-pointers
 * 
 * Approaches:
 * - TODO: Add approach descriptions
 * 
 * Pseudocode:
 * 1. TODO: Add pseudocode steps
 * 2. TODO: Add more steps as needed
 */

package masterDSA.topics.arrays.ThreeNumberSum3Sum.ThreeNumberSum3Sum;

public class ThreeNumberSum3sum {
    
    /**
     * Hash Map Approach - O(n) time, O(n) space
     * Use hash map to store complements for each number
     */
    public static int[] twoSum_hashmap(int[] nums, int target) {
        // Edge case: empty array
        if (nums == null || nums.length < 2) {
            return new int[]{};
        }
        
        // Map to store number -> index
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // If complement exists, we found our pair
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            
            // Store current number and its index
            map.put(nums[i], i);
        }
        
        // No solution found
        return new int[]{};
    }
    
    /**
     * Brute Force Approach - O(n²) time, O(1) space
     * Check all possible pairs
     */
    public static int[] twoSum_bruteforce(int[] nums, int target) {
        // Edge case: empty array
        if (nums == null || nums.length < 2) {
            return new int[]{};
        }
        
        // Check all pairs
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        
        return new int[]{};
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        
        int[] result = twoSum_hashmap(nums, target);
        System.out.println("Result: " + java.util.Arrays.toString(result));
        // Expected: [0, 1] (indices of 2 and 7)
    }
}

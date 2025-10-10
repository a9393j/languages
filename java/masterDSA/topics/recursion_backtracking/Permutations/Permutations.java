/**
 * Permutations
 * 
 * Given an array nums of distinct integers, return all the possible permutations. 
 * You can return the answer in any order.
 * 
 * LeetCode: https://leetcode.com/problems/permutations/
 * Difficulty: Medium
 * Tags: backtracking
 * 
 * Approach:
 * - Use backtracking to generate all permutations
 * - Use swap-based approach or visited array approach
 * - Build permutation incrementally
 * 
 * Pseudocode:
 * 1. Use backtracking to generate permutations
 * 2. For each position, try all remaining elements
 * 3. If element not used, add to current permutation
 * 4. Recursively generate remaining positions
 * 5. Backtrack by removing element
 */

package masterDSA.topics.recursion_backtracking.Permutations.Permutations;

import java.util.*;

public class Permutations {
    
    /**
     * Backtracking approach - O(n! * n) time, O(n! * n) space
     * Most efficient approach for this problem
     */
    public static List<List<Integer>> permute_backtracking(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        
        permuteHelper(nums, current, used, result);
        return result;
    }
    
    private static void permuteHelper(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                permuteHelper(nums, current, used, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
    
    /**
     * Swap-based approach - O(n! * n) time, O(n! * n) space
     * Alternative approach using swaps
     */
    public static List<List<Integer>> permute_swap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteSwapHelper(nums, 0, result);
        return result;
    }
    
    private static void permuteSwapHelper(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            permuteSwapHelper(nums, start + 1, result);
            swap(nums, start, i); // Backtrack
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        
        System.out.println("Testing Permutations:");
        System.out.println("Array: " + Arrays.toString(nums));
        
        List<List<Integer>> result1 = permute_backtracking(nums);
        System.out.println("Backtracking result: " + result1);
        
        List<List<Integer>> result2 = permute_swap(nums);
        System.out.println("Swap result: " + result2);
    }
}
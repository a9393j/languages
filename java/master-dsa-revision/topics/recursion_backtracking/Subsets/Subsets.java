/**
 * Subsets
 * 
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * 
 * LeetCode: https://leetcode.com/problems/subsets/
 * Difficulty: Medium
 * Tags: backtracking
 * 
 * Approach:
 * - Use backtracking to generate all subsets
 * - For each element, choose to include or exclude it
 * - Build subset incrementally
 * 
 * Pseudocode:
 * 1. Use backtracking to generate subsets
 * 2. For each element, try including and excluding it
 * 3. Recursively generate remaining subsets
 * 4. Backtrack by removing element
 */

import java.util.*;

public class Subsets {
    
    /**
     * Backtracking approach - O(2^n * n) time, O(2^n * n) space
     * Most efficient approach for this problem
     */
    public static List<List<Integer>> subsets_backtracking(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        
        subsetsHelper(nums, 0, current, result);
        return result;
    }
    
    private static void subsetsHelper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(current));
        
        // Try including each remaining element
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            subsetsHelper(nums, i + 1, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
    
    /**
     * Bit manipulation approach - O(2^n * n) time, O(2^n * n) space
     * Alternative approach using bit manipulation
     */
    public static List<List<Integer>> subsets_bitManipulation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        // Generate all possible subsets using bit manipulation
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        
        return result;
    }
    
    /**
     * Iterative approach - O(2^n * n) time, O(2^n * n) space
     * Alternative approach using iteration
     */
    public static List<List<Integer>> subsets_iterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Empty subset
        
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        
        return result;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        
        System.out.println("Testing Subsets:");
        System.out.println("Array: " + Arrays.toString(nums));
        
        List<List<Integer>> result1 = subsets_backtracking(nums);
        System.out.println("Backtracking result: " + result1);
        
        List<List<Integer>> result2 = subsets_bitManipulation(nums);
        System.out.println("Bit manipulation result: " + result2);
        
        List<List<Integer>> result3 = subsets_iterative(nums);
        System.out.println("Iterative result: " + result3);
    }
}
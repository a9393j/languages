package masterDSA.topics.recursion_backtracking;
import java.util.*;


/**
 * Subsets
 * 
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * 
 * LeetCode: https://leetcode.com/problems/subsets/
 * Difficulty: Medium
 * Tags: recursion, backtracking
 * 
 * Approach:
 * - Backtracking: Generate all subsets using backtracking
 * - Iterative: Build subsets incrementally
 * - Bit manipulation: Use bit masks to generate subsets
 * 
 * Pseudocode:
 * Backtracking:
 * 1. Start with empty subset
 * 2. For each element, add it to current subset and recurse
 * 3. Remove element and try next element
 * 4. When all elements processed, add current subset to result
 */
public class Subsets {
    
    /**
     * Backtracking approach - O(2^n) time, O(2^n) space
     * Generate all subsets using backtracking
     */
    public static java.util.List<java.util.List<Integer>> subsets_backtracking(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrack(nums, 0, current, result);
        return result;
    }
    
    private static void backtrack(int[] nums, int start, java.util.List<Integer> current, java.util.List<java.util.List<Integer>> result) {
        result.add(new java.util.ArrayList<>(current));
        
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Iterative approach - O(2^n) time, O(2^n) space
     * Build subsets incrementally
     */
    public static java.util.List<java.util.List<Integer>> subsets_iterative(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        result.add(new java.util.ArrayList<>());
        
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                java.util.List<Integer> newSubset = new java.util.ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        
        return result;
    }
    
    /**
     * Bit manipulation approach - O(2^n) time, O(2^n) space
     * Use bit masks to generate subsets
     */
    public static java.util.List<java.util.List<Integer>> subsets_bitManipulation(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < (1 << n); i++) {
            java.util.List<Integer> subset = new java.util.ArrayList<>();
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
     * Helper method to find subsets with target sum
     */
    public static java.util.List<java.util.List<Integer>> subsetsWithTargetSum(int[] nums, int target) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithTarget(nums, 0, current, result, target, 0);
        return result;
    }
    
    private static void backtrackWithTarget(int[] nums, int start, java.util.List<Integer> current, 
                                          java.util.List<java.util.List<Integer>> result, int target, int currentSum) {
        if (currentSum == target) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        if (currentSum > target || start >= nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrackWithTarget(nums, i + 1, current, result, target, currentSum + nums[i]);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Helper method to find subsets with specific size
     */
    public static java.util.List<java.util.List<Integer>> subsetsWithSize(int[] nums, int size) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithSize(nums, 0, current, result, size);
        return result;
    }
    
    private static void backtrackWithSize(int[] nums, int start, java.util.List<Integer> current, 
                                        java.util.List<java.util.List<Integer>> result, int targetSize) {
        if (current.size() == targetSize) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        if (current.size() > targetSize || start >= nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrackWithSize(nums, i + 1, current, result, targetSize);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Helper method to find subsets with duplicates
     */
    public static java.util.List<java.util.List<Integer>> subsetsWithDuplicates(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        java.util.Arrays.sort(nums);
        backtrackWithDuplicates(nums, 0, current, result);
        return result;
    }
    
    private static void backtrackWithDuplicates(int[] nums, int start, java.util.List<Integer> current, 
                                             java.util.List<java.util.List<Integer>> result) {
        result.add(new java.util.ArrayList<>(current));
        
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue; // Skip duplicates
            }
            current.add(nums[i]);
            backtrackWithDuplicates(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Helper method to find maximum subset sum
     */
    public static int maxSubsetSum(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < (1 << n); i++) {
            int currentSum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentSum += nums[j];
                }
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    /**
     * Helper method to find minimum subset sum difference
     */
    public static int minSubsetSumDifference(int[] nums) {
        int totalSum = java.util.Arrays.stream(nums).sum();
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < (1 << n); i++) {
            int subsetSum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subsetSum += nums[j];
                }
            }
            minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * subsetSum));
        }
        
        return minDiff;
    }
    
    /**
     * Helper method to find subsets with even sum
     */
    public static java.util.List<java.util.List<Integer>> subsetsWithEvenSum(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < (1 << n); i++) {
            java.util.List<Integer> subset = new java.util.ArrayList<>();
            int sum = 0;
            
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                    sum += nums[j];
                }
            }
            
            if (sum % 2 == 0) {
                result.add(subset);
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find subsets with odd sum
     */
    public static java.util.List<java.util.List<Integer>> subsetsWithOddSum(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < (1 << n); i++) {
            java.util.List<Integer> subset = new java.util.ArrayList<>();
            int sum = 0;
            
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                    sum += nums[j];
                }
            }
            
            if (sum % 2 == 1) {
                result.add(subset);
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find subsets with product
     */
    public static java.util.List<java.util.List<Integer>> subsetsWithProduct(int[] nums, int targetProduct) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithProduct(nums, 0, current, result, targetProduct, 1);
        return result;
    }
    
    private static void backtrackWithProduct(int[] nums, int start, java.util.List<Integer> current, 
                                           java.util.List<java.util.List<Integer>> result, int targetProduct, int currentProduct) {
        if (currentProduct == targetProduct) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        if (currentProduct > targetProduct || start >= nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrackWithProduct(nums, i + 1, current, result, targetProduct, currentProduct * nums[i]);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        // Test case: nums = [1,2,3]
        int[] nums1 = {1, 2, 3};
        
        System.out.println("Testing Subsets:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        
        java.util.List<java.util.List<Integer>> result1 = subsets_backtracking(nums1);
        System.out.println("Backtracking result: " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = subsets_iterative(nums1);
        System.out.println("Iterative result: " + result2);
        
        java.util.List<java.util.List<Integer>> result3 = subsets_bitManipulation(nums1);
        System.out.println("Bit manipulation result: " + result3);
        
        // Expected: [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
        
        System.out.println();
        
        // Test case: nums = [0]
        int[] nums2 = {0};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        
        java.util.List<java.util.List<Integer>> result4 = subsets_backtracking(nums2);
        System.out.println("Backtracking result: " + result4);
        
        // Expected: [[], [0]]
        
        System.out.println();
        
        // Test case: nums = [1,2,2]
        int[] nums3 = {1, 2, 2};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        
        java.util.List<java.util.List<Integer>> result5 = subsetsWithDuplicates(nums3);
        System.out.println("Subsets with duplicates: " + result5);
        
        // Expected: [[], [1], [1,2], [1,2,2], [2], [2,2]]
        
        System.out.println();
        
        // Test helper methods
        java.util.List<java.util.List<Integer>> withTargetSum = subsetsWithTargetSum(nums1, 3);
        System.out.println("Subsets with target sum 3: " + withTargetSum);
        
        java.util.List<java.util.List<Integer>> withSize = subsetsWithSize(nums1, 2);
        System.out.println("Subsets with size 2: " + withSize);
        
        int maxSum = maxSubsetSum(nums1);
        System.out.println("Maximum subset sum: " + maxSum);
        
        int minDiff = minSubsetSumDifference(nums1);
        System.out.println("Minimum subset sum difference: " + minDiff);
        
        java.util.List<java.util.List<Integer>> evenSum = subsetsWithEvenSum(nums1);
        System.out.println("Subsets with even sum: " + evenSum);
        
        java.util.List<java.util.List<Integer>> oddSum = subsetsWithOddSum(nums1);
        System.out.println("Subsets with odd sum: " + oddSum);
        
        java.util.List<java.util.List<Integer>> withProduct = subsetsWithProduct(nums1, 6);
        System.out.println("Subsets with product 6: " + withProduct);
        
        // Expected: Target sum 3 = [[1,2], [3]], Size 2 = [[1,2], [1,3], [2,3]], Max sum = 6, Min diff = 0, Even sum = [[], [2], [1,3], [1,2,3]], Odd sum = [[1], [3], [1,2]], Product 6 = [[1,2,3]]
    }
}

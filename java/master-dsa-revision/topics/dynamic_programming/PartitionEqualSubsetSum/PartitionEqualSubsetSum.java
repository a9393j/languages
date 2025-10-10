/**
 * Partition Equal Subset Sum
 * 
 * Given a non-empty array nums containing only positive integers, find if the array 
 * can be partitioned into two subsets with equal sum.
 * 
 * LeetCode: https://leetcode.com/problems/partition-equal-subset-sum/
 * Difficulty: Medium
 * Tags: dp, subset-sum
 * 
 * Approach:
 * - Use dynamic programming to solve subset sum problem
 * - Check if subset with sum = totalSum/2 exists
 * - dp[i][j] represents if subset with sum j exists using first i elements
 * 
 * Pseudocode:
 * 1. Calculate total sum
 * 2. If total sum is odd, return false
 * 3. Use DP to check if subset with sum = totalSum/2 exists
 * 4. Return result
 */

import java.util.*;

public class PartitionEqualSubsetSum {
    
    /**
     * DP approach - O(n*sum) time, O(n*sum) space
     * Most efficient approach for this problem
     */
    public static boolean canPartition_dp(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        
        // If total sum is odd, cannot partition
        if (totalSum % 2 != 0) {
            return false;
        }
        
        int targetSum = totalSum / 2;
        int n = nums.length;
        
        boolean[][] dp = new boolean[n + 1][targetSum + 1];
        
        // Base case: empty subset has sum 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        // Fill dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        
        return dp[n][targetSum];
    }
    
    /**
     * Space-optimized DP approach - O(n*sum) time, O(sum) space
     * Optimized space usage
     */
    public static boolean canPartition_spaceOptimized(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        
        if (totalSum % 2 != 0) {
            return false;
        }
        
        int targetSum = totalSum / 2;
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;
        
        for (int num : nums) {
            for (int j = targetSum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[targetSum];
    }
    
    /**
     * Recursive approach with memoization - O(n*sum) time, O(n*sum) space
     * Alternative approach using recursion
     */
    public static boolean canPartition_recursive(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        
        if (totalSum % 2 != 0) {
            return false;
        }
        
        int targetSum = totalSum / 2;
        Boolean[][] memo = new Boolean[nums.length][targetSum + 1];
        
        return canPartitionHelper(nums, 0, targetSum, memo);
    }
    
    private static boolean canPartitionHelper(int[] nums, int index, int targetSum, Boolean[][] memo) {
        if (targetSum == 0) {
            return true;
        }
        
        if (index >= nums.length || targetSum < 0) {
            return false;
        }
        
        if (memo[index][targetSum] != null) {
            return memo[index][targetSum];
        }
        
        boolean result = canPartitionHelper(nums, index + 1, targetSum, memo) ||
                        canPartitionHelper(nums, index + 1, targetSum - nums[index], memo);
        
        memo[index][targetSum] = result;
        return result;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};
        int[] nums3 = {1, 2, 5};
        
        System.out.println("Testing Partition Equal Subset Sum:");
        
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("DP result: " + canPartition_dp(nums1));
        System.out.println("Space-optimized result: " + canPartition_spaceOptimized(nums1));
        System.out.println("Recursive result: " + canPartition_recursive(nums1));
        
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("DP result: " + canPartition_dp(nums2));
        
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("DP result: " + canPartition_dp(nums3));
    }
}
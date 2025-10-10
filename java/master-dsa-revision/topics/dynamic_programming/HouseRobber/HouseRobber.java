/**
 * House Robber
 * 
 * You are a professional robber planning to rob houses along a street. Each house has 
 * a certain amount of money stashed, the only constraint stopping you from robbing each 
 * of them is that adjacent houses have security systems connected and it will automatically 
 * contact the police if two adjacent houses were broken into on the same night.
 * 
 * LeetCode: https://leetcode.com/problems/house-robber/
 * Difficulty: Medium
 * Tags: dp
 * 
 * Approach:
 * - Use dynamic programming to find maximum money
 * - dp[i] represents maximum money robbed from first i houses
 * - For each house, choose between robbing it or not
 * 
 * Pseudocode:
 * 1. Initialize dp array
 * 2. dp[0] = 0, dp[1] = nums[0]
 * 3. For each house:
 *    a. dp[i] = max(dp[i-1], dp[i-2] + nums[i-1])
 * 4. Return dp[n]
 */

import java.util.*;

public class HouseRobber {
    
    /**
     * DP approach - O(n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static int rob_dp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        
        return dp[n];
    }
    
    /**
     * Space-optimized DP approach - O(n) time, O(1) space
     * Optimized space usage
     */
    public static int rob_spaceOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        int prev2 = 0;
        int prev1 = nums[0];
        
        for (int i = 1; i < n; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * Recursive approach with memoization - O(n) time, O(n) space
     * Alternative approach using recursion
     */
    public static int rob_recursive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        
        return robHelper(nums, 0, memo);
    }
    
    private static int robHelper(int[] nums, int index, int[] memo) {
        if (index >= nums.length) {
            return 0;
        }
        
        if (memo[index] != -1) {
            return memo[index];
        }
        
        int robCurrent = nums[index] + robHelper(nums, index + 2, memo);
        int skipCurrent = robHelper(nums, index + 1, memo);
        
        memo[index] = Math.max(robCurrent, skipCurrent);
        return memo[index];
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {2, 1, 1, 2};
        
        System.out.println("Testing House Robber:");
        
        System.out.println("Houses: " + Arrays.toString(nums1));
        System.out.println("DP result: " + rob_dp(nums1));
        System.out.println("Space-optimized result: " + rob_spaceOptimized(nums1));
        System.out.println("Recursive result: " + rob_recursive(nums1));
        
        System.out.println("Houses: " + Arrays.toString(nums2));
        System.out.println("DP result: " + rob_dp(nums2));
        
        System.out.println("Houses: " + Arrays.toString(nums3));
        System.out.println("DP result: " + rob_dp(nums3));
    }
}
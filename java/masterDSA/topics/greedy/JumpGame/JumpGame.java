/**
 * Jump Game
 * 
 * You are given an integer array nums. You are initially positioned at the array's first index, 
 * and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 * 
 * LeetCode: https://leetcode.com/problems/jump-game/
 * Difficulty: Medium
 * Tags: greedy
 * 
 * Approach:
 * - Use greedy approach to track maximum reachable position
 * - For each position, update maximum reachable position
 * - If current position exceeds maximum reachable, return false
 * 
 * Pseudocode:
 * 1. Initialize maximum reachable position
 * 2. For each position:
 *    a. If current position > maximum reachable, return false
 *    b. Update maximum reachable position
 * 3. Return true if last position is reachable
 */

package masterDSA.topics.greedy.JumpGame.JumpGame;

import java.util.*;

public class JumpGame {
    
    /**
     * Greedy approach - O(n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static boolean canJump_greedy(int[] nums) {
        int maxReachable = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If current position exceeds maximum reachable position
            if (i > maxReachable) {
                return false;
            }
            
            // Update maximum reachable position
            maxReachable = Math.max(maxReachable, i + nums[i]);
        }
        
        return true;
    }
    
    /**
     * Dynamic programming approach - O(n^2) time, O(n) space
     * Alternative approach using DP
     */
    public static boolean canJump_dp(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        int[] nums3 = {0};
        
        System.out.println("Testing Jump Game:");
        
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Greedy result: " + canJump_greedy(nums1));
        System.out.println("DP result: " + canJump_dp(nums1));
        
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Greedy result: " + canJump_greedy(nums2));
        System.out.println("DP result: " + canJump_dp(nums2));
        
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("Greedy result: " + canJump_greedy(nums3));
        System.out.println("DP result: " + canJump_dp(nums3));
    }
}
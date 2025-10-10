/**
 * Longest Increasing Subsequence
 * 
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements 
 * without changing the order of the remaining elements.
 * 
 * LeetCode: https://leetcode.com/problems/longest-increasing-subsequence/
 * Difficulty: Medium
 * Tags: dp, binary-search
 * 
 * Approach:
 * - Use dynamic programming to find LIS
 * - dp[i] represents length of LIS ending at index i
 * - For each element, check all previous elements
 * 
 * Pseudocode:
 * 1. Initialize dp array with 1
 * 2. For each element:
 *    a. Check all previous elements
 *    b. If previous element < current element, update dp[i]
 * 3. Return maximum value in dp array
 */

package masterDSA.topics.dynamic_programming.LongestIncreasingSubsequence.LongestIncreasingSubsequence;

import java.util.*;

public class LongestIncreasingSubsequence {
    
    /**
     * DP approach - O(n^2) time, O(n) space
     * Most intuitive approach for this problem
     */
    public static int lengthOfLIS_dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int maxLength = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    /**
     * Binary search approach - O(n log n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static int lengthOfLIS_binarySearch(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int pos = binarySearch(tails, num);
            
            if (pos == tails.size()) {
                tails.add(num);
            } else {
                tails.set(pos, num);
            }
        }
        
        return tails.size();
    }
    
    private static int binarySearch(List<Integer> tails, int target) {
        int left = 0;
        int right = tails.size();
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tails.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        
        System.out.println("Testing Longest Increasing Subsequence:");
        
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("DP result: " + lengthOfLIS_dp(nums1));
        System.out.println("Binary search result: " + lengthOfLIS_binarySearch(nums1));
        
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("DP result: " + lengthOfLIS_dp(nums2));
        System.out.println("Binary search result: " + lengthOfLIS_binarySearch(nums2));
        
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("DP result: " + lengthOfLIS_dp(nums3));
        System.out.println("Binary search result: " + lengthOfLIS_binarySearch(nums3));
    }
}
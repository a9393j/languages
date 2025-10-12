package masterDSA.topics.arrays;
import java.util.*;


/**
 * Maximum Subarray (Kadane's Algorithm)
 * 
 * Given an integer array nums, find the contiguous subarray (containing at least one number) 
 * which has the largest sum and return its sum.
 * 
 * LeetCode: https://leetcode.com/problems/maximum-subarray/
 * Difficulty: Easy
 * Tags: greedy, dp
 * 
 * Approach:
 * - Kadane's algorithm: Keep track of maximum sum ending at current position
 * - If current sum becomes negative, reset it to 0
 * - Dynamic programming: dp[i] = max(nums[i], dp[i-1] + nums[i])
 * - Divide and conquer: Split array and find max in left, right, and crossing subarrays
 * 
 * Pseudocode:
 * Kadane's:
 * 1. Initialize maxSoFar = nums[0], maxEndingHere = nums[0]
 * 2. For each element from index 1:
 *    a. maxEndingHere = max(nums[i], maxEndingHere + nums[i])
 *    b. maxSoFar = max(maxSoFar, maxEndingHere)
 * 3. Return maxSoFar
 */
public class MaximumSubarrayKadane {
    
    /**
     * Kadane's algorithm - O(n) time, O(1) space
     * Keep track of maximum sum ending at current position
     */
    public static int maxSubArray_kadane(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either start new subarray or extend existing one
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    /**
     * Kadane's algorithm with indices - O(n) time, O(1) space
     * Returns the maximum sum and the start/end indices of the subarray
     */
    public static int[] maxSubArrayWithIndices(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int start = 0, end = 0, tempStart = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (maxEndingHere < 0) {
                // Start new subarray
                maxEndingHere = nums[i];
                tempStart = i;
            } else {
                // Extend existing subarray
                maxEndingHere += nums[i];
            }
            
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }
        
        return new int[]{maxSoFar, start, end};
    }
    
    /**
     * Dynamic programming approach - O(n) time, O(n) space
     * dp[i] represents maximum sum ending at index i
     */
    public static int maxSubArray_dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = dp[0];
        
        for (int i = 1; i < n; i++) {
            // Either start new subarray or extend existing one
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        
        return maxSum;
    }
    
    /**
     * Space-optimized DP - O(n) time, O(1) space
     * Only need previous value, not entire array
     */
    public static int maxSubArray_dpOptimized(int[] nums) {
        int prev = nums[0];
        int maxSum = prev;
        
        for (int i = 1; i < nums.length; i++) {
            prev = Math.max(nums[i], prev + nums[i]);
            maxSum = Math.max(maxSum, prev);
        }
        
        return maxSum;
    }
    
    /**
     * Divide and conquer approach - O(n log n) time, O(log n) space
     * Split array and find max in left, right, and crossing subarrays
     */
    public static int maxSubArray_divideConquer(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
    
    private static int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        // Find maximum sum in left half
        int leftMax = maxSubArrayHelper(nums, left, mid);
        
        // Find maximum sum in right half
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        
        // Find maximum sum crossing the middle
        int crossMax = maxCrossingSum(nums, left, mid, right);
        
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // Find maximum sum in left half ending at mid
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // Find maximum sum in right half starting from mid+1
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        return leftSum + rightSum;
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * Check all possible subarrays
     */
    public static int maxSubArray_bruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        
        return maxSum;
    }
    
    /**
     * Helper method to print subarray with maximum sum
     */
    public static void printMaxSubArray(int[] nums) {
        int[] result = maxSubArrayWithIndices(nums);
        int maxSum = result[0];
        int start = result[1];
        int end = result[2];
        
        System.out.println("Maximum sum: " + maxSum);
        System.out.print("Subarray: [");
        for (int i = start; i <= end; i++) {
            System.out.print(nums[i]);
            if (i < end) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        // Test case: nums = [-2,1,-3,4,-1,2,1,-5,4]
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        
        System.out.println("Testing Maximum Subarray:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        
        int result1 = maxSubArray_kadane(nums1);
        System.out.println("Kadane's result: " + result1);
        
        int result2 = maxSubArray_dp(nums1);
        System.out.println("DP result: " + result2);
        
        int result3 = maxSubArray_dpOptimized(nums1);
        System.out.println("DP optimized result: " + result3);
        
        int result4 = maxSubArray_divideConquer(nums1);
        System.out.println("Divide and conquer result: " + result4);
        
        int result5 = maxSubArray_bruteForce(nums1);
        System.out.println("Brute force result: " + result5);
        
        // Expected: 6 (subarray [4,-1,2,1])
        
        System.out.println();
        printMaxSubArray(nums1);
        
        System.out.println();
        
        // Test case: nums = [1]
        int[] nums2 = {1};
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        
        int result6 = maxSubArray_kadane(nums2);
        System.out.println("Kadane's result: " + result6);
        
        // Expected: 1
        
        System.out.println();
        
        // Test case: nums = [5,4,-1,7,8]
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        
        int result7 = maxSubArray_kadane(nums3);
        System.out.println("Kadane's result: " + result7);
        
        // Expected: 23 (entire array)
    }
}

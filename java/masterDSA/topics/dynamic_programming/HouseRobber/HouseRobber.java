package masterDSA.topics.dynamic_programming.HouseRobber;
import java.util.*;


/**
 * House Robber
 * 
 * You are a professional robber planning to rob houses along a street. Each house 
 * has a certain amount of money stashed, the only constraint stopping you from robbing 
 * each of them is that adjacent houses have security systems connected and it will 
 * automatically contact the police if two adjacent houses were broken into on the same night.
 * 
 * LeetCode: https://leetcode.com/problems/house-robber/
 * Difficulty: Medium
 * Tags: dynamic-programming, array
 * 
 * Approach:
 * - DP: O(n) time, O(n) space
 * - Space optimized DP: O(n) time, O(1) space
 * - Recursive with memoization: O(n) time, O(n) space
 * 
 * Pseudocode:
 * DP:
 * 1. Initialize dp array
 * 2. dp[0] = nums[0], dp[1] = max(nums[0], nums[1])
 * 3. For each i from 2 to n-1:
 *    dp[i] = max(dp[i-1], dp[i-2] + nums[i])
 * 4. Return dp[n-1]
 */
public class HouseRobber {
    
    /**
     * DP approach - O(n) time, O(n) space
     * Use dynamic programming to find maximum money
     */
    public static int rob_dp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[n - 1];
    }
    
    /**
     * Space optimized DP approach - O(n) time, O(1) space
     * Use only two variables for space optimization
     */
    public static int rob_spaceOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * Recursive with memoization approach - O(n) time, O(n) space
     * Use recursion with memoization
     */
    public static int rob_memo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] memo = new int[nums.length];
        java.util.Arrays.fill(memo, -1);
        
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
     * Helper method to find houses to rob
     */
    public static java.util.List<Integer> findHousesToRob(int[] nums) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        if (nums.length == 1) {
            result.add(0);
            return result;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        boolean[] robbed = new boolean[n];
        
        dp[0] = nums[0];
        robbed[0] = true;
        
        if (nums[1] > nums[0]) {
            dp[1] = nums[1];
            robbed[1] = true;
            robbed[0] = false;
        } else {
            dp[1] = nums[0];
        }
        
        for (int i = 2; i < n; i++) {
            if (dp[i - 1] > dp[i - 2] + nums[i]) {
                dp[i] = dp[i - 1];
                robbed[i] = false;
            } else {
                dp[i] = dp[i - 2] + nums[i];
                robbed[i] = true;
                robbed[i - 1] = false;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (robbed[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find maximum money with circular houses
     */
    public static int robCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        // Case 1: Rob first house, skip last house
        int[] nums1 = new int[nums.length - 1];
        System.arraycopy(nums, 0, nums1, 0, nums.length - 1);
        int result1 = rob_dp(nums1);
        
        // Case 2: Skip first house, rob last house
        int[] nums2 = new int[nums.length - 1];
        System.arraycopy(nums, 1, nums2, 0, nums.length - 1);
        int result2 = rob_dp(nums2);
        
        return Math.max(result1, result2);
    }
    
    /**
     * Helper method to find maximum money with binary tree houses
     */
    public static int robBinaryTree(TreeNode root) {
        int[] result = robTreeHelper(root);
        return Math.max(result[0], result[1]);
    }
    
    private static int[] robTreeHelper(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        
        int[] left = robTreeHelper(node.left);
        int[] right = robTreeHelper(node.right);
        
        // result[0] = max money if we rob current node
        // result[1] = max money if we don't rob current node
        int[] result = new int[2];
        
        result[0] = node.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        return result;
    }
    
    /**
     * TreeNode class for binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {}
        
        TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    /**
     * Helper method to find maximum money with k adjacent houses constraint
     */
    public static int robKAdjacent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            
            for (int j = Math.max(0, i - k - 1); j < i - 1; j++) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
            }
        }
        
        return java.util.Arrays.stream(dp).max().getAsInt();
    }
    
    /**
     * Helper method to find maximum money with minimum gap
     */
    public static int robMinGap(int[] nums, int minGap) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            
            for (int j = 0; j < i - minGap; j++) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
            }
        }
        
        return java.util.Arrays.stream(dp).max().getAsInt();
    }
    
    /**
     * Helper method to find maximum money with alternating pattern
     */
    public static int robAlternating(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        
        dp[0] = nums[0];
        if (n > 1) {
            dp[1] = Math.max(nums[0], nums[1]);
        }
        
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[n - 1];
    }
    
    /**
     * Helper method to find maximum money with weight constraint
     */
    public static int robWithWeight(int[] nums, int[] weights, int maxWeight) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[][] dp = new int[n + 1][maxWeight + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        dp[i - 1][w],
                        dp[i - 1][w - weights[i - 1]] + nums[i - 1]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][maxWeight];
    }
    
    public static void main(String[] args) {
        // Test case: nums = [1,2,3,1]
        int[] nums1 = {1, 2, 3, 1};
        
        System.out.println("Testing House Robber:");
        System.out.println("Houses: " + java.util.Arrays.toString(nums1));
        
        int result1 = rob_dp(nums1);
        System.out.println("DP result: " + result1);
        
        int result2 = rob_spaceOptimized(nums1);
        System.out.println("Space optimized result: " + result2);
        
        int result3 = rob_memo(nums1);
        System.out.println("Memoization result: " + result3);
        
        // Expected: 4
        
        System.out.println();
        
        // Test case: nums = [2,7,9,3,1]
        int[] nums2 = {2, 7, 9, 3, 1};
        
        System.out.println("Houses: " + java.util.Arrays.toString(nums2));
        
        int result4 = rob_dp(nums2);
        System.out.println("DP result: " + result4);
        
        // Expected: 12
        
        System.out.println();
        
        // Test case: nums = [2,1,1,2]
        int[] nums3 = {2, 1, 1, 2};
        
        System.out.println("Houses: " + java.util.Arrays.toString(nums3));
        
        int result5 = rob_dp(nums3);
        System.out.println("DP result: " + result5);
        
        // Expected: 4
        
        System.out.println();
        
        // Test helper methods
        java.util.List<Integer> housesToRob = findHousesToRob(nums1);
        System.out.println("Houses to rob: " + housesToRob);
        
        int circular = robCircular(nums1);
        System.out.println("Maximum money (circular): " + circular);
        
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        
        int binaryTree = robBinaryTree(root);
        System.out.println("Maximum money (binary tree): " + binaryTree);
        
        int kAdjacent = robKAdjacent(nums1, 1);
        System.out.println("Maximum money (k adjacent): " + kAdjacent);
        
        int minGap = robMinGap(nums1, 1);
        System.out.println("Maximum money (minimum gap): " + minGap);
        
        int alternating = robAlternating(nums1);
        System.out.println("Maximum money (alternating): " + alternating);
        
        int[] weights = {1, 2, 3, 1};
        int withWeight = robWithWeight(nums1, weights, 5);
        System.out.println("Maximum money (with weight): " + withWeight);
        
        // Expected: Houses to rob = [0, 2], Circular = 4, Binary tree = 7, K adjacent = 4, Min gap = 4, Alternating = 4, With weight = 4
    }
}

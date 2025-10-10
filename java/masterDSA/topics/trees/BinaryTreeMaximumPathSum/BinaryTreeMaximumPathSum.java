/**
 * Binary Tree Maximum Path Sum
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes 
 * in the sequence has an edge connecting them. A node can only appear in the sequence 
 * at most once. The path sum of a path is the sum of the node values in the path.
 * 
 * LeetCode: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Difficulty: Hard
 * Tags: dfs, tree-dp
 * 
 * Approach:
 * - Use DFS to calculate maximum path sum
 * - For each node, calculate maximum path sum that goes through it
 * - Consider both paths that go through current node and paths that don't
 * 
 * Pseudocode:
 * 1. Use DFS to traverse tree
 * 2. For each node, calculate maximum path sum that includes it
 * 3. Update global maximum if current path is better
 * 4. Return maximum path sum that can be extended upward
 */

package masterDSA.topics.trees.BinaryTreeMaximumPathSum.BinaryTreeMaximumPathSum;

import java.util.*;

public class BinaryTreeMaximumPathSum {
    
    private static int maxSum;
    
    /**
     * DFS approach - O(n) time, O(h) space
     * Most efficient approach for this problem
     */
    public static int maxPathSum_dfs(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }
    
    private static int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // Calculate maximum path sum for left and right subtrees
        int leftSum = Math.max(0, maxPathSumHelper(node.left));
        int rightSum = Math.max(0, maxPathSumHelper(node.right));
        
        // Calculate maximum path sum that goes through current node
        int currentSum = node.val + leftSum + rightSum;
        
        // Update global maximum
        maxSum = Math.max(maxSum, currentSum);
        
        // Return maximum path sum that can be extended upward
        return node.val + Math.max(leftSum, rightSum);
    }
    
    /**
     * Alternative approach without global variable - O(n) time, O(h) space
     * Uses return value to track maximum
     */
    public static int maxPathSum_noGlobal(TreeNode root) {
        int[] result = {Integer.MIN_VALUE};
        maxPathSumHelper(root, result);
        return result[0];
    }
    
    private static int maxPathSumHelper(TreeNode node, int[] result) {
        if (node == null) {
            return 0;
        }
        
        // Calculate maximum path sum for left and right subtrees
        int leftSum = Math.max(0, maxPathSumHelper(node.left, result));
        int rightSum = Math.max(0, maxPathSumHelper(node.right, result));
        
        // Calculate maximum path sum that goes through current node
        int currentSum = node.val + leftSum + rightSum;
        
        // Update global maximum
        result[0] = Math.max(result[0], currentSum);
        
        // Return maximum path sum that can be extended upward
        return node.val + Math.max(leftSum, rightSum);
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create binary tree: 1 -> 2, 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        
        System.out.println("Testing Binary Tree Maximum Path Sum:");
        
        int result1 = maxPathSum_dfs(root);
        System.out.println("DFS result: " + result1);
        
        int result2 = maxPathSum_noGlobal(root);
        System.out.println("No global result: " + result2);
    }
    
    // TreeNode definition
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
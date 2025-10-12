package masterDSA.topics.trees;
import java.util.*;


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
 * - For each node, calculate maximum path sum that includes the node
 * - Consider both paths through the node and paths ending at the node
 * - Use global variable to track maximum path sum
 * 
 * Pseudocode:
 * 1. Initialize global maxSum
 * 2. DFS function returns maximum path sum ending at current node
 * 3. For each node:
 *    a. Calculate left and right subtree max paths
 *    b. Calculate path through current node
 *    c. Update global maxSum
 *    d. Return max path ending at current node
 */
public class BinaryTreeMaximumPathSum {
    
    /**
     * Definition for a binary tree node
     */
    public static class TreeNode {
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
    
    private static int maxSum;
    
    /**
     * DFS approach - O(n) time, O(h) space
     * Use DFS to calculate maximum path sum
     */
    public static int maxPathSum_dfs(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }
    
    private static int maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Calculate maximum path sum from left and right subtrees
        int leftSum = Math.max(0, maxPathSumHelper(root.left));
        int rightSum = Math.max(0, maxPathSumHelper(root.right));
        
        // Calculate path sum through current node
        int pathSum = root.val + leftSum + rightSum;
        
        // Update global maximum
        maxSum = Math.max(maxSum, pathSum);
        
        // Return maximum path sum ending at current node
        return root.val + Math.max(leftSum, rightSum);
    }
    
    /**
     * Alternative DFS approach - O(n) time, O(h) space
     * Return both maximum path sum and maximum path ending at node
     */
    public static int maxPathSum_alternative(TreeNode root) {
        int[] result = maxPathSumHelper2(root);
        return result[0];
    }
    
    private static int[] maxPathSumHelper2(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MIN_VALUE, 0};
        }
        
        int[] left = maxPathSumHelper2(root.left);
        int[] right = maxPathSumHelper2(root.right);
        
        // Maximum path sum ending at current node
        int maxEndingHere = Math.max(root.val, root.val + Math.max(left[1], right[1]));
        
        // Maximum path sum through current node
        int maxThroughHere = Math.max(maxEndingHere, root.val + left[1] + right[1]);
        
        // Global maximum path sum
        int globalMax = Math.max(Math.max(left[0], right[0]), maxThroughHere);
        
        return new int[]{globalMax, maxEndingHere};
    }
    
    /**
     * Helper method to create a binary tree from array
     */
    public static TreeNode createBinaryTree(Integer[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        for (int i = 1; i < values.length; i += 2) {
            TreeNode current = queue.poll();
            
            if (values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            
            if (i + 1 < values.length && values[i + 1] != null) {
                current.right = new TreeNode(values[i + 1]);
                queue.offer(current.right);
            }
        }
        
        return root;
    }
    
    /**
     * Helper method to print binary tree
     */
    public static void printBinaryTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        
        printBinaryTreeHelper(root, "", true);
    }
    
    private static void printBinaryTreeHelper(TreeNode root, String prefix, boolean isLast) {
        if (root == null) {
            return;
        }
        
        System.out.println(prefix + (isLast ? "└── " : "├── ") + root.val);
        
        if (root.left != null || root.right != null) {
            if (root.left != null) {
                printBinaryTreeHelper(root.left, prefix + (isLast ? "    " : "│   "), root.right == null);
            }
            if (root.right != null) {
                printBinaryTreeHelper(root.right, prefix + (isLast ? "    " : "│   "), true);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test case: [1,2,3]
        Integer[] values = {1, 2, 3};
        TreeNode root = createBinaryTree(values);
        
        System.out.println("Testing Binary Tree Maximum Path Sum:");
        System.out.println("Binary Tree:");
        printBinaryTree(root);
        
        int result1 = maxPathSum_dfs(root);
        System.out.println("DFS result: " + result1);
        
        int result2 = maxPathSum_alternative(root);
        System.out.println("Alternative result: " + result2);
        
        // Expected: 6
        
        System.out.println();
        
        // Test case: [-10,9,20,null,null,15,7]
        Integer[] values2 = {-10, 9, 20, null, null, 15, 7};
        TreeNode root2 = createBinaryTree(values2);
        
        System.out.println("Binary Tree:");
        printBinaryTree(root2);
        
        int result3 = maxPathSum_dfs(root2);
        System.out.println("DFS result: " + result3);
        
        // Expected: 42
        
        System.out.println();
        
        // Test case: [-3]
        Integer[] values3 = {-3};
        TreeNode root3 = createBinaryTree(values3);
        
        System.out.println("Binary Tree:");
        printBinaryTree(root3);
        
        int result4 = maxPathSum_dfs(root3);
        System.out.println("DFS result: " + result4);
        
        // Expected: -3
    }
}

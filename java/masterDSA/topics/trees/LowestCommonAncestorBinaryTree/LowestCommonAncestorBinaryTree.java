package masterDSA.topics.trees.LowestCommonAncestorBinaryTree;
import java.util.*;


/**
 * Lowest Common Ancestor (Binary Tree)
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
 * that has both p and q as descendants.
 * 
 * LeetCode: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Difficulty: Medium
 * Tags: dfs, recursion
 * 
 * Approach:
 * - Use DFS to find the LCA
 * - Return the node if it matches p or q
 * - If both left and right subtrees return non-null, current node is LCA
 * - If only one subtree returns non-null, return that result
 * 
 * Pseudocode:
 * 1. If root is null or matches p or q, return root
 * 2. Recursively find LCA in left and right subtrees
 * 3. If both left and right are non-null, root is LCA
 * 4. Return non-null result from left or right
 */
public class LowestCommonAncestorBinaryTree {
    
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
    
    /**
     * DFS approach - O(n) time, O(h) space
     * Use DFS to find the LCA
     */
    public static TreeNode lowestCommonAncestor_dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor_dfs(root.left, p, q);
        TreeNode right = lowestCommonAncestor_dfs(root.right, p, q);
        
        if (left != null && right != null) {
            return root; // Current node is LCA
        }
        
        return left != null ? left : right;
    }
    
    /**
     * Helper method to create a binary tree from array
     */
    public static TreeNode createBinaryTree(Integer[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
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
        // Test case: [3,5,1,6,2,0,8,null,null,7,4]
        Integer[] values = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = createBinaryTree(values);
        
        System.out.println("Testing Lowest Common Ancestor (Binary Tree):");
        System.out.println("Binary Tree:");
        printBinaryTree(root);
        
        TreeNode p = root.left; // 5
        TreeNode q = root.right; // 1
        
        TreeNode result = lowestCommonAncestor_dfs(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + ": " + (result != null ? result.val : "null"));
        
        // Expected: 3
    }
}

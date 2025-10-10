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
 * - Use DFS to find LCA
 * - If current node is p or q, return it
 * - If both subtrees return non-null, current node is LCA
 * - If only one subtree returns non-null, return that result
 * 
 * Pseudocode:
 * 1. If current node is null, p, or q, return current node
 * 2. Recursively search left and right subtrees
 * 3. If both subtrees return non-null, current node is LCA
 * 4. Return non-null result from either subtree
 */

package masterDSA.topics.trees.LowestCommonAncestorBinaryTree.LowestCommonAncestorBinaryTree;

import java.util.*;

public class LowestCommonAncestorBinaryTree {
    
    /**
     * DFS approach - O(n) time, O(h) space
     * Most efficient approach for this problem
     */
    public static TreeNode lowestCommonAncestor_dfs(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null, p, or q, return root
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Recursively search left and right subtrees
        TreeNode left = lowestCommonAncestor_dfs(root.left, p, q);
        TreeNode right = lowestCommonAncestor_dfs(root.right, p, q);
        
        // If both subtrees return non-null, current node is LCA
        if (left != null && right != null) {
            return root;
        }
        
        // Return non-null result from either subtree
        return left != null ? left : right;
    }
    
    /**
     * Alternative approach using path finding - O(n) time, O(h) space
     * Find paths to both nodes and compare them
     */
    public static TreeNode lowestCommonAncestor_path(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        
        // Find paths to both nodes
        findPath(root, p, pathP);
        findPath(root, q, pathQ);
        
        // Find LCA by comparing paths
        int i = 0;
        while (i < pathP.size() && i < pathQ.size() && pathP.get(i) == pathQ.get(i)) {
            i++;
        }
        
        return pathP.get(i - 1);
    }
    
    private static boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        
        path.add(root);
        
        if (root == target) {
            return true;
        }
        
        if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
            return true;
        }
        
        path.remove(path.size() - 1);
        return false;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create binary tree: 3 -> 5, 1 -> 6, 2, 0, 8 -> 7, 4
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        
        TreeNode p = root.left;  // Node 5
        TreeNode q = root.right;  // Node 1
        
        System.out.println("Testing Lowest Common Ancestor (Binary Tree):");
        
        TreeNode result1 = lowestCommonAncestor_dfs(root, p, q);
        System.out.println("DFS result: " + (result1 != null ? result1.val : "null"));
        
        TreeNode result2 = lowestCommonAncestor_path(root, p, q);
        System.out.println("Path result: " + (result2 != null ? result2.val : "null"));
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
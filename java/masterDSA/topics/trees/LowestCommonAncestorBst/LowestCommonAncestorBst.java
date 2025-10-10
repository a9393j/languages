/**
 * Lowest Common Ancestor (BST)
 * 
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
 * that has both p and q as descendants.
 * 
 * LeetCode: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Difficulty: Medium
 * Tags: bst, property
 * 
 * Approach:
 * - Use BST property: left < root < right
 * - If both nodes are smaller than root, go left
 * - If both nodes are larger than root, go right
 * - Otherwise, root is LCA
 * 
 * Pseudocode:
 * 1. If both nodes are smaller than root, search left subtree
 * 2. If both nodes are larger than root, search right subtree
 * 3. Otherwise, root is LCA
 */

package masterDSA.topics.trees.LowestCommonAncestorBst.LowestCommonAncestorBst;

import java.util.*;

public class LowestCommonAncestorBST {
    
    /**
     * Iterative approach - O(h) time, O(1) space
     * Most efficient approach for this problem
     */
    public static TreeNode lowestCommonAncestor_iterative(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            // If both nodes are smaller than root, go left
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            }
            // If both nodes are larger than root, go right
            else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            }
            // Otherwise, root is LCA
            else {
                return root;
            }
        }
        return null;
    }
    
    /**
     * Recursive approach - O(h) time, O(h) space
     * Alternative approach using recursion
     */
    public static TreeNode lowestCommonAncestor_recursive(TreeNode root, TreeNode p, TreeNode q) {
        // If both nodes are smaller than root, search left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor_recursive(root.left, p, q);
        }
        // If both nodes are larger than root, search right subtree
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor_recursive(root.right, p, q);
        }
        // Otherwise, root is LCA
        else {
            return root;
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create BST: 6 -> 2, 8 -> 0, 4, 7, 9 -> 3, 5
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        
        TreeNode p = root.left;  // Node 2
        TreeNode q = root.left.right;  // Node 4
        
        System.out.println("Testing Lowest Common Ancestor (BST):");
        
        TreeNode result1 = lowestCommonAncestor_iterative(root, p, q);
        System.out.println("Iterative result: " + (result1 != null ? result1.val : "null"));
        
        TreeNode result2 = lowestCommonAncestor_recursive(root, p, q);
        System.out.println("Recursive result: " + (result2 != null ? result2.val : "null"));
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
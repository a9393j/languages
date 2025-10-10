/**
 * Validate Binary Search Tree
 * 
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 * 
 * LeetCode: https://leetcode.com/problems/validate-binary-search-tree/
 * Difficulty: Medium
 * Tags: dfs, range-check
 * 
 * Approach:
 * - Use DFS with range checking
 * - For each node, check if its value is within valid range
 * - Update range for left and right subtrees
 * 
 * Pseudocode:
 * 1. Use DFS to traverse tree
 * 2. For each node, check if value is within range [min, max]
 * 3. Update range for children: left subtree gets [min, node.val], right subtree gets [node.val, max]
 * 4. Return true if all nodes are valid
 */

package masterDSA.topics.trees.ValidateBinarySearchTree.ValidateBinarySearchTree;

import java.util.*;

public class ValidateBinarySearchTree {
    
    /**
     * DFS with range checking - O(n) time, O(h) space
     * Most efficient approach for this problem
     */
    public static boolean isValidBST_range(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        
        // Check if current node value is within valid range
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        // Recursively check left and right subtrees with updated ranges
        return isValidBSTHelper(node.left, min, node.val) && 
               isValidBSTHelper(node.right, node.val, max);
    }
    
    /**
     * Inorder traversal approach - O(n) time, O(h) space
     * Alternative approach using inorder traversal
     */
    public static boolean isValidBST_inorder(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorderTraversal(root, values);
        
        // Check if values are in ascending order
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) <= values.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static void inorderTraversal(TreeNode node, List<Integer> values) {
        if (node != null) {
            inorderTraversal(node.left, values);
            values.add(node.val);
            inorderTraversal(node.right, values);
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create valid BST: 2 -> 1, 3
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        
        // Create invalid BST: 5 -> 1, 4 -> 3, 6
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        
        System.out.println("Testing Validate Binary Search Tree:");
        
        System.out.println("Valid BST:");
        System.out.println("Range check result: " + isValidBST_range(root1));
        System.out.println("Inorder result: " + isValidBST_inorder(root1));
        
        System.out.println("Invalid BST:");
        System.out.println("Range check result: " + isValidBST_range(root2));
        System.out.println("Inorder result: " + isValidBST_inorder(root2));
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
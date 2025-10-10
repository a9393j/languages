package masterDSA.topics.trees.ValidateBinarySearchTree;
import java.util.*;


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
 * - Pass min and max values to validate each node
 * - Alternative: Use inorder traversal to check if values are sorted
 * 
 * Pseudocode:
 * 1. Use DFS with min and max parameters
 * 2. Check if current node value is within range
 * 3. Recursively validate left and right subtrees
 * 4. Update range for children
 */
public class ValidateBinarySearchTree {
    
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
     * DFS with range checking - O(n) time, O(h) space
     * Use min and max values to validate each node
     */
    public static boolean isValidBST_range(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValidBSTHelper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        
        if (root.val <= min || root.val >= max) {
            return false;
        }
        
        return isValidBSTHelper(root.left, min, root.val) && 
               isValidBSTHelper(root.right, root.val, max);
    }
    
    /**
     * Inorder traversal approach - O(n) time, O(h) space
     * Use inorder traversal to check if values are sorted
     */
    public static boolean isValidBST_inorder(TreeNode root) {
        java.util.List<Integer> values = new java.util.ArrayList<>();
        inorderTraversal(root, values);
        
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) <= values.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static void inorderTraversal(TreeNode root, java.util.List<Integer> values) {
        if (root == null) {
            return;
        }
        
        inorderTraversal(root.left, values);
        values.add(root.val);
        inorderTraversal(root.right, values);
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
        // Test case: [2,1,3]
        Integer[] values = {2, 1, 3};
        TreeNode root = createBinaryTree(values);
        
        System.out.println("Testing Validate Binary Search Tree:");
        System.out.println("Binary Tree:");
        printBinaryTree(root);
        
        boolean result1 = isValidBST_range(root);
        System.out.println("Range checking result: " + result1);
        
        boolean result2 = isValidBST_inorder(root);
        System.out.println("Inorder traversal result: " + result2);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: [5,1,4,null,null,3,6]
        Integer[] values2 = {5, 1, 4, null, null, 3, 6};
        TreeNode root2 = createBinaryTree(values2);
        
        System.out.println("Binary Tree:");
        printBinaryTree(root2);
        
        boolean result3 = isValidBST_range(root2);
        System.out.println("Range checking result: " + result3);
        
        // Expected: false
    }
}

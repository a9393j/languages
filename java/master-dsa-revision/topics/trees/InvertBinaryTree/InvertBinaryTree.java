/**
 * Invert Binary Tree
 * 
 * Given the root of a binary tree, invert the tree, and return its root.
 * 
 * LeetCode: https://leetcode.com/problems/invert-binary-tree/
 * Difficulty: Easy
 * Tags: dfs, recursion
 * 
 * Approach:
 * - Use DFS to invert tree
 * - Swap left and right children of each node
 * - Recursively invert left and right subtrees
 * 
 * Pseudocode:
 * 1. If node is null, return null
 * 2. Swap left and right children
 * 3. Recursively invert left subtree
 * 4. Recursively invert right subtree
 * 5. Return current node
 */

import java.util.*;

public class InvertBinaryTree {
    
    /**
     * Recursive approach - O(n) time, O(h) space
     * Most efficient approach for this problem
     */
    public static TreeNode invertTree_recursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        // Swap left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // Recursively invert left and right subtrees
        invertTree_recursive(root.left);
        invertTree_recursive(root.right);
        
        return root;
    }
    
    /**
     * Iterative approach - O(n) time, O(n) space
     * Alternative approach using queue
     */
    public static TreeNode invertTree_iterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            // Swap left and right children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            // Add children to queue
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return root;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create binary tree: 4 -> 2, 7 -> 1, 3, 6, 9
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        
        System.out.println("Testing Invert Binary Tree:");
        System.out.println("Original tree: " + treeToString(root));
        
        TreeNode inverted1 = invertTree_recursive(root);
        System.out.println("Recursive result: " + treeToString(inverted1));
        
        // Create another tree for iterative test
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(7);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(9);
        
        TreeNode inverted2 = invertTree_iterative(root2);
        System.out.println("Iterative result: " + treeToString(inverted2));
    }
    
    // Helper method to convert tree to string
    private static String treeToString(TreeNode root) {
        if (root == null) return "null";
        return root.val + "(" + treeToString(root.left) + "," + treeToString(root.right) + ")";
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
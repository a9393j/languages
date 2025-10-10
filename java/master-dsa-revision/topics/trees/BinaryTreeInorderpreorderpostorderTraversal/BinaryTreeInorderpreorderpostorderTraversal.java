/**
 * Binary Tree Inorder/Preorder/Postorder Traversal
 * 
 * Given the root of a binary tree, return the inorder/preorder/postorder traversal of its nodes' values.
 * 
 * LeetCode: https://leetcode.com/problems/binary-tree-inorder-traversal/
 * Difficulty: Easy
 * Tags: dfs, recursion, stack
 * 
 * Approach:
 * - Recursive: Use recursion to traverse tree
 * - Iterative: Use stack to simulate recursion
 * - Morris: Use threading to avoid extra space
 * 
 * Pseudocode:
 * Inorder:
 * 1. Traverse left subtree
 * 2. Visit root
 * 3. Traverse right subtree
 * 
 * Preorder:
 * 1. Visit root
 * 2. Traverse left subtree
 * 3. Traverse right subtree
 * 
 * Postorder:
 * 1. Traverse left subtree
 * 2. Traverse right subtree
 * 3. Visit root
 */

import java.util.*;

public class BinaryTreeInorderPreorderPostorderTraversal {
    
    /**
     * Recursive inorder traversal - O(n) time, O(h) space
     * Most intuitive approach
     */
    public static List<Integer> inorderTraversal_recursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    
    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderHelper(node.left, result);   // Traverse left
            result.add(node.val);              // Visit root
            inorderHelper(node.right, result); // Traverse right
        }
    }
    
    /**
     * Iterative inorder traversal - O(n) time, O(h) space
     * Uses stack to simulate recursion
     */
    public static List<Integer> inorderTraversal_iterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Process current node
            current = stack.pop();
            result.add(current.val);
            
            // Move to right subtree
            current = current.right;
        }
        
        return result;
    }
    
    /**
     * Recursive preorder traversal - O(n) time, O(h) space
     */
    public static List<Integer> preorderTraversal_recursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }
    
    private static void preorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.val);              // Visit root
            preorderHelper(node.left, result); // Traverse left
            preorderHelper(node.right, result); // Traverse right
        }
    }
    
    /**
     * Iterative preorder traversal - O(n) time, O(h) space
     */
    public static List<Integer> preorderTraversal_iterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            
            // Push right first, then left (so left is processed first)
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return result;
    }
    
    /**
     * Recursive postorder traversal - O(n) time, O(h) space
     */
    public static List<Integer> postorderTraversal_recursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }
    
    private static void postorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            postorderHelper(node.left, result);   // Traverse left
            postorderHelper(node.right, result); // Traverse right
            result.add(node.val);                // Visit root
        }
    }
    
    /**
     * Iterative postorder traversal - O(n) time, O(h) space
     */
    public static List<Integer> postorderTraversal_iterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(0, node.val); // Add to beginning of result
            
            // Push left first, then right (so right is processed first)
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        
        return result;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create binary tree: 1 -> 2, 3 -> 4, 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        System.out.println("Testing Binary Tree Traversals:");
        
        System.out.println("Inorder (recursive): " + inorderTraversal_recursive(root));
        System.out.println("Inorder (iterative): " + inorderTraversal_iterative(root));
        
        System.out.println("Preorder (recursive): " + preorderTraversal_recursive(root));
        System.out.println("Preorder (iterative): " + preorderTraversal_iterative(root));
        
        System.out.println("Postorder (recursive): " + postorderTraversal_recursive(root));
        System.out.println("Postorder (iterative): " + postorderTraversal_iterative(root));
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
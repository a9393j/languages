package masterDSA.topics.trees;
import java.util.*;


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
 * - Use DFS to invert the tree
 * - Swap left and right children recursively
 * - Alternative: Use BFS with queue
 * 
 * Pseudocode:
 * 1. If root is null, return null
 * 2. Swap left and right children
 * 3. Recursively invert left and right subtrees
 * 4. Return root
 */
public class InvertBinaryTree {
    
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
     * Use DFS to invert the tree recursively
     */
    public static TreeNode invertTree_dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        // Swap left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // Recursively invert subtrees
        invertTree_dfs(root.left);
        invertTree_dfs(root.right);
        
        return root;
    }
    
    /**
     * BFS approach - O(n) time, O(w) space where w is maximum width
     * Use BFS with queue to invert the tree
     */
    public static TreeNode invertTree_bfs(TreeNode root) {
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
        // Test case: [4,2,7,1,3,6,9]
        Integer[] values = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root = createBinaryTree(values);
        
        System.out.println("Testing Invert Binary Tree:");
        System.out.println("Original Binary Tree:");
        printBinaryTree(root);
        
        TreeNode inverted1 = invertTree_dfs(root);
        System.out.println("Inverted Binary Tree (DFS):");
        printBinaryTree(inverted1);
        
        System.out.println();
        
        // Test case: [2,1,3]
        Integer[] values2 = {2, 1, 3};
        TreeNode root2 = createBinaryTree(values2);
        
        System.out.println("Original Binary Tree:");
        printBinaryTree(root2);
        
        TreeNode inverted2 = invertTree_bfs(root2);
        System.out.println("Inverted Binary Tree (BFS):");
        printBinaryTree(inverted2);
        
        System.out.println();
        
        // Test case: []
        TreeNode root3 = null;
        
        System.out.println("Empty tree:");
        TreeNode inverted3 = invertTree_dfs(root3);
        System.out.println("Inverted: " + (inverted3 == null ? "null" : "not null"));
    }
}

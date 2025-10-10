/**
 * Binary Tree Level Order Traversal
 * 
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 * 
 * LeetCode: https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Difficulty: Easy
 * Tags: bfs, queue
 * 
 * Approach:
 * - Use BFS with queue to traverse level by level
 * - Process all nodes at current level before moving to next level
 * - Track level size to separate levels
 * 
 * Pseudocode:
 * 1. Create queue and add root
 * 2. While queue is not empty:
 *    a. Get current level size
 *    b. Process all nodes at current level
 *    c. Add children to queue
 *    d. Add current level to result
 * 3. Return result
 */

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    
    /**
     * BFS with queue approach - O(n) time, O(w) space where w is maximum width
     * Most efficient approach for this problem
     */
    public static List<List<Integer>> levelOrder_bfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                // Add children to queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    /**
     * DFS approach - O(n) time, O(h) space
     * Alternative approach using DFS with level tracking
     */
    public static List<List<Integer>> levelOrder_dfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        levelOrderHelper(root, 0, result);
        return result;
    }
    
    private static void levelOrderHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;
        
        // Ensure result has enough levels
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        
        // Add current node to its level
        result.get(level).add(node.val);
        
        // Recursively process children
        levelOrderHelper(node.left, level + 1, result);
        levelOrderHelper(node.right, level + 1, result);
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create binary tree: 3 -> 9, 20 -> 15, 7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        System.out.println("Testing Binary Tree Level Order Traversal:");
        
        List<List<Integer>> result1 = levelOrder_bfs(root);
        System.out.println("BFS result: " + result1);
        
        List<List<Integer>> result2 = levelOrder_dfs(root);
        System.out.println("DFS result: " + result2);
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
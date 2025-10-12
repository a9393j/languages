package masterDSA.topics.trees;
import java.util.*;


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
 * - BFS using queue to process nodes level by level
 * - Use queue size to determine nodes in current level
 * - Alternative: Use DFS with level tracking
 * 
 * Pseudocode:
 * 1. Initialize queue with root
 * 2. While queue is not empty:
 *    a. Get current level size
 *    b. Process all nodes in current level
 *    c. Add children to queue
 * 3. Return result
 */
public class BinaryTreeLevelOrderTraversal {
    
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
     * BFS approach - O(n) time, O(w) space where w is maximum width
     * Use queue to process nodes level by level
     */
    public static java.util.List<java.util.List<Integer>> levelOrder_bfs(TreeNode root) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        if (root == null) {
            return result;
        }
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            java.util.List<Integer> currentLevel = new java.util.ArrayList<>();
            
            // Process all nodes in current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                // Add children to queue
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
     * Use DFS with level tracking
     */
    public static java.util.List<java.util.List<Integer>> levelOrder_dfs(TreeNode root) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        levelOrderHelper(root, 0, result);
        return result;
    }
    
    private static void levelOrderHelper(TreeNode root, int level, java.util.List<java.util.List<Integer>> result) {
        if (root == null) {
            return;
        }
        
        // Ensure we have enough levels
        if (level >= result.size()) {
            result.add(new java.util.ArrayList<>());
        }
        
        // Add current node to its level
        result.get(level).add(root.val);
        
        // Recursively process children
        levelOrderHelper(root.left, level + 1, result);
        levelOrderHelper(root.right, level + 1, result);
    }
    
    /**
     * Two queues approach - O(n) time, O(w) space
     * Use two queues to separate current and next level
     */
    public static java.util.List<java.util.List<Integer>> levelOrder_twoQueues(TreeNode root) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        if (root == null) {
            return result;
        }
        
        java.util.Queue<TreeNode> currentLevel = new java.util.LinkedList<>();
        java.util.Queue<TreeNode> nextLevel = new java.util.LinkedList<>();
        
        currentLevel.offer(root);
        
        while (!currentLevel.isEmpty()) {
            java.util.List<Integer> levelValues = new java.util.ArrayList<>();
            
            // Process current level
            while (!currentLevel.isEmpty()) {
                TreeNode node = currentLevel.poll();
                levelValues.add(node.val);
                
                // Add children to next level
                if (node.left != null) {
                    nextLevel.offer(node.left);
                }
                if (node.right != null) {
                    nextLevel.offer(node.right);
                }
            }
            
            result.add(levelValues);
            
            // Swap queues
            java.util.Queue<TreeNode> temp = currentLevel;
            currentLevel = nextLevel;
            nextLevel = temp;
        }
        
        return result;
    }
    
    /**
     * Level order traversal with null markers - O(n) time, O(w) space
     * Use null markers to separate levels
     */
    public static java.util.List<java.util.List<Integer>> levelOrder_nullMarkers(TreeNode root) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        if (root == null) {
            return result;
        }
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        queue.offer(null); // Level separator
        
        java.util.List<Integer> currentLevel = new java.util.ArrayList<>();
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node == null) {
                // End of current level
                result.add(new java.util.ArrayList<>(currentLevel));
                currentLevel.clear();
                
                if (!queue.isEmpty()) {
                    queue.offer(null); // Add separator for next level
                }
            } else {
                currentLevel.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return result;
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
    
    /**
     * Helper method to get height of binary tree
     */
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    
    /**
     * Helper method to count nodes in binary tree
     */
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    /**
     * Helper method to check if binary tree is balanced
     */
    public static boolean isBalanced(TreeNode root) {
        return getHeightDiff(root) != -1;
    }
    
    private static int getHeightDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftHeight = getHeightDiff(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = getHeightDiff(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    public static void main(String[] args) {
        // Test case: [3,9,20,null,null,15,7]
        Integer[] values = {3, 9, 20, null, null, 15, 7};
        TreeNode root = createBinaryTree(values);
        
        System.out.println("Testing Binary Tree Level Order Traversal:");
        System.out.println("Binary Tree:");
        printBinaryTree(root);
        
        System.out.println("\nBFS Approach:");
        java.util.List<java.util.List<Integer>> result1 = levelOrder_bfs(root);
        System.out.println(result1);
        
        System.out.println("\nDFS Approach:");
        java.util.List<java.util.List<Integer>> result2 = levelOrder_dfs(root);
        System.out.println(result2);
        
        System.out.println("\nTwo Queues Approach:");
        java.util.List<java.util.List<Integer>> result3 = levelOrder_twoQueues(root);
        System.out.println(result3);
        
        System.out.println("\nNull Markers Approach:");
        java.util.List<java.util.List<Integer>> result4 = levelOrder_nullMarkers(root);
        System.out.println(result4);
        
        // Expected: [[3],[9,20],[15,7]]
        
        System.out.println("\nTree Properties:");
        System.out.println("Height: " + getHeight(root));
        System.out.println("Node count: " + countNodes(root));
        System.out.println("Is balanced: " + isBalanced(root));
        
        System.out.println();
        
        // Test case: [1,2,3,4,5,6,7]
        Integer[] values2 = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root2 = createBinaryTree(values2);
        
        System.out.println("Binary Tree:");
        printBinaryTree(root2);
        
        System.out.println("\nLevel Order Traversal:");
        java.util.List<java.util.List<Integer>> result5 = levelOrder_bfs(root2);
        System.out.println(result5);
        
        // Expected: [[1],[2,3],[4,5,6,7]]
        
        System.out.println();
        
        // Test case: [1]
        Integer[] values3 = {1};
        TreeNode root3 = createBinaryTree(values3);
        
        System.out.println("Binary Tree:");
        printBinaryTree(root3);
        
        System.out.println("\nLevel Order Traversal:");
        java.util.List<java.util.List<Integer>> result6 = levelOrder_bfs(root3);
        System.out.println(result6);
        
        // Expected: [[1]]
        
        System.out.println();
        
        // Test case: []
        TreeNode root4 = null;
        
        System.out.println("Empty tree:");
        java.util.List<java.util.List<Integer>> result7 = levelOrder_bfs(root4);
        System.out.println(result7);
        
        // Expected: []
    }
}

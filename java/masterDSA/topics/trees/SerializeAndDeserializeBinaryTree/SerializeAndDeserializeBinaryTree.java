/**
 * Serialize and Deserialize Binary Tree
 * 
 * Serialization is the process of converting a data structure or object into a sequence 
 * of bits so that it can be stored in a file or memory buffer, or transmitted across 
 * a network connection link to be reconstructed later in the same or another computer environment.
 * 
 * LeetCode: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Difficulty: Hard
 * Tags: bfs, dfs, string
 * 
 * Approach:
 * - Use preorder traversal for serialization
 * - Use preorder traversal for deserialization
 * - Handle null nodes with special marker
 * 
 * Pseudocode:
 * Serialize:
 * 1. Use preorder traversal
 * 2. Add "null" for null nodes
 * 3. Join values with delimiter
 * 
 * Deserialize:
 * 1. Split string by delimiter
 * 2. Use preorder traversal to reconstruct tree
 * 3. Handle null nodes
 */

package masterDSA.topics.trees.SerializeAndDeserializeBinaryTree.SerializeAndDeserializeBinaryTree;

import java.util.*;

public class SerializeAndDeserializeBinaryTree {
    
    /**
     * Preorder traversal approach - O(n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static String serialize_preorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private static void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }
    
    public static TreeNode deserialize_preorder(String data) {
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue);
    }
    
    private static TreeNode deserializeHelper(Queue<String> queue) {
        String value = queue.poll();
        
        if (value.equals("null")) {
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        
        return node;
    }
    
    /**
     * BFS approach - O(n) time, O(n) space
     * Alternative approach using level order traversal
     */
    public static String serialize_bfs(TreeNode root) {
        if (root == null) return "";
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        
        return sb.toString();
    }
    
    public static TreeNode deserialize_bfs(String data) {
        if (data.isEmpty()) return null;
        
        String[] values = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            
            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.left);
            }
            i++;
            
            if (i < values.length && !values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.right);
            }
            i++;
        }
        
        return root;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create binary tree: 1 -> 2, 3 -> null, null, 4, 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        
        System.out.println("Testing Serialize and Deserialize Binary Tree:");
        
        String serialized1 = serialize_preorder(root);
        System.out.println("Preorder serialized: " + serialized1);
        
        TreeNode deserialized1 = deserialize_preorder(serialized1);
        System.out.println("Preorder deserialized: " + treeToString(deserialized1));
        
        String serialized2 = serialize_bfs(root);
        System.out.println("BFS serialized: " + serialized2);
        
        TreeNode deserialized2 = deserialize_bfs(serialized2);
        System.out.println("BFS deserialized: " + treeToString(deserialized2));
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
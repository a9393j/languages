package masterDSA.topics.trees;
import java.util.*;


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
 * - Use preorder traversal with null markers
 * - Use BFS with null markers
 * - Use comma-separated values for serialization
 * 
 * Pseudocode:
 * Serialize:
 * 1. Use preorder traversal
 * 2. Add "null" for null nodes
 * 3. Join with commas
 * 
 * Deserialize:
 * 1. Split by commas
 * 2. Use preorder reconstruction
 * 3. Handle null nodes
 */
public class SerializeAndDeserializeBinaryTree {
    
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
     * Preorder serialization - O(n) time, O(n) space
     * Use preorder traversal with null markers
     */
    public static String serialize_preorder(TreeNode root) {
        List<String> values = new ArrayList<>();
        serializeHelper(root, values);
        return String.join(",", values);
    }
    
    private static void serializeHelper(TreeNode root, List<String> values) {
        if (root == null) {
            values.add("null");
            return;
        }
        
        values.add(String.valueOf(root.val));
        serializeHelper(root.left, values);
        serializeHelper(root.right, values);
    }
    
    public static TreeNode deserialize_preorder(String data) {
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (String value : values) {
            queue.offer(value);
        }
        return deserializeHelper(queue);
    }
    
    private static TreeNode deserializeHelper(Queue<String> queue) {
        String value = queue.poll();
        if ("null".equals(value)) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        
        return root;
    }
    
    /**
     * BFS serialization - O(n) time, O(n) space
     * Use BFS with null markers
     */
    public static String serialize_bfs(TreeNode root) {
        if (root == null) {
            return "null";
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        List<String> values = new ArrayList<>();
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node == null) {
                values.add("null");
            } else {
                values.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        
        return String.join(",", values);
    }
    
    public static TreeNode deserialize_bfs(String data) {
        String[] values = data.split(",");
        if ("null".equals(values[0])) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int index = 1;
        while (!queue.isEmpty() && index < values.length) {
            TreeNode node = queue.poll();
            
            if (index < values.length && !"null".equals(values[index])) {
                node.left = new TreeNode(Integer.parseInt(values[index]));
                queue.offer(node.left);
            }
            index++;
            
            if (index < values.length && !"null".equals(values[index])) {
                node.right = new TreeNode(Integer.parseInt(values[index]));
                queue.offer(node.right);
            }
            index++;
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
        // Test case: [1,2,3,null,null,4,5]
        Integer[] values = {1, 2, 3, null, null, 4, 5};
        TreeNode root = createBinaryTree(values);
        
        System.out.println("Testing Serialize and Deserialize Binary Tree:");
        System.out.println("Original Binary Tree:");
        printBinaryTree(root);
        
        String serialized1 = serialize_preorder(root);
        System.out.println("Preorder serialized: " + serialized1);
        
        TreeNode deserialized1 = deserialize_preorder(serialized1);
        System.out.println("Deserialized Binary Tree:");
        printBinaryTree(deserialized1);
        
        String serialized2 = serialize_bfs(root);
        System.out.println("BFS serialized: " + serialized2);
        
        TreeNode deserialized2 = deserialize_bfs(serialized2);
        System.out.println("Deserialized Binary Tree:");
        printBinaryTree(deserialized2);
        
        System.out.println();
        
        // Test case: []
        TreeNode root2 = null;
        
        System.out.println("Empty tree:");
        String serialized3 = serialize_preorder(root2);
        System.out.println("Serialized: " + serialized3);
        
        TreeNode deserialized3 = deserialize_preorder(serialized3);
        System.out.println("Deserialized: " + (deserialized3 == null ? "null" : "not null"));
    }
}

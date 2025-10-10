/**
 * Clone Graph
 * 
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * 
 * LeetCode: https://leetcode.com/problems/clone-graph/
 * Difficulty: Medium
 * Tags: dfs, bfs, hashmap
 * 
 * Approach:
 * - Use HashMap to map original nodes to cloned nodes
 * - Use DFS or BFS to traverse graph
 * - Create cloned nodes and connect them
 * 
 * Pseudocode:
 * 1. Create HashMap to store original -> cloned node mapping
 * 2. Use DFS/BFS to traverse graph
 * 3. For each node:
 *    a. If not cloned, create cloned node
 *    b. Add to HashMap
 *    c. Recursively clone neighbors
 * 4. Return cloned node
 */

import java.util.*;

public class CloneGraph {
    
    /**
     * DFS approach - O(V + E) time, O(V) space
     * Most efficient approach for this problem
     */
    public static Node cloneGraph_dfs(Node node) {
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> visited = new HashMap<>();
        return dfsClone(node, visited);
    }
    
    private static Node dfsClone(Node node, Map<Node, Node> visited) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        // Create cloned node
        Node clonedNode = new Node(node.val);
        visited.put(node, clonedNode);
        
        // Clone neighbors
        for (Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(dfsClone(neighbor, visited));
        }
        
        return clonedNode;
    }
    
    /**
     * BFS approach - O(V + E) time, O(V) space
     * Alternative approach using BFS
     */
    public static Node cloneGraph_bfs(Node node) {
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        
        // Create cloned node for root
        Node clonedNode = new Node(node.val);
        visited.put(node, clonedNode);
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            // Clone neighbors
            for (Node neighbor : current.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }
        
        return clonedNode;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create graph: 1 -> 2, 4 -> 3, 4
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        
        System.out.println("Testing Clone Graph:");
        
        Node cloned1 = cloneGraph_dfs(node1);
        System.out.println("DFS clone result: " + cloned1.val);
        
        Node cloned2 = cloneGraph_bfs(node1);
        System.out.println("BFS clone result: " + cloned2.val);
    }
    
    // Node definition
    public static class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
package masterDSA.topics.graphs.CloneGraph;
import java.util.*;


/**
 * Clone Graph
 * 
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * 
 * LeetCode: https://leetcode.com/problems/clone-graph/
 * Difficulty: Medium
 * Tags: dfs, bfs, graph
 * 
 * Approach:
 * - DFS: Use recursion with HashMap to track visited nodes
 * - BFS: Use queue to traverse and clone nodes level by level
 * - Iterative DFS: Use stack to simulate recursion
 * 
 * Pseudocode:
 * DFS:
 * 1. Create HashMap to store original -> cloned mapping
 * 2. For each unvisited neighbor:
 *    a. Clone the neighbor recursively
 *    b. Add cloned neighbor to current node's neighbors
 * 3. Return cloned node
 */
public class CloneGraph {
    
    /**
     * Node class for graph representation
     */
    public static class Node {
        public int val;
        public java.util.List<Node> neighbors;
        
        public Node() {
            val = 0;
            neighbors = new java.util.ArrayList<>();
        }
        
        public Node(int _val) {
            val = _val;
            neighbors = new java.util.ArrayList<>();
        }
        
        public Node(int _val, java.util.List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    
    /**
     * DFS approach - O(V + E) time, O(V) space
     * Use recursion with HashMap to track visited nodes
     */
    public static Node cloneGraph_dfs(Node node) {
        if (node == null) {
            return null;
        }
        
        java.util.Map<Node, Node> visited = new java.util.HashMap<>();
        return dfsClone(node, visited);
    }
    
    private static Node dfsClone(Node node, java.util.Map<Node, Node> visited) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        Node clonedNode = new Node(node.val);
        visited.put(node, clonedNode);
        
        for (Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(dfsClone(neighbor, visited));
        }
        
        return clonedNode;
    }
    
    /**
     * BFS approach - O(V + E) time, O(V) space
     * Use queue to traverse and clone nodes level by level
     */
    public static Node cloneGraph_bfs(Node node) {
        if (node == null) {
            return null;
        }
        
        java.util.Map<Node, Node> visited = new java.util.HashMap<>();
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        
        Node clonedNode = new Node(node.val);
        visited.put(node, clonedNode);
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            for (Node neighbor : current.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    Node clonedNeighbor = new Node(neighbor.val);
                    visited.put(neighbor, clonedNeighbor);
                    queue.offer(neighbor);
                }
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }
        
        return clonedNode;
    }
    
    /**
     * Iterative DFS approach - O(V + E) time, O(V) space
     * Use stack to simulate recursion
     */
    public static Node cloneGraph_iterative(Node node) {
        if (node == null) {
            return null;
        }
        
        java.util.Map<Node, Node> visited = new java.util.HashMap<>();
        java.util.Stack<Node> stack = new java.util.Stack<>();
        
        Node clonedNode = new Node(node.val);
        visited.put(node, clonedNode);
        stack.push(node);
        
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            
            for (Node neighbor : current.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    Node clonedNeighbor = new Node(neighbor.val);
                    visited.put(neighbor, clonedNeighbor);
                    stack.push(neighbor);
                }
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }
        
        return clonedNode;
    }
    
    /**
     * Helper method to create a test graph
     */
    public static Node createTestGraph() {
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
        
        return node1;
    }
    
    /**
     * Helper method to print graph
     */
    public static void printGraph(Node node) {
        if (node == null) {
            System.out.println("Graph is null");
            return;
        }
        
        java.util.Set<Node> visited = new java.util.HashSet<>();
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        
        queue.offer(node);
        visited.add(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print("Node " + current.val + " -> ");
            
            for (Node neighbor : current.neighbors) {
                System.out.print(neighbor.val + " ");
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Helper method to check if two graphs are equal
     */
    public static boolean areGraphsEqual(Node graph1, Node graph2) {
        if (graph1 == null && graph2 == null) {
            return true;
        }
        
        if (graph1 == null || graph2 == null) {
            return false;
        }
        
        java.util.Map<Node, Node> mapping = new java.util.HashMap<>();
        return dfsCompare(graph1, graph2, mapping);
    }
    
    private static boolean dfsCompare(Node node1, Node node2, java.util.Map<Node, Node> mapping) {
        if (mapping.containsKey(node1)) {
            return mapping.get(node1) == node2;
        }
        
        if (node1.val != node2.val || node1.neighbors.size() != node2.neighbors.size()) {
            return false;
        }
        
        mapping.put(node1, node2);
        
        for (int i = 0; i < node1.neighbors.size(); i++) {
            if (!dfsCompare(node1.neighbors.get(i), node2.neighbors.get(i), mapping)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to find graph diameter
     */
    public static int findDiameter(Node node) {
        if (node == null) {
            return 0;
        }
        
        Node farthest = bfsFarthest(node);
        return bfsDistance(farthest);
    }
    
    private static Node bfsFarthest(Node start) {
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        java.util.Set<Node> visited = new java.util.HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        Node farthest = start;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                farthest = current;
                
                for (Node neighbor : current.neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        return farthest;
    }
    
    private static int bfsDistance(Node start) {
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        java.util.Set<Node> visited = new java.util.HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        int distance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                
                for (Node neighbor : current.neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
            
            if (!queue.isEmpty()) {
                distance++;
            }
        }
        
        return distance;
    }
    
    /**
     * Helper method to find graph center
     */
    public static Node findCenter(Node node) {
        if (node == null) {
            return null;
        }
        
        java.util.Map<Node, Integer> degrees = new java.util.HashMap<>();
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        java.util.Set<Node> visited = new java.util.HashSet<>();
        
        queue.offer(node);
        visited.add(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            degrees.put(current, current.neighbors.size());
            
            for (Node neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        Node center = null;
        int minDegree = Integer.MAX_VALUE;
        
        for (java.util.Map.Entry<Node, Integer> entry : degrees.entrySet()) {
            if (entry.getValue() < minDegree) {
                minDegree = entry.getValue();
                center = entry.getKey();
            }
        }
        
        return center;
    }
    
    /**
     * Helper method to check if graph is bipartite
     */
    public static boolean isBipartite(Node node) {
        if (node == null) {
            return true;
        }
        
        java.util.Map<Node, Integer> colors = new java.util.HashMap<>();
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        
        colors.put(node, 0);
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int currentColor = colors.get(current);
            
            for (Node neighbor : current.neighbors) {
                if (!colors.containsKey(neighbor)) {
                    colors.put(neighbor, 1 - currentColor);
                    queue.offer(neighbor);
                } else if (colors.get(neighbor) == currentColor) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Test case: [[2,4],[1,3],[2,4],[1,3]]
        System.out.println("Testing Clone Graph:");
        
        Node originalGraph = createTestGraph();
        System.out.println("Original graph:");
        printGraph(originalGraph);
        
        Node clonedGraph1 = cloneGraph_dfs(originalGraph);
        System.out.println("Cloned graph (DFS):");
        printGraph(clonedGraph1);
        
        Node clonedGraph2 = cloneGraph_bfs(originalGraph);
        System.out.println("Cloned graph (BFS):");
        printGraph(clonedGraph2);
        
        Node clonedGraph3 = cloneGraph_iterative(originalGraph);
        System.out.println("Cloned graph (Iterative DFS):");
        printGraph(clonedGraph3);
        
        // Expected: All cloned graphs should have the same structure as original
        
        System.out.println();
        
        // Test equality
        boolean areEqual1 = areGraphsEqual(originalGraph, clonedGraph1);
        System.out.println("Original and DFS clone are equal: " + areEqual1);
        
        boolean areEqual2 = areGraphsEqual(originalGraph, clonedGraph2);
        System.out.println("Original and BFS clone are equal: " + areEqual2);
        
        boolean areEqual3 = areGraphsEqual(originalGraph, clonedGraph3);
        System.out.println("Original and Iterative DFS clone are equal: " + areEqual3);
        
        // Expected: All should be true
        
        System.out.println();
        
        // Test helper methods
        int diameter = findDiameter(originalGraph);
        System.out.println("Graph diameter: " + diameter);
        
        Node center = findCenter(originalGraph);
        System.out.println("Graph center: Node " + center.val);
        
        boolean isBipartite = isBipartite(originalGraph);
        System.out.println("Is bipartite: " + isBipartite);
        
        // Expected: Diameter = 2, Center = Node 1 (or any node), Is bipartite = true
    }
}

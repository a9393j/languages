/**
 * Redundant Connection
 * 
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, 
 * with one additional edge added. The added edge has two different vertices chosen from 1 to n, 
 * and was not an edge that already existed.
 * 
 * LeetCode: https://leetcode.com/problems/redundant-connection/
 * Difficulty: Medium
 * Tags: union-find, graph
 * 
 * Approach:
 * - Use Union-Find to detect cycles
 * - For each edge, check if nodes are already connected
 * - If connected, edge is redundant
 * 
 * Pseudocode:
 * 1. Create Union-Find data structure
 * 2. For each edge:
 *    a. Find roots of both nodes
 *    b. If roots are same, edge is redundant
 *    c. Otherwise, union the nodes
 * 3. Return redundant edge
 */

package masterDSA.topics.graphs.RedundantConnection.RedundantConnection;

import java.util.*;

public class RedundantConnection {
    
    /**
     * Union-Find approach - O(n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static int[] findRedundantConnection_unionFind(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);
        
        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return edge; // Redundant edge found
            }
            uf.union(edge[0], edge[1]);
        }
        
        return new int[0]; // No redundant edge
    }
    
    /**
     * DFS approach - O(n^2) time, O(n) space
     * Alternative approach using DFS
     */
    public static int[] findRedundantConnection_dfs(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            // Check if adding this edge creates a cycle
            if (hasCycle(graph, edge[0], edge[1], new boolean[n + 1])) {
                return edge;
            }
            
            // Add edge to graph
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        return new int[0];
    }
    
    private static boolean hasCycle(List<List<Integer>> graph, int u, int v, boolean[] visited) {
        if (u == v) {
            return true;
        }
        
        visited[u] = true;
        
        for (int neighbor : graph.get(u)) {
            if (!visited[neighbor] && hasCycle(graph, neighbor, v, visited)) {
                return true;
            }
        }
        
        visited[u] = false;
        return false;
    }
    
    /**
     * Union-Find implementation
     */
    private static class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[][] edges1 = {{1, 2}, {1, 3}, {2, 3}};
        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        
        System.out.println("Testing Redundant Connection:");
        
        System.out.println("Edges: " + Arrays.deepToString(edges1));
        System.out.println("Union-Find result: " + Arrays.toString(findRedundantConnection_unionFind(edges1)));
        System.out.println("DFS result: " + Arrays.toString(findRedundantConnection_dfs(edges1)));
        
        System.out.println("Edges: " + Arrays.deepToString(edges2));
        System.out.println("Union-Find result: " + Arrays.toString(findRedundantConnection_unionFind(edges2)));
        System.out.println("DFS result: " + Arrays.toString(findRedundantConnection_dfs(edges2)));
    }
}
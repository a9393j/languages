package masterDSA.topics.graphs.GraphValidTree;
import java.util.*;


/**
 * Graph Valid Tree
 * 
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges, write a function 
 * to check whether these edges make up a valid tree.
 * 
 * LeetCode: https://leetcode.com/problems/graph-valid-tree/
 * Difficulty: Medium
 * Tags: union-find, dfs, bfs
 * 
 * Approach:
 * - Union-Find: Check for cycles and connectivity
 * - DFS: Check for cycles and visit all nodes
 * - BFS: Use queue to check connectivity and cycles
 * 
 * Pseudocode:
 * Union-Find:
 * 1. Initialize Union-Find with n nodes
 * 2. For each edge, union the two nodes
 * 3. If nodes are already connected, cycle detected
 * 4. Check if all nodes are connected
 */
public class GraphValidTree {
    
    /**
     * Union-Find approach - O(E) time, O(V) space
     * Use disjoint set to detect cycles and check connectivity
     */
    public static boolean validTree_unionFind(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false; // Tree must have exactly n-1 edges
        }
        
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            if (uf.find(u) == uf.find(v)) {
                return false; // Cycle detected
            }
            
            uf.union(u, v);
        }
        
        return true;
    }
    
    /**
     * Union-Find data structure
     */
    static class UnionFind {
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
                parent[x] = find(parent[x]);
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
     * DFS approach - O(V + E) time, O(V) space
     * Use DFS to detect cycles and check connectivity
     */
    public static boolean validTree_dfs(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false; // Tree must have exactly n-1 edges
        }
        
        // Build adjacency list
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        
        // Check for cycles
        if (hasCycle(graph, 0, -1, visited)) {
            return false;
        }
        
        // Check connectivity
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasCycle(java.util.List<java.util.List<Integer>> graph, int node, int parent, boolean[] visited) {
        visited[node] = true;
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (hasCycle(graph, neighbor, node, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true; // Cycle detected
            }
        }
        
        return false;
    }
    
    /**
     * BFS approach - O(V + E) time, O(V) space
     * Use BFS to detect cycles and check connectivity
     */
    public static boolean validTree_bfs(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false; // Tree must have exactly n-1 edges
        }
        
        // Build adjacency list
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        
        queue.offer(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        // Check connectivity
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to find number of connected components
     */
    public static int numberOfConnectedComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        
        java.util.Set<Integer> roots = new java.util.HashSet<>();
        for (int i = 0; i < n; i++) {
            roots.add(uf.find(i));
        }
        
        return roots.size();
    }
    
    /**
     * Helper method to find cycles in graph
     */
    public static boolean hasCycle(int n, int[][] edges) {
        if (edges.length < n) {
            return false; // Not enough edges for cycle
        }
        
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            if (uf.find(u) == uf.find(v)) {
                return true; // Cycle detected
            }
            
            uf.union(u, v);
        }
        
        return false;
    }
    
    /**
     * Helper method to find minimum spanning tree
     */
    public static int[][] findMST(int n, int[][] edges) {
        if (edges.length < n - 1) {
            return new int[0][0]; // Not enough edges
        }
        
        // Sort edges by weight (assuming edges are [u, v, weight])
        java.util.Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        
        UnionFind uf = new UnionFind(n);
        java.util.List<int[]> mst = new java.util.ArrayList<>();
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                mst.add(new int[]{u, v});
                
                if (mst.size() == n - 1) {
                    break;
                }
            }
        }
        
        return mst.toArray(new int[0][]);
    }
    
    /**
     * Helper method to find shortest path between two nodes
     */
    public static java.util.List<Integer> shortestPath(int n, int[][] edges, int start, int end) {
        // Build adjacency list
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // BFS to find shortest path
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        java.util.Arrays.fill(parent, -1);
        
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            if (current == end) {
                break;
            }
            
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    queue.offer(neighbor);
                }
            }
        }
        
        // Reconstruct path
        if (!visited[end]) {
            return new java.util.ArrayList<>(); // No path found
        }
        
        java.util.List<Integer> path = new java.util.ArrayList<>();
        int current = end;
        
        while (current != -1) {
            path.add(0, current);
            current = parent[current];
        }
        
        return path;
    }
    
    /**
     * Helper method to find diameter of tree
     */
    public static int findDiameter(int n, int[][] edges) {
        if (n <= 1) {
            return 0;
        }
        
        // Build adjacency list
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // Find farthest node from node 0
        int[] farthest = bfsFarthest(graph, 0);
        int farthestNode = farthest[0];
        int maxDistance = farthest[1];
        
        // Find farthest node from the farthest node
        farthest = bfsFarthest(graph, farthestNode);
        
        return farthest[1];
    }
    
    private static int[] bfsFarthest(java.util.List<java.util.List<Integer>> graph, int start) {
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        
        queue.offer(start);
        visited[start] = true;
        
        int farthestNode = start;
        int maxDistance = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                farthestNode = current;
                
                for (int neighbor : graph.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
            
            if (!queue.isEmpty()) {
                maxDistance++;
            }
        }
        
        return new int[]{farthestNode, maxDistance};
    }
    
    public static void main(String[] args) {
        // Test case: n = 5, edges = [[0,1],[1,2],[3,4]]
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        
        System.out.println("Testing Graph Valid Tree:");
        System.out.println("n = " + n1);
        System.out.println("edges = " + java.util.Arrays.deepToString(edges1));
        
        boolean result1 = validTree_unionFind(n1, edges1);
        System.out.println("Union-Find result: " + result1);
        
        boolean result2 = validTree_dfs(n1, edges1);
        System.out.println("DFS result: " + result2);
        
        boolean result3 = validTree_bfs(n1, edges1);
        System.out.println("BFS result: " + result3);
        
        // Expected: false (not connected)
        
        System.out.println();
        
        // Test case: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
        int n2 = 5;
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        
        System.out.println("n = " + n2);
        System.out.println("edges = " + java.util.Arrays.deepToString(edges2));
        
        boolean result4 = validTree_unionFind(n2, edges2);
        System.out.println("Union-Find result: " + result4);
        
        boolean result5 = validTree_dfs(n2, edges2);
        System.out.println("DFS result: " + result5);
        
        boolean result6 = validTree_bfs(n2, edges2);
        System.out.println("BFS result: " + result6);
        
        // Expected: true (valid tree)
        
        System.out.println();
        
        // Test case: n = 3, edges = [[0,1],[1,2],[2,0]]
        int n3 = 3;
        int[][] edges3 = {{0, 1}, {1, 2}, {2, 0}};
        
        System.out.println("n = " + n3);
        System.out.println("edges = " + java.util.Arrays.deepToString(edges3));
        
        boolean result7 = validTree_unionFind(n3, edges3);
        System.out.println("Union-Find result: " + result7);
        
        // Expected: false (cycle detected)
        
        System.out.println();
        
        // Test helper methods
        int connectedComponents = numberOfConnectedComponents(n1, edges1);
        System.out.println("Number of connected components: " + connectedComponents);
        
        boolean hasCycle = hasCycle(n3, edges3);
        System.out.println("Has cycle: " + hasCycle);
        
        java.util.List<Integer> shortestPath = shortestPath(n2, edges2, 0, 4);
        System.out.println("Shortest path from 0 to 4: " + shortestPath);
        
        int diameter = findDiameter(n2, edges2);
        System.out.println("Tree diameter: " + diameter);
        
        // Expected: Connected components = 2, Has cycle = true, Shortest path = [0, 1, 4], Diameter = 2
    }
}

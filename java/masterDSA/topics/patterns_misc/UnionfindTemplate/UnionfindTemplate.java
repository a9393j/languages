/**
 * Union-Find Template
 * 
 * Union-Find (Disjoint Set Union) is a data structure that keeps track of elements 
 * which are split into one or more disjoint sets. It has two primary operations:
 * - Find: Determine which subset a particular element is in
 * - Union: Join two subsets into a single subset
 * 
 * LeetCode: https://leetcode.com/problems/redundant-connection/
 * Difficulty: Medium
 * Tags: union-find
 * 
 * Approach:
 * - Use path compression and union by rank
 * - Find root of element with path compression
 * - Union two elements by rank
 * 
 * Pseudocode:
 * 1. Initialize parent array and rank array
 * 2. Find: use path compression to find root
 * 3. Union: use union by rank to merge sets
 */

package masterDSA.topics.patterns_misc.UnionfindTemplate.UnionfindTemplate;

import java.util.*;

public class UnionFindTemplate {
    
    /**
     * Union-Find implementation - O(α(n)) time per operation, O(n) space
     * Most efficient approach for this problem
     */
    public static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int components;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) {
                return false; // Already in same set
            }
            
            // Union by rank
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            
            components--;
            return true;
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
        
        public int getComponents() {
            return components;
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        System.out.println("Testing Union-Find Template:");
        
        UnionFind uf = new UnionFind(5);
        
        System.out.println("Initial components: " + uf.getComponents());
        
        uf.union(0, 1);
        System.out.println("After union(0, 1), components: " + uf.getComponents());
        System.out.println("0 and 1 connected: " + uf.connected(0, 1));
        
        uf.union(2, 3);
        System.out.println("After union(2, 3), components: " + uf.getComponents());
        System.out.println("2 and 3 connected: " + uf.connected(2, 3));
        
        uf.union(1, 2);
        System.out.println("After union(1, 2), components: " + uf.getComponents());
        System.out.println("0 and 3 connected: " + uf.connected(0, 3));
        
        uf.union(3, 4);
        System.out.println("After union(3, 4), components: " + uf.getComponents());
        System.out.println("All connected: " + uf.connected(0, 4));
    }
}
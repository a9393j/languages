/**
 * Network Delay Time (Dijkstra)
 * 
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, 
 * a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the 
 * source node, vi is the target node, and wi is the time it takes for a signal to travel 
 * from source to target.
 * 
 * LeetCode: https://leetcode.com/problems/network-delay-time/
 * Difficulty: Medium
 * Tags: dijkstra, shortest-path
 * 
 * Approach:
 * - Use Dijkstra's algorithm to find shortest paths
 * - Use priority queue to process nodes in order of distance
 * - Update distances to neighbors
 * 
 * Pseudocode:
 * 1. Create distance array and priority queue
 * 2. Add source node to queue with distance 0
 * 3. While queue is not empty:
 *    a. Remove node with minimum distance
 *    b. Update distances to neighbors
 *    c. Add neighbors to queue if distance improved
 * 4. Return maximum distance
 */

package masterDSA.topics.graphs.NetworkDelayTimeDijkstra.NetworkDelayTimeDijkstra;

import java.util.*;

public class NetworkDelayTimeDijkstra {
    
    /**
     * Dijkstra's algorithm - O(E log V) time, O(V + E) space
     * Most efficient approach for this problem
     */
    public static int networkDelayTime_dijkstra(int[][] times, int n, int k) {
        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        
        // Dijkstra's algorithm
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int distance = current[1];
            
            if (distance > distances[node]) {
                continue;
            }
            
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int nextDistance = distance + neighbor[1];
                
                if (nextDistance < distances[nextNode]) {
                    distances[nextNode] = nextDistance;
                    pq.offer(new int[]{nextNode, nextDistance});
                }
            }
        }
        
        // Find maximum distance
        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1; // Unreachable node
            }
            maxDistance = Math.max(maxDistance, distances[i]);
        }
        
        return maxDistance;
    }
    
    /**
     * Bellman-Ford algorithm - O(VE) time, O(V) space
     * Alternative approach using Bellman-Ford
     */
    public static int networkDelayTime_bellmanFord(int[][] times, int n, int k) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;
        
        // Relax edges n-1 times
        for (int i = 0; i < n - 1; i++) {
            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                
                if (distances[u] != Integer.MAX_VALUE && distances[u] + w < distances[v]) {
                    distances[v] = distances[u] + w;
                }
            }
        }
        
        // Find maximum distance
        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1; // Unreachable node
            }
            maxDistance = Math.max(maxDistance, distances[i]);
        }
        
        return maxDistance;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;
        
        System.out.println("Testing Network Delay Time (Dijkstra):");
        System.out.println("Times: " + Arrays.deepToString(times));
        System.out.println("Nodes: " + n + ", Source: " + k);
        
        int result1 = networkDelayTime_dijkstra(times, n, k);
        System.out.println("Dijkstra result: " + result1);
        
        int result2 = networkDelayTime_bellmanFord(times, n, k);
        System.out.println("Bellman-Ford result: " + result2);
    }
}
/**
 * Course Schedule
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you 
 * must take course bi first if you want to take course ai.
 * 
 * LeetCode: https://leetcode.com/problems/course-schedule/
 * Difficulty: Medium
 * Tags: topological-sort, cycle-detection
 * 
 * Approach:
 * - Use topological sort to detect cycles
 * - Build adjacency list from prerequisites
 * - Use DFS or BFS to detect cycles
 * 
 * Pseudocode:
 * 1. Build adjacency list from prerequisites
 * 2. Use DFS to detect cycles:
 *    a. Mark node as visiting
 *    b. Recursively visit neighbors
 *    c. If neighbor is visiting, cycle detected
 *    d. Mark node as visited
 * 3. Return true if no cycles found
 */

package masterDSA.topics.graphs.CourseSchedule.CourseSchedule;

import java.util.*;

public class CourseSchedule {
    
    /**
     * DFS approach - O(V + E) time, O(V + E) space
     * Most efficient approach for this problem
     */
    public static boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }
        
        // 0: unvisited, 1: visiting, 2: visited
        int[] state = new int[numCourses];
        
        // Check each course for cycles
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycle(graph, i, state)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasCycle(List<List<Integer>> graph, int course, int[] state) {
        if (state[course] == 1) {
            return true; // Cycle detected
        }
        
        if (state[course] == 2) {
            return false; // Already visited
        }
        
        state[course] = 1; // Mark as visiting
        
        // Check all prerequisites
        for (int prerequisite : graph.get(course)) {
            if (hasCycle(graph, prerequisite, state)) {
                return true;
            }
        }
        
        state[course] = 2; // Mark as visited
        return false;
    }
    
    /**
     * BFS approach - O(V + E) time, O(V + E) space
     * Alternative approach using BFS
     */
    public static boolean canFinish_bfs(int numCourses, int[][] prerequisites) {
        // Build adjacency list and in-degree count
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }
        
        // Add courses with no prerequisites to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completedCourses = 0;
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completedCourses++;
            
            // Reduce in-degree for dependent courses
            for (int dependent : graph.get(course)) {
                inDegree[dependent]--;
                if (inDegree[dependent] == 0) {
                    queue.offer(dependent);
                }
            }
        }
        
        return completedCourses == numCourses;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        
        System.out.println("Testing Course Schedule:");
        
        System.out.println("Courses: " + numCourses1 + ", Prerequisites: " + Arrays.deepToString(prerequisites1));
        System.out.println("DFS result: " + canFinish_dfs(numCourses1, prerequisites1));
        System.out.println("BFS result: " + canFinish_bfs(numCourses1, prerequisites1));
        
        System.out.println("Courses: " + numCourses2 + ", Prerequisites: " + Arrays.deepToString(prerequisites2));
        System.out.println("DFS result: " + canFinish_dfs(numCourses2, prerequisites2));
        System.out.println("BFS result: " + canFinish_bfs(numCourses2, prerequisites2));
    }
}
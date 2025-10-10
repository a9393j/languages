/**
 * Course Schedule II
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you 
 * must take course bi first if you want to take course ai.
 * 
 * LeetCode: https://leetcode.com/problems/course-schedule-ii/
 * Difficulty: Medium
 * Tags: topological-sort
 * 
 * Approach:
 * - Use topological sort to find valid course order
 * - Build adjacency list from prerequisites
 * - Use BFS to find topological order
 * 
 * Pseudocode:
 * 1. Build adjacency list and in-degree count
 * 2. Add courses with no prerequisites to queue
 * 3. While queue is not empty:
 *    a. Remove course from queue
 *    b. Add to result
 *    c. Reduce in-degree for dependent courses
 *    d. Add courses with no remaining prerequisites to queue
 * 4. Return result if all courses completed
 */

package masterDSA.topics.graphs.CourseScheduleIi.CourseScheduleIi;

import java.util.*;

public class CourseScheduleII {
    
    /**
     * BFS approach - O(V + E) time, O(V + E) space
     * Most efficient approach for this problem
     */
    public static int[] findOrder_bfs(int numCourses, int[][] prerequisites) {
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
        
        int[] result = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            
            // Reduce in-degree for dependent courses
            for (int dependent : graph.get(course)) {
                inDegree[dependent]--;
                if (inDegree[dependent] == 0) {
                    queue.offer(dependent);
                }
            }
        }
        
        // Return result if all courses completed
        return index == numCourses ? result : new int[0];
    }
    
    /**
     * DFS approach - O(V + E) time, O(V + E) space
     * Alternative approach using DFS
     */
    public static int[] findOrder_dfs(int numCourses, int[][] prerequisites) {
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
        List<Integer> result = new ArrayList<>();
        
        // Check each course for cycles and build result
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycle(graph, i, state, result)) {
                return new int[0]; // Cycle detected
            }
        }
        
        // Reverse result to get correct order
        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    private static boolean hasCycle(List<List<Integer>> graph, int course, int[] state, List<Integer> result) {
        if (state[course] == 1) {
            return true; // Cycle detected
        }
        
        if (state[course] == 2) {
            return false; // Already visited
        }
        
        state[course] = 1; // Mark as visiting
        
        // Check all prerequisites
        for (int prerequisite : graph.get(course)) {
            if (hasCycle(graph, prerequisite, state, result)) {
                return true;
            }
        }
        
        state[course] = 2; // Mark as visited
        result.add(course);
        return false;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        
        int numCourses2 = 4;
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        
        System.out.println("Testing Course Schedule II:");
        
        System.out.println("Courses: " + numCourses1 + ", Prerequisites: " + Arrays.deepToString(prerequisites1));
        System.out.println("BFS result: " + Arrays.toString(findOrder_bfs(numCourses1, prerequisites1)));
        System.out.println("DFS result: " + Arrays.toString(findOrder_dfs(numCourses1, prerequisites1)));
        
        System.out.println("Courses: " + numCourses2 + ", Prerequisites: " + Arrays.deepToString(prerequisites2));
        System.out.println("BFS result: " + Arrays.toString(findOrder_bfs(numCourses2, prerequisites2)));
        System.out.println("DFS result: " + Arrays.toString(findOrder_dfs(numCourses2, prerequisites2)));
    }
}
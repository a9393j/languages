package masterDSA.topics.graphs;
import java.util.*;


/**
 * Course Schedule
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that 
 * you must take course bi first if you want to take course ai.
 * 
 * LeetCode: https://leetcode.com/problems/course-schedule/
 * Difficulty: Medium
 * Tags: topological-sort, dfs, bfs
 * 
 * Approach:
 * - DFS: Check for cycles using DFS with three states (unvisited, visiting, visited)
 * - BFS: Use Kahn's algorithm with in-degree counting
 * - Topological Sort: Build adjacency list and perform topological sort
 * 
 * Pseudocode:
 * DFS:
 * 1. Build adjacency list from prerequisites
 * 2. For each unvisited node, perform DFS
 * 3. If cycle detected (visiting state), return false
 * 4. Return true if no cycles found
 */
public class CourseSchedule {
    
    /**
     * DFS approach - O(V + E) time, O(V + E) space
     * Use DFS to detect cycles with three states
     */
    public static boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        // Build adjacency list
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        // 0: unvisited, 1: visiting, 2: visited
        int[] state = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycle(graph, i, state)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasCycle(java.util.List<java.util.List<Integer>> graph, int node, int[] state) {
        if (state[node] == 1) {
            return true; // Cycle detected
        }
        
        if (state[node] == 2) {
            return false; // Already visited
        }
        
        state[node] = 1; // Mark as visiting
        
        for (int neighbor : graph.get(node)) {
            if (hasCycle(graph, neighbor, state)) {
                return true;
            }
        }
        
        state[node] = 2; // Mark as visited
        return false;
    }
    
    /**
     * BFS approach (Kahn's algorithm) - O(V + E) time, O(V + E) space
     * Use in-degree counting and queue
     */
    public static boolean canFinish_bfs(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        // Build adjacency list and in-degree count
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        
        // Add courses with no prerequisites to queue
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completedCourses = 0;
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completedCourses++;
            
            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return completedCourses == numCourses;
    }
    
    /**
     * Topological sort approach - O(V + E) time, O(V + E) space
     * Return topological order if possible
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }
        
        // Build adjacency list and in-degree count
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        
        // Add courses with no prerequisites to queue
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
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
            
            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return index == numCourses ? result : new int[0];
    }
    
    /**
     * DFS with path tracking - O(V + E) time, O(V + E) space
     * Track path to detect cycles
     */
    public static boolean canFinish_dfsPath(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        // Build adjacency list
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        boolean[] visited = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCyclePath(graph, i, visited, path)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasCyclePath(java.util.List<java.util.List<Integer>> graph, int node, boolean[] visited, boolean[] path) {
        visited[node] = true;
        path[node] = true;
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (hasCyclePath(graph, neighbor, visited, path)) {
                    return true;
                }
            } else if (path[neighbor]) {
                return true; // Cycle detected
            }
        }
        
        path[node] = false;
        return false;
    }
    
    /**
     * Helper method to build adjacency list
     */
    public static java.util.List<java.util.List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        return graph;
    }
    
    /**
     * Helper method to find in-degrees
     */
    public static int[] findInDegrees(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        
        for (int[] prereq : prerequisites) {
            inDegree[prereq[0]]++;
        }
        
        return inDegree;
    }
    
    /**
     * Helper method to check if graph is acyclic
     */
    public static boolean isAcyclic(java.util.List<java.util.List<Integer>> graph) {
        int n = graph.size();
        int[] state = new int[n]; // 0: unvisited, 1: visiting, 2: visited
        
        for (int i = 0; i < n; i++) {
            if (state[i] == 0 && hasCycle(graph, i, state)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to find strongly connected components
     */
    public static java.util.List<java.util.List<Integer>> findSCCs(java.util.List<java.util.List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        // First pass: fill stack
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsFillStack(graph, i, visited, stack);
            }
        }
        
        // Build transpose graph
        java.util.List<java.util.List<Integer>> transpose = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            transpose.add(new java.util.ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph.get(i)) {
                transpose.get(neighbor).add(i);
            }
        }
        
        // Second pass: process in reverse order
        visited = new boolean[n];
        java.util.List<java.util.List<Integer>> sccs = new java.util.ArrayList<>();
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                java.util.List<Integer> scc = new java.util.ArrayList<>();
                dfsSCC(transpose, node, visited, scc);
                sccs.add(scc);
            }
        }
        
        return sccs;
    }
    
    private static void dfsFillStack(java.util.List<java.util.List<Integer>> graph, int node, boolean[] visited, java.util.Stack<Integer> stack) {
        visited[node] = true;
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfsFillStack(graph, neighbor, visited, stack);
            }
        }
        
        stack.push(node);
    }
    
    private static void dfsSCC(java.util.List<java.util.List<Integer>> graph, int node, boolean[] visited, java.util.List<Integer> scc) {
        visited[node] = true;
        scc.add(node);
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfsSCC(graph, neighbor, visited, scc);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test case: numCourses = 2, prerequisites = [[1,0]]
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        
        System.out.println("Testing Course Schedule:");
        System.out.println("numCourses: " + numCourses1);
        System.out.println("prerequisites: " + java.util.Arrays.deepToString(prerequisites1));
        
        boolean result1 = canFinish_dfs(numCourses1, prerequisites1);
        System.out.println("DFS result: " + result1);
        
        boolean result2 = canFinish_bfs(numCourses1, prerequisites1);
        System.out.println("BFS result: " + result2);
        
        boolean result3 = canFinish_dfsPath(numCourses1, prerequisites1);
        System.out.println("DFS path result: " + result3);
        
        int[] order1 = findOrder(numCourses1, prerequisites1);
        System.out.println("Topological order: " + java.util.Arrays.toString(order1));
        
        // Expected: true, [0, 1]
        
        System.out.println();
        
        // Test case: numCourses = 2, prerequisites = [[1,0],[0,1]]
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        
        System.out.println("numCourses: " + numCourses2);
        System.out.println("prerequisites: " + java.util.Arrays.deepToString(prerequisites2));
        
        boolean result4 = canFinish_dfs(numCourses2, prerequisites2);
        System.out.println("DFS result: " + result4);
        
        boolean result5 = canFinish_bfs(numCourses2, prerequisites2);
        System.out.println("BFS result: " + result5);
        
        int[] order2 = findOrder(numCourses2, prerequisites2);
        System.out.println("Topological order: " + java.util.Arrays.toString(order2));
        
        // Expected: false, []
        
        System.out.println();
        
        // Test case: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        
        System.out.println("numCourses: " + numCourses3);
        System.out.println("prerequisites: " + java.util.Arrays.deepToString(prerequisites3));
        
        boolean result6 = canFinish_dfs(numCourses3, prerequisites3);
        System.out.println("DFS result: " + result6);
        
        int[] order3 = findOrder(numCourses3, prerequisites3);
        System.out.println("Topological order: " + java.util.Arrays.toString(order3));
        
        // Expected: true, [0, 1, 2, 3] or [0, 2, 1, 3]
        
        System.out.println();
        
        // Test helper methods
        java.util.List<java.util.List<Integer>> graph = buildGraph(numCourses3, prerequisites3);
        System.out.println("Graph: " + graph);
        
        int[] inDegrees = findInDegrees(numCourses3, prerequisites3);
        System.out.println("In-degrees: " + java.util.Arrays.toString(inDegrees));
        
        boolean isAcyclic = isAcyclic(graph);
        System.out.println("Is acyclic: " + isAcyclic);
        
        java.util.List<java.util.List<Integer>> sccs = findSCCs(graph);
        System.out.println("Strongly connected components: " + sccs);
        
        // Expected: Graph = [[1, 2], [3], [3], []], In-degrees = [0, 1, 1, 2], Is acyclic = true
    }
}

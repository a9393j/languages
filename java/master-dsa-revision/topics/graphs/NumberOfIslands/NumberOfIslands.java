/**
 * Number of Islands
 * 
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), 
 * return the number of islands. An island is surrounded by water and is formed by 
 * connecting adjacent lands horizontally or vertically.
 * 
 * LeetCode: https://leetcode.com/problems/number-of-islands/
 * Difficulty: Medium
 * Tags: dfs, bfs, grid
 * 
 * Approach:
 * - Use DFS or BFS to explore connected components
 * - Mark visited cells to avoid revisiting
 * - Count number of connected components
 * 
 * Pseudocode:
 * 1. Iterate through grid
 * 2. If cell is land and not visited:
 *    a. Increment island count
 *    b. Use DFS/BFS to mark all connected land
 * 3. Return island count
 */

import java.util.*;

public class NumberOfIslands {
    
    /**
     * DFS approach - O(m*n) time, O(m*n) space
     * Most efficient approach for this problem
     */
    public static int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int islandCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        
        return islandCount;
    }
    
    private static void dfs(char[][] grid, int i, int j, int m, int n) {
        // Check bounds and if cell is land
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
            return;
        }
        
        // Mark cell as visited
        grid[i][j] = '0';
        
        // Explore all four directions
        dfs(grid, i + 1, j, m, n);
        dfs(grid, i - 1, j, m, n);
        dfs(grid, i, j + 1, m, n);
        dfs(grid, i, j - 1, m, n);
    }
    
    /**
     * BFS approach - O(m*n) time, O(m*n) space
     * Alternative approach using BFS
     */
    public static int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int islandCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    bfs(grid, i, j, m, n);
                }
            }
        }
        
        return islandCount;
    }
    
    private static void bfs(char[][] grid, int i, int j, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '0';
        
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            
            for (int[] dir : directions) {
                int newI = current[0] + dir[0];
                int newJ = current[1] + dir[1];
                
                if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && grid[newI][newJ] == '1') {
                    grid[newI][newJ] = '0';
                    queue.offer(new int[]{newI, newJ});
                }
            }
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        
        System.out.println("Testing Number of Islands:");
        System.out.println("Grid:");
        for (char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        
        int result1 = numIslands_dfs(grid);
        System.out.println("DFS result: " + result1);
        
        // Reset grid for BFS test
        char[][] grid2 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        
        int result2 = numIslands_bfs(grid2);
        System.out.println("BFS result: " + result2);
    }
}
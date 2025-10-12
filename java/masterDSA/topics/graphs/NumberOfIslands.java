package masterDSA.topics.graphs;
import java.util.*;


/**
 * Number of Islands
 * 
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), 
 * return the number of islands.
 * 
 * LeetCode: https://leetcode.com/problems/number-of-islands/
 * Difficulty: Medium
 * Tags: dfs, bfs, union-find
 * 
 * Approach:
 * - DFS: For each land cell, mark all connected land cells as visited
 * - BFS: Use queue to explore connected land cells
 * - Union-Find: Use disjoint set to count connected components
 * 
 * Pseudocode:
 * DFS:
 * 1. For each cell in grid:
 *    a. If cell is land and not visited, increment count
 *    b. DFS to mark all connected land cells as visited
 * 2. Return count
 */
public class NumberOfIslands {
    
    /**
     * DFS approach - O(mn) time, O(mn) space
     * Use depth-first search to mark connected land cells
     */
    public static int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private static void dfs(char[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] != '1') {
            return;
        }
        
        grid[row][col] = '0'; // Mark as visited
        
        // Explore all four directions
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }
    
    /**
     * BFS approach - O(mn) time, O(min(m,n)) space
     * Use breadth-first search with queue
     */
    public static int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private static void bfs(char[][] grid, int startRow, int startCol) {
        int m = grid.length;
        int n = grid[0].length;
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        
        queue.offer(new int[]{startRow, startCol});
        grid[startRow][startCol] = '0'; // Mark as visited
        
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == '1') {
                    grid[newRow][newCol] = '0';
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
    
    /**
     * Union-Find approach - O(mn) time, O(mn) space
     * Use disjoint set to count connected components
     */
    public static int numIslands_unionFind(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                }
            }
        }
        
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int[] dir : directions) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        
                        if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == '1') {
                            int current = i * n + j;
                            int neighbor = newRow * n + newCol;
                            
                            if (uf.find(current) != uf.find(neighbor)) {
                                uf.union(current, neighbor);
                                count--;
                            }
                        }
                    }
                }
            }
        }
        
        return count;
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
     * Helper method to print grid
     */
    public static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }
    
    /**
     * Helper method to find largest island
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfsArea(grid, i, j));
                }
            }
        }
        
        return maxArea;
    }
    
    private static int dfsArea(int[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] != 1) {
            return 0;
        }
        
        grid[row][col] = 0; // Mark as visited
        
        return 1 + dfsArea(grid, row + 1, col) + dfsArea(grid, row - 1, col) +
                   dfsArea(grid, row, col + 1) + dfsArea(grid, row, col - 1);
    }
    
    /**
     * Helper method to find perimeter of islands
     */
    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int perimeter = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;
                    
                    if (i > 0 && grid[i - 1][j] == 1) perimeter -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) perimeter -= 2;
                }
            }
        }
        
        return perimeter;
    }
    
    /**
     * Helper method to find number of closed islands
     */
    public static int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        // Mark border islands as visited
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) dfsClosed(grid, i, 0);
            if (grid[i][n - 1] == 0) dfsClosed(grid, i, n - 1);
        }
        
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0) dfsClosed(grid, 0, j);
            if (grid[m - 1][j] == 0) dfsClosed(grid, m - 1, j);
        }
        
        // Count remaining islands
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    dfsClosed(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private static void dfsClosed(int[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] != 0) {
            return;
        }
        
        grid[row][col] = 1; // Mark as visited
        
        dfsClosed(grid, row + 1, col);
        dfsClosed(grid, row - 1, col);
        dfsClosed(grid, row, col + 1);
        dfsClosed(grid, row, col - 1);
    }
    
    public static void main(String[] args) {
        // Test case: grid = [["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        
        System.out.println("Testing Number of Islands:");
        System.out.println("Grid:");
        printGrid(grid1);
        
        int result1 = numIslands_dfs(grid1);
        System.out.println("DFS result: " + result1);
        
        // Expected: 1
        
        System.out.println();
        
        // Test case: grid = [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        
        System.out.println("Grid:");
        printGrid(grid2);
        
        int result2 = numIslands_dfs(grid2);
        System.out.println("DFS result: " + result2);
        
        // Expected: 3
        
        System.out.println();
        
        // Test BFS approach
        char[][] grid3 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        
        int result3 = numIslands_bfs(grid3);
        System.out.println("BFS result: " + result3);
        
        // Expected: 1
        
        System.out.println();
        
        // Test Union-Find approach
        char[][] grid4 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        
        int result4 = numIslands_unionFind(grid4);
        System.out.println("Union-Find result: " + result4);
        
        // Expected: 1
        
        System.out.println();
        
        // Test helper methods
        int[][] grid5 = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        
        int maxArea = maxAreaOfIsland(grid5);
        System.out.println("Max area of island: " + maxArea);
        
        int[][] grid6 = {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 0, 0}
        };
        
        int perimeter = islandPerimeter(grid6);
        System.out.println("Island perimeter: " + perimeter);
        
        // Expected: Max area = 6, Perimeter = 16
    }
}

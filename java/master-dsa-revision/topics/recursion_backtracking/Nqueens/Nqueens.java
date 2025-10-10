/**
 * N-Queens
 * 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that 
 * no two queens attack each other. Given an integer n, return all distinct solutions 
 * to the n-queens puzzle. You may return the answer in any order.
 * 
 * LeetCode: https://leetcode.com/problems/n-queens/
 * Difficulty: Hard
 * Tags: backtracking
 * 
 * Approach:
 * - Use backtracking to place queens row by row
 * - Check if queen can be placed at current position
 * - Use arrays to track occupied columns and diagonals
 * 
 * Pseudocode:
 * 1. Use backtracking to place queens
 * 2. For each row, try placing queen in each column
 * 3. Check if position is safe (no conflicts)
 * 4. If safe, place queen and recurse
 * 5. Backtrack by removing queen
 */

import java.util.*;

public class NQueens {
    
    /**
     * Backtracking approach - O(n!) time, O(n^2) space
     * Most efficient approach for this problem
     */
    public static List<List<String>> solveNQueens_backtracking(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        solveNQueensHelper(board, 0, result);
        return result;
    }
    
    private static void solveNQueensHelper(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            // All queens placed successfully
            List<String> solution = new ArrayList<>();
            for (char[] rowArray : board) {
                solution.add(new String(rowArray));
            }
            result.add(solution);
            return;
        }
        
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                solveNQueensHelper(board, row + 1, result);
                board[row][col] = '.'; // Backtrack
            }
        }
    }
    
    private static boolean isSafe(char[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // Check diagonal (top-left to bottom-right)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // Check diagonal (top-right to bottom-left)
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Optimized backtracking approach - O(n!) time, O(n) space
     * Uses arrays to track occupied columns and diagonals
     */
    public static List<List<String>> solveNQueens_optimized(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // Top-left to bottom-right
        boolean[] diag2 = new boolean[2 * n - 1]; // Top-right to bottom-left
        
        solveNQueensOptimizedHelper(board, 0, cols, diag1, diag2, result);
        return result;
    }
    
    private static void solveNQueensOptimizedHelper(char[][] board, int row, boolean[] cols, 
                                                   boolean[] diag1, boolean[] diag2, List<List<String>> result) {
        if (row == board.length) {
            List<String> solution = new ArrayList<>();
            for (char[] rowArray : board) {
                solution.add(new String(rowArray));
            }
            result.add(solution);
            return;
        }
        
        for (int col = 0; col < board.length; col++) {
            int d1 = row - col + board.length - 1;
            int d2 = row + col;
            
            if (!cols[col] && !diag1[d1] && !diag2[d2]) {
                board[row][col] = 'Q';
                cols[col] = true;
                diag1[d1] = true;
                diag2[d2] = true;
                
                solveNQueensOptimizedHelper(board, row + 1, cols, diag1, diag2, result);
                
                board[row][col] = '.';
                cols[col] = false;
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int n = 4;
        
        System.out.println("Testing N-Queens:");
        System.out.println("n: " + n);
        
        List<List<String>> result1 = solveNQueens_backtracking(n);
        System.out.println("Backtracking result:");
        for (List<String> solution : result1) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
        
        List<List<String>> result2 = solveNQueens_optimized(n);
        System.out.println("Optimized result:");
        for (List<String> solution : result2) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
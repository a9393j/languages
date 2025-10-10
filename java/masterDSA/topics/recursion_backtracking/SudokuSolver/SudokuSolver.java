/**
 * Sudoku Solver
 * 
 * Write a program to solve a Sudoku puzzle by filling the empty cells. A sudoku solution 
 * must satisfy all of the following rules:
 * 1. Each of the digits 1-9 must occur exactly once in each row.
 * 2. Each of the digits 1-9 must occur exactly once in each column.
 * 3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * 
 * LeetCode: https://leetcode.com/problems/sudoku-solver/
 * Difficulty: Hard
 * Tags: backtracking, dfs
 * 
 * Approach:
 * - Use backtracking to solve Sudoku
 * - For each empty cell, try digits 1-9
 * - Check if digit is valid (no conflicts)
 * - If valid, place digit and recurse
 * - Backtrack if no solution found
 * 
 * Pseudocode:
 * 1. Find empty cell
 * 2. Try digits 1-9
 * 3. Check if digit is valid
 * 4. If valid, place digit and recurse
 * 5. If solution found, return true
 * 6. Otherwise, backtrack and try next digit
 */

package masterDSA.topics.recursion_backtracking.SudokuSolver.SudokuSolver;

import java.util.*;

public class SudokuSolver {
    
    /**
     * Backtracking approach - O(9^(n*n)) time, O(n*n) space
     * Most efficient approach for this problem
     */
    public static void solveSudoku_backtracking(char[][] board) {
        solveSudokuHelper(board);
    }
    
    private static boolean solveSudokuHelper(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveSudokuHelper(board)) {
                                return true;
                            }
                            board[i][j] = '.'; // Backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean isValid(char[][] board, int row, int col, char c) {
        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == c) {
                return false;
            }
        }
        
        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        
        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Optimized backtracking approach - O(9^(n*n)) time, O(n*n) space
     * Uses arrays to track occupied cells
     */
    public static void solveSudoku_optimized(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        
        // Initialize tracking arrays
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int boxIndex = (i / 3) * 3 + j / 3;
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[boxIndex][num] = true;
                }
            }
        }
        
        solveSudokuOptimizedHelper(board, rows, cols, boxes);
    }
    
    private static boolean solveSudokuOptimizedHelper(char[][] board, boolean[][] rows, 
                                                     boolean[][] cols, boolean[][] boxes) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        int num = c - '1';
                        int boxIndex = (i / 3) * 3 + j / 3;
                        
                        if (!rows[i][num] && !cols[j][num] && !boxes[boxIndex][num]) {
                            board[i][j] = c;
                            rows[i][num] = true;
                            cols[j][num] = true;
                            boxes[boxIndex][num] = true;
                            
                            if (solveSudokuOptimizedHelper(board, rows, cols, boxes)) {
                                return true;
                            }
                            
                            board[i][j] = '.';
                            rows[i][num] = false;
                            cols[j][num] = false;
                            boxes[boxIndex][num] = false;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        
        System.out.println("Testing Sudoku Solver:");
        System.out.println("Original board:");
        printBoard(board);
        
        solveSudoku_backtracking(board);
        System.out.println("Solved board:");
        printBoard(board);
    }
    
    private static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
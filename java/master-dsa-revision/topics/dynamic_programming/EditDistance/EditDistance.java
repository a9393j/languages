/**
 * Edit Distance
 * 
 * Given two strings word1 and word2, return the minimum number of operations required 
 * to convert word1 to word2. You have the following three operations permitted on a word:
 * - Insert a character
 * - Delete a character
 * - Replace a character
 * 
 * LeetCode: https://leetcode.com/problems/edit-distance/
 * Difficulty: Hard
 * Tags: dp, string-dp
 * 
 * Approach:
 * - Use dynamic programming to find minimum operations
 * - dp[i][j] represents minimum operations to convert word1[0:i] to word2[0:j]
 * - Consider three operations: insert, delete, replace
 * 
 * Pseudocode:
 * 1. Initialize dp table with base cases
 * 2. For each character in word1 and word2:
 *    a. If characters match, dp[i][j] = dp[i-1][j-1]
 *    b. Otherwise, dp[i][j] = 1 + min(insert, delete, replace)
 * 3. Return dp[m][n]
 */

import java.util.*;

public class EditDistance {
    
    /**
     * Bottom-up DP approach - O(m*n) time, O(m*n) space
     * Most efficient approach for this problem
     */
    public static int minDistance_bottomUp(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Delete all characters from word1
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Insert all characters to word1
        }
        
        // Fill dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],     // Delete
                        Math.min(
                            dp[i][j - 1], // Insert
                            dp[i - 1][j - 1] // Replace
                        )
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Top-down DP approach - O(m*n) time, O(m*n) space
     * Alternative approach using memoization
     */
    public static int minDistance_topDown(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] memo = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return minDistanceHelper(word1, word2, m, n, memo);
    }
    
    private static int minDistanceHelper(String word1, String word2, int i, int j, int[][] memo) {
        if (i == 0) return j;
        if (j == 0) return i;
        
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            memo[i][j] = minDistanceHelper(word1, word2, i - 1, j - 1, memo);
        } else {
            memo[i][j] = 1 + Math.min(
                minDistanceHelper(word1, word2, i - 1, j, memo),     // Delete
                Math.min(
                    minDistanceHelper(word1, word2, i, j - 1, memo), // Insert
                    minDistanceHelper(word1, word2, i - 1, j - 1, memo) // Replace
                )
            );
        }
        
        return memo[i][j];
    }
    
    /**
     * Space-optimized DP approach - O(m*n) time, O(min(m,n)) space
     * Optimized space usage
     */
    public static int minDistance_spaceOptimized(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // Use shorter string for space optimization
        if (m < n) {
            return minDistance_spaceOptimized(word2, word1);
        }
        
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        
        // Base case
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }
        
        // Fill dp table
        for (int i = 1; i <= m; i++) {
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(prev[j], Math.min(curr[j - 1], prev[j - 1]));
                }
            }
            System.arraycopy(curr, 0, prev, 0, n + 1);
        }
        
        return prev[n];
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        
        System.out.println("Testing Edit Distance:");
        System.out.println("Word1: " + word1 + ", Word2: " + word2);
        
        int result1 = minDistance_bottomUp(word1, word2);
        System.out.println("Bottom-up result: " + result1);
        
        int result2 = minDistance_topDown(word1, word2);
        System.out.println("Top-down result: " + result2);
        
        int result3 = minDistance_spaceOptimized(word1, word2);
        System.out.println("Space-optimized result: " + result3);
    }
}
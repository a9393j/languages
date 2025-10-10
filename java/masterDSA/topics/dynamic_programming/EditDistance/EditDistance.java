package masterDSA.topics.dynamic_programming.EditDistance;
import java.util.*;


/**
 * Edit Distance
 * 
 * Given two strings word1 and word2, return the minimum number of operations required 
 * to convert word1 to word2.
 * 
 * LeetCode: https://leetcode.com/problems/edit-distance/
 * Difficulty: Hard
 * Tags: dynamic-programming, string
 * 
 * Approach:
 * - 2D DP: O(mn) time, O(mn) space
 * - Space optimized DP: O(mn) time, O(min(m,n)) space
 * - Recursive with memoization: O(mn) time, O(mn) space
 * 
 * Pseudocode:
 * 2D DP:
 * 1. Create dp[m+1][n+1] table
 * 2. Initialize base cases: dp[i][0] = i, dp[0][j] = j
 * 3. For each i, j:
 *    a. If word1[i-1] == word2[j-1]: dp[i][j] = dp[i-1][j-1]
 *    b. Else: dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
 * 4. Return dp[m][n]
 */
public class EditDistance {
    
    /**
     * 2D DP approach - O(mn) time, O(mn) space
     * Use 2D table to find edit distance
     */
    public static int minDistance_2d(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i - 1][j], dp[i][j - 1]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Space optimized DP approach - O(mn) time, O(min(m,n)) space
     * Use only two rows for space optimization
     */
    public static int minDistance_spaceOptimized(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        // Make word1 the shorter string
        if (word1.length() > word2.length()) {
            String temp = word1;
            word1 = word2;
            word2 = temp;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];
        
        // Initialize first row
        for (int i = 0; i <= m; i++) {
            prev[i] = i;
        }
        
        for (int j = 1; j <= n; j++) {
            curr[0] = j;
            
            for (int i = 1; i <= m; i++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[i] = prev[i - 1];
                } else {
                    curr[i] = 1 + Math.min(
                        Math.min(prev[i], curr[i - 1]),
                        prev[i - 1]
                    );
                }
            }
            
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[m];
    }
    
    /**
     * Recursive with memoization approach - O(mn) time, O(mn) space
     * Use recursion with memoization
     */
    public static int minDistance_memo(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[][] memo = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }
        
        return editDistanceHelper(word1, word2, m, n, memo);
    }
    
    private static int editDistanceHelper(String word1, String word2, int i, int j, int[][] memo) {
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }
        
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            memo[i][j] = editDistanceHelper(word1, word2, i - 1, j - 1, memo);
        } else {
            memo[i][j] = 1 + Math.min(
                Math.min(
                    editDistanceHelper(word1, word2, i - 1, j, memo),
                    editDistanceHelper(word1, word2, i, j - 1, memo)
                ),
                editDistanceHelper(word1, word2, i - 1, j - 1, memo)
            );
        }
        
        return memo[i][j];
    }
    
    /**
     * Helper method to find the actual edit operations
     */
    public static java.util.List<String> findEditOperations(String word1, String word2) {
        java.util.List<String> operations = new java.util.ArrayList<>();
        
        if (word1 == null || word2 == null) {
            return operations;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i - 1][j], dp[i][j - 1]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }
        
        // Reconstruct operations
        int i = m, j = n;
        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && word1.charAt(i - 1) == word2.charAt(j - 1)) {
                i--;
                j--;
            } else if (i > 0 && j > 0 && dp[i][j] == dp[i - 1][j - 1] + 1) {
                operations.add(0, "Replace '" + word1.charAt(i - 1) + "' with '" + word2.charAt(j - 1) + "'");
                i--;
                j--;
            } else if (i > 0 && dp[i][j] == dp[i - 1][j] + 1) {
                operations.add(0, "Delete '" + word1.charAt(i - 1) + "'");
                i--;
            } else if (j > 0 && dp[i][j] == dp[i][j - 1] + 1) {
                operations.add(0, "Insert '" + word2.charAt(j - 1) + "'");
                j--;
            }
        }
        
        return operations;
    }
    
    /**
     * Helper method to find longest common subsequence
     */
    public static int longestCommonSubsequence(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0) {
            return 0;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Helper method to find longest common substring
     */
    public static int longestCommonSubstring(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0) {
            return 0;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
        return maxLen;
    }
    
    /**
     * Helper method to find minimum deletions to make strings equal
     */
    public static int minDeletions(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        int lcs = longestCommonSubsequence(word1, word2);
        return word1.length() + word2.length() - 2 * lcs;
    }
    
    /**
     * Helper method to find minimum insertions to make strings equal
     */
    public static int minInsertions(String word1, String word2) {
        return minDeletions(word1, word2);
    }
    
    /**
     * Helper method to find minimum operations to make strings equal
     */
    public static int minOperations(String word1, String word2) {
        return minDistance_2d(word1, word2);
    }
    
    /**
     * Helper method to check if strings are one edit away
     */
    public static boolean isOneEditAway(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }
        
        int m = word1.length();
        int n = word2.length();
        
        if (Math.abs(m - n) > 1) {
            return false;
        }
        
        int i = 0, j = 0;
        int edits = 0;
        
        while (i < m && j < n) {
            if (word1.charAt(i) == word2.charAt(j)) {
                i++;
                j++;
            } else {
                edits++;
                if (edits > 1) {
                    return false;
                }
                
                if (m > n) {
                    i++;
                } else if (n > m) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to find minimum operations with different costs
     */
    public static int minDistanceWithCosts(String word1, String word2, int insertCost, int deleteCost, int replaceCost) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i * deleteCost;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j * insertCost;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                        Math.min(
                            dp[i - 1][j] + deleteCost,
                            dp[i][j - 1] + insertCost
                        ),
                        dp[i - 1][j - 1] + replaceCost
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        // Test case: word1 = "horse", word2 = "ros"
        String word1 = "horse";
        String word2 = "ros";
        
        System.out.println("Testing Edit Distance:");
        System.out.println("Word1: " + word1);
        System.out.println("Word2: " + word2);
        
        int result1 = minDistance_2d(word1, word2);
        System.out.println("2D DP result: " + result1);
        
        int result2 = minDistance_spaceOptimized(word1, word2);
        System.out.println("Space optimized result: " + result2);
        
        int result3 = minDistance_memo(word1, word2);
        System.out.println("Memoization result: " + result3);
        
        // Expected: 3
        
        System.out.println();
        
        // Test case: word1 = "intention", word2 = "execution"
        String word3 = "intention";
        String word4 = "execution";
        
        System.out.println("Word1: " + word3);
        System.out.println("Word2: " + word4);
        
        int result4 = minDistance_2d(word3, word4);
        System.out.println("2D DP result: " + result4);
        
        // Expected: 5
        
        System.out.println();
        
        // Test case: word1 = "", word2 = ""
        String word5 = "";
        String word6 = "";
        
        System.out.println("Word1: " + word5);
        System.out.println("Word2: " + word6);
        
        int result5 = minDistance_2d(word5, word6);
        System.out.println("2D DP result: " + result5);
        
        // Expected: 0
        
        System.out.println();
        
        // Test helper methods
        java.util.List<String> operations = findEditOperations(word1, word2);
        System.out.println("Edit operations: " + operations);
        
        int lcs = longestCommonSubsequence(word1, word2);
        System.out.println("Longest common subsequence: " + lcs);
        
        int lcSubstring = longestCommonSubstring(word1, word2);
        System.out.println("Longest common substring: " + lcSubstring);
        
        int minDel = minDeletions(word1, word2);
        System.out.println("Minimum deletions: " + minDel);
        
        int minIns = minInsertions(word1, word2);
        System.out.println("Minimum insertions: " + minIns);
        
        int minOps = minOperations(word1, word2);
        System.out.println("Minimum operations: " + minOps);
        
        boolean oneEdit = isOneEditAway("pale", "ple");
        System.out.println("Is one edit away: " + oneEdit);
        
        int withCosts = minDistanceWithCosts(word1, word2, 1, 1, 2);
        System.out.println("Edit distance with costs: " + withCosts);
        
        // Expected: Operations = ["Replace 'h' with 'r'", "Replace 'o' with 'o'", "Delete 's'", "Delete 'e'"], LCS = 2, LC substring = 1, Min del = 3, Min ins = 3, Min ops = 3, One edit = true, With costs = 4
    }
}

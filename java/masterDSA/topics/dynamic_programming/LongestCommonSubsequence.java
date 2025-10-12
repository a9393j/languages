package masterDSA.topics.dynamic_programming;
import java.util.*;


/**
 * Longest Common Subsequence
 * 
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * 
 * LeetCode: https://leetcode.com/problems/longest-common-subsequence/
 * Difficulty: Medium
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
 * 2. For each i, j:
 *    a. If text1[i-1] == text2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
 *    b. Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * 3. Return dp[m][n]
 */
public class LongestCommonSubsequence {
    
    /**
     * 2D DP approach - O(mn) time, O(mn) space
     * Use 2D table to find LCS
     */
    public static int longestCommonSubsequence_2d(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Space optimized DP approach - O(mn) time, O(min(m,n)) space
     * Use only two rows for space optimization
     */
    public static int longestCommonSubsequence_spaceOptimized(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        
        // Make text1 the shorter string
        if (text1.length() > text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        
        int m = text1.length();
        int n = text2.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];
        
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[i] = prev[i - 1] + 1;
                } else {
                    curr[i] = Math.max(prev[i], curr[i - 1]);
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
    public static int longestCommonSubsequence_memo(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        
        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return lcsHelper(text1, text2, 0, 0, memo);
    }
    
    private static int lcsHelper(String text1, String text2, int i, int j, int[][] memo) {
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }
        
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + lcsHelper(text1, text2, i + 1, j + 1, memo);
        } else {
            memo[i][j] = Math.max(
                lcsHelper(text1, text2, i + 1, j, memo),
                lcsHelper(text1, text2, i, j + 1, memo)
            );
        }
        
        return memo[i][j];
    }
    
    /**
     * Helper method to find the actual LCS string
     */
    public static String findLCS(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return "";
        }
        
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Reconstruct LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.insert(0, text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return lcs.toString();
    }
    
    /**
     * Helper method to find all LCS strings
     */
    public static Set<String> findAllLCS(String text1, String text2) {
        Set<String> result = new HashSet<>();
        
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return result;
        }
        
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        findAllLCSHelper(text1, text2, m, n, dp, "", result);
        return result;
    }
    
    private static void findAllLCSHelper(String text1, String text2, int i, int j, int[][] dp, String current, Set<String> result) {
        if (i == 0 || j == 0) {
            if (!current.isEmpty()) {
                result.add(current);
            }
            return;
        }
        
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            findAllLCSHelper(text1, text2, i - 1, j - 1, dp, text1.charAt(i - 1) + current, result);
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            findAllLCSHelper(text1, text2, i - 1, j, dp, current, result);
        } else if (dp[i][j - 1] > dp[i - 1][j]) {
            findAllLCSHelper(text1, text2, i, j - 1, dp, current, result);
        } else {
            findAllLCSHelper(text1, text2, i - 1, j, dp, current, result);
            findAllLCSHelper(text1, text2, i, j - 1, dp, current, result);
        }
    }
    
    /**
     * Helper method to find longest common substring
     */
    public static int longestCommonSubstring(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
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
     * Helper method to find longest palindromic subsequence
     */
    public static int longestPalindromicSubsequence(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // Single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        // Fill for lengths 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
    
    /**
     * Helper method to find shortest common supersequence
     */
    public static int shortestCommonSupersequence(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        
        int lcs = longestCommonSubsequence_2d(text1, text2);
        return text1.length() + text2.length() - lcs;
    }
    
    /**
     * Helper method to find edit distance
     */
    public static int editDistance(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        
        int m = text1.length();
        int n = text2.length();
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
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
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
    
    public static void main(String[] args) {
        // Test case: text1 = "abcde", text2 = "ace"
        String text1 = "abcde";
        String text2 = "ace";
        
        System.out.println("Testing Longest Common Subsequence:");
        System.out.println("Text1: " + text1);
        System.out.println("Text2: " + text2);
        
        int result1 = longestCommonSubsequence_2d(text1, text2);
        System.out.println("2D DP result: " + result1);
        
        int result2 = longestCommonSubsequence_spaceOptimized(text1, text2);
        System.out.println("Space optimized result: " + result2);
        
        int result3 = longestCommonSubsequence_memo(text1, text2);
        System.out.println("Memoization result: " + result3);
        
        // Expected: 3
        
        System.out.println();
        
        // Test case: text1 = "abc", text2 = "abc"
        String text3 = "abc";
        String text4 = "abc";
        
        System.out.println("Text1: " + text3);
        System.out.println("Text2: " + text4);
        
        int result4 = longestCommonSubsequence_2d(text3, text4);
        System.out.println("2D DP result: " + result4);
        
        // Expected: 3
        
        System.out.println();
        
        // Test case: text1 = "abc", text2 = "def"
        String text5 = "abc";
        String text6 = "def";
        
        System.out.println("Text1: " + text5);
        System.out.println("Text2: " + text6);
        
        int result5 = longestCommonSubsequence_2d(text5, text6);
        System.out.println("2D DP result: " + result5);
        
        // Expected: 0
        
        System.out.println();
        
        // Test helper methods
        String lcs = findLCS(text1, text2);
        System.out.println("LCS string: " + lcs);
        
        Set<String> allLCS = findAllLCS(text1, text2);
        System.out.println("All LCS strings: " + allLCS);
        
        int lcSubstring = longestCommonSubstring(text1, text2);
        System.out.println("Longest common substring: " + lcSubstring);
        
        int lps = longestPalindromicSubsequence("bbbab");
        System.out.println("Longest palindromic subsequence: " + lps);
        
        int scs = shortestCommonSupersequence(text1, text2);
        System.out.println("Shortest common supersequence: " + scs);
        
        int editDist = editDistance(text1, text2);
        System.out.println("Edit distance: " + editDist);
        
        // Expected: LCS string = "ace", All LCS = ["ace"], LC substring = 1, LPS = 4, SCS = 7, Edit distance = 2
    }
}

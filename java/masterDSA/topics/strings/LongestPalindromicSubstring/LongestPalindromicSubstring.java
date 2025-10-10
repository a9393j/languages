/**
 * Longest Palindromic Substring
 * 
 * Given a string s, return the longest palindromic substring in s.
 * 
 * LeetCode: https://leetcode.com/problems/longest-palindromic-substring/
 * Difficulty: Medium
 * Tags: expand-center, dp
 * 
 * Approach:
 * - Expand around center: for each possible center, expand outward
 * - Handle both odd-length (center at character) and even-length (center between characters) palindromes
 * - Keep track of longest palindrome found
 * 
 * Pseudocode:
 * 1. For each possible center in string:
 *    a. Expand around center for odd-length palindromes
 *    b. Expand around center for even-length palindromes
 *    c. Update longest palindrome if current is longer
 * 2. Return longest palindrome
 */

package masterDSA.topics.strings.LongestPalindromicSubstring.LongestPalindromicSubstring;

import java.util.*;

public class LongestPalindromicSubstring {
    
    /**
     * Expand around center approach - O(n^2) time, O(1) space
     * Most efficient approach for this problem
     */
    public static String longestPalindrome_expandCenter(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (center at character)
            int len1 = expandAroundCenter(s, i, i);
            
            // Check for even-length palindromes (center between characters)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Get maximum length
            int len = Math.max(len1, len2);
            
            // Update start and end if current palindrome is longer
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    /**
     * Dynamic programming approach - O(n^2) time, O(n^2) space
     * Alternative approach using DP table
     */
    public static String longestPalindrome_dp(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLength = 1;
        
        // Single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        
        // Check for palindromes of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
        
        // Check for palindromes of length 3 and more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (len > maxLength) {
                        start = i;
                        maxLength = len;
                    }
                }
            }
        }
        
        return s.substring(start, start + maxLength);
    }
    
    /**
     * Brute force approach - O(n^3) time, O(1) space
     * Less efficient but easy to understand
     */
    public static String longestPalindrome_bruteForce(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        
        String longest = "";
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                if (isPalindrome(substring) && substring.length() > longest.length()) {
                    longest = substring;
                }
            }
        }
        
        return longest;
    }
    
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        
        System.out.println("Testing Longest Palindromic Substring:");
        
        System.out.println("String: " + s1);
        System.out.println("Expand center result: " + longestPalindrome_expandCenter(s1));
        System.out.println("DP result: " + longestPalindrome_dp(s1));
        System.out.println("Brute force result: " + longestPalindrome_bruteForce(s1));
        
        System.out.println("String: " + s2);
        System.out.println("Expand center result: " + longestPalindrome_expandCenter(s2));
        
        System.out.println("String: " + s3);
        System.out.println("Expand center result: " + longestPalindrome_expandCenter(s3));
    }
}
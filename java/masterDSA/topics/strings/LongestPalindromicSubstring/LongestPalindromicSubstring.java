package masterDSA.topics.strings.LongestPalindromicSubstring;

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
 * - Expand around center: For each position, expand outward to find longest palindrome
 * - Dynamic programming: Build palindrome table bottom-up
 * - Manacher's algorithm: O(n) time complexity (advanced)
 * 
 * Pseudocode:
 * Expand Center:
 * 1. For each position i in string:
 *    a. Check odd-length palindromes (center at i)
 *    b. Check even-length palindromes (center between i and i+1)
 *    c. Update longest palindrome found
 * 2. Return longest palindrome
 */
public class LongestPalindromicSubstring {
    
    /**
     * Expand around center approach - O(n²) time, O(1) space
     * For each position, expand outward to find longest palindrome
     */
    public static String longestPalindrome_expandCenter(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLen = 1;
        
        for (int i = 0; i < s.length(); i++) {
            // Check odd-length palindromes (center at i)
            int len1 = expandAroundCenter(s, i, i);
            
            // Check even-length palindromes (center between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int currentMaxLen = Math.max(len1, len2);
            
            if (currentMaxLen > maxLen) {
                maxLen = currentMaxLen;
                start = i - (currentMaxLen - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Helper method to expand around center
     */
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    /**
     * Dynamic programming approach - O(n²) time, O(n²) space
     * Build palindrome table bottom-up
     */
    public static String longestPalindrome_dp(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLen = 1;
        
        // Single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        
        // Check for palindromes of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }
        
        // Check for palindromes of length 3 and more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLen = len;
                }
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Brute force approach - O(n³) time, O(1) space
     * Check all possible substrings
     */
    public static String longestPalindrome_bruteForce(String s) {
        if (s == null || s.length() < 2) {
            return s;
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
    
    /**
     * Helper method to check if string is palindrome
     */
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
     * Optimized expand center approach - O(n²) time, O(1) space
     * Skip unnecessary checks by using previous results
     */
    public static String longestPalindrome_optimized(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLen = 1;
        
        for (int i = 0; i < s.length();) {
            int left = i;
            int right = i;
            
            // Skip duplicate characters
            while (right < s.length() - 1 && s.charAt(right) == s.charAt(right + 1)) {
                right++;
            }
            
            i = right + 1;
            
            // Expand around center
            while (left > 0 && right < s.length() - 1 && s.charAt(left - 1) == s.charAt(right + 1)) {
                left--;
                right++;
            }
            
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
                start = left;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Manacher's algorithm - O(n) time, O(n) space
     * Advanced algorithm for finding longest palindrome
     */
    public static String longestPalindrome_manacher(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        // Preprocess string to handle even-length palindromes
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        String processed = sb.toString();
        
        int n = processed.length();
        int[] radius = new int[n];
        int center = 0;
        int right = 0;
        int maxLen = 0;
        int maxCenter = 0;
        
        for (int i = 0; i < n; i++) {
            if (i < right) {
                radius[i] = Math.min(right - i, radius[2 * center - i]);
            }
            
            // Expand around center
            while (i + radius[i] + 1 < n && i - radius[i] - 1 >= 0 &&
                   processed.charAt(i + radius[i] + 1) == processed.charAt(i - radius[i] - 1)) {
                radius[i]++;
            }
            
            // Update center and right boundary
            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
            
            // Update maximum palindrome
            if (radius[i] > maxLen) {
                maxLen = radius[i];
                maxCenter = i;
            }
        }
        
        // Extract original string
        int start = (maxCenter - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Helper method to count palindromes
     */
    public static int countPalindromes(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes
            count += expandAroundCenter(s, i, i);
            
            // Count even-length palindromes
            count += expandAroundCenter(s, i, i + 1);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        // Test case: s = "babad"
        String s1 = "babad";
        
        System.out.println("Testing Longest Palindromic Substring:");
        System.out.println("String: " + s1);
        
        String result1 = longestPalindrome_expandCenter(s1);
        System.out.println("Expand center result: " + result1);
        
        String result2 = longestPalindrome_dp(s1);
        System.out.println("DP result: " + result2);
        
        String result3 = longestPalindrome_bruteForce(s1);
        System.out.println("Brute force result: " + result3);
        
        String result4 = longestPalindrome_optimized(s1);
        System.out.println("Optimized result: " + result4);
        
        String result5 = longestPalindrome_manacher(s1);
        System.out.println("Manacher's result: " + result5);
        
        // Expected: "bab" or "aba"
        
        System.out.println();
        
        // Test case: s = "cbbd"
        String s2 = "cbbd";
        System.out.println("String: " + s2);
        
        String result6 = longestPalindrome_expandCenter(s2);
        System.out.println("Expand center result: " + result6);
        
        // Expected: "bb"
        
        System.out.println();
        
        // Test case: s = "a"
        String s3 = "a";
        System.out.println("String: " + s3);
        
        String result7 = longestPalindrome_expandCenter(s3);
        System.out.println("Expand center result: " + result7);
        
        // Expected: "a"
        
        System.out.println();
        
        // Count palindromes
        int count = countPalindromes(s1);
        System.out.println("Number of palindromes in '" + s1 + "': " + count);
    }
}

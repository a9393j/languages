/**
 * Word Break
 * 
 * Given a string s and a dictionary of strings wordDict, return true if s can be 
 * segmented into a space-separated sequence of one or more dictionary words.
 * 
 * LeetCode: https://leetcode.com/problems/word-break/
 * Difficulty: Medium
 * Tags: dp, trie
 * 
 * Approach:
 * - Dynamic programming: dp[i] represents if substring s[0:i] can be segmented
 * - For each position, check if any word from dictionary ends at that position
 * - Use Trie for efficient word lookup
 * 
 * Pseudocode:
 * 1. Create DP array where dp[i] = true if s[0:i] can be segmented
 * 2. For each position i:
 *    a. For each word in dictionary:
 *       b. If word ends at position i and dp[i-word.length] is true, set dp[i] = true
 * 3. Return dp[s.length()]
 */

package masterDSA.topics.strings.WordBreak.WordBreak;

import java.util.*;

public class WordBreak {
    
    /**
     * Dynamic programming approach - O(n^2) time, O(n) space
     * Most efficient approach for this problem
     */
    public static boolean wordBreak_dp(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Empty string can always be segmented
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // If dp[j] is true and substring s[j:i] is in wordDict
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check further
                }
            }
        }
        
        return dp[s.length()];
    }
    
    /**
     * Trie approach - O(n^2) time, O(n) space
     * Alternative approach using Trie for word lookup
     */
    public static boolean wordBreak_trie(String s, List<String> wordDict) {
        TrieNode root = buildTrie(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        
        for (int i = 0; i < s.length(); i++) {
            if (dp[i]) {
                TrieNode node = root;
                for (int j = i; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (node.children[c - 'a'] == null) {
                        break;
                    }
                    node = node.children[c - 'a'];
                    if (node.isWord) {
                        dp[j + 1] = true;
                    }
                }
            }
        }
        
        return dp[s.length()];
    }
    
    private static TrieNode buildTrie(List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }
        return root;
    }
    
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }
    
    /**
     * Recursive approach with memoization - O(n^2) time, O(n) space
     * Alternative approach using recursion
     */
    public static boolean wordBreak_recursive(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, Boolean> memo = new HashMap<>();
        return wordBreakHelper(s, wordSet, memo);
    }
    
    private static boolean wordBreakHelper(String s, Set<String> wordSet, Map<String, Boolean> memo) {
        if (s.isEmpty()) {
            return true;
        }
        
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordSet.contains(prefix) && wordBreakHelper(s.substring(i), wordSet, memo)) {
                memo.put(s, true);
                return true;
            }
        }
        
        memo.put(s, false);
        return false;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        
        System.out.println("Testing Word Break:");
        
        System.out.println("s: " + s1 + ", wordDict: " + wordDict1);
        System.out.println("DP result: " + wordBreak_dp(s1, wordDict1));
        System.out.println("Trie result: " + wordBreak_trie(s1, wordDict1));
        System.out.println("Recursive result: " + wordBreak_recursive(s1, wordDict1));
        
        System.out.println("s: " + s2 + ", wordDict: " + wordDict2);
        System.out.println("DP result: " + wordBreak_dp(s2, wordDict2));
        
        System.out.println("s: " + s3 + ", wordDict: " + wordDict3);
        System.out.println("DP result: " + wordBreak_dp(s3, wordDict3));
    }
}
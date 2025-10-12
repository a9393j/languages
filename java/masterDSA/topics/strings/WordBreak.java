package masterDSA.topics.strings;
import java.util.*;


/**
 * Word Break
 * 
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented 
 * into a space-separated sequence of one or more dictionary words. Note that the same word 
 * in the dictionary may be reused multiple times in the segmentation.
 * 
 * LeetCode: https://leetcode.com/problems/word-break/
 * Difficulty: Medium
 * Tags: dp, trie
 * 
 * Approach:
 * - Dynamic programming: dp[i] = true if s[0:i] can be segmented
 * - For each position, check if any word ending at that position is in dictionary
 * - Alternative: Use Trie for efficient word lookup
 * - Alternative: Use BFS to explore all possible segmentations
 * 
 * Pseudocode:
 * DP:
 * 1. Initialize dp[0] = true
 * 2. For i = 1 to n:
 *    a. For j = 0 to i-1:
 *       - If dp[j] = true and s[j:i] is in wordDict:
 *         - Set dp[i] = true and break
 * 3. Return dp[n]
 */
public class WordBreak {
    
    /**
     * Dynamic programming approach - O(n²) time, O(n) space
     * dp[i] = true if s[0:i] can be segmented into dictionary words
     */
    public static boolean wordBreak_dp(String s, java.util.List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Empty string can always be segmented
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // If s[0:j] can be segmented and s[j:i] is in dictionary
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check further
                }
            }
        }
        
        return dp[s.length()];
    }
    
    /**
     * Optimized DP approach - O(n²) time, O(n) space
     * Only check words that are shorter than current position
     */
    public static boolean wordBreak_dpOptimized(String s, java.util.List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                // Only check if word length is <= current position
                if (word.length() <= i && dp[i - word.length()] && 
                    wordSet.contains(s.substring(i - word.length(), i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
    
    /**
     * BFS approach - O(n²) time, O(n) space
     * Use BFS to explore all possible segmentations
     */
    public static boolean wordBreak_bfs(String s, java.util.List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordDict);
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        
        queue.offer(0);
        
        while (!queue.isEmpty()) {
            int start = queue.poll();
            
            if (visited[start]) {
                continue;
            }
            
            visited[start] = true;
            
            // Try all possible endings
            for (int end = start + 1; end <= s.length(); end++) {
                String word = s.substring(start, end);
                
                if (wordSet.contains(word)) {
                    if (end == s.length()) {
                        return true; // Found complete segmentation
                    }
                    queue.offer(end);
                }
            }
        }
        
        return false;
    }
    
    /**
     * DFS with memoization - O(n²) time, O(n) space
     * Use DFS to explore all possible segmentations with memoization
     */
    public static boolean wordBreak_dfs(String s, java.util.List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordDict);
        java.util.Map<Integer, Boolean> memo = new java.util.HashMap<>();
        
        return wordBreakHelper(s, 0, wordSet, memo);
    }
    
    private static boolean wordBreakHelper(String s, int start, java.util.Set<String> wordSet, 
                                         java.util.Map<Integer, Boolean> memo) {
        if (start == s.length()) {
            return true;
        }
        
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            
            if (wordSet.contains(word) && wordBreakHelper(s, end, wordSet, memo)) {
                memo.put(start, true);
                return true;
            }
        }
        
        memo.put(start, false);
        return false;
    }
    
    /**
     * Trie approach - O(n²) time, O(n) space
     * Use Trie for efficient word lookup
     */
    public static boolean wordBreak_trie(String s, java.util.List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        TrieNode root = buildTrie(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && searchInTrie(root, s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
    
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }
    
    private static TrieNode buildTrie(java.util.List<String> wordDict) {
        TrieNode root = new TrieNode();
        
        for (String word : wordDict) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
        }
        
        return root;
    }
    
    private static boolean searchInTrie(TrieNode root, String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }
    
    /**
     * Brute force approach - O(2^n) time, O(n) space
     * Check all possible segmentations
     */
    public static boolean wordBreak_bruteForce(String s, java.util.List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordDict);
        
        return wordBreakBruteForceHelper(s, 0, wordSet);
    }
    
    private static boolean wordBreakBruteForceHelper(String s, int start, java.util.Set<String> wordSet) {
        if (start == s.length()) {
            return true;
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            
            if (wordSet.contains(word) && wordBreakBruteForceHelper(s, end, wordSet)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Helper method to find all possible segmentations
     */
    public static java.util.List<String> wordBreakAll(String s, java.util.List<String> wordDict) {
        java.util.List<String> result = new java.util.ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordDict);
        wordBreakAllHelper(s, 0, wordSet, new java.util.ArrayList<>(), result);
        
        return result;
    }
    
    private static void wordBreakAllHelper(String s, int start, java.util.Set<String> wordSet,
                                         java.util.List<String> current, java.util.List<String> result) {
        if (start == s.length()) {
            result.add(String.join(" ", current));
            return;
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            
            if (wordSet.contains(word)) {
                current.add(word);
                wordBreakAllHelper(s, end, wordSet, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test case: s = "leetcode", wordDict = ["leet","code"]
        String s1 = "leetcode";
        java.util.List<String> wordDict1 = java.util.Arrays.asList("leet", "code");
        
        System.out.println("Testing Word Break:");
        System.out.println("String: " + s1);
        System.out.println("Word Dict: " + wordDict1);
        
        boolean result1 = wordBreak_dp(s1, wordDict1);
        System.out.println("DP result: " + result1);
        
        boolean result2 = wordBreak_dpOptimized(s1, wordDict1);
        System.out.println("DP optimized result: " + result2);
        
        boolean result3 = wordBreak_bfs(s1, wordDict1);
        System.out.println("BFS result: " + result3);
        
        boolean result4 = wordBreak_dfs(s1, wordDict1);
        System.out.println("DFS result: " + result4);
        
        boolean result5 = wordBreak_trie(s1, wordDict1);
        System.out.println("Trie result: " + result5);
        
        boolean result6 = wordBreak_bruteForce(s1, wordDict1);
        System.out.println("Brute force result: " + result6);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: s = "applepenapple", wordDict = ["apple","pen"]
        String s2 = "applepenapple";
        java.util.List<String> wordDict2 = java.util.Arrays.asList("apple", "pen");
        
        System.out.println("String: " + s2);
        System.out.println("Word Dict: " + wordDict2);
        
        boolean result7 = wordBreak_dp(s2, wordDict2);
        System.out.println("DP result: " + result7);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        String s3 = "catsandog";
        java.util.List<String> wordDict3 = java.util.Arrays.asList("cats", "dog", "sand", "and", "cat");
        
        System.out.println("String: " + s3);
        System.out.println("Word Dict: " + wordDict3);
        
        boolean result8 = wordBreak_dp(s3, wordDict3);
        System.out.println("DP result: " + result8);
        
        // Expected: false
        
        System.out.println();
        
        // Find all possible segmentations
        String s4 = "catsanddog";
        java.util.List<String> wordDict4 = java.util.Arrays.asList("cat", "cats", "and", "sand", "dog");
        
        System.out.println("String: " + s4);
        System.out.println("Word Dict: " + wordDict4);
        
        java.util.List<String> allSegmentations = wordBreakAll(s4, wordDict4);
        System.out.println("All possible segmentations: " + allSegmentations);
        
        // Expected: ["cat sand dog", "cats and dog"]
    }
}

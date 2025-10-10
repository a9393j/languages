/**
 * Word Search II
 * 
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent 
 * cells are horizontally or vertically neighboring. The same letter cell may not be used 
 * more than once in a word.
 * 
 * LeetCode: https://leetcode.com/problems/word-search-ii/
 * Difficulty: Hard
 * Tags: trie, backtracking
 * 
 * Approach:
 * - Use Trie to store all words
 * - Use DFS with backtracking to search words
 * - Mark visited cells to avoid revisiting
 * 
 * Pseudocode:
 * 1. Build Trie from all words
 * 2. For each cell, start DFS search
 * 3. Use backtracking to explore all paths
 * 4. Check if current path forms a valid word
 * 5. Add found words to result
 */

import java.util.*;

public class WordSearchII {
    
    /**
     * Trie + DFS approach - O(m*n*4^L) time, O(W*L) space
     * Most efficient approach for this problem
     */
    public static List<String> findWords_trie(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode();
                }
                current = current.children[c - 'a'];
            }
            current.word = word;
        }
        return root;
    }
    
    private static void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];
        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }
        
        node = node.children[c - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicates
        }
        
        board[i][j] = '#'; // Mark as visited
        
        // Explore all four directions
        if (i > 0) dfs(board, i - 1, j, node, result);
        if (j > 0) dfs(board, i, j - 1, node, result);
        if (i < board.length - 1) dfs(board, i + 1, j, node, result);
        if (j < board[0].length - 1) dfs(board, i, j + 1, node, result);
        
        board[i][j] = c; // Backtrack
    }
    
    private static class TrieNode {
        TrieNode[] children;
        String word;
        
        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }
    
    /**
     * Alternative approach using HashSet - O(m*n*4^L) time, O(W*L) space
     * Uses HashSet to store words
     */
    public static List<String> findWords_hashSet(char[][] board, String[] words) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        Set<String> result = new HashSet<>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsHashSet(board, i, j, "", wordSet, result, new boolean[board.length][board[0].length]);
            }
        }
        
        return new ArrayList<>(result);
    }
    
    private static void dfsHashSet(char[][] board, int i, int j, String current, 
                                   Set<String> wordSet, Set<String> result, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return;
        }
        
        current += board[i][j];
        if (wordSet.contains(current)) {
            result.add(current);
        }
        
        visited[i][j] = true;
        
        // Explore all four directions
        dfsHashSet(board, i - 1, j, current, wordSet, result, visited);
        dfsHashSet(board, i + 1, j, current, wordSet, result, visited);
        dfsHashSet(board, i, j - 1, current, wordSet, result, visited);
        dfsHashSet(board, i, j + 1, current, wordSet, result, visited);
        
        visited[i][j] = false; // Backtrack
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        char[][] board = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        
        System.out.println("Testing Word Search II:");
        System.out.println("Board:");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Words: " + Arrays.toString(words));
        
        List<String> result1 = findWords_trie(board, words);
        System.out.println("Trie result: " + result1);
        
        List<String> result2 = findWords_hashSet(board, words);
        System.out.println("HashSet result: " + result2);
    }
}
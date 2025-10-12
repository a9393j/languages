package masterDSA.topics.tries_hashing;
import java.util.*;


/**
 * Implement Trie (Prefix Tree)
 * 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to 
 * efficiently store and search strings in a dataset of strings.
 * 
 * LeetCode: https://leetcode.com/problems/implement-trie-prefix-tree/
 * Difficulty: Medium
 * Tags: trie, string
 * 
 * Approach:
 * - Trie Node: Each node contains children and end flag
 * - Insert: Traverse trie and add new nodes as needed
 * - Search: Traverse trie and check if word exists
 * - Prefix: Check if prefix exists in trie
 * 
 * Pseudocode:
 * Insert:
 * 1. Start from root
 * 2. For each character, get or create child node
 * 3. Mark last node as end of word
 * 
 * Search:
 * 1. Start from root
 * 2. For each character, move to child node
 * 3. If node doesn't exist, return false
 * 4. Return true if last node is end of word
 */
public class ImplementTriePrefixTree {
    
    /**
     * Trie Node class
     */
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        
        public TrieNode() {
            children = new TrieNode[26]; // For lowercase English letters
            isEndOfWord = false;
        }
    }
    
    /**
     * Trie class implementation
     */
    static class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        /**
         * Insert word into trie - O(m) time, O(m) space where m is word length
         */
        public void insert(String word) {
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
        
        /**
         * Search word in trie - O(m) time, O(1) space
         */
        public boolean search(String word) {
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
         * Check if prefix exists - O(m) time, O(1) space
         */
        public boolean startsWith(String prefix) {
            TrieNode current = root;
            
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    return false;
                }
                current = current.children[index];
            }
            
            return true;
        }
        
        /**
         * Delete word from trie - O(m) time, O(m) space
         */
        public boolean delete(String word) {
            return deleteHelper(root, word, 0);
        }
        
        private boolean deleteHelper(TrieNode node, String word, int index) {
            if (index == word.length()) {
                if (!node.isEndOfWord) {
                    return false;
                }
                node.isEndOfWord = false;
                return isEmpty(node);
            }
            
            char c = word.charAt(index);
            int charIndex = c - 'a';
            TrieNode child = node.children[charIndex];
            
            if (child == null) {
                return false;
            }
            
            boolean shouldDeleteChild = deleteHelper(child, word, index + 1);
            
            if (shouldDeleteChild) {
                node.children[charIndex] = null;
                return isEmpty(node);
            }
            
            return false;
        }
        
        private boolean isEmpty(TrieNode node) {
            for (TrieNode child : node.children) {
                if (child != null) {
                    return false;
                }
            }
            return true;
        }
        
        /**
         * Get all words with given prefix - O(n) time, O(n) space
         */
        public java.util.List<String> getWordsWithPrefix(String prefix) {
            java.util.List<String> result = new java.util.ArrayList<>();
            TrieNode current = root;
            
            // Navigate to prefix
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    return result;
                }
                current = current.children[index];
            }
            
            // Collect all words from this node
            collectWords(current, prefix, result);
            return result;
        }
        
        private void collectWords(TrieNode node, String currentWord, java.util.List<String> result) {
            if (node.isEndOfWord) {
                result.add(currentWord);
            }
            
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    char c = (char) ('a' + i);
                    collectWords(node.children[i], currentWord + c, result);
                }
            }
        }
        
        /**
         * Get longest common prefix - O(m) time, O(1) space
         */
        public String longestCommonPrefix() {
            StringBuilder prefix = new StringBuilder();
            TrieNode current = root;
            
            while (current != null) {
                int childCount = 0;
                int childIndex = -1;
                
                for (int i = 0; i < 26; i++) {
                    if (current.children[i] != null) {
                        childCount++;
                        childIndex = i;
                    }
                }
                
                if (childCount != 1 || current.isEndOfWord) {
                    break;
                }
                
                prefix.append((char) ('a' + childIndex));
                current = current.children[childIndex];
            }
            
            return prefix.toString();
        }
        
        /**
         * Count total words in trie - O(n) time, O(n) space
         */
        public int countWords() {
            return countWordsHelper(root);
        }
        
        private int countWordsHelper(TrieNode node) {
            int count = 0;
            
            if (node.isEndOfWord) {
                count++;
            }
            
            for (TrieNode child : node.children) {
                if (child != null) {
                    count += countWordsHelper(child);
                }
            }
            
            return count;
        }
        
        /**
         * Check if trie is empty - O(1) time, O(1) space
         */
        public boolean isEmpty() {
            return isEmpty(root);
        }
        
        /**
         * Clear all words from trie - O(1) time, O(1) space
         */
        public void clear() {
            root = new TrieNode();
        }
    }
    
    /**
     * Helper method to build trie from array of words
     */
    public static Trie buildTrie(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        return trie;
    }
    
    /**
     * Helper method to find all words that can be formed from given letters
     */
    public static java.util.List<String> findWordsWithLetters(String[] words, String letters) {
        Trie trie = buildTrie(words);
        java.util.List<String> result = new java.util.ArrayList<>();
        boolean[] used = new boolean[letters.length()];
        
        findWordsHelper(trie.root, "", letters, used, result);
        return result;
    }
    
    private static void findWordsHelper(TrieNode node, String currentWord, String letters, 
                                      boolean[] used, java.util.List<String> result) {
        if (node.isEndOfWord) {
            result.add(currentWord);
        }
        
        for (int i = 0; i < letters.length(); i++) {
            if (!used[i]) {
                char c = letters.charAt(i);
                int index = c - 'a';
                
                if (node.children[index] != null) {
                    used[i] = true;
                    findWordsHelper(node.children[index], currentWord + c, letters, used, result);
                    used[i] = false;
                }
            }
        }
    }
    
    /**
     * Helper method to find words with wildcard pattern
     */
    public static java.util.List<String> findWordsWithWildcard(Trie trie, String pattern) {
        java.util.List<String> result = new java.util.ArrayList<>();
        findWordsWithWildcardHelper(trie.root, "", pattern, 0, result);
        return result;
    }
    
    private static void findWordsWithWildcardHelper(TrieNode node, String currentWord, String pattern, 
                                                  int patternIndex, java.util.List<String> result) {
        if (patternIndex == pattern.length()) {
            if (node.isEndOfWord) {
                result.add(currentWord);
            }
            return;
        }
        
        char c = pattern.charAt(patternIndex);
        
        if (c == '*') {
            // Match any character
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    char charToAdd = (char) ('a' + i);
                    findWordsWithWildcardHelper(node.children[i], currentWord + charToAdd, 
                                              pattern, patternIndex + 1, result);
                }
            }
        } else {
            // Match specific character
            int index = c - 'a';
            if (node.children[index] != null) {
                findWordsWithWildcardHelper(node.children[index], currentWord + c, 
                                          pattern, patternIndex + 1, result);
            }
        }
    }
    
    /**
     * Helper method to find shortest word that contains all given characters
     */
    public static String findShortestWordWithChars(Trie trie, String chars) {
        java.util.Set<Character> charSet = new java.util.HashSet<>();
        for (char c : chars.toCharArray()) {
            charSet.add(c);
        }
        
        return findShortestWordHelper(trie.root, "", charSet);
    }
    
    private static String findShortestWordHelper(TrieNode node, String currentWord, java.util.Set<Character> charSet) {
        if (node.isEndOfWord) {
            java.util.Set<Character> wordChars = new java.util.HashSet<>();
            for (char c : currentWord.toCharArray()) {
                wordChars.add(c);
            }
            
            if (wordChars.containsAll(charSet)) {
                return currentWord;
            }
        }
        
        String shortest = null;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i);
                String result = findShortestWordHelper(node.children[i], currentWord + c, charSet);
                if (result != null && (shortest == null || result.length() < shortest.length())) {
                    shortest = result;
                }
            }
        }
        
        return shortest;
    }
    
    public static void main(String[] args) {
        // Test case: Insert "apple", search "apple", search "app", startsWith "app"
        System.out.println("Testing Implement Trie (Prefix Tree):");
        
        Trie trie = new Trie();
        
        trie.insert("apple");
        System.out.println("Inserted 'apple'");
        
        boolean search1 = trie.search("apple");
        System.out.println("Search 'apple': " + search1);
        
        boolean search2 = trie.search("app");
        System.out.println("Search 'app': " + search2);
        
        boolean startsWith1 = trie.startsWith("app");
        System.out.println("StartsWith 'app': " + startsWith1);
        
        trie.insert("app");
        System.out.println("Inserted 'app'");
        
        boolean search3 = trie.search("app");
        System.out.println("Search 'app': " + search3);
        
        // Expected: Search 'apple' = true, Search 'app' = false, StartsWith 'app' = true, Search 'app' = true
        
        System.out.println();
        
        // Test helper methods
        String[] words = {"apple", "app", "application", "apply", "apt"};
        Trie trie2 = buildTrie(words);
        
        java.util.List<String> wordsWithPrefix = trie2.getWordsWithPrefix("app");
        System.out.println("Words with prefix 'app': " + wordsWithPrefix);
        
        String longestPrefix = trie2.longestCommonPrefix();
        System.out.println("Longest common prefix: " + longestPrefix);
        
        int wordCount = trie2.countWords();
        System.out.println("Total words in trie: " + wordCount);
        
        java.util.List<String> wordsWithLetters = findWordsWithLetters(words, "ppl");
        System.out.println("Words with letters 'ppl': " + wordsWithLetters);
        
        java.util.List<String> wordsWithWildcard = findWordsWithWildcard(trie2, "app*");
        System.out.println("Words with wildcard 'app*': " + wordsWithWildcard);
        
        String shortestWord = findShortestWordWithChars(trie2, "pl");
        System.out.println("Shortest word with chars 'pl': " + shortestWord);
        
        // Test delete
        boolean deleted = trie2.delete("app");
        System.out.println("Deleted 'app': " + deleted);
        
        boolean searchAfterDelete = trie2.search("app");
        System.out.println("Search 'app' after delete: " + searchAfterDelete);
        
        // Expected: Words with prefix 'app' = [app, apple, application, apply], Longest common prefix = "app", Total words = 5, Words with letters 'ppl' = [apple], Words with wildcard 'app*' = [app, apple, application, apply], Shortest word with chars 'pl' = "apple", Deleted 'app' = true, Search 'app' after delete = false
    }
}

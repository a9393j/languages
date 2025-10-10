/**
 * Implement Trie (Prefix Tree)
 * 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently 
 * store and search strings in a dataset of strings. There are various applications of this 
 * data structure, such as autocomplete and spellchecker.
 * 
 * LeetCode: https://leetcode.com/problems/implement-trie-prefix-tree/
 * Difficulty: Medium
 * Tags: trie
 * 
 * Approach:
 * - Use TrieNode to represent each character
 * - Each node has children array and isEnd flag
 * - Implement insert, search, and startsWith operations
 * 
 * Pseudocode:
 * 1. Create TrieNode class with children array and isEnd flag
 * 2. Insert: traverse tree, create nodes as needed, mark end
 * 3. Search: traverse tree, check if word exists and is complete
 * 4. StartsWith: traverse tree, check if prefix exists
 */

package masterDSA.topics.tries_hashing.ImplementTriePrefixTree.ImplementTriePrefixTree;

import java.util.*;

public class ImplementTriePrefixTree {
    
    /**
     * Trie implementation - O(m) time per operation, O(m) space per word
     * Most efficient approach for this problem
     */
    public static class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode();
                }
                current = current.children[c - 'a'];
            }
            current.isEnd = true;
        }
        
        public boolean search(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    return false;
                }
                current = current.children[c - 'a'];
            }
            return current.isEnd;
        }
        
        public boolean startsWith(String prefix) {
            TrieNode current = root;
            for (char c : prefix.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    return false;
                }
                current = current.children[c - 'a'];
            }
            return true;
        }
    }
    
    private static class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        
        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    
    /**
     * Alternative implementation using HashMap - O(m) time per operation, O(m) space per word
     * Uses HashMap instead of array for children
     */
    public static class TrieHashMap {
        private TrieNodeHashMap root;
        
        public TrieHashMap() {
            root = new TrieNodeHashMap();
        }
        
        public void insert(String word) {
            TrieNodeHashMap current = root;
            for (char c : word.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNodeHashMap());
                current = current.children.get(c);
            }
            current.isEnd = true;
        }
        
        public boolean search(String word) {
            TrieNodeHashMap current = root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return false;
                }
                current = current.children.get(c);
            }
            return current.isEnd;
        }
        
        public boolean startsWith(String prefix) {
            TrieNodeHashMap current = root;
            for (char c : prefix.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return false;
                }
                current = current.children.get(c);
            }
            return true;
        }
    }
    
    private static class TrieNodeHashMap {
        Map<Character, TrieNodeHashMap> children;
        boolean isEnd;
        
        public TrieNodeHashMap() {
            children = new HashMap<>();
            isEnd = false;
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        System.out.println("Testing Implement Trie (Prefix Tree):");
        
        // Test array-based implementation
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("Search 'apple': " + trie.search("apple"));
        System.out.println("Search 'app': " + trie.search("app"));
        System.out.println("StartsWith 'app': " + trie.startsWith("app"));
        
        trie.insert("app");
        System.out.println("Search 'app': " + trie.search("app"));
        
        // Test HashMap-based implementation
        TrieHashMap trieHashMap = new TrieHashMap();
        trieHashMap.insert("apple");
        System.out.println("HashMap - Search 'apple': " + trieHashMap.search("apple"));
        System.out.println("HashMap - Search 'app': " + trieHashMap.search("app"));
        System.out.println("HashMap - StartsWith 'app': " + trieHashMap.startsWith("app"));
    }
}
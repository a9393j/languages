package masterDSA.topics.graphs;
import java.util.*;


/**
 * Word Ladder
 * 
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList 
 * is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * - Every adjacent pair of words differs by a single letter.
 * - Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * - sk == endWord
 * 
 * LeetCode: https://leetcode.com/problems/word-ladder/
 * Difficulty: Hard
 * Tags: bfs, graph
 * 
 * Approach:
 * - BFS: Use queue to find shortest transformation path
 * - Bidirectional BFS: Search from both ends simultaneously
 * - Preprocessing: Build adjacency list for faster lookup
 * 
 * Pseudocode:
 * BFS:
 * 1. Add beginWord to queue
 * 2. For each word in queue:
 *    a. Generate all possible one-character transformations
 *    b. If transformation equals endWord, return current level + 1
 *    c. If transformation is in wordList, add to queue
 * 3. Return 0 if no transformation found
 */
public class WordLadder {
    
    /**
     * BFS approach - O(M²N) time, O(MN) space
     * Use queue to find shortest transformation path
     */
    public static int ladderLength_bfs(String beginWord, String endWord, java.util.List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordList);
        java.util.Queue<String> queue = new java.util.LinkedList<>();
        java.util.Set<String> visited = new java.util.HashSet<>();
        
        queue.offer(beginWord);
        visited.add(beginWord);
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                if (current.equals(endWord)) {
                    return level;
                }
                
                // Generate all possible one-character transformations
                for (int j = 0; j < current.length(); j++) {
                    char[] chars = current.toCharArray();
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != chars[j]) {
                            chars[j] = c;
                            String transformed = new String(chars);
                            
                            if (wordSet.contains(transformed) && !visited.contains(transformed)) {
                                queue.offer(transformed);
                                visited.add(transformed);
                            }
                        }
                    }
                }
            }
            
            level++;
        }
        
        return 0;
    }
    
    /**
     * Bidirectional BFS approach - O(M²N) time, O(MN) space
     * Search from both ends simultaneously
     */
    public static int ladderLength_bidirectional(String beginWord, String endWord, java.util.List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordList);
        java.util.Set<String> beginSet = new java.util.HashSet<>();
        java.util.Set<String> endSet = new java.util.HashSet<>();
        java.util.Set<String> visited = new java.util.HashSet<>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        visited.add(beginWord);
        visited.add(endWord);
        
        int level = 1;
        
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller set
            if (beginSet.size() > endSet.size()) {
                java.util.Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            
            java.util.Set<String> nextSet = new java.util.HashSet<>();
            
            for (String current : beginSet) {
                char[] chars = current.toCharArray();
                
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != original) {
                            chars[i] = c;
                            String transformed = new String(chars);
                            
                            if (endSet.contains(transformed)) {
                                return level + 1;
                            }
                            
                            if (wordSet.contains(transformed) && !visited.contains(transformed)) {
                                nextSet.add(transformed);
                                visited.add(transformed);
                            }
                        }
                    }
                    
                    chars[i] = original;
                }
            }
            
            beginSet = nextSet;
            level++;
        }
        
        return 0;
    }
    
    /**
     * Preprocessing approach - O(M²N) time, O(MN) space
     * Build adjacency list for faster lookup
     */
    public static int ladderLength_preprocessing(String beginWord, String endWord, java.util.List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        // Build adjacency list
        java.util.Map<String, java.util.List<String>> graph = buildGraph(wordList);
        
        java.util.Queue<String> queue = new java.util.LinkedList<>();
        java.util.Set<String> visited = new java.util.HashSet<>();
        
        queue.offer(beginWord);
        visited.add(beginWord);
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                if (current.equals(endWord)) {
                    return level;
                }
                
                // Generate all possible one-character transformations
                for (int j = 0; j < current.length(); j++) {
                    char[] chars = current.toCharArray();
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != chars[j]) {
                            chars[j] = c;
                            String transformed = new String(chars);
                            
                            if (graph.containsKey(transformed) && !visited.contains(transformed)) {
                                queue.offer(transformed);
                                visited.add(transformed);
                            }
                        }
                    }
                }
            }
            
            level++;
        }
        
        return 0;
    }
    
    private static java.util.Map<String, java.util.List<String>> buildGraph(java.util.List<String> wordList) {
        java.util.Map<String, java.util.List<String>> graph = new java.util.HashMap<>();
        
        for (String word : wordList) {
            graph.put(word, new java.util.ArrayList<>());
        }
        
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != chars[i]) {
                        chars[i] = c;
                        String transformed = new String(chars);
                        
                        if (graph.containsKey(transformed)) {
                            graph.get(word).add(transformed);
                        }
                    }
                }
            }
        }
        
        return graph;
    }
    
    /**
     * Find all shortest transformation sequences
     */
    public static java.util.List<java.util.List<String>> findLadders(String beginWord, String endWord, java.util.List<String> wordList) {
        java.util.List<java.util.List<String>> result = new java.util.ArrayList<>();
        
        if (!wordList.contains(endWord)) {
            return result;
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordList);
        java.util.Queue<java.util.List<String>> queue = new java.util.LinkedList<>();
        java.util.Set<String> visited = new java.util.HashSet<>();
        
        queue.offer(java.util.Arrays.asList(beginWord));
        visited.add(beginWord);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            java.util.Set<String> currentLevelVisited = new java.util.HashSet<>();
            
            for (int i = 0; i < size; i++) {
                java.util.List<String> currentPath = queue.poll();
                String currentWord = currentPath.get(currentPath.size() - 1);
                
                if (currentWord.equals(endWord)) {
                    result.add(new java.util.ArrayList<>(currentPath));
                    continue;
                }
                
                for (int j = 0; j < currentWord.length(); j++) {
                    char[] chars = currentWord.toCharArray();
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != chars[j]) {
                            chars[j] = c;
                            String transformed = new String(chars);
                            
                            if (wordSet.contains(transformed) && !visited.contains(transformed)) {
                                java.util.List<String> newPath = new java.util.ArrayList<>(currentPath);
                                newPath.add(transformed);
                                queue.offer(newPath);
                                currentLevelVisited.add(transformed);
                            }
                        }
                    }
                }
            }
            
            visited.addAll(currentLevelVisited);
            
            if (!result.isEmpty()) {
                break;
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to check if two words differ by one character
     */
    public static boolean isOneCharDiff(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }
        
        return diffCount == 1;
    }
    
    /**
     * Helper method to find all words that differ by one character
     */
    public static java.util.List<String> findOneCharDiffWords(String word, java.util.Set<String> wordSet) {
        java.util.List<String> result = new java.util.ArrayList<>();
        
        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != chars[i]) {
                    chars[i] = c;
                    String transformed = new String(chars);
                    
                    if (wordSet.contains(transformed)) {
                        result.add(transformed);
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find shortest path between two words
     */
    public static java.util.List<String> findShortestPath(String beginWord, String endWord, java.util.List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return new java.util.ArrayList<>();
        }
        
        java.util.Set<String> wordSet = new java.util.HashSet<>(wordList);
        java.util.Map<String, String> parent = new java.util.HashMap<>();
        java.util.Queue<String> queue = new java.util.LinkedList<>();
        
        queue.offer(beginWord);
        parent.put(beginWord, null);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            
            if (current.equals(endWord)) {
                break;
            }
            
            for (int i = 0; i < current.length(); i++) {
                char[] chars = current.toCharArray();
                
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != chars[i]) {
                        chars[i] = c;
                        String transformed = new String(chars);
                        
                        if (wordSet.contains(transformed) && !parent.containsKey(transformed)) {
                            parent.put(transformed, current);
                            queue.offer(transformed);
                        }
                    }
                }
            }
        }
        
        // Reconstruct path
        java.util.List<String> path = new java.util.ArrayList<>();
        String current = endWord;
        
        while (current != null) {
            path.add(0, current);
            current = parent.get(current);
        }
        
        return path;
    }
    
    public static void main(String[] args) {
        // Test case: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        String beginWord = "hit";
        String endWord = "cog";
        java.util.List<String> wordList = java.util.Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        
        System.out.println("Testing Word Ladder:");
        System.out.println("Begin word: " + beginWord);
        System.out.println("End word: " + endWord);
        System.out.println("Word list: " + wordList);
        
        int result1 = ladderLength_bfs(beginWord, endWord, wordList);
        System.out.println("BFS result: " + result1);
        
        int result2 = ladderLength_bidirectional(beginWord, endWord, wordList);
        System.out.println("Bidirectional BFS result: " + result2);
        
        int result3 = ladderLength_preprocessing(beginWord, endWord, wordList);
        System.out.println("Preprocessing result: " + result3);
        
        // Expected: 5 (hit -> hot -> dot -> dog -> cog)
        
        System.out.println();
        
        // Test case: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
        java.util.List<String> wordList2 = java.util.Arrays.asList("hot", "dot", "dog", "lot", "log");
        
        System.out.println("Word list: " + wordList2);
        
        int result4 = ladderLength_bfs(beginWord, endWord, wordList2);
        System.out.println("BFS result: " + result4);
        
        // Expected: 0 (no transformation possible)
        
        System.out.println();
        
        // Test case: beginWord = "a", endWord = "c", wordList = ["a","b","c"]
        String beginWord2 = "a";
        String endWord2 = "c";
        java.util.List<String> wordList3 = java.util.Arrays.asList("a", "b", "c");
        
        System.out.println("Begin word: " + beginWord2);
        System.out.println("End word: " + endWord2);
        System.out.println("Word list: " + wordList3);
        
        int result5 = ladderLength_bfs(beginWord2, endWord2, wordList3);
        System.out.println("BFS result: " + result5);
        
        // Expected: 2 (a -> c)
        
        System.out.println();
        
        // Test find all ladders
        java.util.List<java.util.List<String>> allLadders = findLadders(beginWord, endWord, wordList);
        System.out.println("All shortest ladders:");
        for (java.util.List<String> ladder : allLadders) {
            System.out.println(ladder);
        }
        
        // Test helper methods
        boolean isOneDiff = isOneCharDiff("hit", "hot");
        System.out.println("'hit' and 'hot' differ by one character: " + isOneDiff);
        
        java.util.List<String> oneCharDiffWords = findOneCharDiffWords("hit", new java.util.HashSet<>(wordList));
        System.out.println("Words differing by one character from 'hit': " + oneCharDiffWords);
        
        java.util.List<String> shortestPath = findShortestPath(beginWord, endWord, wordList);
        System.out.println("Shortest path: " + shortestPath);
        
        // Expected: isOneDiff = true, oneCharDiffWords = [hot], shortestPath = [hit, hot, dot, dog, cog]
    }
}

package masterDSA.topics.graphs;
import java.util.*;


/**
 * Alien Dictionary
 * 
 * There is a new alien language that uses the English alphabet. However, the order among 
 * the letters is unknown to you.
 * 
 * LeetCode: https://leetcode.com/problems/alien-dictionary/
 * Difficulty: Hard
 * Tags: topological-sort, graph
 * 
 * Approach:
 * - Build graph from word comparisons
 * - Use topological sort to find valid ordering
 * - Handle cycles and invalid inputs
 * 
 * Pseudocode:
 * 1. Compare adjacent words to find character orderings
 * 2. Build adjacency list and in-degree count
 * 3. Use Kahn's algorithm for topological sort
 * 4. Return ordering if valid, empty string if cycle detected
 */
public class AlienDictionary {
    
    /**
     * Topological sort approach - O(C) time, O(1) space where C is total characters
     * Use Kahn's algorithm to find valid ordering
     */
    public static String alienOrder_topological(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        // Build adjacency list and in-degree count
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        
        // Initialize all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        
        // Build graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check if word1 is a prefix of word2
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return ""; // Invalid input
            }
            
            // Find first different character
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break; // Only first difference matters
                }
            }
        }
        
        // Kahn's algorithm
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);
            
            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Check if all characters are included
        return result.length() == graph.size() ? result.toString() : "";
    }
    
    /**
     * DFS approach - O(C) time, O(1) space
     * Use DFS to detect cycles and build ordering
     */
    public static String alienOrder_dfs(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        // Build adjacency list
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        // Initialize all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        
        // Build graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check if word1 is a prefix of word2
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return ""; // Invalid input
            }
            
            // Find first different character
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    break; // Only first difference matters
                }
            }
        }
        
        // DFS to detect cycles and build ordering
        Map<Character, Integer> state = new HashMap<>(); // 0: unvisited, 1: visiting, 2: visited
        List<Character> order = new ArrayList<>();
        
        for (char c : graph.keySet()) {
            state.put(c, 0);
        }
        
        for (char c : graph.keySet()) {
            if (state.get(c) == 0) {
                if (dfs(c, graph, state, order)) {
                    return ""; // Cycle detected
                }
            }
        }
        
        // Reverse to get correct order
        Collections.reverse(order);
        StringBuilder result = new StringBuilder();
        for (char c : order) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    private static boolean dfs(char c, Map<Character, Set<Character>> graph, 
                              Map<Character, Integer> state, List<Character> order) {
        if (state.get(c) == 1) {
            return true; // Cycle detected
        }
        
        if (state.get(c) == 2) {
            return false; // Already visited
        }
        
        state.put(c, 1); // Mark as visiting
        
        for (char neighbor : graph.get(c)) {
            if (dfs(neighbor, graph, state, order)) {
                return true;
            }
        }
        
        state.put(c, 2); // Mark as visited
        order.add(c);
        return false;
    }
    
    /**
     * Helper method to validate alien dictionary
     */
    public static boolean isValidAlienDictionary(String[] words, String order) {
        if (words == null || words.length == 0 || order == null) {
            return false;
        }
        
        Map<Character, Integer> charOrder = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            charOrder.put(order.charAt(i), i);
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            if (!isValidOrder(word1, word2, charOrder)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean isValidOrder(String word1, String word2, Map<Character, Integer> charOrder) {
        int minLen = Math.min(word1.length(), word2.length());
        
        for (int i = 0; i < minLen; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            
            if (c1 != c2) {
                int order1 = charOrder.getOrDefault(c1, -1);
                int order2 = charOrder.getOrDefault(c2, -1);
                
                if (order1 == -1 || order2 == -1) {
                    return false; // Unknown character
                }
                
                return order1 < order2;
            }
        }
        
        return word1.length() <= word2.length();
    }
    
    /**
     * Helper method to find all possible orderings
     */
    public static List<String> findAllOrderings(String[] words) {
        List<String> result = new ArrayList<>();
        
        if (words == null || words.length == 0) {
            return result;
        }
        
        // Collect all unique characters
        Set<Character> chars = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                chars.add(c);
            }
        }
        
        // Generate all permutations
        List<Character> charList = new ArrayList<>(chars);
        generatePermutations(charList, 0, words, result);
        
        return result;
    }
    
    private static void generatePermutations(List<Character> chars, int start, 
                                           String[] words, List<String> result) {
        if (start == chars.size()) {
            StringBuilder order = new StringBuilder();
            for (char c : chars) {
                order.append(c);
            }
            
            if (isValidAlienDictionary(words, order.toString())) {
                result.add(order.toString());
            }
            return;
        }
        
        for (int i = start; i < chars.size(); i++) {
            Collections.swap(chars, start, i);
            generatePermutations(chars, start + 1, words, result);
            Collections.swap(chars, start, i);
        }
    }
    
    /**
     * Helper method to find minimum number of comparisons needed
     */
    public static int minComparisons(String[] words) {
        if (words == null || words.length <= 1) {
            return 0;
        }
        
        int comparisons = 0;
        
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            int minLen = Math.min(word1.length(), word2.length());
            boolean foundDiff = false;
            
            for (int j = 0; j < minLen; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    comparisons++;
                    foundDiff = true;
                    break;
                }
            }
            
            if (!foundDiff && word1.length() > word2.length()) {
                return -1; // Invalid input
            }
        }
        
        return comparisons;
    }
    
    /**
     * Helper method to find longest common prefix
     */
    public static String longestCommonPrefix(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        String prefix = words[0];
        
        for (int i = 1; i < words.length; i++) {
            while (!words[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
    
    public static void main(String[] args) {
        // Test case: words = ["wrt","wrf","er","ett","rftt"]
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        
        System.out.println("Testing Alien Dictionary:");
        System.out.println("Words: " + Arrays.toString(words1));
        
        String result1 = alienOrder_topological(words1);
        System.out.println("Topological sort result: " + result1);
        
        String result2 = alienOrder_dfs(words1);
        System.out.println("DFS result: " + result2);
        
        // Expected: "wertf" or "wertf"
        
        System.out.println();
        
        // Test case: words = ["z","x"]
        String[] words2 = {"z", "x"};
        
        System.out.println("Words: " + Arrays.toString(words2));
        
        String result3 = alienOrder_topological(words2);
        System.out.println("Topological sort result: " + result3);
        
        // Expected: "zx"
        
        System.out.println();
        
        // Test case: words = ["z","x","z"]
        String[] words3 = {"z", "x", "z"};
        
        System.out.println("Words: " + Arrays.toString(words3));
        
        String result4 = alienOrder_topological(words3);
        System.out.println("Topological sort result: " + result4);
        
        // Expected: "" (cycle detected)
        
        System.out.println();
        
        // Test case: words = ["abc","ab"]
        String[] words4 = {"abc", "ab"};
        
        System.out.println("Words: " + Arrays.toString(words4));
        
        String result5 = alienOrder_topological(words4);
        System.out.println("Topological sort result: " + result5);
        
        // Expected: "" (invalid input)
        
        System.out.println();
        
        // Test helper methods
        boolean isValid = isValidAlienDictionary(words1, result1);
        System.out.println("Is valid alien dictionary: " + isValid);
        
        int minComparisons = minComparisons(words1);
        System.out.println("Minimum comparisons needed: " + minComparisons);
        
        String commonPrefix = longestCommonPrefix(words1);
        System.out.println("Longest common prefix: " + commonPrefix);
        
        // Test with smaller example
        String[] words5 = {"ba", "bc", "ac"};
        List<String> allOrderings = findAllOrderings(words5);
        System.out.println("All possible orderings: " + allOrderings);
        
        // Expected: Is valid = true, Min comparisons = 4, Common prefix = "", All orderings = [bac, bca]
    }
}

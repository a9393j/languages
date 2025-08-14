package syntax;

import java.util.*;

/**
 * MapOps - Comprehensive Map Operations for Competitive Coding
 * 
 * This class provides essential map operations using HashMap and TreeMap,
 * which are commonly used in competitive programming and coding interviews.
 * 
 * Key Features:
 * - HashMap operations (unordered, O(1) average case)
 * - TreeMap operations (ordered, O(log n) operations)
 * - Common map patterns and algorithms
 * - Performance considerations for competitive coding
 * 
 * Time Complexity Notes:
 * - HashMap: O(1) average case for get/put/remove
 * - TreeMap: O(log n) for get/put/remove
 * - Iteration: O(n) for both
 * 
 * Space Complexity: O(n) where n is number of key-value pairs
 */
public class MapOps {

    /**
     * Demonstrates HashMap operations
     * HashMap provides O(1) average case operations but no ordering
     */
    public static void hashMapOps() {
        System.out.println("------------------------------------------");
        System.out.println("HASHMAP OPERATIONS");
        System.out.println("---------------------");

        // HashMap declaration and initialization
        HashMap<String, Integer> map = new HashMap<>();
        
        // Basic put operations
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        System.out.println("After put operations: " + map);

        // putIfAbsent - only adds if key doesn't exist
        map.putIfAbsent("E", 5);
        map.putIfAbsent("E", 6); // Won't change value
        System.out.println("After putIfAbsent: " + map);

        // Get operations
        System.out.println("get('E'): " + map.get("E"));
        System.out.println("getOrDefault('F', 0): " + map.getOrDefault("F", 0));

        // Remove operations
        map.remove("E");
        System.out.println("After removing 'E': " + map);

        // Size and empty check
        System.out.println("Size: " + map.size());
        System.out.println("Is empty: " + map.isEmpty());

        // Contains operations
        System.out.println("Contains key 'A': " + map.containsKey("A"));
        System.out.println("Contains value 2: " + map.containsValue(2));

        // Clear operation
        map.clear();
        System.out.println("After clear: " + map);
    }

    /**
     * Demonstrates TreeMap operations
     * TreeMap provides O(log n) operations but maintains sorted order
     */
    public static void treeMapOps() {
        System.out.println("------------------------------------------");
        System.out.println("TREEMAP OPERATIONS");
        System.out.println("---------------------");

        // TreeMap declaration (maintains natural ordering)
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        
        // Adding elements
        treeMap.put("Zebra", 1);
        treeMap.put("Apple", 2);
        treeMap.put("Banana", 3);
        treeMap.put("Cat", 4);
        System.out.println("TreeMap (sorted): " + treeMap);

        // First and last operations
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("First entry: " + treeMap.firstEntry());
        System.out.println("Last entry: " + treeMap.lastEntry());

        // Ceiling and floor operations
        System.out.println("Ceiling key for 'B': " + treeMap.ceilingKey("B"));
        System.out.println("Floor key for 'B': " + treeMap.floorKey("B"));
        System.out.println("Higher key for 'B': " + treeMap.higherKey("B"));
        System.out.println("Lower key for 'B': " + treeMap.lowerKey("B"));

        // Submap operations
        System.out.println("Submap from 'A' to 'C': " + treeMap.subMap("A", "C"));
        System.out.println("Head map up to 'C': " + treeMap.headMap("C"));
        System.out.println("Tail map from 'B': " + treeMap.tailMap("B"));

        // Poll operations (remove and return)
        System.out.println("Poll first: " + treeMap.pollFirstEntry());
        System.out.println("Poll last: " + treeMap.pollLastEntry());
        System.out.println("After polling: " + treeMap);
    }

    /**
     * Demonstrates advanced map operations and patterns
     * Shows common competitive coding techniques using maps
     */
    public static void advancedMapOps() {
        System.out.println("------------------------------------------");
        System.out.println("ADVANCED MAP OPERATIONS");
        System.out.println("---------------------");

        // Character frequency counter
        String text = "hello world";
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for (char c : text.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        System.out.println("Character frequency: " + charFreq);

        // Word frequency counter
        String[] words = {"hello", "world", "hello", "java", "world"};
        HashMap<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }
        System.out.println("Word frequency: " + wordFreq);

        // Two sum problem using HashMap
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("Two sum for target " + target + ": " + Arrays.toString(result));

        // Group anagrams
        String[] anagrams = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> grouped = groupAnagrams(anagrams);
        System.out.println("Grouped anagrams: " + grouped);

        // Longest substring without repeating characters
        String s = "abcabcbb";
        int longest = longestSubstringWithoutRepeating(s);
        System.out.println("Longest substring without repeating chars: " + longest);
    }

    /**
     * Demonstrates map iteration and manipulation
     * Shows different ways to iterate through maps
     */
    public static void mapIterationOps() {
        System.out.println("------------------------------------------");
        System.out.println("MAP ITERATION OPERATIONS");
        System.out.println("---------------------");

        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        // Iterate through keys
        System.out.println("Keys:");
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }

        // Iterate through values
        System.out.println("Values:");
        for (int value : map.values()) {
            System.out.println(value);
        }

        // Iterate through entries
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Using forEach (Java 8+)
        System.out.println("Using forEach:");
        map.forEach((key, value) -> System.out.println(key + " -> " + value));

        // Filter and collect
        System.out.println("Values greater than 2:");
        map.entrySet().stream()
            .filter(entry -> entry.getValue() > 2)
            .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
    }

    /**
     * Demonstrates map-based algorithms
     * Shows common algorithms that use maps as their core data structure
     */
    public static void mapAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("MAP ALGORITHMS");
        System.out.println("---------------------");

        // LRU Cache implementation
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        System.out.println("LRU Cache after initial puts: " + lruCache.getCache());
        
        lruCache.get(1); // Move to front
        lruCache.put(4, "D"); // Remove least recently used (2)
        System.out.println("LRU Cache after access and new put: " + lruCache.getCache());

        // First non-repeating character
        String s = "leetcode";
        char firstNonRepeating = firstNonRepeatingCharacter(s);
        System.out.println("First non-repeating character in '" + s + "': " + firstNonRepeating);

        // Valid anagram
        String s1 = "anagram";
        String s2 = "nagaram";
        boolean isValidAnagram = isValidAnagram(s1, s2);
        System.out.println("Are '" + s1 + "' and '" + s2 + "' valid anagrams: " + isValidAnagram);
    }

    /**
     * Two Sum problem using HashMap
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        
        return new int[]{-1, -1}; // No solution found
    }

    /**
     * Group anagrams using HashMap
     * Time Complexity: O(n * k) where n is number of strings, k is max string length
     * Space Complexity: O(n * k)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groups = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            
            groups.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(groups.values());
    }

    /**
     * Longest substring without repeating characters
     * Time Complexity: O(n)
     * Space Complexity: O(min(m, n)) where m is charset size
     */
    public static int longestSubstringWithoutRepeating(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int start = 0;
        
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            
            if (map.containsKey(currentChar)) {
                start = Math.max(start, map.get(currentChar) + 1);
            }
            
            map.put(currentChar, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }

    /**
     * First non-repeating character
     * Time Complexity: O(n)
     * Space Complexity: O(1) - fixed size array
     */
    public static char firstNonRepeatingCharacter(String s) {
        int[] freq = new int[26];
        
        // Count frequency
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Find first non-repeating
        for (char c : s.toCharArray()) {
            if (freq[c - 'a'] == 1) {
                return c;
            }
        }
        
        return '#'; // No non-repeating character found
    }

    /**
     * Valid anagram check
     * Time Complexity: O(n)
     * Space Complexity: O(1) - fixed size array
     */
    public static boolean isValidAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] charCount = new int[26];
        
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        
        for (char c : t.toCharArray()) {
            charCount[c - 'a']--;
            if (charCount[c - 'a'] < 0) return false;
        }
        
        return true;
    }

    /**
     * LRU Cache implementation using HashMap and Doubly Linked List
     */
    static class LRUCache<K, V> {
        private final int capacity;
        private final HashMap<K, Node<K, V>> cache;
        private final Node<K, V> head;
        private final Node<K, V> tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.head = new Node<>();
            this.tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        public V get(K key) {
            Node<K, V> node = cache.get(key);
            if (node == null) return null;
            
            moveToHead(node);
            return node.value;
        }

        public void put(K key, V value) {
            Node<K, V> node = cache.get(key);
            
            if (node == null) {
                Node<K, V> newNode = new Node<>(key, value);
                cache.put(key, newNode);
                addNode(newNode);
                
                if (cache.size() > capacity) {
                    Node<K, V> tail = removeTail();
                    cache.remove(tail.key);
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }

        private void addNode(Node<K, V> node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node<K, V> node) {
            Node<K, V> prev = node.prev;
            Node<K, V> next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        private void moveToHead(Node<K, V> node) {
            removeNode(node);
            addNode(node);
        }

        private Node<K, V> removeTail() {
            Node<K, V> res = tail.prev;
            removeNode(res);
            return res;
        }

        public HashMap<K, V> getCache() {
            HashMap<K, V> result = new HashMap<>();
            for (Map.Entry<K, Node<K, V>> entry : cache.entrySet()) {
                result.put(entry.getKey(), entry.getValue().value);
            }
            return result;
        }

        static class Node<K, V> {
            K key;
            V value;
            Node<K, V> prev;
            Node<K, V> next;

            Node() {}

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    /**
     * Main method to demonstrate all map operations
     */
    public static void mapOps() {
        hashMapOps();
        treeMapOps();
        advancedMapOps();
        mapIterationOps();
        mapAlgorithms();
    }
}

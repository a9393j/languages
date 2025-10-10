package masterDSA.topics.tries_hashing.DesignHashMap;
import java.util.*;


/**
 * Design HashMap
 * 
 * Design a HashMap without using any built-in hash table libraries.
 * 
 * LeetCode: https://leetcode.com/problems/design-hashmap/
 * Difficulty: Easy
 * Tags: hash-table, design
 * 
 * Approach:
 * - Array with chaining: Use array of linked lists for collision handling
 * - Open addressing: Use linear probing for collision handling
 * - Dynamic resizing: Resize when load factor exceeds threshold
 * 
 * Pseudocode:
 * Array with chaining:
 * 1. Create array of buckets (linked lists)
 * 2. Hash key to get bucket index
 * 3. For put: Find or create node in bucket
 * 4. For get: Find node in bucket
 * 5. For remove: Remove node from bucket
 */
public class DesignHashMap {
    
    /**
     * Hash Map Node class
     */
    static class Node {
        int key;
        int value;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    /**
     * HashMap implementation with chaining
     */
    static class MyHashMap {
        private static final int INITIAL_CAPACITY = 16;
        private static final double LOAD_FACTOR = 0.75;
        
        private Node[] buckets;
        private int size;
        private int capacity;
        
        public MyHashMap() {
            this.capacity = INITIAL_CAPACITY;
            this.buckets = new Node[capacity];
            this.size = 0;
        }
        
        /**
         * Put key-value pair - O(1) average time, O(n) worst case
         */
        public void put(int key, int value) {
            int index = hash(key);
            Node current = buckets[index];
            
            // Check if key already exists
            while (current != null) {
                if (current.key == key) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            
            // Add new node at the beginning
            Node newNode = new Node(key, value);
            newNode.next = buckets[index];
            buckets[index] = newNode;
            size++;
            
            // Resize if necessary
            if (size > capacity * LOAD_FACTOR) {
                resize();
            }
        }
        
        /**
         * Get value by key - O(1) average time, O(n) worst case
         */
        public int get(int key) {
            int index = hash(key);
            Node current = buckets[index];
            
            while (current != null) {
                if (current.key == key) {
                    return current.value;
                }
                current = current.next;
            }
            
            return -1;
        }
        
        /**
         * Remove key-value pair - O(1) average time, O(n) worst case
         */
        public void remove(int key) {
            int index = hash(key);
            Node current = buckets[index];
            Node prev = null;
            
            while (current != null) {
                if (current.key == key) {
                    if (prev == null) {
                        buckets[index] = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                    return;
                }
                prev = current;
                current = current.next;
            }
        }
        
        /**
         * Hash function - O(1) time
         */
        private int hash(int key) {
            return key % capacity;
        }
        
        /**
         * Resize hash map - O(n) time, O(n) space
         */
        private void resize() {
            Node[] oldBuckets = buckets;
            capacity *= 2;
            buckets = new Node[capacity];
            size = 0;
            
            for (Node bucket : oldBuckets) {
                Node current = bucket;
                while (current != null) {
                    put(current.key, current.value);
                    current = current.next;
                }
            }
        }
        
        /**
         * Check if hash map is empty - O(1) time
         */
        public boolean isEmpty() {
            return size == 0;
        }
        
        /**
         * Get size of hash map - O(1) time
         */
        public int size() {
            return size;
        }
        
        /**
         * Check if key exists - O(1) average time, O(n) worst case
         */
        public boolean containsKey(int key) {
            return get(key) != -1;
        }
        
        /**
         * Clear all key-value pairs - O(1) time
         */
        public void clear() {
            buckets = new Node[capacity];
            size = 0;
        }
        
        /**
         * Get all keys - O(n) time, O(n) space
         */
        public java.util.List<Integer> keySet() {
            java.util.List<Integer> keys = new java.util.ArrayList<>();
            
            for (Node bucket : buckets) {
                Node current = bucket;
                while (current != null) {
                    keys.add(current.key);
                    current = current.next;
                }
            }
            
            return keys;
        }
        
        /**
         * Get all values - O(n) time, O(n) space
         */
        public java.util.List<Integer> values() {
            java.util.List<Integer> values = new java.util.ArrayList<>();
            
            for (Node bucket : buckets) {
                Node current = bucket;
                while (current != null) {
                    values.add(current.value);
                    current = current.next;
                }
            }
            
            return values;
        }
    }
    
    /**
     * HashMap implementation with open addressing (linear probing)
     */
    static class MyHashMapOpenAddressing {
        private static final int INITIAL_CAPACITY = 16;
        private static final double LOAD_FACTOR = 0.75;
        private static final int DELETED = -2;
        private static final int EMPTY = -1;
        
        private int[] keys;
        private int[] values;
        private boolean[] deleted;
        private int size;
        private int capacity;
        
        public MyHashMapOpenAddressing() {
            this.capacity = INITIAL_CAPACITY;
            this.keys = new int[capacity];
            this.values = new int[capacity];
            this.deleted = new boolean[capacity];
            this.size = 0;
            
            java.util.Arrays.fill(keys, EMPTY);
            java.util.Arrays.fill(values, EMPTY);
        }
        
        /**
         * Put key-value pair - O(1) average time, O(n) worst case
         */
        public void put(int key, int value) {
            int index = findIndex(key);
            
            if (keys[index] == EMPTY || deleted[index]) {
                size++;
            }
            
            keys[index] = key;
            values[index] = value;
            deleted[index] = false;
            
            // Resize if necessary
            if (size > capacity * LOAD_FACTOR) {
                resize();
            }
        }
        
        /**
         * Get value by key - O(1) average time, O(n) worst case
         */
        public int get(int key) {
            int index = findIndex(key);
            
            if (keys[index] == EMPTY || deleted[index]) {
                return -1;
            }
            
            return values[index];
        }
        
        /**
         * Remove key-value pair - O(1) average time, O(n) worst case
         */
        public void remove(int key) {
            int index = findIndex(key);
            
            if (keys[index] != EMPTY && !deleted[index]) {
                deleted[index] = true;
                size--;
            }
        }
        
        /**
         * Find index for key using linear probing - O(1) average time, O(n) worst case
         */
        private int findIndex(int key) {
            int index = hash(key);
            
            while (keys[index] != EMPTY && (keys[index] != key || deleted[index])) {
                index = (index + 1) % capacity;
            }
            
            return index;
        }
        
        /**
         * Hash function - O(1) time
         */
        private int hash(int key) {
            return key % capacity;
        }
        
        /**
         * Resize hash map - O(n) time, O(n) space
         */
        private void resize() {
            int[] oldKeys = keys;
            int[] oldValues = values;
            boolean[] oldDeleted = deleted;
            
            capacity *= 2;
            keys = new int[capacity];
            values = new int[capacity];
            deleted = new boolean[capacity];
            size = 0;
            
            java.util.Arrays.fill(keys, EMPTY);
            java.util.Arrays.fill(values, EMPTY);
            
            for (int i = 0; i < oldKeys.length; i++) {
                if (oldKeys[i] != EMPTY && !oldDeleted[i]) {
                    put(oldKeys[i], oldValues[i]);
                }
            }
        }
        
        /**
         * Check if hash map is empty - O(1) time
         */
        public boolean isEmpty() {
            return size == 0;
        }
        
        /**
         * Get size of hash map - O(1) time
         */
        public int size() {
            return size;
        }
        
        /**
         * Check if key exists - O(1) average time, O(n) worst case
         */
        public boolean containsKey(int key) {
            return get(key) != -1;
        }
        
        /**
         * Clear all key-value pairs - O(1) time
         */
        public void clear() {
            java.util.Arrays.fill(keys, EMPTY);
            java.util.Arrays.fill(values, EMPTY);
            java.util.Arrays.fill(deleted, false);
            size = 0;
        }
    }
    
    /**
     * Helper method to test hash map operations
     */
    public static void testHashMap(MyHashMap map) {
        System.out.println("Testing HashMap operations:");
        
        map.put(1, 1);
        System.out.println("Put (1,1)");
        
        map.put(2, 2);
        System.out.println("Put (2,2)");
        
        int value1 = map.get(1);
        System.out.println("Get(1): " + value1);
        
        int value2 = map.get(3);
        System.out.println("Get(3): " + value2);
        
        map.put(2, 1);
        System.out.println("Put (2,1)");
        
        int value3 = map.get(2);
        System.out.println("Get(2): " + value3);
        
        map.remove(2);
        System.out.println("Remove(2)");
        
        int value4 = map.get(2);
        System.out.println("Get(2): " + value4);
        
        System.out.println("Size: " + map.size());
        System.out.println("Is empty: " + map.isEmpty());
        System.out.println("Contains key 1: " + map.containsKey(1));
        System.out.println("Keys: " + map.keySet());
        System.out.println("Values: " + map.values());
    }
    
    /**
     * Helper method to test hash map performance
     */
    public static void testHashMapPerformance(MyHashMap map, int operations) {
        long startTime = System.currentTimeMillis();
        
        // Insert operations
        for (int i = 0; i < operations; i++) {
            map.put(i, i * 2);
        }
        
        // Search operations
        for (int i = 0; i < operations; i++) {
            map.get(i);
        }
        
        // Remove operations
        for (int i = 0; i < operations; i++) {
            map.remove(i);
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Performance test completed in " + (endTime - startTime) + " ms");
    }
    
    /**
     * Helper method to find most frequent key
     */
    public static int findMostFrequentKey(MyHashMap map) {
        java.util.Map<Integer, Integer> frequency = new java.util.HashMap<>();
        
        for (int key : map.keySet()) {
            frequency.put(key, frequency.getOrDefault(key, 0) + 1);
        }
        
        int mostFrequent = -1;
        int maxCount = 0;
        
        for (java.util.Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        
        return mostFrequent;
    }
    
    /**
     * Helper method to find keys with given value
     */
    public static java.util.List<Integer> findKeysWithValue(MyHashMap map, int value) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        for (int key : map.keySet()) {
            if (map.get(key) == value) {
                result.add(key);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test case: Basic operations
        System.out.println("Testing Design HashMap:");
        
        MyHashMap map1 = new MyHashMap();
        testHashMap(map1);
        
        System.out.println();
        
        // Test open addressing implementation
        MyHashMapOpenAddressing map2 = new MyHashMapOpenAddressing();
        System.out.println("Testing Open Addressing HashMap:");
        
        map2.put(1, 1);
        System.out.println("Put (1,1)");
        
        map2.put(2, 2);
        System.out.println("Put (2,2)");
        
        int value1 = map2.get(1);
        System.out.println("Get(1): " + value1);
        
        int value2 = map2.get(3);
        System.out.println("Get(3): " + value2);
        
        map2.put(2, 1);
        System.out.println("Put (2,1)");
        
        int value3 = map2.get(2);
        System.out.println("Get(2): " + value3);
        
        map2.remove(2);
        System.out.println("Remove(2)");
        
        int value4 = map2.get(2);
        System.out.println("Get(2): " + value4);
        
        System.out.println("Size: " + map2.size());
        System.out.println("Is empty: " + map2.isEmpty());
        System.out.println("Contains key 1: " + map2.containsKey(1));
        
        System.out.println();
        
        // Test performance
        MyHashMap map3 = new MyHashMap();
        System.out.println("Performance test with 1000 operations:");
        testHashMapPerformance(map3, 1000);
        
        System.out.println();
        
        // Test helper methods
        MyHashMap map4 = new MyHashMap();
        map4.put(1, 10);
        map4.put(2, 20);
        map4.put(3, 10);
        map4.put(4, 30);
        
        int mostFrequent = findMostFrequentKey(map4);
        System.out.println("Most frequent key: " + mostFrequent);
        
        java.util.List<Integer> keysWithValue = findKeysWithValue(map4, 10);
        System.out.println("Keys with value 10: " + keysWithValue);
        
        // Expected: Most frequent key = 1, Keys with value 10 = [1, 3]
    }
}

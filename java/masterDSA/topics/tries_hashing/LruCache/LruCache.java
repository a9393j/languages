/**
 * LRU Cache
 * 
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 * - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists. Otherwise, 
 *   add the key-value pair to the cache. If the number of keys exceeds the capacity from 
 *   this operation, evict the least recently used key.
 * 
 * LeetCode: https://leetcode.com/problems/lru-cache/
 * Difficulty: Medium
 * Tags: hashmap, linkedlist
 * 
 * Approach:
 * - Use HashMap for O(1) access
 * - Use Doubly Linked List for O(1) insertion/deletion
 * - Maintain head and tail pointers
 * 
 * Pseudocode:
 * 1. Create HashMap and Doubly Linked List
 * 2. Get: move node to head, return value
 * 3. Put: if key exists, update value and move to head
 * 4. If capacity exceeded, remove tail node
 */

package masterDSA.topics.tries_hashing.LruCache.LruCache;

import java.util.*;

public class LRUCache {
    
    /**
     * HashMap + Doubly Linked List approach - O(1) time per operation, O(capacity) space
     * Most efficient approach for this problem
     */
    private Map<Integer, Node> cache;
    private int capacity;
    private Node head;
    private Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        
        // Move to head
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = cache.get(key);
        
        if (node != null) {
            // Update existing node
            node.value = value;
            moveToHead(node);
        } else {
            // Add new node
            Node newNode = new Node(key, value);
            
            if (cache.size() >= capacity) {
                // Remove least recently used
                Node tailNode = removeTail();
                cache.remove(tailNode.key);
            }
            
            cache.put(key, newNode);
            addToHead(newNode);
        }
    }
    
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    
    private Node removeTail() {
        Node lastNode = tail.prev;
        removeNode(lastNode);
        return lastNode;
    }
    
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    /**
     * Alternative approach using LinkedHashMap - O(1) time per operation, O(capacity) space
     * Uses LinkedHashMap for simpler implementation
     */
    public static class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {
        private int capacity;
        
        public LRUCacheLinkedHashMap(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }
        
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
        
        public int get(int key) {
            return super.getOrDefault(key, -1);
        }
        
        public void put(int key, int value) {
            super.put(key, value);
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        System.out.println("Testing LRU Cache:");
        
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Get 1: " + cache.get(1));
        
        cache.put(3, 3);
        System.out.println("Get 2: " + cache.get(2));
        
        cache.put(4, 4);
        System.out.println("Get 1: " + cache.get(1));
        System.out.println("Get 3: " + cache.get(3));
        System.out.println("Get 4: " + cache.get(4));
        
        // Test LinkedHashMap implementation
        LRUCacheLinkedHashMap cache2 = new LRUCacheLinkedHashMap(2);
        cache2.put(1, 1);
        cache2.put(2, 2);
        System.out.println("LinkedHashMap - Get 1: " + cache2.get(1));
        
        cache2.put(3, 3);
        System.out.println("LinkedHashMap - Get 2: " + cache2.get(2));
    }
}
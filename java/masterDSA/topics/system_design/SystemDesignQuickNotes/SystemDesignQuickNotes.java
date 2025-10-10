/**
 * System Design Quick Notes (Load Balancer, Caching, Queues, CAP)
 * 
 * This file contains quick notes and templates for common system design concepts
 * including load balancers, caching strategies, message queues, and CAP theorem.
 * 
 * LeetCode: (No direct LeetCode equivalent)
 * Difficulty: N/A
 * Tags: system-design, notes
 * 
 * Approach:
 * - Load Balancer: Distribute traffic across multiple servers
 * - Caching: Store frequently accessed data in memory
 * - Queues: Handle asynchronous processing
 * - CAP Theorem: Consistency, Availability, Partition tolerance
 * 
 * Pseudocode:
 * Load Balancer:
 * 1. Receive request
 * 2. Select server based on algorithm
 * 3. Forward request to selected server
 * 4. Return response to client
 */

package masterDSA.topics.system_design.SystemDesignQuickNotes.SystemDesignQuickNotes;

import java.util.*;

public class SystemDesignQuickNotes {
    
    /**
     * Load Balancer implementation - O(1) time per request, O(n) space
     * Round-robin load balancing
     */
    public static class LoadBalancer {
        private List<String> servers;
        private int currentIndex;
        
        public LoadBalancer(List<String> servers) {
            this.servers = new ArrayList<>(servers);
            this.currentIndex = 0;
        }
        
        public String getServer() {
            String server = servers.get(currentIndex);
            currentIndex = (currentIndex + 1) % servers.size();
            return server;
        }
        
        public void addServer(String server) {
            servers.add(server);
        }
        
        public void removeServer(String server) {
            servers.remove(server);
        }
    }
    
    /**
     * LRU Cache implementation - O(1) time per operation, O(capacity) space
     * Least Recently Used cache
     */
    public static class LRUCache {
        private Map<String, String> cache;
        private int capacity;
        private List<String> accessOrder;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.accessOrder = new ArrayList<>();
        }
        
        public String get(String key) {
            if (cache.containsKey(key)) {
                accessOrder.remove(key);
                accessOrder.add(key);
                return cache.get(key);
            }
            return null;
        }
        
        public void put(String key, String value) {
            if (cache.containsKey(key)) {
                cache.put(key, value);
                accessOrder.remove(key);
                accessOrder.add(key);
            } else {
                if (cache.size() >= capacity) {
                    String lruKey = accessOrder.remove(0);
                    cache.remove(lruKey);
                }
                cache.put(key, value);
                accessOrder.add(key);
            }
        }
    }
    
    /**
     * Message Queue implementation - O(1) time per operation, O(n) space
     * Simple message queue
     */
    public static class MessageQueue {
        private Queue<String> queue;
        private int maxSize;
        
        public MessageQueue(int maxSize) {
            this.queue = new LinkedList<>();
            this.maxSize = maxSize;
        }
        
        public boolean enqueue(String message) {
            if (queue.size() >= maxSize) {
                return false; // Queue is full
            }
            queue.offer(message);
            return true;
        }
        
        public String dequeue() {
            return queue.poll();
        }
        
        public boolean isEmpty() {
            return queue.isEmpty();
        }
        
        public int size() {
            return queue.size();
        }
    }
    
    /**
     * CAP Theorem explanation
     */
    public static class CAPTheorem {
        public static void explainCAP() {
            System.out.println("CAP Theorem:");
            System.out.println("C - Consistency: All nodes see the same data at the same time");
            System.out.println("A - Availability: System remains operational");
            System.out.println("P - Partition tolerance: System continues despite network failures");
            System.out.println();
            System.out.println("You can only guarantee 2 out of 3:");
            System.out.println("CP: Consistent and Partition tolerant (e.g., MongoDB)");
            System.out.println("AP: Available and Partition tolerant (e.g., Cassandra)");
            System.out.println("CA: Consistent and Available (e.g., PostgreSQL)");
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        System.out.println("Testing System Design Quick Notes:");
        
        // Test Load Balancer
        List<String> servers = Arrays.asList("server1", "server2", "server3");
        LoadBalancer lb = new LoadBalancer(servers);
        System.out.println("Load Balancer:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Request " + (i + 1) + " -> " + lb.getServer());
        }
        
        // Test LRU Cache
        LRUCache cache = new LRUCache(3);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("\nLRU Cache:");
        System.out.println("Get key1: " + cache.get("key1"));
        cache.put("key4", "value4"); // This should evict key2
        System.out.println("Get key2: " + cache.get("key2")); // Should return null
        
        // Test Message Queue
        MessageQueue mq = new MessageQueue(5);
        mq.enqueue("message1");
        mq.enqueue("message2");
        System.out.println("\nMessage Queue:");
        System.out.println("Dequeue: " + mq.dequeue());
        System.out.println("Dequeue: " + mq.dequeue());
        
        // Explain CAP Theorem
        System.out.println("\n" + "=".repeat(50));
        CAPTheorem.explainCAP();
    }
}

/**
 * Top K Frequent Elements
 * 
 * Given an integer array nums and an integer k, return the k most frequent elements. 
 * You may return the answer in any order.
 * 
 * LeetCode: https://leetcode.com/problems/top-k-frequent-elements/
 * Difficulty: Medium
 * Tags: heap, bucket-sort
 * 
 * Approach:
 * - Min heap: Keep only k most frequent elements
 * - Bucket sort: Use frequency as index
 * - HashMap + sorting: Count frequencies and sort
 * 
 * Pseudocode:
 * Min Heap:
 * 1. Count frequencies using HashMap
 * 2. Create min heap of size k
 * 3. Add elements to heap
 * 4. If heap size > k, remove minimum
 * 5. Return elements from heap
 */

package masterDSA.topics.heaps.TopKFrequentElements.TopKFrequentElements;

import java.util.*;

public class TopKFrequentElements {
    
    /**
     * Min heap approach - O(n log k) time, O(n) space
     * Most efficient approach for this problem
     */
    public static int[] topKFrequent_minHeap(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create min heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );
        
        // Add elements to heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            
            // Keep only k most frequent elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Extract results
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }
        
        return result;
    }
    
    /**
     * Bucket sort approach - O(n) time, O(n) space
     * Alternative approach using bucket sort
     */
    public static int[] topKFrequent_bucketSort(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create buckets based on frequency
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Add numbers to buckets
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        
        // Extract top k frequent elements
        int[] result = new int[k];
        int index = 0;
        
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            for (int num : buckets[i]) {
                if (index < k) {
                    result[index++] = num;
                }
            }
        }
        
        return result;
    }
    
    /**
     * HashMap + sorting approach - O(n log n) time, O(n) space
     * Simple but less efficient
     */
    public static int[] topKFrequent_sorting(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Sort by frequency
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        
        // Extract top k
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }
        
        return result;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        
        System.out.println("Testing Top K Frequent Elements:");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        
        int[] result1 = topKFrequent_minHeap(nums, k);
        System.out.println("Min heap result: " + Arrays.toString(result1));
        
        int[] result2 = topKFrequent_bucketSort(nums, k);
        System.out.println("Bucket sort result: " + Arrays.toString(result2));
        
        int[] result3 = topKFrequent_sorting(nums, k);
        System.out.println("Sorting result: " + Arrays.toString(result3));
    }
}
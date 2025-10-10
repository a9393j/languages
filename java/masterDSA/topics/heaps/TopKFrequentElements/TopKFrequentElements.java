package masterDSA.topics.heaps.TopKFrequentElements;
import java.util.*;


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
 * - Min heap: Maintain heap of size k with frequency as key
 * - Max heap: Use max heap and pop k elements
 * - Bucket sort: Use frequency as index
 * - Quick select: Use partition on frequencies
 * 
 * Pseudocode:
 * Min Heap:
 * 1. Count frequencies of all elements
 * 2. Create min heap of size k
 * 3. For each frequency entry:
 *    a. If heap size < k, add entry
 *    b. Else if frequency > heap.peek(), replace top
 * 4. Return elements from heap
 */
public class TopKFrequentElements {
    
    /**
     * Min heap approach - O(n log k) time, O(n) space
     * Maintain a min heap of size k
     */
    public static int[] topKFrequent_minHeap(int[] nums, int k) {
        // Count frequencies
        java.util.Map<Integer, Integer> frequencyMap = new java.util.HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create min heap based on frequency
        java.util.PriorityQueue<java.util.Map.Entry<Integer, Integer>> minHeap = 
            new java.util.PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        
        for (java.util.Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        
        // Extract elements from heap
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }
        
        return result;
    }
    
    /**
     * Max heap approach - O(n log n) time, O(n) space
     * Use max heap and pop k elements
     */
    public static int[] topKFrequent_maxHeap(int[] nums, int k) {
        // Count frequencies
        java.util.Map<Integer, Integer> frequencyMap = new java.util.HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create max heap based on frequency
        java.util.PriorityQueue<java.util.Map.Entry<Integer, Integer>> maxHeap = 
            new java.util.PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        
        for (java.util.Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            maxHeap.offer(entry);
        }
        
        // Extract top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }
        
        return result;
    }
    
    /**
     * Bucket sort approach - O(n) time, O(n) space
     * Use frequency as index
     */
    public static int[] topKFrequent_bucketSort(int[] nums, int k) {
        // Count frequencies
        java.util.Map<Integer, Integer> frequencyMap = new java.util.HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create buckets
        java.util.List<java.util.List<Integer>> buckets = new java.util.ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new java.util.ArrayList<>());
        }
        
        // Add elements to buckets based on frequency
        for (java.util.Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }
        
        // Extract top k elements
        int[] result = new int[k];
        int index = 0;
        
        for (int i = buckets.size() - 1; i >= 0 && index < k; i--) {
            for (int num : buckets.get(i)) {
                if (index < k) {
                    result[index++] = num;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Quick select approach - O(n) average time, O(n) space
     * Use partition on frequencies
     */
    public static int[] topKFrequent_quickSelect(int[] nums, int k) {
        // Count frequencies
        java.util.Map<Integer, Integer> frequencyMap = new java.util.HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Convert to array
        java.util.Map.Entry<Integer, Integer>[] entries = 
            frequencyMap.entrySet().toArray(new java.util.Map.Entry[0]);
        
        // Quick select
        quickSelect(entries, 0, entries.length - 1, entries.length - k);
        
        // Extract top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries[entries.length - 1 - i].getKey();
        }
        
        return result;
    }
    
    private static void quickSelect(java.util.Map.Entry<Integer, Integer>[] entries, int left, int right, int k) {
        if (left == right) {
            return;
        }
        
        int pivotIndex = partition(entries, left, right);
        
        if (k == pivotIndex) {
            return;
        } else if (k < pivotIndex) {
            quickSelect(entries, left, pivotIndex - 1, k);
        } else {
            quickSelect(entries, pivotIndex + 1, right, k);
        }
    }
    
    private static int partition(java.util.Map.Entry<Integer, Integer>[] entries, int left, int right) {
        int pivot = entries[right].getValue();
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (entries[j].getValue() <= pivot) {
                swap(entries, i, j);
                i++;
            }
        }
        
        swap(entries, i, right);
        return i;
    }
    
    private static void swap(java.util.Map.Entry<Integer, Integer>[] entries, int i, int j) {
        java.util.Map.Entry<Integer, Integer> temp = entries[i];
        entries[i] = entries[j];
        entries[j] = temp;
    }
    
    /**
     * Helper method to print array
     */
    public static void printArray(int[] nums) {
        System.out.println(java.util.Arrays.toString(nums));
    }
    
    /**
     * Helper method to count frequencies
     */
    public static java.util.Map<Integer, Integer> countFrequencies(int[] nums) {
        java.util.Map<Integer, Integer> frequencyMap = new java.util.HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        return frequencyMap;
    }
    
    /**
     * Helper method to find most frequent element
     */
    public static int mostFrequent(int[] nums) {
        java.util.Map<Integer, Integer> frequencyMap = countFrequencies(nums);
        int maxFreq = 0;
        int mostFrequent = nums[0];
        
        for (java.util.Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        
        return mostFrequent;
    }
    
    /**
     * Helper method to find least frequent element
     */
    public static int leastFrequent(int[] nums) {
        java.util.Map<Integer, Integer> frequencyMap = countFrequencies(nums);
        int minFreq = Integer.MAX_VALUE;
        int leastFrequent = nums[0];
        
        for (java.util.Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() < minFreq) {
                minFreq = entry.getValue();
                leastFrequent = entry.getKey();
            }
        }
        
        return leastFrequent;
    }
    
    public static void main(String[] args) {
        // Test case: nums = [1,1,1,2,2,3], k = 2
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        
        System.out.println("Testing Top K Frequent Elements:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("k = " + k1);
        
        int[] result1 = topKFrequent_minHeap(nums1, k1);
        System.out.println("Min heap result: " + java.util.Arrays.toString(result1));
        
        int[] result2 = topKFrequent_maxHeap(nums1, k1);
        System.out.println("Max heap result: " + java.util.Arrays.toString(result2));
        
        int[] result3 = topKFrequent_bucketSort(nums1, k1);
        System.out.println("Bucket sort result: " + java.util.Arrays.toString(result3));
        
        int[] result4 = topKFrequent_quickSelect(nums1, k1);
        System.out.println("Quick select result: " + java.util.Arrays.toString(result4));
        
        // Expected: [1, 2] (or [2, 1])
        
        System.out.println();
        
        // Test case: nums = [1], k = 1
        int[] nums2 = {1};
        int k2 = 1;
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        System.out.println("k = " + k2);
        
        int[] result5 = topKFrequent_minHeap(nums2, k2);
        System.out.println("Min heap result: " + java.util.Arrays.toString(result5));
        
        // Expected: [1]
        
        System.out.println();
        
        // Test helper methods
        int[] nums3 = {1, 1, 1, 2, 2, 3};
        
        java.util.Map<Integer, Integer> frequencies = countFrequencies(nums3);
        System.out.println("Frequencies: " + frequencies);
        
        int mostFreq = mostFrequent(nums3);
        System.out.println("Most frequent: " + mostFreq);
        
        int leastFreq = leastFrequent(nums3);
        System.out.println("Least frequent: " + leastFreq);
        
        // Expected: Frequencies = {1=3, 2=2, 3=1}, Most frequent = 1, Least frequent = 3
    }
}

package masterDSA.topics.patterns_misc;
import java.util.*;


/**
 * Top K Elements Pattern
 * 
 * Problem Statement:
 * The top K elements pattern is used for problems involving finding the K largest,
 * smallest, most frequent, or least frequent elements in a dataset.
 * 
 * LeetCode Link: https://leetcode.com/tag/top-k/
 * Difficulty: Medium
 * Tags: Array, Heap, Sorting, Hash Table
 * 
 * Approach Summary:
 * 1. Use Min Heap for K largest elements
 * 2. Use Max Heap for K smallest elements
 * 3. Use Priority Queue with custom comparator
 * 4. Use Quick Select for O(n) average case
 * 5. Use Bucket Sort for frequency-based problems
 * 
 * Pseudocode:
 * 1. Create heap of size K
 * 2. Add elements to heap
 * 3. If heap size > K, remove root
 * 4. Return elements in heap
 * 
 * Time Complexity: O(n log k) - heap operations
 * Space Complexity: O(k) - heap storage
 */
public class TopKElementsPattern {
    
    /**
     * Find K largest elements using min heap
     * 
     * @param nums Array of integers
     * @param k Number of largest elements to find
     * @return List of K largest elements
     */
    public static List<Integer> findKLargest(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        // Min heap to maintain K largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            
            // Keep only K elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Convert heap to list
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        
        // Reverse to get largest to smallest order
        Collections.reverse(result);
        return result;
    }
    
    /**
     * Find K smallest elements using max heap
     * 
     * @param nums Array of integers
     * @param k Number of smallest elements to find
     * @return List of K smallest elements
     */
    public static List<Integer> findKSmallest(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        // Max heap to maintain K smallest elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int num : nums) {
            maxHeap.offer(num);
            
            // Keep only K elements
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        // Convert heap to list
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }
        
        // Reverse to get smallest to largest order
        Collections.reverse(result);
        return result;
    }
    
    /**
     * Find K most frequent elements
     * 
     * @param nums Array of integers
     * @param k Number of most frequent elements
     * @return List of K most frequent elements
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        // Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Min heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );
        
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Extract results
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        
        Collections.reverse(result);
        return result;
    }
    
    /**
     * Find K most frequent words
     * 
     * @param words Array of words
     * @param k Number of most frequent words
     * @return List of K most frequent words
     */
    public static List<String> topKFrequentWords(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return result;
        }
        
        // Count frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        
        // Min heap with custom comparator
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> {
                int freqCompare = a.getValue() - b.getValue();
                if (freqCompare == 0) {
                    return b.getKey().compareTo(a.getKey()); // Reverse alphabetical order
                }
                return freqCompare;
            }
        );
        
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Extract results
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        
        Collections.reverse(result);
        return result;
    }
    
    /**
     * Find K closest points to origin
     * 
     * @param points Array of 2D points
     * @param k Number of closest points
     * @return Array of K closest points
     */
    public static int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || k <= 0) {
            return new int[0][];
        }
        
        // Max heap based on distance from origin
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> {
                double distA = Math.sqrt(a[0] * a[0] + a[1] * a[1]);
                double distB = Math.sqrt(b[0] * b[0] + b[1] * b[1]);
                return Double.compare(distB, distA); // Reverse order for max heap
            }
        );
        
        for (int[] point : points) {
            maxHeap.offer(point);
            
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        // Convert heap to array
        int[][] result = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            result[index++] = maxHeap.poll();
        }
        
        return result;
    }
    
    /**
     * Find K largest elements using quick select
     * 
     * @param nums Array of integers
     * @param k Number of largest elements
     * @return List of K largest elements
     */
    public static List<Integer> findKLargestQuickSelect(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        int n = nums.length;
        int targetIndex = n - k; // Index of kth largest element
        
        quickSelect(nums, 0, n - 1, targetIndex);
        
        // Extract K largest elements
        for (int i = targetIndex; i < n; i++) {
            result.add(nums[i]);
        }
        
        return result;
    }
    
    /**
     * Quick select algorithm
     */
    private static void quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left >= right) {
            return;
        }
        
        int pivotIndex = partition(nums, left, right);
        
        if (pivotIndex == targetIndex) {
            return;
        } else if (pivotIndex < targetIndex) {
            quickSelect(nums, pivotIndex + 1, right, targetIndex);
        } else {
            quickSelect(nums, left, pivotIndex - 1, targetIndex);
        }
    }
    
    /**
     * Partition method for quick select
     */
    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        swap(nums, i, right);
        return i;
    }
    
    /**
     * Find K smallest elements using quick select
     * 
     * @param nums Array of integers
     * @param k Number of smallest elements
     * @return List of K smallest elements
     */
    public static List<Integer> findKSmallestQuickSelect(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        int n = nums.length;
        int targetIndex = k - 1; // Index of kth smallest element
        
        quickSelect(nums, 0, n - 1, targetIndex);
        
        // Extract K smallest elements
        for (int i = 0; i < k; i++) {
            result.add(nums[i]);
        }
        
        return result;
    }
    
    /**
     * Find K most frequent elements using bucket sort
     * 
     * @param nums Array of integers
     * @param k Number of most frequent elements
     * @return List of K most frequent elements
     */
    public static List<Integer> topKFrequentBucketSort(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
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
        
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        
        // Extract K most frequent elements
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            for (int num : buckets[i]) {
                if (result.size() < k) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Find K largest elements in stream
     * 
     * @param nums Array of integers
     * @param k Number of largest elements
     * @return List of K largest elements at each step
     */
    public static List<List<Integer>> findKLargestInStream(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            
            // Add current K largest elements to result
            List<Integer> currentKLargest = new ArrayList<>(minHeap);
            Collections.sort(currentKLargest, Collections.reverseOrder());
            result.add(currentKLargest);
        }
        
        return result;
    }
    
    /**
     * Helper method to swap elements
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Demonstration method showing various top K elements patterns
     */
    public static void runExample() {
        System.out.println("=== Top K Elements Pattern Examples ===");
        
        // Find K largest elements
        int[] nums1 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k1 = 3;
        List<Integer> kLargest = findKLargest(nums1, k1);
        System.out.println("K largest elements: " + kLargest);
        
        // Find K smallest elements
        int[] nums2 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k2 = 3;
        List<Integer> kSmallest = findKSmallest(nums2, k2);
        System.out.println("K smallest elements: " + kSmallest);
        
        // Find K most frequent elements
        int[] nums3 = {1, 1, 1, 2, 2, 3};
        int k3 = 2;
        List<Integer> kFrequent = topKFrequent(nums3, k3);
        System.out.println("K most frequent elements: " + kFrequent);
        
        // Find K most frequent words
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k4 = 2;
        List<String> kFrequentWords = topKFrequentWords(words, k4);
        System.out.println("K most frequent words: " + kFrequentWords);
        
        // Find K closest points
        int[][] points = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}};
        int k5 = 3;
        int[][] kClosest = kClosest(points, k5);
        System.out.println("K closest points: " + Arrays.deepToString(kClosest));
        
        // Find K largest using quick select
        int[] nums4 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k6 = 3;
        List<Integer> kLargestQuickSelect = findKLargestQuickSelect(nums4.clone(), k6);
        System.out.println("K largest (quick select): " + kLargestQuickSelect);
        
        // Find K smallest using quick select
        int[] nums5 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k7 = 3;
        List<Integer> kSmallestQuickSelect = findKSmallestQuickSelect(nums5.clone(), k7);
        System.out.println("K smallest (quick select): " + kSmallestQuickSelect);
        
        // Find K most frequent using bucket sort
        int[] nums6 = {1, 1, 1, 2, 2, 3};
        int k8 = 2;
        List<Integer> kFrequentBucket = topKFrequentBucketSort(nums6, k8);
        System.out.println("K most frequent (bucket sort): " + kFrequentBucket);
        
        // Find K largest in stream
        int[] nums7 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k9 = 3;
        List<List<Integer>> kLargestStream = findKLargestInStream(nums7, k9);
        System.out.println("K largest in stream:");
        for (int i = 0; i < kLargestStream.size(); i++) {
            System.out.println("After " + (i + 1) + " elements: " + kLargestStream.get(i));
        }
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

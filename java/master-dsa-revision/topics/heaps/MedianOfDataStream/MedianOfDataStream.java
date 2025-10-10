/**
 * Median of Data Stream
 * 
 * The median is the middle value in an ordered integer list. If the size of the list is even, 
 * there is no middle value and the median is the mean of the two middle values.
 * 
 * LeetCode: https://leetcode.com/problems/find-median-from-data-stream/
 * Difficulty: Hard
 * Tags: two-heaps, streaming
 * 
 * Approach:
 * - Use two heaps: max heap for smaller half, min heap for larger half
 * - Keep heaps balanced (size difference <= 1)
 * - Median is top of max heap or average of both tops
 * 
 * Pseudocode:
 * 1. Create max heap for smaller half and min heap for larger half
 * 2. For each number:
 *    a. Add to appropriate heap
 *    b. Balance heaps if necessary
 *    c. Calculate median based on heap sizes
 */

import java.util.*;

public class MedianOfDataStream {
    
    private PriorityQueue<Integer> maxHeap; // Smaller half
    private PriorityQueue<Integer> minHeap; // Larger half
    
    public MedianOfDataStream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    /**
     * Two heaps approach - O(log n) time per operation, O(n) space
     * Most efficient approach for this problem
     */
    public void addNum(int num) {
        // Add to max heap first
        maxHeap.offer(num);
        
        // Move largest element from max heap to min heap
        minHeap.offer(maxHeap.poll());
        
        // Balance heaps if necessary
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        MedianOfDataStream medianFinder = new MedianOfDataStream();
        
        System.out.println("Testing Median of Data Stream:");
        
        medianFinder.addNum(1);
        System.out.println("After adding 1, median: " + medianFinder.findMedian());
        
        medianFinder.addNum(2);
        System.out.println("After adding 2, median: " + medianFinder.findMedian());
        
        medianFinder.addNum(3);
        System.out.println("After adding 3, median: " + medianFinder.findMedian());
        
        medianFinder.addNum(4);
        System.out.println("After adding 4, median: " + medianFinder.findMedian());
        
        medianFinder.addNum(5);
        System.out.println("After adding 5, median: " + medianFinder.findMedian());
    }
}
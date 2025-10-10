package masterDSA.topics.heaps.MedianOfDataStream;
import java.util.*;


/**
 * Median of Data Stream
 * 
 * The median is the middle value in an ordered integer list. If the size of the list is even, 
 * there is no middle value, and the median is the mean of the two middle values.
 * 
 * LeetCode: https://leetcode.com/problems/find-median-from-data-stream/
 * Difficulty: Hard
 * Tags: two-heaps, streaming
 * 
 * Approach:
 * - Two heaps: Use max heap for smaller half and min heap for larger half
 * - Maintain balance between heaps
 * - Median is top of max heap or average of both tops
 * 
 * Pseudocode:
 * 1. Use max heap for smaller half and min heap for larger half
 * 2. For each number:
 *    a. Add to max heap
 *    b. Move top of max heap to min heap
 *    c. If min heap size > max heap size, move top back
 * 3. Median = max heap top (odd) or average of both tops (even)
 */
public class MedianOfDataStream {
    
    private java.util.PriorityQueue<Integer> maxHeap; // Smaller half
    private java.util.PriorityQueue<Integer> minHeap; // Larger half
    
    public MedianOfDataStream() {
        maxHeap = new java.util.PriorityQueue<>((a, b) -> b - a);
        minHeap = new java.util.PriorityQueue<>();
    }
    
    /**
     * Add number to data structure - O(log n) time, O(1) space
     * Maintain balance between two heaps
     */
    public void addNum(int num) {
        // Add to max heap (smaller half)
        maxHeap.offer(num);
        
        // Move top of max heap to min heap
        minHeap.offer(maxHeap.poll());
        
        // Balance heaps: max heap should have equal or one more element
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    /**
     * Find median - O(1) time, O(1) space
     * Return median based on heap sizes
     */
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
    
    /**
     * Two heaps approach - O(log n) add, O(1) find
     * Use max heap for smaller half and min heap for larger half
     */
    public static class MedianFinder {
        private java.util.PriorityQueue<Integer> maxHeap; // Smaller half
        private java.util.PriorityQueue<Integer> minHeap; // Larger half
        
        public MedianFinder() {
            maxHeap = new java.util.PriorityQueue<>((a, b) -> b - a);
            minHeap = new java.util.PriorityQueue<>();
        }
        
        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            
            // Balance heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
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
    }
    
    /**
     * Sorting approach - O(n log n) add, O(1) find
     * Sort array after each addition
     */
    public static class MedianFinderSorting {
        private java.util.List<Integer> nums;
        
        public MedianFinderSorting() {
            nums = new java.util.ArrayList<>();
        }
        
        public void addNum(int num) {
            nums.add(num);
            java.util.Collections.sort(nums);
        }
        
        public double findMedian() {
            int n = nums.size();
            if (n % 2 == 1) {
                return nums.get(n / 2);
            } else {
                return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2.0;
            }
        }
    }
    
    /**
     * Insertion sort approach - O(n) add, O(1) find
     * Insert element in correct position
     */
    public static class MedianFinderInsertion {
        private java.util.List<Integer> nums;
        
        public MedianFinderInsertion() {
            nums = new java.util.ArrayList<>();
        }
        
        public void addNum(int num) {
            int index = 0;
            while (index < nums.size() && nums.get(index) < num) {
                index++;
            }
            nums.add(index, num);
        }
        
        public double findMedian() {
            int n = nums.size();
            if (n % 2 == 1) {
                return nums.get(n / 2);
            } else {
                return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2.0;
            }
        }
    }
    
    /**
     * Helper method to test median finder
     */
    public static void testMedianFinder() {
        MedianFinder medianFinder = new MedianFinder();
        
        System.out.println("Testing Median Finder:");
        
        medianFinder.addNum(1);
        System.out.println("After adding 1: " + medianFinder.findMedian());
        
        medianFinder.addNum(2);
        System.out.println("After adding 2: " + medianFinder.findMedian());
        
        medianFinder.addNum(3);
        System.out.println("After adding 3: " + medianFinder.findMedian());
        
        medianFinder.addNum(4);
        System.out.println("After adding 4: " + medianFinder.findMedian());
        
        medianFinder.addNum(5);
        System.out.println("After adding 5: " + medianFinder.findMedian());
        
        // Expected: 1.0, 1.5, 2.0, 2.5, 3.0
    }
    
    /**
     * Helper method to find median of array
     */
    public static double findMedian(int[] nums) {
        java.util.Arrays.sort(nums);
        int n = nums.length;
        
        if (n % 2 == 1) {
            return nums[n / 2];
        } else {
            return (nums[n / 2 - 1] + nums[n / 2]) / 2.0;
        }
    }
    
    /**
     * Helper method to find median of two sorted arrays
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int left = 0;
        int right = m;
        
        while (left <= right) {
            int partitionX = (left + right) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;
            
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftX, maxLeftY);
                } else {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                }
            } else if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        // Test case: [1,2,3,4,5]
        System.out.println("Testing Median of Data Stream:");
        
        MedianFinder medianFinder = new MedianFinder();
        
        medianFinder.addNum(1);
        System.out.println("After adding 1: " + medianFinder.findMedian());
        
        medianFinder.addNum(2);
        System.out.println("After adding 2: " + medianFinder.findMedian());
        
        medianFinder.addNum(3);
        System.out.println("After adding 3: " + medianFinder.findMedian());
        
        medianFinder.addNum(4);
        System.out.println("After adding 4: " + medianFinder.findMedian());
        
        medianFinder.addNum(5);
        System.out.println("After adding 5: " + medianFinder.findMedian());
        
        // Expected: 1.0, 1.5, 2.0, 2.5, 3.0
        
        System.out.println();
        
        // Test sorting approach
        MedianFinderSorting medianFinderSorting = new MedianFinderSorting();
        
        medianFinderSorting.addNum(1);
        System.out.println("Sorting approach - After adding 1: " + medianFinderSorting.findMedian());
        
        medianFinderSorting.addNum(2);
        System.out.println("Sorting approach - After adding 2: " + medianFinderSorting.findMedian());
        
        System.out.println();
        
        // Test insertion sort approach
        MedianFinderInsertion medianFinderInsertion = new MedianFinderInsertion();
        
        medianFinderInsertion.addNum(1);
        System.out.println("Insertion approach - After adding 1: " + medianFinderInsertion.findMedian());
        
        medianFinderInsertion.addNum(2);
        System.out.println("Insertion approach - After adding 2: " + medianFinderInsertion.findMedian());
        
        System.out.println();
        
        // Test helper methods
        int[] nums = {1, 2, 3, 4, 5};
        double median = findMedian(nums);
        System.out.println("Median of array " + java.util.Arrays.toString(nums) + ": " + median);
        
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double medianSorted = findMedianSortedArrays(nums1, nums2);
        System.out.println("Median of sorted arrays: " + medianSorted);
        
        // Expected: Median of array = 3.0, Median of sorted arrays = 2.0
    }
}

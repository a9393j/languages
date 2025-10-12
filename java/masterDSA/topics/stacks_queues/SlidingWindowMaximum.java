package masterDSA.topics.stacks_queues;
import java.util.*;


/**
 * Sliding Window Maximum
 * 
 * You are given an array of integers nums, there is a sliding window of size k 
 * which is moving from the very left of the array to the very right. You can only 
 * see the k numbers in the window. Each time the sliding window moves right by one position.
 * 
 * LeetCode: https://leetcode.com/problems/sliding-window-maximum/
 * Difficulty: Hard
 * Tags: deque, monotonic-queue
 * 
 * Approach:
 * - Use deque to maintain decreasing order
 * - Remove elements outside window
 * - Remove elements smaller than current element
 * - Deque front always contains maximum element
 * 
 * Pseudocode:
 * 1. Create deque to store indices
 * 2. For each element:
 *    a. Remove indices outside current window
 *    b. Remove indices with smaller values
 *    c. Add current index to deque
 *    d. If window is complete, add max to result
 * 3. Return result array
 */
public class SlidingWindowMaximum {
    
    /**
     * Deque approach - O(n) time, O(k) space
     * Use deque to maintain decreasing order
     */
    public static int[] maxSlidingWindow_deque(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new java.util.ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            // Remove indices outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove indices with smaller values
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            
            // Add current index
            deque.offerLast(i);
            
            // If window is complete, add max to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * Two pointers approach - O(n) time, O(1) space
     * Use two pointers to track maximum in window
     */
    public static int[] maxSlidingWindow_twoPointers(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        int maxIndex = 0;
        for (int i = 1; i < k; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        result[0] = nums[maxIndex];
        
        for (int i = k; i < n; i++) {
            if (maxIndex >= i - k + 1) {
                // Previous max is still in window
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            } else {
                // Previous max is outside window, find new max
                maxIndex = i - k + 1;
                for (int j = i - k + 2; j <= i; j++) {
                    if (nums[j] > nums[maxIndex]) {
                        maxIndex = j;
                    }
                }
            }
            result[i - k + 1] = nums[maxIndex];
        }
        
        return result;
    }
    
    /**
     * Brute force approach - O(nk) time, O(1) space
     * For each window, find maximum element
     */
    public static int[] maxSlidingWindow_bruteForce(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        
        return result;
    }
    
    /**
     * Priority queue approach - O(n log k) time, O(k) space
     * Use priority queue to maintain maximum element
     */
    public static int[] maxSlidingWindow_priorityQueue(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }
        
        result[0] = maxHeap.peek();
        
        for (int i = k; i < n; i++) {
            maxHeap.remove(nums[i - k]);
            maxHeap.offer(nums[i]);
            result[i - k + 1] = maxHeap.peek();
        }
        
        return result;
    }
    
    /**
     * Helper method to find minimum in sliding window
     */
    public static int[] minSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new java.util.ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            // Remove indices outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove indices with larger values
            while (!deque.isEmpty() && nums[deque.peekLast()] >= nums[i]) {
                deque.pollLast();
            }
            
            // Add current index
            deque.offerLast(i);
            
            // If window is complete, add min to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find maximum in sliding window of size 1
     */
    public static int[] maxSlidingWindowSize1(int[] nums) {
        return nums.clone();
    }
    
    /**
     * Helper method to find maximum in sliding window of size n
     */
    public static int[] maxSlidingWindowSizeN(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        return new int[]{max};
    }
    
    /**
     * Helper method to find sum of sliding window
     */
    public static int[] sumSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        result[0] = sum;
        
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            result[i - k + 1] = sum;
        }
        
        return result;
    }
    
    /**
     * Helper method to find average of sliding window
     */
    public static double[] averageSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new double[0];
        }
        
        int n = nums.length;
        double[] result = new double[n - k + 1];
        
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        result[0] = (double) sum / k;
        
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            result[i - k + 1] = (double) sum / k;
        }
        
        return result;
    }
    
    /**
     * Helper method to print array
     */
    public static void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
    
    public static void main(String[] args) {
        // Test case: nums = [1,3,-1,-3,5,3,6,7], k = 3
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        
        System.out.println("Testing Sliding Window Maximum:");
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Window size: " + k1);
        
        int[] result1 = maxSlidingWindow_deque(nums1, k1);
        System.out.println("Deque result: " + Arrays.toString(result1));
        
        int[] result2 = maxSlidingWindow_twoPointers(nums1, k1);
        System.out.println("Two pointers result: " + Arrays.toString(result2));
        
        int[] result3 = maxSlidingWindow_bruteForce(nums1, k1);
        System.out.println("Brute force result: " + Arrays.toString(result3));
        
        int[] result4 = maxSlidingWindow_priorityQueue(nums1, k1);
        System.out.println("Priority queue result: " + Arrays.toString(result4));
        
        // Expected: [3,3,5,5,6,7]
        
        System.out.println();
        
        // Test case: nums = [1], k = 1
        int[] nums2 = {1};
        int k2 = 1;
        
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Window size: " + k2);
        
        int[] result5 = maxSlidingWindow_deque(nums2, k2);
        System.out.println("Deque result: " + Arrays.toString(result5));
        
        // Expected: [1]
        
        System.out.println();
        
        // Test case: nums = [1,-1], k = 1
        int[] nums3 = {1, -1};
        int k3 = 1;
        
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("Window size: " + k3);
        
        int[] result6 = maxSlidingWindow_deque(nums3, k3);
        System.out.println("Deque result: " + Arrays.toString(result6));
        
        // Expected: [1,-1]
        
        System.out.println();
        
        // Test helper methods
        int[] nums4 = {1, 3, -1, -3, 5, 3, 6, 7};
        
        int[] minWindow = minSlidingWindow(nums4, 3);
        System.out.println("Minimum sliding window: " + Arrays.toString(minWindow));
        
        int[] sumWindow = sumSlidingWindow(nums4, 3);
        System.out.println("Sum sliding window: " + Arrays.toString(sumWindow));
        
        double[] avgWindow = averageSlidingWindow(nums4, 3);
        System.out.println("Average sliding window: " + Arrays.toString(avgWindow));
        
        // Expected: Min = [-1,-3,-3,-3,3,3], Sum = [3,5,1,5,14,16], Average = [1.0,1.67,0.33,1.67,4.67,5.33]
    }
}

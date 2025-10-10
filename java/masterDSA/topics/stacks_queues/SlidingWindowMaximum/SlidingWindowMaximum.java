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
 * - Add current element to deque
 * 
 * Pseudocode:
 * 1. Create deque to store indices
 * 2. For each element:
 *    a. Remove indices outside window
 *    b. Remove indices with smaller values
 *    c. Add current index to deque
 *    d. If window is complete, add maximum to result
 */

package masterDSA.topics.stacks_queues.SlidingWindowMaximum.SlidingWindowMaximum;

import java.util.*;

public class SlidingWindowMaximum {
    
    /**
     * Deque approach - O(n) time, O(k) space
     * Most efficient approach for this problem
     */
    public static int[] maxSlidingWindow_deque(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            // Remove indices outside window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove indices with smaller values
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            // Add current index to deque
            deque.offerLast(i);
            
            // If window is complete, add maximum to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * Brute force approach - O(n*k) time, O(1) space
     * Less efficient but easy to understand
     */
    public static int[] maxSlidingWindow_bruteForce(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        
        return result;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        
        System.out.println("Testing Sliding Window Maximum:");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Window size: " + k);
        
        int[] result1 = maxSlidingWindow_deque(nums, k);
        System.out.println("Deque result: " + Arrays.toString(result1));
        
        int[] result2 = maxSlidingWindow_bruteForce(nums, k);
        System.out.println("Brute force result: " + Arrays.toString(result2));
    }
}
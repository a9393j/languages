/**
 * Largest Rectangle in Histogram
 * 
 * Given an array of integers heights representing the histogram's bar height where the 
 * width of each bar is 1, return the area of the largest rectangle in the histogram.
 * 
 * LeetCode: https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Difficulty: Hard
 * Tags: monotonic-stack
 * 
 * Approach:
 * - Use monotonic stack to find largest rectangle
 * - For each bar, find left and right boundaries
 * - Calculate area for each bar
 * 
 * Pseudocode:
 * 1. Use stack to store indices
 * 2. For each bar:
 *    a. While stack is not empty and current bar < stack top:
 *       - Pop index from stack
 *       - Calculate area
 *       - Update maximum area
 *    b. Push current index to stack
 * 3. Process remaining bars in stack
 */

package masterDSA.topics.patterns_misc.LargestRectangleInHistogram.LargestRectangleInHistogram;

import java.util.*;

public class LargestRectangleInHistogram {
    
    /**
     * Monotonic stack approach - O(n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static int largestRectangleArea_monotonicStack(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            int currentHeight = (i == heights.length) ? 0 : heights[i];
            
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
    
    /**
     * Alternative approach using arrays - O(n) time, O(n) space
     * Pre-calculate left and right boundaries
     */
    public static int largestRectangleArea_arrays(int[] heights) {
        int n = heights.length;
        int[] leftBoundary = new int[n];
        int[] rightBoundary = new int[n];
        
        // Calculate left boundaries
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            leftBoundary[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        // Calculate right boundaries
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            rightBoundary[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        // Calculate maximum area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = rightBoundary[i] - leftBoundary[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        
        return maxArea;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        int[] heights2 = {2, 4};
        int[] heights3 = {1, 1};
        
        System.out.println("Testing Largest Rectangle in Histogram:");
        
        System.out.println("Heights: " + Arrays.toString(heights1));
        System.out.println("Monotonic stack result: " + largestRectangleArea_monotonicStack(heights1));
        System.out.println("Arrays result: " + largestRectangleArea_arrays(heights1));
        
        System.out.println("Heights: " + Arrays.toString(heights2));
        System.out.println("Monotonic stack result: " + largestRectangleArea_monotonicStack(heights2));
        
        System.out.println("Heights: " + Arrays.toString(heights3));
        System.out.println("Monotonic stack result: " + largestRectangleArea_monotonicStack(heights3));
    }
}
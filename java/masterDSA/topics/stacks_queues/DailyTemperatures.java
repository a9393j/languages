package masterDSA.topics.stacks_queues;
import java.util.*;


/**
 * Daily Temperatures
 * 
 * Given an array of integers temperatures representing the daily temperatures, 
 * return an array answer such that answer[i] is the number of days you have to wait 
 * after the ith day to get a warmer temperature. If there is no future day for which 
 * this is possible, keep answer[i] == 0 instead.
 * 
 * LeetCode: https://leetcode.com/problems/daily-temperatures/
 * Difficulty: Medium
 * Tags: monotonic-stack
 * 
 * Approach:
 * - Use monotonic stack to find next greater element
 * - Stack stores indices of temperatures
 * - For each temperature, pop elements with smaller temperatures
 * - Calculate difference in indices
 * 
 * Pseudocode:
 * 1. Create stack to store indices
 * 2. For each temperature:
 *    a. While stack is not empty and current temp > stack top temp:
 *       - Pop index from stack
 *       - Set answer[index] = current index - popped index
 *    b. Push current index to stack
 * 3. Return answer array
 */
public class DailyTemperatures {
    
    /**
     * Monotonic stack approach - O(n) time, O(n) space
     * Use stack to find next greater element
     */
    public static int[] dailyTemperatures_stack(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // Pop elements with smaller temperatures
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            
            // Push current index
            stack.push(i);
        }
        
        return answer;
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * For each day, find next warmer day
     */
    public static int[] dailyTemperatures_bruteForce(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    /**
     * Array approach - O(n) time, O(1) space
     * Use array to simulate stack
     */
    public static int[] dailyTemperatures_array(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        int[] stack = new int[n];
        int top = -1;
        
        for (int i = 0; i < n; i++) {
            // Pop elements with smaller temperatures
            while (top >= 0 && temperatures[i] > temperatures[stack[top]]) {
                int index = stack[top--];
                answer[index] = i - index;
            }
            
            // Push current index
            stack[++top] = i;
        }
        
        return answer;
    }
    
    /**
     * Helper method to find next greater element
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int index = stack.pop();
                result[index] = nums[i];
            }
            stack.push(i);
        }
        
        // Remaining elements have no next greater element
        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }
        
        return result;
    }
    
    /**
     * Helper method to find next smaller element
     */
    public static int[] nextSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int index = stack.pop();
                result[index] = nums[i];
            }
            stack.push(i);
        }
        
        // Remaining elements have no next smaller element
        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }
        
        return result;
    }
    
    /**
     * Helper method to find previous greater element
     */
    public static int[] previousGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int index = stack.pop();
                result[index] = nums[i];
            }
            stack.push(i);
        }
        
        // Remaining elements have no previous greater element
        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }
        
        return result;
    }
    
    /**
     * Helper method to find previous smaller element
     */
    public static int[] previousSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int index = stack.pop();
                result[index] = nums[i];
            }
            stack.push(i);
        }
        
        // Remaining elements have no previous smaller element
        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }
        
        return result;
    }
    
    /**
     * Helper method to print array
     */
    public static void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
    
    /**
     * Helper method to find largest rectangle in histogram
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        // Test case: temperatures = [73,74,75,71,69,72,76,73]
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        
        System.out.println("Testing Daily Temperatures:");
        System.out.println("Temperatures: " + Arrays.toString(temperatures));
        
        int[] result1 = dailyTemperatures_stack(temperatures);
        System.out.println("Stack result: " + Arrays.toString(result1));
        
        int[] result2 = dailyTemperatures_bruteForce(temperatures);
        System.out.println("Brute force result: " + Arrays.toString(result2));
        
        int[] result3 = dailyTemperatures_array(temperatures);
        System.out.println("Array result: " + Arrays.toString(result3));
        
        // Expected: [1,1,4,2,1,1,0,0]
        
        System.out.println();
        
        // Test case: temperatures = [30,40,50,60]
        int[] temperatures2 = {30, 40, 50, 60};
        
        System.out.println("Temperatures: " + Arrays.toString(temperatures2));
        
        int[] result4 = dailyTemperatures_stack(temperatures2);
        System.out.println("Stack result: " + Arrays.toString(result4));
        
        // Expected: [1,1,1,0]
        
        System.out.println();
        
        // Test case: temperatures = [30,60,90]
        int[] temperatures3 = {30, 60, 90};
        
        System.out.println("Temperatures: " + Arrays.toString(temperatures3));
        
        int[] result5 = dailyTemperatures_stack(temperatures3);
        System.out.println("Stack result: " + Arrays.toString(result5));
        
        // Expected: [1,1,0]
        
        System.out.println();
        
        // Test helper methods
        int[] nums = {2, 1, 2, 4, 3, 1};
        
        int[] nextGreater = nextGreaterElement(nums);
        System.out.println("Next greater elements: " + Arrays.toString(nextGreater));
        
        int[] nextSmaller = nextSmallerElement(nums);
        System.out.println("Next smaller elements: " + Arrays.toString(nextSmaller));
        
        int[] prevGreater = previousGreaterElement(nums);
        System.out.println("Previous greater elements: " + Arrays.toString(prevGreater));
        
        int[] prevSmaller = previousSmallerElement(nums);
        System.out.println("Previous smaller elements: " + Arrays.toString(prevSmaller));
        
        // Test largest rectangle in histogram
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleArea(heights);
        System.out.println("Largest rectangle area: " + maxArea);
        
        // Expected: Next greater = [4,2,4,-1,-1,-1], Next smaller = [1,-1,1,3,1,-1], etc.
        // Largest rectangle area = 10
    }
}

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
 * - Use monotonic stack to track indices
 * - For each temperature, pop indices with smaller temperatures
 * - Calculate days difference and store in result
 * 
 * Pseudocode:
 * 1. Create stack to store indices
 * 2. For each temperature:
 *    a. While stack is not empty and current temperature > stack top temperature:
 *       - Pop index from stack
 *       - Calculate days difference
 *       - Store in result
 *    b. Push current index to stack
 * 3. Return result
 */

import java.util.*;

public class DailyTemperatures {
    
    /**
     * Monotonic stack approach - O(n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static int[] dailyTemperatures_monotonicStack(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // While stack is not empty and current temperature > stack top temperature
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index; // Calculate days difference
            }
            
            // Push current index to stack
            stack.push(i);
        }
        
        return result;
    }
    
    /**
     * Brute force approach - O(n^2) time, O(1) space
     * Less efficient but easy to understand
     */
    public static int[] dailyTemperatures_bruteForce(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        
        System.out.println("Testing Daily Temperatures:");
        System.out.println("Temperatures: " + Arrays.toString(temperatures));
        
        int[] result1 = dailyTemperatures_monotonicStack(temperatures);
        System.out.println("Monotonic stack result: " + Arrays.toString(result1));
        
        int[] result2 = dailyTemperatures_bruteForce(temperatures);
        System.out.println("Brute force result: " + Arrays.toString(result2));
    }
}
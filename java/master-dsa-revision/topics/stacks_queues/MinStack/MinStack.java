/**
 * Min Stack
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * LeetCode: https://leetcode.com/problems/min-stack/
 * Difficulty: Easy
 * Tags: stack, auxiliary-stack
 * 
 * Approach:
 * - Use auxiliary stack to track minimum elements
 * - For each push, push minimum of current element and previous minimum
 * - For each pop, pop from both stacks
 * 
 * Pseudocode:
 * 1. Create main stack and min stack
 * 2. Push: push to main stack, push minimum to min stack
 * 3. Pop: pop from both stacks
 * 4. Top: return top of main stack
 * 5. GetMin: return top of min stack
 */

import java.util.*;

public class MinStack {
    
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    /**
     * Auxiliary stack approach - O(1) time per operation, O(n) space
     * Most efficient approach for this problem
     */
    public void push(int val) {
        stack.push(val);
        
        // Push minimum of current element and previous minimum
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        
        System.out.println("Testing Min Stack:");
        
        minStack.push(-2);
        System.out.println("After pushing -2, min: " + minStack.getMin());
        
        minStack.push(0);
        System.out.println("After pushing 0, min: " + minStack.getMin());
        
        minStack.push(-3);
        System.out.println("After pushing -3, min: " + minStack.getMin());
        
        minStack.pop();
        System.out.println("After popping, top: " + minStack.top());
        System.out.println("After popping, min: " + minStack.getMin());
    }
}
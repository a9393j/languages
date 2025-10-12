package masterDSA.topics.stacks_queues;

/**
 * Min Stack
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element 
 * in constant time.
 * 
 * LeetCode: https://leetcode.com/problems/min-stack/
 * Difficulty: Medium
 * Tags: stack, design
 * 
 * Approach:
 * - Use two stacks: one for elements, one for minimums
 * - When pushing, also push minimum to min stack
 * - When popping, also pop from min stack
 * - Top of min stack always contains current minimum
 * 
 * Pseudocode:
 * 1. Create two stacks: main stack and min stack
 * 2. Push: Add element to main stack, add min(current, minStack.top) to min stack
 * 3. Pop: Remove from both stacks
 * 4. Top: Return top of main stack
 * 5. GetMin: Return top of min stack
 */
public class MinStack {
    
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    /**
     * Push element onto stack - O(1) time, O(1) space
     * Also maintain minimum in min stack
     */
    public void push(int val) {
        stack.push(val);
        
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }
    
    /**
     * Pop element from stack - O(1) time, O(1) space
     * Also pop from min stack
     */
    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop();
        }
    }
    
    /**
     * Get top element - O(1) time, O(1) space
     * Return top of main stack
     */
    public int top() {
        return stack.peek();
    }
    
    /**
     * Get minimum element - O(1) time, O(1) space
     * Return top of min stack
     */
    public int getMin() {
        return minStack.peek();
    }
    
    /**
     * Two stacks approach - O(1) all operations
     * Use two stacks: one for elements, one for minimums
     */
    public static class MinStackTwoStacks {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;
        
        public MinStackTwoStacks() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        public void pop() {
            if (!stack.isEmpty()) {
                int val = stack.pop();
                if (val == minStack.peek()) {
                    minStack.pop();
                }
            }
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }
    
    /**
     * Single stack approach - O(1) all operations
     * Store pairs of (value, minimum) in single stack
     */
    public static class MinStackSingle {
        private Stack<int[]> stack;
        
        public MinStackSingle() {
            stack = new Stack<>();
        }
        
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(new int[]{val, val});
            } else {
                int min = Math.min(val, stack.peek()[1]);
                stack.push(new int[]{val, min});
            }
        }
        
        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        
        public int top() {
            return stack.peek()[0];
        }
        
        public int getMin() {
            return stack.peek()[1];
        }
    }
    
    /**
     * Array approach - O(1) all operations
     * Use array to simulate stack
     */
    public static class MinStackArray {
        private int[] stack;
        private int[] minStack;
        private int top;
        private int minTop;
        
        public MinStackArray() {
            stack = new int[10000];
            minStack = new int[10000];
            top = -1;
            minTop = -1;
        }
        
        public void push(int val) {
            stack[++top] = val;
            
            if (minTop == -1 || val <= minStack[minTop]) {
                minStack[++minTop] = val;
            }
        }
        
        public void pop() {
            if (top >= 0) {
                int val = stack[top--];
                if (val == minStack[minTop]) {
                    minTop--;
                }
            }
        }
        
        public int top() {
            return stack[top];
        }
        
        public int getMin() {
            return minStack[minTop];
        }
    }
    
    /**
     * Helper method to test min stack
     */
    public static void testMinStack() {
        MinStack minStack = new MinStack();
        
        System.out.println("Testing Min Stack:");
        
        minStack.push(-2);
        System.out.println("After pushing -2: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        minStack.push(0);
        System.out.println("After pushing 0: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        minStack.push(-3);
        System.out.println("After pushing -3: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        minStack.pop();
        System.out.println("After popping: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        // Expected: top=-2, min=-2; top=0, min=-2; top=-3, min=-3; top=0, min=-2
    }
    
    /**
     * Helper method to find minimum in array
     */
    public static int findMin(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        return min;
    }
    
    /**
     * Helper method to find maximum in array
     */
    public static int findMax(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
    
    /**
     * Helper method to find second minimum
     */
    public static int findSecondMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        
        for (int num : nums) {
            if (num < min) {
                secondMin = min;
                min = num;
            } else if (num < secondMin && num != min) {
                secondMin = num;
            }
        }
        
        return secondMin == Integer.MAX_VALUE ? -1 : secondMin;
    }
    
    /**
     * Helper method to find second maximum
     */
    public static int findSecondMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        
        for (int num : nums) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num != max) {
                secondMax = num;
            }
        }
        
        return secondMax == Integer.MIN_VALUE ? -1 : secondMax;
    }
    
    public static void main(String[] args) {
        // Test case: push(-2), push(0), push(-3), pop(), top(), getMin()
        System.out.println("Testing Min Stack:");
        
        MinStack minStack = new MinStack();
        
        minStack.push(-2);
        System.out.println("After pushing -2: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        minStack.push(0);
        System.out.println("After pushing 0: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        minStack.push(-3);
        System.out.println("After pushing -3: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        minStack.pop();
        System.out.println("After popping: top=" + minStack.top() + ", min=" + minStack.getMin());
        
        // Expected: top=-2, min=-2; top=0, min=-2; top=-3, min=-3; top=0, min=-2
        
        System.out.println();
        
        // Test two stacks approach
        MinStackTwoStacks minStack2 = new MinStackTwoStacks();
        
        minStack2.push(-2);
        System.out.println("Two stacks - After pushing -2: top=" + minStack2.top() + ", min=" + minStack2.getMin());
        
        minStack2.push(0);
        System.out.println("Two stacks - After pushing 0: top=" + minStack2.top() + ", min=" + minStack2.getMin());
        
        System.out.println();
        
        // Test single stack approach
        MinStackSingle minStack3 = new MinStackSingle();
        
        minStack3.push(-2);
        System.out.println("Single stack - After pushing -2: top=" + minStack3.top() + ", min=" + minStack3.getMin());
        
        minStack3.push(0);
        System.out.println("Single stack - After pushing 0: top=" + minStack3.top() + ", min=" + minStack3.getMin());
        
        System.out.println();
        
        // Test array approach
        MinStackArray minStack4 = new MinStackArray();
        
        minStack4.push(-2);
        System.out.println("Array - After pushing -2: top=" + minStack4.top() + ", min=" + minStack4.getMin());
        
        minStack4.push(0);
        System.out.println("Array - After pushing 0: top=" + minStack4.top() + ", min=" + minStack4.getMin());
        
        System.out.println();
        
        // Test helper methods
        int[] nums = {3, 1, 4, 1, 5, 9, 2, 6};
        
        int min = findMin(nums);
        System.out.println("Minimum in array: " + min);
        
        int max = findMax(nums);
        System.out.println("Maximum in array: " + max);
        
        int secondMin = findSecondMin(nums);
        System.out.println("Second minimum: " + secondMin);
        
        int secondMax = findSecondMax(nums);
        System.out.println("Second maximum: " + secondMax);
        
        // Expected: Min = 1, Max = 9, Second min = 1, Second max = 6
    }
}

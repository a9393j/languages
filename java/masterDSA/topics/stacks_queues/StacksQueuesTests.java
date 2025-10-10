package masterDSA.topics.stacks_queues;

import masterDSA.topics.stacks_queues.ValidParentheses.ValidParentheses;
import masterDSA.topics.stacks_queues.DailyTemperatures.DailyTemperatures;
import masterDSA.topics.stacks_queues.SlidingWindowMaximum.SlidingWindowMaximum;
import masterDSA.topics.stacks_queues.MinStack.MinStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for stack and queue problems
 * Tests representative methods from each stack/queue problem
 */
public class StacksQueuesTests {
    
    @Test
    public void testValidParentheses() {
        assertTrue(ValidParentheses.isValid_stack("()"));
        assertTrue(ValidParentheses.isValid_stack("()[]{}"));
        assertFalse(ValidParentheses.isValid_stack("(]"));
        assertFalse(ValidParentheses.isValid_stack("([)]"));
    }
    
    @Test
    public void testDailyTemperatures() {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = DailyTemperatures.dailyTemperatures_stack(temperatures);
        int[] expected = {1, 1, 4, 2, 1, 1, 0, 0};
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testSlidingWindowMaximum() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = SlidingWindowMaximum.maxSlidingWindow_deque(nums, 3);
        int[] expected = {3, 3, 5, 5, 6, 7};
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testMinStack() {
        MinStack minStack = new MinStack();
        
        minStack.push(-2);
        assertEquals(-2, minStack.top());
        assertEquals(-2, minStack.getMin());
        
        minStack.push(0);
        assertEquals(0, minStack.top());
        assertEquals(-2, minStack.getMin());
        
        minStack.push(-3);
        assertEquals(-3, minStack.top());
        assertEquals(-3, minStack.getMin());
        
        minStack.pop();
        assertEquals(0, minStack.top());
        assertEquals(-2, minStack.getMin());
    }
}

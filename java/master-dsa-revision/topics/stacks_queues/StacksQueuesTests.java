import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Stacks and Queues topic problems
 */
public class StacksQueuesTests {
    
    @Test
    public void testValidParentheses() {
        String s = "()[]{}";
        boolean result = ValidParentheses.isValid_stack(s);
        assertTrue(result);
        
        String s2 = "([)]";
        boolean result2 = ValidParentheses.isValid_stack(s2);
        assertFalse(result2);
    }
    
    @Test
    public void testDailyTemperatures() {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = DailyTemperatures.dailyTemperatures_monotonicStack(temperatures);
        assertEquals(8, result.length);
        assertEquals(1, result[0]);
        assertEquals(1, result[1]);
        assertEquals(4, result[2]);
        assertEquals(2, result[3]);
        assertEquals(1, result[4]);
        assertEquals(1, result[5]);
        assertEquals(0, result[6]);
        assertEquals(0, result[7]);
    }
    
    @Test
    public void testSlidingWindowMaximum() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = SlidingWindowMaximum.maxSlidingWindow_deque(nums, k);
        assertEquals(6, result.length);
        assertEquals(3, result[0]);
        assertEquals(3, result[1]);
        assertEquals(5, result[2]);
        assertEquals(5, result[3]);
        assertEquals(6, result[4]);
        assertEquals(7, result[5]);
    }
    
    @Test
    public void testMinStack() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin());
        
        minStack.pop();
        assertEquals(0, minStack.top());
        assertEquals(-2, minStack.getMin());
    }
}

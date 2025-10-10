package masterDSA.topics.greedy;

import masterDSA.topics.greedy.JumpGame.JumpGame;
import masterDSA.topics.greedy.JumpGameII.JumpGameII;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for greedy problems
 * Tests representative methods from each greedy problem
 */
public class GreedyTests {
    
    @Test
    public void testJumpGame() {
        int[] nums = {2, 3, 1, 1, 4};
        boolean result = JumpGame.canJump_greedy(nums);
        assertTrue(result);
    }
    
    @Test
    public void testJumpGameII() {
        int[] nums = {2, 3, 1, 1, 4};
        int result = JumpGameII.jump_greedy(nums);
        assertEquals(2, result);
    }
    
    @Test
    public void testJumpGameCannotReach() {
        int[] nums = {3, 2, 1, 0, 4};
        boolean result = JumpGame.canJump_greedy(nums);
        assertFalse(result);
    }
    
    @Test
    public void testJumpGameIIMinimumJumps() {
        int[] nums = {2, 3, 0, 1, 4};
        int result = JumpGameII.jump_greedy(nums);
        assertEquals(2, result);
    }
}

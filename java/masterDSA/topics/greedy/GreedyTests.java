package masterDSA.topics.greedy.GreedyTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Greedy topic problems
 */
public class GreedyTests {
    
    @Test
    public void testJumpGame() {
        int[] nums = {2, 3, 1, 1, 4};
        boolean result = JumpGame.canJump_greedy(nums);
        assertTrue(result);
        
        int[] nums2 = {3, 2, 1, 0, 4};
        boolean result2 = JumpGame.canJump_greedy(nums2);
        assertFalse(result2);
    }
    
    @Test
    public void testActivitySelectionMeetingRooms() {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        boolean result = ActivitySelectionMeetingRooms.canAttendMeetings_sorting(intervals);
        assertFalse(result);
        
        int[][] intervals2 = {{7, 10}, {2, 4}};
        boolean result2 = ActivitySelectionMeetingRooms.canAttendMeetings_sorting(intervals2);
        assertTrue(result2);
    }
}
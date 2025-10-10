package masterDSA.topics.dynamic_programming.DynamicProgrammingTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Dynamic Programming topic problems
 */
public class DynamicProgrammingTests {
    
    @Test
    public void testCoinChange() {
        int[] coins = {1, 3, 4};
        int amount = 6;
        int result = CoinChange.coinChange_bottomUp(coins, amount);
        assertEquals(2, result);
    }
    
    @Test
    public void testLongestIncreasingSubsequence() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int result = LongestIncreasingSubsequence.lengthOfLIS_dp(nums);
        assertEquals(4, result);
    }
    
    @Test
    public void testEditDistance() {
        String word1 = "horse";
        String word2 = "ros";
        int result = EditDistance.minDistance_bottomUp(word1, word2);
        assertEquals(3, result);
    }
    
    @Test
    public void testHouseRobber() {
        int[] nums = {1, 2, 3, 1};
        int result = HouseRobber.rob_dp(nums);
        assertEquals(4, result);
    }
    
    @Test
    public void testPartitionEqualSubsetSum() {
        int[] nums = {1, 5, 11, 5};
        boolean result = PartitionEqualSubsetSum.canPartition_dp(nums);
        assertTrue(result);
    }
}

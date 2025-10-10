package masterDSA.topics.dynamic_programming;

import masterDSA.topics.dynamic_programming.CoinChange.CoinChange;
import masterDSA.topics.dynamic_programming.LongestIncreasingSubsequence.LongestIncreasingSubsequence;
import masterDSA.topics.dynamic_programming.LongestCommonSubsequence.LongestCommonSubsequence;
import masterDSA.topics.dynamic_programming.EditDistance.EditDistance;
import masterDSA.topics.dynamic_programming.HouseRobber.HouseRobber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for dynamic programming problems
 * Tests representative methods from each DP problem
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
    public void testLongestCommonSubsequence() {
        String text1 = "abcde";
        String text2 = "ace";
        int result = LongestCommonSubsequence.longestCommonSubsequence_2d(text1, text2);
        assertEquals(3, result);
    }
    
    @Test
    public void testEditDistance() {
        String word1 = "horse";
        String word2 = "ros";
        int result = EditDistance.minDistance_2d(word1, word2);
        assertEquals(3, result);
    }
    
    @Test
    public void testHouseRobber() {
        int[] nums = {1, 2, 3, 1};
        int result = HouseRobber.rob_dp(nums);
        assertEquals(4, result);
    }
    
    @Test
    public void testCoinChangeImpossible() {
        int[] coins = {2};
        int amount = 3;
        int result = CoinChange.coinChange_bottomUp(coins, amount);
        assertEquals(-1, result);
    }
    
    @Test
    public void testHouseRobberCircular() {
        int[] nums = {2, 3, 2};
        int result = HouseRobber.robCircular(nums);
        assertEquals(3, result);
    }
}

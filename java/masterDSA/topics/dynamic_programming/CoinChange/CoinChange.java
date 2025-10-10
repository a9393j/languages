/**
 * Coin Change
 * 
 * You are given an integer array coins representing coins of different denominations 
 * and an integer amount representing a total amount of money. Return the fewest number 
 * of coins that you need to make up that amount. If that amount of money cannot be 
 * made up by any combination of the coins, return -1.
 * 
 * LeetCode: https://leetcode.com/problems/coin-change/
 * Difficulty: Medium
 * Tags: dp, unbounded-knapsack
 * 
 * Approach:
 * - Use dynamic programming to find minimum coins
 * - dp[i] represents minimum coins needed for amount i
 * - For each coin, update dp[i + coin] if better solution found
 * 
 * Pseudocode:
 * 1. Initialize dp array with infinity
 * 2. dp[0] = 0 (no coins needed for amount 0)
 * 3. For each amount from 1 to target:
 *    a. For each coin:
 *       b. If coin <= amount, update dp[amount]
 * 4. Return dp[amount] if finite, otherwise -1
 */

package masterDSA.topics.dynamic_programming.CoinChange.CoinChange;

import java.util.*;

public class CoinChange {
    
    /**
     * Bottom-up DP approach - O(amount * coins.length) time, O(amount) space
     * Most efficient approach for this problem
     */
    public static int coinChange_bottomUp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    
    /**
     * Top-down DP approach - O(amount * coins.length) time, O(amount) space
     * Alternative approach using memoization
     */
    public static int coinChange_topDown(int[] coins, int amount) {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = coinChangeHelper(coins, amount, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private static int coinChangeHelper(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) {
            return 0;
        }
        
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
        
        int minCoins = Integer.MAX_VALUE;
        
        for (int coin : coins) {
            if (coin <= amount) {
                int result = coinChangeHelper(coins, amount - coin, memo);
                if (result != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, result + 1);
                }
            }
        }
        
        memo.put(amount, minCoins);
        return minCoins;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[] coins1 = {1, 3, 4};
        int amount1 = 6;
        
        int[] coins2 = {2};
        int amount2 = 3;
        
        int[] coins3 = {1, 2, 5};
        int amount3 = 11;
        
        System.out.println("Testing Coin Change:");
        
        System.out.println("Coins: " + Arrays.toString(coins1) + ", Amount: " + amount1);
        System.out.println("Bottom-up result: " + coinChange_bottomUp(coins1, amount1));
        System.out.println("Top-down result: " + coinChange_topDown(coins1, amount1));
        
        System.out.println("Coins: " + Arrays.toString(coins2) + ", Amount: " + amount2);
        System.out.println("Bottom-up result: " + coinChange_bottomUp(coins2, amount2));
        
        System.out.println("Coins: " + Arrays.toString(coins3) + ", Amount: " + amount3);
        System.out.println("Bottom-up result: " + coinChange_bottomUp(coins3, amount3));
    }
}
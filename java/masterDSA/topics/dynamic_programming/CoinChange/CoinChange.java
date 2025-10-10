package masterDSA.topics.dynamic_programming.CoinChange;
import java.util.*;


/**
 * Coin Change
 * 
 * You are given an integer array coins representing coins of different denominations 
 * and an integer amount representing a total amount of money.
 * 
 * LeetCode: https://leetcode.com/problems/coin-change/
 * Difficulty: Medium
 * Tags: dynamic-programming, bfs
 * 
 * Approach:
 * - Bottom-up DP: Build solution from smaller amounts
 * - Top-down DP: Use memoization with recursion
 * - BFS: Use queue to find minimum coins level by level
 * 
 * Pseudocode:
 * Bottom-up DP:
 * 1. Initialize dp array with amount+1
 * 2. dp[0] = 0
 * 3. For each amount from 1 to target:
 *    a. For each coin, if coin <= amount:
 *       dp[amount] = min(dp[amount], dp[amount-coin] + 1)
 * 4. Return dp[amount] if it's not amount+1
 */
public class CoinChange {
    
    /**
     * Bottom-up DP approach - O(amount * coins) time, O(amount) space
     * Build solution from smaller amounts
     */
    public static int coinChange_bottomUp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Top-down DP approach - O(amount * coins) time, O(amount) space
     * Use memoization with recursion
     */
    public static int coinChange_topDown(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        
        int[] memo = new int[amount + 1];
        java.util.Arrays.fill(memo, -1);
        
        int result = coinChangeHelper(coins, amount, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private static int coinChangeHelper(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        
        if (memo[amount] != -1) {
            return memo[amount];
        }
        
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = coinChangeHelper(coins, amount - coin, memo);
            if (result != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }
        
        memo[amount] = minCoins;
        return minCoins;
    }
    
    /**
     * BFS approach - O(amount * coins) time, O(amount) space
     * Use queue to find minimum coins level by level
     */
    public static int coinChange_bfs(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[amount + 1];
        
        queue.offer(0);
        visited[0] = true;
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                if (current == amount) {
                    return level;
                }
                
                for (int coin : coins) {
                    int next = current + coin;
                    if (next <= amount && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            
            level++;
        }
        
        return -1;
    }
    
    /**
     * Helper method to find all possible ways to make change
     */
    public static int coinChangeWays(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with limited supply
     */
    public static int coinChangeLimited(int[] coins, int[] counts, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                
                for (int k = 1; k <= counts[i - 1]; k++) {
                    if (j >= k * coins[i - 1] && dp[i - 1][j - k * coins[i - 1]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * coins[i - 1]] + k);
                    }
                }
            }
        }
        
        return dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount];
    }
    
    /**
     * Helper method to find minimum coins with different coin values
     */
    public static int coinChangeDifferentValues(int[] coins, int amount) {
        java.util.Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with transaction costs
     */
    public static int coinChangeWithCost(int[] coins, int amount, int transactionCost) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1 + transactionCost);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with minimum value constraint
     */
    public static int coinChangeMinValue(int[] coins, int amount, int minValue) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin >= minValue && coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with maximum value constraint
     */
    public static int coinChangeMaxValue(int[] coins, int amount, int maxValue) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= maxValue && coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with odd/even constraint
     */
    public static int coinChangeOddEven(int[] coins, int amount, boolean useOdd) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && ((useOdd && coin % 2 == 1) || (!useOdd && coin % 2 == 0))) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    public static void main(String[] args) {
        // Test case: coins = [1,3,4], amount = 6
        int[] coins1 = {1, 3, 4};
        int amount1 = 6;
        
        System.out.println("Testing Coin Change:");
        System.out.println("Coins: " + java.util.Arrays.toString(coins1));
        System.out.println("Amount: " + amount1);
        
        int result1 = coinChange_bottomUp(coins1, amount1);
        System.out.println("Bottom-up DP result: " + result1);
        
        int result2 = coinChange_topDown(coins1, amount1);
        System.out.println("Top-down DP result: " + result2);
        
        int result3 = coinChange_bfs(coins1, amount1);
        System.out.println("BFS result: " + result3);
        
        // Expected: 2 (3 + 3)
        
        System.out.println();
        
        // Test case: coins = [2], amount = 3
        int[] coins2 = {2};
        int amount2 = 3;
        
        System.out.println("Coins: " + java.util.Arrays.toString(coins2));
        System.out.println("Amount: " + amount2);
        
        int result4 = coinChange_bottomUp(coins2, amount2);
        System.out.println("Bottom-up DP result: " + result4);
        
        // Expected: -1 (impossible)
        
        System.out.println();
        
        // Test case: coins = [1], amount = 0
        int[] coins3 = {1};
        int amount3 = 0;
        
        System.out.println("Coins: " + java.util.Arrays.toString(coins3));
        System.out.println("Amount: " + amount3);
        
        int result5 = coinChange_bottomUp(coins3, amount3);
        System.out.println("Bottom-up DP result: " + result5);
        
        // Expected: 0
        
        System.out.println();
        
        // Test helper methods
        int ways = coinChangeWays(coins1, amount1);
        System.out.println("Number of ways to make change: " + ways);
        
        int[] counts = {2, 1, 1};
        int limited = coinChangeLimited(coins1, counts, amount1);
        System.out.println("Minimum coins with limited supply: " + limited);
        
        int differentValues = coinChangeDifferentValues(coins1, amount1);
        System.out.println("Minimum coins with different values: " + differentValues);
        
        int withCost = coinChangeWithCost(coins1, amount1, 1);
        System.out.println("Minimum coins with transaction cost: " + withCost);
        
        int minValue = coinChangeMinValue(coins1, amount1, 2);
        System.out.println("Minimum coins with minimum value constraint: " + minValue);
        
        int maxValue = coinChangeMaxValue(coins1, amount1, 3);
        System.out.println("Minimum coins with maximum value constraint: " + maxValue);
        
        int oddCoins = coinChangeOddEven(coins1, amount1, true);
        System.out.println("Minimum coins using only odd denominations: " + oddCoins);
        
        // Expected: Ways = 3, Limited = 2, Different values = 2, With cost = 3, Min value = 2, Max value = 2, Odd coins = 2
    }
}

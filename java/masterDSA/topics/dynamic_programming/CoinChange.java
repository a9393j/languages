package masterDSA.topics.dynamic_programming;
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
        // Base case: if amount is 0, we need 0 coins
        if (amount == 0) {
            return 0;
        }
        
        // dp[i] represents minimum number of coins needed to make amount i
        int[] dp = new int[amount + 1];
        // Initialize with amount + 1 (impossible value) to represent infinity
        Arrays.fill(dp, amount + 1);
        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;
        
        // For each amount from 1 to target amount
        for (int i = 1; i <= amount; i++) {
            // Try each coin denomination
            for (int coin : coins) {
                // If coin value is less than or equal to current amount
                if (coin <= i) {
                    // Update dp[i] with minimum of:
                    // - current dp[i] value
                    // - dp[i-coin] + 1 (using this coin)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // If dp[amount] is still amount + 1, it means no solution exists
        // Otherwise return the minimum number of coins
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Top-down DP approach - O(amount * coins) time, O(amount) space
     * Use memoization with recursion
     */
    public static int coinChange_topDown(int[] coins, int amount) {
        // Base case: if amount is 0, we need 0 coins
        if (amount == 0) {
            return 0;
        }
        
        // memo[i] stores the minimum coins needed for amount i
        int[] memo = new int[amount + 1];
        // Initialize with -1 to indicate not computed yet
        Arrays.fill(memo, -1);
        
        // Call helper function with memoization
        int result = coinChangeHelper(coins, amount, memo);
        // If result is MAX_VALUE, no solution exists
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private static int coinChangeHelper(int[] coins, int amount, int[] memo) {
        // Base case: if amount is 0, we need 0 coins
        if (amount == 0) {
            return 0;
        }
        
        // Base case: if amount is negative, impossible to make change
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        
        // If already computed, return cached result
        if (memo[amount] != -1) {
            return memo[amount];
        }
        
        // Initialize minimum coins to maximum value
        int minCoins = Integer.MAX_VALUE;
        // Try each coin denomination
        for (int coin : coins) {
            // Recursively find minimum coins for (amount - coin)
            int result = coinChangeHelper(coins, amount - coin, memo);
            // If solution exists for (amount - coin)
            if (result != Integer.MAX_VALUE) {
                // Update minimum coins needed
                minCoins = Math.min(minCoins, result + 1);
            }
        }
        
        // Store result in memo and return
        memo[amount] = minCoins;
        return minCoins;
    }
    
    /**
     * BFS approach - O(amount * coins) time, O(amount) space
     * Use queue to find minimum coins level by level
     */
    public static int coinChange_bfs(int[] coins, int amount) {
        // Base case: if amount is 0, we need 0 coins
        if (amount == 0) {
            return 0;
        }
        
        // Queue to store amounts to process
        Queue<Integer> queue = new LinkedList<>();
        // Track visited amounts to avoid cycles
        boolean[] visited = new boolean[amount + 1];
        
        // Start with amount 0
        queue.offer(0);
        visited[0] = true;
        int level = 0; // Level represents number of coins used
        
        // Process each level (each level = one more coin)
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of amounts at current level
            
            // Process all amounts at current level
            for (int i = 0; i < size; i++) {
                int current = queue.poll(); // Get current amount
                
                // If we reached target amount, return level (min coins)
                if (current == amount) {
                    return level;
                }
                
                // Try adding each coin to current amount
                for (int coin : coins) {
                    int next = current + coin;
                    // If next amount is valid and not visited
                    if (next <= amount && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next); // Add to next level
                    }
                }
            }
            
            level++; // Move to next level (one more coin)
        }
        
        // If queue is empty and target not reached, no solution exists
        return -1;
    }
    
    /**
     * Helper method to find all possible ways to make change
     * This counts the number of different combinations, not minimum coins
     */
    public static int coinChangeWays(int[] coins, int amount) {
        // dp[i] represents number of ways to make amount i
        int[] dp = new int[amount + 1];
        dp[0] = 1; // One way to make amount 0: use no coins
        
        // For each coin denomination
        for (int coin : coins) {
            // For each amount from coin value to target
            for (int i = coin; i <= amount; i++) {
                // Add ways to make (i-coin) to current ways
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with limited supply
     * Each coin has a maximum count that can be used
     */
    public static int coinChangeLimited(int[] coins, int[] counts, int amount) {
        int n = coins.length;
        // dp[i][j] = minimum coins using first i coin types to make amount j
        int[][] dp = new int[n + 1][amount + 1];
        
        // Initialize with infinity (impossible values)
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        // For each coin type
        for (int i = 1; i <= n; i++) {
            // For each amount
            for (int j = 0; j <= amount; j++) {
                // Start with solution without using current coin type
                dp[i][j] = dp[i - 1][j];
                
                // Try using 1 to counts[i-1] coins of current type
                for (int k = 1; k <= counts[i - 1]; k++) {
                    // If we can use k coins of current type
                    if (j >= k * coins[i - 1] && dp[i - 1][j - k * coins[i - 1]] != Integer.MAX_VALUE) {
                        // Update minimum coins needed
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * coins[i - 1]] + k);
                    }
                }
            }
        }
        
        return dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount];
    }
    
    /**
     * Helper method to find minimum coins with different coin values
     * Optimized version that breaks early when coin > amount
     */
    public static int coinChangeDifferentValues(int[] coins, int amount) {
        // Sort coins to enable early termination
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // If coin value exceeds current amount, break (coins are sorted)
                if (coin > i) {
                    break;
                }
                // Update minimum coins needed
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with transaction costs
     * Each transaction has an additional cost
     */
    public static int coinChangeWithCost(int[] coins, int amount, int transactionCost) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // Add transaction cost to each coin usage
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1 + transactionCost);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with minimum value constraint
     * Only coins with value >= minValue can be used
     */
    public static int coinChangeMinValue(int[] coins, int amount, int minValue) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // Only use coins that meet minimum value requirement
                if (coin >= minValue && coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with maximum value constraint
     * Only coins with value <= maxValue can be used
     */
    public static int coinChangeMaxValue(int[] coins, int amount, int maxValue) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // Only use coins that meet maximum value requirement
                if (coin <= maxValue && coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Helper method to find minimum coins with odd/even constraint
     * Only odd or even denomination coins can be used based on useOdd flag
     */
    public static int coinChangeOddEven(int[] coins, int amount, boolean useOdd) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // Check if coin meets odd/even requirement
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
        System.out.println("Coins: " + Arrays.toString(coins1));
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
        
        System.out.println("Coins: " + Arrays.toString(coins2));
        System.out.println("Amount: " + amount2);
        
        int result4 = coinChange_bottomUp(coins2, amount2);
        System.out.println("Bottom-up DP result: " + result4);
        
        // Expected: -1 (impossible)
        
        System.out.println();
        
        // Test case: coins = [1], amount = 0
        int[] coins3 = {1};
        int amount3 = 0;
        
        System.out.println("Coins: " + Arrays.toString(coins3));
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

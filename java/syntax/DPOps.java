package syntax;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPOps {

    public static void dpBasics() {
        System.out.println("------------------------------------------");
        System.out.println("DYNAMIC PROGRAMMING BASICS");
        System.out.println("---------------------");

        // Fibonacci with different approaches
        int n = 10;
        System.out.println("Fibonacci(" + n + ") with different approaches:");
        System.out.println("Recursive: " + fibonacciRecursive(n));
        System.out.println("Memoization: " + fibonacciMemo(n));
        System.out.println("Bottom-up: " + fibonacciDP(n));
        System.out.println("Space optimized: " + fibonacciOptimized(n));
    }

    public static void classicDPProblems() {
        System.out.println("------------------------------------------");
        System.out.println("CLASSIC DP PROBLEMS");
        System.out.println("---------------------");

        // Longest Common Subsequence
        String s1 = "ABCDGH";
        String s2 = "AEDFHR";
        System.out.println("LCS of '" + s1 + "' and '" + s2 + "': " + lcs(s1, s2));

        // Longest Increasing Subsequence
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println("LIS of " + Arrays.toString(arr) + ": " + lis(arr));

        // Edit Distance
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Edit distance between '" + word1 + "' and '" + word2 + "': " + editDistance(word1, word2));

        // Coin Change
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Coin change for amount " + amount + " with coins " + Arrays.toString(coins) + ": " + coinChange(coins, amount));
    }

    public static void knapsackProblems() {
        System.out.println("------------------------------------------");
        System.out.println("KNAPSACK PROBLEMS");
        System.out.println("---------------------");

        // 0/1 Knapsack
        int[] weights = {2, 1, 3, 2};
        int[] values = {12, 10, 20, 15};
        int capacity = 5;
        System.out.println("0/1 Knapsack - Max value: " + knapsack01(weights, values, capacity));

        // Unbounded Knapsack
        int[] unboundedWeights = {1, 3, 4, 5};
        int[] unboundedValues = {10, 40, 50, 70};
        int unboundedCapacity = 8;
        System.out.println("Unbounded Knapsack - Max value: " + unboundedKnapsack(unboundedWeights, unboundedValues, unboundedCapacity));

        // Subset Sum
        int[] subsetArr = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("Subset sum for target " + target + ": " + subsetSum(subsetArr, target));
    }

    public static void matrixDP() {
        System.out.println("------------------------------------------");
        System.out.println("MATRIX DP PROBLEMS");
        System.out.println("---------------------");

        // Unique Paths
        int m = 3, n = 7;
        System.out.println("Unique paths in " + m + "x" + n + " grid: " + uniquePaths(m, n));

        // Minimum Path Sum
        int[][] grid = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println("Minimum path sum: " + minPathSum(grid));

        // Longest Palindromic Substring
        String s = "babad";
        System.out.println("Longest palindromic substring of '" + s + "': " + longestPalindrome(s));
    }

    public static void advancedDP() {
        System.out.println("------------------------------------------");
        System.out.println("ADVANCED DP PROBLEMS");
        System.out.println("---------------------");

        // House Robber
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("House robber max profit: " + houseRobber(houses));

        // Buy and Sell Stock
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max profit from stock trading: " + maxProfit(prices));

        // Climbing Stairs
        int stairs = 5;
        System.out.println("Ways to climb " + stairs + " stairs: " + climbStairs(stairs));

        // Decode Ways
        String encoded = "226";
        System.out.println("Decode ways for '" + encoded + "': " + numDecodings(encoded));
    }

    // Fibonacci implementations
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciMemo(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return fibonacciMemoHelper(n, memo);
    }

    private static int fibonacciMemoHelper(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n <= 1) return n;
        
        int result = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static int fibonacciDP(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }

    public static int fibonacciOptimized(int n) {
        if (n <= 1) return n;
        
        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        
        return curr;
    }

    // Classic DP problems
    public static int lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }

    public static int lis(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return Arrays.stream(dp).max().orElse(0);
    }

    public static int editDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // Knapsack problems
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }

    public static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        
        for (int w = 0; w <= capacity; w++) {
            for (int i = 0; i < weights.length; i++) {
                if (weights[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                }
            }
        }
        
        return dp[capacity];
    }

    public static boolean subsetSum(int[] arr, int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        for (int i = 0; i <= n; i++) dp[i][0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][target];
    }

    // Matrix DP problems
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[m - 1][n - 1];
    }

    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;
        
        for (int i = 0; i < n; i++) dp[i][i] = true;
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && (len == 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                }
            }
        }
        
        return s.substring(start, start + maxLen);
    }

    // Advanced DP problems
    public static int houseRobber(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[n - 1];
    }

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        
        return maxProfit;
    }

    public static int climbStairs(int n) {
        if (n <= 2) return n;
        
        int prev = 1, curr = 2;
        for (int i = 3; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        
        return curr;
    }

    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            
            if (oneDigit >= 1) dp[i] += dp[i - 1];
            if (twoDigits >= 10 && twoDigits <= 26) dp[i] += dp[i - 2];
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        dpBasics();
        classicDPProblems();
        knapsackProblems();
        matrixDP();
        advancedDP();
    }
} 
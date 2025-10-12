package masterDSA.topics.arrays;
import java.util.*;


/**
 * Non-Constructible Change
 * 
 * Given an array of positive integers representing the values of coins in your possession, 
 * write a function that returns the minimum amount of change (the minimum sum of money) 
 * that you cannot create. The given coins can have any positive integer value and aren't 
 * necessarily unique (i.e., you can have multiple coins of the same value).
 * 
 * LeetCode: https://www.interviewcake.com/question/java/non-constructible-change
 * Difficulty: Easy
 * Tags: greedy, sorting
 * 
 * Approach:
 * - Sort the coins to process them in ascending order
 * - Keep track of the maximum change we can make so far
 * - If current coin > maxChange + 1, then maxChange + 1 is the answer
 * - Otherwise, add current coin to maxChange
 * 
 * Pseudocode:
 * 1. Sort the coins array
 * 2. Initialize maxChange = 0
 * 3. For each coin in sorted array:
 *    a. If coin > maxChange + 1, return maxChange + 1
 *    b. Else, maxChange += coin
 * 4. Return maxChange + 1
 */
public class NonConstructibleChange {
    
    /**
     * Greedy approach - O(n log n) time, O(1) space
     * Sort coins and greedily build up the maximum change we can make
     */
    public static int nonConstructibleChange_greedy(int[] coins) {
        // Sort coins to process them in ascending order
        java.util.Arrays.sort(coins);
        
        int maxChange = 0; // Maximum change we can make so far
        
        for (int coin : coins) {
            // If current coin is greater than maxChange + 1,
            // then we cannot make maxChange + 1
            if (coin > maxChange + 1) {
                return maxChange + 1;
            }
            
            // Otherwise, we can make all values from 1 to maxChange + coin
            maxChange += coin;
        }
        
        // If we can make all values up to maxChange, then maxChange + 1 is the answer
        return maxChange + 1;
    }
    
    /**
     * Alternative greedy approach with explicit explanation
     * Same time complexity but with more detailed comments
     */
    public static int nonConstructibleChange_detailed(int[] coins) {
        if (coins.length == 0) {
            return 1; // Cannot make change 1 with no coins
        }
        
        java.util.Arrays.sort(coins);
        
        int maxChange = 0;
        
        for (int i = 0; i < coins.length; i++) {
            int currentCoin = coins[i];
            
            // Key insight: If we can make all values from 1 to maxChange,
            // and currentCoin > maxChange + 1, then we cannot make maxChange + 1
            if (currentCoin > maxChange + 1) {
                return maxChange + 1;
            }
            
            // With currentCoin, we can now make all values from 1 to maxChange + currentCoin
            maxChange += currentCoin;
        }
        
        return maxChange + 1;
    }
    
    /**
     * Brute force approach - O(n * 2^n) time, O(1) space
     * Check all possible subsets to find the minimum non-constructible change
     * Note: This is inefficient and only for educational purposes
     */
    public static int nonConstructibleChange_bruteForce(int[] coins) {
        java.util.Set<Integer> possibleSums = new java.util.HashSet<>();
        possibleSums.add(0); // We can always make 0
        
        // Generate all possible sums using subsets
        for (int i = 1; i < (1 << coins.length); i++) {
            int sum = 0;
            for (int j = 0; j < coins.length; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += coins[j];
                }
            }
            possibleSums.add(sum);
        }
        
        // Find the smallest positive integer not in possibleSums
        int change = 1;
        while (possibleSums.contains(change)) {
            change++;
        }
        
        return change;
    }
    
    public static void main(String[] args) {
        // Test case 1: coins = [1, 2, 5]
        int[] coins1 = {1, 2, 5};
        System.out.println("Testing Non-Constructible Change:");
        System.out.println("Coins: " + java.util.Arrays.toString(coins1));
        
        int result1 = nonConstructibleChange_greedy(coins1);
        System.out.println("Greedy result: " + result1);
        
        int result2 = nonConstructibleChange_detailed(coins1);
        System.out.println("Detailed result: " + result2);
        
        int result3 = nonConstructibleChange_bruteForce(coins1);
        System.out.println("Brute force result: " + result3);
        
        // Expected: 4 (we can make 1, 2, 3, 5, 6, 7, 8 but not 4)
        
        System.out.println();
        
        // Test case 2: coins = [5, 7, 1, 1, 2, 3, 22]
        int[] coins2 = {5, 7, 1, 1, 2, 3, 22};
        System.out.println("Coins: " + java.util.Arrays.toString(coins2));
        
        int result4 = nonConstructibleChange_greedy(coins2);
        System.out.println("Greedy result: " + result4);
        
        // Expected: 20
    }
}

package masterDSA.topics.arrays;
import java.util.*;


/**
 * Min Rewards
 * 
 * Given an array of integers representing scores of students, distribute rewards such that:
 * - Each student gets at least one reward
 * - A student with higher score than adjacent students gets more rewards
 * - Minimize the total number of rewards distributed
 * 
 * LeetCode: 
 * Difficulty: Medium
 * Tags: greedy
 * 
 * Approach:
 * - Two passes: left to right, then right to left
 * - First pass: ensure left neighbor constraint is satisfied
 * - Second pass: ensure right neighbor constraint is satisfied
 * - Alternative: Use peaks and valleys approach
 * 
 * Pseudocode:
 * 1. Initialize rewards array with 1s
 * 2. Left to right pass:
 *    a. If current score > previous score, current reward = previous reward + 1
 * 3. Right to left pass:
 *    a. If current score > next score, current reward = max(current, next + 1)
 * 4. Return sum of rewards
 */
public class MinRewards {
    
    /**
     * Two passes approach - O(n) time, O(n) space
     * First pass ensures left neighbor constraint, second pass ensures right neighbor constraint
     */
    public static int minRewards_twoPasses(int[] scores) {
        int n = scores.length;
        int[] rewards = new int[n];
        
        // Initialize all rewards to 1 (minimum requirement)
        java.util.Arrays.fill(rewards, 1);
        
        // Left to right pass: ensure left neighbor constraint
        for (int i = 1; i < n; i++) {
            if (scores[i] > scores[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }
        
        // Right to left pass: ensure right neighbor constraint
        for (int i = n - 2; i >= 0; i--) {
            if (scores[i] > scores[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }
        
        // Calculate total rewards
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }
        
        return totalRewards;
    }
    
    /**
     * Peaks and valleys approach - O(n) time, O(n) space
     * Identify local minima and expand rewards outward
     */
    public static int minRewards_peaksValleys(int[] scores) {
        int n = scores.length;
        int[] rewards = new int[n];
        
        // Find local minima (valleys)
        java.util.List<Integer> valleys = new java.util.ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            boolean isValley = true;
            
            // Check left neighbor
            if (i > 0 && scores[i] >= scores[i - 1]) {
                isValley = false;
            }
            
            // Check right neighbor
            if (i < n - 1 && scores[i] >= scores[i + 1]) {
                isValley = false;
            }
            
            if (isValley) {
                valleys.add(i);
            }
        }
        
        // Initialize rewards array
        java.util.Arrays.fill(rewards, 1);
        
        // Expand rewards from each valley
        for (int valley : valleys) {
            rewards[valley] = 1;
            
            // Expand leftward
            for (int i = valley - 1; i >= 0; i--) {
                if (scores[i] > scores[i + 1]) {
                    rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
                } else {
                    break; // Stop if constraint is violated
                }
            }
            
            // Expand rightward
            for (int i = valley + 1; i < n; i++) {
                if (scores[i] > scores[i - 1]) {
                    rewards[i] = Math.max(rewards[i], rewards[i - 1] + 1);
                } else {
                    break; // Stop if constraint is violated
                }
            }
        }
        
        // Calculate total rewards
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }
        
        return totalRewards;
    }
    
    /**
     * Brute force approach - O(n²) time, O(n) space
     * For each position, calculate minimum reward needed
     */
    public static int minRewards_bruteForce(int[] scores) {
        int n = scores.length;
        int[] rewards = new int[n];
        
        // Initialize all rewards to 1
        java.util.Arrays.fill(rewards, 1);
        
        boolean changed = true;
        while (changed) {
            changed = false;
            
            for (int i = 0; i < n; i++) {
                // Check left neighbor
                if (i > 0 && scores[i] > scores[i - 1] && rewards[i] <= rewards[i - 1]) {
                    rewards[i] = rewards[i - 1] + 1;
                    changed = true;
                }
                
                // Check right neighbor
                if (i < n - 1 && scores[i] > scores[i + 1] && rewards[i] <= rewards[i + 1]) {
                    rewards[i] = rewards[i + 1] + 1;
                    changed = true;
                }
            }
        }
        
        // Calculate total rewards
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }
        
        return totalRewards;
    }
    
    /**
     * Helper method to print rewards distribution
     */
    public static void printRewardsDistribution(int[] scores, int[] rewards) {
        System.out.println("Scores:  " + java.util.Arrays.toString(scores));
        System.out.println("Rewards: " + java.util.Arrays.toString(rewards));
        System.out.println("Total:   " + java.util.Arrays.stream(rewards).sum());
    }
    
    public static void main(String[] args) {
        // Test case: scores = [8, 4, 2, 1, 3, 6, 7, 9, 5]
        int[] scores = {8, 4, 2, 1, 3, 6, 7, 9, 5};
        
        System.out.println("Testing Min Rewards:");
        System.out.println("Scores: " + java.util.Arrays.toString(scores));
        
        int result1 = minRewards_twoPasses(scores);
        System.out.println("Two passes result: " + result1);
        
        int result2 = minRewards_peaksValleys(scores);
        System.out.println("Peaks and valleys result: " + result2);
        
        int result3 = minRewards_bruteForce(scores);
        System.out.println("Brute force result: " + result3);
        
        // Expected: 25 (rewards: [4, 3, 2, 1, 2, 3, 4, 5, 1])
        
        System.out.println();
        
        // Test case: scores = [1, 2, 3, 4, 5] (increasing)
        int[] increasingScores = {1, 2, 3, 4, 5};
        System.out.println("Increasing scores: " + java.util.Arrays.toString(increasingScores));
        
        int result4 = minRewards_twoPasses(increasingScores);
        System.out.println("Two passes result: " + result4);
        
        // Expected: 15 (rewards: [1, 2, 3, 4, 5])
        
        System.out.println();
        
        // Test case: scores = [5, 4, 3, 2, 1] (decreasing)
        int[] decreasingScores = {5, 4, 3, 2, 1};
        System.out.println("Decreasing scores: " + java.util.Arrays.toString(decreasingScores));
        
        int result5 = minRewards_twoPasses(decreasingScores);
        System.out.println("Two passes result: " + result5);
        
        // Expected: 15 (rewards: [5, 4, 3, 2, 1])
    }
}

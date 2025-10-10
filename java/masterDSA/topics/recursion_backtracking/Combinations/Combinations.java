package masterDSA.topics.recursion_backtracking.Combinations;
import java.util.*;


/**
 * Combinations
 * 
 * Given two integers n and k, return all possible combinations of k numbers 
 * chosen from the range [1, n].
 * 
 * LeetCode: https://leetcode.com/problems/combinations/
 * Difficulty: Medium
 * Tags: recursion, backtracking
 * 
 * Approach:
 * - Backtracking: Generate combinations using backtracking
 * - Iterative: Build combinations incrementally
 * - Mathematical: Use combination formula
 * 
 * Pseudocode:
 * Backtracking:
 * 1. Start with empty combination
 * 2. For each number from start to n:
 *    a. Add number to current combination
 *    b. Recurse with next number
 *    c. Remove number and try next
 * 3. When combination size equals k, add to result
 */
public class Combinations {
    
    /**
     * Backtracking approach - O(C(n,k)) time, O(C(n,k)) space
     * Generate combinations using backtracking
     */
    public static java.util.List<java.util.List<Integer>> combine_backtracking(int n, int k) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrack(1, n, k, current, result);
        return result;
    }
    
    private static void backtrack(int start, int n, int k, java.util.List<Integer> current, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == k) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrack(i + 1, n, k, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Iterative approach - O(C(n,k)) time, O(C(n,k)) space
     * Build combinations incrementally
     */
    public static java.util.List<java.util.List<Integer>> combine_iterative(int n, int k) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        result.add(new java.util.ArrayList<>());
        
        for (int i = 1; i <= n; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                java.util.List<Integer> current = result.get(j);
                if (current.size() < k) {
                    java.util.List<Integer> newCombination = new java.util.ArrayList<>(current);
                    newCombination.add(i);
                    if (newCombination.size() == k) {
                        result.add(newCombination);
                    } else {
                        result.add(newCombination);
                    }
                }
            }
        }
        
        // Filter to only include combinations of size k
        result.removeIf(combination -> combination.size() != k);
        return result;
    }
    
    /**
     * Mathematical approach - O(C(n,k)) time, O(C(n,k)) space
     * Use combination formula C(n,k) = C(n-1,k-1) + C(n-1,k)
     */
    public static java.util.List<java.util.List<Integer>> combine_mathematical(int n, int k) {
        if (k == 0 || k > n) {
            return new java.util.ArrayList<>();
        }
        
        if (k == 1) {
            java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
            for (int i = 1; i <= n; i++) {
                java.util.List<Integer> combination = new java.util.ArrayList<>();
                combination.add(i);
                result.add(combination);
            }
            return result;
        }
        
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        
        // C(n,k) = C(n-1,k-1) + C(n-1,k)
        java.util.List<java.util.List<Integer>> withN = combine_mathematical(n - 1, k - 1);
        for (java.util.List<Integer> combination : withN) {
            combination.add(n);
            result.add(combination);
        }
        
        java.util.List<java.util.List<Integer>> withoutN = combine_mathematical(n - 1, k);
        result.addAll(withoutN);
        
        return result;
    }
    
    /**
     * Helper method to calculate combination count
     */
    public static int combinationCount(int n, int k) {
        if (k > n || k < 0) {
            return 0;
        }
        
        if (k == 0 || k == n) {
            return 1;
        }
        
        // Use Pascal's triangle: C(n,k) = C(n-1,k-1) + C(n-1,k)
        return combinationCount(n - 1, k - 1) + combinationCount(n - 1, k);
    }
    
    /**
     * Helper method to calculate combination count with memoization
     */
    public static int combinationCountMemo(int n, int k) {
        int[][] memo = new int[n + 1][k + 1];
        return combinationCountHelper(n, k, memo);
    }
    
    private static int combinationCountHelper(int n, int k, int[][] memo) {
        if (k > n || k < 0) {
            return 0;
        }
        
        if (k == 0 || k == n) {
            return 1;
        }
        
        if (memo[n][k] != 0) {
            return memo[n][k];
        }
        
        memo[n][k] = combinationCountHelper(n - 1, k - 1, memo) + combinationCountHelper(n - 1, k, memo);
        return memo[n][k];
    }
    
    /**
     * Helper method to find combinations with sum
     */
    public static java.util.List<java.util.List<Integer>> combineWithSum(int n, int k, int targetSum) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithSum(1, n, k, targetSum, current, result, 0);
        return result;
    }
    
    private static void backtrackWithSum(int start, int n, int k, int targetSum, 
                                       java.util.List<Integer> current, java.util.List<java.util.List<Integer>> result, int currentSum) {
        if (current.size() == k && currentSum == targetSum) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        if (current.size() > k || currentSum > targetSum) {
            return;
        }
        
        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrackWithSum(i + 1, n, k, targetSum, current, result, currentSum + i);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Helper method to find combinations with product
     */
    public static java.util.List<java.util.List<Integer>> combineWithProduct(int n, int k, int targetProduct) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithProduct(1, n, k, targetProduct, current, result, 1);
        return result;
    }
    
    private static void backtrackWithProduct(int start, int n, int k, int targetProduct, 
                                           java.util.List<Integer> current, java.util.List<java.util.List<Integer>> result, int currentProduct) {
        if (current.size() == k && currentProduct == targetProduct) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        if (current.size() > k || currentProduct > targetProduct) {
            return;
        }
        
        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrackWithProduct(i + 1, n, k, targetProduct, current, result, currentProduct * i);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Helper method to find combinations with constraints
     */
    public static java.util.List<java.util.List<Integer>> combineWithConstraints(int n, int k, java.util.Set<Integer> forbidden) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithConstraints(1, n, k, forbidden, current, result);
        return result;
    }
    
    private static void backtrackWithConstraints(int start, int n, int k, java.util.Set<Integer> forbidden, 
                                              java.util.List<Integer> current, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == k) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            if (!forbidden.contains(i)) {
                current.add(i);
                backtrackWithConstraints(i + 1, n, k, forbidden, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
    
    /**
     * Helper method to find combinations with minimum difference
     */
    public static java.util.List<java.util.List<Integer>> combineWithMinDifference(int n, int k, int minDiff) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithMinDifference(1, n, k, minDiff, current, result);
        return result;
    }
    
    private static void backtrackWithMinDifference(int start, int n, int k, int minDiff, 
                                                 java.util.List<Integer> current, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == k) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            if (current.isEmpty() || i - current.get(current.size() - 1) >= minDiff) {
                current.add(i);
                backtrackWithMinDifference(i + 1, n, k, minDiff, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
    
    /**
     * Helper method to find combinations with maximum difference
     */
    public static java.util.List<java.util.List<Integer>> combineWithMaxDifference(int n, int k, int maxDiff) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        backtrackWithMaxDifference(1, n, k, maxDiff, current, result);
        return result;
    }
    
    private static void backtrackWithMaxDifference(int start, int n, int k, int maxDiff, 
                                                 java.util.List<Integer> current, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == k) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            if (current.isEmpty() || i - current.get(current.size() - 1) <= maxDiff) {
                current.add(i);
                backtrackWithMaxDifference(i + 1, n, k, maxDiff, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test case: n = 4, k = 2
        int n = 4;
        int k = 2;
        
        System.out.println("Testing Combinations:");
        System.out.println("n = " + n + ", k = " + k);
        
        java.util.List<java.util.List<Integer>> result1 = combine_backtracking(n, k);
        System.out.println("Backtracking result: " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = combine_iterative(n, k);
        System.out.println("Iterative result: " + result2);
        
        java.util.List<java.util.List<Integer>> result3 = combine_mathematical(n, k);
        System.out.println("Mathematical result: " + result3);
        
        // Expected: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]
        
        System.out.println();
        
        // Test case: n = 1, k = 1
        int n2 = 1;
        int k2 = 1;
        
        System.out.println("n = " + n2 + ", k = " + k2);
        
        java.util.List<java.util.List<Integer>> result4 = combine_backtracking(n2, k2);
        System.out.println("Backtracking result: " + result4);
        
        // Expected: [[1]]
        
        System.out.println();
        
        // Test helper methods
        int count = combinationCount(4, 2);
        System.out.println("Combination count C(4,2): " + count);
        
        int countMemo = combinationCountMemo(4, 2);
        System.out.println("Combination count with memoization: " + countMemo);
        
        java.util.List<java.util.List<Integer>> withSum = combineWithSum(4, 2, 5);
        System.out.println("Combinations with sum 5: " + withSum);
        
        java.util.List<java.util.List<Integer>> withProduct = combineWithProduct(4, 2, 6);
        System.out.println("Combinations with product 6: " + withProduct);
        
        java.util.Set<Integer> forbidden = new java.util.HashSet<>();
        forbidden.add(2);
        java.util.List<java.util.List<Integer>> withConstraints = combineWithConstraints(4, 2, forbidden);
        System.out.println("Combinations with constraints: " + withConstraints);
        
        java.util.List<java.util.List<Integer>> withMinDiff = combineWithMinDifference(4, 2, 2);
        System.out.println("Combinations with minimum difference 2: " + withMinDiff);
        
        java.util.List<java.util.List<Integer>> withMaxDiff = combineWithMaxDifference(4, 2, 1);
        System.out.println("Combinations with maximum difference 1: " + withMaxDiff);
        
        // Expected: Count = 6, With sum 5 = [[1,4], [2,3]], With product 6 = [[1,6], [2,3]], With constraints = [[1,3], [1,4], [3,4]], With min diff 2 = [[1,3], [1,4], [2,4]], With max diff 1 = [[1,2], [2,3], [3,4]]
    }
}

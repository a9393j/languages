package masterDSA.topics.greedy;
import java.util.*;


/**
 * Jump Game
 * 
 * You are given an integer array nums. You are initially positioned at the array's first index, 
 * and each element in the array represents your maximum jump length at that position.
 * 
 * LeetCode: https://leetcode.com/problems/jump-game/
 * Difficulty: Medium
 * Tags: greedy, array
 * 
 * Approach:
 * - Greedy: Track the farthest reachable position
 * - DP: Use dynamic programming to check each position
 * - Backtracking: Try all possible jumps (inefficient)
 * 
 * Pseudocode:
 * Greedy:
 * 1. Initialize maxReach = 0
 * 2. For each position i:
 *    a. If i > maxReach, return false
 *    b. Update maxReach = max(maxReach, i + nums[i])
 * 3. Return true if maxReach >= last index
 */
public class JumpGame {
    
    /**
     * Greedy approach - O(n) time, O(1) space
     * Track the farthest reachable position
     */
    public static boolean canJump_greedy(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int maxReach = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false;
            }
            
            maxReach = Math.max(maxReach, i + nums[i]);
            
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
    
    /**
     * Dynamic programming approach - O(n²) time, O(n) space
     * Use DP to check each position
     */
    public static boolean canJump_dp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Backtracking approach - O(2^n) time, O(n) space
     * Try all possible jumps (inefficient)
     */
    public static boolean canJump_backtracking(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        return backtrack(nums, 0);
    }
    
    private static boolean backtrack(int[] nums, int position) {
        if (position >= nums.length - 1) {
            return true;
        }
        
        int maxJump = nums[position];
        for (int i = 1; i <= maxJump; i++) {
            if (backtrack(nums, position + i)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Helper method to find minimum jumps needed
     */
    public static int minJumps(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        
        return jumps;
    }
    
    /**
     * Helper method to find all possible jump paths
     */
    public static java.util.List<java.util.List<Integer>> findAllPaths(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        java.util.List<Integer> path = new java.util.ArrayList<>();
        path.add(0);
        
        findAllPathsHelper(nums, 0, path, result);
        
        return result;
    }
    
    private static void findAllPathsHelper(int[] nums, int position, java.util.List<Integer> path, java.util.List<java.util.List<Integer>> result) {
        if (position >= nums.length - 1) {
            result.add(new java.util.ArrayList<>(path));
            return;
        }
        
        int maxJump = nums[position];
        for (int i = 1; i <= maxJump; i++) {
            if (position + i < nums.length) {
                path.add(position + i);
                findAllPathsHelper(nums, position + i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
    
    /**
     * Helper method to find maximum score from jumps
     */
    public static int maxScore(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Helper method to check if we can reach the last index with at most k jumps
     */
    public static boolean canReachWithKJumps(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int n = nums.length;
        boolean[][] dp = new boolean[n][k + 1];
        dp[0][0] = true;
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l < i; l++) {
                    if (dp[l][j - 1] && l + nums[l] >= i) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        
        return dp[n - 1][k];
    }
    
    /**
     * Helper method to find the shortest path with minimum jumps
     */
    public static java.util.List<Integer> shortestPath(int[] nums) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        int[] parent = new int[n];
        java.util.Arrays.fill(parent, -1);
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            if (current == n - 1) {
                break;
            }
            
            int maxJump = nums[current];
            for (int i = 1; i <= maxJump; i++) {
                int next = current + i;
                if (next < n && !visited[next]) {
                    visited[next] = true;
                    parent[next] = current;
                    queue.offer(next);
                }
            }
        }
        
        // Reconstruct path
        if (visited[n - 1]) {
            int current = n - 1;
            while (current != -1) {
                result.add(0, current);
                current = parent[current];
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find the longest increasing subsequence
     */
    public static int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return java.util.Arrays.stream(dp).max().getAsInt();
    }
    
    public static void main(String[] args) {
        // Test case: nums = [2,3,1,1,4]
        int[] nums1 = {2, 3, 1, 1, 4};
        
        System.out.println("Testing Jump Game:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        
        boolean result1 = canJump_greedy(nums1);
        System.out.println("Greedy result: " + result1);
        
        boolean result2 = canJump_dp(nums1);
        System.out.println("DP result: " + result2);
        
        boolean result3 = canJump_backtracking(nums1);
        System.out.println("Backtracking result: " + result3);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: nums = [3,2,1,0,4]
        int[] nums2 = {3, 2, 1, 0, 4};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        
        boolean result4 = canJump_greedy(nums2);
        System.out.println("Greedy result: " + result4);
        
        // Expected: false
        
        System.out.println();
        
        // Test case: nums = [0]
        int[] nums3 = {0};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        
        boolean result5 = canJump_greedy(nums3);
        System.out.println("Greedy result: " + result5);
        
        // Expected: true
        
        System.out.println();
        
        // Test helper methods
        int minJumps = minJumps(nums1);
        System.out.println("Minimum jumps needed: " + minJumps);
        
        java.util.List<java.util.List<Integer>> allPaths = findAllPaths(nums1);
        System.out.println("All possible paths: " + allPaths);
        
        int maxScore = maxScore(nums1);
        System.out.println("Maximum score: " + maxScore);
        
        boolean canReachWithK = canReachWithKJumps(nums1, 2);
        System.out.println("Can reach with 2 jumps: " + canReachWithK);
        
        java.util.List<Integer> shortestPath = shortestPath(nums1);
        System.out.println("Shortest path: " + shortestPath);
        
        int lis = longestIncreasingSubsequence(nums1);
        System.out.println("Longest increasing subsequence: " + lis);
        
        // Expected: Min jumps = 2, All paths = [[0,1,4], [0,2,3,4]], Max score = 10, Can reach with 2 jumps = true, Shortest path = [0,1,4], LIS = 3
    }
}

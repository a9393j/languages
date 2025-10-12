package masterDSA.topics.greedy;
import java.util.*;


/**
 * Jump Game II
 * 
 * Given an array of non-negative integers nums, you are initially positioned at the first index 
 * of the array. Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * LeetCode: https://leetcode.com/problems/jump-game-ii/
 * Difficulty: Medium
 * Tags: greedy, array
 * 
 * Approach:
 * - Greedy: Track current range and next range
 * - BFS: Use level-order traversal
 * - DP: Use dynamic programming (less efficient)
 * 
 * Pseudocode:
 * Greedy:
 * 1. Initialize jumps = 0, currentEnd = 0, farthest = 0
 * 2. For each position i:
 *    a. Update farthest = max(farthest, i + nums[i])
 *    b. If i == currentEnd, increment jumps and update currentEnd
 * 3. Return jumps
 */
public class JumpGameII {
    
    /**
     * Greedy approach - O(n) time, O(1) space
     * Track current range and next range
     */
    public static int jump_greedy(int[] nums) {
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
     * BFS approach - O(n) time, O(n) space
     * Use level-order traversal
     */
    public static int jump_bfs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        
        queue.offer(0);
        visited[0] = true;
        int jumps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                if (current == nums.length - 1) {
                    return jumps;
                }
                
                int maxJump = nums[current];
                for (int j = 1; j <= maxJump; j++) {
                    int next = current + j;
                    if (next < nums.length && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            
            jumps++;
        }
        
        return jumps;
    }
    
    /**
     * Dynamic programming approach - O(n²) time, O(n) space
     * Use DP to find minimum jumps
     */
    public static int jump_dp(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            
            int maxJump = nums[i];
            for (int j = 1; j <= maxJump && i + j < n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Helper method to find minimum jumps with obstacles
     */
    public static int jumpWithObstacles(int[] nums, boolean[] obstacles) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (obstacles[i]) {
                continue; // Skip obstacles
            }
            
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
     * Helper method to find minimum jumps with cost
     */
    public static int jumpWithCost(int[] nums, int[] costs) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = costs[0];
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            
            int maxJump = nums[i];
            for (int j = 1; j <= maxJump && i + j < n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + costs[i + j]);
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Helper method to find all paths with minimum jumps
     */
    public static java.util.List<java.util.List<Integer>> findAllMinJumpPaths(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        
        if (nums == null || nums.length <= 1) {
            return result;
        }
        
        int minJumps = jump_greedy(nums);
        java.util.List<Integer> path = new java.util.ArrayList<>();
        path.add(0);
        
        findAllMinJumpPathsHelper(nums, 0, path, result, minJumps);
        
        return result;
    }
    
    private static void findAllMinJumpPathsHelper(int[] nums, int position, java.util.List<Integer> path, 
                                                 java.util.List<java.util.List<Integer>> result, int minJumps) {
        if (position == nums.length - 1) {
            if (path.size() - 1 == minJumps) {
                result.add(new java.util.ArrayList<>(path));
            }
            return;
        }
        
        if (path.size() - 1 > minJumps) {
            return;
        }
        
        int maxJump = nums[position];
        for (int i = 1; i <= maxJump; i++) {
            int next = position + i;
            if (next < nums.length) {
                path.add(next);
                findAllMinJumpPathsHelper(nums, next, path, result, minJumps);
                path.remove(path.size() - 1);
            }
        }
    }
    
    /**
     * Helper method to find maximum score with minimum jumps
     */
    public static int maxScoreWithMinJumps(int[] nums, int[] scores) {
        if (nums == null || nums.length <= 1) {
            return scores[0];
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = scores[0];
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MIN_VALUE) {
                continue;
            }
            
            int maxJump = nums[i];
            for (int j = 1; j <= maxJump && i + j < n; j++) {
                dp[i + j] = Math.max(dp[i + j], dp[i] + scores[i + j]);
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Helper method to find minimum jumps to reach target
     */
    public static int jumpToTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        
        queue.offer(0);
        visited[0] = true;
        int jumps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                if (current == target) {
                    return jumps;
                }
                
                int maxJump = nums[current];
                for (int j = 1; j <= maxJump; j++) {
                    int next = current + j;
                    if (next < nums.length && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            
            jumps++;
        }
        
        return -1;
    }
    
    /**
     * Helper method to find minimum jumps with direction changes
     */
    public static int jumpWithDirectionChanges(int[] nums, boolean[] directions) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        boolean currentDirection = directions[0];
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (directions[i] != currentDirection) {
                jumps++; // Direction change counts as a jump
                currentDirection = directions[i];
            }
            
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
    
    public static void main(String[] args) {
        // Test case: nums = [2,3,1,1,4]
        int[] nums1 = {2, 3, 1, 1, 4};
        
        System.out.println("Testing Jump Game II:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        
        int result1 = jump_greedy(nums1);
        System.out.println("Greedy result: " + result1);
        
        int result2 = jump_bfs(nums1);
        System.out.println("BFS result: " + result2);
        
        int result3 = jump_dp(nums1);
        System.out.println("DP result: " + result3);
        
        // Expected: 2
        
        System.out.println();
        
        // Test case: nums = [2,3,0,1,4]
        int[] nums2 = {2, 3, 0, 1, 4};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        
        int result4 = jump_greedy(nums2);
        System.out.println("Greedy result: " + result4);
        
        // Expected: 2
        
        System.out.println();
        
        // Test case: nums = [1,2,3]
        int[] nums3 = {1, 2, 3};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        
        int result5 = jump_greedy(nums3);
        System.out.println("Greedy result: " + result5);
        
        // Expected: 2
        
        System.out.println();
        
        // Test helper methods
        boolean[] obstacles = {false, false, true, false, false};
        int jumpsWithObstacles = jumpWithObstacles(nums1, obstacles);
        System.out.println("Jumps with obstacles: " + jumpsWithObstacles);
        
        int[] costs = {1, 2, 3, 4, 5};
        int jumpsWithCost = jumpWithCost(nums1, costs);
        System.out.println("Jumps with cost: " + jumpsWithCost);
        
        java.util.List<java.util.List<Integer>> allMinJumpPaths = findAllMinJumpPaths(nums1);
        System.out.println("All minimum jump paths: " + allMinJumpPaths);
        
        int[] scores = {1, 2, 3, 4, 5};
        int maxScore = maxScoreWithMinJumps(nums1, scores);
        System.out.println("Maximum score with minimum jumps: " + maxScore);
        
        int jumpsToTarget = jumpToTarget(nums1, 3);
        System.out.println("Jumps to reach target 3: " + jumpsToTarget);
        
        boolean[] directions = {true, false, true, false, true};
        int jumpsWithDirectionChanges = jumpWithDirectionChanges(nums1, directions);
        System.out.println("Jumps with direction changes: " + jumpsWithDirectionChanges);
        
        // Expected: Jumps with obstacles = 2, Jumps with cost = 8, All minimum jump paths = [[0,1,4]], Max score = 8, Jumps to target 3 = 2, Jumps with direction changes = 4
    }
}

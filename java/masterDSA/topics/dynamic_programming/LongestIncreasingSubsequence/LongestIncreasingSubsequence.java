package masterDSA.topics.dynamic_programming.LongestIncreasingSubsequence;
import java.util.*;


/**
 * Longest Increasing Subsequence
 * 
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * 
 * LeetCode: https://leetcode.com/problems/longest-increasing-subsequence/
 * Difficulty: Medium
 * Tags: dynamic-programming, binary-search
 * 
 * Approach:
 * - DP: O(n²) time, O(n) space
 * - Binary Search: O(n log n) time, O(n) space
 * - Segment Tree: O(n log n) time, O(n) space
 * 
 * Pseudocode:
 * DP:
 * 1. Initialize dp array with 1
 * 2. For each i from 1 to n-1:
 *    a. For each j from 0 to i-1:
 *       if nums[j] < nums[i]: dp[i] = max(dp[i], dp[j] + 1)
 * 3. Return max(dp)
 */
public class LongestIncreasingSubsequence {
    
    /**
     * DP approach - O(n²) time, O(n) space
     * Use dynamic programming to find LIS
     */
    public static int lengthOfLIS_dp(int[] nums) {
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
    
    /**
     * Binary search approach - O(n log n) time, O(n) space
     * Use binary search to maintain increasing subsequence
     */
    public static int lengthOfLIS_binarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        java.util.List<Integer> tails = new java.util.ArrayList<>();
        
        for (int num : nums) {
            int pos = binarySearch(tails, num);
            
            if (pos == tails.size()) {
                tails.add(num);
            } else {
                tails.set(pos, num);
            }
        }
        
        return tails.size();
    }
    
    private static int binarySearch(java.util.List<Integer> tails, int target) {
        int left = 0;
        int right = tails.size();
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tails.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Segment tree approach - O(n log n) time, O(n) space
     * Use segment tree for range maximum queries
     */
    public static int lengthOfLIS_segmentTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Coordinate compression
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        int rank = 1;
        for (int num : sorted) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }
        
        SegmentTree st = new SegmentTree(rank);
        int result = 0;
        
        for (int num : nums) {
            int compressed = map.get(num);
            int maxLen = st.query(1, compressed - 1) + 1;
            st.update(compressed, maxLen);
            result = Math.max(result, maxLen);
        }
        
        return result;
    }
    
    /**
     * Segment Tree implementation
     */
    static class SegmentTree {
        private int[] tree;
        private int n;
        
        public SegmentTree(int size) {
            n = size;
            tree = new int[4 * n];
        }
        
        public void update(int pos, int val) {
            update(1, 1, n, pos, val);
        }
        
        private void update(int node, int start, int end, int pos, int val) {
            if (start == end) {
                tree[node] = val;
            } else {
                int mid = (start + end) / 2;
                if (pos <= mid) {
                    update(2 * node, start, mid, pos, val);
                } else {
                    update(2 * node + 1, mid + 1, end, pos, val);
                }
                tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
            }
        }
        
        public int query(int left, int right) {
            if (left > right) return 0;
            return query(1, 1, n, left, right);
        }
        
        private int query(int node, int start, int end, int left, int right) {
            if (start > right || end < left) {
                return 0;
            }
            if (start >= left && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return Math.max(
                query(2 * node, start, mid, left, right),
                query(2 * node + 1, mid + 1, end, left, right)
            );
        }
    }
    
    /**
     * Helper method to find the actual LIS sequence
     */
    public static java.util.List<Integer> findLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new java.util.ArrayList<>();
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        java.util.Arrays.fill(dp, 1);
        java.util.Arrays.fill(parent, -1);
        
        int maxLen = 1;
        int maxIndex = 0;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }
        
        java.util.List<Integer> result = new java.util.ArrayList<>();
        int current = maxIndex;
        while (current != -1) {
            result.add(0, nums[current]);
            current = parent[current];
        }
        
        return result;
    }
    
    /**
     * Helper method to find number of LIS
     */
    public static int numberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        java.util.Arrays.fill(dp, 1);
        java.util.Arrays.fill(count, 1);
        
        int maxLen = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                result += count[i];
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find longest decreasing subsequence
     */
    public static int lengthOfLDS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return java.util.Arrays.stream(dp).max().getAsInt();
    }
    
    /**
     * Helper method to find longest bitonic subsequence
     */
    public static int lengthOfLBS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] lis = new int[n];
        int[] lds = new int[n];
        
        // Calculate LIS from left to right
        java.util.Arrays.fill(lis, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        
        // Calculate LDS from right to left
        java.util.Arrays.fill(lds, 1);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, lis[i] + lds[i] - 1);
        }
        
        return maxLen;
    }
    
    /**
     * Helper method to find longest common subsequence
     */
    public static int lengthOfLCS(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        // Test case: nums = [10,9,2,5,3,7,101,18]
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        
        System.out.println("Testing Longest Increasing Subsequence:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        
        int result1 = lengthOfLIS_dp(nums1);
        System.out.println("DP result: " + result1);
        
        int result2 = lengthOfLIS_binarySearch(nums1);
        System.out.println("Binary search result: " + result2);
        
        int result3 = lengthOfLIS_segmentTree(nums1);
        System.out.println("Segment tree result: " + result3);
        
        // Expected: 4
        
        System.out.println();
        
        // Test case: nums = [0,1,0,3,2,3]
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        
        int result4 = lengthOfLIS_dp(nums2);
        System.out.println("DP result: " + result4);
        
        // Expected: 4
        
        System.out.println();
        
        // Test case: nums = [7,7,7,7,7,7,7]
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        
        int result5 = lengthOfLIS_dp(nums3);
        System.out.println("DP result: " + result5);
        
        // Expected: 1
        
        System.out.println();
        
        // Test helper methods
        java.util.List<Integer> lis = findLIS(nums1);
        System.out.println("LIS sequence: " + lis);
        
        int numLIS = numberOfLIS(nums1);
        System.out.println("Number of LIS: " + numLIS);
        
        int lds = lengthOfLDS(nums1);
        System.out.println("Longest decreasing subsequence: " + lds);
        
        int lbs = lengthOfLBS(nums1);
        System.out.println("Longest bitonic subsequence: " + lbs);
        
        int[] nums4 = {1, 3, 2, 4};
        int[] nums5 = {1, 2, 3, 4};
        int lcs = lengthOfLCS(nums4, nums5);
        System.out.println("Longest common subsequence: " + lcs);
        
        // Expected: LIS sequence = [2, 3, 7, 18], Number of LIS = 1, LDS = 3, LBS = 5, LCS = 3
    }
}

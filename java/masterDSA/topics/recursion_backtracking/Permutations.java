package masterDSA.topics.recursion_backtracking;
import java.util.*;


/**
 * Permutations
 * 
 * Given an array nums of distinct integers, return all the possible permutations. 
 * You can return the answer in any order.
 * 
 * LeetCode: https://leetcode.com/problems/permutations/
 * Difficulty: Medium
 * Tags: recursion, backtracking
 * 
 * Approach:
 * - Backtracking: Generate permutations using backtracking
 * - Iterative: Build permutations incrementally
 * - Heap's algorithm: Generate permutations in-place
 * 
 * Pseudocode:
 * Backtracking:
 * 1. Start with empty permutation
 * 2. For each unused element:
 *    a. Add element to current permutation
 *    b. Mark element as used
 *    c. Recurse with remaining elements
 *    d. Unmark element and remove from permutation
 * 3. When all elements used, add permutation to result
 */
public class Permutations {
    
    /**
     * Backtracking approach - O(n!) time, O(n!) space
     * Generate permutations using backtracking
     */
    public static java.util.List<java.util.List<Integer>> permute_backtracking(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, current, used, result);
        return result;
    }
    
    private static void backtrack(int[] nums, java.util.List<Integer> current, boolean[] used, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);
                used[i] = true;
                backtrack(nums, current, used, result);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }
    
    /**
     * Iterative approach - O(n!) time, O(n!) space
     * Build permutations incrementally
     */
    public static java.util.List<java.util.List<Integer>> permute_iterative(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        result.add(new java.util.ArrayList<>());
        
        for (int num : nums) {
            java.util.List<java.util.List<Integer>> newResult = new java.util.ArrayList<>();
            
            for (java.util.List<Integer> permutation : result) {
                for (int i = 0; i <= permutation.size(); i++) {
                    java.util.List<Integer> newPermutation = new java.util.ArrayList<>(permutation);
                    newPermutation.add(i, num);
                    newResult.add(newPermutation);
                }
            }
            
            result = newResult;
        }
        
        return result;
    }
    
    /**
     * Heap's algorithm approach - O(n!) time, O(n!) space
     * Generate permutations in-place
     */
    public static java.util.List<java.util.List<Integer>> permute_heaps(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        permuteHeapsHelper(nums, nums.length, result);
        return result;
    }
    
    private static void permuteHeapsHelper(int[] nums, int n, java.util.List<java.util.List<Integer>> result) {
        if (n == 1) {
            java.util.List<Integer> permutation = new java.util.ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            permuteHeapsHelper(nums, n - 1, result);
            
            if (n % 2 == 1) {
                swap(nums, 0, n - 1);
            } else {
                swap(nums, i, n - 1);
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Helper method to find permutations with duplicates
     */
    public static java.util.List<java.util.List<Integer>> permuteWithDuplicates(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        java.util.Arrays.sort(nums);
        backtrackWithDuplicates(nums, current, used, result);
        return result;
    }
    
    private static void backtrackWithDuplicates(int[] nums, java.util.List<Integer> current, boolean[] used, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            
            current.add(nums[i]);
            used[i] = true;
            backtrackWithDuplicates(nums, current, used, result);
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Helper method to find next permutation
     */
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        
        // Find the largest index i such that nums[i] < nums[i + 1]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            int j = nums.length - 1;
            
            // Find the largest index j such that nums[i] < nums[j]
            while (nums[j] <= nums[i]) {
                j--;
            }
            
            // Swap nums[i] and nums[j]
            swap(nums, i, j);
        }
        
        // Reverse the suffix starting at nums[i + 1]
        reverse(nums, i + 1);
    }
    
    private static void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    
    /**
     * Helper method to find kth permutation
     */
    public static String getKthPermutation(int n, int k) {
        java.util.List<Integer> numbers = new java.util.ArrayList<>();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }
        
        k--; // Convert to 0-based index
        StringBuilder result = new StringBuilder();
        
        for (int i = n; i > 0; i--) {
            int index = k / factorial[i - 1];
            result.append(numbers.get(index));
            numbers.remove(index);
            k %= factorial[i - 1];
        }
        
        return result.toString();
    }
    
    /**
     * Helper method to find permutations with constraints
     */
    public static java.util.List<java.util.List<Integer>> permuteWithConstraints(int[] nums, java.util.Map<Integer, java.util.Set<Integer>> constraints) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackWithConstraints(nums, current, used, constraints, result);
        return result;
    }
    
    private static void backtrackWithConstraints(int[] nums, java.util.List<Integer> current, boolean[] used, 
                                               java.util.Map<Integer, java.util.Set<Integer>> constraints, 
                                               java.util.List<java.util.List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // Check constraints
                boolean valid = true;
                if (!current.isEmpty()) {
                    int lastElement = current.get(current.size() - 1);
                    if (constraints.containsKey(lastElement) && constraints.get(lastElement).contains(nums[i])) {
                        valid = false;
                    }
                }
                
                if (valid) {
                    current.add(nums[i]);
                    used[i] = true;
                    backtrackWithConstraints(nums, current, used, constraints, result);
                    used[i] = false;
                    current.remove(current.size() - 1);
                }
            }
        }
    }
    
    /**
     * Helper method to find permutations with sum
     */
    public static java.util.List<java.util.List<Integer>> permuteWithSum(int[] nums, int targetSum) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackWithSum(nums, current, used, targetSum, 0, result);
        return result;
    }
    
    private static void backtrackWithSum(int[] nums, java.util.List<Integer> current, boolean[] used, 
                                       int targetSum, int currentSum, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == nums.length && currentSum == targetSum) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        if (current.size() >= nums.length || currentSum > targetSum) {
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);
                used[i] = true;
                backtrackWithSum(nums, current, used, targetSum, currentSum + nums[i], result);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }
    
    /**
     * Helper method to find permutations with product
     */
    public static java.util.List<java.util.List<Integer>> permuteWithProduct(int[] nums, int targetProduct) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackWithProduct(nums, current, used, targetProduct, 1, result);
        return result;
    }
    
    private static void backtrackWithProduct(int[] nums, java.util.List<Integer> current, boolean[] used, 
                                           int targetProduct, int currentProduct, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == nums.length && currentProduct == targetProduct) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        if (current.size() >= nums.length || currentProduct > targetProduct) {
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);
                used[i] = true;
                backtrackWithProduct(nums, current, used, targetProduct, currentProduct * nums[i], result);
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }
    
    /**
     * Helper method to find circular permutations
     */
    public static java.util.List<java.util.List<Integer>> permuteCircular(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.List<Integer> current = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackCircular(nums, current, used, result);
        return result;
    }
    
    private static void backtrackCircular(int[] nums, java.util.List<Integer> current, boolean[] used, java.util.List<java.util.List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new java.util.ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // Check if this forms a valid circular permutation
                if (current.isEmpty() || Math.abs(nums[i] - current.get(current.size() - 1)) <= 1) {
                    current.add(nums[i]);
                    used[i] = true;
                    backtrackCircular(nums, current, used, result);
                    used[i] = false;
                    current.remove(current.size() - 1);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // Test case: nums = [1,2,3]
        int[] nums1 = {1, 2, 3};
        
        System.out.println("Testing Permutations:");
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        
        java.util.List<java.util.List<Integer>> result1 = permute_backtracking(nums1);
        System.out.println("Backtracking result: " + result1);
        
        java.util.List<java.util.List<Integer>> result2 = permute_iterative(nums1);
        System.out.println("Iterative result: " + result2);
        
        java.util.List<java.util.List<Integer>> result3 = permute_heaps(nums1);
        System.out.println("Heap's algorithm result: " + result3);
        
        // Expected: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
        
        System.out.println();
        
        // Test case: nums = [0,1]
        int[] nums2 = {0, 1};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        
        java.util.List<java.util.List<Integer>> result4 = permute_backtracking(nums2);
        System.out.println("Backtracking result: " + result4);
        
        // Expected: [[0,1], [1,0]]
        
        System.out.println();
        
        // Test case: nums = [1]
        int[] nums3 = {1};
        
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        
        java.util.List<java.util.List<Integer>> result5 = permute_backtracking(nums3);
        System.out.println("Backtracking result: " + result5);
        
        // Expected: [[1]]
        
        System.out.println();
        
        // Test helper methods
        int[] nums4 = {1, 1, 2};
        java.util.List<java.util.List<Integer>> withDuplicates = permuteWithDuplicates(nums4);
        System.out.println("Permutations with duplicates: " + withDuplicates);
        
        int[] nums5 = {1, 2, 3};
        nextPermutation(nums5);
        System.out.println("Next permutation: " + java.util.Arrays.toString(nums5));
        
        String kthPermutation = getKthPermutation(3, 3);
        System.out.println("3rd permutation of [1,2,3]: " + kthPermutation);
        
        java.util.Map<Integer, java.util.Set<Integer>> constraints = new java.util.HashMap<>();
        java.util.Set<Integer> forbidden = new java.util.HashSet<>();
        forbidden.add(2);
        constraints.put(1, forbidden);
        java.util.List<java.util.List<Integer>> withConstraints = permuteWithConstraints(nums1, constraints);
        System.out.println("Permutations with constraints: " + withConstraints);
        
        java.util.List<java.util.List<Integer>> withSum = permuteWithSum(nums1, 6);
        System.out.println("Permutations with sum 6: " + withSum);
        
        java.util.List<java.util.List<Integer>> withProduct = permuteWithProduct(nums1, 6);
        System.out.println("Permutations with product 6: " + withProduct);
        
        java.util.List<java.util.List<Integer>> circular = permuteCircular(nums1);
        System.out.println("Circular permutations: " + circular);
        
        // Expected: With duplicates = [[1,1,2], [1,2,1], [2,1,1]], Next permutation = [1,3,2], 3rd permutation = "213", With constraints = [[1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]], With sum 6 = [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]], With product 6 = [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]], Circular = [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
    }
}

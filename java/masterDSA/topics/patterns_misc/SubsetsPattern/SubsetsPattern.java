package masterDSA.topics.patterns_misc.SubsetsPattern;
import java.util.*;


/**
 * Subsets Pattern
 * 
 * Problem Statement:
 * The subsets pattern is used for problems involving generating all possible
 * subsets, combinations, or permutations of a given set of elements.
 * 
 * LeetCode Link: https://leetcode.com/tag/subsets/
 * Difficulty: Medium
 * Tags: Array, Backtracking, Bit Manipulation
 * 
 * Approach Summary:
 * 1. Backtracking: Generate subsets recursively
 * 2. Bit Manipulation: Use bit masks to represent subsets
 * 3. Iterative: Build subsets incrementally
 * 4. Handle duplicates and constraints
 * 
 * Pseudocode:
 * 1. Start with empty subset
 * 2. For each element, either include or exclude it
 * 3. Recursively generate subsets
 * 4. Backtrack when necessary
 * 5. Add valid subsets to result
 * 
 * Time Complexity: O(2^n) - exponential time
 * Space Complexity: O(2^n) - exponential space
 */
public class SubsetsPattern {
    
    /**
     * Generate all subsets using backtracking
     * 
     * @param nums Array of integers
     * @return List of all possible subsets
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(current));
        
        // Generate subsets starting from current index
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsets(nums, i + 1, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
    
    /**
     * Generate all subsets using bit manipulation
     * 
     * @param nums Array of integers
     * @return List of all possible subsets
     */
    public static List<List<Integer>> subsetsBitManipulation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        
        int n = nums.length;
        int totalSubsets = 1 << n; // 2^n
        
        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        
        return result;
    }
    
    /**
     * Generate all subsets iteratively
     * 
     * @param nums Array of integers
     * @return List of all possible subsets
     */
    public static List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        
        result.add(new ArrayList<>()); // Start with empty subset
        
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        
        return result;
    }
    
    /**
     * Generate all subsets with duplicates
     * 
     * @param nums Array of integers (may contain duplicates)
     * @return List of all possible subsets
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        
        Arrays.sort(nums); // Sort to handle duplicates
        generateSubsetsWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsetsWithDup(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        
        for (int i = index; i < nums.length; i++) {
            // Skip duplicates
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            
            current.add(nums[i]);
            generateSubsetsWithDup(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Generate all combinations of size k
     * 
     * @param n Range [1, n]
     * @param k Size of combinations
     * @return List of all combinations
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return result;
        }
        
        generateCombinations(n, k, 1, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateCombinations(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            current.add(i);
            generateCombinations(n, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Generate all permutations
     * 
     * @param nums Array of integers
     * @return List of all permutations
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        generatePermutations(nums, new ArrayList<>(), result);
        return result;
    }
    
    private static void generatePermutations(int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) {
                continue; // Skip if already used
            }
            
            current.add(nums[i]);
            generatePermutations(nums, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Generate all permutations with duplicates
     * 
     * @param nums Array of integers (may contain duplicates)
     * @return List of all permutations
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums); // Sort to handle duplicates
        boolean[] used = new boolean[nums.length];
        generatePermutationsUnique(nums, used, new ArrayList<>(), result);
        return result;
    }
    
    private static void generatePermutationsUnique(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            
            used[i] = true;
            current.add(nums[i]);
            generatePermutationsUnique(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
    
    /**
     * Generate all combinations that sum to target
     * 
     * @param candidates Array of candidates
     * @param target Target sum
     * @return List of all combinations
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        
        Arrays.sort(candidates);
        generateCombinationSum(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateCombinationSum(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break; // No need to continue if current candidate is too large
            }
            
            current.add(candidates[i]);
            generateCombinationSum(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Generate all combinations that sum to target (each number used once)
     * 
     * @param candidates Array of candidates
     * @param target Target sum
     * @return List of all combinations
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        
        Arrays.sort(candidates);
        generateCombinationSum2(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateCombinationSum2(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            
            // Skip duplicates
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            current.add(candidates[i]);
            generateCombinationSum2(candidates, target - candidates[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Generate all subsets that sum to target
     * 
     * @param nums Array of integers
     * @param target Target sum
     * @return List of all subsets
     */
    public static List<List<Integer>> subsetSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        generateSubsetSum(nums, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsetSum(int[] nums, int target, int index, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        if (index >= nums.length || target < 0) {
            return;
        }
        
        // Include current element
        current.add(nums[index]);
        generateSubsetSum(nums, target - nums[index], index + 1, current, result);
        current.remove(current.size() - 1);
        
        // Exclude current element
        generateSubsetSum(nums, target, index + 1, current, result);
    }
    
    /**
     * Generate all subsets of size k
     * 
     * @param nums Array of integers
     * @param k Size of subsets
     * @return List of all subsets of size k
     */
    public static List<List<Integer>> subsetsOfSizeK(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return result;
        }
        
        generateSubsetsOfSizeK(nums, k, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateSubsetsOfSizeK(int[] nums, int k, int index, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsetsOfSizeK(nums, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Helper method to print list of lists
     */
    private static void printSubsets(List<List<Integer>> subsets) {
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
    
    /**
     * Demonstration method showing various subsets patterns
     */
    public static void runExample() {
        System.out.println("=== Subsets Pattern Examples ===");
        
        // Generate all subsets
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> subsets = subsets(nums1);
        System.out.println("All subsets of [1, 2, 3]:");
        printSubsets(subsets);
        
        // Generate subsets with bit manipulation
        int[] nums2 = {1, 2, 3};
        List<List<Integer>> subsetsBit = subsetsBitManipulation(nums2);
        System.out.println("\nAll subsets (bit manipulation) of [1, 2, 3]:");
        printSubsets(subsetsBit);
        
        // Generate subsets iteratively
        int[] nums3 = {1, 2, 3};
        List<List<Integer>> subsetsIter = subsetsIterative(nums3);
        System.out.println("\nAll subsets (iterative) of [1, 2, 3]:");
        printSubsets(subsetsIter);
        
        // Generate subsets with duplicates
        int[] nums4 = {1, 2, 2};
        List<List<Integer>> subsetsDup = subsetsWithDup(nums4);
        System.out.println("\nAll subsets of [1, 2, 2]:");
        printSubsets(subsetsDup);
        
        // Generate combinations
        List<List<Integer>> combinations = combine(4, 2);
        System.out.println("\nAll combinations of 4 choose 2:");
        printSubsets(combinations);
        
        // Generate permutations
        int[] nums5 = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums5);
        System.out.println("\nAll permutations of [1, 2, 3]:");
        printSubsets(permutations);
        
        // Generate permutations with duplicates
        int[] nums6 = {1, 1, 2};
        List<List<Integer>> permutationsDup = permuteUnique(nums6);
        System.out.println("\nAll permutations of [1, 1, 2]:");
        printSubsets(permutationsDup);
        
        // Generate combination sum
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        List<List<Integer>> combinationSum = combinationSum(candidates1, target1);
        System.out.println("\nCombination sum for target " + target1 + ":");
        printSubsets(combinationSum);
        
        // Generate combination sum 2
        int[] candidates2 = {10, 1, 2, 7, 6, 1, 5};
        int target2 = 8;
        List<List<Integer>> combinationSum2 = combinationSum2(candidates2, target2);
        System.out.println("\nCombination sum 2 for target " + target2 + ":");
        printSubsets(combinationSum2);
        
        // Generate subset sum
        int[] nums7 = {1, 2, 3, 4, 5};
        int target3 = 6;
        List<List<Integer>> subsetSum = subsetSum(nums7, target3);
        System.out.println("\nSubset sum for target " + target3 + ":");
        printSubsets(subsetSum);
        
        // Generate subsets of size k
        int[] nums8 = {1, 2, 3, 4};
        int k = 2;
        List<List<Integer>> subsetsK = subsetsOfSizeK(nums8, k);
        System.out.println("\nSubsets of size " + k + ":");
        printSubsets(subsetsK);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

package masterDSA.topics.patterns_misc.CyclicSortPattern;
import java.util.*;


/**
 * Cyclic Sort Pattern
 * 
 * Problem Statement:
 * The cyclic sort pattern is used for problems involving arrays containing numbers
 * in a given range. It sorts the array in-place with O(n) time complexity.
 * 
 * LeetCode Link: https://leetcode.com/tag/cyclic-sort/
 * Difficulty: Easy to Medium
 * Tags: Array, Sorting, In-place
 * 
 * Approach Summary:
 * 1. Use array indices as hash table
 * 2. Place each number at its correct position
 * 3. Handle duplicates and missing numbers
 * 4. Solve problems involving range [1, n] or [0, n-1]
 * 
 * Pseudocode:
 * 1. Iterate through array
 * 2. For each element, place it at correct index
 * 3. If element is already at correct position, move to next
 * 4. Continue until all elements are in correct positions
 * 
 * Time Complexity: O(n) - each element visited at most twice
 * Space Complexity: O(1) - sorting in-place
 */
public class CyclicSortPattern {
    
    /**
     * Basic cyclic sort for array containing numbers 1 to n
     * 
     * @param nums Array containing numbers 1 to n
     * @return Sorted array
     */
    public static int[] cyclicSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1; // For numbers 1 to n
            
            if (nums[i] != nums[correctIndex]) {
                // Swap elements
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
        
        return nums;
    }
    
    /**
     * Find missing number in array containing numbers 0 to n
     * 
     * @param nums Array containing numbers 0 to n (missing one)
     * @return Missing number
     */
    public static int findMissingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int i = 0;
        
        // Place each number at its correct index
        while (i < n) {
            if (nums[i] < n && nums[i] != i) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }
        
        // Find the missing number
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        
        return n; // All numbers 0 to n-1 are present, missing is n
    }
    
    /**
     * Find all missing numbers in array containing numbers 1 to n
     * 
     * @param nums Array containing numbers 1 to n
     * @return List of missing numbers
     */
    public static List<Integer> findMissingNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        int i = 0;
        
        // Place each number at its correct index
        while (i < n) {
            int correctIndex = nums[i] - 1;
            if (nums[i] <= n && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
        
        // Find missing numbers
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        
        return result;
    }
    
    /**
     * Find duplicate number in array containing numbers 1 to n
     * 
     * @param nums Array containing numbers 1 to n (one duplicate)
     * @return Duplicate number
     */
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
        
        // Find duplicate
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }
        
        return -1;
    }
    
    /**
     * Find all duplicates in array containing numbers 1 to n
     * 
     * @param nums Array containing numbers 1 to n
     * @return List of duplicate numbers
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return result;
        }
        
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
        
        // Find duplicates
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }
        
        return result;
    }
    
    /**
     * Find first missing positive number
     * 
     * @param nums Array of integers
     * @return First missing positive number
     */
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        int n = nums.length;
        int i = 0;
        
        // Place each positive number at its correct index
        while (i < n) {
            int correctIndex = nums[i] - 1;
            
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
        
        // Find first missing positive
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        return n + 1; // All numbers 1 to n are present
    }
    
    /**
     * Find all numbers disappeared in array
     * 
     * @param nums Array containing numbers 1 to n
     * @return List of disappeared numbers
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        int i = 0;
        
        // Place each number at its correct index
        while (i < n) {
            int correctIndex = nums[i] - 1;
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
        
        // Find disappeared numbers
        for (i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        
        return result;
    }
    
    /**
     * Set matrix zeroes using cyclic sort approach
     * 
     * @param matrix 2D matrix
     * @return Matrix with zeroes set
     */
    public static int[][] setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        
        // Check if first row has zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        
        // Check if first column has zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        
        // Use first row and column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // Set zeroes based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Set first row and column
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        
        return matrix;
    }
    
    /**
     * Find the duplicate number using Floyd's cycle detection
     * 
     * @param nums Array containing numbers 1 to n (one duplicate)
     * @return Duplicate number
     */
    public static int findDuplicateFloyd(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        // Phase 1: Detect cycle
        int slow = nums[0];
        int fast = nums[0];
        
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Phase 2: Find cycle start
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    /**
     * Find all duplicate numbers using negative marking
     * 
     * @param nums Array containing numbers 1 to n
     * @return List of duplicate numbers
     */
    public static List<Integer> findDuplicatesNegative(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return result;
        }
        
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            
            if (nums[index] < 0) {
                result.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to swap elements in array
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Helper method to print array
     */
    private static void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
    
    /**
     * Helper method to print 2D array
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    /**
     * Demonstration method showing various cyclic sort patterns
     */
    public static void runExample() {
        System.out.println("=== Cyclic Sort Pattern Examples ===");
        
        // Basic cyclic sort
        int[] nums1 = {3, 1, 4, 2, 5};
        int[] sorted = cyclicSort(nums1.clone());
        System.out.println("Original: " + Arrays.toString(nums1));
        System.out.println("Sorted: " + Arrays.toString(sorted));
        
        // Find missing number
        int[] nums2 = {3, 0, 1};
        int missing = findMissingNumber(nums2.clone());
        System.out.println("Missing number: " + missing);
        
        // Find all missing numbers
        int[] nums3 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> missingNumbers = findMissingNumbers(nums3.clone());
        System.out.println("Missing numbers: " + missingNumbers);
        
        // Find duplicate number
        int[] nums4 = {1, 3, 4, 2, 2};
        int duplicate = findDuplicate(nums4.clone());
        System.out.println("Duplicate number: " + duplicate);
        
        // Find all duplicates
        int[] nums5 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> duplicates = findDuplicates(nums5.clone());
        System.out.println("Duplicate numbers: " + duplicates);
        
        // First missing positive
        int[] nums6 = {3, 4, -1, 1};
        int firstMissing = firstMissingPositive(nums6.clone());
        System.out.println("First missing positive: " + firstMissing);
        
        // Find disappeared numbers
        int[] nums7 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> disappeared = findDisappearedNumbers(nums7.clone());
        System.out.println("Disappeared numbers: " + disappeared);
        
        // Set matrix zeroes
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] zeroMatrix = setZeroes(matrix.clone());
        System.out.println("Matrix after setting zeroes:");
        printMatrix(zeroMatrix);
        
        // Find duplicate using Floyd's algorithm
        int[] nums8 = {1, 3, 4, 2, 2};
        int duplicateFloyd = findDuplicateFloyd(nums8.clone());
        System.out.println("Duplicate (Floyd's): " + duplicateFloyd);
        
        // Find duplicates using negative marking
        int[] nums9 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> duplicatesNegative = findDuplicatesNegative(nums9.clone());
        System.out.println("Duplicates (negative marking): " + duplicatesNegative);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

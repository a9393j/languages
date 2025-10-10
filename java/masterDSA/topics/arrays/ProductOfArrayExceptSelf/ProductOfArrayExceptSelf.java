package masterDSA.topics.arrays.ProductOfArrayExceptSelf;
import java.util.*;


/**
 * Product of Array Except Self
 * 
 * Given an integer array nums, return an array answer such that answer[i] is equal to 
 * the product of all the elements of nums except nums[i]. The product of any prefix or 
 * suffix of nums is guaranteed to fit in a 32-bit integer. You must write an algorithm 
 * that runs in O(n) time and without using the division operator.
 * 
 * LeetCode: https://leetcode.com/problems/product-of-array-except-self/
 * Difficulty: Medium
 * Tags: prefix-suffix, no-division
 * 
 * Approach:
 * - Use prefix and suffix products to avoid division
 * - First pass: calculate prefix products (product of all elements to the left)
 * - Second pass: calculate suffix products and multiply with prefix products
 * - Alternative: Use two arrays for prefix and suffix products
 * 
 * Pseudocode:
 * 1. Initialize result array with 1s
 * 2. First pass (left to right):
 *    a. result[i] = product of all elements to the left of i
 * 3. Second pass (right to left):
 *    a. result[i] *= product of all elements to the right of i
 * 4. Return result
 */
public class ProductOfArrayExceptSelf {
    
    /**
     * Two passes approach - O(n) time, O(1) space (excluding output)
     * Use result array to store prefix products, then multiply with suffix products
     */
    public static int[] productExceptSelf_twoPasses(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // First pass: calculate prefix products
        // result[i] = product of all elements to the left of i
        result[0] = 1; // No elements to the left of first element
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Second pass: multiply with suffix products
        // result[i] *= product of all elements to the right of i
        int suffixProduct = 1; // Product of all elements to the right
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i]; // Update suffix product for next iteration
        }
        
        return result;
    }
    
    /**
     * Two arrays approach - O(n) time, O(n) space
     * Use separate arrays for prefix and suffix products
     */
    public static int[] productExceptSelf_twoArrays(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];
        
        // Calculate prefix products
        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        
        // Calculate suffix products
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        
        // Calculate result
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        
        return result;
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * For each element, calculate product of all other elements
     */
    public static int[] productExceptSelf_bruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }
        
        return result;
    }
    
    /**
     * Division approach - O(n) time, O(1) space
     * Note: This violates the "no division" constraint but is included for completeness
     */
    public static int[] productExceptSelf_division(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate total product
        int totalProduct = 1;
        int zeroCount = 0;
        int zeroIndex = -1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                zeroIndex = i;
            } else {
                totalProduct *= nums[i];
            }
        }
        
        // Handle different cases
        if (zeroCount > 1) {
            // Multiple zeros: all products are 0
            java.util.Arrays.fill(result, 0);
        } else if (zeroCount == 1) {
            // One zero: only the zero index has non-zero product
            java.util.Arrays.fill(result, 0);
            result[zeroIndex] = totalProduct;
        } else {
            // No zeros: divide total product by each element
            for (int i = 0; i < n; i++) {
                result[i] = totalProduct / nums[i];
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test case: nums = [1,2,3,4]
        int[] nums = {1, 2, 3, 4};
        
        System.out.println("Testing Product of Array Except Self:");
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        
        int[] result1 = productExceptSelf_twoPasses(nums);
        System.out.println("Two passes result: " + java.util.Arrays.toString(result1));
        
        int[] result2 = productExceptSelf_twoArrays(nums);
        System.out.println("Two arrays result: " + java.util.Arrays.toString(result2));
        
        int[] result3 = productExceptSelf_bruteForce(nums);
        System.out.println("Brute force result: " + java.util.Arrays.toString(result3));
        
        int[] result4 = productExceptSelf_division(nums);
        System.out.println("Division result: " + java.util.Arrays.toString(result4));
        
        // Expected: [24,12,8,6]
        
        System.out.println();
        
        // Test case with zero: nums = [1,2,0,4]
        int[] numsWithZero = {1, 2, 0, 4};
        System.out.println("Array with zero: " + java.util.Arrays.toString(numsWithZero));
        
        int[] result5 = productExceptSelf_twoPasses(numsWithZero);
        System.out.println("Two passes result: " + java.util.Arrays.toString(result5));
        
        // Expected: [0,0,8,0]
    }
}

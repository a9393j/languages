package masterDSA.topics.arrays;
import java.util.*;


/**
 * First Duplicate Value
 * 
 * Given an array of integers between 1 and n, where n is the length of the array, 
 * write a function that returns the first integer that appears more than once 
 * (when the array is read from left to right). If no integer appears more than once, 
 * return -1.
 * 
 * LeetCode: https://leetcode.com/problems/contains-duplicate/
 * Difficulty: Easy
 * Tags: hashing
 * 
 * Approach:
 * - Use HashSet to track seen elements
 * - Return first element that's already in the set
 * - Alternative: Use array as hash table since values are 1 to n
 * - Alternative: Use Floyd's cycle detection algorithm
 * 
 * Pseudocode:
 * 1. Create HashSet to track seen elements
 * 2. For each element in array:
 *    a. If element is already in set, return it
 *    b. Else add element to set
 * 3. Return -1 if no duplicates found
 */
public class FirstDuplicateValue {
    
    /**
     * HashSet approach - O(n) time, O(n) space
     * Use HashSet to track seen elements
     */
    public static int firstDuplicateValue_hashSet(int[] array) {
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        
        for (int num : array) {
            if (seen.contains(num)) {
                return num; // First duplicate found
            }
            seen.add(num);
        }
        
        return -1; // No duplicates found
    }
    
    /**
     * Array as hash table approach - O(n) time, O(1) space
     * Use the array itself as a hash table since values are 1 to n
     * Mark visited elements by making them negative
     */
    public static int firstDuplicateValue_arrayHash(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int absValue = Math.abs(array[i]);
            int index = absValue - 1; // Convert to 0-based index
            
            // If the value at this index is negative, we've seen this value before
            if (array[index] < 0) {
                return absValue;
            }
            
            // Mark this value as seen by making it negative
            array[index] = -array[index];
        }
        
        return -1; // No duplicates found
    }
    
    /**
     * Floyd's cycle detection approach - O(n) time, O(1) space
     * Treat the array as a linked list where array[i] points to array[array[i]-1]
     * Find the start of the cycle
     */
    public static int firstDuplicateValue_floydCycle(int[] array) {
        // Phase 1: Find the meeting point
        int slow = array[0];
        int fast = array[0];
        
        do {
            slow = array[slow - 1]; // Move one step
            fast = array[array[fast - 1] - 1]; // Move two steps
        } while (slow != fast);
        
        // Phase 2: Find the start of the cycle
        slow = array[0];
        while (slow != fast) {
            slow = array[slow - 1];
            fast = array[fast - 1];
        }
        
        return slow;
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * For each element, check if it appears later in the array
     */
    public static int firstDuplicateValue_bruteForce(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    return array[i];
                }
            }
        }
        return -1;
    }
    
    /**
     * Sorting approach - O(n log n) time, O(1) space
     * Sort array and check adjacent elements
     * Note: This doesn't preserve the "first" duplicate order
     */
    public static int firstDuplicateValue_sorting(int[] array) {
        java.util.Arrays.sort(array);
        
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                return array[i];
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        // Test case: array = [2, 1, 5, 2, 3, 3, 4]
        int[] array = {2, 1, 5, 2, 3, 3, 4};
        
        System.out.println("Testing First Duplicate Value:");
        System.out.println("Array: " + java.util.Arrays.toString(array));
        
        int result1 = firstDuplicateValue_hashSet(array);
        System.out.println("HashSet result: " + result1);
        
        // Create a copy for array hash approach since it modifies the array
        int[] arrayCopy = array.clone();
        int result2 = firstDuplicateValue_arrayHash(arrayCopy);
        System.out.println("Array hash result: " + result2);
        
        // Create a copy for Floyd's cycle approach
        int[] arrayCopy2 = array.clone();
        int result3 = firstDuplicateValue_floydCycle(arrayCopy2);
        System.out.println("Floyd's cycle result: " + result3);
        
        int result4 = firstDuplicateValue_bruteForce(array);
        System.out.println("Brute force result: " + result4);
        
        int result5 = firstDuplicateValue_sorting(array);
        System.out.println("Sorting result: " + result5);
        
        // Expected: 2 (first duplicate when reading left to right)
        
        System.out.println();
        
        // Test case with no duplicates: array = [1, 2, 3, 4, 5]
        int[] noDuplicates = {1, 2, 3, 4, 5};
        System.out.println("Array with no duplicates: " + java.util.Arrays.toString(noDuplicates));
        
        int result6 = firstDuplicateValue_hashSet(noDuplicates);
        System.out.println("HashSet result: " + result6);
        
        // Expected: -1
    }
}

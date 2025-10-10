package masterDSA.topics.arrays.SmallestDifferenceBetweenTwoArrays;
import java.util.*;


/**
 * Smallest Difference Between Two Arrays
 * 
 * Given two arrays of integers, compute the pair of values (one value in each array) 
 * with the smallest (non-negative) difference. Return the difference.
 * 
 * LeetCode: https://leetcode.com/problems/minimum-absolute-difference/
 * Difficulty: Medium
 * Tags: two-arrays, two-pointers
 * 
 * Approach:
 * - Sort both arrays to enable two-pointer technique
 * - Use two pointers to find the pair with minimum difference
 * - Move the pointer pointing to the smaller value
 * 
 * Pseudocode:
 * 1. Sort both arrays
 * 2. Initialize two pointers at start of both arrays
 * 3. Initialize minDiff = Integer.MAX_VALUE
 * 4. While both pointers are valid:
 *    a. Calculate current difference
 *    b. Update minDiff if current difference is smaller
 *    c. Move pointer pointing to smaller value
 * 5. Return minDiff
 */
public class SmallestDifferenceBetweenTwoArrays {
    
    /**
     * Two pointers approach - O(n log n + m log m) time, O(1) space
     * Sort both arrays and use two pointers to find minimum difference
     */
    public static int smallestDifference_twoPointers(int[] arrayOne, int[] arrayTwo) {
        // Sort both arrays to enable two-pointer technique
        java.util.Arrays.sort(arrayOne);
        java.util.Arrays.sort(arrayTwo);
        
        int indexOne = 0;
        int indexTwo = 0;
        int minDiff = Integer.MAX_VALUE;
        
        while (indexOne < arrayOne.length && indexTwo < arrayTwo.length) {
            int currentDiff = Math.abs(arrayOne[indexOne] - arrayTwo[indexTwo]);
            minDiff = Math.min(minDiff, currentDiff);
            
            // Move pointer pointing to smaller value
            if (arrayOne[indexOne] < arrayTwo[indexTwo]) {
                indexOne++;
            } else {
                indexTwo++;
            }
        }
        
        return minDiff;
    }
    
    /**
     * Two pointers approach that returns the actual pair
     * O(n log n + m log m) time, O(1) space
     */
    public static int[] smallestDifferencePair(int[] arrayOne, int[] arrayTwo) {
        java.util.Arrays.sort(arrayOne);
        java.util.Arrays.sort(arrayTwo);
        
        int indexOne = 0;
        int indexTwo = 0;
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];
        
        while (indexOne < arrayOne.length && indexTwo < arrayTwo.length) {
            int currentDiff = Math.abs(arrayOne[indexOne] - arrayTwo[indexTwo]);
            
            if (currentDiff < minDiff) {
                minDiff = currentDiff;
                result[0] = arrayOne[indexOne];
                result[1] = arrayTwo[indexTwo];
            }
            
            // Move pointer pointing to smaller value
            if (arrayOne[indexOne] < arrayTwo[indexTwo]) {
                indexOne++;
            } else {
                indexTwo++;
            }
        }
        
        return result;
    }
    
    /**
     * Brute force approach - O(n * m) time, O(1) space
     * Check all possible pairs
     */
    public static int smallestDifference_bruteForce(int[] arrayOne, int[] arrayTwo) {
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < arrayOne.length; i++) {
            for (int j = 0; j < arrayTwo.length; j++) {
                int currentDiff = Math.abs(arrayOne[i] - arrayTwo[j]);
                minDiff = Math.min(minDiff, currentDiff);
            }
        }
        
        return minDiff;
    }
    
    /**
     * Binary search approach - O(n log m) time, O(1) space
     * For each element in arrayOne, find closest element in arrayTwo using binary search
     */
    public static int smallestDifference_binarySearch(int[] arrayOne, int[] arrayTwo) {
        java.util.Arrays.sort(arrayTwo);
        int minDiff = Integer.MAX_VALUE;
        
        for (int num : arrayOne) {
            int closestIndex = findClosestIndex(arrayTwo, num);
            int currentDiff = Math.abs(num - arrayTwo[closestIndex]);
            minDiff = Math.min(minDiff, currentDiff);
        }
        
        return minDiff;
    }
    
    /**
     * Helper method to find the index of the closest element using binary search
     */
    private static int findClosestIndex(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Check if left-1 is closer
        if (left > 0 && Math.abs(array[left - 1] - target) < Math.abs(array[left] - target)) {
            return left - 1;
        }
        
        return left;
    }
    
    /**
     * Hash set approach - O(n + m) time, O(n + m) space
     * Store all elements in sets and find minimum difference
     */
    public static int smallestDifference_hashSet(int[] arrayOne, int[] arrayTwo) {
        java.util.Set<Integer> setOne = new java.util.HashSet<>();
        java.util.Set<Integer> setTwo = new java.util.HashSet<>();
        
        for (int num : arrayOne) {
            setOne.add(num);
        }
        
        for (int num : arrayTwo) {
            setTwo.add(num);
        }
        
        int minDiff = Integer.MAX_VALUE;
        
        // Check for exact matches first
        for (int num : setOne) {
            if (setTwo.contains(num)) {
                return 0;
            }
        }
        
        // Find minimum difference
        for (int num1 : setOne) {
            for (int num2 : setTwo) {
                minDiff = Math.min(minDiff, Math.abs(num1 - num2));
            }
        }
        
        return minDiff;
    }
    
    public static void main(String[] args) {
        // Test case: arrayOne = [-1, 5, 10, 20, 28, 3], arrayTwo = [26, 134, 135, 15, 17]
        int[] arrayOne = {-1, 5, 10, 20, 28, 3};
        int[] arrayTwo = {26, 134, 135, 15, 17};
        
        System.out.println("Testing Smallest Difference Between Two Arrays:");
        System.out.println("Array One: " + java.util.Arrays.toString(arrayOne));
        System.out.println("Array Two: " + java.util.Arrays.toString(arrayTwo));
        
        int result1 = smallestDifference_twoPointers(arrayOne, arrayTwo);
        System.out.println("Two pointers result: " + result1);
        
        int[] pair = smallestDifferencePair(arrayOne, arrayTwo);
        System.out.println("Closest pair: " + java.util.Arrays.toString(pair));
        
        int result2 = smallestDifference_bruteForce(arrayOne, arrayTwo);
        System.out.println("Brute force result: " + result2);
        
        int result3 = smallestDifference_binarySearch(arrayOne, arrayTwo);
        System.out.println("Binary search result: " + result3);
        
        int result4 = smallestDifference_hashSet(arrayOne, arrayTwo);
        System.out.println("Hash set result: " + result4);
        
        // Expected: 2 (difference between 28 and 26)
        
        System.out.println();
        
        // Test case: arrayOne = [1, 2, 3], arrayTwo = [4, 5, 6]
        int[] arrayOne2 = {1, 2, 3};
        int[] arrayTwo2 = {4, 5, 6};
        
        System.out.println("Array One: " + java.util.Arrays.toString(arrayOne2));
        System.out.println("Array Two: " + java.util.Arrays.toString(arrayTwo2));
        
        int result5 = smallestDifference_twoPointers(arrayOne2, arrayTwo2);
        System.out.println("Two pointers result: " + result5);
        
        // Expected: 1 (difference between 3 and 4)
        
        System.out.println();
        
        // Test case: arrayOne = [1, 2, 3], arrayTwo = [2, 3, 4]
        int[] arrayOne3 = {1, 2, 3};
        int[] arrayTwo3 = {2, 3, 4};
        
        System.out.println("Array One: " + java.util.Arrays.toString(arrayOne3));
        System.out.println("Array Two: " + java.util.Arrays.toString(arrayTwo3));
        
        int result6 = smallestDifference_twoPointers(arrayOne3, arrayTwo3);
        System.out.println("Two pointers result: " + result6);
        
        // Expected: 0 (exact match between 2 and 2, or 3 and 3)
    }
}

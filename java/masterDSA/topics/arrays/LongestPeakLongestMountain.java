package masterDSA.topics.arrays;
import java.util.*;


/**
 * Longest Peak / Longest Mountain
 * 
 * Given an array of integers, a peak is defined as adjacent integers that are strictly 
 * increasing until they reach a tip (the highest value in the peak), at which point they 
 * become strictly decreasing. Return the length of the longest peak.
 * 
 * LeetCode: https://leetcode.com/problems/longest-mountain-in-array/
 * Difficulty: Medium
 * Tags: scan, peak-detection
 * 
 * Approach:
 * - Scan the array to identify peaks
 * - For each peak, expand leftward and rightward to find its full length
 * - Keep track of the maximum peak length found
 * 
 * Pseudocode:
 * 1. Initialize maxPeakLength = 0
 * 2. For each index i from 1 to n-2:
 *    a. If array[i] is a peak (greater than neighbors):
 *       - Expand leftward to find start of increasing sequence
 *       - Expand rightward to find end of decreasing sequence
 *       - Calculate peak length and update maxPeakLength
 * 3. Return maxPeakLength
 */
public class LongestPeakLongestMountain {
    
    /**
     * Peak detection approach - O(n) time, O(1) space
     * Identify peaks and expand them to find their full length
     */
    public static int longestPeak_peakDetection(int[] array) {
        int maxPeakLength = 0;
        
        // Check each position as potential peak (skip first and last)
        for (int i = 1; i < array.length - 1; i++) {
            // Check if current position is a peak
            if (array[i] > array[i - 1] && array[i] > array[i + 1]) {
                // Expand leftward to find start of increasing sequence
                int left = i - 1;
                while (left > 0 && array[left] > array[left - 1]) {
                    left--;
                }
                
                // Expand rightward to find end of decreasing sequence
                int right = i + 1;
                while (right < array.length - 1 && array[right] > array[right + 1]) {
                    right++;
                }
                
                // Calculate peak length
                int peakLength = right - left + 1;
                maxPeakLength = Math.max(maxPeakLength, peakLength);
            }
        }
        
        return maxPeakLength;
    }
    
    /**
     * Two passes approach - O(n) time, O(n) space
     * First pass: find increasing sequences from left
     * Second pass: find decreasing sequences from right
     * Combine to find peaks
     */
    public static int longestPeak_twoPasses(int[] array) {
        int n = array.length;
        if (n < 3) return 0;
        
        // First pass: find increasing sequences from left
        int[] leftIncreasing = new int[n];
        for (int i = 1; i < n; i++) {
            if (array[i] > array[i - 1]) {
                leftIncreasing[i] = leftIncreasing[i - 1] + 1;
            }
        }
        
        // Second pass: find decreasing sequences from right
        int[] rightDecreasing = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            if (array[i] > array[i + 1]) {
                rightDecreasing[i] = rightDecreasing[i + 1] + 1;
            }
        }
        
        // Find peaks and calculate their lengths
        int maxPeakLength = 0;
        for (int i = 1; i < n - 1; i++) {
            if (leftIncreasing[i] > 0 && rightDecreasing[i] > 0) {
                int peakLength = leftIncreasing[i] + rightDecreasing[i] + 1;
                maxPeakLength = Math.max(maxPeakLength, peakLength);
            }
        }
        
        return maxPeakLength;
    }
    
    /**
     * State machine approach - O(n) time, O(1) space
     * Track current state (increasing, decreasing, or flat) and peak length
     */
    public static int longestPeak_stateMachine(int[] array) {
        if (array.length < 3) return 0;
        
        int maxPeakLength = 0;
        int currentPeakLength = 1;
        boolean isIncreasing = false;
        boolean isDecreasing = false;
        
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                if (isDecreasing) {
                    // End of previous peak, start new increasing sequence
                    currentPeakLength = 2;
                    isIncreasing = true;
                    isDecreasing = false;
                } else {
                    // Continue increasing sequence
                    currentPeakLength++;
                    isIncreasing = true;
                }
            } else if (array[i] < array[i - 1]) {
                if (isIncreasing) {
                    // Start decreasing sequence (peak found)
                    currentPeakLength++;
                    isDecreasing = true;
                    isIncreasing = false;
                    maxPeakLength = Math.max(maxPeakLength, currentPeakLength);
                } else if (isDecreasing) {
                    // Continue decreasing sequence
                    currentPeakLength++;
                    maxPeakLength = Math.max(maxPeakLength, currentPeakLength);
                } else {
                    // Start new sequence
                    currentPeakLength = 1;
                }
            } else {
                // Flat sequence, reset
                currentPeakLength = 1;
                isIncreasing = false;
                isDecreasing = false;
            }
        }
        
        return maxPeakLength;
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * For each position, check if it's a peak and calculate its length
     */
    public static int longestPeak_bruteForce(int[] array) {
        int maxPeakLength = 0;
        
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] > array[i - 1] && array[i] > array[i + 1]) {
                // Found a peak, calculate its length
                int left = i;
                while (left > 0 && array[left] > array[left - 1]) {
                    left--;
                }
                
                int right = i;
                while (right < array.length - 1 && array[right] > array[right + 1]) {
                    right++;
                }
                
                int peakLength = right - left + 1;
                maxPeakLength = Math.max(maxPeakLength, peakLength);
            }
        }
        
        return maxPeakLength;
    }
    
    /**
     * Helper method to find all peaks in the array
     */
    public static List<Integer> findAllPeaks(int[] array) {
        List<Integer> peaks = new ArrayList<>();
        
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] > array[i - 1] && array[i] > array[i + 1]) {
                peaks.add(i);
            }
        }
        
        return peaks;
    }
    
    public static void main(String[] args) {
        // Test case: array = [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3]
        int[] array = {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
        
        System.out.println("Testing Longest Peak:");
        System.out.println("Array: " + Arrays.toString(array));
        
        int result1 = longestPeak_peakDetection(array);
        System.out.println("Peak detection result: " + result1);
        
        int result2 = longestPeak_twoPasses(array);
        System.out.println("Two passes result: " + result2);
        
        int result3 = longestPeak_stateMachine(array);
        System.out.println("State machine result: " + result3);
        
        int result4 = longestPeak_bruteForce(array);
        System.out.println("Brute force result: " + result4);
        
        // Expected: 6 (peak from index 2 to 7: [3, 4, 0, 10, 6, 5])
        
        System.out.println();
        
        // Test case: array = [1, 2, 3, 4, 5] (no peak)
        int[] noPeak = {1, 2, 3, 4, 5};
        System.out.println("Array with no peak: " + Arrays.toString(noPeak));
        
        int result5 = longestPeak_peakDetection(noPeak);
        System.out.println("Peak detection result: " + result5);
        
        // Expected: 0
        
        System.out.println();
        
        // Test case: array = [5, 4, 3, 2, 1] (no peak)
        int[] decreasing = {5, 4, 3, 2, 1};
        System.out.println("Decreasing array: " + Arrays.toString(decreasing));
        
        int result6 = longestPeak_peakDetection(decreasing);
        System.out.println("Peak detection result: " + result6);
        
        // Expected: 0
    }
}

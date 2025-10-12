package masterDSA.topics.patterns_misc;
import java.util.*;


/**
 * Sliding Window Pattern
 * 
 * Problem Statement:
 * The sliding window pattern is a technique for solving problems involving contiguous subarrays or substrings.
 * It maintains a window of elements and slides it across the data structure to find optimal solutions.
 * 
 * LeetCode Link: https://leetcode.com/tag/sliding-window/
 * Difficulty: Medium
 * Tags: Array, Sliding Window, Two Pointers
 * 
 * Approach Summary:
 * 1. Fixed Window Size: Maintain a window of fixed size k
 * 2. Variable Window Size: Expand/contract window based on conditions
 * 3. Use two pointers (left, right) to define window boundaries
 * 4. Update window state incrementally as pointers move
 * 
 * Pseudocode:
 * 1. Initialize left = 0, right = 0
 * 2. Expand window by moving right pointer
 * 3. Update window state (sum, count, etc.)
 * 4. Contract window by moving left pointer when condition met
 * 5. Track optimal result during window movement
 * 
 * Time Complexity: O(n) - each element visited at most twice
 * Space Complexity: O(1) to O(k) depending on auxiliary data structures
 */
public class SlidingWindowPattern {
    
    /**
     * Fixed window size - Maximum sum of subarray of size k
     * 
     * @param nums Array of integers
     * @param k Window size
     * @return Maximum sum of any subarray of size k
     */
    public static int maxSumSubarray_fixedWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0;
        }
        
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window
        for (int i = k; i < nums.length; i++) {
            // Remove leftmost element and add rightmost element
            windowSum = windowSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    /**
     * Variable window size - Longest substring with at most k distinct characters
     * 
     * @param s Input string
     * @param k Maximum number of distinct characters
     * @return Length of longest substring with at most k distinct characters
     */
    public static int longestSubstringAtMostKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
            // Shrink window if more than k distinct characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Variable window size - Minimum window substring
     * 
     * @param s Source string
     * @param t Target string
     * @return Minimum window in s that contains all characters of t
     */
    public static String minWindowSubstring(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        Map<Character, Integer> targetCount = new HashMap<>();
        Map<Character, Integer> windowCount = new HashMap<>();
        
        // Count characters in target string
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        
        int left = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int formed = 0; // Number of unique characters formed
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
            
            // Check if current character contributes to forming target
            if (targetCount.containsKey(rightChar) && 
                windowCount.get(rightChar).intValue() == targetCount.get(rightChar).intValue()) {
                formed++;
            }
            
            // Try to contract window
            while (left <= right && formed == targetCount.size()) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }
                
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                
                if (targetCount.containsKey(leftChar) && 
                    windowCount.get(leftChar) < targetCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
    
    /**
     * Variable window size - Longest substring without repeating characters
     * 
     * @param s Input string
     * @return Length of longest substring without repeating characters
     */
    public static int longestSubstringWithoutRepeating(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> seen = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Shrink window until no duplicates
            while (seen.contains(rightChar)) {
                seen.remove(s.charAt(left));
                left++;
            }
            
            seen.add(rightChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Fixed window size - Average of all subarrays of size k
     * 
     * @param nums Array of integers
     * @param k Window size
     * @return Array of averages for each subarray of size k
     */
    public static double[] findAverages(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new double[0];
        }
        
        double[] result = new double[nums.length - k + 1];
        double windowSum = 0;
        
        // Calculate sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        result[0] = windowSum / k;
        
        // Slide the window
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            result[i - k + 1] = windowSum / k;
        }
        
        return result;
    }
    
    /**
     * Variable window size - Find all anagrams in a string
     * 
     * @param s Source string
     * @param p Pattern string
     * @return List of starting indices of anagrams of p in s
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        
        Map<Character, Integer> patternCount = new HashMap<>();
        Map<Character, Integer> windowCount = new HashMap<>();
        
        // Count characters in pattern
        for (char c : p.toCharArray()) {
            patternCount.put(c, patternCount.getOrDefault(c, 0) + 1);
        }
        
        int left = 0;
        int matched = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
            
            // Check if current character matches pattern
            if (patternCount.containsKey(rightChar) && 
                windowCount.get(rightChar).intValue() == patternCount.get(rightChar).intValue()) {
                matched++;
            }
            
            // If window size equals pattern length
            if (right - left + 1 == p.length()) {
                if (matched == patternCount.size()) {
                    result.add(left);
                }
                
                char leftChar = s.charAt(left);
                if (patternCount.containsKey(leftChar) && 
                    windowCount.get(leftChar).intValue() == patternCount.get(leftChar).intValue()) {
                    matched--;
                }
                
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                if (windowCount.get(leftChar) == 0) {
                    windowCount.remove(leftChar);
                }
                
                left++;
            }
        }
        
        return result;
    }
    
    /**
     * Variable window size - Subarray product less than k
     * 
     * @param nums Array of positive integers
     * @param k Target product threshold
     * @return Number of contiguous subarrays where product < k
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }
        
        int left = 0;
        int product = 1;
        int count = 0;
        
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            // Shrink window while product >= k
            while (left <= right && product >= k) {
                product /= nums[left];
                left++;
            }
            
            // All subarrays ending at right with start from left to right are valid
            count += right - left + 1;
        }
        
        return count;
    }
    
    /**
     * Demonstration method showing various sliding window patterns
     */
    public static void runExample() {
        System.out.println("=== Sliding Window Pattern Examples ===");
        
        // Fixed window example
        int[] nums1 = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int k1 = 4;
        int maxSum = maxSumSubarray_fixedWindow(nums1, k1);
        System.out.println("Maximum sum of subarray of size " + k1 + ": " + maxSum);
        
        // Variable window example
        String s1 = "eceba";
        int k2 = 2;
        int longest = longestSubstringAtMostKDistinct(s1, k2);
        System.out.println("Longest substring with at most " + k2 + " distinct chars: " + longest);
        
        // Minimum window substring
        String s2 = "ADOBECODEBANC";
        String t2 = "ABC";
        String minWindow = minWindowSubstring(s2, t2);
        System.out.println("Minimum window substring: " + minWindow);
        
        // Longest substring without repeating
        String s3 = "abcabcbb";
        int longestNoRepeat = longestSubstringWithoutRepeating(s3);
        System.out.println("Longest substring without repeating: " + longestNoRepeat);
        
        // Averages of subarrays
        int[] nums2 = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int k3 = 5;
        double[] averages = findAverages(nums2, k3);
        System.out.println("Averages of subarrays of size " + k3 + ": " + Arrays.toString(averages));
        
        // Find anagrams
        String s4 = "cbaebabacd";
        String p4 = "abc";
        List<Integer> anagramIndices = findAnagrams(s4, p4);
        System.out.println("Anagram starting indices: " + anagramIndices);
        
        // Subarray product less than k
        int[] nums3 = {10, 5, 2, 6};
        int k4 = 100;
        int productCount = numSubarrayProductLessThanK(nums3, k4);
        System.out.println("Subarrays with product < " + k4 + ": " + productCount);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

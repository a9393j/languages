/**
 * Longest Substring Without Repeating Characters
 * 
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * LeetCode: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Difficulty: Medium
 * Tags: sliding-window, hashmap
 * 
 * Approach:
 * - Use sliding window technique with two pointers
 * - Use HashMap to track character frequencies
 * - Expand window when no duplicates, contract when duplicates found
 * 
 * Pseudocode:
 * 1. Initialize left pointer and maxLength
 * 2. For each right pointer:
 *    a. Add current character to window
 *    b. If duplicate found, move left pointer until no duplicates
 *    c. Update maxLength
 * 3. Return maxLength
 */

package masterDSA.topics.strings.LongestSubstringWithoutRepeatingCharacters.LongestSubstringWithoutRepeatingCharacters;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    
    /**
     * Sliding window with HashMap - O(n) time, O(min(m,n)) space
     * Most efficient approach for this problem
     */
    public static int lengthOfLongestSubstring_slidingWindow(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> charIndex = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If character is already in window, move left pointer
            if (charIndex.containsKey(currentChar) && charIndex.get(currentChar) >= left) {
                left = charIndex.get(currentChar) + 1;
            }
            
            // Update character index
            charIndex.put(currentChar, right);
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Sliding window with HashSet - O(n) time, O(min(m,n)) space
     * Alternative approach using HashSet
     */
    public static int lengthOfLongestSubstring_hashSet(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If character is already in window, move left pointer
            while (window.contains(currentChar)) {
                window.remove(s.charAt(left));
                left++;
            }
            
            // Add current character to window
            window.add(currentChar);
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Brute force approach - O(n^3) time, O(min(m,n)) space
     * Less efficient but easy to understand
     */
    public static int lengthOfLongestSubstring_bruteForce(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
    
    private static boolean isUnique(String s, int start, int end) {
        Set<Character> seen = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (seen.contains(s.charAt(i))) {
                return false;
            }
            seen.add(s.charAt(i));
        }
        return true;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        
        System.out.println("Testing Longest Substring Without Repeating Characters:");
        
        System.out.println("String: " + s1);
        System.out.println("Sliding window result: " + lengthOfLongestSubstring_slidingWindow(s1));
        System.out.println("HashSet result: " + lengthOfLongestSubstring_hashSet(s1));
        System.out.println("Brute force result: " + lengthOfLongestSubstring_bruteForce(s1));
        
        System.out.println("String: " + s2);
        System.out.println("Sliding window result: " + lengthOfLongestSubstring_slidingWindow(s2));
        
        System.out.println("String: " + s3);
        System.out.println("Sliding window result: " + lengthOfLongestSubstring_slidingWindow(s3));
    }
}
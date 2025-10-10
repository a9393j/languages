package masterDSA.topics.strings.LongestSubstringWithoutRepeatingCharacters;
import java.util.*;


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
 * - Sliding window technique with two pointers
 * - Use HashMap to track character frequencies
 * - Expand right pointer, contract left pointer when duplicates found
 * - Alternative: Use HashSet for simpler implementation
 * 
 * Pseudocode:
 * 1. Initialize left = 0, maxLen = 0
 * 2. For right = 0 to n-1:
 *    a. Add s[right] to window
 *    b. While window has duplicates:
 *       - Remove s[left] from window
 *       - Increment left
 *    c. Update maxLen
 * 3. Return maxLen
 */
public class LongestSubstringWithoutRepeatingCharacters {
    
    /**
     * Sliding window with HashMap - O(n) time, O(min(m,n)) space
     * Use HashMap to track character frequencies in current window
     */
    public static int lengthOfLongestSubstring_hashMap(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        java.util.Map<Character, Integer> charCount = new java.util.HashMap<>();
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
            // Shrink window from left until no duplicates
            while (charCount.get(rightChar) > 1) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    /**
     * Sliding window with HashSet - O(n) time, O(min(m,n)) space
     * Use HashSet to track characters in current window
     */
    public static int lengthOfLongestSubstring_hashSet(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        java.util.Set<Character> charSet = new java.util.HashSet<>();
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Shrink window from left until rightChar can be added
            while (charSet.contains(rightChar)) {
                char leftChar = s.charAt(left);
                charSet.remove(leftChar);
                left++;
            }
            
            charSet.add(rightChar);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    /**
     * Optimized sliding window - O(n) time, O(min(m,n)) space
     * Use HashMap to store character -> index mapping for O(1) lookups
     */
    public static int lengthOfLongestSubstring_optimized(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        java.util.Map<Character, Integer> charIndex = new java.util.HashMap<>();
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // If character exists and is within current window, move left pointer
            if (charIndex.containsKey(rightChar) && charIndex.get(rightChar) >= left) {
                left = charIndex.get(rightChar) + 1;
            }
            
            charIndex.put(rightChar, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    /**
     * Brute force approach - O(n³) time, O(min(m,n)) space
     * Check all possible substrings
     */
    public static int lengthOfLongestSubstring_bruteForce(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        
        return maxLen;
    }
    
    /**
     * Helper method to check if substring is unique
     */
    private static boolean isUnique(String s, int start, int end) {
        java.util.Set<Character> charSet = new java.util.HashSet<>();
        
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (charSet.contains(c)) {
                return false;
            }
            charSet.add(c);
        }
        
        return true;
    }
    
    /**
     * Array-based approach - O(n) time, O(1) space
     * Use array instead of HashMap for ASCII characters
     */
    public static int lengthOfLongestSubstring_array(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] charIndex = new int[128]; // ASCII characters
        java.util.Arrays.fill(charIndex, -1);
        
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // If character exists and is within current window, move left pointer
            if (charIndex[rightChar] >= left) {
                left = charIndex[rightChar] + 1;
            }
            
            charIndex[rightChar] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    /**
     * Helper method to find the actual longest substring
     */
    public static String longestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        java.util.Map<Character, Integer> charIndex = new java.util.HashMap<>();
        int left = 0;
        int maxLen = 0;
        int start = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            if (charIndex.containsKey(rightChar) && charIndex.get(rightChar) >= left) {
                left = charIndex.get(rightChar) + 1;
            }
            
            charIndex.put(rightChar, right);
            
            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                start = left;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    public static void main(String[] args) {
        // Test case: s = "abcabcbb"
        String s1 = "abcabcbb";
        
        System.out.println("Testing Longest Substring Without Repeating Characters:");
        System.out.println("String: " + s1);
        
        int result1 = lengthOfLongestSubstring_hashMap(s1);
        System.out.println("HashMap result: " + result1);
        
        int result2 = lengthOfLongestSubstring_hashSet(s1);
        System.out.println("HashSet result: " + result2);
        
        int result3 = lengthOfLongestSubstring_optimized(s1);
        System.out.println("Optimized result: " + result3);
        
        int result4 = lengthOfLongestSubstring_bruteForce(s1);
        System.out.println("Brute force result: " + result4);
        
        int result5 = lengthOfLongestSubstring_array(s1);
        System.out.println("Array result: " + result5);
        
        String longestSub = longestSubstring(s1);
        System.out.println("Longest substring: " + longestSub);
        
        // Expected: 3 (substring "abc")
        
        System.out.println();
        
        // Test case: s = "bbbbb"
        String s2 = "bbbbb";
        System.out.println("String: " + s2);
        
        int result6 = lengthOfLongestSubstring_hashMap(s2);
        System.out.println("HashMap result: " + result6);
        
        // Expected: 1 (substring "b")
        
        System.out.println();
        
        // Test case: s = "pwwkew"
        String s3 = "pwwkew";
        System.out.println("String: " + s3);
        
        int result7 = lengthOfLongestSubstring_hashMap(s3);
        System.out.println("HashMap result: " + result7);
        
        // Expected: 3 (substring "wke")
    }
}

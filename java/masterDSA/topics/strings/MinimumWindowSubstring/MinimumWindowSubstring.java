/**
 * Minimum Window Substring
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum window 
 * substring of s such that every character in t (including duplicates) is included 
 * in the window. If there is no such window, return the empty string "".
 * 
 * LeetCode: https://leetcode.com/problems/minimum-window-substring/
 * Difficulty: Hard
 * Tags: sliding-window, two-pointer
 * 
 * Approach:
 * - Use sliding window technique with two pointers
 * - Expand window to include all characters from t
 * - Contract window while maintaining validity
 * - Keep track of minimum valid window
 * 
 * Pseudocode:
 * 1. Create frequency map for string t
 * 2. Initialize left pointer and window variables
 * 3. For each right pointer:
 *    a. Add current character to window
 *    b. If window contains all characters from t, try to contract
 *    c. Update minimum window if current is smaller
 * 4. Return minimum window substring
 */

package masterDSA.topics.strings.MinimumWindowSubstring.MinimumWindowSubstring;

import java.util.*;

public class MinimumWindowSubstring {
    
    /**
     * Sliding window approach - O(|s| + |t|) time, O(|s| + |t|) space
     * Most efficient approach for this problem
     */
    public static String minWindow_slidingWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        // Create frequency map for string t
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        
        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int matched = 0; // Number of characters matched
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            // If current character is in target, decrease its count
            if (targetCount.containsKey(rightChar)) {
                targetCount.put(rightChar, targetCount.get(rightChar) - 1);
                if (targetCount.get(rightChar) >= 0) {
                    matched++;
                }
            }
            
            // Try to contract window from left
            while (matched == t.length()) {
                // Update minimum window if current is smaller
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }
                
                char leftChar = s.charAt(left);
                
                // If left character is in target, increase its count
                if (targetCount.containsKey(leftChar)) {
                    targetCount.put(leftChar, targetCount.get(leftChar) + 1);
                    if (targetCount.get(leftChar) > 0) {
                        matched--;
                    }
                }
                
                left++;
            }
            
            right++;
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
    
    /**
     * Alternative approach using array for frequency - O(|s| + |t|) time, O(1) space
     * Optimized for ASCII characters
     */
    public static String minWindow_array(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        // Create frequency array for string t
        int[] targetCount = new int[128]; // ASCII characters
        for (char c : t.toCharArray()) {
            targetCount[c]++;
        }
        
        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int matched = 0;
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            // If current character is in target, decrease its count
            if (targetCount[rightChar] > 0) {
                matched++;
            }
            targetCount[rightChar]--;
            
            // Try to contract window from left
            while (matched == t.length()) {
                // Update minimum window if current is smaller
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }
                
                char leftChar = s.charAt(left);
                targetCount[leftChar]++;
                
                // If left character is in target, decrease matched count
                if (targetCount[leftChar] > 0) {
                    matched--;
                }
                
                left++;
            }
            
            right++;
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        
        String s2 = "a";
        String t2 = "a";
        
        String s3 = "a";
        String t3 = "aa";
        
        System.out.println("Testing Minimum Window Substring:");
        
        System.out.println("s: " + s1 + ", t: " + t1);
        System.out.println("Sliding window result: " + minWindow_slidingWindow(s1, t1));
        System.out.println("Array result: " + minWindow_array(s1, t1));
        
        System.out.println("s: " + s2 + ", t: " + t2);
        System.out.println("Sliding window result: " + minWindow_slidingWindow(s2, t2));
        
        System.out.println("s: " + s3 + ", t: " + t3);
        System.out.println("Sliding window result: " + minWindow_slidingWindow(s3, t3));
    }
}
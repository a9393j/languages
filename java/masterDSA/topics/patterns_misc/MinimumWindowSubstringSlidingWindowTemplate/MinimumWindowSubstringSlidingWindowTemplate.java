/**
 * Minimum Window Substring (Sliding Window Template)
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum window 
 * substring of s such that every character in t (including duplicates) is included 
 * in the window. If there is no such window, return the empty string "".
 * 
 * LeetCode: https://leetcode.com/problems/minimum-window-substring/
 * Difficulty: Hard
 * Tags: sliding-window
 * 
 * Approach:
 * - Use sliding window technique
 * - Expand window to include all characters from t
 * - Contract window while maintaining validity
 * - Keep track of minimum valid window
 * 
 * Pseudocode:
 * 1. Create frequency map for string t
 * 2. Use two pointers (left, right)
 * 3. Expand right pointer until window contains all characters
 * 4. Contract left pointer while maintaining validity
 * 5. Update minimum window if current is smaller
 */

package masterDSA.topics.patterns_misc.MinimumWindowSubstringSlidingWindowTemplate.MinimumWindowSubstringSlidingWindowTemplate;

import java.util.*;

public class MinimumWindowSubstringSlidingWindowTemplate {
    
    /**
     * Sliding window template - O(|s| + |t|) time, O(|s| + |t|) space
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
     * Alternative sliding window template - O(|s| + |t|) time, O(|s| + |t|) space
     * Uses different approach for window contraction
     */
    public static String minWindow_alternative(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        
        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int required = targetCount.size();
        int formed = 0;
        
        Map<Character, Integer> windowCount = new HashMap<>();
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
            
            if (targetCount.containsKey(rightChar) && 
                windowCount.get(rightChar).intValue() == targetCount.get(rightChar).intValue()) {
                formed++;
            }
            
            while (left <= right && formed == required) {
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
        
        System.out.println("Testing Minimum Window Substring (Sliding Window Template):");
        
        System.out.println("s: " + s1 + ", t: " + t1);
        System.out.println("Sliding window result: " + minWindow_slidingWindow(s1, t1));
        System.out.println("Alternative result: " + minWindow_alternative(s1, t1));
        
        System.out.println("s: " + s2 + ", t: " + t2);
        System.out.println("Sliding window result: " + minWindow_slidingWindow(s2, t2));
        
        System.out.println("s: " + s3 + ", t: " + t3);
        System.out.println("Sliding window result: " + minWindow_slidingWindow(s3, t3));
    }
}
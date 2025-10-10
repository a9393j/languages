package masterDSA.topics.strings.MinimumWindowSubstring;
import java.util.*;


/**
 * Minimum Window Substring
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum window 
 * substring of s such that every character in t (including duplicates) is included in the window. 
 * If there is no such window, return the empty string "".
 * 
 * LeetCode: https://leetcode.com/problems/minimum-window-substring/
 * Difficulty: Hard
 * Tags: sliding-window, two-pointer
 * 
 * Approach:
 * - Sliding window technique with two pointers
 * - Expand right pointer to include characters from t
 * - Contract left pointer to minimize window size
 * - Use HashMap to track character frequencies
 * 
 * Pseudocode:
 * 1. Count characters in t
 * 2. Initialize left = 0, minLen = infinity, minStart = 0
 * 3. For right = 0 to n-1:
 *    a. Include s[right] in window
 *    b. While window contains all characters from t:
 *       - Update minimum window if current is smaller
 *       - Remove s[left] from window
 *       - Increment left
 * 4. Return minimum window substring
 */
public class MinimumWindowSubstring {
    
    /**
     * Sliding window approach - O(|s| + |t|) time, O(|s| + |t|) space
     * Use two pointers to maintain a sliding window
     */
    public static String minWindow_slidingWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        // Count characters in t
        java.util.Map<Character, Integer> tCount = new java.util.HashMap<>();
        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }
        
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int required = tCount.size(); // Number of unique characters in t
        int formed = 0; // Number of unique characters in current window
        
        java.util.Map<Character, Integer> windowCount = new java.util.HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
            
            // Check if current character's count matches required count
            if (tCount.containsKey(rightChar) && 
                windowCount.get(rightChar).intValue() == tCount.get(rightChar).intValue()) {
                formed++;
            }
            
            // Try to contract the window from the left
            while (left <= right && formed == required) {
                // Update minimum window if current is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                
                // If character count drops below required, decrement formed
                if (tCount.containsKey(leftChar) && 
                    windowCount.get(leftChar) < tCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    
    /**
     * Optimized sliding window - O(|s| + |t|) time, O(|s| + |t|) space
     * Use array instead of HashMap for better performance
     */
    public static String minWindow_optimized(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        // Count characters in t
        int[] tCount = new int[128]; // ASCII characters
        int required = 0;
        for (char c : t.toCharArray()) {
            if (tCount[c] == 0) required++;
            tCount[c]++;
        }
        
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int formed = 0;
        
        int[] windowCount = new int[128];
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowCount[rightChar]++;
            
            // Check if current character's count matches required count
            if (tCount[rightChar] > 0 && windowCount[rightChar] == tCount[rightChar]) {
                formed++;
            }
            
            // Try to contract the window from the left
            while (left <= right && formed == required) {
                // Update minimum window if current is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                
                char leftChar = s.charAt(left);
                windowCount[leftChar]--;
                
                // If character count drops below required, decrement formed
                if (tCount[leftChar] > 0 && windowCount[leftChar] < tCount[leftChar]) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    
    /**
     * Brute force approach - O(|s|³) time, O(|s| + |t|) space
     * Check all possible substrings
     */
    public static String minWindow_bruteForce(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        String minWindow = "";
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                if (containsAllCharacters(substring, t) && substring.length() < minLen) {
                    minLen = substring.length();
                    minWindow = substring;
                }
            }
        }
        
        return minWindow;
    }
    
    /**
     * Helper method to check if substring contains all characters from t
     */
    private static boolean containsAllCharacters(String s, String t) {
        java.util.Map<Character, Integer> sCount = new java.util.HashMap<>();
        for (char c : s.toCharArray()) {
            sCount.put(c, sCount.getOrDefault(c, 0) + 1);
        }
        
        for (char c : t.toCharArray()) {
            if (!sCount.containsKey(c) || sCount.get(c) == 0) {
                return false;
            }
            sCount.put(c, sCount.get(c) - 1);
        }
        
        return true;
    }
    
    /**
     * Two pointers with frequency array - O(|s| + |t|) time, O(1) space
     * Use frequency array for ASCII characters
     */
    public static String minWindow_frequencyArray(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        // Count characters in t
        int[] tCount = new int[128];
        int required = 0;
        for (char c : t.toCharArray()) {
            if (tCount[c] == 0) required++;
            tCount[c]++;
        }
        
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int formed = 0;
        
        int[] windowCount = new int[128];
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowCount[rightChar]++;
            
            if (tCount[rightChar] > 0 && windowCount[rightChar] == tCount[rightChar]) {
                formed++;
            }
            
            while (left <= right && formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                
                char leftChar = s.charAt(left);
                windowCount[leftChar]--;
                
                if (tCount[leftChar] > 0 && windowCount[leftChar] < tCount[leftChar]) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    
    /**
     * Helper method to find all substrings containing t
     */
    public static java.util.List<String> findAllWindows(String s, String t) {
        java.util.List<String> windows = new java.util.ArrayList<>();
        
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return windows;
        }
        
        // Count characters in t
        java.util.Map<Character, Integer> tCount = new java.util.HashMap<>();
        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }
        
        int left = 0;
        int required = tCount.size();
        int formed = 0;
        
        java.util.Map<Character, Integer> windowCount = new java.util.HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
            
            if (tCount.containsKey(rightChar) && 
                windowCount.get(rightChar).intValue() == tCount.get(rightChar).intValue()) {
                formed++;
            }
            
            while (left <= right && formed == required) {
                windows.add(s.substring(left, right + 1));
                
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                
                if (tCount.containsKey(leftChar) && 
                    windowCount.get(leftChar) < tCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return windows;
    }
    
    /**
     * Helper method to check if two strings are anagrams
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Test case: s = "ADOBECODEBANC", t = "ABC"
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        
        System.out.println("Testing Minimum Window Substring:");
        System.out.println("String s: " + s1);
        System.out.println("String t: " + t1);
        
        String result1 = minWindow_slidingWindow(s1, t1);
        System.out.println("Sliding window result: " + result1);
        
        String result2 = minWindow_optimized(s1, t1);
        System.out.println("Optimized result: " + result2);
        
        String result3 = minWindow_bruteForce(s1, t1);
        System.out.println("Brute force result: " + result3);
        
        String result4 = minWindow_frequencyArray(s1, t1);
        System.out.println("Frequency array result: " + result4);
        
        // Expected: "BANC"
        
        System.out.println();
        
        // Test case: s = "a", t = "a"
        String s2 = "a";
        String t2 = "a";
        
        System.out.println("String s: " + s2);
        System.out.println("String t: " + t2);
        
        String result5 = minWindow_slidingWindow(s2, t2);
        System.out.println("Sliding window result: " + result5);
        
        // Expected: "a"
        
        System.out.println();
        
        // Test case: s = "a", t = "aa"
        String s3 = "a";
        String t3 = "aa";
        
        System.out.println("String s: " + s3);
        System.out.println("String t: " + t3);
        
        String result6 = minWindow_slidingWindow(s3, t3);
        System.out.println("Sliding window result: " + result6);
        
        // Expected: ""
        
        System.out.println();
        
        // Find all windows
        java.util.List<String> allWindows = findAllWindows(s1, t1);
        System.out.println("All windows containing '" + t1 + "': " + allWindows);
        
        // Expected: ["ADOBEC", "DOBECODEBA", "OBECODEBA", "BECODEBA", "ECODEBA", "CODEBA", "ODEBANC", "DEBANC", "EBANC", "BANC"]
    }
}

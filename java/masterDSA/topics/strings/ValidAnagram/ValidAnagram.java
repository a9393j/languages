/**
 * Valid Anagram
 * 
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word 
 * or phrase, typically using all the original letters exactly once.
 * 
 * LeetCode: https://leetcode.com/problems/valid-anagram/
 * Difficulty: Easy
 * Tags: hashing
 * 
 * Approach:
 * - Count character frequencies in both strings
 * - Compare frequency maps
 * - Alternative: sort both strings and compare
 * 
 * Pseudocode:
 * 1. Create frequency maps for both strings
 * 2. Compare frequency maps
 * 3. Return true if maps are equal, false otherwise
 */

package masterDSA.topics.strings.ValidAnagram.ValidAnagram;

import java.util.*;

public class ValidAnagram {
    
    /**
     * HashMap approach - O(n) time, O(1) space (26 characters max)
     * Most efficient approach for this problem
     */
    public static boolean isAnagram_hashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Integer> charCount = new HashMap<>();
        
        // Count characters in first string
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Decrease count for characters in second string
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) - 1);
            
            // If count becomes negative, strings are not anagrams
            if (charCount.get(c) < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Array approach - O(n) time, O(1) space
     * Optimized for lowercase English letters
     */
    public static boolean isAnagram_array(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] charCount = new int[26];
        
        // Count characters in first string
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        
        // Decrease count for characters in second string
        for (char c : t.toCharArray()) {
            charCount[c - 'a']--;
            
            // If count becomes negative, strings are not anagrams
            if (charCount[c - 'a'] < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Sorting approach - O(n log n) time, O(1) space
     * Simple but less efficient
     */
    public static boolean isAnagram_sorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        
        return Arrays.equals(sArray, tArray);
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String s1 = "anagram";
        String t1 = "nagaram";
        
        String s2 = "rat";
        String t2 = "car";
        
        String s3 = "a";
        String t3 = "ab";
        
        System.out.println("Testing Valid Anagram:");
        
        System.out.println("s: " + s1 + ", t: " + t1);
        System.out.println("HashMap result: " + isAnagram_hashMap(s1, t1));
        System.out.println("Array result: " + isAnagram_array(s1, t1));
        System.out.println("Sorting result: " + isAnagram_sorting(s1, t1));
        
        System.out.println("s: " + s2 + ", t: " + t2);
        System.out.println("HashMap result: " + isAnagram_hashMap(s2, t2));
        
        System.out.println("s: " + s3 + ", t: " + t3);
        System.out.println("HashMap result: " + isAnagram_hashMap(s3, t3));
    }
}
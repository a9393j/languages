package masterDSA.topics.strings.ValidAnagram;
import java.util.*;


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
 * - Sort both strings and compare
 * - Use character frequency counting
 * - Use HashMap to track character counts
 * - Use array for ASCII characters
 * 
 * Pseudocode:
 * HashMap:
 * 1. If lengths differ, return false
 * 2. Count characters in first string
 * 3. Subtract characters in second string
 * 4. Check if all counts are zero
 * 
 * Sorting:
 * 1. Sort both strings
 * 2. Compare sorted strings
 */
public class ValidAnagram {
    
    /**
     * HashMap approach - O(n) time, O(1) space (max 26 characters)
     * Count character frequencies in both strings
     */
    public static boolean isAnagram_hashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        java.util.Map<Character, Integer> charCount = new java.util.HashMap<>();
        
        // Count characters in first string
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Subtract characters in second string
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) - 1);
        }
        
        // Check if all counts are zero
        for (int count : charCount.values()) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Array approach - O(n) time, O(1) space
     * Use array for ASCII characters (more efficient)
     */
    public static boolean isAnagram_array(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] charCount = new int[26]; // Assuming lowercase English letters
        
        // Count characters in first string
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        
        // Subtract characters in second string
        for (char c : t.toCharArray()) {
            charCount[c - 'a']--;
        }
        
        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Sorting approach - O(n log n) time, O(1) space
     * Sort both strings and compare
     */
    public static boolean isAnagram_sorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        
        java.util.Arrays.sort(sArray);
        java.util.Arrays.sort(tArray);
        
        return java.util.Arrays.equals(sArray, tArray);
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * For each character in first string, find and remove from second string
     */
    public static boolean isAnagram_bruteForce(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        StringBuilder tBuilder = new StringBuilder(t);
        
        for (char c : s.toCharArray()) {
            int index = tBuilder.indexOf(String.valueOf(c));
            if (index == -1) {
                return false;
            }
            tBuilder.deleteCharAt(index);
        }
        
        return tBuilder.length() == 0;
    }
    
    /**
     * XOR approach - O(n) time, O(1) space
     * XOR all characters (works for single character differences)
     * Note: This approach has limitations and may not work for all cases
     */
    public static boolean isAnagram_xor(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int xor = 0;
        
        for (int i = 0; i < s.length(); i++) {
            xor ^= s.charAt(i);
            xor ^= t.charAt(i);
        }
        
        return xor == 0;
    }
    
    /**
     * Prime number approach - O(n) time, O(1) space
     * Assign prime numbers to characters and multiply
     * Note: This approach can overflow for long strings
     */
    public static boolean isAnagram_prime(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        
        long product1 = 1;
        long product2 = 1;
        
        for (char c : s.toCharArray()) {
            product1 *= primes[c - 'a'];
        }
        
        for (char c : t.toCharArray()) {
            product2 *= primes[c - 'a'];
        }
        
        return product1 == product2;
    }
    
    /**
     * Helper method to find all anagrams of a string
     */
    public static java.util.List<String> findAllAnagrams(String s) {
        java.util.List<String> anagrams = new java.util.ArrayList<>();
        findAllAnagramsHelper(s.toCharArray(), 0, anagrams);
        return anagrams;
    }
    
    private static void findAllAnagramsHelper(char[] chars, int start, java.util.List<String> result) {
        if (start == chars.length) {
            result.add(new String(chars));
            return;
        }
        
        for (int i = start; i < chars.length; i++) {
            // Swap characters
            char temp = chars[start];
            chars[start] = chars[i];
            chars[i] = temp;
            
            // Recursively find anagrams
            findAllAnagramsHelper(chars, start + 1, result);
            
            // Backtrack
            temp = chars[start];
            chars[start] = chars[i];
            chars[i] = temp;
        }
    }
    
    /**
     * Helper method to group anagrams
     */
    public static java.util.List<java.util.List<String>> groupAnagrams(String[] strs) {
        java.util.Map<String, java.util.List<String>> anagramGroups = new java.util.HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            java.util.Arrays.sort(chars);
            String sorted = new String(chars);
            
            anagramGroups.computeIfAbsent(sorted, k -> new java.util.ArrayList<>()).add(str);
        }
        
        return new java.util.ArrayList<>(anagramGroups.values());
    }
    
    public static void main(String[] args) {
        // Test case: s = "anagram", t = "nagaram"
        String s1 = "anagram";
        String t1 = "nagaram";
        
        System.out.println("Testing Valid Anagram:");
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + t1);
        
        boolean result1 = isAnagram_hashMap(s1, t1);
        System.out.println("HashMap result: " + result1);
        
        boolean result2 = isAnagram_array(s1, t1);
        System.out.println("Array result: " + result2);
        
        boolean result3 = isAnagram_sorting(s1, t1);
        System.out.println("Sorting result: " + result3);
        
        boolean result4 = isAnagram_bruteForce(s1, t1);
        System.out.println("Brute force result: " + result4);
        
        boolean result5 = isAnagram_xor(s1, t1);
        System.out.println("XOR result: " + result5);
        
        boolean result6 = isAnagram_prime(s1, t1);
        System.out.println("Prime result: " + result6);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: s = "rat", t = "car"
        String s2 = "rat";
        String t2 = "car";
        
        System.out.println("String 1: " + s2);
        System.out.println("String 2: " + t2);
        
        boolean result7 = isAnagram_hashMap(s2, t2);
        System.out.println("HashMap result: " + result7);
        
        // Expected: false
        
        System.out.println();
        
        // Test case: s = "a", t = "ab"
        String s3 = "a";
        String t3 = "ab";
        
        System.out.println("String 1: " + s3);
        System.out.println("String 2: " + t3);
        
        boolean result8 = isAnagram_hashMap(s3, t3);
        System.out.println("HashMap result: " + result8);
        
        // Expected: false
        
        System.out.println();
        
        // Find all anagrams of "abc"
        String testString = "abc";
        java.util.List<String> anagrams = findAllAnagrams(testString);
        System.out.println("All anagrams of '" + testString + "': " + anagrams);
        
        // Expected: [abc, acb, bac, bca, cab, cba]
        
        System.out.println();
        
        // Group anagrams
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        java.util.List<java.util.List<String>> groups = groupAnagrams(words);
        System.out.println("Grouped anagrams: " + groups);
        
        // Expected: [[eat, tea, ate], [tan, nat], [bat]]
    }
}

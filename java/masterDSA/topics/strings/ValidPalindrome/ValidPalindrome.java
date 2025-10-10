/**
 * Valid Palindrome
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters 
 * and removing all non-alphanumeric characters, it reads the same forward and backward. 
 * Alphanumeric characters include letters and numbers.
 * 
 * LeetCode: https://leetcode.com/problems/valid-palindrome/
 * Difficulty: Easy
 * Tags: two-pointers, string
 * 
 * Approach:
 * - Use two pointers from start and end
 * - Skip non-alphanumeric characters
 * - Compare characters (case-insensitive)
 * - Move pointers inward until they meet
 * 
 * Pseudocode:
 * 1. Initialize left = 0, right = s.length() - 1
 * 2. While left < right:
 *    a. Skip non-alphanumeric characters
 *    b. Compare characters (case-insensitive)
 *    c. If not equal, return false
 *    d. Move pointers inward
 * 3. Return true if all comparisons passed
 */

package masterDSA.topics.strings.ValidPalindrome.ValidPalindrome;

import java.util.*;

public class ValidPalindrome {
    
    /**
     * Two pointers approach - O(n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static boolean isPalindrome_twoPointers(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Alternative approach using string preprocessing - O(n) time, O(n) space
     * Preprocess string to remove non-alphanumeric characters
     */
    public static boolean isPalindrome_preprocessing(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        // Remove non-alphanumeric characters and convert to lowercase
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        String cleanedString = cleaned.toString();
        int left = 0;
        int right = cleanedString.length() - 1;
        
        while (left < right) {
            if (cleanedString.charAt(left) != cleanedString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        String s3 = " ";
        String s4 = "racecar";
        
        System.out.println("Testing Valid Palindrome:");
        
        System.out.println("String: " + s1);
        System.out.println("Two pointers result: " + isPalindrome_twoPointers(s1));
        System.out.println("Preprocessing result: " + isPalindrome_preprocessing(s1));
        
        System.out.println("String: " + s2);
        System.out.println("Two pointers result: " + isPalindrome_twoPointers(s2));
        
        System.out.println("String: " + s3);
        System.out.println("Two pointers result: " + isPalindrome_twoPointers(s3));
        
        System.out.println("String: " + s4);
        System.out.println("Two pointers result: " + isPalindrome_twoPointers(s4));
    }
}
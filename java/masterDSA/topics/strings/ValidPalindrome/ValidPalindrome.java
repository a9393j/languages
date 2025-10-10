package masterDSA.topics.strings.ValidPalindrome;

/**
 * Valid Palindrome
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase 
 * letters and removing all non-alphanumeric characters, it reads the same forward and 
 * backward. Alphanumeric characters include letters and numbers.
 * 
 * LeetCode: https://leetcode.com/problems/valid-palindrome/
 * Difficulty: Easy
 * Tags: two-pointers, string
 * 
 * Approach:
 * - Two pointers approach: Start from both ends and move inward
 * - Skip non-alphanumeric characters
 * - Compare characters case-insensitively
 * - Alternative: Clean string first, then check palindrome
 * 
 * Pseudocode:
 * 1. Initialize left = 0, right = s.length() - 1
 * 2. While left < right:
 *    a. Skip non-alphanumeric characters from left
 *    b. Skip non-alphanumeric characters from right
 *    c. Compare characters (case-insensitive)
 *    d. If not equal, return false
 *    e. Move pointers inward
 * 3. Return true
 */
public class ValidPalindrome {
    
    /**
     * Two pointers approach - O(n) time, O(1) space
     * Use two pointers from both ends, skip non-alphanumeric characters
     */
    public static boolean isPalindrome_twoPointers(String s) {
        if (s == null || s.length() <= 1) {
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
     * Clean string approach - O(n) time, O(n) space
     * Clean the string first, then check if it's a palindrome
     */
    public static boolean isPalindrome_cleanString(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        // Clean the string: remove non-alphanumeric characters and convert to lowercase
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        String cleanedString = cleaned.toString();
        
        // Check if cleaned string is palindrome
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
     * Recursive approach - O(n) time, O(n) space
     * Use recursion to check palindrome
     */
    public static boolean isPalindrome_recursive(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        return isPalindromeHelper(s, 0, s.length() - 1);
    }
    
    private static boolean isPalindromeHelper(String s, int left, int right) {
        // Base case: pointers have crossed
        if (left >= right) {
            return true;
        }
        
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
        
        // Recursively check the rest
        return isPalindromeHelper(s, left + 1, right - 1);
    }
    
    /**
     * Brute force approach - O(n) time, O(n) space
     * Reverse the cleaned string and compare
     */
    public static boolean isPalindrome_bruteForce(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        // Clean the string
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        String cleanedString = cleaned.toString();
        String reversed = cleaned.reverse().toString();
        
        return cleanedString.equals(reversed);
    }
    
    /**
     * Stack approach - O(n) time, O(n) space
     * Use stack to reverse characters
     */
    public static boolean isPalindrome_stack(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        // Clean the string
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        String cleanedString = cleaned.toString();
        
        // Use stack to reverse
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : cleanedString.toCharArray()) {
            stack.push(c);
        }
        
        // Compare with original
        for (char c : cleanedString.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to check if a string is palindrome (without cleaning)
     */
    public static boolean isPalindromeSimple(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Helper method to find longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLen = 1;
        
        for (int i = 0; i < s.length(); i++) {
            // Check odd-length palindromes
            int len1 = expandAroundCenter(s, i, i);
            
            // Check even-length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int currentMaxLen = Math.max(len1, len2);
            
            if (currentMaxLen > maxLen) {
                maxLen = currentMaxLen;
                start = i - (currentMaxLen - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    /**
     * Helper method to count palindromic substrings
     */
    public static int countPalindromicSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes
            count += expandAroundCenter(s, i, i);
            
            // Count even-length palindromes
            count += expandAroundCenter(s, i, i + 1);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        // Test case: s = "A man, a plan, a canal: Panama"
        String s1 = "A man, a plan, a canal: Panama";
        
        System.out.println("Testing Valid Palindrome:");
        System.out.println("String: " + s1);
        
        boolean result1 = isPalindrome_twoPointers(s1);
        System.out.println("Two pointers result: " + result1);
        
        boolean result2 = isPalindrome_cleanString(s1);
        System.out.println("Clean string result: " + result2);
        
        boolean result3 = isPalindrome_recursive(s1);
        System.out.println("Recursive result: " + result3);
        
        boolean result4 = isPalindrome_bruteForce(s1);
        System.out.println("Brute force result: " + result4);
        
        boolean result5 = isPalindrome_stack(s1);
        System.out.println("Stack result: " + result5);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: s = "race a car"
        String s2 = "race a car";
        System.out.println("String: " + s2);
        
        boolean result6 = isPalindrome_twoPointers(s2);
        System.out.println("Two pointers result: " + result6);
        
        // Expected: false
        
        System.out.println();
        
        // Test case: s = " "
        String s3 = " ";
        System.out.println("String: '" + s3 + "'");
        
        boolean result7 = isPalindrome_twoPointers(s3);
        System.out.println("Two pointers result: " + result7);
        
        // Expected: true
        
        System.out.println();
        
        // Test simple palindrome
        String s4 = "racecar";
        System.out.println("Simple string: " + s4);
        
        boolean result8 = isPalindromeSimple(s4);
        System.out.println("Simple palindrome result: " + result8);
        
        // Expected: true
        
        System.out.println();
        
        // Find longest palindrome
        String s5 = "babad";
        System.out.println("String: " + s5);
        
        String longestPal = longestPalindrome(s5);
        System.out.println("Longest palindrome: " + longestPal);
        
        // Expected: "bab" or "aba"
        
        System.out.println();
        
        // Count palindromic substrings
        String s6 = "abc";
        System.out.println("String: " + s6);
        
        int count = countPalindromicSubstrings(s6);
        System.out.println("Number of palindromic substrings: " + count);
        
        // Expected: 3
    }
}

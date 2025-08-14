package syntax;

/**
 * StringOps - Comprehensive String Operations for Competitive Coding
 * 
 * This class provides essential string manipulation methods commonly used in
 * competitive programming and coding interviews. It covers basic operations,
 * advanced techniques, and common patterns.
 * 
 * Key Features:
 * - Basic string operations (length, charAt, substring, etc.)
 * - String comparison methods
 * - String conversion and formatting
 * - Advanced string algorithms (palindrome, anagram, etc.)
 * - Performance considerations for competitive coding
 * 
 * Time Complexity Notes:
 * - Most basic operations: O(1) or O(n) where n is string length
 * - String concatenation: O(n) - use StringBuilder for multiple operations
 * - String comparison: O(min(n,m)) where n,m are string lengths
 */
public class StringOps {

    /**
     * Demonstrates basic string operations and methods
     * Covers fundamental string manipulation techniques
     */
    public static void stringBasicOps() {
        System.out.println("------------------------------------------");
        System.out.println("STRING BASIC OPERATIONS");
        System.out.println("---------------------");

        String s = "Hello World";
        System.out.println("Original string: " + s);

        // Length and character access
        System.out.println("Length: " + s.length());
        System.out.println("Character at index 1: " + s.charAt(1));
        System.out.println("Last character: " + s.charAt(s.length() - 1));

        // Case conversion
        System.out.println("Uppercase: " + s.toUpperCase());
        System.out.println("Lowercase: " + s.toLowerCase());

        // Substring operations
        System.out.println("Substring(0, 5): " + s.substring(0, 5)); // "Hello"
        System.out.println("Substring(6): " + s.substring(6)); // "World"

        // Search operations
        System.out.println("Index of 'World': " + s.indexOf("World"));
        System.out.println("Index of 'o': " + s.indexOf('o'));
        System.out.println("Last index of 'o': " + s.lastIndexOf('o'));
        System.out.println("Contains 'Hello': " + s.contains("Hello"));

        // String splitting
        String[] words = s.split(" ");
        System.out.println("Split by space: " + java.util.Arrays.toString(words));

        // String trimming
        String spaced = "  Hello World  ";
        System.out.println("Trimmed: '" + spaced.trim() + "'");
    }

    /**
     * Demonstrates string comparison methods
     * Shows different ways to compare strings and their use cases
     */
    public static void stringComparisonOps() {
        System.out.println("------------------------------------------");
        System.out.println("STRING COMPARISON OPERATIONS");
        System.out.println("---------------------");

        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = "hello";
        String s4 = "World";

        // Equality comparison
        System.out.println("s1.equals(s2): " + s1.equals(s2)); // true
        System.out.println("s1 == s2: " + (s1 == s2)); // true (string pool)
        
        // Case-insensitive comparison
        System.out.println("s1.equalsIgnoreCase(s3): " + s1.equalsIgnoreCase(s3)); // true
        
        // Lexicographical comparison
        System.out.println("s1.compareTo(s2): " + s1.compareTo(s2)); // 0 (equal)
        System.out.println("s1.compareTo(s4): " + s1.compareTo(s4)); // negative (s1 < s4)
        System.out.println("s4.compareTo(s1): " + s4.compareTo(s1)); // positive (s4 > s1)
        
        // Case-insensitive lexicographical comparison
        System.out.println("s1.compareToIgnoreCase(s3): " + s1.compareToIgnoreCase(s3)); // 0

        // Content comparison
        System.out.println("s1.startsWith('He'): " + s1.startsWith("He")); // true
        System.out.println("s1.endsWith('lo'): " + s1.endsWith("lo")); // true
    }

    /**
     * Demonstrates string conversion and formatting
     * Shows how to convert between different data types and strings
     */
    public static void stringConversionOps() {
        System.out.println("------------------------------------------");
        System.out.println("STRING CONVERSION OPERATIONS");
        System.out.println("---------------------");

        // String to character array
        String s = "Hello";
        char[] chars = s.toCharArray();
        System.out.println("String to char array: " + java.util.Arrays.toString(chars));

        // Character array to string
        char[] charArray = {'H', 'e', 'l', 'l', 'o'};
        String fromChars = new String(charArray);
        System.out.println("Char array to string: " + fromChars);

        // String to bytes
        byte[] bytes = s.getBytes();
        System.out.println("String to bytes: " + java.util.Arrays.toString(bytes));

        // Number to string
        int num = 123;
        String numStr = String.valueOf(num);
        System.out.println("Number to string: " + numStr);

        // String to number
        String numString = "456";
        int parsedNum = Integer.parseInt(numString);
        System.out.println("String to number: " + parsedNum);

        // String formatting
        String formatted = String.format("Hello %s, you are %d years old", "John", 25);
        System.out.println("Formatted string: " + formatted);
    }

    /**
     * Demonstrates advanced string algorithms
     * Shows common string problems and their solutions
     */
    public static void stringAdvancedOps() {
        System.out.println("------------------------------------------");
        System.out.println("STRING ADVANCED OPERATIONS");
        System.out.println("---------------------");

        // Palindrome check
        String palindrome = "racecar";
        System.out.println("Is '" + palindrome + "' palindrome: " + isPalindrome(palindrome));

        // Anagram check
        String s1 = "listen";
        String s2 = "silent";
        System.out.println("Are '" + s1 + "' and '" + s2 + "' anagrams: " + areAnagrams(s1, s2));

        // Character frequency
        String text = "hello world";
        System.out.println("Character frequency in '" + text + "':");
        printCharacterFrequency(text);

        // String reversal
        String original = "Hello World";
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reverseString(original));

        // Longest common prefix
        String[] words = {"flower", "flow", "flight"};
        System.out.println("Longest common prefix: " + longestCommonPrefix(words));
    }

    /**
     * Checks if a string is a palindrome
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(1)
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        
        int left = 0, right = s.length() - 1;
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
     * Checks if two strings are anagrams
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(1) - using fixed size array
     */
    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        int[] charCount = new int[26]; // Assuming lowercase letters
        
        for (char c : s1.toCharArray()) {
            charCount[c - 'a']++;
        }
        
        for (char c : s2.toCharArray()) {
            charCount[c - 'a']--;
            if (charCount[c - 'a'] < 0) return false;
        }
        
        return true;
    }

    /**
     * Prints character frequency in a string
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(1) - using fixed size array
     */
    public static void printCharacterFrequency(String s) {
        int[] freq = new int[26]; // Assuming lowercase letters
        
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                freq[Character.toLowerCase(c) - 'a']++;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                System.out.println("'" + (char)(i + 'a') + "': " + freq[i]);
            }
        }
    }

    /**
     * Reverses a string
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(n) for the result string
     */
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Finds the longest common prefix among an array of strings
     * Time Complexity: O(S) where S is sum of all characters in all strings
     * Space Complexity: O(1)
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    /**
     * Demonstrates StringBuilder operations for efficient string manipulation
     * StringBuilder is preferred for multiple string operations
     */
    public static void stringBuilderOps() {
        System.out.println("------------------------------------------");
        System.out.println("STRINGBUILDER OPERATIONS");
        System.out.println("---------------------");

        StringBuilder sb = new StringBuilder();
        
        // Append operations
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        System.out.println("After append: " + sb.toString());

        // Insert operation
        sb.insert(5, " Beautiful ");
        System.out.println("After insert: " + sb.toString());

        // Delete operation
        sb.delete(5, 16);
        System.out.println("After delete: " + sb.toString());

        // Reverse operation
        sb.reverse();
        System.out.println("After reverse: " + sb.toString());

        // Set character
        sb.setCharAt(0, 'h');
        System.out.println("After setCharAt: " + sb.toString());

        // Capacity and length
        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());
    }

    /**
     * Main method to demonstrate all string operations
     */
    public static void stringOps() {
        stringBasicOps();
        stringComparisonOps();
        stringConversionOps();
        stringAdvancedOps();
        stringBuilderOps();
    }
}

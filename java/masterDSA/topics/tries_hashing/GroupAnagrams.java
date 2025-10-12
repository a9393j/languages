package masterDSA.topics.tries_hashing;
import java.util.*;


/**
 * Group Anagrams
 * 
 * Given an array of strings strs, group the anagrams together. You can return 
 * the answer in any order.
 * 
 * LeetCode: https://leetcode.com/problems/group-anagrams/
 * Difficulty: Medium
 * Tags: hash-table, string, sorting
 * 
 * Approach:
 * - Sorting: Sort each string and use as key
 * - Character count: Count characters and use as key
 * - Prime factorization: Use prime numbers for character mapping
 * 
 * Pseudocode:
 * Sorting:
 * 1. For each string, sort characters
 * 2. Use sorted string as key in hash map
 * 3. Group strings with same key
 * 4. Return grouped anagrams
 */
public class GroupAnagrams {
    
    /**
     * Sorting approach - O(n * m log m) time, O(n * m) space
     * Sort each string and use as key
     */
    public static List<List<String>> groupAnagrams_sorting(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Character count approach - O(n * m) time, O(n * m) space
     * Count characters and use as key
     */
    public static List<List<String>> groupAnagrams_charCount(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                key.append('#').append(count[i]);
            }
            
            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Prime factorization approach - O(n * m) time, O(n * m) space
     * Use prime numbers for character mapping
     */
    public static List<List<String>> groupAnagrams_prime(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        // First 26 prime numbers
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        
        Map<Long, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            long key = 1;
            for (char c : str.toCharArray()) {
                key *= primes[c - 'a'];
            }
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Helper method to check if two strings are anagrams
     */
    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        int[] count = new int[26];
        
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        
        for (char c : s2.toCharArray()) {
            count[c - 'a']--;
        }
        
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to find all anagrams of a string
     */
    public static List<String> findAllAnagrams(String str, String[] words) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (areAnagrams(str, word)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to find anagrams with case sensitivity
     */
    public static List<List<String>> groupAnagramsCaseSensitive(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] count = new int[52]; // 26 lowercase + 26 uppercase
            
            for (char c : str.toCharArray()) {
                if (c >= 'a' && c <= 'z') {
                    count[c - 'a']++;
                } else if (c >= 'A' && c <= 'Z') {
                    count[c - 'A' + 26]++;
                }
            }
            
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 52; i++) {
                key.append('#').append(count[i]);
            }
            
            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Helper method to find anagrams with special characters
     */
    public static List<List<String>> groupAnagramsWithSpecialChars(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            Map<Character, Integer> count = new HashMap<>();
            
            for (char c : str.toCharArray()) {
                count.put(c, count.getOrDefault(c, 0) + 1);
            }
            
            StringBuilder key = new StringBuilder();
            for (char c : count.keySet()) {
                key.append(c).append(count.get(c));
            }
            
            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Helper method to find anagrams with minimum group size
     */
    public static List<List<String>> groupAnagramsMinSize(String[] strs, int minSize) {
        List<List<String>> result = groupAnagrams_sorting(strs);
        result.removeIf(group -> group.size() < minSize);
        return result;
    }
    
    /**
     * Helper method to find anagrams with maximum group size
     */
    public static List<List<String>> groupAnagramsMaxSize(String[] strs, int maxSize) {
        List<List<String>> result = groupAnagrams_sorting(strs);
        result.removeIf(group -> group.size() > maxSize);
        return result;
    }
    
    /**
     * Helper method to find anagrams with specific length
     */
    public static List<List<String>> groupAnagramsByLength(String[] strs, int length) {
        List<String> filtered = new ArrayList<>();
        
        for (String str : strs) {
            if (str.length() == length) {
                filtered.add(str);
            }
        }
        
        return groupAnagrams_sorting(filtered.toArray(new String[0]));
    }
    
    /**
     * Helper method to find anagrams with common prefix
     */
    public static List<List<String>> groupAnagramsWithPrefix(String[] strs, String prefix) {
        List<String> filtered = new ArrayList<>();
        
        for (String str : strs) {
            if (str.startsWith(prefix)) {
                filtered.add(str);
            }
        }
        
        return groupAnagrams_sorting(filtered.toArray(new String[0]));
    }
    
    /**
     * Helper method to find anagrams with common suffix
     */
    public static List<List<String>> groupAnagramsWithSuffix(String[] strs, String suffix) {
        List<String> filtered = new ArrayList<>();
        
        for (String str : strs) {
            if (str.endsWith(suffix)) {
                filtered.add(str);
            }
        }
        
        return groupAnagrams_sorting(filtered.toArray(new String[0]));
    }
    
    /**
     * Helper method to find anagrams with substring
     */
    public static List<List<String>> groupAnagramsWithSubstring(String[] strs, String substring) {
        List<String> filtered = new ArrayList<>();
        
        for (String str : strs) {
            if (str.contains(substring)) {
                filtered.add(str);
            }
        }
        
        return groupAnagrams_sorting(filtered.toArray(new String[0]));
    }
    
    /**
     * Helper method to find anagrams with regex pattern
     */
    public static List<List<String>> groupAnagramsWithRegex(String[] strs, String pattern) {
        List<String> filtered = new ArrayList<>();
        
        for (String str : strs) {
            if (str.matches(pattern)) {
                filtered.add(str);
            }
        }
        
        return groupAnagrams_sorting(filtered.toArray(new String[0]));
    }
    
    /**
     * Helper method to find anagrams with character set
     */
    public static List<List<String>> groupAnagramsWithCharSet(String[] strs, String charSet) {
        List<String> filtered = new ArrayList<>();
        Set<Character> allowedChars = new HashSet<>();
        
        for (char c : charSet.toCharArray()) {
            allowedChars.add(c);
        }
        
        for (String str : strs) {
            boolean valid = true;
            for (char c : str.toCharArray()) {
                if (!allowedChars.contains(c)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                filtered.add(str);
            }
        }
        
        return groupAnagrams_sorting(filtered.toArray(new String[0]));
    }
    
    public static void main(String[] args) {
        // Test case: strs = ["eat","tea","tan","ate","nat","bat"]
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        System.out.println("Testing Group Anagrams:");
        System.out.println("Input: " + Arrays.toString(strs1));
        
        List<List<String>> result1 = groupAnagrams_sorting(strs1);
        System.out.println("Sorting result: " + result1);
        
        List<List<String>> result2 = groupAnagrams_charCount(strs1);
        System.out.println("Character count result: " + result2);
        
        List<List<String>> result3 = groupAnagrams_prime(strs1);
        System.out.println("Prime factorization result: " + result3);
        
        // Expected: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
        
        System.out.println();
        
        // Test case: strs = [""]
        String[] strs2 = {""};
        
        System.out.println("Input: " + Arrays.toString(strs2));
        
        List<List<String>> result4 = groupAnagrams_sorting(strs2);
        System.out.println("Sorting result: " + result4);
        
        // Expected: [[""]]
        
        System.out.println();
        
        // Test case: strs = ["a"]
        String[] strs3 = {"a"};
        
        System.out.println("Input: " + Arrays.toString(strs3));
        
        List<List<String>> result5 = groupAnagrams_sorting(strs3);
        System.out.println("Sorting result: " + result5);
        
        // Expected: [["a"]]
        
        System.out.println();
        
        // Test helper methods
        boolean areAnagrams = areAnagrams("listen", "silent");
        System.out.println("Are 'listen' and 'silent' anagrams: " + areAnagrams);
        
        List<String> allAnagrams = findAllAnagrams("listen", strs1);
        System.out.println("All anagrams of 'listen' in input: " + allAnagrams);
        
        String[] strs4 = {"Eat", "Tea", "Tan", "Ate", "Nat", "Bat"};
        List<List<String>> caseSensitive = groupAnagramsCaseSensitive(strs4);
        System.out.println("Case sensitive anagrams: " + caseSensitive);
        
        String[] strs5 = {"hello!", "olleh!", "world", "dlrow"};
        List<List<String>> withSpecialChars = groupAnagramsWithSpecialChars(strs5);
        System.out.println("Anagrams with special chars: " + withSpecialChars);
        
        List<List<String>> minSize = groupAnagramsMinSize(strs1, 2);
        System.out.println("Anagrams with minimum size 2: " + minSize);
        
        List<List<String>> maxSize = groupAnagramsMaxSize(strs1, 2);
        System.out.println("Anagrams with maximum size 2: " + maxSize);
        
        List<List<String>> byLength = groupAnagramsByLength(strs1, 3);
        System.out.println("Anagrams with length 3: " + byLength);
        
        List<List<String>> withPrefix = groupAnagramsWithPrefix(strs1, "t");
        System.out.println("Anagrams with prefix 't': " + withPrefix);
        
        List<List<String>> withSuffix = groupAnagramsWithSuffix(strs1, "t");
        System.out.println("Anagrams with suffix 't': " + withSuffix);
        
        List<List<String>> withSubstring = groupAnagramsWithSubstring(strs1, "a");
        System.out.println("Anagrams with substring 'a': " + withSubstring);
        
        List<List<String>> withRegex = groupAnagramsWithRegex(strs1, ".*t.*");
        System.out.println("Anagrams matching regex '.*t.*': " + withRegex);
        
        List<List<String>> withCharSet = groupAnagramsWithCharSet(strs1, "aetn");
        System.out.println("Anagrams with character set 'aetn': " + withCharSet);
        
        // Expected: Are anagrams = true, All anagrams = [], Case sensitive = [["Eat", "Tea", "Ate"], ["Tan", "Nat"], ["Bat"]], With special chars = [["hello!", "olleh!"], ["world", "dlrow"]], Min size 2 = [["eat","tea","ate"], ["tan","nat"]], Max size 2 = [["tan","nat"], ["bat"]], By length 3 = [["eat","tea","ate"], ["tan","nat"], ["bat"]], With prefix 't' = [["tan","nat"]], With suffix 't' = [["eat","tea","ate"]], With substring 'a' = [["eat","tea","ate"], ["tan","nat"], ["bat"]], With regex '.*t.*' = [["eat","tea","ate"], ["tan","nat"], ["bat"]], With char set 'aetn' = [["eat","tea","ate"], ["tan","nat"]]
    }
}

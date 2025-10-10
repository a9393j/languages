package masterDSA.topics.strings;
import java.util.*;

import masterDSA.topics.strings.LongestSubstringWithoutRepeatingCharacters.LongestSubstringWithoutRepeatingCharacters;
import masterDSA.topics.strings.LongestPalindromicSubstring.LongestPalindromicSubstring;
import masterDSA.topics.strings.ValidAnagram.ValidAnagram;
import masterDSA.topics.strings.ValidPalindrome.ValidPalindrome;
import masterDSA.topics.strings.MinimumWindowSubstring.MinimumWindowSubstring;
import masterDSA.topics.strings.WordBreak.WordBreak;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for string problems
 * Tests representative methods from each string problem
 */
public class StringsTests {
    
    @Test
    public void testLongestSubstringWithoutRepeatingCharacters() {
        String s = "abcabcbb";
        int result = LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring_hashMap(s);
        assertEquals(3, result);
    }
    
    @Test
    public void testLongestPalindromicSubstring() {
        String s = "babad";
        String result = LongestPalindromicSubstring.longestPalindrome_expandCenter(s);
        assertTrue(result.equals("bab") || result.equals("aba"));
    }
    
    @Test
    public void testValidAnagram() {
        String s = "anagram";
        String t = "nagaram";
        boolean result = ValidAnagram.isAnagram_hashMap(s, t);
        assertTrue(result);
    }
    
    @Test
    public void testValidPalindrome() {
        String s = "A man, a plan, a canal: Panama";
        boolean result = ValidPalindrome.isPalindrome_twoPointers(s);
        assertTrue(result);
    }
    
    @Test
    public void testMinimumWindowSubstring() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = MinimumWindowSubstring.minWindow_slidingWindow(s, t);
        assertEquals("BANC", result);
    }
    
    @Test
    public void testWordBreak() {
        String s = "leetcode";
        java.util.List<String> wordDict = java.util.Arrays.asList("leet", "code");
        boolean result = WordBreak.wordBreak_dp(s, wordDict);
        assertTrue(result);
    }
}

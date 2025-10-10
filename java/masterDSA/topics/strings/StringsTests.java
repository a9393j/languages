package masterDSA.topics.strings.StringsTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Strings topic problems
 */
public class StringsTests {
    
    @Test
    public void testLongestSubstringWithoutRepeatingCharacters() {
        String s = "abcabcbb";
        int result = LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring_slidingWindow(s);
        assertEquals(3, result);
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
        List<String> wordDict = Arrays.asList("leet", "code");
        boolean result = WordBreak.wordBreak_dp(s, wordDict);
        assertTrue(result);
    }
}
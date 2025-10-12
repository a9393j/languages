package masterDSA.topics.tries_hashing;
import java.util.*;

import masterDSA.topics.tries_hashing.ImplementTriePrefixTree.ImplementTriePrefixTree;
import masterDSA.topics.tries_hashing.DesignHashMap.DesignHashMap;
import masterDSA.topics.tries_hashing.GroupAnagrams.GroupAnagrams;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for trie and hashing problems
 * Tests representative methods from each trie/hashing problem
 */
public class TriesHashingTests {
    
    @Test
    public void testImplementTrie() {
        ImplementTriePrefixTree.Trie trie = new ImplementTriePrefixTree.Trie();
        
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        
        trie.insert("app");
        assertTrue(trie.search("app"));
    }
    
    @Test
    public void testDesignHashMap() {
        DesignHashMap.MyHashMap map = new DesignHashMap.MyHashMap();
        
        map.put(1, 1);
        map.put(2, 2);
        assertEquals(1, map.get(1));
        assertEquals(-1, map.get(3));
        
        map.put(2, 1);
        assertEquals(1, map.get(2));
        
        map.remove(2);
        assertEquals(-1, map.get(2));
    }
    
    @Test
    public void testGroupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = GroupAnagrams.groupAnagrams_sorting(strs);
        
        assertEquals(3, result.size());
        
        // Check that each group contains the expected anagrams
        boolean foundEatGroup = false;
        boolean foundTanGroup = false;
        boolean foundBatGroup = false;
        
        for (List<String> group : result) {
            if (group.contains("eat") && group.contains("tea") && group.contains("ate")) {
                foundEatGroup = true;
            } else if (group.contains("tan") && group.contains("nat")) {
                foundTanGroup = true;
            } else if (group.contains("bat")) {
                foundBatGroup = true;
            }
        }
        
        assertTrue(foundEatGroup);
        assertTrue(foundTanGroup);
        assertTrue(foundBatGroup);
    }
    
    @Test
    public void testTrieOperations() {
        ImplementTriePrefixTree.Trie trie = new ImplementTriePrefixTree.Trie();
        
        trie.insert("hello");
        trie.insert("world");
        trie.insert("help");
        
        assertTrue(trie.search("hello"));
        assertTrue(trie.search("world"));
        assertTrue(trie.search("help"));
        assertFalse(trie.search("hel"));
        assertTrue(trie.startsWith("hel"));
        
        List<String> wordsWithPrefix = trie.getWordsWithPrefix("hel");
        assertEquals(2, wordsWithPrefix.size());
        assertTrue(wordsWithPrefix.contains("hello"));
        assertTrue(wordsWithPrefix.contains("help"));
    }
    
    @Test
    public void testHashMapOperations() {
        DesignHashMap.MyHashMap map = new DesignHashMap.MyHashMap();
        
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);
        
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(4));
        
        assertEquals(10, map.get(1));
        assertEquals(20, map.get(2));
        assertEquals(30, map.get(3));
        
        map.remove(2);
        assertEquals(2, map.size());
        assertFalse(map.containsKey(2));
        assertEquals(-1, map.get(2));
    }
    
    @Test
    public void testAnagramDetection() {
        assertTrue(GroupAnagrams.areAnagrams("listen", "silent"));
        assertTrue(GroupAnagrams.areAnagrams("evil", "vile"));
        assertFalse(GroupAnagrams.areAnagrams("hello", "world"));
        assertFalse(GroupAnagrams.areAnagrams("abc", "abcd"));
    }
    
    @Test
    public void testTrieDelete() {
        ImplementTriePrefixTree.Trie trie = new ImplementTriePrefixTree.Trie();
        
        trie.insert("apple");
        trie.insert("app");
        
        assertTrue(trie.search("apple"));
        assertTrue(trie.search("app"));
        
        trie.delete("app");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        
        trie.delete("apple");
        assertFalse(trie.search("apple"));
    }
    
    @Test
    public void testHashMapClear() {
        DesignHashMap.MyHashMap map = new DesignHashMap.MyHashMap();
        
        map.put(1, 10);
        map.put(2, 20);
        assertEquals(2, map.size());
        
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertFalse(map.containsKey(1));
        assertFalse(map.containsKey(2));
    }
}

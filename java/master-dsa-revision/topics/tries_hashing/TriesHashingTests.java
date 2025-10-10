import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Tries and Hashing topic problems
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
    public void testWordSearchII() {
        char[][] board = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> result = WordSearchII.findWords_trie(board, words);
        assertEquals(2, result.size());
        assertTrue(result.contains("eat"));
        assertTrue(result.contains("oath"));
    }
    
    @Test
    public void testLRUCache() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        
        cache.put(3, 3);
        assertEquals(-1, cache.get(2));
        
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }
}

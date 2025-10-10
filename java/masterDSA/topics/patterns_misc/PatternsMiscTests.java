package masterDSA.topics.patterns_misc.PatternsMiscTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Patterns and Miscellaneous topic problems
 */
public class PatternsMiscTests {
    
    @Test
    public void testLargestRectangleInHistogram() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int result = LargestRectangleInHistogram.largestRectangleArea_monotonicStack(heights);
        assertEquals(10, result);
    }
    
    @Test
    public void testUnionFindTemplate() {
        UnionFindTemplate.UnionFind uf = new UnionFindTemplate.UnionFind(5);
        assertEquals(5, uf.getComponents());
        
        uf.union(0, 1);
        assertEquals(4, uf.getComponents());
        assertTrue(uf.connected(0, 1));
        
        uf.union(2, 3);
        assertEquals(3, uf.getComponents());
        assertTrue(uf.connected(2, 3));
        
        uf.union(1, 2);
        assertEquals(2, uf.getComponents());
        assertTrue(uf.connected(0, 3));
    }
    
    @Test
    public void testMinimumWindowSubstringSlidingWindowTemplate() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = MinimumWindowSubstringSlidingWindowTemplate.minWindow_slidingWindow(s, t);
        assertEquals("BANC", result);
    }
}

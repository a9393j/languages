package masterDSA.topics.recursion_backtracking;
import java.util.*;

import masterDSA.topics.recursion_backtracking.GenerateParentheses.GenerateParentheses;
import masterDSA.topics.recursion_backtracking.NQueens.NQueens;
import masterDSA.topics.recursion_backtracking.Subsets.Subsets;
import masterDSA.topics.recursion_backtracking.Combinations.Combinations;
import masterDSA.topics.recursion_backtracking.Permutations.Permutations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for recursion and backtracking problems
 * Tests representative methods from each recursion/backtracking problem
 */
public class RecursionBacktrackingTests {
    
    @Test
    public void testGenerateParentheses() {
        List<String> result = GenerateParentheses.generateParentheses_backtracking(3);
        assertEquals(5, result.size());
        assertTrue(result.contains("((()))"));
        assertTrue(result.contains("(()())"));
        assertTrue(result.contains("(())()"));
        assertTrue(result.contains("()(())"));
        assertTrue(result.contains("()()()"));
    }
    
    @Test
    public void testNQueens() {
        List<List<String>> result = NQueens.solveNQueens_backtracking(4);
        assertEquals(2, result.size());
    }
    
    @Test
    public void testSubsets() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = Subsets.subsets_backtracking(nums);
        assertEquals(8, result.size());
        assertTrue(result.contains(Arrays.asList()));
        assertTrue(result.contains(Arrays.asList(1)));
        assertTrue(result.contains(Arrays.asList(1, 2, 3)));
    }
    
    @Test
    public void testCombinations() {
        List<List<Integer>> result = Combinations.combine_backtracking(4, 2);
        assertEquals(6, result.size());
        assertTrue(result.contains(Arrays.asList(1, 2)));
        assertTrue(result.contains(Arrays.asList(1, 3)));
        assertTrue(result.contains(Arrays.asList(1, 4)));
        assertTrue(result.contains(Arrays.asList(2, 3)));
        assertTrue(result.contains(Arrays.asList(2, 4)));
        assertTrue(result.contains(Arrays.asList(3, 4)));
    }
    
    @Test
    public void testPermutations() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = Permutations.permute_backtracking(nums);
        assertEquals(6, result.size());
        assertTrue(result.contains(Arrays.asList(1, 2, 3)));
        assertTrue(result.contains(Arrays.asList(1, 3, 2)));
        assertTrue(result.contains(Arrays.asList(2, 1, 3)));
        assertTrue(result.contains(Arrays.asList(2, 3, 1)));
        assertTrue(result.contains(Arrays.asList(3, 1, 2)));
        assertTrue(result.contains(Arrays.asList(3, 2, 1)));
    }
    
    @Test
    public void testGenerateParenthesesEmpty() {
        List<String> result = GenerateParentheses.generateParentheses_backtracking(0);
        assertEquals(1, result.size());
        assertTrue(result.contains(""));
    }
    
    @Test
    public void testNQueensCount() {
        int count = NQueens.totalNQueens(4);
        assertEquals(2, count);
    }
    
    @Test
    public void testSubsetsEmpty() {
        int[] nums = {};
        List<List<Integer>> result = Subsets.subsets_backtracking(nums);
        assertEquals(1, result.size());
        assertTrue(result.contains(Arrays.asList()));
    }
}

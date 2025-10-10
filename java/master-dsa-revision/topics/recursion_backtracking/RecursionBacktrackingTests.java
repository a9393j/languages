import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Recursion and Backtracking topic problems
 */
public class RecursionBacktrackingTests {
    
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
    public void testSubsets() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = Subsets.subsets_backtracking(nums);
        assertEquals(8, result.size());
        assertTrue(result.contains(Arrays.asList()));
        assertTrue(result.contains(Arrays.asList(1)));
        assertTrue(result.contains(Arrays.asList(2)));
        assertTrue(result.contains(Arrays.asList(3)));
        assertTrue(result.contains(Arrays.asList(1, 2)));
        assertTrue(result.contains(Arrays.asList(1, 3)));
        assertTrue(result.contains(Arrays.asList(2, 3)));
        assertTrue(result.contains(Arrays.asList(1, 2, 3)));
    }
    
    @Test
    public void testNQueens() {
        int n = 4;
        List<List<String>> result = NQueens.solveNQueens_backtracking(n);
        assertEquals(2, result.size());
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void testSudokuSolver() {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        
        SudokuSolver.solveSudoku_backtracking(board);
        
        // Check if board is solved (no '.' characters)
        for (char[] row : board) {
            for (char cell : row) {
                assertNotEquals('.', cell);
            }
        }
    }
}

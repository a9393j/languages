import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Sorting and Searching topic problems
 */
public class SortingSearchingTests {
    
    @Test
    public void testSearchInRotatedSortedArray() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = SearchInRotatedSortedArray.search_binarySearch(nums, target);
        assertEquals(4, result);
        
        int target2 = 3;
        int result2 = SearchInRotatedSortedArray.search_binarySearch(nums, target2);
        assertEquals(-1, result2);
    }
    
    @Test
    public void testKthSmallestElementInASortedMatrix() {
        int[][] matrix = {
            {1, 5, 9},
            {10, 11, 13},
            {12, 13, 15}
        };
        int k = 8;
        int result = KthSmallestElementInASortedMatrix.kthSmallest_minHeap(matrix, k);
        assertEquals(13, result);
    }
    
    @Test
    public void testQuickSortTemplate() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        QuickSortTemplate.quickSort(arr);
        
        int[] expected = {11, 12, 22, 25, 34, 64, 90};
        assertArrayEquals(expected, arr);
    }
}

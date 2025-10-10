package masterDSA.topics.sorting_searching;

import masterDSA.topics.sorting_searching.BinarySearch.BinarySearch;
import masterDSA.topics.sorting_searching.SearchInRotatedSortedArray.SearchInRotatedSortedArray;
import masterDSA.topics.sorting_searching.FindFirstAndLastPositionOfElementInSortedArray.FindFirstAndLastPositionOfElementInSortedArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for sorting and searching problems
 * Tests representative methods from each sorting/searching problem
 */
public class SortingSearchingTests {
    
    @Test
    public void testBinarySearch() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int result = BinarySearch.search_iterative(nums, target);
        assertEquals(4, result);
    }
    
    @Test
    public void testBinarySearchNotFound() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 2;
        int result = BinarySearch.search_iterative(nums, target);
        assertEquals(-1, result);
    }
    
    @Test
    public void testSearchInRotatedSortedArray() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = SearchInRotatedSortedArray.search_modifiedBinarySearch(nums, target);
        assertEquals(4, result);
    }
    
    @Test
    public void testSearchInRotatedSortedArrayNotFound() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;
        int result = SearchInRotatedSortedArray.search_modifiedBinarySearch(nums, target);
        assertEquals(-1, result);
    }
    
    @Test
    public void testFindFirstAndLastPosition() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = FindFirstAndLastPositionOfElementInSortedArray.searchRange_twoBinarySearches(nums, target);
        assertArrayEquals(new int[]{3, 4}, result);
    }
    
    @Test
    public void testFindFirstAndLastPositionNotFound() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 6;
        int[] result = FindFirstAndLastPositionOfElementInSortedArray.searchRange_twoBinarySearches(nums, target);
        assertArrayEquals(new int[]{-1, -1}, result);
    }
    
    @Test
    public void testBinarySearchRecursive() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int result = BinarySearch.search_recursive(nums, target);
        assertEquals(4, result);
    }
    
    @Test
    public void testFindFirstOccurrence() {
        int[] nums = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int result = BinarySearch.findFirst(nums, target);
        assertEquals(1, result);
    }
    
    @Test
    public void testFindLastOccurrence() {
        int[] nums = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int result = BinarySearch.findLast(nums, target);
        assertEquals(3, result);
    }
    
    @Test
    public void testFindMinInRotated() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int result = SearchInRotatedSortedArray.findMin(nums);
        assertEquals(0, result);
    }
    
    @Test
    public void testCountOccurrences() {
        int[] nums = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int result = FindFirstAndLastPositionOfElementInSortedArray.countOccurrences(nums, target);
        assertEquals(3, result);
    }
}

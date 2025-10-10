package masterDSA.topics.heaps.HeapsTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Heaps topic problems
 */
public class HeapsTests {
    
    @Test
    public void testKthLargestElementInAnArray() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = KthLargestElementInAnArray.findKthLargest_minHeap(nums, k);
        assertEquals(5, result);
    }
    
    @Test
    public void testTopKFrequentElements() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = TopKFrequentElements.topKFrequent_minHeap(nums, k);
        assertEquals(2, result.length);
        assertTrue(Arrays.asList(result).contains(1));
        assertTrue(Arrays.asList(result).contains(2));
    }
    
    @Test
    public void testMedianOfDataStream() {
        MedianOfDataStream medianFinder = new MedianOfDataStream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        assertEquals(1.5, medianFinder.findMedian());
        
        medianFinder.addNum(3);
        assertEquals(2.0, medianFinder.findMedian());
    }
}
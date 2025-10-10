package masterDSA.topics.heaps;
import java.util.*;

import masterDSA.topics.heaps.KthLargestElementInAnArray.KthLargestElementInAnArray;
import masterDSA.topics.heaps.TopKFrequentElements.TopKFrequentElements;
import masterDSA.topics.heaps.MedianOfDataStream.MedianOfDataStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for heap problems
 * Tests representative methods from each heap problem
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
        assertTrue(java.util.Arrays.asList(result).contains(1));
        assertTrue(java.util.Arrays.asList(result).contains(2));
    }
    
    @Test
    public void testMedianOfDataStream() {
        MedianOfDataStream.MedianFinder medianFinder = new MedianOfDataStream.MedianFinder();
        
        medianFinder.addNum(1);
        assertEquals(1.0, medianFinder.findMedian());
        
        medianFinder.addNum(2);
        assertEquals(1.5, medianFinder.findMedian());
        
        medianFinder.addNum(3);
        assertEquals(2.0, medianFinder.findMedian());
    }
}

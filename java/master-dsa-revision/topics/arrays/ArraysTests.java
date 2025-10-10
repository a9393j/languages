import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for Arrays topic problems
 */
public class ArraysTests {
    
    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = TwoSum.twoSum_hashmap(nums, target);
        assertEquals(2, result.length);
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
    }
    
    @Test
    public void testThreeSum() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = ThreeNumberSum3Sum.threeSum_twoPointers(nums);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
    
    @Test
    public void testProductOfArrayExceptSelf() {
        int[] nums = {1, 2, 3, 4};
        int[] result = ProductOfArrayExceptSelf.productExceptSelf_prefixSuffix(nums);
        assertEquals(4, result.length);
        assertEquals(24, result[0]);
        assertEquals(12, result[1]);
        assertEquals(8, result[2]);
        assertEquals(6, result[3]);
    }
    
    @Test
    public void testMissingNumber() {
        int[] nums = {3, 0, 1};
        int result = MissingNumber.missingNumber_xor(nums);
        assertEquals(2, result);
    }
    
    @Test
    public void testMaximumSubarray() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = MaximumSubarrayKadane.maxSubArray_kadane(nums);
        assertEquals(6, result);
    }
}
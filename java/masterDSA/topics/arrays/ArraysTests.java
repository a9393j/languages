package masterDSA.topics.arrays;
import masterDSA.topics.arrays.FourSum.FourSum;
import masterDSA.topics.arrays.NonConstructibleChange.NonConstructibleChange;
import masterDSA.topics.arrays.SmallestDifference.SmallestDifference;
import masterDSA.topics.arrays.ThreeNumberSum3Sum.ThreeNumberSum3Sum;
import masterDSA.topics.arrays.TwoSum.TwoSum;
import masterDSA.topics.arrays.ProductOfArrayExceptSelf.ProductOfArrayExceptSelf;
import masterDSA.topics.arrays.FirstDuplicateValue.FirstDuplicateValue;
import masterDSA.topics.arrays.MinRewards.MinRewards;
import masterDSA.topics.arrays.LongestPeakLongestMountain.LongestPeakLongestMountain;
import masterDSA.topics.arrays.MergeIntervals.MergeIntervals;
import masterDSA.topics.arrays.MissingNumber.MissingNumber;
import masterDSA.topics.arrays.MaximumSubarrayKadane.MaximumSubarrayKadane;
import masterDSA.topics.arrays.SmallestDifferenceBetweenTwoArrays.SmallestDifferenceBetweenTwoArrays;
import masterDSA.topics.arrays.TournamentWinner.TournamentWinner;
import org.junit.jupiter.api.Test;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for array problems
 * Tests representative methods from each array problem
 */
public class ArraysTests {
    
    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = TwoSum.twoSum_hashmap(nums, target);
        assertArrayEquals(new int[]{0, 1}, result);
    }
    
    @Test
    public void testThreeSum() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        java.util.List<java.util.List<Integer>> result = ThreeNumberSum3Sum.threeSum_twoPointers(nums);
        assertEquals(2, result.size());
    }
    
    @Test
    public void testFourSum() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        java.util.List<java.util.List<Integer>> result = FourSum.fourSum_twoPointers(nums, target);
        assertEquals(3, result.size());
    }
    
    @Test
    public void testNonConstructibleChange() {
        int[] coins = {1, 2, 5};
        int result = NonConstructibleChange.nonConstructibleChange_greedy(coins);
        assertEquals(4, result);
    }
    
    @Test
    public void testSmallestDifference() {
        int[] arrayOne = {-1, 5, 10, 20, 28, 3};
        int[] arrayTwo = {26, 134, 135, 15, 17};
        int result = SmallestDifference.smallestDifference_twoPointers(arrayOne, arrayTwo);
        assertEquals(2, result);
    }
    
    @Test
    public void testProductOfArrayExceptSelf() {
        int[] nums = {1, 2, 3, 4};
        int[] result = ProductOfArrayExceptSelf.productExceptSelf_twoPasses(nums);
        assertArrayEquals(new int[]{24, 12, 8, 6}, result);
    }
    
    @Test
    public void testFirstDuplicateValue() {
        int[] array = {2, 1, 5, 2, 3, 3, 4};
        int result = FirstDuplicateValue.firstDuplicateValue_hashSet(array);
        assertEquals(2, result);
    }
    
    @Test
    public void testMinRewards() {
        int[] scores = {8, 4, 2, 1, 3, 6, 7, 9, 5};
        int result = MinRewards.minRewards_twoPasses(scores);
        assertEquals(25, result);
    }
    
    @Test
    public void testLongestPeak() {
        int[] array = {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
        int result = LongestPeakLongestMountain.longestPeak_peakDetection(array);
        assertEquals(6, result);
    }
    
    @Test
    public void testMergeIntervals() {
        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        int[][] result = MergeIntervals.mergeIntervals_sorting(intervals);
        assertEquals(3, result.length);
        assertArrayEquals(new int[]{1, 6}, result[0]);
    }
    
    @Test
    public void testMissingNumber() {
        int[] nums = {3, 0, 1};
        int result = MissingNumber.missingNumber_math(nums);
        assertEquals(2, result);
    }
    
    @Test
    public void testMaximumSubarray() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = MaximumSubarrayKadane.maxSubArray_kadane(nums);
        assertEquals(6, result);
    }
    
    @Test
    public void testSmallestDifferenceBetweenTwoArrays() {
        int[] arrayOne = {-1, 5, 10, 20, 28, 3};
        int[] arrayTwo = {26, 134, 135, 15, 17};
        int result = SmallestDifferenceBetweenTwoArrays.smallestDifference_twoPointers(arrayOne, arrayTwo);
        assertEquals(2, result);
    }
    
    @Test
    public void testTournamentWinner() {
        String[][] competitions = {{"HTML", "C#"}, {"C#", "Python"}, {"Python", "HTML"}};
        int[] results = {0, 0, 1};
        String result = TournamentWinner.tournamentWinner_hashMap(competitions, results);
        assertEquals("Python", result);
    }
}

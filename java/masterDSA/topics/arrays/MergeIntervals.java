package masterDSA.topics.arrays;
import java.util.*;


/**
 * Merge Intervals
 * 
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping 
 * intervals, and return an array of the non-overlapping intervals that cover all the 
 * intervals in the input.
 * 
 * LeetCode: https://leetcode.com/problems/merge-intervals/
 * Difficulty: Medium
 * Tags: intervals, sorting
 * 
 * Approach:
 * - Sort intervals by start time
 * - Iterate through sorted intervals and merge overlapping ones
 * - Two intervals overlap if start of second <= end of first
 * 
 * Pseudocode:
 * 1. Sort intervals by start time
 * 2. Initialize result list with first interval
 * 3. For each subsequent interval:
 *    a. If current interval overlaps with last interval in result:
 *       - Merge by updating end time of last interval
 *    b. Else add current interval to result
 * 4. Return result
 */
public class MergeIntervals {
    
    /**
     * Sorting approach - O(n log n) time, O(1) space (excluding output)
     * Sort intervals by start time and merge overlapping ones
     */
    public static int[][] mergeIntervals_sorting(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = result.get(result.size() - 1);
            
            // Check if intervals overlap
            if (current[0] <= last[1]) {
                // Merge intervals by updating end time
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap, add current interval
                result.add(current);
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    /**
     * Two pointers approach - O(n log n) time, O(1) space (excluding output)
     * Sort intervals and use two pointers to merge
     */
    public static int[][] mergeIntervals_twoPointers(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                // Overlapping intervals, merge them
                end = Math.max(end, intervals[i][1]);
            } else {
                // No overlap, add previous interval and start new one
                result.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        
        // Add the last interval
        result.add(new int[]{start, end});
        
        return result.toArray(new int[result.size()][]);
    }
    
    /**
     * Brute force approach - O(n²) time, O(n) space
     * For each interval, check all other intervals for overlap
     */
    public static int[][] mergeIntervals_bruteForce(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        List<int[]> result = new ArrayList<>();
        boolean[] merged = new boolean[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            if (merged[i]) continue;
            
            int start = intervals[i][0];
            int end = intervals[i][1];
            merged[i] = true;
            
            // Find all overlapping intervals
            boolean foundOverlap = true;
            while (foundOverlap) {
                foundOverlap = false;
                for (int j = 0; j < intervals.length; j++) {
                    if (merged[j]) continue;
                    
                    // Check if intervals overlap
                    if (intervals[j][0] <= end && intervals[j][1] >= start) {
                        start = Math.min(start, intervals[j][0]);
                        end = Math.max(end, intervals[j][1]);
                        merged[j] = true;
                        foundOverlap = true;
                    }
                }
            }
            
            result.add(new int[]{start, end});
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    /**
     * Helper method to check if two intervals overlap
     */
    public static boolean intervalsOverlap(int[] interval1, int[] interval2) {
        return interval1[0] <= interval2[1] && interval2[0] <= interval1[1];
    }
    
    /**
     * Helper method to print intervals
     */
    public static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        // Test case: intervals = [[1,3],[2,6],[8,10],[15,18]]
        int[][] intervals1 = {{1,3}, {2,6}, {8,10}, {15,18}};
        
        System.out.println("Testing Merge Intervals:");
        System.out.print("Input: ");
        printIntervals(intervals1);
        
        int[][] result1 = mergeIntervals_sorting(intervals1);
        System.out.print("Sorting result: ");
        printIntervals(result1);
        
        int[][] result2 = mergeIntervals_twoPointers(intervals1);
        System.out.print("Two pointers result: ");
        printIntervals(result2);
        
        int[][] result3 = mergeIntervals_bruteForce(intervals1);
        System.out.print("Brute force result: ");
        printIntervals(result3);
        
        // Expected: [[1,6],[8,10],[15,18]]
        
        System.out.println();
        
        // Test case: intervals = [[1,4],[4,5]]
        int[][] intervals2 = {{1,4}, {4,5}};
        System.out.print("Input: ");
        printIntervals(intervals2);
        
        int[][] result4 = mergeIntervals_sorting(intervals2);
        System.out.print("Sorting result: ");
        printIntervals(result4);
        
        // Expected: [[1,5]]
        
        System.out.println();
        
        // Test case: intervals = [[1,4],[2,3]]
        int[][] intervals3 = {{1,4}, {2,3}};
        System.out.print("Input: ");
        printIntervals(intervals3);
        
        int[][] result5 = mergeIntervals_sorting(intervals3);
        System.out.print("Sorting result: ");
        printIntervals(result5);
        
        // Expected: [[1,4]]
    }
}

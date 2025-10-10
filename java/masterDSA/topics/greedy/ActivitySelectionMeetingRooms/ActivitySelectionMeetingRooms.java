/**
 * Activity Selection / Meeting Rooms
 * 
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], 
 * determine if a person could attend all meetings.
 * 
 * LeetCode: https://leetcode.com/problems/meeting-rooms/
 * Difficulty: Medium
 * Tags: intervals, greedy
 * 
 * Approach:
 * - Sort intervals by start time
 * - Check if any interval overlaps with next interval
 * - If overlap found, return false
 * 
 * Pseudocode:
 * 1. Sort intervals by start time
 * 2. For each interval:
 *    a. If current interval overlaps with next interval, return false
 * 3. Return true if no overlaps found
 */

package masterDSA.topics.greedy.ActivitySelectionMeetingRooms.ActivitySelectionMeetingRooms;

import java.util.*;

public class ActivitySelectionMeetingRooms {
    
    /**
     * Sorting approach - O(n log n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static boolean canAttendMeetings_sorting(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Check for overlaps
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false; // Overlap found
            }
        }
        
        return true;
    }
    
    /**
     * Alternative approach using TreeMap - O(n log n) time, O(n) space
     * Uses TreeMap to automatically handle sorting
     */
    public static boolean canAttendMeetings_treeMap(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            // Check for overlaps
            Integer prevStart = map.floorKey(start);
            if (prevStart != null && map.get(prevStart) > start) {
                return false;
            }
            
            Integer nextStart = map.ceilingKey(start);
            if (nextStart != null && end > nextStart) {
                return false;
            }
            
            map.put(start, end);
        }
        
        return true;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}};
        int[][] intervals3 = {{1, 4}, {4, 5}};
        
        System.out.println("Testing Activity Selection / Meeting Rooms:");
        
        System.out.println("Intervals: " + Arrays.deepToString(intervals1));
        System.out.println("Sorting result: " + canAttendMeetings_sorting(intervals1));
        System.out.println("TreeMap result: " + canAttendMeetings_treeMap(intervals1));
        
        System.out.println("Intervals: " + Arrays.deepToString(intervals2));
        System.out.println("Sorting result: " + canAttendMeetings_sorting(intervals2));
        
        System.out.println("Intervals: " + Arrays.deepToString(intervals3));
        System.out.println("Sorting result: " + canAttendMeetings_sorting(intervals3));
    }
}
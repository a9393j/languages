package masterDSA.topics.patterns_misc.MergeIntervalsPattern;
import java.util.*;


/**
 * Merge Intervals Pattern
 * 
 * Problem Statement:
 * The merge intervals pattern deals with problems involving overlapping intervals,
 * merging intervals, and finding intersections between intervals.
 * 
 * LeetCode Link: https://leetcode.com/tag/merge-intervals/
 * Difficulty: Medium
 * Tags: Array, Sorting, Intervals
 * 
 * Approach Summary:
 * 1. Sort intervals by start time
 * 2. Merge overlapping intervals
 * 3. Find intersections between interval sets
 * 4. Insert new intervals into existing set
 * 5. Handle various interval operations
 * 
 * Pseudocode:
 * 1. Sort intervals by start time
 * 2. Initialize result with first interval
 * 3. For each subsequent interval:
 *    - If overlaps with last merged interval, merge them
 *    - Otherwise, add to result
 * 4. Return merged intervals
 * 
 * Time Complexity: O(n log n) - sorting dominates
 * Space Complexity: O(1) to O(n) depending on approach
 */
public class MergeIntervalsPattern {
    
    // Interval class definition
    static class Interval {
        int start;
        int end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
    
    /**
     * Merge overlapping intervals
     * 
     * @param intervals Array of intervals
     * @return Array of merged intervals
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            
            // If intervals overlap, merge them
            if (current[1] >= next[0]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                // No overlap, add current to result and move to next
                merged.add(current);
                current = next;
            }
        }
        
        // Add the last interval
        merged.add(current);
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    /**
     * Insert new interval into existing intervals
     * 
     * @param intervals Existing intervals
     * @param newInterval New interval to insert
     * @return Array of intervals after insertion
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        
        // Add all intervals that end before new interval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Merge overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        
        // Add merged interval
        result.add(newInterval);
        
        // Add remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    /**
     * Find intersection of two interval lists
     * 
     * @param intervals1 First list of intervals
     * @param intervals2 Second list of intervals
     * @return List of intersecting intervals
     */
    public static int[][] intervalIntersection(int[][] intervals1, int[][] intervals2) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < intervals1.length && j < intervals2.length) {
            // Find intersection
            int start = Math.max(intervals1[i][0], intervals2[j][0]);
            int end = Math.min(intervals1[i][1], intervals2[j][1]);
            
            // If there's an intersection, add it
            if (start <= end) {
                result.add(new int[]{start, end});
            }
            
            // Move pointer of interval that ends first
            if (intervals1[i][1] < intervals2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    /**
     * Check if intervals can be attended
     * 
     * @param intervals Array of intervals
     * @return True if all intervals can be attended
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Check for overlaps
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false; // Overlap found
            }
        }
        
        return true;
    }
    
    /**
     * Minimum number of meeting rooms required
     * 
     * @param intervals Array of meeting intervals
     * @return Minimum number of rooms needed
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Extract start and end times
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        // Sort both arrays
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int rooms = 0;
        int endIndex = 0;
        
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endIndex]) {
                rooms++; // Need another room
            } else {
                endIndex++; // Free up a room
            }
        }
        
        return rooms;
    }
    
    /**
     * Minimum number of arrows to burst balloons
     * 
     * @param points Array of balloon intervals
     * @return Minimum number of arrows needed
     */
    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        
        // Sort by end point
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        
        int arrows = 1;
        int end = points[0][1];
        
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                arrows++;
                end = points[i][1];
            }
        }
        
        return arrows;
    }
    
    /**
     * Employee free time
     * 
     * @param schedule List of employee schedules
     * @return List of free time intervals
     */
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        
        // Flatten all intervals
        List<Interval> allIntervals = new ArrayList<>();
        for (List<Interval> employeeSchedule : schedule) {
            allIntervals.addAll(employeeSchedule);
        }
        
        // Sort by start time
        allIntervals.sort((a, b) -> Integer.compare(a.start, b.start));
        
        // Merge overlapping intervals
        List<Interval> merged = new ArrayList<>();
        for (Interval interval : allIntervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1).end < interval.start) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1).end = Math.max(merged.get(merged.size() - 1).end, interval.end);
            }
        }
        
        // Find gaps between merged intervals
        for (int i = 1; i < merged.size(); i++) {
            if (merged.get(i - 1).end < merged.get(i).start) {
                result.add(new Interval(merged.get(i - 1).end, merged.get(i).start));
            }
        }
        
        return result;
    }
    
    /**
     * Maximum number of non-overlapping intervals
     * 
     * @param intervals Array of intervals
     * @return Maximum number of non-overlapping intervals
     */
    public static int maxNonOverlappingIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 1;
        int end = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        
        return count;
    }
    
    /**
     * Remove covered intervals
     * 
     * @param intervals Array of intervals
     * @return Number of intervals after removing covered ones
     */
    public static int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Sort by start time, then by end time (descending)
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        
        int count = 0;
        int end = Integer.MIN_VALUE;
        
        for (int[] interval : intervals) {
            if (interval[1] > end) {
                count++;
                end = interval[1];
            }
        }
        
        return count;
    }
    
    /**
     * Partition labels
     * 
     * @param s Input string
     * @return List of partition sizes
     */
    public static List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        
        // Find last occurrence of each character
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }
        
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);
            
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to print intervals
     */
    private static void printIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print("[" + interval[0] + ", " + interval[1] + "] ");
        }
        System.out.println();
    }
    
    /**
     * Demonstration method showing various merge intervals patterns
     */
    public static void runExample() {
        System.out.println("=== Merge Intervals Pattern Examples ===");
        
        // Merge intervals
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merged = merge(intervals1);
        System.out.print("Merged intervals: ");
        printIntervals(merged);
        
        // Insert interval
        int[][] intervals2 = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] inserted = insert(intervals2, newInterval);
        System.out.print("After inserting [2, 5]: ");
        printIntervals(inserted);
        
        // Interval intersection
        int[][] intervals3 = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] intervals4 = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] intersection = intervalIntersection(intervals3, intervals4);
        System.out.print("Interval intersection: ");
        printIntervals(intersection);
        
        // Can attend meetings
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};
        boolean canAttend = canAttendMeetings(meetings);
        System.out.println("Can attend all meetings: " + canAttend);
        
        // Minimum meeting rooms
        int[][] meetingRooms = {{0, 30}, {5, 10}, {15, 20}};
        int minRooms = minMeetingRooms(meetingRooms);
        System.out.println("Minimum meeting rooms needed: " + minRooms);
        
        // Minimum arrows to burst balloons
        int[][] balloons = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int minArrows = findMinArrowShots(balloons);
        System.out.println("Minimum arrows to burst balloons: " + minArrows);
        
        // Maximum non-overlapping intervals
        int[][] nonOverlapping = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int maxNonOverlapping = maxNonOverlappingIntervals(nonOverlapping);
        System.out.println("Maximum non-overlapping intervals: " + maxNonOverlapping);
        
        // Remove covered intervals
        int[][] covered = {{1, 4}, {3, 6}, {2, 8}};
        int remaining = removeCoveredIntervals(covered);
        System.out.println("Intervals after removing covered ones: " + remaining);
        
        // Partition labels
        String s = "ababcbacadefegdehijhklij";
        List<Integer> partitions = partitionLabels(s);
        System.out.println("Partition labels: " + partitions);
        
        // Employee free time
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 3), new Interval(6, 7)));
        schedule.add(Arrays.asList(new Interval(2, 4)));
        schedule.add(Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
        List<Interval> freeTime = employeeFreeTime(schedule);
        System.out.println("Employee free time: " + freeTime);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

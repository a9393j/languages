/**
 * Kth Smallest Element in a Sorted Matrix
 * 
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, 
 * return the kth smallest element in the matrix. Note that it is the kth smallest element 
 * in the sorted order, not the kth distinct element.
 * 
 * LeetCode: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * Difficulty: Medium
 * Tags: heap, binary-search
 * 
 * Approach:
 * - Use min heap to find kth smallest element
 * - Add first element of each row to heap
 * - Remove k-1 elements, then return kth element
 * 
 * Pseudocode:
 * 1. Create min heap
 * 2. Add first element of each row to heap
 * 3. Remove k-1 elements
 * 4. Return kth element
 */

package masterDSA.topics.sorting_searching.KthSmallestElementInASortedMatrix.KthSmallestElementInASortedMatrix;

import java.util.*;

public class KthSmallestElementInASortedMatrix {
    
    /**
     * Min heap approach - O(k log n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static int kthSmallest_minHeap(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Add first element of each row to heap
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{matrix[i][0], i, 0});
        }
        
        // Remove k-1 elements
        for (int i = 0; i < k - 1; i++) {
            int[] current = minHeap.poll();
            int row = current[1];
            int col = current[2];
            
            // Add next element from same row
            if (col + 1 < n) {
                minHeap.offer(new int[]{matrix[row][col + 1], row, col + 1});
            }
        }
        
        // Return kth element
        return minHeap.poll()[0];
    }
    
    /**
     * Binary search approach - O(n log(max-min)) time, O(1) space
     * Alternative approach using binary search
     */
    public static int kthSmallest_binarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessOrEqual(matrix, mid);
            
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private static int countLessOrEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        int row = n - 1;
        int col = 0;
        
        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }
        
        return count;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 5, 9},
            {10, 11, 13},
            {12, 13, 15}
        };
        int k = 8;
        
        System.out.println("Testing Kth Smallest Element in a Sorted Matrix:");
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("k: " + k);
        
        int result1 = kthSmallest_minHeap(matrix, k);
        System.out.println("Min heap result: " + result1);
        
        int result2 = kthSmallest_binarySearch(matrix, k);
        System.out.println("Binary search result: " + result2);
    }
}
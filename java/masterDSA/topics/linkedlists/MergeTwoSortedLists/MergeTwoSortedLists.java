/**
 * Merge Two Sorted Lists
 * 
 * You are given the heads of two sorted linked lists list1 and list2. Merge the two lists 
 * in a one sorted list. The list should be made by splicing together the nodes of the 
 * first two lists. Return the head of the merged linked list.
 * 
 * LeetCode: https://leetcode.com/problems/merge-two-sorted-lists/
 * Difficulty: Easy
 * Tags: pointers
 * 
 * Approach:
 * - Use dummy node to simplify edge cases
 * - Compare values from both lists and link smaller one
 * - Handle remaining nodes from either list
 * 
 * Pseudocode:
 * 1. Create dummy node and current pointer
 * 2. While both lists have nodes:
 *    a. Compare values and link smaller one
 *    b. Move pointer and advance the chosen list
 * 3. Link remaining nodes from either list
 * 4. Return dummy.next
 */

package masterDSA.topics.linkedlists.MergeTwoSortedLists.MergeTwoSortedLists;

import java.util.*;

public class MergeTwoSortedLists {
    
    /**
     * Iterative approach - O(n + m) time, O(1) space
     * Most efficient approach for this problem
     */
    public static ListNode mergeTwoLists_iterative(ListNode list1, ListNode list2) {
        // Create dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Merge while both lists have nodes
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Link remaining nodes from either list
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    /**
     * Recursive approach - O(n + m) time, O(n + m) space
     * Alternative approach using recursion
     */
    public static ListNode mergeTwoLists_recursive(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        // Recursive case
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists_recursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists_recursive(list1, list2.next);
            return list2;
        }
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create first sorted list: 1 -> 2 -> 4
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        
        // Create second sorted list: 1 -> 3 -> 4
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        
        System.out.println("Testing Merge Two Sorted Lists:");
        System.out.println("List 1: " + listToString(list1));
        System.out.println("List 2: " + listToString(list2));
        
        ListNode merged1 = mergeTwoLists_iterative(list1, list2);
        System.out.println("Iterative result: " + listToString(merged1));
        
        // Create another set of lists for recursive test
        ListNode list3 = new ListNode(1);
        list3.next = new ListNode(2);
        list3.next.next = new ListNode(4);
        
        ListNode list4 = new ListNode(1);
        list4.next = new ListNode(3);
        list4.next.next = new ListNode(4);
        
        ListNode merged2 = mergeTwoLists_recursive(list3, list4);
        System.out.println("Recursive result: " + listToString(merged2));
    }
    
    // Helper method to convert linked list to string
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }
    
    // ListNode definition
    public static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
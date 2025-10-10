/**
 * Interweaving / Merge Alternate Nodes
 * 
 * Given two linked lists, merge them by alternating nodes from each list.
 * The first node should come from the first list, the second from the second list,
 * and so on. If one list is longer than the other, append the remaining nodes.
 * 
 * LeetCode: (No direct LeetCode equivalent)
 * Difficulty: Medium
 * Tags: pointer-manipulation
 * 
 * Approach:
 * - Use dummy node to simplify edge cases
 * - Alternate between linking nodes from each list
 * - Handle remaining nodes from either list
 * 
 * Pseudocode:
 * 1. Create dummy node and current pointer
 * 2. While both lists have nodes:
 *    a. Link node from first list
 *    b. Link node from second list
 *    c. Move pointers
 * 3. Link remaining nodes from either list
 * 4. Return dummy.next
 */

import java.util.*;

public class InterweavingMergeAlternateNodes {
    
    /**
     * Iterative approach - O(n + m) time, O(1) space
     * Most efficient approach for this problem
     */
    public static ListNode interweave_iterative(ListNode list1, ListNode list2) {
        // Create dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Alternate between linking nodes from each list
        while (list1 != null && list2 != null) {
            // Link node from first list
            current.next = list1;
            list1 = list1.next;
            current = current.next;
            
            // Link node from second list
            current.next = list2;
            list2 = list2.next;
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
    public static ListNode interweave_recursive(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        // Recursive case: alternate nodes
        ListNode next1 = list1.next;
        ListNode next2 = list2.next;
        
        list1.next = list2;
        list2.next = interweave_recursive(next1, next2);
        
        return list1;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create first list: 1 -> 3 -> 5
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);
        
        // Create second list: 2 -> 4 -> 6
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);
        
        System.out.println("Testing Interweaving / Merge Alternate Nodes:");
        System.out.println("List 1: " + listToString(list1));
        System.out.println("List 2: " + listToString(list2));
        
        ListNode interweaved1 = interweave_iterative(list1, list2);
        System.out.println("Iterative result: " + listToString(interweaved1));
        
        // Create another set of lists for recursive test
        ListNode list3 = new ListNode(1);
        list3.next = new ListNode(3);
        list3.next.next = new ListNode(5);
        
        ListNode list4 = new ListNode(2);
        list4.next = new ListNode(4);
        list4.next.next = new ListNode(6);
        
        ListNode interweaved2 = interweave_recursive(list3, list4);
        System.out.println("Recursive result: " + listToString(interweaved2));
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
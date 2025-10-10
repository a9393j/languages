/**
 * Remove Nth Node From End
 * 
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * 
 * LeetCode: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Difficulty: Medium
 * Tags: two-pointers
 * 
 * Approach:
 * - Use two pointers with gap of n nodes
 * - Move first pointer n steps ahead
 * - Move both pointers until first reaches end
 * - Remove node at second pointer
 * 
 * Pseudocode:
 * 1. Create dummy node to handle edge cases
 * 2. Move first pointer n steps ahead
 * 3. Move both pointers until first reaches end
 * 4. Remove node at second pointer
 * 5. Return dummy.next
 */

import java.util.*;

public class RemoveNthNodeFromEnd {
    
    /**
     * Two pointers approach - O(n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static ListNode removeNthFromEnd_twoPointers(ListNode head, int n) {
        // Create dummy node to handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode first = dummy;
        ListNode second = dummy;
        
        // Move first pointer n steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        
        // Move both pointers until first reaches end
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        // Remove node at second pointer
        second.next = second.next.next;
        
        return dummy.next;
    }
    
    /**
     * Alternative approach using length calculation - O(n) time, O(1) space
     * Calculate length first, then remove node
     */
    public static ListNode removeNthFromEnd_length(ListNode head, int n) {
        // Calculate length of list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        
        // If removing head node
        if (n == length) {
            return head.next;
        }
        
        // Find node before the one to remove
        current = head;
        for (int i = 0; i < length - n - 1; i++) {
            current = current.next;
        }
        
        // Remove the node
        current.next = current.next.next;
        
        return head;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        
        System.out.println("Testing Remove Nth Node From End:");
        System.out.println("Original list: " + listToString(head1));
        
        ListNode result1 = removeNthFromEnd_twoPointers(head1, 2);
        System.out.println("After removing 2nd from end: " + listToString(result1));
        
        // Create another list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        
        ListNode result2 = removeNthFromEnd_length(head2, 2);
        System.out.println("After removing 2nd from end (length method): " + listToString(result2));
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
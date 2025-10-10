/**
 * Reverse Linked List
 * 
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * 
 * LeetCode: https://leetcode.com/problems/reverse-linked-list/
 * Difficulty: Easy
 * Tags: iterative, two-pointers
 * 
 * Approach:
 * - Iterative: Use three pointers (prev, curr, next) to reverse links
 * - Recursive: Reverse the rest of the list and then reverse current link
 * 
 * Pseudocode:
 * Iterative:
 * 1. Initialize prev = null, curr = head
 * 2. While curr != null:
 *    a. Store next node
 *    b. Reverse link: curr.next = prev
 *    c. Move pointers: prev = curr, curr = next
 * 3. Return prev (new head)
 */

import java.util.*;

public class ReverseLinkedList {
    
    /**
     * Iterative approach - O(n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static ListNode reverseList_iterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next; // Store next node
            curr.next = prev;          // Reverse link
            prev = curr;               // Move prev pointer
            curr = next;               // Move curr pointer
        }
        
        return prev; // prev is the new head
    }
    
    /**
     * Recursive approach - O(n) time, O(n) space
     * Alternative approach using recursion
     */
    public static ListNode reverseList_recursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode newHead = reverseList_recursive(head.next);
        
        // Reverse the link between current node and next node
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        System.out.println("Testing Reverse Linked List:");
        System.out.println("Original list: " + listToString(head));
        
        ListNode reversed1 = reverseList_iterative(head);
        System.out.println("Iterative result: " + listToString(reversed1));
        
        // Create another list for recursive test
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        
        ListNode reversed2 = reverseList_recursive(head2);
        System.out.println("Recursive result: " + listToString(reversed2));
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
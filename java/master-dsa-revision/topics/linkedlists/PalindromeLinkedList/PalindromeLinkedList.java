/**
 * Palindrome Linked List
 * 
 * Given the head of a singly linked list, return true if it is a palindrome.
 * 
 * LeetCode: https://leetcode.com/problems/palindrome-linked-list/
 * Difficulty: Medium
 * Tags: reverse-half, stack
 * 
 * Approach:
 * - Find middle of list using slow/fast pointers
 * - Reverse second half of list
 * - Compare first half with reversed second half
 * - Alternative: use stack to store first half
 * 
 * Pseudocode:
 * 1. Find middle of list using slow/fast pointers
 * 2. Reverse second half of list
 * 3. Compare first half with reversed second half
 * 4. Return true if all comparisons match
 */

import java.util.*;

public class PalindromeLinkedList {
    
    /**
     * Reverse half approach - O(n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static boolean isPalindrome_reverseHalf(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle of list
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null; // Break the list
        
        // Compare first half with reversed second half
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
    
    /**
     * Stack approach - O(n) time, O(n) space
     * Alternative approach using stack
     */
    public static boolean isPalindrome_stack(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        
        // Push first half to stack
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Skip middle node if odd length
        if (fast != null) {
            slow = slow.next;
        }
        
        // Compare second half with stack
        while (slow != null) {
            if (stack.pop() != slow.val) {
                return false;
            }
            slow = slow.next;
        }
        
        return true;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create palindrome list: 1 -> 2 -> 2 -> 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        
        // Create non-palindrome list: 1 -> 2 -> 3 -> 4
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        
        System.out.println("Testing Palindrome Linked List:");
        
        System.out.println("List: " + listToString(head1));
        System.out.println("Reverse half result: " + isPalindrome_reverseHalf(head1));
        System.out.println("Stack result: " + isPalindrome_stack(head1));
        
        System.out.println("List: " + listToString(head2));
        System.out.println("Reverse half result: " + isPalindrome_reverseHalf(head2));
        System.out.println("Stack result: " + isPalindrome_stack(head2));
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
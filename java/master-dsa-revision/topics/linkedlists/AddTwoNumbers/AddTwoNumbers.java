/**
 * Add Two Numbers
 * 
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list.
 * 
 * LeetCode: https://leetcode.com/problems/add-two-numbers/
 * Difficulty: Medium
 * Tags: linked-list, math
 * 
 * Approach:
 * - Use dummy node to simplify edge cases
 * - Add digits from both lists with carry
 * - Handle remaining digits and carry
 * 
 * Pseudocode:
 * 1. Create dummy node and current pointer
 * 2. While both lists have nodes or carry exists:
 *    a. Calculate sum of current digits plus carry
 *    b. Create new node with sum % 10
 *    c. Update carry to sum / 10
 *    d. Move pointers
 * 3. Return dummy.next
 */

import java.util.*;

public class AddTwoNumbers {
    
    /**
     * Iterative approach - O(max(m,n)) time, O(max(m,n)) space
     * Most efficient approach for this problem
     */
    public static ListNode addTwoNumbers_iterative(ListNode l1, ListNode l2) {
        // Create dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        // Add digits while both lists have nodes or carry exists
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            
            // Add digit from first list
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            // Add digit from second list
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            // Create new node with sum % 10
            current.next = new ListNode(sum % 10);
            current = current.next;
            
            // Update carry
            carry = sum / 10;
        }
        
        return dummy.next;
    }
    
    /**
     * Recursive approach - O(max(m,n)) time, O(max(m,n)) space
     * Alternative approach using recursion
     */
    public static ListNode addTwoNumbers_recursive(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }
    
    private static ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        // Base case: both lists are null and no carry
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        
        int sum = carry;
        
        // Add digit from first list
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        
        // Add digit from second list
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        
        // Create new node and recurse
        ListNode node = new ListNode(sum % 10);
        node.next = addTwoNumbersHelper(l1, l2, sum / 10);
        
        return node;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create first number: 342 (stored as 2 -> 4 -> 3)
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        
        // Create second number: 465 (stored as 5 -> 6 -> 4)
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        System.out.println("Testing Add Two Numbers:");
        System.out.println("Number 1: " + listToString(l1));
        System.out.println("Number 2: " + listToString(l2));
        
        ListNode result1 = addTwoNumbers_iterative(l1, l2);
        System.out.println("Iterative result: " + listToString(result1));
        
        // Create another set of numbers for recursive test
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(4);
        l3.next.next = new ListNode(3);
        
        ListNode l4 = new ListNode(5);
        l4.next = new ListNode(6);
        l4.next.next = new ListNode(4);
        
        ListNode result2 = addTwoNumbers_recursive(l3, l4);
        System.out.println("Recursive result: " + listToString(result2));
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
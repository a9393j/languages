package masterDSA.topics.linkedlists;
import java.util.*;


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
 * - Iterative: Use three pointers (prev, current, next) to reverse links
 * - Recursive: Reverse the rest of the list, then reverse current node
 * - Stack: Push all nodes to stack, then pop to create reversed list
 * 
 * Pseudocode:
 * Iterative:
 * 1. Initialize prev = null, current = head
 * 2. While current != null:
 *    a. next = current.next
 *    b. current.next = prev
 *    c. prev = current
 *    d. current = next
 * 3. Return prev
 */
public class ReverseLinkedList {
    
    /**
     * Definition for singly-linked list node
     */
    public static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {}
        
        ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
    /**
     * Iterative approach - O(n) time, O(1) space
     * Use three pointers to reverse links iteratively
     */
    public static ListNode reverseList_iterative(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next; // Store next node
            current.next = prev;          // Reverse the link
            prev = current;               // Move prev forward
            current = next;               // Move current forward
        }
        
        return prev; // prev is the new head
    }
    
    /**
     * Recursive approach - O(n) time, O(n) space
     * Recursively reverse the rest of the list, then reverse current node
     */
    public static ListNode reverseList_recursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode reversedHead = reverseList_recursive(head.next);
        
        // Reverse the current node
        head.next.next = head;
        head.next = null;
        
        return reversedHead;
    }
    
    /**
     * Stack approach - O(n) time, O(n) space
     * Push all nodes to stack, then pop to create reversed list
     */
    public static ListNode reverseList_stack(ListNode head) {
        if (head == null) {
            return null;
        }
        
        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        
        // Push all nodes to stack
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        
        // Pop nodes to create reversed list
        ListNode newHead = stack.pop();
        ListNode prev = newHead;
        
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            prev.next = node;
            prev = node;
        }
        
        prev.next = null; // Set last node's next to null
        
        return newHead;
    }
    
    /**
     * Two pointers approach - O(n) time, O(1) space
     * Alternative iterative implementation
     */
    public static ListNode reverseList_twoPointers(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    /**
     * Helper method to create a linked list from array
     */
    public static ListNode createLinkedList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    /**
     * Helper method to convert linked list to array
     */
    public static int[] linkedListToArray(ListNode head) {
        java.util.List<Integer> values = new java.util.ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        
        return values.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Helper method to print linked list
     */
    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        
        System.out.println("]");
    }
    
    /**
     * Helper method to get length of linked list
     */
    public static int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    /**
     * Helper method to find middle node of linked list
     */
    public static ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    /**
     * Helper method to check if linked list is palindrome
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle node
        ListNode middle = findMiddle(head);
        
        // Reverse second half
        ListNode secondHalf = reverseList_iterative(middle);
        
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
    
    public static void main(String[] args) {
        // Test case: [1,2,3,4,5]
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = createLinkedList(values);
        
        System.out.println("Testing Reverse Linked List:");
        System.out.print("Original: ");
        printLinkedList(head);
        
        ListNode result1 = reverseList_iterative(head);
        System.out.print("Iterative: ");
        printLinkedList(result1);
        
        // Recreate list for next test
        head = createLinkedList(values);
        ListNode result2 = reverseList_recursive(head);
        System.out.print("Recursive: ");
        printLinkedList(result2);
        
        // Recreate list for next test
        head = createLinkedList(values);
        ListNode result3 = reverseList_stack(head);
        System.out.print("Stack: ");
        printLinkedList(result3);
        
        // Recreate list for next test
        head = createLinkedList(values);
        ListNode result4 = reverseList_twoPointers(head);
        System.out.print("Two pointers: ");
        printLinkedList(result4);
        
        // Expected: [5,4,3,2,1]
        
        System.out.println();
        
        // Test case: [1,2]
        int[] values2 = {1, 2};
        ListNode head2 = createLinkedList(values2);
        
        System.out.print("Original: ");
        printLinkedList(head2);
        
        ListNode result5 = reverseList_iterative(head2);
        System.out.print("Reversed: ");
        printLinkedList(result5);
        
        // Expected: [2,1]
        
        System.out.println();
        
        // Test case: [1]
        int[] values3 = {1};
        ListNode head3 = createLinkedList(values3);
        
        System.out.print("Original: ");
        printLinkedList(head3);
        
        ListNode result6 = reverseList_iterative(head3);
        System.out.print("Reversed: ");
        printLinkedList(result6);
        
        // Expected: [1]
        
        System.out.println();
        
        // Test case: []
        ListNode head4 = null;
        
        System.out.print("Original: ");
        printLinkedList(head4);
        
        ListNode result7 = reverseList_iterative(head4);
        System.out.print("Reversed: ");
        printLinkedList(result7);
        
        // Expected: []
        
        System.out.println();
        
        // Test helper methods
        int[] values5 = {1, 2, 3, 4, 5};
        ListNode head5 = createLinkedList(values5);
        
        System.out.println("Length: " + getLength(head5));
        
        ListNode middle = findMiddle(head5);
        System.out.println("Middle node: " + middle.val);
        
        int[] array = linkedListToArray(head5);
        System.out.println("Array: " + java.util.Arrays.toString(array));
        
        // Test palindrome
        int[] values6 = {1, 2, 3, 2, 1};
        ListNode head6 = createLinkedList(values6);
        
        System.out.print("Palindrome test: ");
        printLinkedList(head6);
        System.out.println("Is palindrome: " + isPalindrome(head6));
        
        // Expected: true
    }
}

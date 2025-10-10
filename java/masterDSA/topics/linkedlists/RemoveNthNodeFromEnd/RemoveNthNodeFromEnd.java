package masterDSA.topics.linkedlists.RemoveNthNodeFromEnd;
import java.util.*;


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
 * - Two pointers: Move first pointer n steps ahead, then move both pointers
 * - When first pointer reaches end, second pointer is at the node to remove
 * - Use dummy node to handle edge case of removing head
 * 
 * Pseudocode:
 * 1. Create dummy node and set its next to head
 * 2. Move first pointer n steps ahead
 * 3. Move both pointers until first pointer reaches end
 * 4. Remove the node after second pointer
 * 5. Return dummy.next
 */
public class RemoveNthNodeFromEnd {
    
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
     * Two pointers approach - O(n) time, O(1) space
     * Use two pointers with n gap between them
     */
    public static ListNode removeNthFromEnd_twoPointers(ListNode head, int n) {
        // Create dummy node to handle edge case of removing head
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
        
        // Remove the node after second pointer
        second.next = second.next.next;
        
        return dummy.next;
    }
    
    /**
     * Two passes approach - O(n) time, O(1) space
     * First pass: count total nodes, second pass: remove nth from end
     */
    public static ListNode removeNthFromEnd_twoPasses(ListNode head, int n) {
        // Count total nodes
        int length = getLength(head);
        
        // Handle edge case: remove head
        if (n == length) {
            return head.next;
        }
        
        // Find node before the one to remove
        ListNode current = head;
        for (int i = 0; i < length - n - 1; i++) {
            current = current.next;
        }
        
        // Remove the node
        current.next = current.next.next;
        
        return head;
    }
    
    /**
     * Recursive approach - O(n) time, O(n) space
     * Use recursion to find nth node from end
     */
    public static ListNode removeNthFromEnd_recursive(ListNode head, int n) {
        int[] count = {0};
        return removeNthFromEndHelper(head, n, count);
    }
    
    private static ListNode removeNthFromEndHelper(ListNode head, int n, int[] count) {
        if (head == null) {
            return null;
        }
        
        // Recursively process the rest
        head.next = removeNthFromEndHelper(head.next, n, count);
        
        // Increment count and check if current node should be removed
        count[0]++;
        if (count[0] == n) {
            return head.next;
        }
        
        return head;
    }
    
    /**
     * Stack approach - O(n) time, O(n) space
     * Use stack to store nodes, then pop n nodes
     */
    public static ListNode removeNthFromEnd_stack(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        ListNode current = head;
        
        // Push all nodes to stack
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        
        // Pop n nodes
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        
        // Remove the nth node
        if (stack.isEmpty()) {
            return head.next; // Remove head
        } else {
            ListNode nodeBefore = stack.pop();
            nodeBefore.next = nodeBefore.next.next;
            return head;
        }
    }
    
    /**
     * Array approach - O(n) time, O(n) space
     * Convert to array, remove element, convert back
     */
    public static ListNode removeNthFromEnd_array(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        // Convert to array
        java.util.List<Integer> values = new java.util.ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        
        // Remove nth element from end
        if (n > values.size()) {
            return head;
        }
        
        values.remove(values.size() - n);
        
        // Convert back to linked list
        if (values.isEmpty()) {
            return null;
        }
        
        ListNode newHead = new ListNode(values.get(0));
        current = newHead;
        
        for (int i = 1; i < values.size(); i++) {
            current.next = new ListNode(values.get(i));
            current = current.next;
        }
        
        return newHead;
    }
    
    /**
     * Helper method to get length of linked list
     */
    private static int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
        return length;
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
     * Helper method to find nth node from end
     */
    public static ListNode findNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode first = head;
        ListNode second = head;
        
        // Move first pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (first == null) {
                return null; // n is greater than list length
            }
            first = first.next;
        }
        
        // Move both pointers until first reaches end
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        return second;
    }
    
    /**
     * Helper method to remove nth node from beginning
     */
    public static ListNode removeNthFromBeginning(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        if (n == 1) {
            return head.next;
        }
        
        ListNode current = head;
        for (int i = 1; i < n - 1; i++) {
            if (current == null) {
                return head;
            }
            current = current.next;
        }
        
        if (current != null && current.next != null) {
            current.next = current.next.next;
        }
        
        return head;
    }
    
    /**
     * Helper method to remove all nodes with given value
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode current = dummy;
        
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return dummy.next;
    }
    
    public static void main(String[] args) {
        // Test case: [1,2,3,4,5], n = 2
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = createLinkedList(values);
        
        System.out.println("Testing Remove Nth Node From End:");
        System.out.print("Original: ");
        printLinkedList(head);
        
        ListNode result1 = removeNthFromEnd_twoPointers(head, 2);
        System.out.print("Two pointers (n=2): ");
        printLinkedList(result1);
        
        // Recreate list for next test
        head = createLinkedList(values);
        ListNode result2 = removeNthFromEnd_twoPasses(head, 2);
        System.out.print("Two passes (n=2): ");
        printLinkedList(result2);
        
        // Recreate list for next test
        head = createLinkedList(values);
        ListNode result3 = removeNthFromEnd_recursive(head, 2);
        System.out.print("Recursive (n=2): ");
        printLinkedList(result3);
        
        // Recreate list for next test
        head = createLinkedList(values);
        ListNode result4 = removeNthFromEnd_stack(head, 2);
        System.out.print("Stack (n=2): ");
        printLinkedList(result4);
        
        // Recreate list for next test
        head = createLinkedList(values);
        ListNode result5 = removeNthFromEnd_array(head, 2);
        System.out.print("Array (n=2): ");
        printLinkedList(result5);
        
        // Expected: [1,2,3,5]
        
        System.out.println();
        
        // Test case: [1,2], n = 1
        int[] values2 = {1, 2};
        ListNode head2 = createLinkedList(values2);
        
        System.out.print("Original: ");
        printLinkedList(head2);
        
        ListNode result6 = removeNthFromEnd_twoPointers(head2, 1);
        System.out.print("Two pointers (n=1): ");
        printLinkedList(result6);
        
        // Expected: [1]
        
        System.out.println();
        
        // Test case: [1], n = 1
        int[] values3 = {1};
        ListNode head3 = createLinkedList(values3);
        
        System.out.print("Original: ");
        printLinkedList(head3);
        
        ListNode result7 = removeNthFromEnd_twoPointers(head3, 1);
        System.out.print("Two pointers (n=1): ");
        printLinkedList(result7);
        
        // Expected: []
        
        System.out.println();
        
        // Test case: [1,2,3,4,5], n = 5
        int[] values4 = {1, 2, 3, 4, 5};
        ListNode head4 = createLinkedList(values4);
        
        System.out.print("Original: ");
        printLinkedList(head4);
        
        ListNode result8 = removeNthFromEnd_twoPointers(head4, 5);
        System.out.print("Two pointers (n=5): ");
        printLinkedList(result8);
        
        // Expected: [2,3,4,5]
        
        System.out.println();
        
        // Test helper methods
        int[] values5 = {1, 2, 3, 4, 5};
        ListNode head5 = createLinkedList(values5);
        
        System.out.println("Testing helper methods:");
        System.out.print("Original: ");
        printLinkedList(head5);
        
        ListNode nthNode = findNthFromEnd(head5, 3);
        System.out.println("3rd node from end: " + (nthNode != null ? nthNode.val : "null"));
        
        ListNode result9 = removeNthFromBeginning(head5, 2);
        System.out.print("Remove 2nd from beginning: ");
        printLinkedList(result9);
        
        // Expected: 3rd node from end is 3, remove 2nd from beginning gives [1,3,4,5]
        
        System.out.println();
        
        // Test remove elements
        int[] values6 = {1, 2, 3, 2, 4, 2};
        ListNode head6 = createLinkedList(values6);
        
        System.out.print("Original: ");
        printLinkedList(head6);
        
        ListNode result10 = removeElements(head6, 2);
        System.out.print("Remove all 2s: ");
        printLinkedList(result10);
        
        // Expected: [1,3,4]
    }
}

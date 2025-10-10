/**
 * Detect Cycle in Linked List
 * 
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached 
 * again by continuously following the next pointer.
 * 
 * LeetCode: https://leetcode.com/problems/linked-list-cycle/
 * Difficulty: Easy
 * Tags: floyd, two-pointers
 * 
 * Approach:
 * - Floyd's cycle detection algorithm (tortoise and hare)
 * - Use two pointers: slow (moves 1 step) and fast (moves 2 steps)
 * - If there's a cycle, fast pointer will eventually meet slow pointer
 * 
 * Pseudocode:
 * 1. Initialize slow = head, fast = head
 * 2. While fast != null && fast.next != null:
 *    a. Move slow pointer one step
 *    b. Move fast pointer two steps
 *    c. If slow == fast, cycle detected
 * 3. Return false if no cycle found
 */

package masterDSA.topics.linkedlists.DetectCycleInLinkedList.DetectCycleInLinkedList;

import java.util.*;

public class DetectCycleInLinkedList {
    
    /**
     * Floyd's cycle detection algorithm - O(n) time, O(1) space
     * Most efficient approach for this problem
     */
    public static boolean hasCycle_floyd(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Move pointers until they meet or fast reaches end
        while (fast != null && fast.next != null) {
            slow = slow.next;        // Move slow pointer one step
            fast = fast.next.next;   // Move fast pointer two steps
            
            // If pointers meet, cycle detected
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * HashSet approach - O(n) time, O(n) space
     * Alternative approach using HashSet to track visited nodes
     */
    public static boolean hasCycle_hashSet(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        
        while (current != null) {
            // If we've seen this node before, cycle detected
            if (visited.contains(current)) {
                return true;
            }
            
            visited.add(current);
            current = current.next;
        }
        
        return false;
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        // Create linked list with cycle: 1 -> 2 -> 3 -> 4 -> 2 (cycle)
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Create cycle
        
        // Create linked list without cycle: 1 -> 2 -> 3 -> 4
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        
        System.out.println("Testing Detect Cycle in Linked List:");
        
        System.out.println("List with cycle:");
        System.out.println("Floyd's algorithm result: " + hasCycle_floyd(head1));
        System.out.println("HashSet result: " + hasCycle_hashSet(head1));
        
        System.out.println("List without cycle:");
        System.out.println("Floyd's algorithm result: " + hasCycle_floyd(head2));
        System.out.println("HashSet result: " + hasCycle_hashSet(head2));
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
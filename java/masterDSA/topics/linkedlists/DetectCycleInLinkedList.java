package masterDSA.topics.linkedlists;
import java.util.*;


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
 * - Floyd's cycle detection: Use slow and fast pointers
 * - Hash set: Store visited nodes in a set
 * - Marking: Mark visited nodes (modifies the list)
 * 
 * Pseudocode:
 * Floyd's:
 * 1. Initialize slow = head, fast = head
 * 2. While fast != null && fast.next != null:
 *    a. slow = slow.next
 *    b. fast = fast.next.next
 *    c. If slow == fast, return true
 * 3. Return false
 */
public class DetectCycleInLinkedList {
    
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
     * Floyd's cycle detection algorithm - O(n) time, O(1) space
     * Use slow and fast pointers to detect cycle
     */
    public static boolean hasCycle_floyd(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Move slow pointer one step and fast pointer two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            // If they meet, there's a cycle
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Hash set approach - O(n) time, O(n) space
     * Store visited nodes in a set
     */
    public static boolean hasCycle_hashSet(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        
        while (current != null) {
            // If we've seen this node before, there's a cycle
            if (visited.contains(current)) {
                return true;
            }
            
            visited.add(current);
            current = current.next;
        }
        
        return false;
    }
    
    /**
     * Marking approach - O(n) time, O(1) space
     * Mark visited nodes by setting their value to a special marker
     * Note: This modifies the original list
     */
    public static boolean hasCycle_marking(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        final int MARKER = Integer.MIN_VALUE;
        ListNode current = head;
        
        while (current != null) {
            // If we find a marked node, there's a cycle
            if (current.val == MARKER) {
                return true;
            }
            
            // Mark current node
            current.val = MARKER;
            current = current.next;
        }
        
        return false;
    }
    
    /**
     * Find the start of the cycle - O(n) time, O(1) space
     * Use Floyd's algorithm to find cycle start
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Phase 1: Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break;
            }
        }
        
        // No cycle found
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Phase 2: Find cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
    
    /**
     * Find cycle length - O(n) time, O(1) space
     * Use Floyd's algorithm to find cycle length
     */
    public static int cycleLength(ListNode head) {
        if (head == null || head.next == null) {
            return 0;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Phase 1: Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break;
            }
        }
        
        // No cycle found
        if (fast == null || fast.next == null) {
            return 0;
        }
        
        // Phase 2: Count cycle length
        int length = 1;
        fast = fast.next;
        while (slow != fast) {
            fast = fast.next;
            length++;
        }
        
        return length;
    }
    
    /**
     * Helper method to create a linked list with cycle
     */
    public static ListNode createLinkedListWithCycle(int[] values, int cyclePos) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleStart = null;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
            
            if (i == cyclePos) {
                cycleStart = current;
            }
        }
        
        // Create cycle
        if (cycleStart != null) {
            current.next = cycleStart;
        }
        
        return head;
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
     * Helper method to print linked list (limited to avoid infinite loop)
     */
    public static void printLinkedList(ListNode head, int maxNodes) {
        ListNode current = head;
        System.out.print("[");
        int count = 0;
        
        while (current != null && count < maxNodes) {
            System.out.print(current.val);
            if (current.next != null && count < maxNodes - 1) {
                System.out.print(" -> ");
            }
            current = current.next;
            count++;
        }
        
        if (current != null) {
            System.out.print(" -> ...");
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
    
    public static void main(String[] args) {
        // Test case: [3,2,0,-4] with cycle at position 1
        int[] values = {3, 2, 0, -4};
        ListNode head = createLinkedListWithCycle(values, 1);
        
        System.out.println("Testing Detect Cycle in Linked List:");
        System.out.print("List with cycle: ");
        printLinkedList(head, 10);
        
        boolean result1 = hasCycle_floyd(head);
        System.out.println("Floyd's algorithm: " + result1);
        
        boolean result2 = hasCycle_hashSet(head);
        System.out.println("Hash set: " + result2);
        
        // Note: Marking approach modifies the list, so we'll skip it for this test
        
        ListNode cycleStart = detectCycle(head);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.val : "No cycle"));
        
        int cycleLen = cycleLength(head);
        System.out.println("Cycle length: " + cycleLen);
        
        // Expected: true, cycle starts at node with value 2, cycle length is 3
        
        System.out.println();
        
        // Test case: [1,2] with cycle at position 0
        int[] values2 = {1, 2};
        ListNode head2 = createLinkedListWithCycle(values2, 0);
        
        System.out.print("List with cycle: ");
        printLinkedList(head2, 10);
        
        boolean result3 = hasCycle_floyd(head2);
        System.out.println("Floyd's algorithm: " + result3);
        
        ListNode cycleStart2 = detectCycle(head2);
        System.out.println("Cycle starts at node with value: " + (cycleStart2 != null ? cycleStart2.val : "No cycle"));
        
        // Expected: true, cycle starts at node with value 1
        
        System.out.println();
        
        // Test case: [1] with no cycle
        int[] values3 = {1};
        ListNode head3 = createLinkedList(values3);
        
        System.out.print("List without cycle: ");
        printLinkedList(head3, 10);
        
        boolean result4 = hasCycle_floyd(head3);
        System.out.println("Floyd's algorithm: " + result4);
        
        ListNode cycleStart3 = detectCycle(head3);
        System.out.println("Cycle starts at node with value: " + (cycleStart3 != null ? cycleStart3.val : "No cycle"));
        
        // Expected: false, no cycle
        
        System.out.println();
        
        // Test case: [] (empty list)
        ListNode head4 = null;
        
        System.out.print("Empty list: ");
        printLinkedList(head4, 10);
        
        boolean result5 = hasCycle_floyd(head4);
        System.out.println("Floyd's algorithm: " + result5);
        
        // Expected: false
        
        System.out.println();
        
        // Test case: [1,2,3,4,5] with no cycle
        int[] values5 = {1, 2, 3, 4, 5};
        ListNode head5 = createLinkedList(values5);
        
        System.out.print("List without cycle: ");
        printLinkedList(head5, 10);
        
        boolean result6 = hasCycle_floyd(head5);
        System.out.println("Floyd's algorithm: " + result6);
        
        ListNode cycleStart5 = detectCycle(head5);
        System.out.println("Cycle starts at node with value: " + (cycleStart5 != null ? cycleStart5.val : "No cycle"));
        
        // Expected: false, no cycle
        
        System.out.println();
        
        // Test helper methods
        System.out.println("Length: " + getLength(head5));
        
        ListNode middle = findMiddle(head5);
        System.out.println("Middle node: " + middle.val);
        
        // Expected: Length 5, Middle node 3
    }
}

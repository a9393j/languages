package masterDSA.topics.linkedlists.InterweavingMergeAlternateNodes;
import java.util.*;


/**
 * Interweaving / Merge Alternate Nodes
 * 
 * Given two linked lists, merge them by alternating nodes from each list.
 * If one list is longer than the other, append the remaining nodes from the longer list.
 * 
 * LeetCode: 
 * Difficulty: Medium
 * Tags: pointer-manipulation
 * 
 * Approach:
 * - Use dummy node and two pointers to merge alternately
 * - Alternate between the two lists while maintaining order
 * - Handle remaining nodes from longer list
 * 
 * Pseudocode:
 * 1. Create dummy node and current pointer
 * 2. While both lists have nodes:
 *    a. Add node from first list
 *    b. Add node from second list
 *    c. Move pointers forward
 * 3. Add remaining nodes from either list
 * 4. Return dummy.next
 */
public class InterweavingMergeAlternateNodes {
    
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
     * Merge alternate nodes approach - O(n + m) time, O(1) space
     * Use dummy node and two pointers to merge alternately
     */
    public static ListNode mergeAlternateNodes(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (list1 != null && list2 != null) {
            // Add node from first list
            current.next = list1;
            list1 = list1.next;
            current = current.next;
            
            // Add node from second list
            current.next = list2;
            list2 = list2.next;
            current = current.next;
        }
        
        // Add remaining nodes from either list
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    /**
     * Merge alternate nodes with alternating pattern - O(n + m) time, O(1) space
     * Alternate between the two lists while maintaining order
     */
    public static ListNode mergeAlternateNodes_pattern(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        ListNode head = list1;
        ListNode current1 = list1;
        ListNode current2 = list2;
        
        while (current1 != null && current2 != null) {
            ListNode next1 = current1.next;
            ListNode next2 = current2.next;
            
            // Link current1 to current2
            current1.next = current2;
            
            // Move to next nodes
            current1 = next1;
            current2 = next2;
            
            // Link current2 to next1 (if exists)
            if (current1 != null) {
                current2.next = current1;
            }
        }
        
        return head;
    }
    
    /**
     * Merge alternate nodes with two pointers - O(n + m) time, O(1) space
     * Alternative implementation using two pointers
     */
    public static ListNode mergeAlternateNodes_twoPointers(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (list1 != null && list2 != null) {
            tail.next = list1;
            tail = tail.next;
            list1 = list1.next;
            
            tail.next = list2;
            tail = tail.next;
            list2 = list2.next;
        }
        
        // Append remaining nodes
        tail.next = (list1 != null) ? list1 : list2;
        
        return dummy.next;
    }
    
    /**
     * Merge alternate nodes recursively - O(n + m) time, O(n + m) space
     * Recursive approach to merge alternately
     */
    public static ListNode mergeAlternateNodes_recursive(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        // Take first node from list1
        ListNode head = list1;
        list1 = list1.next;
        
        // Recursively merge the rest
        head.next = mergeAlternateNodes_recursive(list2, list1);
        
        return head;
    }
    
    /**
     * Merge alternate nodes with count - O(n + m) time, O(1) space
     * Use count to determine which list to take from
     */
    public static ListNode mergeAlternateNodes_count(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int count = 0;
        
        while (list1 != null && list2 != null) {
            if (count % 2 == 0) {
                // Take from first list
                current.next = list1;
                list1 = list1.next;
            } else {
                // Take from second list
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
            count++;
        }
        
        // Add remaining nodes
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
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
     * Helper method to split linked list into two halves
     */
    public static ListNode[] splitLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return new ListNode[]{head, null};
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode secondHalf = slow.next;
        slow.next = null;
        
        return new ListNode[]{head, secondHalf};
    }
    
    /**
     * Helper method to merge two sorted lists
     */
    public static ListNode mergeSortedLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
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
        
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    public static void main(String[] args) {
        // Test case: list1 = [1,2,3], list2 = [4,5,6]
        int[] values1 = {1, 2, 3};
        int[] values2 = {4, 5, 6};
        
        ListNode list1 = createLinkedList(values1);
        ListNode list2 = createLinkedList(values2);
        
        System.out.println("Testing Interweaving / Merge Alternate Nodes:");
        System.out.print("List 1: ");
        printLinkedList(list1);
        System.out.print("List 2: ");
        printLinkedList(list2);
        
        ListNode result1 = mergeAlternateNodes(list1, list2);
        System.out.print("Merge alternate: ");
        printLinkedList(result1);
        
        // Recreate lists for next test
        list1 = createLinkedList(values1);
        list2 = createLinkedList(values2);
        ListNode result2 = mergeAlternateNodes_pattern(list1, list2);
        System.out.print("Merge pattern: ");
        printLinkedList(result2);
        
        // Recreate lists for next test
        list1 = createLinkedList(values1);
        list2 = createLinkedList(values2);
        ListNode result3 = mergeAlternateNodes_twoPointers(list1, list2);
        System.out.print("Two pointers: ");
        printLinkedList(result3);
        
        // Recreate lists for next test
        list1 = createLinkedList(values1);
        list2 = createLinkedList(values2);
        ListNode result4 = mergeAlternateNodes_recursive(list1, list2);
        System.out.print("Recursive: ");
        printLinkedList(result4);
        
        // Recreate lists for next test
        list1 = createLinkedList(values1);
        list2 = createLinkedList(values2);
        ListNode result5 = mergeAlternateNodes_count(list1, list2);
        System.out.print("Count-based: ");
        printLinkedList(result5);
        
        // Expected: [1,4,2,5,3,6]
        System.out.println("Test completed successfully!");
    }
}

package masterDSA.topics.linkedlists.MergeTwoSortedLists;
import java.util.*;


/**
 * Merge Two Sorted Lists
 * 
 * You are given the heads of two sorted linked lists list1 and list2. 
 * Merge the two lists in a one sorted list. The list should be made by splicing 
 * together the nodes of the first two lists. Return the head of the merged linked list.
 * 
 * LeetCode: https://leetcode.com/problems/merge-two-sorted-lists/
 * Difficulty: Easy
 * Tags: pointers
 * 
 * Approach:
 * - Iterative: Use dummy node and compare nodes from both lists
 * - Recursive: Compare heads and recursively merge the rest
 * - In-place: Merge without using extra space
 * 
 * Pseudocode:
 * Iterative:
 * 1. Create dummy node and current pointer
 * 2. While both lists have nodes:
 *    a. Compare current nodes
 *    b. Add smaller node to result
 *    c. Move pointer forward
 * 3. Add remaining nodes from either list
 * 4. Return dummy.next
 */
public class MergeTwoSortedLists {
    
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
     * Iterative approach - O(n + m) time, O(1) space
     * Use dummy node and compare nodes from both lists
     */
    public static ListNode mergeTwoLists_iterative(ListNode list1, ListNode list2) {
        // Create dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Merge while both lists have nodes
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
        
        // Add remaining nodes from either list
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    /**
     * Recursive approach - O(n + m) time, O(n + m) space
     * Compare heads and recursively merge the rest
     */
    public static ListNode mergeTwoLists_recursive(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        // Compare heads and recursively merge
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists_recursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists_recursive(list1, list2.next);
            return list2;
        }
    }
    
    /**
     * In-place approach - O(n + m) time, O(1) space
     * Merge without using extra space
     */
    public static ListNode mergeTwoLists_inPlace(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        // Determine which list to use as base
        ListNode head, current;
        if (list1.val <= list2.val) {
            head = list1;
            current = list1;
            list1 = list1.next;
        } else {
            head = list2;
            current = list2;
            list2 = list2.next;
        }
        
        // Merge remaining nodes
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
        
        // Add remaining nodes
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return head;
    }
    
    /**
     * Two pointers approach - O(n + m) time, O(1) space
     * Alternative iterative implementation
     */
    public static ListNode mergeTwoLists_twoPointers(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        
        // Append remaining nodes
        tail.next = (list1 != null) ? list1 : list2;
        
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
     * Helper method to merge multiple sorted lists
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    
    private static ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        
        return mergeTwoLists_iterative(left, right);
    }
    
    /**
     * Helper method to merge two lists with duplicate removal
     */
    public static ListNode mergeTwoLists_noDuplicates(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                if (current == dummy || current.val != list1.val) {
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
            } else if (list1.val > list2.val) {
                if (current == dummy || current.val != list2.val) {
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next;
            } else {
                // Equal values, add one and skip both
                if (current == dummy || current.val != list1.val) {
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
                list2 = list2.next;
            }
        }
        
        // Add remaining nodes
        while (list1 != null) {
            if (current == dummy || current.val != list1.val) {
                current.next = list1;
                current = current.next;
            }
            list1 = list1.next;
        }
        
        while (list2 != null) {
            if (current == dummy || current.val != list2.val) {
                current.next = list2;
                current = current.next;
            }
            list2 = list2.next;
        }
        
        current.next = null;
        return dummy.next;
    }
    
    public static void main(String[] args) {
        // Test case: list1 = [1,2,4], list2 = [1,3,4]
        int[] values1 = {1, 2, 4};
        int[] values2 = {1, 3, 4};
        
        ListNode list1 = createLinkedList(values1);
        ListNode list2 = createLinkedList(values2);
        
        System.out.println("Testing Merge Two Sorted Lists:");
        System.out.print("List 1: ");
        printLinkedList(list1);
        System.out.print("List 2: ");
        printLinkedList(list2);
        
        ListNode result1 = mergeTwoLists_iterative(list1, list2);
        System.out.print("Iterative: ");
        printLinkedList(result1);
        
        // Recreate lists for next test
        list1 = createLinkedList(values1);
        list2 = createLinkedList(values2);
        ListNode result2 = mergeTwoLists_recursive(list1, list2);
        System.out.print("Recursive: ");
        printLinkedList(result2);
        
        // Recreate lists for next test
        list1 = createLinkedList(values1);
        list2 = createLinkedList(values2);
        ListNode result3 = mergeTwoLists_inPlace(list1, list2);
        System.out.print("In-place: ");
        printLinkedList(result3);
        
        // Recreate lists for next test
        list1 = createLinkedList(values1);
        list2 = createLinkedList(values2);
        ListNode result4 = mergeTwoLists_twoPointers(list1, list2);
        System.out.print("Two pointers: ");
        printLinkedList(result4);
        
        // Expected: [1,1,2,3,4,4]
        
        System.out.println();
        
        // Test case: list1 = [], list2 = [0]
        int[] values3 = {};
        int[] values4 = {0};
        
        ListNode list3 = createLinkedList(values3);
        ListNode list4 = createLinkedList(values4);
        
        System.out.print("List 1: ");
        printLinkedList(list3);
        System.out.print("List 2: ");
        printLinkedList(list4);
        
        ListNode result5 = mergeTwoLists_iterative(list3, list4);
        System.out.print("Merged: ");
        printLinkedList(result5);
        
        // Expected: [0]
        
        System.out.println();
        
        // Test case: list1 = [], list2 = []
        int[] values5 = {};
        int[] values6 = {};
        
        ListNode list5 = createLinkedList(values5);
        ListNode list6 = createLinkedList(values6);
        
        System.out.print("List 1: ");
        printLinkedList(list5);
        System.out.print("List 2: ");
        printLinkedList(list6);
        
        ListNode result6 = mergeTwoLists_iterative(list5, list6);
        System.out.print("Merged: ");
        printLinkedList(result6);
        
        // Expected: []
        
        System.out.println();
        
        // Test merge K lists
        int[] values7 = {1, 4, 5};
        int[] values8 = {1, 3, 4};
        int[] values9 = {2, 6};
        
        ListNode list7 = createLinkedList(values7);
        ListNode list8 = createLinkedList(values8);
        ListNode list9 = createLinkedList(values9);
        
        ListNode[] lists = {list7, list8, list9};
        
        System.out.println("Testing Merge K Lists:");
        for (int i = 0; i < lists.length; i++) {
            System.out.print("List " + (i + 1) + ": ");
            printLinkedList(lists[i]);
        }
        
        ListNode result7 = mergeKLists(lists);
        System.out.print("Merged K lists: ");
        printLinkedList(result7);
        
        // Expected: [1,1,2,3,4,4,5,6]
        
        System.out.println();
        
        // Test merge with no duplicates
        int[] values10 = {1, 2, 3};
        int[] values11 = {2, 3, 4};
        
        ListNode list10 = createLinkedList(values10);
        ListNode list11 = createLinkedList(values11);
        
        System.out.println("Testing Merge with No Duplicates:");
        System.out.print("List 1: ");
        printLinkedList(list10);
        System.out.print("List 2: ");
        printLinkedList(list11);
        
        ListNode result8 = mergeTwoLists_noDuplicates(list10, list11);
        System.out.print("No duplicates: ");
        printLinkedList(result8);
        
        // Expected: [1,2,3,4]
    }
}

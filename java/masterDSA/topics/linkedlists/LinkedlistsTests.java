package masterDSA.topics.linkedlists.LinkedlistsTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for LinkedLists topic problems
 */
public class LinkedListsTests {
    
    @Test
    public void testReverseLinkedList() {
        ReverseLinkedList.ListNode head = new ReverseLinkedList.ListNode(1);
        head.next = new ReverseLinkedList.ListNode(2);
        head.next.next = new ReverseLinkedList.ListNode(3);
        
        ReverseLinkedList.ListNode result = ReverseLinkedList.reverseList_iterative(head);
        assertEquals(3, result.val);
        assertEquals(2, result.next.val);
        assertEquals(1, result.next.next.val);
    }
    
    @Test
    public void testMergeTwoSortedLists() {
        MergeTwoSortedLists.ListNode list1 = new MergeTwoSortedLists.ListNode(1);
        list1.next = new MergeTwoSortedLists.ListNode(2);
        list1.next.next = new MergeTwoSortedLists.ListNode(4);
        
        MergeTwoSortedLists.ListNode list2 = new MergeTwoSortedLists.ListNode(1);
        list2.next = new MergeTwoSortedLists.ListNode(3);
        list2.next.next = new MergeTwoSortedLists.ListNode(4);
        
        MergeTwoSortedLists.ListNode result = MergeTwoSortedLists.mergeTwoLists_iterative(list1, list2);
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
        assertEquals(2, result.next.next.val);
    }
    
    @Test
    public void testDetectCycleInLinkedList() {
        DetectCycleInLinkedList.ListNode head = new DetectCycleInLinkedList.ListNode(1);
        DetectCycleInLinkedList.ListNode node2 = new DetectCycleInLinkedList.ListNode(2);
        DetectCycleInLinkedList.ListNode node3 = new DetectCycleInLinkedList.ListNode(3);
        
        head.next = node2;
        node2.next = node3;
        node3.next = node2; // Create cycle
        
        boolean result = DetectCycleInLinkedList.hasCycle_floyd(head);
        assertTrue(result);
    }
    
    @Test
    public void testAddTwoNumbers() {
        AddTwoNumbers.ListNode l1 = new AddTwoNumbers.ListNode(2);
        l1.next = new AddTwoNumbers.ListNode(4);
        l1.next.next = new AddTwoNumbers.ListNode(3);
        
        AddTwoNumbers.ListNode l2 = new AddTwoNumbers.ListNode(5);
        l2.next = new AddTwoNumbers.ListNode(6);
        l2.next.next = new AddTwoNumbers.ListNode(4);
        
        AddTwoNumbers.ListNode result = AddTwoNumbers.addTwoNumbers_iterative(l1, l2);
        assertEquals(7, result.val);
        assertEquals(0, result.next.val);
        assertEquals(8, result.next.next.val);
    }
}
package masterDSA.topics.patterns_misc.LinkedListPatterns;
import java.util.*;


/**
 * Linked List Patterns
 * 
 * Problem Statement:
 * Common patterns for solving linked list problems including reversal,
 * merging, intersection detection, and other operations.
 * 
 * LeetCode Link: https://leetcode.com/tag/linked-list/
 * Difficulty: Easy to Hard
 * Tags: Linked List, Two Pointers, Recursion
 * 
 * Approach Summary:
 * 1. Reversal: Iterative and recursive approaches
 * 2. Merging: Merge two sorted lists
 * 3. Intersection: Find common nodes
 * 4. Cycle Detection: Floyd's algorithm
 * 5. Partitioning: Split list based on condition
 * 
 * Pseudocode:
 * 1. Handle edge cases (null, single node)
 * 2. Use appropriate pattern (two pointers, recursion, etc.)
 * 3. Maintain proper node connections
 * 4. Return modified head
 * 
 * Time Complexity: O(n) to O(n²) depending on problem
 * Space Complexity: O(1) to O(n) depending on approach
 */
public class LinkedListPatterns {
    
    // ListNode definition
    public static class ListNode {
        public int val;
        public ListNode next;
        
        public ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
    /**
     * Reverse linked list iteratively
     * 
     * @param head Head of linked list
     * @return Reversed linked list head
     */
    public static ListNode reverseList_iterative(ListNode head) {
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
     * Reverse linked list recursively
     * 
     * @param head Head of linked list
     * @return Reversed linked list head
     */
    public static ListNode reverseList_recursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode reversed = reverseList_recursive(head.next);
        head.next.next = head;
        head.next = null;
        
        return reversed;
    }
    
    /**
     * Merge two sorted linked lists
     * 
     * @param list1 First sorted list
     * @param list2 Second sorted list
     * @return Merged sorted list
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
        
        // Append remaining nodes
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    /**
     * Find intersection of two linked lists
     * 
     * @param headA First list
     * @param headB Second list
     * @return Intersection node, null if no intersection
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode a = headA;
        ListNode b = headB;
        
        // When one list ends, switch to the other
        // This handles different lengths
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        
        return a; // Either intersection node or null
    }
    
    /**
     * Detect cycle in linked list
     * 
     * @param head Head of linked list
     * @return True if cycle exists
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Find the node where cycle begins
     * 
     * @param head Head of linked list
     * @return Cycle start node, null if no cycle
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break;
            }
        }
        
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Find cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
    
    /**
     * Remove nth node from end
     * 
     * @param head Head of linked list
     * @param n Position from end (1-indexed)
     * @return New head after removal
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        // Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both pointers until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // Remove nth node
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    /**
     * Partition list around value x
     * 
     * @param head Head of linked list
     * @param x Partition value
     * @return Partitioned list
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode beforeHead = new ListNode(0);
        ListNode afterHead = new ListNode(0);
        ListNode before = beforeHead;
        ListNode after = afterHead;
        
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        
        // Connect the two partitions
        after.next = null;
        before.next = afterHead.next;
        
        return beforeHead.next;
    }
    
    /**
     * Reverse nodes in k-group
     * 
     * @param head Head of linked list
     * @param k Group size
     * @return Modified list
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (head != null) {
            ListNode tail = head;
            int count = 0;
            
            // Check if we have k nodes
            while (tail != null && count < k) {
                tail = tail.next;
                count++;
            }
            
            if (count == k) {
                // Reverse k nodes
                ListNode nextGroup = tail;
                ListNode newHead = reverseGroup(head, k);
                prev.next = newHead;
                prev = head;
                head = nextGroup;
            } else {
                break;
            }
        }
        
        return dummy.next;
    }
    
    /**
     * Reverse a group of k nodes
     */
    private static ListNode reverseGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode current = head;
        
        for (int i = 0; i < k; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    /**
     * Swap nodes in pairs
     * 
     * @param head Head of linked list
     * @return Modified list
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            
            // Swap nodes
            prev.next = second;
            first.next = second.next;
            second.next = first;
            
            // Move pointers
            prev = first;
            head = first.next;
        }
        
        return dummy.next;
    }
    
    /**
     * Copy list with random pointers
     * 
     * @param head Head of original list
     * @return Head of copied list
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        
        // First pass: create copies
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }
        
        // Second pass: set next and random pointers
        current = head;
        while (current != null) {
            Node copy = map.get(current);
            copy.next = map.get(current.next);
            copy.random = map.get(current.random);
            current = current.next;
        }
        
        return map.get(head);
    }
    
    // Node class for random pointer list
    static class Node {
        int val;
        Node next;
        Node random;
        
        Node(int val) {
            this.val = val;
        }
    }
    
    /**
     * Sort linked list using merge sort
     * 
     * @param head Head of linked list
     * @return Sorted list
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Find middle
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Split list
        ListNode secondHalf = slow.next;
        slow.next = null;
        
        // Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(secondHalf);
        
        // Merge sorted halves
        return mergeTwoLists(left, right);
    }
    
    /**
     * Helper method to create linked list from array
     */
    private static ListNode createList(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(nums[0]);
        ListNode current = head;
        
        for (int i = 1; i < nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        
        return head;
    }
    
    /**
     * Helper method to print linked list
     */
    private static void printList(ListNode head) {
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        System.out.println(values);
    }
    
    /**
     * Demonstration method showing various linked list patterns
     */
    public static void runExample() {
        System.out.println("=== Linked List Patterns Examples ===");
        
        // Create test lists
        ListNode list1 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode list2 = createList(new int[]{1, 3, 5, 7, 9});
        ListNode list3 = createList(new int[]{2, 4, 6, 8, 10});
        
        // Reverse list
        ListNode reversed = reverseList_iterative(list1);
        System.out.print("Reversed list: ");
        printList(reversed);
        
        // Merge two sorted lists
        ListNode merged = mergeTwoLists(list2, list3);
        System.out.print("Merged sorted lists: ");
        printList(merged);
        
        // Remove nth from end
        ListNode list4 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode removed = removeNthFromEnd(list4, 2);
        System.out.print("After removing 2nd from end: ");
        printList(removed);
        
        // Partition list
        ListNode list5 = createList(new int[]{1, 4, 3, 2, 5, 2});
        ListNode partitioned = partition(list5, 3);
        System.out.print("Partitioned list (x=3): ");
        printList(partitioned);
        
        // Reverse k-group
        ListNode list6 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode reversedK = reverseKGroup(list6, 2);
        System.out.print("Reversed in groups of 2: ");
        printList(reversedK);
        
        // Swap pairs
        ListNode list7 = createList(new int[]{1, 2, 3, 4});
        ListNode swapped = swapPairs(list7);
        System.out.print("Swapped pairs: ");
        printList(swapped);
        
        // Sort list
        ListNode list8 = createList(new int[]{4, 2, 1, 3, 5});
        ListNode sorted = sortList(list8);
        System.out.print("Sorted list: ");
        printList(sorted);
        
        // Cycle detection
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Create cycle
        
        boolean hasCycle = hasCycle(head1);
        System.out.println("List has cycle: " + hasCycle);
        
        if (hasCycle) {
            ListNode cycleStart = detectCycle(head1);
            System.out.println("Cycle starts at node: " + cycleStart.val);
        }
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

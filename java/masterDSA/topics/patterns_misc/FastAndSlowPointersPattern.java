package masterDSA.topics.patterns_misc;
import java.util.*;


/**
 * Fast and Slow Pointers Pattern (Floyd's Cycle Detection)
 * 
 * Problem Statement:
 * The fast and slow pointers pattern uses two pointers moving at different speeds
 * to detect cycles, find middle elements, or solve other problems involving cycles.
 * 
 * LeetCode Link: https://leetcode.com/tag/two-pointers/
 * Difficulty: Medium
 * Tags: Linked List, Two Pointers, Cycle Detection
 * 
 * Approach Summary:
 * 1. Cycle Detection: Fast pointer moves 2 steps, slow moves 1 step
 * 2. Middle Element: Fast reaches end when slow is at middle
 * 3. Palindrome Check: Use slow pointer to find middle, then compare
 * 4. Cycle Length: After detecting cycle, count steps to find length
 * 
 * Pseudocode:
 * 1. Initialize slow = head, fast = head
 * 2. Move fast pointer 2 steps, slow pointer 1 step
 * 3. If fast meets slow, cycle detected
 * 4. If fast reaches end, no cycle
 * 
 * Time Complexity: O(n) - linear time
 * Space Complexity: O(1) - constant space
 */
public class FastAndSlowPointersPattern {
    
    // ListNode definition for linked list problems
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
     * Detect cycle in linked list
     * 
     * @param head Head of linked list
     * @return True if cycle exists, false otherwise
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
                return true; // Cycle detected
            }
        }
        
        return false; // No cycle
    }
    
    /**
     * Find middle node of linked list
     * 
     * @param head Head of linked list
     * @return Middle node
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
     * Find the node where cycle begins
     * 
     * @param head Head of linked list
     * @return Node where cycle begins, null if no cycle
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // First phase: detect if cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break; // Cycle detected
            }
        }
        
        // No cycle found
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Second phase: find cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow; // Cycle start node
    }
    
    /**
     * Check if linked list is palindrome
     * 
     * @param head Head of linked list
     * @return True if palindrome, false otherwise
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle node
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverseList(slow);
        ListNode firstHalf = head;
        
        // Compare first and second half
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    /**
     * Remove nth node from end of list
     * 
     * @param head Head of linked list
     * @param n Position from end (1-indexed)
     * @return New head after removal
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
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
     * Find duplicate number in array (Floyd's cycle detection)
     * 
     * @param nums Array where 1 <= nums[i] <= n
     * @return Duplicate number
     */
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        
        // Phase 1: Detect cycle
        int slow = nums[0];
        int fast = nums[0];
        
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Phase 2: Find cycle start
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    /**
     * Find the start of cycle in array
     * 
     * @param nums Array with cycle
     * @return Start index of cycle
     */
    public static int findCycleStart(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        
        int slow = 0;
        int fast = 0;
        
        // Phase 1: Detect cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Phase 2: Find cycle start
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    /**
     * Find the length of cycle
     * 
     * @param head Head of linked list
     * @return Length of cycle, 0 if no cycle
     */
    public static int cycleLength(ListNode head) {
        if (head == null || head.next == null) {
            return 0;
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
            return 0; // No cycle
        }
        
        // Count cycle length
        int length = 0;
        do {
            slow = slow.next;
            length++;
        } while (slow != fast);
        
        return length;
    }
    
    /**
     * Check if array has cycle
     * 
     * @param nums Array of integers
     * @return True if cycle exists, false otherwise
     */
    public static boolean hasCycleInArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        
        int slow = 0;
        int fast = 0;
        
        // Move pointers
        while (fast < nums.length && nums[fast] < nums.length) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        
        return false; // No cycle
    }
    
    /**
     * Find the middle element in array (conceptual)
     * 
     * @param nums Array of integers
     * @return Middle element
     */
    public static int findMiddleElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int slow = 0;
        int fast = 0;
        
        // Move fast pointer twice as fast
        while (fast < nums.length - 1) {
            slow++;
            fast += 2;
        }
        
        return nums[slow];
    }
    
    /**
     * Helper method to reverse linked list
     */
    private static ListNode reverseList(ListNode head) {
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
     * Demonstration method showing various fast and slow pointers patterns
     */
    public static void runExample() {
        System.out.println("=== Fast and Slow Pointers Pattern Examples ===");
        
        // Create linked list with cycle
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2; // Create cycle
        
        boolean hasCycle = hasCycle(head1);
        System.out.println("Linked list has cycle: " + hasCycle);
        
        // Find cycle start
        ListNode cycleStart = detectCycle(head1);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.val : "null"));
        
        // Find cycle length
        int cycleLen = cycleLength(head1);
        System.out.println("Cycle length: " + cycleLen);
        
        // Create linked list without cycle
        ListNode head2 = createList(new int[]{1, 2, 3, 4, 5});
        boolean hasCycle2 = hasCycle(head2);
        System.out.println("Second linked list has cycle: " + hasCycle2);
        
        // Find middle node
        ListNode middle = findMiddle(head2);
        System.out.println("Middle node value: " + middle.val);
        
        // Check palindrome
        ListNode head3 = createList(new int[]{1, 2, 3, 2, 1});
        boolean isPal = isPalindrome(head3);
        System.out.println("Linked list is palindrome: " + isPal);
        
        // Remove nth from end
        ListNode head4 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode newHead = removeNthFromEnd(head4, 2);
        System.out.print("After removing 2nd from end: ");
        printList(newHead);
        
        // Find duplicate in array
        int[] nums1 = {1, 3, 4, 2, 2};
        int duplicate = findDuplicate(nums1);
        System.out.println("Duplicate number: " + duplicate);
        
        // Find cycle start in array
        int[] nums2 = {1, 2, 3, 4, 5, 6, 3};
        int cycleStartIndex = findCycleStart(nums2);
        System.out.println("Cycle starts at index: " + cycleStartIndex);
        
        // Check cycle in array
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7};
        boolean hasCycleInArr = hasCycleInArray(nums3);
        System.out.println("Array has cycle: " + hasCycleInArr);
        
        // Find middle element
        int[] nums4 = {1, 2, 3, 4, 5, 6, 7};
        int middleElement = findMiddleElement(nums4);
        System.out.println("Middle element: " + middleElement);
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

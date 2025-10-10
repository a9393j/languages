package masterDSA.topics.linkedlists.AddTwoNumbers;

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
 * - Iterate through both lists simultaneously
 * - Add corresponding digits plus carry
 * - Create new node for each digit in result
 * - Handle different lengths and final carry
 * 
 * Pseudocode:
 * 1. Initialize dummy node and current pointer
 * 2. Initialize carry = 0
 * 3. While l1 != null OR l2 != null OR carry != 0:
 *    a. Sum = carry + l1.val + l2.val
 *    b. carry = sum / 10
 *    c. Create new node with sum % 10
 *    d. Move pointers forward
 * 4. Return dummy.next
 */
public class AddTwoNumbers {
    
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
     * Iterative approach - O(max(m,n)) time, O(max(m,n)) space
     * Add digits with carry, handling different lengths
     */
    public static ListNode addTwoNumbers_iterative(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        // Continue while either list has nodes or there's a carry
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            
            // Add value from l1 if it exists
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            // Add value from l2 if it exists
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            // Calculate carry and digit
            carry = sum / 10;
            int digit = sum % 10;
            
            // Create new node
            current.next = new ListNode(digit);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    /**
     * Recursive approach - O(max(m,n)) time, O(max(m,n)) space
     * Recursively add digits with carry
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
        
        // Add value from l1 if it exists
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        
        // Add value from l2 if it exists
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        
        // Calculate carry and digit
        carry = sum / 10;
        int digit = sum % 10;
        
        // Create new node and recursively process the rest
        ListNode node = new ListNode(digit);
        node.next = addTwoNumbersHelper(l1, l2, carry);
        
        return node;
    }
    
    /**
     * In-place approach - O(max(m,n)) time, O(1) space
     * Modify one of the lists to store the result
     */
    public static ListNode addTwoNumbers_inPlace(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        ListNode head = l1;
        ListNode prev = null;
        int carry = 0;
        
        // Add digits while both lists have nodes
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            l1.val = sum % 10;
            
            prev = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        // Handle remaining nodes in l1
        while (l1 != null && carry != 0) {
            int sum = l1.val + carry;
            carry = sum / 10;
            l1.val = sum % 10;
            
            prev = l1;
            l1 = l1.next;
        }
        
        // Handle remaining nodes in l2
        if (l2 != null) {
            prev.next = l2;
            while (l2 != null && carry != 0) {
                int sum = l2.val + carry;
                carry = sum / 10;
                l2.val = sum % 10;
                
                prev = l2;
                l2 = l2.next;
            }
        }
        
        // Handle final carry
        if (carry != 0) {
            prev.next = new ListNode(carry);
        }
        
        return head;
    }
    
    /**
     * Two pointers approach - O(max(m,n)) time, O(max(m,n)) space
     * Alternative iterative implementation
     */
    public static ListNode addTwoNumbers_twoPointers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        
        while (l1 != null || l2 != null || carry != 0) {
            int digit1 = (l1 != null) ? l1.val : 0;
            int digit2 = (l2 != null) ? l2.val : 0;
            
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            int digit = sum % 10;
            
            tail.next = new ListNode(digit);
            tail = tail.next;
            
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
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
     * Helper method to convert linked list to number
     */
    public static long linkedListToNumber(ListNode head) {
        long number = 0;
        long multiplier = 1;
        ListNode current = head;
        
        while (current != null) {
            number += current.val * multiplier;
            multiplier *= 10;
            current = current.next;
        }
        
        return number;
    }
    
    /**
     * Helper method to convert number to linked list
     */
    public static ListNode numberToLinkedList(long number) {
        if (number == 0) {
            return new ListNode(0);
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (number > 0) {
            int digit = (int) (number % 10);
            current.next = new ListNode(digit);
            current = current.next;
            number /= 10;
        }
        
        return dummy.next;
    }
    
    /**
     * Helper method to reverse linked list
     */
    public static ListNode reverseList(ListNode head) {
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
     * Helper method to add two numbers represented as linked lists (forward order)
     */
    public static ListNode addTwoNumbersForward(ListNode l1, ListNode l2) {
        // Reverse both lists
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        
        // Add the numbers
        ListNode result = addTwoNumbers_iterative(l1, l2);
        
        // Reverse the result
        return reverseList(result);
    }
    
    /**
     * Helper method to multiply two numbers represented as linked lists
     */
    public static ListNode multiplyTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = linkedListToNumber(l1);
        long num2 = linkedListToNumber(l2);
        long product = num1 * num2;
        
        return numberToLinkedList(product);
    }
    
    /**
     * Helper method to subtract two numbers represented as linked lists
     */
    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = linkedListToNumber(l1);
        long num2 = linkedListToNumber(l2);
        long difference = Math.abs(num1 - num2);
        
        return numberToLinkedList(difference);
    }
    
    public static void main(String[] args) {
        // Test case: l1 = [2,4,3], l2 = [5,6,4]
        int[] values1 = {2, 4, 3};
        int[] values2 = {5, 6, 4};
        
        ListNode l1 = createLinkedList(values1);
        ListNode l2 = createLinkedList(values2);
        
        System.out.println("Testing Add Two Numbers:");
        System.out.print("List 1: ");
        printLinkedList(l1);
        System.out.print("List 2: ");
        printLinkedList(l2);
        
        ListNode result1 = addTwoNumbers_iterative(l1, l2);
        System.out.print("Iterative: ");
        printLinkedList(result1);
        
        // Recreate lists for next test
        l1 = createLinkedList(values1);
        l2 = createLinkedList(values2);
        ListNode result2 = addTwoNumbers_recursive(l1, l2);
        System.out.print("Recursive: ");
        printLinkedList(result2);
        
        // Recreate lists for next test
        l1 = createLinkedList(values1);
        l2 = createLinkedList(values2);
        ListNode result3 = addTwoNumbers_inPlace(l1, l2);
        System.out.print("In-place: ");
        printLinkedList(result3);
        
        // Recreate lists for next test
        l1 = createLinkedList(values1);
        l2 = createLinkedList(values2);
        ListNode result4 = addTwoNumbers_twoPointers(l1, l2);
        System.out.print("Two pointers: ");
        printLinkedList(result4);
        
        // Expected: [7,0,8] (342 + 465 = 807)
        
        System.out.println();
        
        // Test case: l1 = [0], l2 = [0]
        int[] values3 = {0};
        int[] values4 = {0};
        
        ListNode l3 = createLinkedList(values3);
        ListNode l4 = createLinkedList(values4);
        
        System.out.print("List 1: ");
        printLinkedList(l3);
        System.out.print("List 2: ");
        printLinkedList(l4);
        
        ListNode result5 = addTwoNumbers_iterative(l3, l4);
        System.out.print("Result: ");
        printLinkedList(result5);
        
        // Expected: [0]
        
        System.out.println();
        
        // Test case: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        int[] values5 = {9, 9, 9, 9, 9, 9, 9};
        int[] values6 = {9, 9, 9, 9};
        
        ListNode l5 = createLinkedList(values5);
        ListNode l6 = createLinkedList(values6);
        
        System.out.print("List 1: ");
        printLinkedList(l5);
        System.out.print("List 2: ");
        printLinkedList(l6);
        
        ListNode result6 = addTwoNumbers_iterative(l5, l6);
        System.out.print("Result: ");
        printLinkedList(result6);
        
        // Expected: [8,9,9,9,0,0,0,1]
        
        System.out.println();
        
        // Test helper methods
        int[] values7 = {1, 2, 3};
        ListNode l7 = createLinkedList(values7);
        
        System.out.println("Testing helper methods:");
        System.out.print("List: ");
        printLinkedList(l7);
        
        long number = linkedListToNumber(l7);
        System.out.println("Number: " + number);
        
        ListNode l8 = numberToLinkedList(321);
        System.out.print("Number to list: ");
        printLinkedList(l8);
        
        // Expected: Number 321, Number to list [1,2,3]
        
        System.out.println();
        
        // Test add forward order
        int[] values9 = {3, 4, 2};
        int[] values10 = {4, 6, 5};
        
        ListNode l9 = createLinkedList(values9);
        ListNode l10 = createLinkedList(values10);
        
        System.out.print("Forward List 1: ");
        printLinkedList(l9);
        System.out.print("Forward List 2: ");
        printLinkedList(l10);
        
        ListNode result7 = addTwoNumbersForward(l9, l10);
        System.out.print("Forward addition: ");
        printLinkedList(result7);
        
        // Expected: [8,0,7] (342 + 465 = 807)
        
        System.out.println();
        
        // Test multiplication
        int[] values11 = {2, 4, 3};
        int[] values12 = {5, 6, 4};
        
        ListNode l11 = createLinkedList(values11);
        ListNode l12 = createLinkedList(values12);
        
        System.out.print("List 1: ");
        printLinkedList(l11);
        System.out.print("List 2: ");
        printLinkedList(l12);
        
        ListNode result8 = multiplyTwoNumbers(l11, l12);
        System.out.print("Multiplication: ");
        printLinkedList(result8);
        
        // Expected: [0,1,5,9,0,1] (342 * 465 = 159030)
    }
    
    /**
     * Helper method to convert linked list to array
     */
    public static int[] linkedListToArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}

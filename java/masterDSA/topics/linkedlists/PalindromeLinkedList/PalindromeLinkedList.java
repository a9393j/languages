package masterDSA.topics.linkedlists.PalindromeLinkedList;
import java.util.*;


/**
 * Palindrome Linked List
 * 
 * Given the head of a singly linked list, return true if it is a palindrome.
 * 
 * LeetCode: https://leetcode.com/problems/palindrome-linked-list/
 * Difficulty: Medium
 * Tags: reverse-half, stack
 * 
 * Approach:
 * - Find middle node using slow/fast pointers
 * - Reverse second half of the list
 * - Compare first half with reversed second half
 * - Alternative: Use stack to store first half, then compare
 * 
 * Pseudocode:
 * 1. Find middle node using slow/fast pointers
 * 2. Reverse second half of the list
 * 3. Compare first half with reversed second half
 * 4. Restore original list (optional)
 * 5. Return result
 */
public class PalindromeLinkedList {
    
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
     * Reverse half approach - O(n) time, O(1) space
     * Find middle, reverse second half, compare, then restore
     */
    public static boolean isPalindrome_reverseHalf(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle node
        ListNode middle = findMiddle(head);
        
        // Reverse second half
        ListNode secondHalf = reverseList(middle.next);
        
        // Compare first half with reversed second half
        ListNode firstHalf = head;
        boolean isPalindrome = true;
        
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        // Restore original list
        middle.next = reverseList(secondHalf);
        
        return isPalindrome;
    }
    
    /**
     * Stack approach - O(n) time, O(n) space
     * Use stack to store first half, then compare with second half
     */
    public static boolean isPalindrome_stack(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle node
        ListNode middle = findMiddle(head);
        
        // Push first half to stack
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        ListNode current = head;
        
        while (current != middle) {
            stack.push(current.val);
            current = current.next;
        }
        
        // Skip middle node if odd length
        if (current.next != null) {
            current = current.next;
        }
        
        // Compare second half with stack
        while (current != null) {
            if (stack.isEmpty() || stack.pop() != current.val) {
                return false;
            }
            current = current.next;
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Recursive approach - O(n) time, O(n) space
     * Use recursion to compare nodes from both ends
     */
    public static boolean isPalindrome_recursive(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        ListNode[] front = {head};
        return isPalindromeHelper(front, head);
    }
    
    private static boolean isPalindromeHelper(ListNode[] front, ListNode back) {
        if (back == null) {
            return true;
        }
        
        // Recursively check the rest
        boolean isPalindrome = isPalindromeHelper(front, back.next);
        
        // Compare current nodes
        if (!isPalindrome || front[0].val != back.val) {
            return false;
        }
        
        // Move front pointer forward
        front[0] = front[0].next;
        
        return true;
    }
    
    /**
     * Two pointers with array - O(n) time, O(n) space
     * Convert to array and use two pointers
     */
    public static boolean isPalindrome_array(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Convert to array
        java.util.List<Integer> values = new java.util.ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        
        // Use two pointers
        int left = 0;
        int right = values.size() - 1;
        
        while (left < right) {
            if (!values.get(left).equals(values.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Brute force approach - O(n²) time, O(1) space
     * For each node, check if it matches the corresponding node from the end
     */
    public static boolean isPalindrome_bruteForce(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        int length = getLength(head);
        ListNode current = head;
        
        for (int i = 0; i < length / 2; i++) {
            ListNode nodeFromEnd = getNodeFromEnd(head, i);
            if (current.val != nodeFromEnd.val) {
                return false;
            }
            current = current.next;
        }
        
        return true;
    }
    
    /**
     * Helper method to find middle node
     */
    private static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
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
     * Helper method to get node from end
     */
    private static ListNode getNodeFromEnd(ListNode head, int index) {
        int length = getLength(head);
        ListNode current = head;
        
        for (int i = 0; i < length - 1 - index; i++) {
            current = current.next;
        }
        
        return current;
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
     * Helper method to check if array is palindrome
     */
    public static boolean isArrayPalindrome(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Helper method to find longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLen = 1;
        
        for (int i = 0; i < s.length(); i++) {
            // Check odd-length palindromes
            int len1 = expandAroundCenter(s, i, i);
            
            // Check even-length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int currentMaxLen = Math.max(len1, len2);
            
            if (currentMaxLen > maxLen) {
                maxLen = currentMaxLen;
                start = i - (currentMaxLen - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    public static void main(String[] args) {
        // Test case: [1,2,2,1]
        int[] values = {1, 2, 2, 1};
        ListNode head = createLinkedList(values);
        
        System.out.println("Testing Palindrome Linked List:");
        System.out.print("List: ");
        printLinkedList(head);
        
        boolean result1 = isPalindrome_reverseHalf(head);
        System.out.println("Reverse half: " + result1);
        
        boolean result2 = isPalindrome_stack(head);
        System.out.println("Stack: " + result2);
        
        boolean result3 = isPalindrome_recursive(head);
        System.out.println("Recursive: " + result3);
        
        boolean result4 = isPalindrome_array(head);
        System.out.println("Array: " + result4);
        
        boolean result5 = isPalindrome_bruteForce(head);
        System.out.println("Brute force: " + result5);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: [1,2]
        int[] values2 = {1, 2};
        ListNode head2 = createLinkedList(values2);
        
        System.out.print("List: ");
        printLinkedList(head2);
        
        boolean result6 = isPalindrome_reverseHalf(head2);
        System.out.println("Reverse half: " + result6);
        
        // Expected: false
        
        System.out.println();
        
        // Test case: [1]
        int[] values3 = {1};
        ListNode head3 = createLinkedList(values3);
        
        System.out.print("List: ");
        printLinkedList(head3);
        
        boolean result7 = isPalindrome_reverseHalf(head3);
        System.out.println("Reverse half: " + result7);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: [1,2,3,2,1]
        int[] values4 = {1, 2, 3, 2, 1};
        ListNode head4 = createLinkedList(values4);
        
        System.out.print("List: ");
        printLinkedList(head4);
        
        boolean result8 = isPalindrome_reverseHalf(head4);
        System.out.println("Reverse half: " + result8);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: [1,2,3,4,5]
        int[] values5 = {1, 2, 3, 4, 5};
        ListNode head5 = createLinkedList(values5);
        
        System.out.print("List: ");
        printLinkedList(head5);
        
        boolean result9 = isPalindrome_reverseHalf(head5);
        System.out.println("Reverse half: " + result9);
        
        // Expected: false
        
        System.out.println();
        
        // Test helper methods
        int[] array = linkedListToArray(head4);
        System.out.println("Array: " + java.util.Arrays.toString(array));
        
        boolean isArrayPal = isArrayPalindrome(array);
        System.out.println("Is array palindrome: " + isArrayPal);
        
        // Expected: [1,2,3,2,1], true
        
        System.out.println();
        
        // Test longest palindrome
        String s = "babad";
        System.out.println("String: " + s);
        
        String longestPal = longestPalindrome(s);
        System.out.println("Longest palindrome: " + longestPal);
        
        // Expected: "bab" or "aba"
    }
}

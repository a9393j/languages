package masterDSA.topics.patterns_misc;
import java.util.*;

import masterDSA.topics.patterns_misc.SlidingWindowPattern.SlidingWindowPattern;
import masterDSA.topics.patterns_misc.TwoPointersPattern.TwoPointersPattern;
import masterDSA.topics.patterns_misc.FastAndSlowPointersPattern.FastAndSlowPointersPattern;
import masterDSA.topics.patterns_misc.LinkedListPatterns.LinkedListPatterns;
import masterDSA.topics.patterns_misc.TreeTraversalPatterns.TreeTraversalPatterns;
import masterDSA.topics.patterns_misc.MergeIntervalsPattern.MergeIntervalsPattern;
import masterDSA.topics.patterns_misc.CyclicSortPattern.CyclicSortPattern;
import masterDSA.topics.patterns_misc.TopKElementsPattern.TopKElementsPattern;
import masterDSA.topics.patterns_misc.SubsetsPattern.SubsetsPattern;
import masterDSA.topics.patterns_misc.ModifiedBinarySearchPattern.ModifiedBinarySearchPattern;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for patterns and miscellaneous problems
 * Tests representative methods from each pattern
 */
public class PatternsMiscTests {
    
    @Test
    public void testSlidingWindowMaxSum() {
        int[] nums = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int k = 4;
        int result = SlidingWindowPattern.maxSumSubarray_fixedWindow(nums, k);
        assertEquals(39, result);
    }
    
    @Test
    public void testSlidingWindowLongestSubstring() {
        String s = "eceba";
        int k = 2;
        int result = SlidingWindowPattern.longestSubstringAtMostKDistinct(s, k);
        assertEquals(3, result);
    }
    
    @Test
    public void testTwoPointersTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = TwoPointersPattern.twoSum_sorted(nums, target);
        assertArrayEquals(new int[]{0, 1}, result);
    }
    
    @Test
    public void testTwoPointersPalindrome() {
        String s = "A man a plan a canal Panama";
        boolean result = TwoPointersPattern.isPalindrome(s);
        assertTrue(result);
    }
    
    @Test
    public void testFastSlowPointersCycle() {
        // Create linked list with cycle
        FastAndSlowPointersPattern.ListNode head = new FastAndSlowPointersPattern.ListNode(1);
        FastAndSlowPointersPattern.ListNode node2 = new FastAndSlowPointersPattern.ListNode(2);
        FastAndSlowPointersPattern.ListNode node3 = new FastAndSlowPointersPattern.ListNode(3);
        FastAndSlowPointersPattern.ListNode node4 = new FastAndSlowPointersPattern.ListNode(4);
        
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Create cycle
        
        boolean result = FastAndSlowPointersPattern.hasCycle(head);
        assertTrue(result);
    }
    
    @Test
    public void testFastSlowPointersMiddle() {
        // Create linked list without cycle
        FastAndSlowPointersPattern.ListNode head = new FastAndSlowPointersPattern.ListNode(1);
        FastAndSlowPointersPattern.ListNode node2 = new FastAndSlowPointersPattern.ListNode(2);
        FastAndSlowPointersPattern.ListNode node3 = new FastAndSlowPointersPattern.ListNode(3);
        FastAndSlowPointersPattern.ListNode node4 = new FastAndSlowPointersPattern.ListNode(4);
        FastAndSlowPointersPattern.ListNode node5 = new FastAndSlowPointersPattern.ListNode(5);
        
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        
        FastAndSlowPointersPattern.ListNode result = FastAndSlowPointersPattern.findMiddle(head);
        assertEquals(3, result.val);
    }
    
    @Test
    public void testLinkedListReverse() {
        LinkedListPatterns.ListNode head = new LinkedListPatterns.ListNode(1);
        LinkedListPatterns.ListNode node2 = new LinkedListPatterns.ListNode(2);
        LinkedListPatterns.ListNode node3 = new LinkedListPatterns.ListNode(3);
        
        head.next = node2;
        node2.next = node3;
        
        LinkedListPatterns.ListNode result = LinkedListPatterns.reverseList_iterative(head);
        assertEquals(3, result.val);
        assertEquals(2, result.next.val);
        assertEquals(1, result.next.next.val);
    }
    
    @Test
    public void testLinkedListMerge() {
        LinkedListPatterns.ListNode list1 = new LinkedListPatterns.ListNode(1);
        list1.next = new LinkedListPatterns.ListNode(3);
        list1.next.next = new LinkedListPatterns.ListNode(5);
        
        LinkedListPatterns.ListNode list2 = new LinkedListPatterns.ListNode(2);
        list2.next = new LinkedListPatterns.ListNode(4);
        list2.next.next = new LinkedListPatterns.ListNode(6);
        
        LinkedListPatterns.ListNode result = LinkedListPatterns.mergeTwoLists(list1, list2);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }
    
    @Test
    public void testTreeTraversalPreorder() {
        TreeTraversalPatterns.TreeNode root = new TreeTraversalPatterns.TreeNode(1);
        root.left = new TreeTraversalPatterns.TreeNode(2);
        root.right = new TreeTraversalPatterns.TreeNode(3);
        root.left.left = new TreeTraversalPatterns.TreeNode(4);
        root.left.right = new TreeTraversalPatterns.TreeNode(5);
        
        List<Integer> result = TreeTraversalPatterns.preorderTraversal(root);
        assertEquals(Arrays.asList(1, 2, 4, 5, 3), result);
    }
    
    @Test
    public void testTreeTraversalInorder() {
        TreeTraversalPatterns.TreeNode root = new TreeTraversalPatterns.TreeNode(1);
        root.left = new TreeTraversalPatterns.TreeNode(2);
        root.right = new TreeTraversalPatterns.TreeNode(3);
        root.left.left = new TreeTraversalPatterns.TreeNode(4);
        root.left.right = new TreeTraversalPatterns.TreeNode(5);
        
        List<Integer> result = TreeTraversalPatterns.inorderTraversal(root);
        assertEquals(Arrays.asList(4, 2, 5, 1, 3), result);
    }
    
    @Test
    public void testMergeIntervals() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = MergeIntervalsPattern.merge(intervals);
        assertEquals(3, result.length);
        assertArrayEquals(new int[]{1, 6}, result[0]);
        assertArrayEquals(new int[]{8, 10}, result[1]);
        assertArrayEquals(new int[]{15, 18}, result[2]);
    }
    
    @Test
    public void testMergeIntervalsCanAttend() {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        boolean result = MergeIntervalsPattern.canAttendMeetings(intervals);
        assertFalse(result);
    }
    
    @Test
    public void testCyclicSortMissingNumber() {
        int[] nums = {3, 0, 1};
        int result = CyclicSortPattern.findMissingNumber(nums);
        assertEquals(2, result);
    }
    
    @Test
    public void testCyclicSortDuplicate() {
        int[] nums = {1, 3, 4, 2, 2};
        int result = CyclicSortPattern.findDuplicate(nums);
        assertEquals(2, result);
    }
    
    @Test
    public void testTopKElementsLargest() {
        int[] nums = {3, 1, 4, 1, 5, 9, 2, 6};
        int k = 3;
        List<Integer> result = TopKElementsPattern.findKLargest(nums, k);
        assertEquals(3, result.size());
        assertTrue(result.contains(9));
        assertTrue(result.contains(6));
        assertTrue(result.contains(5));
    }
    
    @Test
    public void testTopKElementsFrequent() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> result = TopKElementsPattern.topKFrequent(nums, k);
        assertEquals(2, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }
    
    @Test
    public void testSubsetsPattern() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = SubsetsPattern.subsets(nums);
        assertEquals(8, result.size()); // 2^3 = 8 subsets
    }
    
    @Test
    public void testSubsetsPatternCombinations() {
        int n = 4;
        int k = 2;
        List<List<Integer>> result = SubsetsPattern.combine(n, k);
        assertEquals(6, result.size()); // C(4,2) = 6
    }
    
    @Test
    public void testModifiedBinarySearchFirst() {
        int[] nums = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int result = ModifiedBinarySearchPattern.findFirst(nums, target);
        assertEquals(1, result);
    }
    
    @Test
    public void testModifiedBinarySearchLast() {
        int[] nums = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int result = ModifiedBinarySearchPattern.findLast(nums, target);
        assertEquals(3, result);
    }
    
    @Test
    public void testModifiedBinarySearchRotated() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = ModifiedBinarySearchPattern.searchRotated(nums, target);
        assertEquals(4, result);
    }
    
    @Test
    public void testModifiedBinarySearchMin() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int result = ModifiedBinarySearchPattern.findMin(nums);
        assertEquals(0, result);
    }
    
    @Test
    public void testModifiedBinarySearchSqrt() {
        int x = 8;
        int result = ModifiedBinarySearchPattern.sqrt(x);
        assertEquals(2, result);
    }
}

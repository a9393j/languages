# Master DSA - Data Structures and Algorithms

A comprehensive collection of data structure and algorithm problems with multiple solution approaches.

## Topics

### Arrays
- [Two Sum](topics/arrays/TwoSum/TwoSum.java) - Hashmap, two-pointers approach
- [Three Number Sum (3Sum)](topics/arrays/ThreeNumberSum3Sum/ThreeNumberSum3Sum.java) - Sorting, two-pointers
- [Four Sum](topics/arrays/FourSum/FourSum.java) - Sorting, k-sum
- [Non-Constructible Change](topics/arrays/NonConstructibleChange/NonConstructibleChange.java) - Greedy, sorting
- [Smallest Difference](topics/arrays/SmallestDifference/SmallestDifference.java) - Two-pointers, sorting
- [Product of Array Except Self](topics/arrays/ProductOfArrayExceptSelf/ProductOfArrayExceptSelf.java) - Prefix-suffix, no-division
- [First Duplicate Value](topics/arrays/FirstDuplicateValue/FirstDuplicateValue.java) - Hashing
- [Min Rewards](topics/arrays/MinRewards/MinRewards.java) - Greedy
- [Longest Peak / Longest Mountain](topics/arrays/LongestPeakLongestMountain/LongestPeakLongestMountain.java) - Scan, peak-detection
- [Merge Intervals](topics/arrays/MergeIntervals/MergeIntervals.java) - Intervals, sorting
- [Missing Number](topics/arrays/MissingNumber/MissingNumber.java) - XOR, math
- [Maximum Subarray (Kadane)](topics/arrays/MaximumSubarrayKadane/MaximumSubarrayKadane.java) - Greedy, dp
- [Smallest Difference Between Two Arrays](topics/arrays/SmallestDifferenceBetweenTwoArrays/SmallestDifferenceBetweenTwoArrays.java) - Two-arrays, two-pointers
- [Tournament Winner](topics/arrays/TournamentWinner/TournamentWinner.java) - Hashmap, score-tracking

### Strings
- [Longest Substring Without Repeating Characters](topics/strings/LongestSubstringWithoutRepeatingCharacters/LongestSubstringWithoutRepeatingCharacters.java) - Sliding-window, hashmap
- [Longest Palindromic Substring](topics/strings/LongestPalindromicSubstring/LongestPalindromicSubstring.java) - Expand-center, dp
- [Valid Anagram](topics/strings/ValidAnagram/ValidAnagram.java) - Hashing
- [Valid Palindrome](topics/strings/ValidPalindrome/ValidPalindrome.java) - Two-pointers, string
- [Minimum Window Substring](topics/strings/MinimumWindowSubstring/MinimumWindowSubstring.java) - Sliding-window, two-pointer
- [Word Break](topics/strings/WordBreak/WordBreak.java) - DP, trie

### Linked Lists
- [Reverse Linked List](topics/linkedlists/ReverseLinkedList/ReverseLinkedList.java) - Iterative, two-pointers
- [Merge Two Sorted Lists](topics/linkedlists/MergeTwoSortedLists/MergeTwoSortedLists.java) - Pointers
- [Detect Cycle in Linked List](topics/linkedlists/DetectCycleInLinkedList/DetectCycleInLinkedList.java) - Floyd, two-pointers
- [Interweaving / Merge Alternate Nodes](topics/linkedlists/InterweavingMergeAlternateNodes/InterweavingMergeAlternateNodes.java) - Pointer-manipulation
- [Palindrome Linked List](topics/linkedlists/PalindromeLinkedList/PalindromeLinkedList.java) - Reverse-half, stack
- [Remove Nth Node From End](topics/linkedlists/RemoveNthNodeFromEnd/RemoveNthNodeFromEnd.java) - Two-pointers
- [Add Two Numbers](topics/linkedlists/AddTwoNumbers/AddTwoNumbers.java) - Linked-list, math

### Trees
- [Binary Tree Inorder/Preorder/Postorder Traversal](topics/trees/BinaryTreeInorderPreorderPostorderTraversal/BinaryTreeInorderPreorderPostorderTraversal.java) - DFS, recursion, stack
- [Binary Tree Level Order Traversal](topics/trees/BinaryTreeLevelOrderTraversal/BinaryTreeLevelOrderTraversal.java) - BFS, queue
- [Binary Tree Maximum Path Sum](topics/trees/BinaryTreeMaximumPathSum/BinaryTreeMaximumPathSum.java) - DFS, tree-dp
- [Lowest Common Ancestor (Binary Tree)](topics/trees/LowestCommonAncestorBinaryTree/LowestCommonAncestorBinaryTree.java) - DFS, recursion
- [Lowest Common Ancestor (BST)](topics/trees/LowestCommonAncestorBST/LowestCommonAncestorBST.java) - BST, property
- [Validate Binary Search Tree](topics/trees/ValidateBinarySearchTree/ValidateBinarySearchTree.java) - DFS, range-check
- [Serialize and Deserialize Binary Tree](topics/trees/SerializeAndDeserializeBinaryTree/SerializeAndDeserializeBinaryTree.java) - BFS, DFS, string
- [Invert Binary Tree](topics/trees/InvertBinaryTree/InvertBinaryTree.java) - DFS, recursion

### Heaps
- [Kth Largest Element in an Array](topics/heaps/KthLargestElementInAnArray/KthLargestElementInAnArray.java) - Heap, quickselect
- [Top K Frequent Elements](topics/heaps/TopKFrequentElements/TopKFrequentElements.java) - Heap, bucket-sort
- [Median of Data Stream](topics/heaps/MedianOfDataStream/MedianOfDataStream.java) - Two-heaps, streaming

### Stacks & Queues
- [Valid Parentheses](topics/stacks_queues/ValidParentheses/ValidParentheses.java) - Stack
- [Daily Temperatures](topics/stacks_queues/DailyTemperatures/DailyTemperatures.java) - Monotonic-stack
- [Sliding Window Maximum](topics/stacks_queues/SlidingWindowMaximum/SlidingWindowMaximum.java) - Deque, monotonic-queue
- [Min Stack](topics/stacks_queues/MinStack/MinStack.java) - Stack, auxiliary-stack

### Graphs
- [Number of Islands](topics/graphs/NumberOfIslands/NumberOfIslands.java) - DFS, BFS, grid
- [Course Schedule](topics/graphs/CourseSchedule/CourseSchedule.java) - Topological-sort, cycle-detection
- [Course Schedule II](topics/graphs/CourseScheduleII/CourseScheduleII.java) - Topological-sort
- [Clone Graph](topics/graphs/CloneGraph/CloneGraph.java) - DFS, BFS, hashmap
- [Redundant Connection](topics/graphs/RedundantConnection/RedundantConnection.java) - Union-find, graph
- [Network Delay Time (Dijkstra)](topics/graphs/NetworkDelayTimeDijkstra/NetworkDelayTimeDijkstra.java) - Dijkstra, shortest-path

### Greedy
- [Jump Game](topics/greedy/JumpGame/JumpGame.java) - Greedy
- [Activity Selection / Meeting Rooms](topics/greedy/ActivitySelectionMeetingRooms/ActivitySelectionMeetingRooms.java) - Intervals, greedy

### Dynamic Programming
- [Coin Change](topics/dynamic_programming/CoinChange/CoinChange.java) - DP, unbounded-knapsack
- [Longest Increasing Subsequence](topics/dynamic_programming/LongestIncreasingSubsequence/LongestIncreasingSubsequence.java) - DP, binary-search
- [Edit Distance](topics/dynamic_programming/EditDistance/EditDistance.java) - DP, string-dp
- [House Robber](topics/dynamic_programming/HouseRobber/HouseRobber.java) - DP
- [Partition Equal Subset Sum](topics/dynamic_programming/PartitionEqualSubsetSum/PartitionEqualSubsetSum.java) - DP, subset-sum

### Recursion & Backtracking
- [Permutations](topics/recursion_backtracking/Permutations/Permutations.java) - Backtracking
- [Subsets](topics/recursion_backtracking/Subsets/Subsets.java) - Backtracking
- [N-Queens](topics/recursion_backtracking/NQueens/NQueens.java) - Backtracking
- [Sudoku Solver](topics/recursion_backtracking/SudokuSolver/SudokuSolver.java) - Backtracking, DFS

### Tries & Hashing
- [Implement Trie (Prefix Tree)](topics/tries_hashing/ImplementTriePrefixTree/ImplementTriePrefixTree.java) - Trie
- [Word Search II](topics/tries_hashing/WordSearchII/WordSearchII.java) - Trie, backtracking
- [LRU Cache](topics/tries_hashing/LRUCache/LRUCache.java) - Hashmap, linkedlist

### Sorting & Searching
- [Search in Rotated Sorted Array](topics/sorting_searching/SearchInRotatedSortedArray/SearchInRotatedSortedArray.java) - Binary-search, rotated-array
- [Kth Smallest Element in a Sorted Matrix](topics/sorting_searching/KthSmallestElementInASortedMatrix/KthSmallestElementInASortedMatrix.java) - Heap, binary-search
- [Quick Sort Template](topics/sorting_searching/QuickSortTemplate/QuickSortTemplate.java) - Sorting, divide-and-conquer

### Patterns & Misc
- [Largest Rectangle in Histogram](topics/patterns_misc/LargestRectangleInHistogram/LargestRectangleInHistogram.java) - Monotonic-stack
- [Union-Find Template](topics/patterns_misc/UnionFindTemplate/UnionFindTemplate.java) - Union-find
- [Minimum Window Substring (Sliding Window Template)](topics/patterns_misc/MinimumWindowSubstringSlidingWindowTemplate/MinimumWindowSubstringSlidingWindowTemplate.java) - Sliding-window

### System Design
- [System Design Quick Notes](topics/system_design/SystemDesignQuickNotes/SystemDesignQuickNotes.java) - System-design, notes

## Compilation and Testing

### Compile all Java files:
```bash
find java/masterDSA -name "*.java" -exec javac {} \;
```

### Run tests with JUnit:
```bash
# Compile test files
javac -cp ".:junit-platform-console-standalone.jar" java/masterDSA/topics/*/Tests.java

# Run tests
java -cp ".:junit-platform-console-standalone.jar" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

### Run individual problem examples:
```bash
# Example: Run Two Sum
cd java/masterDSA/topics/arrays/TwoSum
javac TwoSum.java
java TwoSum
```

## File Structure

Each problem is organized in its own directory with:
- Main implementation file: `ProblemName.java`
- Comprehensive Javadoc documentation
- Multiple solution approaches
- Rich inline comments
- Example usage in `main` method
- Helper methods for testing and visualization

## Features

- **Multiple Approaches**: Each problem includes 3-5 different solution approaches
- **Rich Documentation**: Comprehensive Javadoc with problem description, approach, and pseudocode
- **Inline Comments**: Detailed explanations for each logical block
- **Time & Space Complexity**: Annotated for each method
- **Edge Case Handling**: Explicit handling of edge cases
- **Test Cases**: Example usage and test cases in main methods
- **Helper Methods**: Utility methods for tree visualization, array conversion, etc.

## Usage

1. Navigate to the specific problem directory
2. Compile the Java file: `javac ProblemName.java`
3. Run the program: `java ProblemName`
4. Review the output and test cases
5. Study the different approaches and their trade-offs

## Contributing

When adding new problems:
1. Follow the established naming convention
2. Include comprehensive Javadoc
3. Implement multiple approaches
4. Add rich inline comments
5. Include example usage in main method
6. Handle edge cases appropriately

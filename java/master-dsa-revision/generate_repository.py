#!/usr/bin/env python3
"""
DSA Revision Repository Generator
Generates Java files for DSA problems with proper structure and documentation.
"""

import os
import json
import re
from typing import Dict, List

def to_pascal_case(title: str) -> str:
    """Convert title to PascalCase for class names."""
    # Remove punctuation and extra spaces
    cleaned = re.sub(r'[^\w\s]', '', title)
    # Split by spaces and capitalize each word
    words = cleaned.split()
    return ''.join(word.capitalize() for word in words)

def create_problem_directory(topic: str, problem_name: str) -> str:
    """Create directory structure for a problem."""
    pascal_name = to_pascal_case(problem_name)
    problem_dir = f"topics/{topic}/{pascal_name}"
    os.makedirs(problem_dir, exist_ok=True)
    return problem_dir

def generate_java_file(topic: str, problem: Dict) -> None:
    """Generate a Java file for a problem."""
    pascal_name = to_pascal_case(problem['title'])
    problem_dir = create_problem_directory(topic, problem['title'])
    file_path = f"{problem_dir}/{pascal_name}.java"
    
    # Generate Javadoc and implementation based on problem type
    content = generate_java_content(problem, pascal_name, topic)
    
    with open(file_path, 'w') as f:
        f.write(content)
    
    print(f"Created: {file_path}")

def generate_java_content(problem: Dict, class_name: str, topic: str = None) -> str:
    """Generate Java file content based on problem type."""
    title = problem['title']
    difficulty = problem['difficulty']
    leetcode = problem['leetcode']
    tags = ', '.join(problem['tags'])
    
    # Generate Javadoc
    javadoc = f"""/**
 * {title}
 * 
 * Problem Statement: TODO - Add problem statement here
 * 
 * LeetCode: {leetcode if leetcode else 'N/A'}
 * Difficulty: {difficulty}
 * Tags: {tags}
 * 
 * Approaches:
 * - TODO: Add approach descriptions
 * 
 * Pseudocode:
 * 1. TODO: Add pseudocode steps
 * 2. TODO: Add more steps as needed
 */
"""
    
    # Generate class content based on problem type
    if 'sum' in title.lower():
        return generate_sum_problem(class_name, javadoc, problem)
    elif 'array' in title.lower() or topic == 'arrays':
        return generate_array_problem(class_name, javadoc, problem)
    elif 'string' in title.lower() or topic == 'strings':
        return generate_string_problem(class_name, javadoc, problem)
    elif 'linked' in title.lower() or topic == 'linkedlists':
        return generate_linkedlist_problem(class_name, javadoc, problem)
    elif 'tree' in title.lower() or topic == 'trees':
        return generate_tree_problem(class_name, javadoc, problem)
    elif 'heap' in title.lower() or topic == 'heaps':
        return generate_heap_problem(class_name, javadoc, problem)
    elif 'stack' in title.lower() or 'queue' in title.lower() or topic == 'stacks_queues':
        return generate_stack_queue_problem(class_name, javadoc, problem)
    elif 'graph' in title.lower() or topic == 'graphs':
        return generate_graph_problem(class_name, javadoc, problem)
    elif 'dp' in tags or 'dynamic' in title.lower() or topic == 'dynamic_programming':
        return generate_dp_problem(class_name, javadoc, problem)
    else:
        return generate_generic_problem(class_name, javadoc, problem)

def generate_sum_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for sum-related problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * Hash Map Approach - O(n) time, O(n) space
     * Use hash map to store complements for each number
     */
    public static int[] twoSum_hashmap(int[] nums, int target) {{
        // Edge case: empty array
        if (nums == null || nums.length < 2) {{
            return new int[]{{}};
        }}
        
        // Map to store number -> index
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {{
            int complement = target - nums[i];
            
            // If complement exists, we found our pair
            if (map.containsKey(complement)) {{
                return new int[]{{map.get(complement), i}};
            }}
            
            // Store current number and its index
            map.put(nums[i], i);
        }}
        
        // No solution found
        return new int[]{{}};
    }}
    
    /**
     * Brute Force Approach - O(n²) time, O(1) space
     * Check all possible pairs
     */
    public static int[] twoSum_bruteforce(int[] nums, int target) {{
        // Edge case: empty array
        if (nums == null || nums.length < 2) {{
            return new int[]{{}};
        }}
        
        // Check all pairs
        for (int i = 0; i < nums.length; i++) {{
            for (int j = i + 1; j < nums.length; j++) {{
                if (nums[i] + nums[j] == target) {{
                    return new int[]{{i, j}};
                }}
            }}
        }}
        
        return new int[]{{}};
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        int[] nums = {{2, 7, 11, 15}};
        int target = 9;
        
        int[] result = twoSum_hashmap(nums, target);
        System.out.println("Result: " + java.util.Arrays.toString(result));
        // Expected: [0, 1] (indices of 2 and 7)
    }}
}}
"""

def generate_array_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for array problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * Main solution approach - O(n) time, O(1) space
     * TODO: Implement specific algorithm
     */
    public static int solve(int[] nums) {{
        // Edge case: empty array
        if (nums == null || nums.length == 0) {{
            return 0;
        }}
        
        // TODO: Implement algorithm
        // 1. Initialize variables
        // 2. Process array elements
        // 3. Return result
        
        return 0; // Placeholder
    }}
    
    /**
     * Alternative approach - O(n²) time, O(1) space
     * TODO: Implement alternative solution
     */
    public static int solve_alternative(int[] nums) {{
        // TODO: Implement alternative approach
        return 0; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        int[] nums = {{1, 2, 3, 4, 5}};
        int result = solve(nums);
        System.out.println("Result: " + result);
    }}
}}
"""

def generate_string_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for string problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * Sliding Window Approach - O(n) time, O(min(m,n)) space
     * where m is charset size
     */
    public static int solve(String s) {{
        // Edge case: empty string
        if (s == null || s.length() == 0) {{
            return 0;
        }}
        
        // TODO: Implement sliding window algorithm
        // 1. Initialize window boundaries
        // 2. Expand right boundary
        // 3. Contract left boundary when needed
        // 4. Track maximum window size
        
        return 0; // Placeholder
    }}
    
    /**
     * Brute Force Approach - O(n²) time, O(1) space
     * Check all possible substrings
     */
    public static int solve_bruteforce(String s) {{
        // TODO: Implement brute force approach
        return 0; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        String s = "abcabcbb";
        int result = solve(s);
        System.out.println("Result: " + result);
    }}
}}
"""

def generate_linkedlist_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for linked list problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    // Definition for singly-linked list
    public static class ListNode {{
        int val;
        ListNode next;
        ListNode() {{}}
        ListNode(int val) {{ this.val = val; }}
        ListNode(int val, ListNode next) {{ this.val = val; this.next = next; }}
    }}
    
    /**
     * Iterative Approach - O(n) time, O(1) space
     * TODO: Implement specific algorithm
     */
    public static ListNode solve(ListNode head) {{
        // Edge case: empty list
        if (head == null || head.next == null) {{
            return head;
        }}
        
        // TODO: Implement algorithm
        // 1. Initialize pointers
        // 2. Process nodes iteratively
        // 3. Return result
        
        return head; // Placeholder
    }}
    
    /**
     * Recursive Approach - O(n) time, O(n) space
     * TODO: Implement recursive solution
     */
    public static ListNode solve_recursive(ListNode head) {{
        // TODO: Implement recursive approach
        return head; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        // Create test linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        ListNode result = solve(head);
        System.out.println("Result: " + result.val);
    }}
}}
"""

def generate_tree_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for tree problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    // Definition for a binary tree node
    public static class TreeNode {{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {{}}
        TreeNode(int val) {{ this.val = val; }}
        TreeNode(int val, TreeNode left, TreeNode right) {{
            this.val = val;
            this.left = left;
            this.right = right;
        }}
    }}
    
    /**
     * DFS Approach - O(n) time, O(h) space where h is height
     * TODO: Implement specific algorithm
     */
    public static int solve(TreeNode root) {{
        // Edge case: empty tree
        if (root == null) {{
            return 0;
        }}
        
        // TODO: Implement DFS algorithm
        // 1. Handle base case
        // 2. Recursively process left and right subtrees
        // 3. Combine results
        
        return 0; // Placeholder
    }}
    
    /**
     * BFS Approach - O(n) time, O(w) space where w is max width
     * TODO: Implement BFS solution
     */
    public static int solve_bfs(TreeNode root) {{
        // TODO: Implement BFS approach
        return 0; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        // Create test tree: [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        int result = solve(root);
        System.out.println("Result: " + result);
    }}
}}
"""

def generate_heap_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for heap problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * Min Heap Approach - O(n log k) time, O(k) space
     * TODO: Implement heap-based solution
     */
    public static int solve(int[] nums, int k) {{
        // Edge case: invalid input
        if (nums == null || nums.length == 0 || k <= 0) {{
            return 0;
        }}
        
        // TODO: Implement heap algorithm
        // 1. Create min heap of size k
        // 2. Add elements to heap
        // 3. Return kth largest element
        
        return 0; // Placeholder
    }}
    
    /**
     * Quick Select Approach - O(n) average time, O(1) space
     * TODO: Implement quick select solution
     */
    public static int solve_quickselect(int[] nums, int k) {{
        // TODO: Implement quick select algorithm
        return 0; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        int[] nums = {{3, 2, 1, 5, 6, 4}};
        int k = 2;
        
        int result = solve(nums, k);
        System.out.println("Result: " + result);
    }}
}}
"""

def generate_stack_queue_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for stack/queue problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * Stack Approach - O(n) time, O(n) space
     * TODO: Implement stack-based solution
     */
    public static boolean solve(String s) {{
        // Edge case: empty string
        if (s == null || s.length() == 0) {{
            return true;
        }}
        
        // TODO: Implement stack algorithm
        // 1. Create stack
        // 2. Process characters
        // 3. Check stack state
        
        return false; // Placeholder
    }}
    
    /**
     * Alternative Approach - O(n) time, O(1) space
     * TODO: Implement space-optimized solution
     */
    public static boolean solve_optimized(String s) {{
        // TODO: Implement optimized approach
        return false; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        String s = "()[]{{}}";
        boolean result = solve(s);
        System.out.println("Result: " + result);
    }}
}}
"""

def generate_graph_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for graph problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * DFS Approach - O(V + E) time, O(V) space
     * TODO: Implement DFS-based solution
     */
    public static int solve(char[][] grid) {{
        // Edge case: empty grid
        if (grid == null || grid.length == 0 || grid[0].length == 0) {{
            return 0;
        }}
        
        // TODO: Implement DFS algorithm
        // 1. Iterate through grid
        // 2. Start DFS from unvisited cells
        // 3. Mark visited cells
        // 4. Count components
        
        return 0; // Placeholder
    }}
    
    /**
     * BFS Approach - O(V + E) time, O(V) space
     * TODO: Implement BFS solution
     */
    public static int solve_bfs(char[][] grid) {{
        // TODO: Implement BFS approach
        return 0; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        char[][] grid = {{
            {{'1','1','1','1','0'}},
            {{'1','1','0','1','0'}},
            {{'1','1','0','0','0'}},
            {{'0','0','0','0','0'}}
        }};
        
        int result = solve(grid);
        System.out.println("Result: " + result);
    }}
}}
"""

def generate_dp_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for dynamic programming problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * Bottom-up DP Approach - O(n*m) time, O(n) space
     * TODO: Implement bottom-up DP solution
     */
    public static int solve_bottomup(int[] coins, int amount) {{
        // Edge case: invalid input
        if (coins == null || coins.length == 0 || amount < 0) {{
            return -1;
        }}
        
        // TODO: Implement bottom-up DP
        // 1. Create DP array
        // 2. Initialize base cases
        // 3. Fill DP table
        // 4. Return result
        
        return 0; // Placeholder
    }}
    
    /**
     * Top-down DP Approach - O(n*m) time, O(n) space
     * TODO: Implement top-down DP solution
     */
    public static int solve_topdown(int[] coins, int amount) {{
        // TODO: Implement top-down DP with memoization
        return 0; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        int[] coins = {{1, 3, 4}};
        int amount = 6;
        
        int result = solve_bottomup(coins, amount);
        System.out.println("Result: " + result);
    }}
}}
"""

def generate_generic_problem(class_name: str, javadoc: str, problem: Dict) -> str:
    """Generate content for generic problems."""
    return f"""{javadoc}
public class {class_name} {{
    
    /**
     * Main solution approach - TODO: Add complexity
     * TODO: Implement specific algorithm
     */
    public static int solve(int[] nums) {{
        // Edge case: empty input
        if (nums == null || nums.length == 0) {{
            return 0;
        }}
        
        // TODO: Implement algorithm
        // 1. Initialize variables
        // 2. Process input
        // 3. Return result
        
        return 0; // Placeholder
    }}
    
    /**
     * Alternative approach - TODO: Add complexity
     * TODO: Implement alternative solution
     */
    public static int solve_alternative(int[] nums) {{
        // TODO: Implement alternative approach
        return 0; // Placeholder
    }}
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {{
        int[] nums = {{1, 2, 3, 4, 5}};
        int result = solve(nums);
        System.out.println("Result: " + result);
    }}
}}
"""

def create_test_file(topic: str, problems: List[Dict]) -> None:
    """Create a JUnit test file for a topic."""
    test_file = f"topics/{topic}/{topic.capitalize()}Tests.java"
    
    # Select 3-5 representative problems for testing
    test_problems = problems[:min(5, len(problems))]
    
    content = f"""import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for {topic} problems
 */
public class {topic.capitalize()}Tests {{
"""
    
    for problem in test_problems:
        class_name = to_pascal_case(problem['title'])
        content += f"""
    @Test
    public void test{class_name}() {{
        // TODO: Add test cases for {problem['title']}
        // Example:
        // int[] nums = {{1, 2, 3}};
        // int result = {class_name}.solve(nums);
        // assertEquals(expected, result);
        
        assertTrue(true); // Placeholder test
    }}
"""
    
    content += "}\n"
    
    with open(test_file, 'w') as f:
        f.write(content)
    
    print(f"Created test: {test_file}")

def main():
    """Main function to generate the DSA revision repository."""
    
    # Problem data from the JSON
    problems_data = {
        "arrays": [
            {"title":"Two Sum","difficulty":"Easy","leetcode":"https://leetcode.com/problems/two-sum/","tags":["hashmap","two-pointers"]},
            {"title":"Three Number Sum (3Sum)","difficulty":"Medium","leetcode":"https://leetcode.com/problems/3sum/","tags":["sorting","two-pointers"]},
            {"title":"Four Sum","difficulty":"Medium","leetcode":"https://leetcode.com/problems/4sum/","tags":["sorting","k-sum"]},
            {"title":"Non-Constructible Change","difficulty":"Easy","leetcode":"https://www.interviewcake.com/question/java/non-constructible-change","tags":["greedy","sorting"]},
            {"title":"Smallest Difference","difficulty":"Medium","leetcode":"https://leetcode.com/problems/minimum-absolute-difference/","tags":["two-pointers","sorting"]},
            {"title":"Product of Array Except Self","difficulty":"Medium","leetcode":"https://leetcode.com/problems/product-of-array-except-self/","tags":["prefix-suffix","no-division"]},
            {"title":"First Duplicate Value","difficulty":"Easy","leetcode":"https://leetcode.com/problems/contains-duplicate/","tags":["hashing"]},
            {"title":"Min Rewards","difficulty":"Medium","leetcode":"","tags":["greedy"]},
            {"title":"Longest Peak / Longest Mountain","difficulty":"Medium","leetcode":"https://leetcode.com/problems/longest-mountain-in-array/","tags":["scan","peak-detection"]},
            {"title":"Merge Intervals","difficulty":"Medium","leetcode":"https://leetcode.com/problems/merge-intervals/","tags":["intervals","sorting"]},
            {"title":"Missing Number","difficulty":"Easy","leetcode":"https://leetcode.com/problems/missing-number/","tags":["xor","math"]},
            {"title":"Maximum Subarray (Kadane)","difficulty":"Easy","leetcode":"https://leetcode.com/problems/maximum-subarray/","tags":["greedy","dp"]},
            {"title":"Smallest Difference Between Two Arrays","difficulty":"Medium","leetcode":"https://leetcode.com/problems/minimum-absolute-difference/","tags":["two-arrays","two-pointers"]},
            {"title":"Tournament Winner","difficulty":"Easy","leetcode":"","tags":["hashmap","score-tracking"]}
        ],
        "strings": [
            {"title":"Longest Substring Without Repeating Characters","difficulty":"Medium","leetcode":"https://leetcode.com/problems/longest-substring-without-repeating-characters/","tags":["sliding-window","hashmap"]},
            {"title":"Longest Palindromic Substring","difficulty":"Medium","leetcode":"https://leetcode.com/problems/longest-palindromic-substring/","tags":["expand-center","dp"]},
            {"title":"Valid Anagram","difficulty":"Easy","leetcode":"https://leetcode.com/problems/valid-anagram/","tags":["hashing"]},
            {"title":"Valid Palindrome","difficulty":"Easy","leetcode":"https://leetcode.com/problems/valid-palindrome/","tags":["two-pointers","string"]},
            {"title":"Minimum Window Substring","difficulty":"Hard","leetcode":"https://leetcode.com/problems/minimum-window-substring/","tags":["sliding-window","two-pointer"]},
            {"title":"Word Break","difficulty":"Medium","leetcode":"https://leetcode.com/problems/word-break/","tags":["dp","trie"]}
        ],
        "linkedlists": [
            {"title":"Reverse Linked List","difficulty":"Easy","leetcode":"https://leetcode.com/problems/reverse-linked-list/","tags":["iterative","two-pointers"]},
            {"title":"Merge Two Sorted Lists","difficulty":"Easy","leetcode":"https://leetcode.com/problems/merge-two-sorted-lists/","tags":["pointers"]},
            {"title":"Detect Cycle in Linked List","difficulty":"Easy","leetcode":"https://leetcode.com/problems/linked-list-cycle/","tags":["floyd","two-pointers"]},
            {"title":"Interweaving / Merge Alternate Nodes","difficulty":"Medium","leetcode":"","tags":["pointer-manipulation"]},
            {"title":"Palindrome Linked List","difficulty":"Medium","leetcode":"https://leetcode.com/problems/palindrome-linked-list/","tags":["reverse-half","stack"]},
            {"title":"Remove Nth Node From End","difficulty":"Medium","leetcode":"https://leetcode.com/problems/remove-nth-node-from-end-of-list/","tags":["two-pointers"]},
            {"title":"Add Two Numbers","difficulty":"Medium","leetcode":"https://leetcode.com/problems/add-two-numbers/","tags":["linked-list","math"]}
        ],
        "trees": [
            {"title":"Binary Tree Inorder/Preorder/Postorder Traversal","difficulty":"Easy","leetcode":"https://leetcode.com/problems/binary-tree-inorder-traversal/","tags":["dfs","recursion","stack"]},
            {"title":"Binary Tree Level Order Traversal","difficulty":"Easy","leetcode":"https://leetcode.com/problems/binary-tree-level-order-traversal/","tags":["bfs","queue"]},
            {"title":"Binary Tree Maximum Path Sum","difficulty":"Hard","leetcode":"https://leetcode.com/problems/binary-tree-maximum-path-sum/","tags":["dfs","tree-dp"]},
            {"title":"Lowest Common Ancestor (Binary Tree)","difficulty":"Medium","leetcode":"https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/","tags":["dfs","recursion"]},
            {"title":"Lowest Common Ancestor (BST)","difficulty":"Medium","leetcode":"https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/","tags":["bst","property"]},
            {"title":"Validate Binary Search Tree","difficulty":"Medium","leetcode":"https://leetcode.com/problems/validate-binary-search-tree/","tags":["dfs","range-check"]},
            {"title":"Serialize and Deserialize Binary Tree","difficulty":"Hard","leetcode":"https://leetcode.com/problems/serialize-and-deserialize-binary-tree/","tags":["bfs","dfs","string"]},
            {"title":"Invert Binary Tree","difficulty":"Easy","leetcode":"https://leetcode.com/problems/invert-binary-tree/","tags":["dfs","recursion"]}
        ],
        "heaps": [
            {"title":"Kth Largest Element in an Array","difficulty":"Medium","leetcode":"https://leetcode.com/problems/kth-largest-element-in-an-array/","tags":["heap","quickselect"]},
            {"title":"Top K Frequent Elements","difficulty":"Medium","leetcode":"https://leetcode.com/problems/top-k-frequent-elements/","tags":["heap","bucket-sort"]},
            {"title":"Median of Data Stream","difficulty":"Hard","leetcode":"https://leetcode.com/problems/find-median-from-data-stream/","tags":["two-heaps","streaming"]}
        ],
        "stacks_queues": [
            {"title":"Valid Parentheses","difficulty":"Easy","leetcode":"https://leetcode.com/problems/valid-parentheses/","tags":["stack"]},
            {"title":"Daily Temperatures","difficulty":"Medium","leetcode":"https://leetcode.com/problems/daily-temperatures/","tags":["monotonic-stack"]},
            {"title":"Sliding Window Maximum","difficulty":"Hard","leetcode":"https://leetcode.com/problems/sliding-window-maximum/","tags":["deque","monotonic-queue"]},
            {"title":"Min Stack","difficulty":"Easy","leetcode":"https://leetcode.com/problems/min-stack/","tags":["stack","auxiliary-stack"]}
        ],
        "graphs": [
            {"title":"Number of Islands","difficulty":"Medium","leetcode":"https://leetcode.com/problems/number-of-islands/","tags":["dfs","bfs","grid"]},
            {"title":"Course Schedule","difficulty":"Medium","leetcode":"https://leetcode.com/problems/course-schedule/","tags":["topological-sort","cycle-detection"]},
            {"title":"Course Schedule II","difficulty":"Medium","leetcode":"https://leetcode.com/problems/course-schedule-ii/","tags":["topological-sort"]},
            {"title":"Clone Graph","difficulty":"Medium","leetcode":"https://leetcode.com/problems/clone-graph/","tags":["dfs","bfs","hashmap"]},
            {"title":"Redundant Connection","difficulty":"Medium","leetcode":"https://leetcode.com/problems/redundant-connection/","tags":["union-find","graph"]},
            {"title":"Network Delay Time (Dijkstra)","difficulty":"Medium","leetcode":"https://leetcode.com/problems/network-delay-time/","tags":["dijkstra","shortest-path"]}
        ],
        "greedy": [
            {"title":"Jump Game","difficulty":"Medium","leetcode":"https://leetcode.com/problems/jump-game/","tags":["greedy"]},
            {"title":"Activity Selection / Meeting Rooms","difficulty":"Medium","leetcode":"https://leetcode.com/problems/meeting-rooms/","tags":["intervals","greedy"]}
        ],
        "dynamic_programming": [
            {"title":"Coin Change","difficulty":"Medium","leetcode":"https://leetcode.com/problems/coin-change/","tags":["dp","unbounded-knapsack"]},
            {"title":"Longest Increasing Subsequence","difficulty":"Medium","leetcode":"https://leetcode.com/problems/longest-increasing-subsequence/","tags":["dp","binary-search"]},
            {"title":"Edit Distance","difficulty":"Hard","leetcode":"https://leetcode.com/problems/edit-distance/","tags":["dp","string-dp"]},
            {"title":"House Robber","difficulty":"Medium","leetcode":"https://leetcode.com/problems/house-robber/","tags":["dp"]},
            {"title":"Partition Equal Subset Sum","difficulty":"Medium","leetcode":"https://leetcode.com/problems/partition-equal-subset-sum/","tags":["dp","subset-sum"]}
        ],
        "recursion_backtracking": [
            {"title":"Permutations","difficulty":"Medium","leetcode":"https://leetcode.com/problems/permutations/","tags":["backtracking"]},
            {"title":"Subsets","difficulty":"Medium","leetcode":"https://leetcode.com/problems/subsets/","tags":["backtracking"]},
            {"title":"N-Queens","difficulty":"Hard","leetcode":"https://leetcode.com/problems/n-queens/","tags":["backtracking"]},
            {"title":"Sudoku Solver","difficulty":"Hard","leetcode":"https://leetcode.com/problems/sudoku-solver/","tags":["backtracking","dfs"]}
        ],
        "tries_hashing": [
            {"title":"Implement Trie (Prefix Tree)","difficulty":"Medium","leetcode":"https://leetcode.com/problems/implement-trie-prefix-tree/","tags":["trie"]},
            {"title":"Word Search II","difficulty":"Hard","leetcode":"https://leetcode.com/problems/word-search-ii/","tags":["trie","backtracking"]},
            {"title":"LRU Cache","difficulty":"Medium","leetcode":"https://leetcode.com/problems/lru-cache/","tags":["hashmap","linkedlist"]}
        ],
        "sorting_searching": [
            {"title":"Search in Rotated Sorted Array","difficulty":"Medium","leetcode":"https://leetcode.com/problems/search-in-rotated-sorted-array/","tags":["binary-search","rotated-array"]},
            {"title":"Kth Smallest Element in a Sorted Matrix","difficulty":"Medium","leetcode":"https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/","tags":["heap","binary-search"]},
            {"title":"Quick Sort Template","difficulty":"Medium","leetcode":"","tags":["sorting","divide-and-conquer"]}
        ],
        "patterns_misc": [
            {"title":"Largest Rectangle in Histogram","difficulty":"Hard","leetcode":"https://leetcode.com/problems/largest-rectangle-in-histogram/","tags":["monotonic-stack"]},
            {"title":"Union-Find Template","difficulty":"Medium","leetcode":"https://leetcode.com/problems/redundant-connection/","tags":["union-find"]},
            {"title":"Minimum Window Substring (Sliding Window Template)","difficulty":"Hard","leetcode":"https://leetcode.com/problems/minimum-window-substring/","tags":["sliding-window"]}
        ],
        "system_design": [
            {"title":"System Design Quick Notes (Load Balancer, Caching, Queues, CAP)","difficulty":"N/A","leetcode":"","tags":["system-design","notes"]}
        ]
    }
    
    print("🚀 Generating DSA Revision Repository...")
    
    total_problems = 0
    total_topics = 0
    
    # Generate Java files for each topic
    for topic, problems in problems_data.items():
        print(f"\n📚 Processing {topic} topic ({len(problems)} problems)...")
        total_topics += 1
        
        for problem in problems:
            generate_java_file(topic, problem)
            total_problems += 1
        
        # Create test file for this topic
        create_test_file(topic, problems)
    
    # Create summary files
    create_summary_files(problems_data)
    
    print(f"\n✅ Generation Complete!")
    print(f"📊 Summary:")
    print(f"   • Topics created: {total_topics}")
    print(f"   • Problem files: {total_problems}")
    print(f"   • Test files: {total_topics}")
    print(f"\n🔧 Compile command:")
    print(f"   find topics -name '*.java' -exec javac {{}} \\;")
    print(f"\n🧪 Run tests:")
    print(f"   java -cp .:junit-platform-console-standalone.jar org.junit.platform.console.ConsoleLauncher --scan-classpath")

def create_summary_files(problems_data: Dict) -> None:
    """Create SUMMARY.md and problems_index.json files."""
    
    # Create SUMMARY.md
    summary_content = "# DSA Revision Repository Summary\n\n"
    summary_content += "This repository contains Java implementations of essential Data Structures and Algorithms problems for interview preparation.\n\n"
    
    for topic, problems in problems_data.items():
        summary_content += f"## {topic.replace('_', ' ').title()}\n\n"
        for problem in problems:
            pascal_name = to_pascal_case(problem['title'])
            file_path = f"topics/{topic}/{pascal_name}/{pascal_name}.java"
            summary_content += f"- [{problem['title']}]({file_path}) - {problem['difficulty']}\n"
        summary_content += "\n"
    
    with open("SUMMARY.md", 'w') as f:
        f.write(summary_content)
    
    # Create problems_index.json
    with open("problems_index.json", 'w') as f:
        json.dump(problems_data, f, indent=2)
    
    print("Created: SUMMARY.md")
    print("Created: problems_index.json")

if __name__ == "__main__":
    main()

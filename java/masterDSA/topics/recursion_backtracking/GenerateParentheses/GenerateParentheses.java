package masterDSA.topics.recursion_backtracking.GenerateParentheses;
import java.util.*;


/**
 * Generate Parentheses
 * 
 * Given n pairs of parentheses, write a function to generate all combinations 
 * of well-formed parentheses.
 * 
 * LeetCode: https://leetcode.com/problems/generate-parentheses/
 * Difficulty: Medium
 * Tags: recursion, backtracking
 * 
 * Approach:
 * - Backtracking: Generate all valid combinations
 * - Recursive: Use recursion with constraints
 * - Iterative: Use queue to generate combinations
 * 
 * Pseudocode:
 * Backtracking:
 * 1. If open < n, add '(' and recurse
 * 2. If close < open, add ')' and recurse
 * 3. If length == 2*n, add to result
 * 4. Backtrack by removing last character
 */
public class GenerateParentheses {
    
    /**
     * Backtracking approach - O(4^n / sqrt(n)) time, O(4^n / sqrt(n)) space
     * Generate all valid combinations using backtracking
     */
    public static java.util.List<String> generateParentheses_backtracking(int n) {
        java.util.List<String> result = new java.util.ArrayList<>();
        backtrack("", 0, 0, n, result);
        return result;
    }
    
    private static void backtrack(String current, int open, int close, int n, java.util.List<String> result) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        
        if (open < n) {
            backtrack(current + "(", open + 1, close, n, result);
        }
        
        if (close < open) {
            backtrack(current + ")", open, close + 1, n, result);
        }
    }
    
    /**
     * Recursive approach - O(4^n / sqrt(n)) time, O(4^n / sqrt(n)) space
     * Use recursion with constraints
     */
    public static java.util.List<String> generateParentheses_recursive(int n) {
        java.util.List<String> result = new java.util.ArrayList<>();
        generateRecursive("", n, n, result);
        return result;
    }
    
    private static void generateRecursive(String current, int open, int close, java.util.List<String> result) {
        if (open == 0 && close == 0) {
            result.add(current);
            return;
        }
        
        if (open > 0) {
            generateRecursive(current + "(", open - 1, close, result);
        }
        
        if (close > open) {
            generateRecursive(current + ")", open, close - 1, result);
        }
    }
    
    /**
     * Iterative approach - O(4^n / sqrt(n)) time, O(4^n / sqrt(n)) space
     * Use queue to generate combinations
     */
    public static java.util.List<String> generateParentheses_iterative(int n) {
        java.util.List<String> result = new java.util.ArrayList<>();
        java.util.Queue<String> queue = new java.util.LinkedList<>();
        java.util.Queue<Integer> openQueue = new java.util.LinkedList<>();
        java.util.Queue<Integer> closeQueue = new java.util.LinkedList<>();
        
        queue.offer("");
        openQueue.offer(0);
        closeQueue.offer(0);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int open = openQueue.poll();
            int close = closeQueue.poll();
            
            if (current.length() == 2 * n) {
                result.add(current);
            } else {
                if (open < n) {
                    queue.offer(current + "(");
                    openQueue.offer(open + 1);
                    closeQueue.offer(close);
                }
                
                if (close < open) {
                    queue.offer(current + ")");
                    openQueue.offer(open);
                    closeQueue.offer(close + 1);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to validate parentheses
     */
    public static boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        
        return count == 0;
    }
    
    /**
     * Helper method to find longest valid parentheses
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(-1);
        int maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
    
    /**
     * Helper method to remove invalid parentheses
     */
    public static java.util.List<String> removeInvalidParentheses(String s) {
        java.util.List<String> result = new java.util.ArrayList<>();
        removeInvalidParenthesesHelper(s, 0, 0, new char[]{'(', ')'}, result);
        return result;
    }
    
    private static void removeInvalidParenthesesHelper(String s, int lastI, int lastJ, char[] par, java.util.List<String> result) {
        int stack = 0;
        
        for (int i = lastI; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            
            for (int j = lastJ; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == lastJ || s.charAt(j - 1) != par[1])) {
                    removeInvalidParenthesesHelper(s.substring(0, j) + s.substring(j + 1), i, j, par, result);
                }
            }
            return;
        }
        
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            removeInvalidParenthesesHelper(reversed, 0, 0, new char[]{')', '('}, result);
        } else {
            result.add(reversed);
        }
    }
    
    /**
     * Helper method to find minimum add to make parentheses valid
     */
    public static int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int open = 0;
        int close = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }
        
        return open + close;
    }
    
    /**
     * Helper method to find minimum remove to make parentheses valid
     */
    public static int minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int open = 0;
        int close = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }
        
        return open + close;
    }
    
    /**
     * Helper method to generate parentheses with different characters
     */
    public static java.util.List<String> generateParenthesesWithChars(int n, char openChar, char closeChar) {
        java.util.List<String> result = new java.util.ArrayList<>();
        generateWithChars("", 0, 0, n, openChar, closeChar, result);
        return result;
    }
    
    private static void generateWithChars(String current, int open, int close, int n, char openChar, char closeChar, java.util.List<String> result) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        
        if (open < n) {
            generateWithChars(current + openChar, open + 1, close, n, openChar, closeChar, result);
        }
        
        if (close < open) {
            generateWithChars(current + closeChar, open, close + 1, n, openChar, closeChar, result);
        }
    }
    
    /**
     * Helper method to generate parentheses with maximum depth
     */
    public static java.util.List<String> generateParenthesesWithMaxDepth(int n, int maxDepth) {
        java.util.List<String> result = new java.util.ArrayList<>();
        generateWithMaxDepth("", 0, 0, n, maxDepth, 0, result);
        return result;
    }
    
    private static void generateWithMaxDepth(String current, int open, int close, int n, int maxDepth, int currentDepth, java.util.List<String> result) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        
        if (open < n && currentDepth < maxDepth) {
            generateWithMaxDepth(current + "(", open + 1, close, n, maxDepth, currentDepth + 1, result);
        }
        
        if (close < open) {
            generateWithMaxDepth(current + ")", open, close + 1, n, maxDepth, Math.max(0, currentDepth - 1), result);
        }
    }
    
    /**
     * Helper method to count valid parentheses combinations
     */
    public static int countValidParentheses(int n) {
        if (n <= 0) {
            return 0;
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        
        return dp[n];
    }
    
    public static void main(String[] args) {
        // Test case: n = 3
        int n = 3;
        
        System.out.println("Testing Generate Parentheses:");
        System.out.println("n = " + n);
        
        java.util.List<String> result1 = generateParentheses_backtracking(n);
        System.out.println("Backtracking result: " + result1);
        
        java.util.List<String> result2 = generateParentheses_recursive(n);
        System.out.println("Recursive result: " + result2);
        
        java.util.List<String> result3 = generateParentheses_iterative(n);
        System.out.println("Iterative result: " + result3);
        
        // Expected: ["((()))", "(()())", "(())()", "()(())", "()()()"]
        
        System.out.println();
        
        // Test case: n = 1
        int n2 = 1;
        
        System.out.println("n = " + n2);
        
        java.util.List<String> result4 = generateParentheses_backtracking(n2);
        System.out.println("Backtracking result: " + result4);
        
        // Expected: ["()"]
        
        System.out.println();
        
        // Test case: n = 2
        int n3 = 2;
        
        System.out.println("n = " + n3);
        
        java.util.List<String> result5 = generateParentheses_backtracking(n3);
        System.out.println("Backtracking result: " + result5);
        
        // Expected: ["(())", "()()"]
        
        System.out.println();
        
        // Test helper methods
        boolean isValid = isValidParentheses("()()");
        System.out.println("Is '()()' valid: " + isValid);
        
        int longestValid = longestValidParentheses("(()");
        System.out.println("Longest valid parentheses in '(()': " + longestValid);
        
        java.util.List<String> removed = removeInvalidParentheses("()())()");
        System.out.println("Remove invalid parentheses: " + removed);
        
        int minAdd = minAddToMakeValid("()))((");
        System.out.println("Minimum add to make valid: " + minAdd);
        
        int minRemove = minRemoveToMakeValid("()))((");
        System.out.println("Minimum remove to make valid: " + minRemove);
        
        java.util.List<String> withChars = generateParenthesesWithChars(2, '[', ']');
        System.out.println("Parentheses with chars: " + withChars);
        
        java.util.List<String> withMaxDepth = generateParenthesesWithMaxDepth(3, 2);
        System.out.println("Parentheses with max depth: " + withMaxDepth);
        
        int count = countValidParentheses(3);
        System.out.println("Count valid parentheses: " + count);
        
        // Expected: Is valid = true, Longest valid = 2, Remove invalid = ["()()()", "(())()"], Min add = 4, Min remove = 4, With chars = ["[[]]", "[][]"], With max depth = ["()()()", "()(())", "(())()"], Count = 5
    }
}

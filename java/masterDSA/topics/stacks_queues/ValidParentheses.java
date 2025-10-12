package masterDSA.topics.stacks_queues;
import java.util.*;


/**
 * Valid Parentheses
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * LeetCode: https://leetcode.com/problems/valid-parentheses/
 * Difficulty: Easy
 * Tags: stack
 * 
 * Approach:
 * - Use stack to match opening and closing brackets
 * - Push opening brackets onto stack
 * - Pop and match when encountering closing brackets
 * - Check if stack is empty at the end
 * 
 * Pseudocode:
 * 1. Create stack
 * 2. For each character:
 *    a. If opening bracket, push to stack
 *    b. If closing bracket, check if stack is empty or doesn't match
 *    c. If matches, pop from stack
 * 3. Return stack.isEmpty()
 */
public class ValidParentheses {
    
    /**
     * Stack approach - O(n) time, O(n) space
     * Use stack to match opening and closing brackets
     */
    public static boolean isValid_stack(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if (!isMatching(top, c)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Helper method to check if brackets match
     */
    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
    
    /**
     * HashMap approach - O(n) time, O(n) space
     * Use HashMap to store bracket mappings
     */
    public static boolean isValid_hashMap(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (bracketMap.containsKey(c)) {
                // Closing bracket
                if (stack.isEmpty() || stack.pop() != bracketMap.get(c)) {
                    return false;
                }
            } else {
                // Opening bracket
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Array approach - O(n) time, O(n) space
     * Use array to simulate stack
     */
    public static boolean isValid_array(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        char[] stack = new char[s.length()];
        int top = -1;
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack[++top] = c;
            } else if (c == ')' || c == '}' || c == ']') {
                if (top == -1) {
                    return false;
                }
                
                char open = stack[top--];
                if (!isMatching(open, c)) {
                    return false;
                }
            }
        }
        
        return top == -1;
    }
    
    /**
     * Recursive approach - O(n) time, O(n) space
     * Use recursion to validate brackets
     */
    public static boolean isValid_recursive(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        return isValidHelper(s, 0, new Stack<>());
    }
    
    private static boolean isValidHelper(String s, int index, Stack<Character> stack) {
        if (index == s.length()) {
            return stack.isEmpty();
        }
        
        char c = s.charAt(index);
        
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
            return isValidHelper(s, index + 1, stack);
        } else if (c == ')' || c == '}' || c == ']') {
            if (stack.isEmpty()) {
                return false;
            }
            
            char top = stack.pop();
            if (!isMatching(top, c)) {
                return false;
            }
            
            return isValidHelper(s, index + 1, stack);
        }
        
        return isValidHelper(s, index + 1, stack);
    }
    
    /**
     * Helper method to find longest valid parentheses
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
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
     * Helper method to generate valid parentheses
     */
    public static List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesesHelper("", 0, 0, n, result);
        return result;
    }
    
    private static void generateParenthesesHelper(String current, int open, int close, int n, List<String> result) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        
        if (open < n) {
            generateParenthesesHelper(current + "(", open + 1, close, n, result);
        }
        
        if (close < open) {
            generateParenthesesHelper(current + ")", open, close + 1, n, result);
        }
    }
    
    /**
     * Helper method to remove invalid parentheses
     */
    public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        removeInvalidParenthesesHelper(s, 0, 0, new char[]{'(', ')'}, result);
        return result;
    }
    
    private static void removeInvalidParenthesesHelper(String s, int lastI, int lastJ, char[] par, List<String> result) {
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
    
    public static void main(String[] args) {
        // Test case: s = "()"
        String s1 = "()";
        
        System.out.println("Testing Valid Parentheses:");
        System.out.println("String: " + s1);
        
        boolean result1 = isValid_stack(s1);
        System.out.println("Stack result: " + result1);
        
        boolean result2 = isValid_hashMap(s1);
        System.out.println("HashMap result: " + result2);
        
        boolean result3 = isValid_array(s1);
        System.out.println("Array result: " + result3);
        
        boolean result4 = isValid_recursive(s1);
        System.out.println("Recursive result: " + result4);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: s = "()[]{}"
        String s2 = "()[]{}";
        
        System.out.println("String: " + s2);
        
        boolean result5 = isValid_stack(s2);
        System.out.println("Stack result: " + result5);
        
        // Expected: true
        
        System.out.println();
        
        // Test case: s = "(]"
        String s3 = "(]";
        
        System.out.println("String: " + s3);
        
        boolean result6 = isValid_stack(s3);
        System.out.println("Stack result: " + result6);
        
        // Expected: false
        
        System.out.println();
        
        // Test case: s = "([)]"
        String s4 = "([)]";
        
        System.out.println("String: " + s4);
        
        boolean result7 = isValid_stack(s4);
        System.out.println("Stack result: " + result7);
        
        // Expected: false
        
        System.out.println();
        
        // Test helper methods
        String s5 = "(()";
        int longestValid = longestValidParentheses(s5);
        System.out.println("Longest valid parentheses in '" + s5 + "': " + longestValid);
        
        List<String> generated = generateParentheses(3);
        System.out.println("Generated parentheses for n=3: " + generated);
        
        // Expected: Longest valid = 2, Generated = ["((()))", "(()())", "(())()", "()(())", "()()()"]
    }
}

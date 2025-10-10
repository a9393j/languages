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
 * - Use stack to track opening brackets
 * - When closing bracket found, check if it matches top of stack
 * - If stack is empty or doesn't match, return false
 * 
 * Pseudocode:
 * 1. Create stack for opening brackets
 * 2. For each character:
 *    a. If opening bracket, push to stack
 *    b. If closing bracket, check if stack is empty or doesn't match
 *    c. If matches, pop from stack
 * 3. Return true if stack is empty
 */

package masterDSA.topics.stacks_queues.ValidParentheses.ValidParentheses;

import java.util.*;

public class ValidParentheses {
    
    /**
     * Stack approach - O(n) time, O(n) space
     * Most efficient approach for this problem
     */
    public static boolean isValid_stack(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                // Opening bracket, push to stack
                stack.push(c);
            } else {
                // Closing bracket, check if stack is empty
                if (stack.isEmpty()) {
                    return false;
                }
                
                // Check if closing bracket matches opening bracket
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        // Return true if stack is empty
        return stack.isEmpty();
    }
    
    /**
     * Alternative approach using HashMap - O(n) time, O(n) space
     * Uses HashMap to map closing brackets to opening brackets
     */
    public static boolean isValid_hashMap(String s) {
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (bracketMap.containsValue(c)) {
                // Opening bracket, push to stack
                stack.push(c);
            } else if (bracketMap.containsKey(c)) {
                // Closing bracket, check if stack is empty or doesn't match
                if (stack.isEmpty() || stack.pop() != bracketMap.get(c)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Example usage and test case
     */
    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{[]}";
        
        System.out.println("Testing Valid Parentheses:");
        
        System.out.println("String: " + s1);
        System.out.println("Stack result: " + isValid_stack(s1));
        System.out.println("HashMap result: " + isValid_hashMap(s1));
        
        System.out.println("String: " + s2);
        System.out.println("Stack result: " + isValid_stack(s2));
        
        System.out.println("String: " + s3);
        System.out.println("Stack result: " + isValid_stack(s3));
        
        System.out.println("String: " + s4);
        System.out.println("Stack result: " + isValid_stack(s4));
        
        System.out.println("String: " + s5);
        System.out.println("Stack result: " + isValid_stack(s5));
    }
}
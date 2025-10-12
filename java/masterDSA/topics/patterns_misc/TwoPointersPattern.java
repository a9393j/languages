package masterDSA.topics.patterns_misc;
import java.util.*;


/**
 * Two Pointers Pattern
 * 
 * Problem Statement:
 * The two pointers pattern is a technique for solving problems involving arrays or strings
 * by using two pointers that move through the data structure in a coordinated manner.
 * 
 * LeetCode Link: https://leetcode.com/tag/two-pointers/
 * Difficulty: Easy to Medium
 * Tags: Array, Two Pointers, String
 * 
 * Approach Summary:
 * 1. Opposite Direction: Pointers start at opposite ends and move toward each other
 * 2. Same Direction: Both pointers start at the same end and move in the same direction
 * 3. Fast and Slow: One pointer moves faster than the other (also called Floyd's cycle detection)
 * 4. Use pointers to avoid nested loops and achieve O(n) time complexity
 * 
 * Pseudocode:
 * 1. Initialize two pointers (left, right) or (slow, fast)
 * 2. Move pointers based on problem conditions
 * 3. Update result when pointers meet certain criteria
 * 4. Continue until pointers cross or meet
 * 
 * Time Complexity: O(n) - each element visited at most once
 * Space Complexity: O(1) - only using constant extra space
 */
public class TwoPointersPattern {
    
    /**
     * Opposite direction - Two sum in sorted array
     * 
     * @param nums Sorted array of integers
     * @param target Target sum
     * @return Indices of two numbers that sum to target
     */
    public static int[] twoSum_sorted(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++; // Need larger sum
            } else {
                right--; // Need smaller sum
            }
        }
        
        return new int[]{-1, -1};
    }
    
    /**
     * Opposite direction - Valid palindrome
     * 
     * @param s Input string
     * @return True if string is a valid palindrome
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Same direction - Remove duplicates from sorted array
     * 
     * @param nums Sorted array with duplicates
     * @return Length of array after removing duplicates
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int slow = 0; // Position for next unique element
        
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        
        return slow + 1;
    }
    
    /**
     * Same direction - Remove element
     * 
     * @param nums Array of integers
     * @param val Value to remove
     * @return Length of array after removing all instances of val
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int slow = 0; // Position for next element not equal to val
        
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        return slow;
    }
    
    /**
     * Opposite direction - Container with most water
     * 
     * @param height Array representing heights of vertical lines
     * @return Maximum area of water that can be contained
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            // Calculate area with current pointers
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = width * minHeight;
            maxArea = Math.max(maxArea, currentArea);
            
            // Move pointer with smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    /**
     * Opposite direction - Three sum
     * 
     * @param nums Array of integers
     * @param target Target sum
     * @return List of triplets that sum to target
     */
    public static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for second element
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for third element
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Same direction - Move zeros to end
     * 
     * @param nums Array of integers
     * @return Array with zeros moved to the end
     */
    public static int[] moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        
        int slow = 0; // Position for next non-zero element
        
        // Move all non-zero elements to the front
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        // Fill remaining positions with zeros
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
        
        return nums;
    }
    
    /**
     * Opposite direction - Squares of sorted array
     * 
     * @param nums Sorted array of integers (can be negative)
     * @return Array of squares in sorted order
     */
    public static int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }
        
        return result;
    }
    
    /**
     * Same direction - Find all duplicates
     * 
     * @param nums Array of integers (1 <= nums[i] <= n)
     * @return List of numbers that appear twice
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // Use array indices as hash table
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            
            if (nums[index] < 0) {
                // Already seen this number
                result.add(Math.abs(nums[i]));
            } else {
                // Mark as seen by making it negative
                nums[index] = -nums[index];
            }
        }
        
        return result;
    }
    
    /**
     * Opposite direction - Reverse string
     * 
     * @param s Character array to reverse
     * @return Reversed character array
     */
    public static char[] reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return s;
        }
        
        int left = 0;
        int right = s.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            left++;
            right--;
        }
        
        return s;
    }
    
    /**
     * Demonstration method showing various two pointers patterns
     */
    public static void runExample() {
        System.out.println("=== Two Pointers Pattern Examples ===");
        
        // Two sum in sorted array
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] twoSumResult = twoSum_sorted(nums1, target1);
        System.out.println("Two sum indices: " + Arrays.toString(twoSumResult));
        
        // Valid palindrome
        String s1 = "A man a plan a canal Panama";
        boolean isPal = isPalindrome(s1);
        System.out.println("Is palindrome: " + isPal);
        
        // Remove duplicates
        int[] nums2 = {1, 1, 2, 2, 3, 4, 4, 5};
        int newLength = removeDuplicates(nums2);
        System.out.println("Array after removing duplicates: " + Arrays.toString(Arrays.copyOf(nums2, newLength)));
        
        // Remove element
        int[] nums3 = {3, 2, 2, 3};
        int val = 3;
        int newLength2 = removeElement(nums3, val);
        System.out.println("Array after removing " + val + ": " + Arrays.toString(Arrays.copyOf(nums3, newLength2)));
        
        // Container with most water
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = maxArea(height);
        System.out.println("Maximum area: " + maxArea);
        
        // Three sum
        int[] nums4 = {-1, 0, 1, 2, -1, -4};
        int target2 = 0;
        List<List<Integer>> threeSumResult = threeSum(nums4, target2);
        System.out.println("Three sum triplets: " + threeSumResult);
        
        // Move zeros
        int[] nums5 = {0, 1, 0, 3, 12};
        int[] movedZeros = moveZeroes(nums5);
        System.out.println("Array after moving zeros: " + Arrays.toString(movedZeros));
        
        // Sorted squares
        int[] nums6 = {-4, -1, 0, 3, 10};
        int[] squares = sortedSquares(nums6);
        System.out.println("Sorted squares: " + Arrays.toString(squares));
        
        // Find duplicates
        int[] nums7 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> duplicates = findDuplicates(nums7);
        System.out.println("Duplicates: " + duplicates);
        
        // Reverse string
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        char[] reversed = reverseString(chars);
        System.out.println("Reversed string: " + Arrays.toString(reversed));
    }
    
    public static void main(String[] args) {
        runExample();
    }
}

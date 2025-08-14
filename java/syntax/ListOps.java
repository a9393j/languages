package syntax;

import java.util.*;

public class ListOps {

    public static void listOps() {
        /**
         * - List indexes start from '0', just like array index.
         * - List allows "null"
         * - List supports Generics and we should use it whenever possible. Using
         * Generics with List will avoid ClassCastException at runtime.
         */

        System.out.println("------------------------------------------");
        System.out.println("LIST OPERATIONS");
        System.out.println("---------------------");

        List<Integer> list = new ArrayList<Integer>(); // Empty Constructor

        // Basic operations
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(5);
        list.add(10);
        Printer.print(list);

        System.out.println("list.size(): " + list.size());
        System.out.println("list.contains(5): " + list.contains(5));
        System.out.println("list.indexOf(5): " + list.indexOf(5));
        System.out.println("list.lastIndexOf(5): " + list.lastIndexOf(5));
        System.out.println("list.get(4): " + list.get(4));
        System.out.println("list.set(4,0): " + list.set(4, 0));
        System.out.println("list.add(2): " + list.add(2));
        
        // Advanced operations
        System.out.println("list.addAll(2): " + list.addAll(new ArrayList<Integer>(list.subList(0, 2))));
        System.out.println("list.subList(2, 5): " + list.subList(2, 5));
        System.out.println("list.remove(Integer.valueOf(5)): " + list.remove(Integer.valueOf(5)));
        System.out.println("list.remove(0): " + list.remove(0));

        // Sorting operations
        System.out.println("Collections.sort(list)");
        Collections.sort(list);
        Printer.print(list);

        System.out.println("Collections.sort(list, Collections.reverseOrder());");
        Collections.sort(list, Collections.reverseOrder());
        Printer.print(list);

        // Custom comparator
        System.out.println("Custom sorting by absolute value:");
        Collections.sort(list, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));
        Printer.print(list);

        // List utilities
        System.out.println("Collections.max(list): " + Collections.max(list));
        System.out.println("Collections.min(list): " + Collections.min(list));
        System.out.println("Collections.frequency(list, 5): " + Collections.frequency(list, 5));
        
        // Shuffle and reverse
        Collections.shuffle(list);
        System.out.println("After shuffle: ");
        Printer.print(list);
        
        Collections.reverse(list);
        System.out.println("After reverse: ");
        Printer.print(list);

        System.out.println("list.clear(): ");
        list.clear(); // remove all element from the list
    }

    public static void stackOps() {
        System.out.println("------------------------------------------");
        System.out.println("STACK OPERATIONS");
        System.out.println("---------------------");
        
        Stack<Integer> st = new Stack<>();
        System.out.println("st.push(1): " + st.push(1));
        System.out.println("st.push(2): " + st.push(2));
        System.out.println("st.push(3): " + st.push(3));
        System.out.println("st.pop(): " + st.pop());
        System.out.println("st.peek(): " + st.peek());
        System.out.println("st.isEmpty(): " + st.isEmpty());
        System.out.println("st.size(): " + st.size());
        
        // Advanced stack operations
        System.out.println("st.search(1): " + st.search(1)); // Returns 1-based position
        System.out.println("st.search(2): " + st.search(2));
        
        // Stack applications
        System.out.println("Valid parentheses check: " + isValidParentheses("()[]{}"));
        System.out.println("Valid parentheses check: " + isValidParentheses("([)]"));
        
        // Reverse string using stack
        System.out.println("Reverse string using stack: " + reverseString("hello"));
        
        // Evaluate postfix expression
        System.out.println("Evaluate postfix '23*4+': " + evaluatePostfix("23*4+"));
    }

    public static void queueOps() {
        System.out.println("------------------------------------------");
        System.out.println("QUEUE OPERATIONS");
        System.out.println("---------------------");

        Queue<Integer> q = new LinkedList<Integer>();
        System.out.println("q.add(1): " + q.add(1));
        System.out.println("q.add(2): " + q.add(2));
        System.out.println("q.add(3): " + q.add(3));
        System.out.println("q.remove(): " + q.remove());
        System.out.println("q.poll(): " + q.poll());
        System.out.println("q.peek(): " + q.peek());
        System.out.println("q.size(): " + q.size());
        
        // Queue applications
        System.out.println("First non-repeating character: " + firstNonRepeatingChar("leetcode"));
        System.out.println("First non-repeating character: " + firstNonRepeatingChar("loveleetcode"));
        
        // Circular queue simulation
        System.out.println("Circular queue operations:");
        circularQueueDemo();
    }

    public static void linkedListOps() {
        System.out.println("------------------------------------------");
        System.out.println("LINKEDLIST OPERATIONS");
        System.out.println("---------------------");

        // Creation
        List<Integer> list = new LinkedList<Integer>();
        List<Integer> list2 = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        
        // Add elements
        list.add(0, null);
        list.add(0, 1);
        list.add(2);
        list.add(3);
        list.addAll(list2);
        list.addAll(0, Arrays.asList(10, 20, 30));

        System.out.println("After adding elements: ");
        Printer.print(list);

        // Removing
        System.out.println("list.remove(1): " + list.remove(1));
        System.out.println("list.remove(Integer.valueOf(2)): " + list.remove(Integer.valueOf(2)));
        
        // LinkedList specific operations
        LinkedList<Integer> linkedList = new LinkedList<>(list);
        System.out.println("linkedList.getFirst(): " + linkedList.getFirst());
        System.out.println("linkedList.getLast(): " + linkedList.getLast());
        System.out.println("linkedList.removeFirst(): " + linkedList.removeFirst());
        System.out.println("linkedList.removeLast(): " + linkedList.removeLast());
        
        // Add at specific positions
        linkedList.addFirst(100);
        linkedList.addLast(200);
        System.out.println("After addFirst and addLast: ");
        Printer.print(linkedList);
        
        // Sort
        Collections.sort(linkedList);
        System.out.println("After sorting: ");
        Printer.print(linkedList);
        
        // LinkedList applications
        System.out.println("Reverse linked list: ");
        LinkedList<Integer> reversed = reverseLinkedList(linkedList);
        Printer.print(reversed);
    }

    public static void priorityQueueOps() {
        System.out.println("------------------------------------------");
        System.out.println("PRIORITY QUEUE OPERATIONS");
        System.out.println("---------------------");

        // Min heap (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        minHeap.add(5);
        minHeap.add(2);
        minHeap.add(8);
        minHeap.add(1);
        minHeap.add(9);
        
        System.out.println("Min heap elements: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();

        // Max heap (custom comparator)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
        maxHeap.add(5);
        maxHeap.add(2);
        maxHeap.add(8);
        maxHeap.add(1);
        maxHeap.add(9);
        
        System.out.println("Max heap elements: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();

        // Custom comparator with HashMap
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 3);
        map.put("banana", 1);
        map.put("cherry", 5);
        map.put("date", 2);
        
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        pq.addAll(map.keySet());
        
        System.out.println("Priority queue by map values: ");
        while (!pq.isEmpty()) {
            String key = pq.poll();
            System.out.print(key + "(" + map.get(key) + ") ");
        }
        System.out.println();
        
        // Priority queue applications
        System.out.println("Kth largest element: " + findKthLargest(Arrays.asList(3, 2, 1, 5, 6, 4), 2));
        System.out.println("Top K frequent elements: " + topKFrequent(Arrays.asList(1, 1, 1, 2, 2, 3), 2));
    }

    public static void dequeOps() {
        System.out.println("------------------------------------------");
        System.out.println("DEQUE OPERATIONS");
        System.out.println("---------------------");

        Deque<Integer> deque = new LinkedList<>();
        
        // Add operations
        deque.addFirst(1);
        deque.addLast(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        
        System.out.println("After adding elements: " + deque);
        
        // Access operations
        System.out.println("deque.getFirst(): " + deque.getFirst());
        System.out.println("deque.getLast(): " + deque.getLast());
        System.out.println("deque.peekFirst(): " + deque.peekFirst());
        System.out.println("deque.peekLast(): " + deque.peekLast());
        
        // Remove operations
        System.out.println("deque.removeFirst(): " + deque.removeFirst());
        System.out.println("deque.removeLast(): " + deque.removeLast());
        System.out.println("deque.pollFirst(): " + deque.pollFirst());
        System.out.println("deque.pollLast(): " + deque.pollLast());
        
        // Deque applications
        System.out.println("Sliding window maximum: " + 
            maxSlidingWindow(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7), 3));
    }

    public static void randomOps() {
        System.out.println("------------------------------------------");
        System.out.println("RANDOM OPERATIONS");
        System.out.println("---------------------");

        // List to array conversion
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer[] array = list.toArray(new Integer[0]);
        System.out.println("List to array: " + Arrays.toString(array));
        
        // Array to list conversion
        List<Integer> newList = Arrays.asList(array);
        System.out.println("Array to list: " + newList);
        
        // List initialization
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list3 = Collections.nCopies(5, 10);
        
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        
        // List utilities
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original: " + numbers);
        
        // Rotate
        Collections.rotate(numbers, 3);
        System.out.println("After rotate by 3: " + numbers);
        
        // Swap
        Collections.swap(numbers, 0, numbers.size() - 1);
        System.out.println("After swap first and last: " + numbers);
        
        // Replace all
        Collections.replaceAll(numbers, 1, 100);
        System.out.println("After replace 1 with 100: " + numbers);
    }

    // Helper methods for applications
    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == ']' && top != '[') || 
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static String reverseString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (c) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }

    public static char firstNonRepeatingChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
            if (count.get(c) == 1) {
                queue.offer(c);
            }
        }
        
        while (!queue.isEmpty()) {
            char c = queue.peek();
            if (count.get(c) == 1) {
                return c;
            }
            queue.poll();
        }
        return '#';
    }

    public static void circularQueueDemo() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            queue.offer(i);
        }
        
        System.out.println("Initial queue: " + queue);
        for (int i = 0; i < 3; i++) {
            System.out.println("Removed: " + queue.poll());
            queue.offer(10 + i);
            System.out.println("After operation: " + queue);
        }
    }

    public static LinkedList<Integer> reverseLinkedList(LinkedList<Integer> list) {
        LinkedList<Integer> reversed = new LinkedList<>();
        for (Integer item : list) {
            reversed.addFirst(item);
        }
        return reversed;
    }

    public static int findKthLargest(List<Integer> nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static List<Integer> topKFrequent(List<Integer> nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> pq = 
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll().getKey());
        }
        return result;
    }

    public static List<Integer> maxSlidingWindow(List<Integer> nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < nums.size(); i++) {
            // Remove elements outside the window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove smaller elements
            while (!deque.isEmpty() && nums.get(deque.peekLast()) < nums.get(i)) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            if (i >= k - 1) {
                result.add(nums.get(deque.peekFirst()));
            }
        }
        
        return result;
    }
}

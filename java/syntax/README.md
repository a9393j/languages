# Java Syntax for Competitive Coding

This folder contains comprehensive Java syntax examples and implementations for competitive coding preparation. Each file focuses on specific areas of Java programming that are commonly used in coding competitions.

## 📁 File Structure

```
syntax/
├── README.md                           # This file - comprehensive documentation
├── COMPETITIVE_CODING_GUIDE.md         # Complete guide with advanced topics
├── QUICK_REFERENCE.md                  # Quick reference card for contests
├── javaCollections.java                # Main runner - executes all operations
├── ListOps.java                       # List, Stack, Queue, Deque operations
├── SetOps.java                        # HashSet, TreeSet operations
├── MapOps.java                        # HashMap, TreeMap operations (Enhanced)
├── StringOps.java                     # String manipulation operations (Enhanced)
├── ArrayOps.java                      # Array operations and algorithms
├── MathOps.java                       # Mathematical operations and algorithms
├── GraphOps.java                      # Graph algorithms and data structures
├── DPOps.java                         # Dynamic programming examples
├── SearchSortOps.java                 # Search and sort algorithms
└── Printer.java                       # Utility printing methods (Enhanced)
```

## 🎯 What's Covered

### 1. **Basic Collections** (`ListOps.java`, `SetOps.java`, `MapOps.java`, `StringOps.java`)
- **List Operations**: ArrayList, LinkedList, Stack, Queue, Deque, PriorityQueue
- **Set Operations**: HashSet, TreeSet with sorting and uniqueness
- **Map Operations**: HashMap, TreeMap with key-value operations (Enhanced with advanced algorithms)
- **String Operations**: Manipulation, comparison, conversion methods (Enhanced with advanced algorithms)

### 2. **Array Operations** (`ArrayOps.java`)
- **Array Basics**: Declaration, initialization, iteration
- **Array Sorting**: Built-in and custom sorting
- **Array Searching**: Linear, binary, and advanced search techniques
- **Array Manipulation**: Copy, fill, range operations
- **Array Conversion**: Array to List, String to char array
- **Advanced Techniques**: Two pointers, sliding window, prefix sum, Kadane's algorithm

### 3. **Mathematical Operations** (`MathOps.java`)
- **Math Basics**: Arithmetic, bitwise operations, Math class functions
- **Number Theory**: GCD, LCM, prime numbers, Sieve of Eratosthenes
- **Bit Manipulation**: Set bits, power of 2, bit operations
- **Combinatorics**: Factorial, permutation, combination, Catalan numbers
- **Matrix Operations**: Transpose, multiplication, rotation

### 4. **Graph Operations** (`GraphOps.java`)
- **Graph Basics**: Adjacency matrix, adjacency list, edge list
- **Graph Traversal**: BFS, DFS (iterative and recursive)
- **Graph Algorithms**: Dijkstra's shortest path, connected components
- **Tree Algorithms**: Binary tree traversals, height, balance check
- **Advanced Problems**: Topological sort, cycle detection, bipartite graphs

### 5. **Dynamic Programming** (`DPOps.java`)
- **DP Basics**: Fibonacci with different approaches (recursive, memoization, bottom-up)
- **Classic Problems**: LCS, LIS, Edit Distance, Coin Change
- **Knapsack Problems**: 0/1 Knapsack, Unbounded Knapsack, Subset Sum
- **Matrix DP**: Unique paths, minimum path sum, longest palindromic substring
- **Advanced DP**: House robber, stock trading, climbing stairs, decode ways

### 6. **Search and Sort** (`SearchSortOps.java`)
- **Sorting Algorithms**: Bubble, Selection, Insertion, Merge, Quick, Heap, Counting
- **Searching Algorithms**: Linear, Binary (iterative/recursive), Jump, Interpolation
- **Advanced Searching**: First/last occurrence, peak element, rotated array minimum
- **Sorting Optimizations**: Median-of-three pivot, random pivot, hybrid sorting

### 7. **Utility Classes** (`Printer.java`)
- **Generic Printing**: Methods for all data structures
- **Formatted Output**: Clean, readable output formatting
- **Debug Utilities**: Helpful methods for debugging
- **Performance Optimized**: Efficient printing for large datasets

## 🚀 How to Use

### Running All Operations
```bash
cd java/syntax
javac *.java
java JavaCollections
```

### Running Individual Files
```bash
# Example: Run only array operations
javac ArrayOps.java
java ArrayOps

# Example: Run only math operations
javac MathOps.java
java MathOps

# Example: Run only string operations
javac StringOps.java
java StringOps
```

## 📚 Documentation Files

### **COMPETITIVE_CODING_GUIDE.md**
Complete guide covering:
- Basic syntax and data types
- Collections framework deep dive
- String manipulation techniques
- Array operations and algorithms
- Mathematical operations and number theory
- Graph algorithms and data structures
- Dynamic programming patterns
- Search and sort algorithms
- Advanced techniques (two pointers, sliding window, etc.)
- Performance optimization tips
- Common patterns and templates
- Debugging and testing strategies

### **QUICK_REFERENCE.md**
Quick reference card for contests including:
- Fast I/O templates
- Common data structure operations
- Essential algorithms (two pointers, sliding window, binary search)
- Dynamic programming templates
- Sorting algorithms
- Common patterns (frequency counter, monotonic stack, union find)
- Performance tips and optimization techniques
- Debugging utilities
- Time complexity reference table

## 📊 Key Features

### **Comprehensive Coverage**
- All major data structures and algorithms
- Common competitive coding patterns
- Optimization techniques
- Real-world problem examples

### **Ready-to-Use Code**
- Copy-paste friendly implementations
- Well-documented methods with time complexity
- Multiple approaches for same problem
- Performance considerations

### **Enhanced Documentation**
- Detailed JavaDoc comments
- Time and space complexity analysis
- Usage examples and edge cases
- Best practices and optimization tips

### **Competitive Coding Focus**
- Fast I/O patterns
- Memory-efficient solutions
- Time complexity awareness
- Common optimization tricks

## 🎯 Common Use Cases

### **Array Problems**
```java
// Two pointers technique
int left = 0, right = arr.length - 1;
while (left < right) {
    // Process elements
}

// Sliding window
int windowSum = 0;
for (int i = 0; i < k; i++) windowSum += arr[i];
```

### **String Problems**
```java
// Character frequency
int[] freq = new int[26];
for (char c : str.toCharArray()) {
    freq[c - 'a']++;
}

// Palindrome check
String reversed = new StringBuilder(str).reverse().toString();
boolean isPalindrome = str.equals(reversed);
```

### **Graph Problems**
```java
// BFS template
Queue<Integer> queue = new LinkedList<>();
Set<Integer> visited = new HashSet<>();
queue.offer(start);
visited.add(start);
```

### **Dynamic Programming**
```java
// Memoization template
Map<Integer, Integer> memo = new HashMap<>();
if (memo.containsKey(n)) return memo.get(n);
// ... computation
memo.put(n, result);
```

## 🔧 Utility Methods

### **Fast I/O** (for competitive coding)
```java
// Fast input
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer st = new StringTokenizer(br.readLine());
int n = Integer.parseInt(st.nextToken());

// Fast output
PrintWriter pw = new PrintWriter(System.out);
pw.println(result);
pw.flush();
```

### **Common Conversions**
```java
// String to char array
char[] chars = str.toCharArray();

// Array to List
List<Integer> list = Arrays.asList(arr);

// List to Array
Integer[] arr = list.toArray(new Integer[0]);
```

### **Mathematical Operations**
```java
// GCD
public static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}

// Power with modulo
public static long pow(long base, long exp, long mod) {
    long result = 1;
    while (exp > 0) {
        if ((exp & 1) == 1) result = (result * base) % mod;
        base = (base * base) % mod;
        exp >>= 1;
    }
    return result;
}
```

### **Printing Utilities**
```java
// Print array
Printer.printArray(arr);

// Print matrix
Printer.printMatrix(matrix);

// Print collection
Printer.print(list);

// Print with formatting
Printer.printResult("Answer", result);
```

## 📊 Time Complexity Reference

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Array Access | O(1) | O(1) |
| Linear Search | O(n) | O(1) |
| Binary Search | O(log n) | O(1) |
| Bubble Sort | O(n²) | O(1) |
| Quick Sort | O(n log n) avg | O(log n) |
| Merge Sort | O(n log n) | O(n) |
| Heap Sort | O(n log n) | O(1) |
| BFS/DFS | O(V + E) | O(V) |
| Dijkstra | O((V + E) log V) | O(V) |
| HashMap Operations | O(1) avg | O(n) |
| TreeMap Operations | O(log n) | O(n) |

## 🎯 Competitive Coding Tips

### **1. Fast I/O**
- Use `BufferedReader` and `StringTokenizer` for input
- Use `PrintWriter` for output
- Avoid `Scanner` for large inputs

### **2. Memory Management**
- Use primitive arrays instead of objects when possible
- Reuse arrays instead of creating new ones
- Use `StringBuilder` for string concatenation

### **3. Algorithm Selection**
- Choose algorithms based on input size
- Consider both time and space complexity
- Use appropriate data structures

### **4. Common Patterns**
- Two pointers for array problems
- Sliding window for subarray problems
- Binary search for optimization problems
- DFS/BFS for graph problems
- DP for optimization problems

### **5. Debugging**
- Use the `Printer` utility class for formatted output
- Check edge cases (null, empty, single element)
- Verify time complexity before submission
- Test with small and large inputs

## 🔗 Related Resources

- [LeetCode](https://leetcode.com/) - Practice problems
- [HackerRank](https://www.hackerrank.com/) - Competitive programming
- [Codeforces](https://codeforces.com/) - Advanced competitive programming
- [GeeksforGeeks](https://www.geeksforgeeks.org/) - Algorithm tutorials

## 📝 Notes

- All code examples are tested and ready to use
- Time complexity is mentioned where relevant
- Multiple approaches are provided for complex problems
- Focus on practical implementation over theoretical perfection
- Code is optimized for competitive coding scenarios
- Enhanced documentation provides detailed explanations and usage examples

This comprehensive syntax collection provides everything needed for competitive coding in Java, from basic operations to advanced algorithms and optimization techniques. The enhanced documentation makes it easy to understand and use the code effectively. 
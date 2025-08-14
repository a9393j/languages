package syntax;

import java.util.*;

// https://satishkathiriya99.medium.com/java-cheat-sheet-287da6e15e96
class JavaCollections {

  public static void main(String[] args) {

    System.out.println("==========================================");
    System.out.println("COMPREHENSIVE JAVA SYNTAX FOR COMPETITIVE CODING");
    System.out.println("==========================================");

    // Basic Collections
    System.out.println("\n1. BASIC COLLECTIONS");
    System.out.println("=====================");
    ListOps.listOps();
    SetOps.hashSetOps();
    SetOps.treeSetOps();
    StringOps.stringOps();
    MapOps.hashMapOps();
    MapOps.treeMapOps();
    ListOps.stackOps();
    ListOps.queueOps();
    ListOps.dequeOps();
    ListOps.priorityQueueOps();
    ListOps.linkedListOps();

    // Array Operations
    System.out.println("\n2. ARRAY OPERATIONS");
    System.out.println("===================");
    ArrayOps.arrayBasics();
    ArrayOps.arraySorting();
    ArrayOps.arraySearching();
    ArrayOps.arrayManipulation();
    ArrayOps.arrayConversion();
    ArrayOps.arrayAdvanced();

    // Mathematical Operations
    System.out.println("\n3. MATHEMATICAL OPERATIONS");
    System.out.println("===========================");
    MathOps.mathBasics();
    MathOps.numberTheory();
    MathOps.bitManipulation();
    MathOps.combinatorics();
    MathOps.matrixOperations();

    // Graph Operations
    System.out.println("\n4. GRAPH OPERATIONS");
    System.out.println("====================");
    GraphOps.graphBasics();
    GraphOps.graphTraversal();
    GraphOps.graphAlgorithms();
    GraphOps.treeAlgorithms();
    GraphOps.advancedGraphProblems();

    // Dynamic Programming
    System.out.println("\n5. DYNAMIC PROGRAMMING");
    System.out.println("=======================");
    DPOps.dpBasics();
    DPOps.classicDPProblems();
    DPOps.knapsackProblems();
    DPOps.matrixDP();
    DPOps.advancedDP();

    // Search and Sort
    System.out.println("\n6. SEARCH AND SORT ALGORITHMS");
    System.out.println("==============================");
    SearchSortOps.sortingAlgorithms();
    SearchSortOps.searchingAlgorithms();
    SearchSortOps.advancedSearching();
    SearchSortOps.sortingOptimizations();

    // Additional Utility Methods
    System.out.println("\n7. ADDITIONAL UTILITY METHODS");
    System.out.println("=============================");
    other();

  }

  public static void other() {

    System.out.println("------------------------------------------");
    System.out.println("ADDITIONAL UTILITY METHODS");
    System.out.println("---------------------");

    char c = '0';
    String str = "hello";
    Integer num = 5;
    int[] arr = new int[5];
    
    // Character operations
    System.out.println("Character.isDigit('0'): " + Character.isDigit(c));
    System.out.println("Character.isAlphabetic('a'): " + Character.isAlphabetic('a'));
    System.out.println("Character.isUpperCase('A'): " + Character.isUpperCase('A'));
    System.out.println("Character.isLowerCase('a'): " + Character.isLowerCase('a'));
    System.out.println("Character.toUpperCase('a'): " + Character.toUpperCase('a'));
    System.out.println("Character.toLowerCase('A'): " + Character.toLowerCase('A'));

    // String conversions
    System.out.println("Integer.parseInt(\"1234\"): " + Integer.parseInt("1234"));
    System.out.println("Float.parseFloat(\"3.14\"): " + Float.parseFloat("3.14"));
    System.out.println("Double.parseDouble(\"3.14159\"): " + Double.parseDouble("3.14159"));
    System.out.println("Long.parseLong(\"123456789\"): " + Long.parseLong("123456789"));

    // Number conversions
    System.out.println("Integer.toBinaryString(10): " + Integer.toBinaryString(10));
    System.out.println("Integer.toHexString(255): " + Integer.toHexString(255));
    System.out.println("Integer.toOctalString(64): " + Integer.toOctalString(64));
    System.out.println("Integer.toString(123, 2): " + Integer.toString(123, 2)); // Base 2

    // Array conversions
    System.out.println("Arrays.asList(arr): " + Arrays.asList(arr));
    
    // Constants
    System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
    System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
    System.out.println("Long.MIN_VALUE: " + Long.MIN_VALUE);
    System.out.println("Long.MAX_VALUE: " + Long.MAX_VALUE);
    System.out.println("Double.POSITIVE_INFINITY: " + Double.POSITIVE_INFINITY);
    System.out.println("Double.NEGATIVE_INFINITY: " + Double.NEGATIVE_INFINITY);

    // Hash code
    System.out.println("str.hashCode(): " + str.hashCode());
    System.out.println("num.hashCode(): " + num.hashCode());

    // Type conversions
    System.out.println("Integer.valueOf(5): " + Integer.valueOf(5));
    System.out.println("num.intValue(): " + num.intValue());
    System.out.println("num.doubleValue(): " + num.doubleValue());
    System.out.println("num.floatValue(): " + num.floatValue());

    // String operations
    System.out.println("str.length(): " + str.length());
    System.out.println("str.isEmpty(): " + str.isEmpty());
    System.out.println("str.trim(): " + str.trim());
    System.out.println("str.replace('l', 'L'): " + str.replace('l', 'L'));
    System.out.println("str.startsWith(\"he\"): " + str.startsWith("he"));
    System.out.println("str.endsWith(\"lo\"): " + str.endsWith("lo"));

    // Array operations
    System.out.println("Arrays.fill(arr, 1): ");
    Arrays.fill(arr, 1);
    System.out.println("arr: " + Arrays.toString(arr));
    
    System.out.println("Arrays.copyOf(arr, 10): " + Arrays.toString(Arrays.copyOf(arr, 10)));
    System.out.println("Arrays.copyOfRange(arr, 1, 3): " + Arrays.toString(Arrays.copyOfRange(arr, 1, 3)));

    // Collections utility
    List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6);
    System.out.println("Original list: " + list);
    System.out.println("Collections.max(list): " + Collections.max(list));
    System.out.println("Collections.min(list): " + Collections.min(list));
    System.out.println("Collections.frequency(list, 1): " + Collections.frequency(list, 1));
    
    Collections.reverse(list);
    System.out.println("After Collections.reverse(): " + list);
    
    Collections.sort(list);
    System.out.println("After Collections.sort(): " + list);
    
    Collections.shuffle(list);
    System.out.println("After Collections.shuffle(): " + list);

    // Math operations
    System.out.println("Math.abs(-5): " + Math.abs(-5));
    System.out.println("Math.ceil(3.2): " + Math.ceil(3.2));
    System.out.println("Math.floor(3.8): " + Math.floor(3.8));
    System.out.println("Math.round(3.6): " + Math.round(3.6));
    System.out.println("Math.sqrt(16): " + Math.sqrt(16));
    System.out.println("Math.pow(2, 3): " + Math.pow(2, 3));
    System.out.println("Math.log(10): " + Math.log(10));
    System.out.println("Math.log10(100): " + Math.log10(100));
    System.out.println("Math.sin(Math.PI/2): " + Math.sin(Math.PI/2));
    System.out.println("Math.cos(0): " + Math.cos(0));
    System.out.println("Math.tan(Math.PI/4): " + Math.tan(Math.PI/4));

    // Random number generation
    System.out.println("Math.random(): " + Math.random());
    System.out.println("(int)(Math.random() * 100): " + (int)(Math.random() * 100));

    // Bit operations
    System.out.println("5 << 1: " + (5 << 1)); // Left shift
    System.out.println("5 >> 1: " + (5 >> 1)); // Right shift
    System.out.println("5 >>> 1: " + (5 >>> 1)); // Unsigned right shift
    System.out.println("5 & 3: " + (5 & 3)); // AND
    System.out.println("5 | 3: " + (5 | 3)); // OR
    System.out.println("5 ^ 3: " + (5 ^ 3)); // XOR
    System.out.println("~5: " + (~5)); // NOT

    // String formatting
    System.out.println("String.format(\"%.2f\", 3.14159): " + String.format("%.2f", 3.14159));
    System.out.println("String.format(\"%d\", 42): " + String.format("%d", 42));
    System.out.println("String.format(\"%s\", \"hello\"): " + String.format("%s", "hello"));

    // StringBuilder operations
    StringBuilder sb = new StringBuilder();
    sb.append("Hello");
    sb.append(" World");
    sb.insert(5, " Beautiful ");
    System.out.println("StringBuilder: " + sb.toString());
    System.out.println("StringBuilder reverse: " + sb.reverse().toString());

    // Regular expressions
    String text = "Hello123World456";
    System.out.println("text.replaceAll(\"\\\\d+\", \"\"): " + text.replaceAll("\\d+", ""));
    System.out.println("text.replaceAll(\"[a-zA-Z]+\", \"\"): " + text.replaceAll("[a-zA-Z]+", ""));
    
    // Split and join
    String[] words = text.split("\\d+");
    System.out.println("Split by digits: " + Arrays.toString(words));
    System.out.println("Joined with space: " + String.join(" ", words));

  }

}
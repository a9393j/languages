package syntax;

import java.util.*;

class SetOps {

    public static void hashSetOps() {
        System.out.println("------------------------------------------");
        System.out.println("HASHSET OPERATIONS");
        System.out.println("---------------------");

        HashSet<Integer> hashSet = new HashSet<Integer>();
        System.out.println("hashSet.add(1) " + hashSet.add(1));
        System.out.println("hashSet.add(2) " + hashSet.add(2));
        System.out.println("hashSet.add(3) " + hashSet.add(3));
        System.out.println("hashSet.add(4) " + hashSet.add(4));
        System.out.println("hashSet.contains(1) " + hashSet.contains(1));
        System.out.println("hashSet.remove(1) " + hashSet.remove(1));
        System.out.println("hashSet.isEmpty() " + hashSet.isEmpty());

        // Advanced HashSet operations
        System.out.println("hashSet.size() " + hashSet.size());
        System.out.println("hashSet.add(2) " + hashSet.add(2)); // Duplicate, returns false
        
        // HashSet with custom objects
        HashSet<Point> pointSet = new HashSet<>();
        pointSet.add(new Point(1, 2));
        pointSet.add(new Point(3, 4));
        pointSet.add(new Point(1, 2)); // Duplicate
        System.out.println("Point set size: " + pointSet.size());

        // HashSet from collection
        List<Integer> list = Arrays.asList(5, 6, 7, 8);
        HashSet<Integer> hashSetFromList = new HashSet<>(list);
        System.out.println("HashSet from list: " + hashSetFromList);

        // Set operations
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        
        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        
        // Union
        HashSet<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);
        
        // Intersection
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        HashSet<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);

        // HashSet applications
        System.out.println("Remove duplicates from array: " + 
            removeDuplicates(Arrays.asList(1, 2, 2, 3, 3, 4, 5, 5)));
        
        System.out.println("Find missing number: " + 
            findMissingNumber(Arrays.asList(0, 1, 3, 4, 5)));
        
        System.out.println("Check if array contains duplicates: " + 
            containsDuplicate(Arrays.asList(1, 2, 3, 1)));

        /**
         * - HashSet does not allow duplicate elements.
         * - HashSet allows only one null element.
         * - The elements are inserted in random order in a HashSet.
         * - A HashSet is internally backed by a HashMap.
         * - initial capacity of 16 and a load factor of 0.75
         */
    }

    public static void treeSetOps() {
        System.out.println("------------------------------------------");
        System.out.println("TREESET OPERATIONS");
        System.out.println("---------------------");

        /**
         * - TreeSet does not allow duplicate elements.
         * - TreeSet class doesn't allow null elements.
         * - Since elements are stored in a tree, the access and retrieval times are
         * quite fast in a TreeSet.
         * - The elements are stored in ascending order in a TreeSet.
         */
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        System.out.println("treeSet.add(1) " + treeSet.add(1));
        System.out.println("treeSet.add(2) " + treeSet.add(2));
        System.out.println("treeSet.add(3) " + treeSet.add(3));
        System.out.println("treeSet.add(4) " + treeSet.add(4));
        System.out.println("treeSet.add(5) " + treeSet.add(5));
        System.out.println("treeSet.add(6) " + treeSet.add(6));
        System.out.println("treeSet.contains(1) " + treeSet.contains(1));
        System.out.println("treeSet.size() " + treeSet.size());
        System.out.println("treeSet.remove(1) " + treeSet.remove(1));
        System.out.println("treeSet.first() " + treeSet.first()); // Fetching the first element in TreeSet
        System.out.println("treeSet.last() " + treeSet.last()); // Fetching the last element in TreeSet

        // Fetching all the elements less than 4 | 4 is not inclusive.
        System.out.println("treeSet.headSet(4) " + treeSet.headSet(4));
        // Fetching all the elements greater than 4 | 4 is inclusive.
        System.out.println("treeSet.tailSet(4) " + treeSet.tailSet(4));

        // Advanced TreeSet operations
        System.out.println("treeSet.lower(4) " + treeSet.lower(4)); // Greatest element < 4
        System.out.println("treeSet.higher(4) " + treeSet.higher(4)); // Smallest element > 4
        System.out.println("treeSet.floor(4) " + treeSet.floor(4)); // Greatest element <= 4
        System.out.println("treeSet.ceiling(4) " + treeSet.ceiling(4)); // Smallest element >= 4

        // TreeSet with custom comparator
        TreeSet<String> stringTreeSet = new TreeSet<>((a, b) -> Integer.compare(a.length(), b.length()));
        stringTreeSet.add("apple");
        stringTreeSet.add("banana");
        stringTreeSet.add("cherry");
        stringTreeSet.add("date");
        System.out.println("String TreeSet by length: " + stringTreeSet);

        // TreeSet applications
        System.out.println("Find Kth largest element: " + 
            findKthLargest(Arrays.asList(3, 2, 1, 5, 6, 4), 2));
        
        System.out.println("Find median from data stream: " + 
            findMedian(Arrays.asList(1, 2, 3, 4, 5, 6)));

        System.out.println("treeSet.clear() ");
        treeSet.clear();
        System.out.println("treeSet.isEmpty() " + treeSet.isEmpty());
    }

    public static void linkedHashSetOps() {
        System.out.println("------------------------------------------");
        System.out.println("LINKEDHASHSET OPERATIONS");
        System.out.println("---------------------");

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(3);
        linkedHashSet.add(1);
        linkedHashSet.add(4);
        linkedHashSet.add(2);
        linkedHashSet.add(5);
        
        System.out.println("LinkedHashSet maintains insertion order: " + linkedHashSet);
        
        // Remove and add to see order preservation
        linkedHashSet.remove(1);
        linkedHashSet.add(1);
        System.out.println("After remove and add: " + linkedHashSet);
        
        // LinkedHashSet applications
        System.out.println("Remove duplicates maintaining order: " + 
            removeDuplicatesMaintainOrder(Arrays.asList(1, 2, 2, 3, 3, 4, 5, 5)));
    }

    public static void setAlgorithms() {
        System.out.println("------------------------------------------");
        System.out.println("SET ALGORITHMS");
        System.out.println("---------------------");

        // Set intersection algorithm
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Set union algorithm
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);
        
        // Set difference algorithm
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);
        
        // Symmetric difference
        Set<Integer> symmetricDiff = new HashSet<>(union);
        symmetricDiff.removeAll(intersection);
        System.out.println("Symmetric difference: " + symmetricDiff);
        
        // Subset and superset checks
        Set<Integer> subset = new HashSet<>(Arrays.asList(1, 2));
        System.out.println("Is subset: " + set1.containsAll(subset));
        System.out.println("Is superset: " + subset.containsAll(set1));
        
        // Disjoint sets
        Set<Integer> disjointSet = new HashSet<>(Arrays.asList(9, 10));
        System.out.println("Are disjoint: " + Collections.disjoint(set1, disjointSet));
    }

    public static void setApplications() {
        System.out.println("------------------------------------------");
        System.out.println("SET APPLICATIONS");
        System.out.println("---------------------");

        // Find all unique characters in a string
        String str = "hello world";
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : str.toCharArray()) {
            uniqueChars.add(c);
        }
        System.out.println("Unique characters in '" + str + "': " + uniqueChars);
        
        // Find common elements in multiple arrays
        List<Integer> arr1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> arr2 = Arrays.asList(4, 5, 6, 7, 8);
        List<Integer> arr3 = Arrays.asList(5, 6, 7, 8, 9);
        
        Set<Integer> common = new HashSet<>(arr1);
        common.retainAll(arr2);
        common.retainAll(arr3);
        System.out.println("Common elements: " + common);
        
        // Check if array contains all elements of another array
        List<Integer> mainArray = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> subArray = Arrays.asList(2, 4, 6, 8);
        
        Set<Integer> mainSet = new HashSet<>(mainArray);
        Set<Integer> subSet = new HashSet<>(subArray);
        System.out.println("Main array contains all elements of sub array: " + mainSet.containsAll(subSet));
        
        // Find elements that appear only once
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 5, 6);
        Set<Integer> allNumbers = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        
        for (int num : numbers) {
            if (!allNumbers.add(num)) {
                duplicates.add(num);
            }
        }
        
        Set<Integer> uniqueNumbers = new HashSet<>(allNumbers);
        uniqueNumbers.removeAll(duplicates);
        System.out.println("Elements appearing only once: " + uniqueNumbers);
    }

    // Helper methods for applications
    public static List<Integer> removeDuplicates(List<Integer> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    public static List<Integer> removeDuplicatesMaintainOrder(List<Integer> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public static int findMissingNumber(List<Integer> nums) {
        Set<Integer> set = new HashSet<>(nums);
        for (int i = 0; i <= nums.size(); i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean containsDuplicate(List<Integer> nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static int findKthLargest(List<Integer> nums, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > k) {
                set.pollFirst();
            }
        }
        return set.first();
    }

    public static double findMedian(List<Integer> nums) {
        TreeSet<Integer> left = new TreeSet<>(Collections.reverseOrder());
        TreeSet<Integer> right = new TreeSet<>();
        
        for (int num : nums) {
            if (left.isEmpty() || num <= left.first()) {
                left.add(num);
            } else {
                right.add(num);
            }
            
            // Balance the sets
            if (left.size() > right.size() + 1) {
                right.add(left.pollFirst());
            } else if (right.size() > left.size()) {
                left.add(right.pollFirst());
            }
        }
        
        if (left.size() == right.size()) {
            return (left.first() + right.first()) / 2.0;
        } else {
            return left.first();
        }
    }

    // Custom Point class for HashSet demonstration
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}

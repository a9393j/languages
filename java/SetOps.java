
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

class SetOps {

    public static void hashSetOps() {

        System.out.println("---------------------");
        System.out.println("HASHSET OPERATIONS");
        System.out.println("---------------------");

        HashSet<Integer> hashSet = new HashSet<Integer>();
        System.out.println("hashSet.add(1) " + hashSet.add(1));
        System.out.println("hashSet.add(2) " + hashSet.add(2));
        System.out.println("hashSet.add(3) " + hashSet.add(3));
        System.out.println("hashSet.add(4) " + hashSet.add(4));
        System.out.println("hashSet.contains(1) " + hashSet.contains(1));
        System.out.println("hashSet.remove(1) " + hashSet.remove(1));
        // System.out.println("hashSet.clear()");
        // hashSet.clear();
        System.out.println("hashSet.isEmpty()" + hashSet.isEmpty());

        /**
         * SORTING : NOT POSSIBLE IN HASHSET
         * Since a HashSet stores the elements in random order, it is not possible
         * to store the elements in a HashSet in sorted order. If we want to sort
         * the elements of a HashSet, then we should convert it into some other
         * Collection such as a List,TreeSet, or LinkedHashSet.
         */

        // Creating an ArrayList from existing set.
        List<Integer> list = new ArrayList<>(hashSet);
        // Sorting the list.
        Collections.sort(list);

        // Printer.print(list);

        /**
         * - HashSet does not allow duplicate elements.
         * - HashSet allows only one null element.
         * - The elements are inserted in random order in a HashSet.
         * - A HashSet is internally backed by a HashMap.
         * - initial capacity of 16 and a load factor of 0.75
         */

    }

    public static void treeSetOps() {

        System.out.println("---------------------");
        System.out.println("TREESET OPERATIONS");
        System.out.println("---------------------");

        /**
         * - TreeSet does not allow duplicate elements.
         * - TreeSet class doesn’t allow null elements.
         * - Since elements are stored in a tree, the access and retrieval times are
         * quite fast in a TreeSet.
         * - The elements are stored in ascending order in a TreeSet.
         * 
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

        System.out.println("treeSet.clear() ");
        treeSet.clear();
        System.out.println("treeSet.isEmpty() " + treeSet.isEmpty());

    }

}

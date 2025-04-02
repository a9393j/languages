package syntax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ListOps {

    public static void listOps() {

        //sorting vert Important
        Collections.sort(people, (p1, p2) -> Integer.compare(p1.age, p2.age));

       // In Java, arrays (not Lists) can be sorted using the Arrays.sort() method.
        Arrays.sort(people, Comparator.comparingInt((Person p) -> p.age).reversed());
        /**
         * - List indexes start from ‘0’, just like array index.
         * - List allows “null”
         * - List supports Generics and we should use it whenever possible. Using
         * Generics with List will avoid ClassCastException at runtime.
         */

        System.out.println("------------------------------------------");
        System.out.println("LIST OPERATIONS");
        System.out.println("---------------------");

        List<Integer> list = new ArrayList<Integer>(); // Empty Constructor

        // printSize(list);
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
        // System.out.println("list.add(2,0): " + list.add(0, 2));
        System.out.println("list.addAll(2): " + list.addAll(new ArrayList<Integer>(list.subList(0, 2))));

        System.out.println("Collections.sort(list)");
        Collections.sort(list);
        Printer.print(list);

        System.out.println("Collections.sort(list, Collections.reverseOrder());");
        Collections.sort(list, Collections.reverseOrder());
        Printer.print(list);

        System.out.println("list.clear(): ");
        list.clear(); // remove all element from the list


        public Optional<Cab> mathCabToRider {
            return Candiadate.stream.filter(cab->cab.getCurrentTrip()==null).findAny();
    }

        Here Optional means it will return only one cab but filter method returns a list of cabs. so to stop at the first matching position and to just return only one 
            object we need to add findAny()  here

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

    }

    public static void linkedListOps() {

        System.out.println("------------------------------------------");
        System.out.println("LINKEDLIST OPERATIONS");
        System.out.println("---------------------");

        // CREATTION
        List<Integer> list = new LinkedList<Integer>();
        // List<Integer> list = new LinkedList<Integer>(oldList);
        // ADD ELEMENTS
        list.add(0, null);
        list.add(0, 1);
        list.add(2);
        list.add(3);
        // list.addAll(Collection c);
        // list.addAll(0, Collection c);

        // list.get();
        // REMOVING
        list.remove(1);
        // list.removeLast();
        // removeLast();
        // remove(int index)
        // remove(E element) // first occurrence is removed.
        // removeLastOccurrence(E element)
        // SORT
        // Collections.sort(linkedList);

    }

    public static void priorityQueueOps() {

        System.out.println("------------------------------------------");
        System.out.println("PRIORITY QUEUE OPERATIONS");
        System.out.println("---------------------");

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        // (max elements on top/descending order)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);

        // Let’s say you want your own comparator, example, the pq has keys of a hashmap
        // as it’s elements, and they need to be arranged according to their values in a
        // hashmap.

        // pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        // You can define your own comparator separately( named MyComparator for
        // example):

        // pq = new PriorityQueue<>(new MyComparator());
        final Comparator<Map.Entry<String, Amount>> ascending = Comparator.comparingDouble(balance -> balance.getValue().getAmount());
        final PriorityQueue<Map.Entry<String, Amount>>
                positiveAmounts = new PriorityQueue<>(ascending.reversed()),
                negativeAmounts = new PriorityQueue<>(ascending);

         graph.get(largestPositive.getKey()).getBalances()
                    .put(largestNegative.getKey(), new Amount(USD, Math.min(positiveAmount, negativeAmount)));

        positiveAmounts.add(new AbstractMap.SimpleEntry<>(largestPositive.getKey(), remainingAmount));
        negativeAmounts.add(new AbstractMap.SimpleEntry<>(largestNegative.getKey(), remainingAmount));

    }

    public static void dequeOps() {

        System.out.println("------------------------------------------");
        System.out.println("DEQUE OPERATIONS");
        System.out.println("---------------------");

    }

    public static void randomOps() {

        System.out.println("------------------------------------------");
        System.out.println("RANDOM OPERATIONS");
        System.out.println("---------------------");

    }

}

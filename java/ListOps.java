
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOps {

    public static void listOps() {
        /**
         * - List indexes start from ‘0’, just like array index.
         * - List allows “null”
         * - List supports Generics and we should use it whenever possible. Using
         * Generics with List will avoid ClassCastException at runtime.
         */

        System.out.println("---------------------");
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

    }

}

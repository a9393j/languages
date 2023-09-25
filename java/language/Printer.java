package language;

import java.util.List;

class Printer {

    public static <T> void printSize(List<T> list) {
        System.out.println("---------------------");
        System.out.println("size of the list:" + list.size());
        System.out.println("---------------------");
    }

    public static <T> void print(List<T> list) {
        System.out.println("---------------------");
        System.out.print("List -> ");

        for (T item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("---------------------");
    }

}

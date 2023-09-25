
import java.util.*;

// https://satishkathiriya99.medium.com/java-cheat-sheet-287da6e15e96
class JavaCollections {

  public static void main(String[] args) {

    System.out.println("------------------------------------------");
    System.out.println("Collections Testing");

    // operations on List
    System.out.println("------------------------------------------");
    ListOps.listOps();

    // operations on Hashset
    System.out.println("------------------------------------------");
    SetOps.hashSetOps();

    // operations on TreeSet
    System.out.println("------------------------------------------");
    SetOps.treeSetOps();

    // operations on String
    System.out.println("------------------------------------------");
    StringOps.stringOps();

    // operations on HashMap
    System.out.println("------------------------------------------");
    MapOps.hashMapOps();

    // operations on TreeMap
    System.out.println("------------------------------------------");
    MapOps.treeMapOps();

    // operations on Stack
    System.out.println("------------------------------------------");
    ListOps.stackOps();

    // operations on Queue
    System.out.println("------------------------------------------");
    ListOps.queueOps();

    // operations on Deque
    System.out.println("------------------------------------------");
    ListOps.dequeOps();

    // operations on priorityQueue
    System.out.println("------------------------------------------");
    ListOps.priorityQueueOps();

    // operations on LinkedList
    System.out.println("------------------------------------------");
    ListOps.linkedListOps();

  }

  public static void other() {

    char c = '0';
    String str = "hello";
    Integer num = 5;
    int[] arr = new int[5];
    Character.isDigit(c); // returns bool
    Character.isAlphabetic(c);
    Integer.parseInt(str); // if str = “1234”, it returns integer 1234.
    Float.parseFloat(str); // same as above for floats
    Integer.toBinaryString(num); // returns binary representation of num, input = 10 returns “1010”
    Arrays.asList(arr); // converts array to list
    // Integer.MIN_VALUE //returns least possible int in Java
    // object.hashCode(); //returns hashcode value for object
    // IntegerList.get(i).intValue(); // to convert Integer to int;
  }

}
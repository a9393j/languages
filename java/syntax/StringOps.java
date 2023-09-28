package syntax;

public class StringOps {

    public static void stringOps() {

        System.out.println("------------------------------------------");
        System.out.println("STRING OPERATIONS");
        System.out.println("---------------------");

        String s = "Hello";

        System.out.println("s.toCharArray(): " + s.toCharArray());
        System.out.println("s.indexOf(\"llo\"): " + s.indexOf("llo"));
        System.out.println("s.charAt(1): " + s.charAt(1));
        System.out.println("s.toUpperCase(): " + s.toUpperCase());

        String s1 = "Hello";
        String s2 = "Hello";

        // Note to self: DON’T DO “==” AGAIN AND WONDER WHY THE ANSWER IS CONSTANTLY
        // INCORRECT

        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s1.equalsIgnoreCase(s2): " + s1.equalsIgnoreCase(s2));

        // returns s1-s2, in dictionary order so a-b returns -1
        System.out.println("s1.compareTo(s2): " + s1.compareTo(s2));
        System.out.println("s1.contains(s2): " + s1.contains(s2));

        System.out.println("s.length(): " + s.length());

        // incl: inclusive index, included, excl: exclusive
        System.out.println("s.substring(0, 2): " + s.substring(0, 2));

        // index, excluded
        // s.split(“ “);//return string array separated by spaces. “Hello
        // world” returns [“Hello”,”world”]

    }

}

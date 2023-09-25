public class StringOps {

    public static void stringOps() {
        String s = "Hello";

        s.toCharArray();
        s.indexOf("llo");
        s.charAt(1);
        s.toUpperCase();

        String s1 = "Hello";
        String s2 = "Hello";
        // Note to self: DON’T DO “==” AGAIN AND WONDER WHY THE ANSWER IS CONSTANTLY
        // INCORRECT
        s1.equals(s2);
        s1.equalsIgnoreCase(s2);
        s1.compareTo(s2); // returns s1-s2, in dictionary order so a-b returns -1
        s1.contains(s2);
        s.length();

        s.substring(0, 2); // incl: inclusive index, included, excl: exclusive
        // index, excluded
        // s.split(“ “);//return string array separated by spaces. “Hello
        // world” returns [“Hello”,”world”]

    }

}

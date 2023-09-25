import java.util.HashMap;

public class MapOps {

    public static void hashMapOps() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        map.putIfAbsent("E", 5);
        map.putIfAbsent("E", 6);

        map.get("E");
        map.getOrDefault("F", 0);
        map.remove("E");

        map.size();

        map.containsKey("E");
        map.containsValue(2);

    }

    public static void treeMapOps() {

    }

}

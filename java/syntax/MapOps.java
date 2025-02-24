package syntax;

import java.util.HashMap;

public class MapOps {

    public static void hashMapOps() {

        System.out.println("------------------------------------------");
        System.out.println("HASHMAP OPERATIONS");
        System.out.println("---------------------");

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        map.putIfAbsent("E", 5);
        map.putIfAbsent("E", 6);

        map.get("E");
        map.getOrDefault("F", 0);
                cartItems.put(product, cartItems.getOrDefault(product,0) + quantity); //useful method to update values at map when you are ensure if it exists or not

        // to remove a specific key from map
        map.remove("E");
        cartItems.remove(product);

        map.size();

        map.containsKey("E");
        map.containsValue(2);


        resultBalances.put(user, resultBalances.getOrDefault(user, NIL).add(amount));
        
        //This method checks if the resultBalances map already contains a balance for the given user.
        //If there is already an entry for the user, it returns the existing Amount associated with that user.
        //If there is no existing entry for the user, it returns a default value, which in this case is NIL.

        //to iterate over map we need entrySet()
        he entrySet() method is used here to iterate over the key-value pairs (entries) in a Map. 

        //HashMap and ConcurrentHashMap are both implementations of the Map interface in Java,
        //but they serve different purposes and have different characteristics, especially in the context of concurrency
          Map<Integer,String> map = new HashMap<>();
             for (Map.Entry<Integer, String> entry :map.entrySet()) 
        {
            Integer key = (Integer)entry.getKey();
            String value = entry.getValue();
 
            System.out.println(key + "=" + value);
        }

        //Here is the other way to iterate over map using forEach method
        cartItems.forEach((product, quantity) -> {
            System.out.println(product.name + " x " + quantity);
        });

        cartItems.values().removeIf(p->p.getId().equals(Id));
       
       Optional<String> cab = MatchingStrategy.match(to,from,currentLocation,rider);

        //here because of using Optional it doesn't throw null exception
    
    

    }

    public static void treeMapOps() {

        System.out.println("------------------------------------------");
        System.out.println("TREEMAP OPERATIONS");
        System.out.println("---------------------");

    }

}

package collections;

import java.util.HashMap;
import java.util.Map;

public class CollectionsPart7 {
    /*
        Map:
            - A Map is an object that maps keys to values.
            - A map cannot contain duplicate keys: Each key can map to at most one value.

        Methods
            - Basic operations: put, get, remove, containsKey, containsValue, size, and empty
            - Bulk operations:  putAll and clear, and
            - Collection views (such as keySet, entrySet, and values

        Implementations:
            - Three general-purpose Map implementations: HashMap, TreeMap, and LinkedHashMap.
            - Their behavior and performance are precisely analogous to HashSet, TreeSet, and LinkedHashSet,

     */
    public void mapDemo() {
        Map<Integer, String> idToName = new HashMap<>();
        idToName.put(2, "Harry");
        idToName.put(1, "Ron");
        idToName.put(3, "Hermoine");
        idToName.put(5, "Harry");
        idToName.put(4, "Dumbledore");
        idToName.put(4, "Hagrid");  // This will replace dumbledore

        System.out.println("Name with id 4: " + idToName.get(4));
        System.out.println("Id 8 exists: " + idToName.containsKey(8));
        System.out.println("Name Harry exists: " + idToName.containsValue("Harry"));

        // Keyset and Entryset
        System.out.println("Map Entries:");
        for (Integer key : idToName.keySet()) {
            System.out.println("Key: " + key + ", Value: " + idToName.get(key));
        }

        System.out.println("Map Entries:");
        for (Map.Entry<Integer, String> entry : idToName.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

    }
}

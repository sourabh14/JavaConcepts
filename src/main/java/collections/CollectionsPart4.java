package collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionsPart4 {
    /*
        Set
            - A Set is a Collection that cannot contain duplicate elements.
            - The Set interface contains only methods inherited from Collection

        Set implementations
            - three general-purpose Set implementations: HashSet, TreeSet, and LinkedHashSet
            -  HashSet, which stores its elements in a hash table, is the best-performing implementation;
                - however it makes no guarantees concerning the order of iteration.
            - TreeSet, which stores its elements in a red-black tree, orders its elements based on their values; it is
                substantially slower than HashSet.
            - LinkedHashSet, which is implemented as a hash table with a linked list running through it, orders its
                elements based on the order in which they were inserted into the set (insertion-order).
                - LinkedHashSet spares its clients from the unspecified, generally chaotic ordering provided by
                HashSet at a cost that is only slightly higher

     */
    public void setDemo() {
        Set<String> names = new HashSet<>();

        addToSet(names, "News");
        addToSet(names, "Hello");
        addToSet(names, "World");
        addToSet(names, "Hello");


        System.out.print("Names: ");
        for (String name : names) {
            System.out.print(name + ", ");
        }
        System.out.println("");

        Set<String> names2 = new HashSet<>();
        names2.add("World");
        names2.add("News");

        System.out.println("names2: " + names2);
        System.out.println("names collection contains all elements of names2: " + names.containsAll(names2));

        names.removeAll(names2);
        System.out.println("After removing names2 from names: " + names);
    }

    public void addToSet(Set s, String val) {
        if (s.add(val)) {
            System.out.println(val + " was added to set");
        }
        else {
            System.out.println(val + " is duplicate. Not added to set");
        }
    }

}

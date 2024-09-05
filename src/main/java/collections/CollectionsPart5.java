package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsPart5 {
    /*
        The List Interface
            - A List is an ordered Collection.
            - Lists may contain duplicate elements.
            - In addition to the operations inherited from Collection, the List interface includes operations for
                the following:
                Positional access — manipulates elements based on their numerical position in the list. This includes
                    methods such as get, set, add, addAll, and remove.
                Search — searches for a specified object in the list and returns its numerical position. Search methods
                    include indexOf and lastIndexOf.
                Iteration — extends Iterator semantics to take advantage of the list's sequential nature. The
                    listIterator methods provide this behavior.
                Range-view — The sublist method performs arbitrary range operations on the list.

        Implementations
            - The Java platform contains two general-purpose List implementations. ArrayList, which is usually the
                better-performing implementation, and LinkedList which offers better performance under certain circumstances.
     */
    public void listDemo() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(4);
        integerList.add(2);
        integerList.add(8);
        integerList.add(2);
        integerList.add(9);
        System.out.println("integerList = " + integerList);

        // get and set
        System.out.println("2nd element : " + integerList.get(1));
        System.out.println("3rd element : " + integerList.get(2));
        System.out.println("setting 3rd element as 7");
        integerList.set(2, 7);
        System.out.println("integerList = " + integerList);

        // indexOf - returns the first index of given element
        System.out.println("index of 2 : " + integerList.indexOf(2));

        Collections.sort(integerList);
        System.out.println("integerList = " + integerList);

        // sublist - (fromIndex, toIndex) gives arraylist of range [fromIndex, toIndex-1]
        System.out.println("Sublist [1-4] : " + integerList.subList(1, 4));
    }
}

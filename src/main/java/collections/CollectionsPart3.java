package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionsPart3 {
    /*
        Interfaces:
            - The core collection interfaces encapsulate different types of collections (see Core-collection-interfaces.png)
            - These interfaces allow collections to be manipulated independently of the details of their representation.
            - The hierarchy consists of two distinct trees — a Map is not a true Collection.

        Collection — the root of the collection hierarchy. A collection represents a group of objects known as its elements.

        List — an ordered collection (sometimes called a sequence). Lists can contain duplicate elements.
            - The user of a List generally has precise control over where in the list each element is inserted
                and can access elements by their integer index (position).

        Set — a collection that cannot contain duplicate elements.

        SortedSet — a Set that maintains its elements in ascending order.

        Queue — a collection used to hold multiple elements prior to processing. Besides basic Collection operations,
            a Queue provides additional insertion, extraction, and inspection operations.

        Deque — a collection used to hold multiple elements prior to processing. Besides basic Collection operations,
            a Deque provides additional insertion, extraction, and inspection operations.
            - Deques can be used both as FIFO (first-in, first-out) and LIFO (last-in, first-out). In a deque all
                new elements can be inserted, retrieved and removed at both ends.

        Map — an object that maps keys to values. A Map cannot contain duplicate keys; each key can map to at most one value.

        SortedMap — a Map that maintains its mappings in ascending key order.

        [see Collection-properties.png]
    */

    /*
        The Collection Interface
            - The Collection interface contains methods that perform basic operations, such as
                int size(),
                boolean isEmpty(),
                boolean contains(Object element),
                boolean add(E element),
                boolean remove(Object element), and
                Iterator<E> iterator().

            - It also contains methods that operate on entire collections (Bulk operations ), such as
                boolean containsAll(Collection<?> c) - returns true if the target Collection contains all the elements in the specified Collection.
                boolean addAll(Collection<? extends E> c) - adds all the elements in the specified Collection to the target Collection
                boolean removeAll(Collection<?> c) - removes from the target Collection all of its elements that are also contained in the specified Collection
                boolean retainAll(Collection<?> c) - emoves from the target Collection all its elements that are not also contained in the specified Collection. That is, it retains only those elements in the target Collection that are also contained in the specified Collection.
                void clear() - removes all elements from the Collection

            - Additional methods
                Object[] toArray() and <T> T[] toArray(T[],
                Stream<E> stream() and Stream<E> parallelStream(),
     */

    public void collectionsInterfaceDemo() {
        Collection<String> c1 = new ArrayList<>();
        Collection<Integer> c2 = new ArrayList<>();
        // add method
        c1.add("Hello");
        c1.add("World");
        c1.add("There");

        System.out.println("Collection c1: " + c1);

        // size
        System.out.println("Size of c1 collection: " + c1.size());

        // isEmpty
        System.out.println("Collection c1 is empty: " + c1.isEmpty());
        System.out.println("Collection c2 is empty: " + c2.isEmpty());

        // contains
        System.out.println("Collection c1 contains 'World' : " + c1.contains("World"));
        System.out.println("Collection c1 contains 'News' : " + c1.contains("News"));

        // remove
        c1.remove("World");
        System.out.println("Collection c1 contains 'World' : " + c1.contains("World"));

        // iterator
        System.out.println("Iterator demo: ");
        Iterator<String> c1iterator = c1.iterator();
        while (c1iterator.hasNext()) {
            System.out.println(c1iterator.next());
        }

        // for each
        System.out.println("For each demo: ");
        for (String s : c1) {
            System.out.println(s);
        }
    }
}

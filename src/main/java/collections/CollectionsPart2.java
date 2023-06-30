package collections;

import java.util.ArrayList;

public class CollectionsPart2 {
        /*
            ArrayList:
                - Resizable-array implementation of the List interface.

                Capacity:
                    - Each ArrayList instance has a capacity. The capacity is the size of the array used to store the
                        elements in the list.
                    - It is always at least as large as the list size. As elements are added to an ArrayList, its
                        capacity grows automatically.
                    - An application can increase the capacity of an ArrayList instance before adding a large number of
                        elements using the ensureCapacity operation. This may reduce the amount of incremental reallocation.

                Time Complexity of operations:
                    - The size, isEmpty, get, set, iterator, and listIterator operations run in constant time.
                    - The add operation runs in amortized constant time, that is, adding n elements requires O(n) time.
                    - All the other operations run in linear time (roughly speaking).
                    - The constant factor is low compared to that for the LinkedList implementation.

                Un-synchronized:
                    - This implementation is not synchronized. If multiple threads access an ArrayList instance
                        concurrently, and at least one of the threads modifies the list structurally, it must be
                        synchronized externally. (A structural modification is any operation that adds or deletes
                        one or more elements, or explicitly resizes the backing array; merely setting the value of
                        an element is not a structural modification.)
                    - This is typically accomplished by synchronizing on some object that naturally encapsulates the
                        list. If no such object exists, the list should be "wrapped" using the Collections.synchronizedList
                        method. This is best done at creation time, to prevent accidental un-synchronized access to the list:

                        List list = Collections.synchronizedList(new ArrayList(...));
         */
        public void arrayListDemo() {
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i=0; i<5; i++) {
                arrayList.add("element-" + i);
            }
            System.out.println("4th element: " + arrayList.get(3));
            System.out.println("arrayList = " + arrayList);
        }
}

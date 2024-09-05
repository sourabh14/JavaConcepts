package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsPart10 {
    /*
        java.util.Collections Class - Algorithms
            - Collections class provides polymorphic algorithms which are pieces of reusable functionality.
            - all take the form of static methods whose first argument is the collection on which the operation is to be performed.
            - The great majority of the algorithms provided by the Java platform operate on List instances, but a few of them operate on arbitrary Collection instances.
     */

    public void collectionsClassDemo() {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(5, 2, 8, 22, 3, 1, 23, 2, 5, 2));
        System.out.println("integerList = " + integerList);

        // Sorting
        /*
            The sort operation uses a slightly optimized merge sort algorithm that is fast and stable:
                - Fast: It is guaranteed to run in n log(n) time and runs substantially faster on nearly sorted lists.
                    Empirical tests showed it to be as fast as a highly optimized quicksort. A quicksort is generally
                    considered to be faster than a merge sort but isn't stable and doesn't guarantee n log(n) performance.
                - Stable: It doesn't reorder equal elements. This is important if you sort the same list repeatedly on
                    different attributes. If a user of a mail program sorts the inbox by mailing date and then sorts it
                    by sender, the user naturally expects that the now-contiguous list of messages from a given sender
                    will (still) be sorted by mailing date. This is guaranteed only if the second sort was stable.

         */
        Collections.sort(integerList);
        System.out.println("sorted integerList = " + integerList);

        // Binary search
        // This form assumes that the List is sorted in ascending order according to the natural ordering of its elements. T
        // If the list contains multiple elements equal to the specified object, there is no guarantee which one will be found.
        Integer index = Collections.binarySearch(integerList, 3);
        System.out.println("index of 3 = " + index);

        // Shuffle
        Collections.shuffle(integerList);
        System.out.println("shuffled integerList = " + integerList);

        // Reverse
        Collections.reverse(integerList);
        System.out.println("reversed integerList = " + integerList);

        // Swap
        Collections.swap(integerList, 1, 3);
        System.out.println("index 1 and 3 swapped integerList = " + integerList);

        // Frequency
        System.out.println("Frequency of 2 = " + Collections.frequency(integerList, 2));

        // Min max
        System.out.println("Min: " + Collections.min(integerList) + ", Max: " + Collections.max(integerList));

        Collections.fill(integerList, 0);
        System.out.println("0 fill integerList = " + integerList);

        List<String> col1 = new ArrayList<>();
        Set<String> union, intersection;
        col1.add("a");
        col1.add("b");
        col1.add("c");
        System.out.println("col1 = " + col1);

        List<String> col2 = new ArrayList<>();
        col2.add("b");
        col2.add("c");
        col2.add("d");
        col2.add("e");
        System.out.println("col2 = " + col2);

        // Union and intersection
        union = new HashSet<>(col1);
        union.addAll(col2);
        intersection = new HashSet<>(col1);
        intersection.retainAll(col2);
        System.out.println("Union of col1 and col2: " + union);
        System.out.println("Intersection of col1 and col2: " + intersection);

    }
}

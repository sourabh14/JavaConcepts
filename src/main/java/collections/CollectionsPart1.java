package collections;

import java.util.Arrays;
import java.util.List;

public class CollectionsPart1 {
    /*
        Collections Framework, Arrays Class

        Collections:
            - A collection — sometimes called a container — is simply an object that groups multiple elements into a single unit.
            - Collections are used to store, retrieve, manipulate, and communicate aggregate data.

        Collections Framework
            - A collections framework is a unified architecture for representing and manipulating collections. All
            collections frameworks contain the following:
                - Interfaces: These are abstract data types that represent collections. Interfaces allow collections to
                    be manipulated independently of the details of their representation.
                - Implementations: These are the concrete implementations of the collection interfaces. In essence,
                they are reusable data structures.
                - Algorithms: These are the methods that perform useful computations, such as searching and sorting,
                on objects that implement collection interfaces.



     */
    public void ArraysDemo() {
        /*
            Java Arrays and Arrays Class

            Arrays Class
                - This class contains various methods for manipulating arrays (such as sorting and searching).
                - This class provides static methods to dynamically create and access Java arrays.
         */
        int[] array = new int[10];
        int[] array2 = {24, 93, 93, 51, 85, 99, 26, 84, 63, 29};

        populateArray(array);
        printArray(array, "array");

        // We can create both primitive and object arrays
        String[] stringArray = new String[10];

        /* asList -
            - Using this method, we can convert from an array to a fixed-size List object.
            - Only Object type arrays can be converted to list (not primitive type - int[] cannot be converted to list)
            - This List is just a wrapper that makes the array available as a list. No data is copied or created.
            - However, we can modify single items inside the array. Note that all the modifications we make to the
                single items of the List will be reflected in our original array:
         */

        List<String> list = Arrays.asList(stringArray);
        list.set(2, "16");
        System.out.println("list = " + list);

        // Sort
        //  - The sorting algorithm is a Dual-Pivot Quicksort -  O(n log(n)) performance
        Arrays.sort(array2);
        printArray(array2, "sorted array");

        // Binary search
        //  The array must be sorted (as by the sort(int[]) method) prior to making this call.
        //  If the array contains multiple elements with the specified value, there is no guarantee which one will be found.
        //  Returns index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1).
        int index = Arrays.binarySearch(array2, 51);
        System.out.println("Binary search - Found 51 at index: " + (index >= 0 ? index : "Not found"));
        index = Arrays.binarySearch(array2, 31);
        System.out.println("Binary search - Found 31 at index: " + (index >= 0 ? index : "Not found"));

        // Fill an array with a particular value.
        Arrays.fill(array, 3);
        printArray(array, "array");

    }

    private void populateArray(int[] array) {
        for (int i=0; i<array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
    }

    private void printArray(int[] array, String arrayName) {
        System.out.print(arrayName + ": ");
        for (int i = 0; i< array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("");
    }
}

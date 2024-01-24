package collections;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class CollectionsRevisionTest {

    public String getElements(int[] arr) {
        StringBuilder ret = new StringBuilder();
        ret.append("[");
        for (int i : arr) {
            ret.append(i).append(", ");
        }
        ret.append("]");
        return ret.toString();
    }

    private void populateArrayWithRandomValues(int[] array) {
        for (int i=0; i<array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
    }

    @Test
    void arraysDemo() {
        int[] arr = new int[5];
        System.out.println("array length: " + arr.length);
        System.out.println("array elements: " + getElements(arr));
        System.out.println("array elements: " + Arrays.toString(arr));

        int[] arr2 = {5, 2, 3};
        System.out.println("array elements: " + getElements(arr2));
    }
}

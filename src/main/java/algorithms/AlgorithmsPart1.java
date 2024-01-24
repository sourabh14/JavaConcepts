package algorithms;

import java.net.Inet4Address;
import java.util.Arrays;

public class AlgorithmsPart1 {
    /*
        Sort - Bubble, Insertion, Selection,
         - Merge, Heap, Quick,
         - Counting, Radix, Bucket

        Binary search

     */

    public void execute() {
        int[] arr1 = {4,6,1,9,4,6,3};
        System.out.println("Bubble sort");
        System.out.println("arr1 = " + Arrays.toString(arr1));

        /*
            Bubble Sort: repeatedly swapping the adjacent elements if they are in the wrong order.
                - Time Complexity: O(N2)
                - Auxiliary Space: O(1)
                - It is a stable sorting algorithm, meaning that elements with the same key value
                    maintain their relative order in the sorted output.
         */
        bubbleSort(arr1);
        System.out.println("arr1 = " + Arrays.toString(arr1));

        /*
            Insertion Sort: The array is virtually split into a sorted and an unsorted part.
                Values from the unsorted part are picked and placed at the correct position in the sorted part.
                - Can be used for linked lists
                - Time Complexity: O(N2)
                - Auxiliary Space: O(1)
         */
        System.out.println("\nInsertion sort");
        arr1 = new int[]{4, 6, 1, 9, 4, 6, 3};
        System.out.println("arr1 = " + Arrays.toString(arr1));
        insertionSort(arr1);
        System.out.println("arr1 = " + Arrays.toString(arr1));

        /*
            Selection Sort:  by repeatedly finding the minimum element (considering ascending order) from unsorted part and putting it at the beginning
                - Time Complexity: O(N2)
                - Auxiliary Space: O(1)
         */
        System.out.println("\nSelection sort");
        arr1 = new int[]{4, 6, 1, 9, 4, 6, 3};
        System.out.println("arr1 = " + Arrays.toString(arr1));
        selectionSort(arr1);
        System.out.println("arr1 = " + Arrays.toString(arr1));

        /*
            Merge Sort: a Divide and Conquer algorithm.
                - It divides the input array into two halves, calls itself for the two halves, and then merges the two sorted halves.
                - Time Complexity: θ(n-Log-n) in all 3 cases (worst, average and best)
                - Auxiliary Space: O(N), In merge sort all elements are copied into an auxiliary array. So N auxiliary space is required for merge sort.
         */
        System.out.println("\nMerge sort");
        arr1 = new int[]{4, 6, 1, 9, 4, 6, 3};
        System.out.println("arr1 = " + Arrays.toString(arr1));
        mergeSort(arr1, 0, arr1.length-1);
        System.out.println("arr1 = " + Arrays.toString(arr1));

        /*
             Quick sort - read cormen pg 171-173
                - It's a sorting algorithm based on the Divide and Conquer algorithm that picks an element as a pivot and
                    partitions the given array around the picked pivot by placing the pivot in its correct position in the sorted array.
                - As the partition process is done recursively, it keeps on putting the pivot in its actual position in
                    the sorted array. Repeatedly putting pivots in their actual position makes the array sorted.
                - Time complexity: worst case: O(n^2) average and best case: O(n-log-n) and constant factors are small
                - Auxiliary Space: O(1), if we don’t consider the recursive stack space.
                - Advantage- Quicksort’s average-case performance is usually very good in practice,
                    - sorting in place (whereas merge sort requires O(N) extra storage)
         */
        System.out.println("\nQuick sort");
        arr1 = new int[]{4, 6, 1, 9, 4, 6, 3};
        System.out.println("arr1 = " + Arrays.toString(arr1));
        quickSort(arr1, 0, arr1.length-1);
        System.out.println("arr1 = " + Arrays.toString(arr1));

        /*
            Counting sort - sorting technique based on keys between a specific range. It works by counting
                the number of objects having distinct key values (kind of hashing).

            Radix sort - digit by digit sort starting from least significant digit to most significant digit.
                Radix sort uses counting sort as a subroutine to sort.
            Eg:
                    Original, unsorted list:
                170, 45, 75, 90, 802, 24, 2, 66

                Sorting by least significant digit (1s place) gives:
                [*Notice that we keep 802 before 2, because 802 occurred
                before 2 in the original list, and similarly for pairs
                170 & 90 and 45 & 75.]

                170, 90, 802, 2, 24, 45, 75, 66

                Sorting by next digit (10s place) gives:
                [*Notice that 802 again comes before 2 as 802 comes before
                2 in the previous list.]

                802, 2, 24, 45, 66, 170, 75, 90

                Sorting by the most significant digit (100s place) gives:
                2, 24, 45, 66, 75, 90, 170, 802

            Application:
                In a typical computer, which is a sequential random-access machine, where the records are keyed by
                multiple fields radix sort is used.
                For eg., you want to sort on three keys month, day and year. You could compare two records on year,
                then on a tie on month and finally on the date. Alternatively, sorting the data three times using
                Radix sort first on the date, then on month, and finally on year could be used.


        Bucket sort: Bucket sort is mainly useful when input is uniformly distributed over a range.
            For example, consider the following problem: Sort a large set of floating point numbers which are in
            range from 0.0 to 1.0 and are uniformly distributed across the range.

        Algo:
            Loop through the original array and put each object in a “bucket”.
            Sort each of the non-empty buckets
            Check the buckets in order and then put all objects back into the original array.

        Time complexity: average time complexity is O(n + k)

        See: https://www.geeksforgeeks.org/bucket-sort-2/

    */


        /*
            Arrays.sort - When sorting primitives, the Arrays.sort method uses a Dual-Pivot implementation of Quicksort.
                DualPivotQuickSort algorithm picks 2 pivot instead of 1. It's a bit faster than the original single pivot quicksort.
                However, when sorting objects an iterative implementation of MergeSort is used.
         */
        System.out.println("\nArrays.sort ");
        arr1 = new int[]{4, 6, 1, 9, 4, 6, 4, 4, 1, 1, 3};
        System.out.println("arr1 = " + Arrays.toString(arr1));
        Arrays.sort(arr1);
        System.out.println("arr1 = " + Arrays.toString(arr1));

        /*
            Binary search: a searching algorithm used in a sorted array by repeatedly dividing the search interval in half.
         */

        System.out.println("Index of 4: " + Arrays.binarySearch(arr1, 4));
        System.out.println("First index of 4: " + binarySearch(arr1, 0, arr1.length-1, 4));
        System.out.println("First index of 2: " + binarySearch(arr1, 0, arr1.length-1, 2));
        System.out.println("First index of 6: " + binarySearch(arr1, 0, arr1.length-1, 6));
        System.out.println("First index of 31: " + binarySearch(arr1, 0, arr1.length-1, 31));

    }

    public int binarySearch(int[] arr, int l, int r, int key) {
        if (l > r) return -1;
        if ((l == r) && (key != arr[l])) return -1;
        if ((l == r) && (key == arr[l])) return l;

        int mid = l + (r-l)/2;
        if (key <= arr[mid]) return binarySearch(arr, l, mid, key);
        return binarySearch(arr, mid+1, r, key);
    }


    private void quickSort(int[] arr, int l, int r) {
        if (l<r) {
           int p = partition(arr, l, r);
           quickSort(arr, l, p-1);
           quickSort(arr, p+1, r);
        }
    }

    private int partition(int[] arr, int l, int r) {
        int pivot = arr[r], indx = l, temp;

        for (int i=l; i<r; i++) {
            if (arr[i] < pivot) {
                temp = arr[i];
                arr[i] = arr[indx];
                arr[indx] = temp;
                indx++;
            }
        }

        temp = arr[r];
        arr[r] = arr[indx];
        arr[indx] = temp;

        return indx;
    }

    private void mergeSort(int[] arr, int start, int end) {
        if (start >= end) return;

        int middle = start + (end - start)/2;
        mergeSort(arr, start, middle);
        mergeSort(arr,middle+1, end);
        merge(arr, start, middle, middle+1, end);
    }

    private void merge(int[] arr, int start1, int end1, int start2, int end2) {
        int[] arr1 = Arrays.copyOfRange(arr, start1, end1+1);
        int[] arr2 = Arrays.copyOfRange(arr, start2, end2+1);

        int indx1, indx2, indx;
        for (indx = start1, indx1 = 0, indx2 = 0; indx1 < arr1.length && indx2 < arr2.length;) {
            if (arr1[indx1] < arr2[indx2]) {
                arr[indx] = arr1[indx1];
                indx++;
                indx1++;
            } else {
                arr[indx] = arr2[indx2];
                indx++;
                indx2++;
            }
        }

        while (indx1 < arr1.length) {
            arr[indx] = arr1[indx1];
            indx++;
            indx1++;
        }

        while (indx2 < arr2.length) {
            arr[indx] = arr2[indx2];
            indx++;
            indx2++;
        }
    }

    private void selectionSort(int[] arr) {
        int minVal, indx, temp;
        int n = arr.length;

        for (int i=0; i<n; i++) {
            minVal = arr[i];
            indx = i;
            for (int j=i+1; j<n; j++) {
                if (minVal > arr[j]) {
                    minVal = arr[j];
                    indx = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[indx];
            arr[indx] = temp;
        }
    }

    private void insertionSort(int[] arr) {
        int n = arr.length;
        int temp;
        for (int i=n-1; i>0; i--) {
            for (int j=0; j<i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n-1; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}

package algorithms;

import java.util.Arrays;

public class SummaryAlgorithms {

    // ------------------  Sorting
    /*
        Bubble Sort: repeatedly swapping the adjacent elements if they are in the wrong order.
        Insertion Sort: Values from the unsorted part are picked and placed at the correct position in
            the sorted part. (linked list)
        Selection Sort:  by repeatedly finding the minimum element (considering ascending order) from unsorted part
             and putting it at the beginning

        Merge Sort: divides the input array into two halves, calls itself for the two halves, and then
            merges the two sorted halves.
            - θ(n-Log-n) in all 3 cases (worst, average and best)
            - Auxiliary Space: O(N), It is a stable sort

        Quick sort: picks an element as a pivot and partitions the given array around the picked pivot by
            placing the pivot in its correct position in the sorted array.
            - worst case: O(n^2) average and best case: O(n-log-n) and constant factors are small
            - sorting in place (whereas merge sort requires O(N) extra storage)
            - Randomized quicksort: randomly chooses the pivot from the subarray
            - DualPivot QuickSort: algorithm picks 2 pivot instead of 1. a bit faster than the original single pivot.
                Used in Arrays.sort()

        MergeSort vs QuickSort vs HeapSort
            - Time complexity the three algorithms are in average case 𝑂(𝑛log𝑛) while quick sort worst case is 𝑂(𝑛2)

            - Space complexity : especially if it's in-place sort Heap sort and quick sort can be done in-place.
                So they can directly work on the pre-allocated space where initial unsorted data is stored.
                While heap sort removes recursive calls by tail optimization and its space requirement is 𝑂(1)
                quick sort requires variables put on the stacks at each recursive step, so it requires in total 𝑂(log𝑛)
                merge sort is not in-place and requires additional 𝑂(𝑛) space.

             - Stable or unstable : Merge sort is only the stable sorting among the three.

            - External sort or not This means whether the algorithm works efficiently with external memory
                (e.g. HDD/SSD) which is slower than the main memory. Merge sort and quick sort are typical
                external sort since they can divide target data set and work on the small pieces loaded on memory,
                but heap sort is difficult to do that.


        Counting sort: counting the number of objects having distinct key values. When keys are bw
            a specific range
        Radix sort - digit by digit sort starting from least significant digit to most significant digit.
        Bucket sort: Bucket sort is mainly useful when input is uniformly distributed over a range.
            For example, Sort a large set of floating point numbers which are in range from 0.0 to 1.0 and are uniformly distributed across the range.
            Loop through the original array and put each object in a “bucket”. Sort each of the non-empty buckets

     */

    // ------------------  Binary search
    /*
        - a searching algorithm used in a sorted array by repeatedly dividing the search interval in half.
     */

    // ------------------  Infix, Postfix
    /*
        -Infix expression:  a op b. When an operator is in-between every pair of operands.
		-Postfix expression: a b op

     */



    public static void main(String[] args) {
        int[] arr1 = {4, 6, 1, 9, 4, 6, 3};
        System.out.println("arr1: " + Arrays.toString(arr1));
        SummaryAlgorithms obj = new SummaryAlgorithms();
        obj.mergeSort(arr1, 0, arr1.length-1);
        System.out.println("arr1 sorted: " + Arrays.toString(arr1));
        System.out.println("Search 6: " + obj.firstOccurrence(arr1, 0, arr1.length-1, 6));
        System.out.println("Search 15: " + obj.firstOccurrence(arr1, 0, arr1.length-1, 15));
    }

    //---- Merge Sort

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

    //---- Quick Sort

    private void quickSort(int[] arr, int l, int r) {
        if (l<r) {
            int p = partition(arr, l, r);
            quickSort(arr, l, p-1);
            quickSort(arr, p+1, r);
        }
    }

    private int partition(int[] arr, int l, int r) {
        // Take last element as pivot
        int pivot = arr[r], indx = l, temp;

        // Rearrange array from l to r such that elements from arr[l, indx-1] are less than pivot
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


    //---- Binary Search

    /*
            if mid is less than key then l = mid+1 else r = mid

            l will always be either less than target or the first occurence
            At the end, if target exists then l will point to first occurence
     */

    public int firstOccurrence(int[] arr, int l, int r, int key) {
        while (l<r) {
            int mid = (l+r)/2;
            if (arr[mid] < key) l = mid+1;
            else r = mid;
        }
        return arr[l] == key ? l : -1;
    }

    /*
        // During the course - r will always point to a value which is greater than target,
        //    l may be equal to target or less than target
        //    At the end, r will point to the first element which is greater than target or the last occurence
        //    From this we can deduce if r or r-1 is key or not
        //    Special case - single element
     */

    public int lastOccurrence(int[] arr, int l, int r, int key) {
        while (l<r) {
            int mid = (l+r)/2;
            // The only difference is the equal sign
            if (arr[mid] <= key) l = mid+1;
            else r = mid;
        }

        if (arr[r] == key) return r;
        return ((r-1) >= 0) ? (arr[r-1] == key ? r-1 : -1) : -1;
    }


}

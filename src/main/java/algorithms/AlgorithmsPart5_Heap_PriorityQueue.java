package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AlgorithmsPart5_Heap_PriorityQueue {
    /*
        Complete binary tree
            - A complete binary tree is a binary tree in which every level, except possibly the last, is completely
                filled, and all nodes are as far left as possible.

        Heap
            - A heap is a complete binary tree where value of a node is greater than or equal to the
                values of its children. There are two types of heaps:
            - Max-Heap: for any given node i
                    The value of i is greater than or equal to the values of its children.
                    The highest value is at the root of the tree.
            - Min-Heap: for any given node
                    The value of i is less than or equal to the values of its children.
                    The lowest value is at the root of the tree.

        Operations
            Insertion:
                - Add the new element at the end of the tree (the bottom-rightmost spot).
                - Heapify (rearrange the tree) to maintain the heap property by comparing the added element with its parent and swapping if necessary (bubble up).

            Deletion (usually deletion of the root node):
                - Replace the root with the last element in the tree.
                - Remove the last element.
                - Heapify (rearrange the tree) to maintain the heap property by comparing the root with its children and swapping if necessary (bubble down).

            Peek
                - Return the root element without removing it (O(1) operation).

        Applications
            - Priority Queues: Heaps are often used to implement priority queues because they allow quick access to the
                highest (max-heap) or lowest (min-heap) priority element.
            - Heap Sort: Heaps can be used to implement an efficient sorting algorithm called heap sort.
            - Graph Algorithms: Heaps are used in algorithms like Dijkstra's and Prim's for finding the shortest path
                and minimum spanning tree, respectively.

        PriorityQueue<E>
            - PriorityQueue is a part of the Java Collections Framework
            - Ordering: The PriorityQueue orders elements according to their natural ordering (if they implement the
                Comparable interface) or by a Comparator provided at queue construction time.
            - Heap-Based: Internally, the PriorityQueue is implemented as a binary heap, which provides efficient
                insertion and deletion operations.
            - PriorityQueue does not permit null elements.
            - The queue's iterator does not guarantee any particular order.
            - It is not thread-safe. For a thread-safe alternative, consider using PriorityBlockingQueue.

        Time complexity:
            - add() / remove() : O(log n) time for the enqueing and dequeing
            - element() / size() : O(1) time
            - contains() : O(n)
     */

    class Student {
        String name;
        Integer rank;

        public Student(String name, Integer rank) {
            this.name = name;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", rank=" + rank +
                    '}';
        }
    }

    public void execute() {
        // By default this will build min heap. Min element at top
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(4);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(7);
        priorityQueue.add(9);
        priorityQueue.add(3);
        priorityQueue.add(6);

        System.out.print("Priority queue: ");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.remove() + ", ");
        }
        System.out.println();

        // Reverse ordering of natural comparison
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        maxPriorityQueue.add(4);
        maxPriorityQueue.add(1);
        maxPriorityQueue.add(3);
        maxPriorityQueue.add(4);
        maxPriorityQueue.add(7);
        maxPriorityQueue.add(9);
        maxPriorityQueue.add(3);
        maxPriorityQueue.add(6);

        System.out.print("Priority queue reverse: ");
        while (!maxPriorityQueue.isEmpty()) {
            System.out.print(maxPriorityQueue.remove() + ", ");
        }
        System.out.println();

        // Ordering by comparator - rank
        PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.rank));
        studentPriorityQueue.add(new Student("test1", 5));
        studentPriorityQueue.add(new Student("test2", 1));
        studentPriorityQueue.add(new Student("test3", 6));
        studentPriorityQueue.add(new Student("test4", 2));
        studentPriorityQueue.add(new Student("test5", 4));

        System.out.print("Student priority queue: ");
        while (!studentPriorityQueue.isEmpty()) {
            System.out.print(studentPriorityQueue.remove() + ", ");
        }
        System.out.println();
    }
}

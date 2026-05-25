package collections;

import java.lang.reflect.Array;
import java.util.*;

public class ReviseCollections {
    public static void main(String[] args) {
        /*
            Arrays
            - Create 1d array - empty and with value, print
            - Create 2d array - empty and with value, print
            - Sort 1d array, 2d array (lambda)

            List
            - Create ArrayList, add, get, set, size, isEmpty method
            - Sort arraylist
            - sublist
            - Loop through Iterator
            - Loop through foreach

            Stack
            - Create stack - push, pop, peek

            Queue
            - Create queue - add, remove, element

            Dequeue
            - Create deque
            - Operations on both ends

            PriorityQueue
            - Create Priority queue
            - Operations, show all elements by removing
            - Custom comparator - max queue

            Set
            - Create set, 3 variants
            - Operations add, search
            - Create set from list
            - union and intersection of two list using set

            Map
            - Create hashmap - methods, check if key is present
            - print all keys, values
            - iterate through map and print keys and values

         */

        System.out.println("Arrays");

        int[] arr1 = new int[10];
        int[] arr2 = {2, 4, 6, 1, 3, 6};

        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));

        Arrays.sort(arr2);
        System.out.println("arr2 = " + Arrays.toString(arr2));

        int[][] arr2d1 = {{1, 2}, {4, 5}, {2, 3}, {0, 5}, {2, 1}};
        System.out.println("arr2d1 = " + Arrays.deepToString(arr2d1));

        Arrays.sort(arr2d1, (a, b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        System.out.println("arr2d1 = " + Arrays.deepToString(arr2d1));

        System.out.println("\nList");

        List<Integer> list1 = new ArrayList<>();
        for (int i=0; i<5; i++) {
            list1.add(i);
        }
        System.out.println("list1 = " + list1);
        System.out.println("2nd element: " + list1.get(1));

        System.out.println("Setting 3rd element as 5");
        list1.set(2, 5);
        System.out.println("list1 = " + list1);

        System.out.println("size: " + list1.size());
        Collections.sort(list1);

        System.out.println("sorted list1 = " + list1);
        System.out.println("sublist 1 to 4: " + list1.subList(1, 4));

        Iterator<Integer> it = list1.iterator();
        while (it.hasNext()) {
            System.out.println("iterator value: " + it.next());
        }

        for (int i : list1) {
            System.out.println("i: " + i);
        }

        System.out.println("\nStack");

        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<5; i++) {
            stack.push(i);
        }

        System.out.println("Stack size: " + stack.size());

        while (!stack.isEmpty()) {
            int top = stack.peek();
            System.out.println("Stack top: " + top);
            stack.pop();
        }

        System.out.println("\nQueue");

        Queue<Integer> queue = new LinkedList<>();
        queue.add(5);
        queue.add(6);
        queue.add(3);
        queue.add(1);

        while (!queue.isEmpty()) {
            int front = queue.element();
            System.out.println("Queue front : " + front);
            queue.remove();
        }

        System.out.println("\nDeque");

        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(5);
        deque.addLast(6);
        deque.addFirst(3);
        deque.addLast(1);

        System.out.println("Deque: " + deque);
        System.out.println("First element: " + deque.getFirst());
        System.out.println("After removing first element");
        deque.removeFirst();
        System.out.println("Deque: " + deque);
        System.out.println("After removing last element");
        deque.removeLast();
        System.out.println("Deque: " + deque);

        System.out.println("\nPriorityQueue");
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();
        pqueue.add(5);
        pqueue.add(6);
        pqueue.add(3);
        pqueue.add(1);

        while (!pqueue.isEmpty()) {
            System.out.println("Priority queue element: " + pqueue.element());
            pqueue.remove();
        }

        System.out.println("Max queue");

        PriorityQueue<Integer> pqueue2 = new PriorityQueue<>(Comparator.reverseOrder());
        pqueue2.add(5);
        pqueue2.add(6);
        pqueue2.add(3);
        pqueue2.add(1);

        while (!pqueue2.isEmpty()) {
            System.out.println("Priority queue element: " + pqueue2.element());
            pqueue2.remove();
        }

        System.out.println("\nSet");

        Set<Integer> set1 = new HashSet<>();
        set1.add(5); set1.add(4); set1.add(1); set1.add(5); set1.add(4); set1.add(3);
        System.out.println("hash set: " + set1);

        set1 = new TreeSet<>();
        set1.add(5); set1.add(4); set1.add(1); set1.add(5); set1.add(4); set1.add(3);
        System.out.println("tree set: " + set1);

        set1 = new LinkedHashSet<>();
        set1.add(5); set1.add(4); set1.add(1); set1.add(5); set1.add(4); set1.add(3);
        System.out.println("linked hash set: " + set1);

        System.out.println("\nMap");

        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 5);
        map1.put(2, 3);
        map1.put(3, 7);
        map1.put(4, 4);
        map1.put(5, 2);

        System.out.println("map: " + map1);
        System.out.println("Value of 3: " + map1.get(3));
        System.out.println("Key 45 is present: " + map1.containsKey(45));
        System.out.println("Get 45 or default demo : " + map1.getOrDefault(45, 0));

        System.out.println("keys: " + map1.keySet());
        System.out.println("values: " + map1.values());

        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            System.out.println("key: " + entry.getKey() + "  value : " + entry.getValue());
        }

    }
}

package algorithms;

import java.util.*;

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

public class SummaryCollections {
    public static void main(String[] args) {
        // -----------------------  Arrays
        int[] arr1d = new int[10];
        int[] arr1 = {24, 93, 93, 51, 85, 99, 26, 84, 63, 29};
        System.out.println("arr2 = " + Arrays.toString(arr1));

        int[][] arr2d = new int[5][2];
        int[][] arr2 = {{3, 4}, {7, 2}, {4, 9} , {2, 4}, {4, 4}};
        System.out.println("2D array: " + Arrays.deepToString(arr2));

        // The sorting algo is a Dual-Pivot Quicksort -  O(n log(n)) performance
        Arrays.sort(arr1);
        System.out.println("arr2 = " + Arrays.toString(arr1));

        Arrays.sort(arr2, (a, b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        System.out.println("2D array: " + Arrays.deepToString(arr2));

        // Fill an array with a particular value.
        Arrays.fill(arr1, 3);

        // -----------------------  List
        List<Integer> test = new ArrayList<>(Arrays.asList(24, 93, 93, 51, 85, 99, 26, 84, 63, 29));
        System.out.println();
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<5; i++) {
            list.add(i);
        }

        System.out.println("list = " + list);
        System.out.println("4th element of list: " + list.get(3));
        list.set(2, 15);
        System.out.println("list = " + list);

        // Sort
        // - provides stable sort
        // - Algorithm: TimSort - hybrid between Merge Sort and Insertion Sort.
        Collections.sort(list);

        // sublist
        List<Integer> sublist = list.subList(2, 5);
        System.out.println(sublist);

        // Collection methods
        System.out.println("size: " + list.size());
        System.out.println("isEmpty: " + list.isEmpty());
        System.out.println("list contains 15: " + list.contains(15));
        list.remove(3);
        System.out.println("remove number at index 3: " + list);

        // iterator
        System.out.println("Iterator demo: ");
        Iterator<Integer> c1iterator = list.iterator();
        while (c1iterator.hasNext()) {
            System.out.print(c1iterator.next() + ", ");
        }

        // for each
        System.out.println("\nFor-each demo: ");
        for (Integer i : list) {
            System.out.print(i + ", ");
        }


        // -----------------------  Stack - push, pop, peek
        System.out.println();
        Stack<Integer> stack = new Stack<>();
        System.out.println("\nStack push: ");
        for (int i=0; i<10; i+=2) {
            System.out.print("Add " + i + ", ");
            stack.add(i);
        }

        System.out.println("\npeek: " + stack.peek());

        System.out.print("Stack pop: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ", ");
        }

        // -----------------------  Queue - add, remove, element
        System.out.println();
        Queue<Integer> queue = new LinkedList<>();
        System.out.println("\nQueue add: ");
        for (int i=0; i<10; i+=2) {
            System.out.print("Add " + i + ", ");
            queue.add(i);
        }
        System.out.println("\nelement: " + queue.element());
        System.out.print("Queue remove: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + ", ");
        }

        // -----------------------  Deque
        /*
            - double-ended queue. supports the insertion and removal of elements at both ends.
            - LinkedList implementation
                - Implements Deque using a doubly-linked list. Supports null elements
            - Methods
                - Same as queue: add, remove - first and last
                - addFirst, addLast, removeFirst, removeLast, getFirst, getLast
         */
        System.out.println("\nDeque");
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("Apple"); deque.addFirst("Orange");
        deque.addLast("Banana"); deque.addLast("Grapes");
        System.out.println("First Element: " + deque.getFirst());
        System.out.println("Last Element: " + deque.getLast());
        deque.removeFirst(); deque.removeLast();


        // -----------------------  PriorityQueue
        // By default this will build min heap. Min element at top
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(4); priorityQueue.add(1); priorityQueue.add(3); priorityQueue.add(4); priorityQueue.add(7);
        priorityQueue.add(9); priorityQueue.add(3); priorityQueue.add(6);

        System.out.print("Priority queue: ");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.remove() + ", ");
        }
        System.out.println();
        // For max-heap use: Comparator.reverseOrder() in constructor

        // Custom comparator
        PriorityQueue<Student> pqueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.rank));


        // -----------------------  Set

        /*
            Hashset
                - stores its elements in a hash table
                - Complexity: best performing, Insert, Search, Delete in O(1)
                - Allows one null element
            TreeSet
                - stores its elements in a red-black tree
                - Maintains elements in a sorted order
                - Complexity: Insert, Search, Delete in O(log-n)
                - Usage: where you need a sorted collection and need to perform operations like range queries or need the elements in order.
            LinkedHashSet
                - combination of hashset and linked list. Implemented as a hash table with a linked list running through it
                - insertion-order is maintained
                - Complexity: Insert, Search, Delete in O(1)
                - Usage: when you need fast access to elements while also preserving the order of insertion.
         */
        System.out.println("\n\nSet");
        Set<String> set = new HashSet<>();
        set.add("Banana"); set.add("Cherry"); set.add("Apple"); set.add("Cherry");
        System.out.println("HashSet = " + set);

        set = new TreeSet<>();
        set.add("Banana"); set.add("Cherry"); set.add("Apple"); set.add("Cherry");
        System.out.println("TreeSet = " + set);

        set = new LinkedHashSet<>();
        set.add("Banana"); set.add("Cherry"); set.add("Apple"); set.add("Cherry");
        System.out.println("LinkedHashSet = " + set);

        // Union (addAll) and Intersection (retainAll)
        List<String> col1 = new ArrayList<>();
        Set<String> union, intersection;
        col1.add("a"); col1.add("b"); col1.add("c");
        System.out.println("col1 = " + col1);
        List<String> col2 = new ArrayList<>();
        col2.add("b"); col2.add("c"); col2.add("d"); col2.add("e");
        System.out.println("col2 = " + col2);

        union = new HashSet<>(col1);
        union.addAll(col2);
        intersection = new HashSet<>(col1);
        intersection.retainAll(col2);
        System.out.println("Union of col1 and col2: " + union);
        System.out.println("Intersection of col1 and col2: " + intersection);



        // -----------------------  Map
        // Similar three implementation like set
        System.out.println("\nHashMap");
        Map<Integer, String> idToName = new HashMap<>();
        idToName.put(2, "Harry");
        idToName.put(1, "Ron");
        idToName.put(3, "Hermoine");
        idToName.put(5, "Harry");
        idToName.put(4, "Dumbledore");
        idToName.put(4, "Hagrid");  // This will replace dumbledore

        System.out.println("Name with id 4: " + idToName.get(4));
        System.out.println("Id 8 exists: " + idToName.containsKey(8));
        System.out.println("Name Harry exists: " + idToName.containsValue("Harry"));

        // Keyset, Values and Entryset
        System.out.println("Keyset: " + idToName.keySet());
        System.out.println("Vakues: " + idToName.values());

        System.out.println("Map Entries:");
        for (Map.Entry<Integer, String> entry : idToName.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }


    }
}

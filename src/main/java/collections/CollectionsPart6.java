package collections;

import java.util.LinkedList;
import java.util.Queue;

public class CollectionsPart6 {
    /*
        Queue :
            - A Queue is a collection for holding elements prior to processing.
            - Queues typically, but not necessarily, order elements in a FIFO (first-in-first-out) manner. Among the
                exceptions are priority queues, which order elements according to their values
            - Queues provide additional insertion, removal, and inspection operations. The Queue interface follows.

            public interface Queue<E> extends Collection<E> {
                E element();
                boolean offer(E e);
                E peek();
                E poll();
                E remove();
            }

            Each Queue method exists in two forms: (1) one throws an exception if the operation fails, and (2) the
            other returns a special value if the operation fails (either null or false, depending on the operation).
            The regular structure of the interface is illustrated in the following table.

            Queue Interface Structure
                Type of Operation	Throws exception	Returns special value
                Insert	            add(e)	            offer(e)
                Remove	            remove()	        poll()
                Examine	            element()	        peek()

        Bounded queue:
            - It is possible for a Queue implementation to restrict the number of elements that it holds; such queues
                are known as bounded.
            - Some Queue implementations in java.util.concurrent are bounded, but the implementations in java.util are not.

        add method
            - inserts an element unless it would violate the queue's capacity restrictions,
            - The offer method, which is intended solely for use on bounded queues, differs from add only in that it
                indicates failure to insert an element by returning false.

        remove and poll methods
            - both remove and return the head of the queue.
            - The remove and poll methods differ in their behavior only when the queue is empty. Under these circumstances,
                remove throws NoSuchElementException, while poll returns null.

        element and peek method
            - return, but do not remove, the head of the queue.
            - If the queue is empty, element throws NoSuchElementException, while peek returns null.

     */
    public void queueDemo() {
        Queue<Integer> integerQueue = new LinkedList<>();
        integerQueue.add(2);
        integerQueue.add(6);
        integerQueue.add(2);
        integerQueue.add(3);
        integerQueue.add(10);
        integerQueue.add(8);

        Integer head;
        System.out.println("Queue size: " + integerQueue.size());
        while (!integerQueue.isEmpty()) {
            head = integerQueue.poll();
            System.out.println("head = " + head);
        }
        System.out.println("Queue size: " + integerQueue.size());
    }

    /*
        Deque:
            - Usually pronounced as deck, a deque is a double-ended-queue.
            - A double-ended-queue is a linear collection of elements that supports the insertion and removal of elements at both end points.
            - The Deque interface is a richer abstract data type than both Stack and Queue because it implements both
                stacks and queues at the same time.
            - Predefined classes like ArrayDeque and LinkedList implement the Deque interface.
            - Note that the Deque interface can be used both as last-in-first-out stacks and first-in-first-out queues.

        Deque methods
            - Insert:
                - The addFirst and offerFirst methods insert elements at the beginning of the Deque instance.
                - The methods addLast and offerLast insert elements at the end of the Deque instance.
            - Remove:
                - The removeFirst and pollFirst methods remove elements from the beginning of the Deque instance.
                - The removeLast and pollLast methods remove elements from the end.
            - Retrieve
                - The methods getFirst and peekFirst retrieve the first element of the Deque instance.
                - Similarly, the methods getLast and peekLast retrieve the last element.
     */
}

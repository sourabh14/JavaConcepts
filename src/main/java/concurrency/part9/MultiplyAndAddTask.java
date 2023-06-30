package concurrency.part9;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

// Accept an input array of numbers
// Multiply all the numbers in array with a number
// Get sum of all the values of array

public class MultiplyAndAddTask extends RecursiveTask<Integer> {
    List<Integer> list;
    Integer start;
    Integer end;

    public MultiplyAndAddTask(List<Integer> list, Integer start, Integer end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println("start : " + start + ", end : " + end);
        if (Objects.equals(start, end)) {
            return list.get(start) * 3;
        }

        if ((end - start) == 1) {
            return ((list.get(start) * 3) + (list.get(end) * 3));
        }

        int mid = (start + end) /2;
        MultiplyAndAddTask subTask1 = new MultiplyAndAddTask(list, start, mid);
        MultiplyAndAddTask subTask2 = new MultiplyAndAddTask(list, mid + 1, end);
        invokeAll(subTask1, subTask2);

        return subTask1.join() + subTask2.join();
    }

}

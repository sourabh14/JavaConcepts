package concurrency.part9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import concurrency.part2.PrimeNumberUtils;

public class ConcurrencyPart9Demo {
    public void execute() {
        System.out.println("\n\n ----- Callable, Futures, Fork Join  -----");
        /*
           Callable interface:
                - A task that returns a result and may throw an exception.
                - Implementors define a single method with no arguments called call.
                - The Callable interface is similar to Runnable, in that both are designed for classes whose instances
                    are potentially executed by another thread. A Runnable, however, does not return a result and cannot
                    throw a checked exception.
                - The Executors class contains utility methods to convert from other common forms to Callable classes.

            executorService.submit()
                - Takes callable as argument
                - Submits a value-returning task for execution and returns a Future representing the pending results
                    of the task.
                - The Future's get method will return the task's result upon successful completion.

         */
        Runnable runnable = () -> System.out.println("Print from runnable");
        Callable<String> callable = () -> {
            System.out.println("From callable. Waiting for 5 sec");
            Thread.sleep(5000);
            return "Return value from callable";
        };

        // #### Uncomment following lines for demo ####
//        ExecutorService executorService = Executors.newFixedThreadPool(2);


//        Future<String> future = executorService.submit(callable);

//        System.out.println("Executing in main thread");
//        System.out.println("Executing in main thread dummy");
//
//        try {
//            // since future.get waits hence it throws exception
//            System.out.println(future.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        // #####


        // Prime number demo with callable
        /*
            Futures:
                - A Future represents the result of an asynchronous computation. Methods are provided to check if the
                    computation is complete, to wait for its completion, and to retrieve the result of the computation.
                - The result can only be retrieved using method get when the computation has completed, blocking if
                    necessary until it is ready.
                - Cancellation is performed by the cancel method. Additional methods are provided to determine if the
                    task completed normally or was cancelled.
                - Once a computation has completed, the computation cannot be cancelled. If you would like to use a
                    Future for the sake of cancellability but not provide a usable result, you can declare types of the
                    form Future<?> and return null as a result of the underlying task.

                - Attempts to cancel execution of this task. This attempt will fail if the task has already completed,
                has already been cancelled, or could not be cancelled for some other reason.
                - If successful, and this task has not started when cancel is called, this task should never run. If
                the task has already started, then the mayInterruptIfRunning parameter determines whether the thread
                executing this task should be interrupted in an attempt to stop the task.
A               - After this method returns, subsequent calls to isDone will always return true. Subsequent calls to
                isCancelled will always return true if this method returned true.
         */
        int n = 123;
        Callable calculatePrimeCallable = () -> new PrimeNumberUtils().calculateNthPrime(n);

        // #### Uncomment following lines for demo ####
//        Future<Integer> prime = executorService.submit(calculatePrimeCallable);
//
//        try {
//            System.out.println(n + "th prime : " + prime.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        // #####

        /*
            Completable futures:
                - A CompltableFuture is used for asynchronous programming. Asynchronous programming means writing
                    non-blocking code. It runs a task on a separate thread than the main application thread and
                    notifies the main thread about its progress, completion or failure.
                - In this way, the main thread does not block or wait for the completion of the task. Other tasks execute
                    in parallel.
                - We don't have to check if future object is done and then print value.
         */

        // #### Uncomment following lines for demo ####
        // CompletableFuture.supplyAsync(() -> (new PrimeNumberUtils()).calculateNthPrime(n))
               // .thenAccept(retValue -> System.out.println(retValue));

        /*
            Fork Join
                - Take a large problem set. You want to process a huge list of numbers. You can iterate over it one by one.
                But that will be slow and not leveraging concurrency.
                So you divide problem set divide in several parts (let's say we have 4 cores so we divide the problem
                set in 4 parts).
                - What fork join does is break down the problem into smaller pieces and process it recursively. And having
                these smaller pieces run in a concurrent manner.
                - Map Reduce is similar pattern as fork join.
                - The fork/join framework is an implementation of the ExecutorService interface that helps you take
                    advantage of multiple processors.
                - The goal is to use all the available processing power to enhance the performance of your application.
                - there are some generally useful features in Java SE which are already implemented using the fork/join
                    framework. One such implementation, introduced in Java SE 8, is used by the java.util.Arrays class
                    for its parallelSort() methods. These methods are similar to sort(), but leverage concurrency via the
                    fork/join framework.
                - As with any ExecutorService implementation, the fork/join framework distributes tasks to worker
                    threads in a thread pool.

                - The fork/join framework is distinct because it uses a work-stealing algorithm. Worker threads that
                    run out of things to do can steal tasks from other threads that are still busy.

            Problem with this approach
                - Too many thread instances. Thread comes with a cost.
                - Existing mechanism too resource intensive.
                - You need something lightweight, because we have large set of small problems

            Fork Join Pool
                - It is a special implementation of executor service, for this specific usecase.
                - The center of the fork/join framework is the ForkJoinPool class, an extension of the
                    AbstractExecutorService class. ForkJoinPool implements the core work-stealing algorithm and can
                    execute ForkJoinTask processes.

            Fork Join Task
                - ForkJoinTask is a special type of task designed to run a fork join pool.
                - Usually we don't use it directly. We have two sub implementations
                    - RecursiveAction - recursive breakdown of task that need not be accumulated. Eg; Adding a number to each element of array
                    - RecursiveTask - accumulation is required. Eg: sum of all elements of array

            Steps
                - Create an implementation of ForkJoinTask.
                - You get an instance of ForkJoinPool
                - Submit an instance of ForkJoinTask implementation to the ForkJoinPool

            Problem - Sum of all elements of an array

            Basic Use
                - The first step for using the fork/join framework is to write code that performs a segment of the work.
                    Your code should look similar to the following pseudocode:

                        if (my portion of the work is small enough)
                          do the work directly
                        else
                          split my work into two pieces
                          invoke the two pieces and wait for the results

                - Wrap this code in a ForkJoinTask subclass, typically using one of its more specialized types, either
                    RecursiveTask (which can return a result) or RecursiveAction.
                - After your ForkJoinTask subclass is ready, create the object that represents all the work to be done
                    and pass it to the invoke() method of a ForkJoinPool instance.

         */

        // Multiply all numbers with 3 and get sum of those numbers
        List<Integer> integerList = new ArrayList<>(Arrays.asList(3, 2, 6, 3, 9, 1));


        // ##### Uncomment following lines for demo
//        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        MultiplyAndAddTask task = new MultiplyAndAddTask(integerList, 0, integerList.size() - 1);
//
//        Integer result = forkJoinPool.invoke(task);
//        System.out.println("result = " + result);
//
//        forkJoinPool.shutdown();
//
//        executorService.shutdown();

        // ######

        System.out.println("-----------------------------");
    }
}












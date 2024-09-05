package concurrency.part8;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import concurrency.part2.PrimeNumberUtils;
import concurrency.part7.CounterWithLock;
import concurrency.part7.ThreadLocalDemo;

public class ConcurrencyPart8Demo {
    public void execute() {
        System.out.println("\n\n ----- Executors  -----");
        /*
           Issues with multithreading:
                - Thread creation is resource intensive.
                - Solution - reuse thread
                - Thread pool - fixed no of thread instance created in beginning. When tasks come in, a new thread is picked
                    from the pool. When all the threads are busy, other tasks have to wait.
                - Advantages:
                        - You don't have to keep creating new instance of threads.
                        - You have control over number of thread instances.
           Executors
            - It is high level API for executing tasks.
            - In large-scale applications, it makes sense to separate thread management and creation from the rest of
                the application. Objects that encapsulate these functions are known as executors.

            Executor Interfaces
            -------------------
                The java.util.concurrent package defines three executor interfaces:

                - Executor, a simple interface that supports launching new tasks.
                - ExecutorService, a sub-interface of Executor, which adds features that help manage the life cycle,
                    both of the individual tasks and of the executor itself.
                - ScheduledExecutorService, a sub-interface of ExecutorService, supports future and/or periodic execution of tasks.

                - Typically, variables that refer to executor objects are declared as one of these three interface types,
                    not with an executor class type.

                The Executor Interface
                ----------------------
                    - The Executor interface provides a single method, execute, designed to be a drop-in replacement
                    for a common thread-creation idiom.
                    - If r is a Runnable object, and e is an Executor object you can replace
                            (new Thread(r)).start();
                            with
                            e.execute(r);
                    - However, the definition of execute is less specific. The low-level idiom creates a new thread and
                    launches it immediately. Depending on the Executor implementation, execute may do the same thing,
                    but is more likely to use an existing worker thread to run r, or to place r in a queue to wait for
                    a worker thread to become available.

                    - The executor implementations in java.util.concurrent are designed to make full use of the more
                    advanced ExecutorService and ScheduledExecutorService interfaces, although they also work with the
                    base Executor interface.

                The ExecutorService Interface
                -------------------------------
                    - ExecutorService automatically provides a pool of threads and an API for assigning tasks to it.
                    - The ExecutorService
                        - Manage runnables (or "tasks") for you
                        - provides extra abilities (such as thread pool)
                        - Enables results (Runnable is a one way task, it doesn't returns a value)


                    - The ExecutorService interface supplements execute with a similar, but more versatile submit
                        method. Like execute, submit accepts Runnable objects, but also accepts Callable objects,
                        which allow the task to return a value.
                    - The submit method returns a Future object, which is used to retrieve the Callable return value
                    and to manage the status of both Callable and Runnable tasks.
                    - ExecutorService also provides methods for submitting large collections of Callable objects.
                        Finally, ExecutorService provides a number of methods for managing the shutdown of the executor.
                        To support immediate shutdown, tasks should handle interrupts correctly.

                The ScheduledExecutorService Interface
                ---------------------------------------
                    - The ScheduledExecutorService interface supplements the methods of its parent ExecutorService with
                        schedule, which executes a Runnable or Callable task after a specified delay.
                    - In addition, the interface defines scheduleAtFixedRate and scheduleWithFixedDelay, which executes
                        specified tasks repeatedly, at defined intervals.


            Thread Pools
            ------------
                - Most of the executor implementations in java.util.concurrent use thread pools, which consist of
                    worker threads. This kind of thread exists separately from the Runnable and Callable tasks it
                    executes and is often used to execute multiple tasks.
                - Using worker threads minimizes the overhead due to thread creation. Thread objects use a
                    significant amount of memory, and in a large-scale application, allocating and deallocating many
                    thread objects creates a significant memory management overhead.
                - One common type of thread pool is the fixed thread pool. This type of pool always has a specified
                    number of threads running; if a thread is somehow terminated while it is still in use, it is
                    automatically replaced with a new thread.
                - Tasks are submitted to the pool via an internal queue, which holds extra tasks whenever there are
                    more active tasks than threads.
                - An important advantage of the fixed thread pool is that applications using it degrade gracefully.
                    To understand this, consider a web server application where each HTTP request is handled by a
                    separate thread. If the application simply creates a new thread for every new HTTP request, and the
                    system receives more requests than it can handle immediately, the application will suddenly stop
                    responding to all requests when the overhead of all those threads exceed the capacity of the system.

                    With a limit on the number of the threads that can be created, the application will not be servicing
                    HTTP requests as quickly as they come in, but it will be servicing them as quickly as the system can sustain.

                - A simple way to create an executor that uses a fixed thread pool is to invoke the newFixedThreadPool
                    factory method in java.util.concurrent.Executors

                - Factory method in Executors:
                    - newFixedThreadPool()
                        - Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded
                            queue. At any point, at most nThreads threads will be active processing tasks. If additional
                            tasks are submitted when all threads are active, they will wait in the queue until a thread
                            is available. If any thread terminates due to a failure during execution prior to shutdown,
                            a new one will take its place if needed to execute subsequent tasks. The threads in the pool
                            will exist until it is explicitly shutdown.

                    - The newCachedThreadPool()
                        - Creates a thread pool that creates new threads as needed, but will reuse previously constructed
                        threads when they are available. These pools will typically improve the performance of programs
                        that execute many short-lived asynchronous tasks. Calls to execute will reuse previously constructed
                        threads if available. If no existing thread is available, a new thread will be created and added
                        to the pool.

                        Threads that have not been used for sixty seconds are terminated and removed from the cache. Thus,
                        a pool that remains idle for long enough will not consume any resources. Note that pools with
                        similar properties but different details (for example, timeout parameters) may be created using
                        ThreadPoolExecutor constructors.

                    - The newScheduledThreadPool()
                        - Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.

         */
        System.out.println("\nFixed thread pool demo");
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // For small values of n, such as 10, it will calculate immediately
        // Give input n as 40000 three times then all threads will be busy.
        // After that give input 10 it will be put in a queue
        for (Integer i : IntStream.range(1, 5).toArray()) {
            System.out.print("Enter input n (enter 0 to quit): ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if (n == 0) break;
            Runnable primeNumberCalculator = () -> new PrimeNumberUtils().calculateNthPrime(n);
            executorService.execute(primeNumberCalculator);
        }

        System.out.println("\nScheduled thread pool demo with ThreadPoolExecutor");
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        Runnable reporterRunnable = () -> {
            System.out.println("\n--[Running threads report]--");
            System.out.println("Active threads: " + threadPoolExecutor.getActiveCount());
            System.out.println("Completed threads: " + threadPoolExecutor.getCompletedTaskCount());

            System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());
            System.out.println("Core pool size: " + threadPoolExecutor.getCorePoolSize());
            System.out.println("Max Pool size: " + threadPoolExecutor.getMaximumPoolSize());
            System.out.println("Task count: " + threadPoolExecutor.getTaskCount());
            System.out.println("-- --\n");
        };
        // Now we dont have to do thread.sleet etc ..
        scheduledExecutor.scheduleAtFixedRate(reporterRunnable, 1, 5, TimeUnit.SECONDS);

        for (Integer i : IntStream.range(1, 5).toArray()) {
            System.out.print("Enter input n (enter 0 to quit): ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println("Shutting down threadPoolExecutor and scheduledExecutor");
                threadPoolExecutor.shutdown();
                scheduledExecutor.shutdown();
                break;
            }
            Runnable primeNumberCalculator = () -> new PrimeNumberUtils().calculateNthPrime(n);
            threadPoolExecutor.execute(primeNumberCalculator);
        }

        executorService.shutdown();
    }
}












package concurrency;

public class SummaryConcurrency {
    /*
        Intro
            - A process is basically the programs that are dispatched from the ready state and are scheduled in
                    the CPU for execution.
            - A thread is a single sequential flow of control within a program. Sequence of programmed instructions
                    that can be managed independently.
            - A Thread is lightweight as each thread in a process shares code, data, and resources.
            - Multithreading is a form of parallelization or dividing up work for simultaneous processing.
                    Instead of giving a large workload to a single core, threaded programs split the work into multiple
                    software threads. These threads are processed in parallel by different CPU cores to save time.
            - A core is usually the basic computation unit of the CPU - it can run a single program context

         Thread Class
            - java.lang.Thread implements Runnable - which is a functional interface with method run().
            - The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
                    The class must define a method of no arguments called run.
            - Steps to create a thread:
                - Let's say you have a piece of code that you want to execute in a separate thread.
                - Put it into a Runnable class
                - Create a new thread from that runnable
                - Start the thread

        User thread vs Daemon thread/ Thread Priority
            - User threads: Threads you create. These are high-priority threads. JVM will wait for any user thread to complete
                Eg: main thread, new Thread()
            - Daemon thread: low-priority threads whose role is to provide services to user threads.
                Eg: garbage collection, cache data cleanup (background threads)
                thread.setDaemon(true);
            - Threads with higher priority are executed in preference to threads with lower priority.

        Thread lifecycle
            - thread.getState()
            - NEW: thread is created
            - ACTIVE: start() is called
                - RUNNING: When the thread gets the CPU, it moves from the runnable to the running
                - RUNNABLE: executing in the jvm but it may be waiting for other resources from the OS such as processor
                    In the runnable state, there is a queue where the threads lie.

                - BLOCKED: waiting for some condition to be met or for a resource to become available.
                    - Eg thread is blocked trying to operate printer, when other thread has performing some action
                - WAITING
                    - when it is waiting for another thread to perform a particular action or notify it of some condition.
                - TIMED_WAITING: The thread is waiting for a specified period of time to elapse.
                    - Thread.sleep

            - TERMINATED: thread has completed execution


        Joins
            - The join method allows one thread to wait for the completion of another thread.
            - The calling thread will block until the thread on which join was called has finished executing.

        Interrupts
            - An interrupt is an indication to a thread that it should stop what it is doing and do something else.
            - It's up to the programmer to decide exactly how a thread responds to an interrupt, but it is very common
                for the thread to terminate.

        Synchronization
            - It is a mechanism to control the access of multiple threads to shared resources.
            - It ensures that only one thread can access the resource at a time, preventing race conditions and
                ensuring data consistency

        synchronized keyword in java
            - The synchronized keyword in Java is used to control access to a particular section of code by multiple
                threads. It ensures that only one thread can execute a block of code or method at a time
            - When a method is declared with the synchronized keyword, the thread holds the monitor lock for the object
                that the method belongs to. Only one thread can hold the monitor at a time.
            - The synchronized block is known as critical section (CS)
            - The lock release occurs even if the return was caused by an uncaught exception.

         Thread contention:
            - Thread contention occurs when multiple threads compete for the same resources, such as CPU time,
                memory, or data structures, leading to performance bottlenecks and reduced efficiency.
            - In a multi-threaded environment, contention typically arises when threads try to access shared
                resources simultaneously

         ThreadLocal
            - TheadLocal construct allows us to store data that will be accessible only by a specific thread
            - Each thread accessing such a variable has its own, independently initialized copy of the variable.
            - It is another way to achieve thread-safety apart from writing immutable classes.



     */

    public static void main(String[] args) {
        System.out.println("Main Thread: " + Thread.currentThread().getName());

        Runnable printerRunnable = () -> {
            System.out.println("Thread t1: " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(printerRunnable);

        Runnable joinDemo = () -> {
            try {
                t1.join();  // t2 will wait for t1 to complete
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread t2: " + Thread.currentThread().getName());
        };

        Thread t2 = new Thread(joinDemo);
        t2.start();

        t1.start();

        System.out.println("Main method ends");

        //
    }
}

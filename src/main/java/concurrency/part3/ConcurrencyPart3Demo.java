package concurrency.part3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import concurrency.part2.MyRunnable;
import concurrency.part2.PrimeNumberUtils;

public class ConcurrencyPart3Demo {
    public void execute() {
        System.out.println("\n\n ----- Daemon Threads, Thread Lifecycle and Thread States -----");
        /*
            When does an application ends
                - When the last thread completes.

            Types of threads:
                - User threads: Threads you create
                    - These are high-priority threads.
                    - The JVM will wait for any user thread to complete its task before terminating it.
                    - Eg: main thread, new Thread()
                - Daemon threads (pronounced as demon):
                    - These are low-priority threads whose only role is to provide services to user threads.
                    - they won't prevent the JVM from exiting once all user threads have finished their execution.
                    - For this reason, daemon threads are not recommended for I/O tasks.
                    - Daemon threads are useful for background supporting tasks such as garbage collection, releasing
                        memory of unused objects and removing unwanted entries from the cache.
                    - Most of the JVM threads are daemon threads.
                    - Eg: Monitoring thread - the lifecycle of thread is connected to the main thread

        */

        // Creating a daemon thread - this thread will end when main thread ends
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20000);
                    System.out.println("Daemon thread running.. [" + Thread.currentThread().getName() + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemonThread.setDaemon(true);
        // ##### Uncomment following lines for demo
        // daemonThread.start();


        /*

            Thread Life cycle [see thread-states.png]
                - In Java, a thread always exists in any one of the following states. These states are:
                    - NEW
                        - Whenever a new thread is created, it is always in the new state. For a thread in the new
                            state, the code has not been run yet and thus has not begun its execution.

                    - Active (Runnable and Running)
                        When a thread invokes the start() method, it moves from the new state to the active state.
                        The active state contains two states within it: one is runnable, and the other is running.

                        - RUNNABLE : A thread, that is ready to run is then moved to the runnable state.
                            - Thread state for a runnable thread. A thread in the runnable state is executing in the
                                Java virtual machine but it may be waiting for other resources from the operating system
                                such as processor.
                            - Each and every thread runs for a short span of time and when that allocated time slice is
                            over, the thread voluntarily gives up the CPU to the other thread, so that the other threads
                            can also run for their slice of time. Whenever such a scenario occurs, all those threads
                            that are willing to run, waiting for their turn to run, lie in the runnable state.
                            In the runnable state, there is a queue where the threads lie.

                        - RUNNING: When the thread gets the CPU, it moves from the runnable to the running state.
                            Generally, the most common change in the state of a thread is from runnable to running and
                            again back to runnable.

                    - BLOCKED : Whenever a thread is inactive for a span of time (not permanently) then, either the
                        thread is in the blocked state or is in the waiting state.
                            - Thread state for a thread blocked waiting for a monitor lock. A thread in the blocked state
                            is waiting for a monitor lock to enter a synchronized block/method or reenter a synchronized
                            block/method after calling Object.wait.

                            For example, a thread (let's say its name is A) may want to print some data from the printer.
                            However, at the same time, the other thread (let's say its name is B) is using the printer
                            to print some data. Therefore, thread A has to wait for thread B to use the printer.

                            Thus, thread A is in the blocked state. A thread in the blocked state is unable to perform
                            any execution and thus never consume any cycle of the Central Processing Unit (CPU).
                            Hence, we can say that thread A remains idle until the thread scheduler reactivates thread A,
                            which is in the waiting or blocked state.

                            When the main thread invokes the join() method then, it is said that the main thread is in
                            the waiting state. The main thread then waits for the child threads to complete their tasks.
                            When the child threads complete their job, a notification is sent to the main thread, which
                            again moves the thread from waiting to the active state.


                    - WAITING : Thread state for a waiting thread.
                        A thread in the waiting state is waiting for another thread to perform a particular action.
                        For example, a thread that has called Object.wait() on an object is waiting for another thread
                        to call Object.notify() or Object.notifyAll() on that object.
                        A thread that has called Thread.join() is waiting for a specified thread to terminate.
                        A thread is in the waiting state due to calling one of the following methods:
                            - Object.wait with no timeout
                            - Thread.join with no timeout
                            - LockSupport.park
                    A thread in the waiting state is waiting for another thread to perform a particular action. For example, a thread that has called Object.wait() on an object is waiting for another thread to call Object.notify() or Object.notifyAll() on that object. A thread that has called Thread.join() is waiting for a specified thread to terminate.

                    - TIMED_WAITING : Thread state for a waiting thread with a specified waiting time. A thread is in
                        the timed waiting state due to calling one of the following methods with a specified positive waiting time:
                            - Thread.sleep
                            - Object.wait with timeout
                            - Thread.join with timeout
                            - LockSupport.parkNanos
                            - LockSupport.parkUntil

                    - TERMINATED : The thread has completed execution.

         */

        List<Thread> threadList = new ArrayList<>();

        Runnable threadStatusReporter = () -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printThreads(threadList);
            }
        };

        Thread statusThread = new Thread(threadStatusReporter);
        statusThread.setDaemon(true);
        // #### Uncomment following lines for demo
        // statusThread.start();

        int n = 8;
        Thread t1 = getThread(8);
        Thread t2 = getThread(20000);
        Thread t3 = getThread(30000);

        threadList.add(t1);
        threadList.add(t2);
        threadList.add(t3);

        printThreads(threadList);

        // #### Uncomment following lines for demo
        // threadList.forEach(Thread::start);

        /*


             Thread Priority
                - Every thread has a priority. Threads with higher priority are executed in preference to threads with
                lower priority.
                - When code running in some thread creates a new Thread object, the new thread has its priority
                initially set equal to the priority of the creating thread, and is a daemon thread if and only if the
                creating thread is a daemon.

                - When a Java Virtual Machine starts up, there is usually a single non-daemon thread (which typically
                calls the method named main of some designated class).
         */



    }

    private Thread getThread(int n) {
        return new Thread(() -> {
            System.out.println("Starting calculation for " + n + "th prime..");
            new PrimeNumberUtils().calculateNthPrime(n);
        });
    }

    private void printThreads(List<Thread> threadList) {
        System.out.println("\nThread list: ");
        threadList.forEach(thread -> System.out.println("[" + thread.getId() + "] Name: " + thread.getName() + " , State: " + thread.getState()));
    }
}

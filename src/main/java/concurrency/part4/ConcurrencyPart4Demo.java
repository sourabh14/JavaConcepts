package concurrency.part4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import concurrency.part2.PrimeNumberUtils;

public class ConcurrencyPart4Demo {
    public void execute() {
        System.out.println("\n\n ----- Joins, Interrupts  -----");
        /*
            Joins:
                - What if you want to wait for all the threads that you've created, to end before your thread ends.
                - Use case: print a message after last thread ends.
                - The way to make a thread wait for another thread is by using join.

                - The join method allows one thread to wait for the completion of another. If t is a Thread object
                    whose thread is currently executing,
                        t.join();
                    causes the current thread to pause execution until t's thread terminates.
                - Overloads of join allow the programmer to specify a waiting period. However, as with sleep, join is
                    dependent on the OS for timing, so you should not assume that join will wait exactly as long as you specify.
        */

        // Print a message after t1 and t2 ends
        System.out.println("\nThread Join Demo");
        Thread t1 = getThread(2);
        Thread t2 = getThread(5);

        // #### Uncomment following lines for demo ####
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // The following line will be executed when t1 and t2 have been terminated
//        System.out.println("-- Threads t1 and t2 have been terminated --");
        // #### ####

        // This concept of main thread waiting for other thread is known as Barrier Synchronization.
        // It is the end point that we are synchronizing. With joining all threads, the end point are synchronized.



        /*
            Interrupt:
                An interrupt is an indication to a thread that it should stop what it is doing and do something else.
                It's up to the programmer to decide exactly how a thread responds to an interrupt, but it is very common
                for the thread to terminate.

                A thread sends an interrupt by invoking interrupt on the Thread object for the thread to be interrupted.
                For the interrupt mechanism to work correctly, the interrupted thread must support its own interruption.

                Supporting Interruption
                    - How does a thread support its own interruption? This depends on what it's currently doing. If the
                    thread is frequently invoking methods that throw InterruptedException, it simply returns from the
                    run method after it catches that exception.

                    Many methods that throw InterruptedException, such as sleep, are designed to cancel their current
                    operation and return immediately when an interrupt is received.

                    What if a thread goes a long time without invoking a method that throws InterruptedException?
                    Then it must periodically invoke Thread.interrupted, which returns true if an interrupt has been
                    received. For example:

                    for (int i = 0; i < inputs.length; i++) {
                        heavyCrunch(inputs[i]);
                        if (Thread.interrupted()) {
                            // We've been interrupted: no more crunching.
                            return;
                        }
                    }
                    In this simple example, the code simply tests for the interrupt and exits the thread if one has
                    been received. In more complex applications, it might make more sense to throw an InterruptedException:

                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    This allows interrupt handling code to be centralized in a catch clause.
   */

        System.out.println("\nThread Interrupt Demo");
        // This thread will ideally run for 4 * 5 i,e. 20 seconds
        Thread t3 = getThread(4);
        // we will try to interrupt this after 10 seconds from another thread
        Thread interruptingThread = getInterruptingThread(2, t3);

        // #### Uncomment following lines for demo ####
//         t3.start();
//         interruptingThread.start();
        // #### ####
    }

    private Thread getThread(int n) {
        return new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(n * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("Thread [" + Thread.currentThread().getName() + "] running");
            }
            System.out.println("Thread [" + Thread.currentThread().getName() + "] completed");
        });
    }

    private Thread getInterruptingThread(int n, Thread t) {
        return new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(n * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Interrupting Thread [" + Thread.currentThread().getName() + "] running");
            }
            System.out.println("Interrupting Thread [" + Thread.currentThread().getName() + "] completed");
            t.interrupt();
        });
    }







}

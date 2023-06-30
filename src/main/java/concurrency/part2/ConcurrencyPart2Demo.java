package concurrency.part2;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ConcurrencyPart2Demo {
    public void execute() {
        System.out.println("\n\n ----- Threads Introduction -----");
        /*
            Thread Class (java.lang.Thread)
                - A thread is a thread of execution in a program.
                - The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
                - implements Runnable - which is a functional interface with method run(). Indicates that something that can be run.
                - The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
                    The class must define a method of no arguments called run.

            Steps to create a thread:
                - Let's say you have a piece of code that you want to execute in a separate thread.
                - Put it into a Runnable class
                - Create a new thread from that runnable
                - Start the thread

            When does a thread end
                - when the run method returns
                - if an exception is thrown

        */
        System.out.println("Main thread | Thread[" + Thread.currentThread().getName() + "]");

        // Implementing a Runnable
        Runnable myRunnable = new MyRunnable();
        Thread myThread = new Thread(myRunnable);
        myThread.start();

        // Creating thread using a lambda
        Runnable printHelloWorld = () -> System.out.println("Hello World !!    | Thread[" + Thread.currentThread().getName() + "]");
        Thread thread = new Thread(printHelloWorld);
        thread.start();

        // Practical usage of Thread API - Calculate nth prime number
        // for large value of n, this is a heavy operation
        System.out.println("\nCalculate nth prime - Without multi threading ");
        // #### Uncomment following lines for demo ####
//        getNthPrime();
        // #### ####

        System.out.println("\nCalculate nth prime - with multi threading");
        // #### Uncomment following lines for demo ####
//        getNthPrimeMultithreading();
        // #### ####
    }

    private void getNthPrime() {
        for (Integer i : IntStream.range(1, 3).toArray()) {
            System.out.println("Enter input n: ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            // If we enter n as 50000, this takes about 40 seconds to calculate
            new PrimeNumberUtils().calculateNthPrime(n);

        }
    }

    private void getNthPrimeMultithreading() {
        for (Integer i : IntStream.range(1, 3).toArray()) {
            System.out.println("Enter input n: ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            // We can now spawn a new thread and continue taking input. This way we won't be blocked if
            // calculation takes more time.
            new Thread(() -> new PrimeNumberUtils().calculateNthPrime(n)).start();
        }
    }
}

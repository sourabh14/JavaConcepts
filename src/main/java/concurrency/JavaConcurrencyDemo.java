package concurrency;

import concurrency.part1.ConcurrencyPart1Demo;
import concurrency.part10.ConcurrencyPart10Demo;
import concurrency.part2.ConcurrencyPart2Demo;
import concurrency.part3.ConcurrencyPart3Demo;
import concurrency.part4.ConcurrencyPart4Demo;
import concurrency.part5.ConcurrencyPart5Demo;
import concurrency.part6.ConcurrencyPart6Demo;
import concurrency.part7.ConcurrencyPart7Demo;
import concurrency.part8.ConcurrencyPart8Demo;
import concurrency.part9.ConcurrencyPart9Demo;

public class JavaConcurrencyDemo {
    public void execute() {
        /*
            Agenda:
                - Threads
                - Process
                - Using threads
                - Daemon threads
                - Life cycle and thread states
                - Sleeping, joining and interrupting threads
                - Race conditions
                - Synchronizations
                - Monitors and structured locking
                - Volatile keyword
                - Thread local
                - Advanced concurrency APIs
                - Unstructured locks
                - Executor service
                - Thread pool
                - Callables and Futures
                - Semaphore
                - Fork join framework
         */

        new ConcurrencyPart1Demo().execute();
        new ConcurrencyPart2Demo().execute();
        new ConcurrencyPart3Demo().execute();
        new ConcurrencyPart4Demo().execute();
        new ConcurrencyPart5Demo().execute();
        new ConcurrencyPart6Demo().execute();
        new ConcurrencyPart7Demo().execute();
        new ConcurrencyPart8Demo().execute();
        new ConcurrencyPart9Demo().execute();
        new ConcurrencyPart10Demo().execute();
    }
}

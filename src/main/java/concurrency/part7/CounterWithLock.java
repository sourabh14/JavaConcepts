package concurrency.part7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterWithLock implements Runnable {
    private int value = 0;
    private Lock myLock = new ReentrantLock();

    public void increment() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value++;
    }

    public void decrement() {
        value--;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        // Acquire a lock
        myLock.lock();
        try {
            this.increment();
            System.out.println("[" + Thread.currentThread().getName() + "] increments " + this.getValue());

            this.decrement();
            System.out.println("[" + Thread.currentThread().getName() + "] decrements " + this.getValue());
        } finally {
            // Release a lock
            // if above code throws an exception then we have to take care of unlocking hence put it in finally
            myLock.unlock();
        }

    }
}

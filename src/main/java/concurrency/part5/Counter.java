package concurrency.part5;

public class Counter implements Runnable {
    private int value = 0;

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
        // Remove synchronized block to demo race condition
        // synchronized (this) {
            this.increment();
            System.out.println("[" + Thread.currentThread().getName() + "] increments " + this.getValue());

            this.decrement();
            System.out.println("[" + Thread.currentThread().getName() + "] decrements " + this.getValue());
        // }

    }
}

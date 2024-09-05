package concurrency.part2;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Piece of code that needs to be executed in a separate thread    | Thread[" + Thread.currentThread().getName() + "]");
    }
}

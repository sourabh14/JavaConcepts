package concurrency.part7;

public class ThreadLocalDemo implements Runnable {
    ThreadLocal<Integer> integerThreadLocal = new InheritableThreadLocal<>();
    int instanceVariable = 0;

    @Override
    public void run() {
        integerThreadLocal.set((int) (Math.random() * 1000));
        instanceVariable = (int) (Math.random() * 1000);

        try {
            System.out.println("[" + Thread.currentThread().getName() + "] sleeping for 2 sec..");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // instanceVariable will be overriden but not integerThreadLocal
        System.out.println("[" + Thread.currentThread().getName() + "] value of thread local: " + integerThreadLocal.get());
        System.out.println("[" + Thread.currentThread().getName() + "] value of instanceVariable: " + instanceVariable);
    }
}

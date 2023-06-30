package concurrency.part6;

public class ConcurrencyPart6Demo {
    public void execute() {
        System.out.println("\n\n ----- Liveness, Deadlock, Starvation, Livelock   -----");
        /*
            Liveness
                - A concurrent application's ability to execute in a timely manner is known as its liveness.
                - State of general activity and motion. The system is making any progress.
                - Basically, the application is not stuck.
                - Liveness issues with concurrency:
                    - Deadlock
                    - Livelock
                    - Starvation

            Deadlock
                - Deadlock describes a situation where two or more threads are blocked forever, waiting for each other.
                - Real life example: "No you hang up first" problem
                synchronized(obj1) {
                    synchronized(obj2) {
                        // ...
                    }
                }

                synchronized(obj2) {
                    synchronized(obj1) {
                        // ...
                    }
                }

                - Other variants
                    - circular invocation of synchronized methods
                    - two threads are invoking join on each other


                Livelock:
                    - A thread often acts in response to the action of another thread. If the other thread's action is
                    also a response to the action of another thread, then livelock may result. As with deadlock,
                    livelocked threads are unable to make further progress. However, the threads are not blocked —
                    they are simply too busy responding to each other to resume work.

                    - Real life example: This is comparable to two people attempting to pass each other in a corridor:
                    Alphonse moves to his left to let Gaston pass, while Gaston moves to his right to let Alphonse pass.
                    Seeing that they are still blocking each other, Alphone moves to his right, while Gaston moves to his left.
                    They're still blocking each other, so...
                    - Another example: stalemate in chess

                    - Naive solution to deadlock:
                        - Try to get lock 1
                        - Try to get lock 2
                        - if lock 2 is not acquired in 10 ms then, release lock 1
                        - Try again after sometime
                      However, this doesn't guarantee that deadlock will be resolved. Let's suppose two threads are doing
                      just the same thing.
                  - IOW, steps are taken to mitigate deadlock, however, it causes perpetual "corrective" action.

                Starvation
                    - Thread is ready to run but is never given a chance. This happens when shared resources are made
                        unavailable for long periods by "greedy" threads.
                    - Starvation describes a situation where a thread is unable to gain regular access to shared resources
                    and is unable to make progress.
                    - Low priority threads are not scheduled by executor.
                    - For example, suppose an object provides a synchronized method that often takes a long time to
                        return. If one thread invokes this method frequently, other threads that also need frequent
                        synchronized access to the same object will often be blocked.
                    - Real life example: talented cricket players are not given chance to play because some established
                        players have solidified their position in the team.


                How to avoid deadlock?
                    - No Java/JVM feature to avoid these.
                    - Just be careful while using locks. Eg: avoid using more than one lock


         */

        Integer lock1 = 0, lock2 = 0;

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("[" + Thread.currentThread().getName() + "] acquired lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("[" + Thread.currentThread().getName() + "] acquired lock2");
                }
                System.out.println("[" + Thread.currentThread().getName() + "] released lock2");
            }
            System.out.println("[" + Thread.currentThread().getName() + "] released lock1");
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("[" + Thread.currentThread().getName() + "] acquired lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("[" + Thread.currentThread().getName() + "] acquired lock1");
                }
                System.out.println("[" + Thread.currentThread().getName() + "] released lock1");
            }
            System.out.println("[" + Thread.currentThread().getName() + "] released lock2");
        });


        // #### Uncomment following lines for demo ####
//        t1.start();
//        t2.start();
        // ####

        /*
            Shared multiprocessor architecture [see shared-multiprocessor-architecture.png]
                - As CPUs can carry out many instructions per second, fetching from RAM isn't that ideal for them. To
                    improve this situation, processors are using tricks like Out of Order Execution, Branch Prediction,
                    Speculative Execution, and, of course, Caching. This is where the following memory hierarchy comes
                    into play:
                - As different cores execute more instructions and manipulate more data, they fill up their caches with
                    more relevant data and instructions. This will improve the overall performance at the expense of
                    introducing cache coherence challenges.

            Cache coherence problem:
                - Consider following example:

                    public class TaskRunner {
                        private static int number;
                        private static boolean ready;

                        private static class Reader extends Thread {
                            @Override
                            public void run() {
                                while (!ready) {
                                    Thread.yield();
                                }
                                System.out.println(number);
                            }
                        }

                        public static void main(String[] args) {
                            new Reader().start();
                            number = 42;
                            ready = true;
                        }
                    }

                - The TaskRunner class maintains two simple variables. In its main method, it creates another thread
                    that spins on the ready variable as long as it's false. When the variable becomes true, the thread
                    will simply print the number variable.

                - Many may expect this program to simply print 42 after a short delay; however, in reality, the delay
                    may be much longer. It may even hang forever or print zero.
                 - The cause of these anomalies is the lack of proper memory visibility and reordering.

            Memory Visibility
                - we have two application threads: the main thread and the reader thread.
                - Let's imagine a scenario in which the OS schedules those threads on two different CPU cores, where:
                    - The main thread has its copy of ready and number variables in its core cache.
                    - The reader thread ends up with its copies, too.
                    - The main thread updates the cached values.
                - On most modern processors, write requests won't be applied right after they're issued. In fact,
                    processors tend to queue those writes in a special write buffer. After a while, they'll apply those
                    writes to the main memory all at once.
                - With all that being said, when the main thread updates the number and ready variables, there's
                no guarantee about what the reader thread may see. In other words, the reader thread may see the
                updated value right away, with some delay, or never at all.
                - This memory visibility may cause liveness issues in programs relying on visibility.

            Reordering
                -To make matters even worse, the reader thread may see those writes in an order other than the actual
                program order. For instance, since we first update the number variable:

                    public static void main(String[] args) {
                        new Reader().start();
                        number = 42;
                        ready = true;
                    }

                    We may expect the reader thread to print 42. But it's actually possible to see zero as the printed value
                        (default value of int).

                - Reordering is an optimization technique for performance improvements. Interestingly, different
                components may apply this optimization:

                    - The processor may flush its write buffer in an order other than the program order.
                    - The processor may apply an out-of-order execution technique.
                    - The JIT compiler may optimize via reordering.

            Volatile keyword
            -----------------
                - To ensure that updates to variables propagate predictably to other threads, we should apply the volatile
                modifier to those variables:

                    public class TaskRunner {

                        private volatile static int number;
                        private volatile static boolean ready;

                        // same as before
                    }

                - This way, we communicate with runtime and processor to not reorder any instruction involving the
                    volatile variable. Also, processors understand that they should flush any updates to these variables
                    right away.
                - Every read from a thread is directly from main memory and every write from a thread is made directly
                    to the main memory.


           volatile and Thread Synchronization
                - For multithreaded applications, we need to ensure a couple of rules for consistent behavior:
                    - Mutual Exclusion – only one thread executes a critical section at a time
                    - Visibility – changes made by one thread to the shared data are visible to other threads to maintain data consistency
                - synchronized methods and blocks provide both of the above properties at the cost of application performance.
                - volatile is quite a useful keyword because it can help ensure the visibility aspect of the data change
                    without providing mutual exclusion.
                - Thus, it's useful in the places where we're ok with multiple threads executing a block of code in
                    parallel, but we need to ensure the visibility property.

            Immutable Objects
                - An object is considered immutable if its state cannot change after it is constructed. Maximum reliance
                on immutable objects is widely accepted as a sound strategy for creating simple, reliable code.
                - Immutable objects are particularly useful in concurrent applications. Since they cannot change state,
                they cannot be corrupted by thread interference or observed in an inconsistent state.

            A Strategy for Defining Immutable Objects
                - The following rules define a simple strategy for creating immutable objects.
                    - Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
                    - Make all fields final and private.
                    - Don't allow subclasses to override methods. The simplest way to do this is to declare the class
                        as final. A more sophisticated approach is to make the constructor private and construct
                        instances in factory methods.
                    - If the instance fields include references to mutable objects, don't allow those objects to be changed:
                    - Don't provide methods that modify the mutable objects.
                    - Don't share references to the mutable objects. Never store references to external, mutable
                        objects passed to the constructor; if necessary, create copies, and store references to the
                        copies. Similarly, create copies of your internal mutable objects when necessary to avoid
                        returning the originals in your methods.


         */

    }
}












package concurrency.part5;

public class ConcurrencyPart5Demo {
    public void execute() {
        System.out.println("\n\n ----- Concurrency vs Parallelism, Race conditions, Synchronization   -----");
        /*
            Scheduler
                - The cpu scheduler is responsible for scheduling a thread to be executed by core
                - It unschedules a running thread temporarily, as needed.
                - Scheduler tries to be fair, so that every thread gets a chance.
                - It also considers priority, some thread may be higher in priority, so they get more cpu.

            Parallelism
                - Parallelism means, we have two cpu cores and two threads are running simultaneously.
                - It basically means we are, running many different tasks at the same time.
                - By definition parallelism needs multiple cores. We cannot achieve parallelism with one core.

            Concurrency
                - Let's say we have more threads than no of cores. At a particular moment there will be two threads running,
                     but overall, they are running and making progress.
                - Therefore, concurrency is multiple tasks are in progress at the same time, although all of them may not
                    be running.
                - Concurrency can be achieved with single core, or multi-core cpus.

            CPU architecture [See processor-core-with-cache.png]
                - Each core comes with its own memory called processor cache - L1, L2
                - The memory RAM is system memory
                - When we say int i=0; i++; a copy of i is made in cache, then it is processed
                and then it is copied back to the system memory.
                - For single threaded application there is no problem, but for multi-threaded applications,
                this can create race conditions. One thread can try read the value of i from system memory,
                meanwhile other thread is copying it from cache to the system memory.

            Common race condition patterns:
                ==> Pattern 1: Check-then-Act a.k.a Memory Consistency Errors:
                    - Let's say we have a thread 1 which checks if value is 0 and then prints

                    [thread 1]
                        if (value == 0) {
                            System.out.println(value + 1);
                        }

                    Ideally this should print 1. But there might be case when another thread changes value after condition
                    is satisfied, and before print statement.

                    [thread 2]
                        value = 10;

                    - Memory consistency errors occur when different threads have inconsistent views of what should be the same data.

                 ==> Pattern 2: Read-modify-write a.k.a Thread Interference:
                    - Incrementing value
                        value++;

                        This includes multiple actions:
                            - fetch value from memory to cache
                            - increment value
                            - write incremented value to memory

                      - A second thread can change value
                      - Interference happens when two operations, running in different threads, but acting on the same
                      data, interleave. This means that the two operations consist of multiple steps, and the sequences
                      of steps overlap.


                Solving race conditions - Lock and Key Model:
                    - Make sure only one thread picks the data element.
                    - The way to achieve is through - "Lock and key model"


            Synchronization:
            ----------------
                - Threads communicate primarily by sharing access to fields and the objects reference fields refer
                to. This form of communication is extremely efficient, but makes two kinds of errors possible:
                    - thread interference and
                    - memory consistency errors.
                - The tool needed to prevent these errors is synchronization.

                - Synchronization is to make sure that two threads don't simultaneously access a critical data element.
                - This feature is provided by JVM.

                - However, synchronization can introduce thread contention, which occurs when two or more threads
                try to access the same resource simultaneously and cause the Java runtime to execute one or more
                threads more slowly, or even suspend their execution. Starvation and livelock are forms of thread contention.

        The Synchronized Keyword
            - We can use the synchronized keyword on different levels:
                Instance methods
                Static methods
                Code blocks

            Synchronized Method
                - We can add the synchronized keyword in the method declaration to make the method synchronized:
                - Here lock is associated with the object whose method is being called.
                - You might wonder what happens when a static synchronized method is invoked, since a static method is
                    associated with a class, not an object. In this case, the thread acquires the intrinsic lock for the
                    Class object associated with the class. Thus access to class's static fields is controlled by a lock
                    that's distinct from the lock for any instance of the class.


            Synchronized Block (a.k.a Intrinsic lock or monitor lock)
                - A piece of logic marked with synchronized becomes a synchronized block, allowing only one thread to
                    execute at any given time.
                - The synchronized block is known as critical section (CS)
                - Common use case : protect member variables
                - When we use a synchronized block, Java internally uses a monitor, also known as a monitor lock or
                    intrinsic lock, to provide synchronization. These monitors are bound to an object; therefore, all
                    synchronized blocks of the same object can have only one thread executing them at the same time.
                - The lock release occurs even if the return was caused by an uncaught exception.
                - Steps :
                    - Thread tries to get access to monitor lock.
                    - If it gets access, it executes the block.
                    - Releases the monitor lock when exiting the block
                    - Ano other thread needs to wait


                - Example:
                    public void addName(String name) {
                        synchronized(this) {
                            lastName = name;
                            nameCount++;
                        }
                        nameList.add(name);
                    }
                - In this example, the addName method needs to synchronize changes to lastName and nameCount, but also
                needs to avoid synchronizing invocations of other objects' methods. (Invoking other objects' methods from
                synchronized code can create problems that are described in the section on Liveness.) Without synchronized
                statements, there would have to be a separate, unsynchronized method for the sole purpose of invoking
                nameList.add.

                Synchronized statements are also useful for improving concurrency with fine-grained synchronization.
                Suppose, for example, class MsLunch has two instance fields, c1 and c2, that are never used together.
                All updates of these fields must be synchronized, but there's no reason to prevent an update of c1 from
                being interleaved with an update of c2 — and doing so reduces concurrency by creating unnecessary blocking.
                Instead of using synchronized methods or otherwise using the lock associated with this, we create two
                objects solely to provide locks.

                public class MsLunch {
                    private long c1 = 0;
                    private long c2 = 0;
                    private Object lock1 = new Object();
                    private Object lock2 = new Object();

                    public void inc1() {
                        synchronized(lock1) {
                            c1++;
                        }
                    }

                    public void inc2() {
                        synchronized(lock2) {
                            c2++;
                        }
                    }
                }

        Structured lock
            - The synchronized block/keyword is referred to as structured lock.
            - Acquiring and releasing lock is implicit.
            - Benefit: If synchronized block throws an exception, and control goes out of synchronized block, then the
                jvm automatically releases the lock. We dont have to worry about releasing the lock.



        */

        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread One");
        Thread t2 = new Thread(counter, "Thread Two");
        Thread t3 = new Thread(counter, "Thread Three");
        Thread t4 = new Thread(counter, "Thread Four");

        // #### Uncomment following lines for demo ####
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
        //#### ####

        /*
            Goals of synchronization
            - Mutual exclusion (mutex) : A given piece of critical section is mutually exclusive, i.e. only one thread can run it at a time.
            - Visibility : Value is read from memory before block execution. Value is written to memory after block execution.
                The jvm will ensure that the value is not just sitting at the processor cache, it is gonna force write the value
                to the memory.

            How to handle concurrency issues:
                - don't have shared state
                - share only immutable objects bw threads
                - use synchronization

            Thread safe:
                When we handle concurrency issues, we call it thread-safe i.e. we are able to avoid race conditions.

            Challenges with synchronized blocks
                - Performance: there is a cost of adding synchronization block, creating and managing threads
                - Mutex locks:
                    - have to be applied carefully, choose the right object for lock
                    - synchronize the bare minimum, or else if we synchronize everything then it becomes just like single
                    threaded program

            Problem 1: Choosing the wrong lock
                - Remember: it has to be the same lock

                public void methodA() {
                    synchronized (obj1) {
                        ...
                        methodB();
                    }
                }

                public void methodB() {
                    synchronized (obj2) {

                    }
                }

                Let's say Thread1 calls methodA and Some other thread calls methodB. If you want to ensure that if a
                thread enters methodA and nothing should call methodB then we need to make sure that obj1 and obj2 are same.
                This is the case where methodA and methodB are synchronized individually but not together. Make sure you
                choose right object for lock.


            Problem 2: Extreme synchronization
                - Non-concurrent serial code

         */

    }
}












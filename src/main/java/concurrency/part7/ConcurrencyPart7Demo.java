package concurrency.part7;

import concurrency.part5.Counter;

public class ConcurrencyPart7Demo {
    public void execute() {
        System.out.println("\n\n ----- Thread local, Lock object   -----");
        /*
           High Level Concurrency Objects
            - higher-level building blocks are needed for more advanced tasks. This is especially true for massively
                concurrent applications that fully exploit today's multiprocessor and multi-core systems.


            Use case:
                - Let's say you want to access a variable in multiple places in a thread
                Eg:
                    Runnable r = () -> processUserData(userId);

                    public void processUserData(int userId) {
                        doSomeStuff();
                        doAnotherStuff();
                        moreStuff();
                    }
                You wanna access userId in multiple places in thread
                Options
                    - Pass around variable everywhere
                        - Drawback: what if it is a bunch of data, you need to add extra variable to method signature
                    - Use a class member variable. You save it and then every thread member reads from it.
                        - Drawback: this is prone to synchronization issues


            ThreadLocal (java.lang.ThreadLocal<T>)
                - The TheadLocal construct allows us to store data that will be accessible only by a specific thread.
                - ThreadLocal instances are typically private static fields in classes that wish to associate
                    state with a thread (e.g., a user ID or Transaction ID).
                - Each thread holds an implicit reference to its copy of a thread-local variable as long as the
                    thread is alive and the ThreadLocal instance is accessible; after a thread goes away, all of its
                    copies of thread-local instances are subject to garbage collection (unless other references to
                    these copies exist).
                - It is another way to achieve thread-safety apart from writing immutable classes. Thread local can be
                    considered as a scope of access like session scope or request scope. In thread local, you can set
                    any object and this object will be local and global to the specific thread which is accessing this object.
                - It enables you to create variables that can only be read and write by the same thread. If two threads
                    are executing the same code and that code has a reference to a ThreadLocal variable then the two
                    threads can't see the local variable of each other.

                Characteristics of ThreadLocal:
                    - Scope is per-thread
                    - Global in context of thread instance
                    - Each thread just sees its own thread local variable

                 ThreadLocal<Integer> threadLocalUserId = new ThreadLocal<>();

                 - Everytime a thread starts, it's only then it creates a space for threadLocalUserId
                 - if we save something in threadLocalUserId in threadA, and we try to retrieve threadLocalUserId from
                    threadB, they wont be same.

                    threadLocalUserId.set(100);
                    Integer userId = threadLocalUserId.get();

                - ThreadLocal can be also used to store context
                - Spring uses ThreadLocal and ContextHolder in many classes

         */

        System.out.println("\nThread local demo ---");
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        Thread demo1 = new Thread(threadLocalDemo);
        Thread demo2 = new Thread(threadLocalDemo);

        demo1.start(); demo2.start();

        try {
            demo1.join();
            demo1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
            Structured Locks:
                - synchronized keyword was structured lock
                - acquiring and releasing lock was already handled
                - nesting of synchronized was possible

            Problem statement:
                for (int i=0; i<(arrSize - 2); i++) {
                    process(arr[i], arr[i+1]);
                }

                Let's suppose I want to implement synchronization for this and make arr[i] and arr[i+1] thread safe
                while we are processing. We can do this by nested synchronization:

                for (int i=0; i<(arrSize - 2); i++) {
                    synchronized (arr[i]) {
                        synchronized (arr[i+1]) {
                            process(arr[i], arr[i+1]);
                        }
                    }
                }

                Issue:
                    - In first iteration we acquire lock for arr[0] and arr[1], then in next we acquire lock for
                    arr[1] and arr[2], and so on..
                    - The better way would be to release lock for arr[0] and acquire lock for arr[2]. We dont have to release
                    arr[1] lock.
                    - This is known as hand-over-hand locking. Here synchronized lock won't work.

            Lock Objects (Unstructured Lock)
            ---------------------------------
            -Lock implementations provide more extensive locking operations than can be obtained using synchronized
                methods and statements. They allow more flexible structuring

         */
        System.out.println("\nLock demo ---");
        CounterWithLock counter = new CounterWithLock();
        Thread t1 = new Thread(counter);
        Thread t2 = new Thread(counter);
        Thread t3 = new Thread(counter);
        Thread t4 = new Thread(counter);

        // #### Uncomment following lines for demo ####
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}












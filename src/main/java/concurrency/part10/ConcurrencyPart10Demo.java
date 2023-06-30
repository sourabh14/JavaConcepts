package concurrency.part10;

public class ConcurrencyPart10Demo {
    public void execute() {
        System.out.println("\n\n ----- Thread Pool Executor  -----");
        /*
           Thread Pool Executor:
                - An ExecutorService that executes each submitted task using one of possibly several pooled threads,
                    normally configured using Executors factory methods.
                - Thread pools address two different problems: they usually provide improved performance when executing
                    large numbers of asynchronous tasks, due to reduced per-task invocation overhead, and they provide a
                    means of bounding and managing the resources, including threads, consumed when executing a collection
                    of tasks.
                - Each ThreadPoolExecutor also maintains some basic statistics, such as the number of completed tasks.

            Core and maximum pool sizes
                - The pool consists of a fixed number of core threads that are kept inside all the time. It also
                    consists of some excessive threads that may be spawned and then terminated when they are no longer needed.

                - Core pool threads - kept alive all the time
                - Excess threads (Max pool - core pool) : created and destroyed based on requirement

                - The corePoolSize parameter is the number of core threads that will be instantiated and kept in the pool.
                    When a new task comes in, if all core threads are busy and the internal queue is full, the pool is
                    allowed to grow up to maximumPoolSize.

                - If fewer than corePoolSize threads are running, the Executor always prefers adding a new thread rather than queuing.
                - If corePoolSize or more threads are running, the Executor always prefers queuing a request rather than adding a new thread.
                - If a request cannot be queued, a new thread is created unless this would exceed maximumPoolSize, in
                    which case, the task will be rejected.


                - A ThreadPoolExecutor will automatically adjust the pool size (see getPoolSize()) according to the
                    bounds set by corePoolSize (see getCorePoolSize()) and maximumPoolSize (see getMaximumPoolSize()).
                - When a new task is submitted in method execute(Runnable), and fewer than corePoolSize threads are
                    running, a new thread is created to handle the request, even if other worker threads are idle.
                - If there are more than corePoolSize but less than maximumPoolSize threads running, a new thread
                    will be created only if the queue is full.
                - By setting corePoolSize and maximumPoolSize the same, you create a fixed-size thread pool. By setting
                    maximumPoolSize to an essentially unbounded value such as Integer.MAX_VALUE, you allow the pool to
                    accommodate an arbitrary number of concurrent tasks.
                - Most typically, core and maximum pool sizes are set only upon construction, but they may also be
                    changed dynamically using setCorePoolSize(int) and setMaximumPoolSize(int).

                - The keepAliveTime parameter is the interval of time for which the excessive threads (instantiated in
                    excess of the corePoolSize) are allowed to exist in the idle state. By default, the ThreadPoolExecutor
                    only considers non-core threads for removal. In order to apply the same removal policy to core
                    threads, we can use the allowCoreThreadTimeOut(true) method.

                - These parameters cover a wide range of use cases, but the most typical configurations are predefined in the Executors static methods.

            On-demand construction
                By default, even core threads are initially created and started only when new tasks arrive.

            Creating new threads
                New threads are created using a ThreadFactory. If not otherwise specified, a Executors.defaultThreadFactory()
                is used, that creates threads to all be in the same ThreadGroup and with the same NORM_PRIORITY priority
                and non-daemon status. By supplying a different ThreadFactory, you can alter the thread's name, thread
                group, priority, daemon status, etc. If a ThreadFactory fails to create a thread when asked by
                returning null from newThread, the executor will continue, but might not be able to execute any tasks. Threads should possess the "modifyThread" RuntimePermission. If worker threads or other threads using the pool do not possess this permission, service may be degraded: configuration changes may not take effect in a timely manner, and a shutdown pool may remain in a state in which termination is possible but not completed.
                Keep-alive times
                If the pool currently has more than corePoolSize threads, excess threads will be terminated if they have been idle for more than the keepAliveTime (see getKeepAliveTime(TimeUnit)). This provides a means of reducing resource consumption when the pool is not being actively used. If the pool becomes more active later, new threads will be constructed. This parameter can also be changed dynamically using method setKeepAliveTime(long, TimeUnit). Using a value of Long.MAX_VALUE TimeUnit.NANOSECONDS effectively disables idle threads from ever terminating prior to shut down. By default, the keep-alive policy applies only when there are more than corePoolSize threads. But method allowCoreThreadTimeOut(boolean) can be used to apply this time-out policy to core threads as well, so long as the keepAliveTime value is non-zero.
                Queuing
                Any BlockingQueue may be used to transfer and hold submitted tasks. The use of this queue interacts with pool sizing:
                If fewer than corePoolSize threads are running, the Executor always prefers adding a new thread rather than queuing.
                If corePoolSize or more threads are running, the Executor always prefers queuing a request rather than adding a new thread.
                If a request cannot be queued, a new thread is created unless this would exceed maximumPoolSize, in which case, the task will be rejected.
                There are three general strategies for queuing:
                Direct handoffs. A good default choice for a work queue is a SynchronousQueue that hands off tasks to threads without otherwise holding them. Here, an attempt to queue a task will fail if no threads are immediately available to run it, so a new thread will be constructed. This policy avoids lockups when handling sets of requests that might have internal dependencies. Direct handoffs generally require unbounded maximumPoolSizes to avoid rejection of new submitted tasks. This in turn admits the possibility of unbounded thread growth when commands continue to arrive on average faster than they can be processed.
                Unbounded queues. Using an unbounded queue (for example a LinkedBlockingQueue without a predefined capacity) will cause new tasks to wait in the queue when all corePoolSize threads are busy. Thus, no more than corePoolSize threads will ever be created. (And the value of the maximumPoolSize therefore doesn't have any effect.) This may be appropriate when each task is completely independent of others, so tasks cannot affect each others execution; for example, in a web page server. While this style of queuing can be useful in smoothing out transient bursts of requests, it admits the possibility of unbounded work queue growth when commands continue to arrive on average faster than they can be processed.
                Bounded queues. A bounded queue (for example, an ArrayBlockingQueue) helps prevent resource exhaustion when used with finite maximumPoolSizes, but can be more difficult to tune and control. Queue sizes and maximum pool sizes may be traded off for each other: Using large queues and small pools minimizes CPU usage, OS resources, and context-switching overhead, but can lead to artificially low throughput. If tasks frequently block (for example if they are I/O bound), a system may be able to schedule time for more threads than you otherwise allow. Use of small queues generally requires larger pool sizes, which keeps CPUs busier but may encounter unacceptable scheduling overhead, which also decreases throughput.
                Rejected tasks
                New tasks submitted in method execute(Runnable) will be rejected when the Executor has been shut down, and also when the Executor uses finite bounds for both maximum threads and work queue capacity, and is saturated. In either case, the execute method invokes the RejectedExecutionHandler.rejectedExecution(Runnable, ThreadPoolExecutor) method of its RejectedExecutionHandler. Four predefined handler policies are provided:
                In the default ThreadPoolExecutor.AbortPolicy, the handler throws a runtime RejectedExecutionException upon rejection.
                In ThreadPoolExecutor.CallerRunsPolicy, the thread that invokes execute itself runs the task. This provides a simple feedback control mechanism that will slow down the rate that new tasks are submitted.
                In ThreadPoolExecutor.DiscardPolicy, a task that cannot be executed is simply dropped.
                In ThreadPoolExecutor.DiscardOldestPolicy, if the executor is not shut down, the task at the head of the work queue is dropped, and then execution is retried (which can fail again, causing this to be repeated.)
                It is possible to define and use other kinds of RejectedExecutionHandler classes. Doing so requires some care especially when policies are designed to work only under particular capacity or queuing policies.
                Hook methods
                This class provides protected overridable beforeExecute(Thread, Runnable) and afterExecute(Runnable, Throwable) methods that are called before and after execution of each task. These can be used to manipulate the execution environment; for example, reinitializing ThreadLocals, gathering statistics, or adding log entries. Additionally, method terminated() can be overridden to perform any special processing that needs to be done once the Executor has fully terminated.
                If hook or callback methods throw exceptions, internal worker threads may in turn fail and abruptly terminate.

                Queue maintenance
                Method getQueue() allows access to the work queue for purposes of monitoring and debugging. Use of this method for any other purpose is strongly discouraged. Two supplied methods, remove(Runnable) and purge() are available to assist in storage reclamation when large numbers of queued tasks become cancelled.
                Finalization
                A pool that is no longer referenced in a program AND has no remaining threads will be shutdown automatically. If you would like to ensure that unreferenced pools are reclaimed even if users forget to call shutdown(), then you must arrange that unused threads eventually die, by setting appropriate keep-alive times, using a lower bound of zero core threads and/or setting allowCoreThreadTimeOut(boolean).

         */
    }
}












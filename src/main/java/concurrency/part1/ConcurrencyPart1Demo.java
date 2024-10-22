package concurrency.part1;

public class ConcurrencyPart1Demo {
    public void execute() {
        /*

            https://jenkov.com/tutorials/java-concurrency/index.html

            ------------ Threads and Process : Introduction --------

            What is a process
                - Processes are basically the programs that are dispatched from the ready state and are scheduled in
                    the CPU for execution.
                - The process is isolated means it does not share the memory with any other process.
                - They can be started and stopped without affecting others
                - A process can be single threaded - i.e. all instructions executed sequentially, or multi-threaded where
                    it can spawn threads that could run in parallel.

            What is a thread
                - A thread is a single sequential flow of control within a program. Sequence of programmed instructions
                    that can be managed independently.
                - It is a unit of execution within a process.
                - Allows the program to split into simultaneously running tasks.
                - Thread is the segment of a process which means a process can have multiple threads and these
                multiple threads are contained within a process.
                - A Thread is lightweight as each thread in a process shares code, data, and resources.
                - A thread has three states: Running, Ready, and Blocked.

            What happens when you run an application
                - Binary instructions are loaded into memory
                - The process gets access to resources, like memory such as hard disk
                - The process is allocated resources such as stack, heap, registers. These resources are protected from other processes
                - The process is executed

            How java application runs
               - The jvm process starts.
               - It consists of various threads - eg. security, garbage collection
               - There is one thread - application thread - runs main method.

            What Is Multithreading?
                 - Multithreading is a form of parallelization or dividing up work for simultaneous processing.
                    Instead of giving a large workload to a single core, threaded programs split the work into multiple
                    software threads. These threads are processed in parallel by different CPU cores to save time.
                - Analogy: A group of workers building a wall. Each worker has tasks such as - Get cement, add a layer
                    on top, get brick, place brick on layer of mortar, loop back. Each builder working on these
                    instructions is like a "thread". These workers can work in parallel.

            Cores and Processors:
                - A core is usually the basic computation unit of the CPU - it can run a single program context ,
                    maintaining the correct program state, registers, and correct execution order, and performing the
                    operations through ALUs.
                - A CPU may have one or more cores to perform tasks at a given time. These tasks are usually software
                    processes and threads that the OS schedules. Note that the OS may have many threads to run, but the
                    CPU can only run X such tasks at a given time, where X = number cores * number of hardware threads
                    per core. The rest would have to wait for the OS to schedule them whether by preempting currently
                    running tasks or any other means.
                - Many modern processors support hyper-threading: each physical core behaves as if it is actually two
                    cores, so it can run two threads simultaneously (e.g. execute one thread while the other is waiting on a cache miss).
                    For example, this mac has one processor (Intel Core i5) with 2 cores and hyper-threading enabled.
                    So it can support 4 threads at a time.
                - An individual processor core speed is not improving because of
                    hardware limitations. Therefore, to build powerful processors, we add more cores to processor chip.

             Evolution of operating system process model:
                - Early operating systems supported a single process with a single thread at a time (single tasking).
                    They ran batch jobs (one user at a time).
                - By late 1970's most operating systems supported multitasking: multiple processes could exist at once,
                    but each process had only a single thread.
                - Some early personal computer operating systems used single-tasking (e.g. MS-DOS), but these systems
                    are almost unheard of today.
                - In the 1990's systems converted to multithreading: multiple threads within each process.


        Multithreading Benefits
                - Better CPU Utilization
                - Simpler Program Design
                - More Responsive Programs
                - More Fair Distribution of CPU Resources

            Better CPU Utilization:

                    Imagine an application that reads and processes files from the local file system. Lets say that reading a file from disk takes 5 seconds and processing it takes 2 seconds. Processing two files then takes

                      5 seconds reading file A
                      2 seconds processing file A
                      5 seconds reading file B
                      2 seconds processing file B
                    -----------------------
                     14 seconds total
                    When reading the file from disk most of the CPU time is spent waiting for the disk to read the data. The CPU is pretty much idle during that time. It could be doing something else. By changing the order of the operations, the CPU could be better utilized. Look at this ordering:

                      5 seconds reading file A
                      5 seconds reading file B + 2 seconds processing file A
                      2 seconds processing file B
                    -----------------------
                     12 seconds total
                    The CPU waits for the first file to be read. Then it starts the read of the second file. While the
                    second file is being read in by the IO components of the computer, the CPU processes the first file.
                    Remember, while waiting for the file to be read from disk, the CPU is mostly idle.

            Simpler Program Design
                If you were to program the above ordering of reading and processing by hand in a singlethreaded
                application, you would have to keep track of both the read and processing state of each file.
                Instead you can start two threads that each just reads and processes a single file. Each of these
                threads will be blocked while waiting for the disk to read its file. While waiting, other threads can
                'use the CPU to process the parts of the file they have already read. The result is, that the disk is
                kept busy at all times, reading from various files into memory. This results in a better utilization
                of both the disk and the CPU. It is also easier to program, since each thread only has to keep track of a single file.

            More Responsive Programs
                Another common goal for turning a singlethreaded application into a multithreaded application is to
                achieve a more responsive application. Imagine a server application that listens on some port for incoming
                requests. when a request is received, it handles the request and then goes back to listening.
                The server loop is sketched below:

                  while(server is active){
                    listen for request
                    process request
                  }

                If the request takes a long time to process, no new clients can send requests to the server for that
                duration. Only while the server is listening can requests be received.

                An alternate design would be for the listening thread to pass the request to a worker thread, and
                 return to listening immediatedly. The worker thread will process the request and send a reply to the client. This design is sketched below:

                  while(server is active){
                    listen for request
                    hand request to worker thread
                  }

                This way the server thread will be back at listening sooner. Thus more clients can send requests to the
                server. The server has become more responsive.


            More Fair Distribution of CPU Resources
                Imagine a server that is receiving requests from clients. Imagine then, that one of the clients sends a request that takes a long time to process - e.g. 10 seconds. If the server processed all tasks using a single thread, then all requests following the request that was slow to process would be forced to wait until the full request has been processed.

                By dividing the CPU time between multiple threads and switching between the threads, then the CPU can share its execution time more fairly between several requests.




            Multithreading Costs
                - Going from a singlethreaded to a multithreaded application doesn't just provide benefits. It also has
                some costs. Don't just multithread-enable an application just because you can. You should have a well-founded
                expectation that the benefits gained by doing so are larger than the costs. When in doubt, try measuring
                the performance or responsiveness of the application, instead of just guessing.

                - More complex design
                    - Though some parts of a multithreaded applications are simpler than a singlethreaded application, other parts are more complex. Code executed by multiple threads accessing shared data need special attention. Thread interaction is far from always simple. Errors arising from incorrect thread synchronization can be very hard to detect, reproduce and fix.

                - Context Switching Overhead
                        When a CPU switches from executing one thread to executing another, the CPU needs to save the local data, program pointer etc. of the current thread, and load the local data, program pointer etc. of the next thread to execute. This switch is called a "context switch". The CPU switches from executing in the context of one thread to executing in the context of another.

                        Context switching isn't cheap. You don't want to switch between threads more than necessary.

                - Increased Resource Consumption
                    A thread needs some resources from the computer in order to run. Besides CPU time a thread needs some memory to keep its local stack. It may also take up some resources inside the operating system needed to manage the thread. Try creating a program that creates 100 threads that does nothing but wait, and see how much memory the application takes when running.




         */
    }
}

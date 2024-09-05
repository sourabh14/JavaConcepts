package concurrency.part1;

public class ConcurrencyPart1Demo {
    public void execute() {
        /*
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

         */
    }
}

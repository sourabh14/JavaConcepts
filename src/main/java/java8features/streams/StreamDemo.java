package java8features.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.Predicate;

import java8features.lambdas.Person;

public class StreamDemo {
    public static void execute() {
        System.out.println("\n\n---- Streams, ForEach() method, ----");

        /*
            forEach loop provides  a new, concise way to iterate over a collection. It performs the given action for
            each element of the Iterable until all elements have been processed or the action throws an exception

            void forEach(Consumer<? super T> action)
         */

        Consumer<String> printString = System.out::println;
        Consumer<String> printStringWithPrefix = (str) -> System.out.println("[prefix]" + str);

        List<String> names = new ArrayList<>(Arrays.asList("Harry Potter", "Tom Riddle", "Ron Weaseley", "Hermoine Granger", "Hagrid"));
        names.forEach(printString);
        names.forEach(printStringWithPrefix);


        /*
            Streams: A sequence of elements supporting sequential and parallel aggregate operations.

                 Using stream, you can process data in a declarative way similar to SQL statements.
                 For example, consider the following SQL statement.
                    SELECT max(salary), employee_id, employee_name FROM Employee
                 The above SQL expression automatically returns the maximum salaried employee's details,

                 Using collections framework in Java, a developer has to use loops and make repeated checks.
                 Another concern is efficiency; as multi-core processors are available at ease, a Java developer has to write parallel code processing that can be pretty error-prone.

                To resolve such issues, Java 8 introduced the concept of stream that lets the developer to process data
                declaratively and leverage multicore architecture without the need to write any specific code for it.

            Advantages:
                - Make more efficient java programmer
                - Heavy use of lambda expressions
                - parallel streams make it easy to multi-thread operations

            Elements of a stream:
                 - source: provides elements - colleciton
                 - intermediate operations: such as filter/ map/ sort
                 - terminal operation: such as forEach/ collect/ reduce


            Source -> Filter -> Sort -> Map -> Collect

        */


        System.out.println("\nNames starting with letter H");

        Predicate<String> startsWithLetterH = (str) -> str.startsWith("H");
        Consumer<String> printStringWithThread = (str) -> System.out.println(str + " Thread[" + Thread.currentThread().getName() + "]");

        names.stream()
                .filter(startsWithLetterH)          // this is like where clause
                .forEach(printStringWithThread);

        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("fname1", "lname1", 24),
                new Person("fname2", "lname2", 26),
                new Person("fname3", "lname2", 28),
                new Person("fname4", "lname1", 21),
                new Person("fname5", "lname3", 23)
        ));

        // Get maximum age of person with last name as as lname1
        // select max(age) from person where lastName = "lname1";

        OptionalInt maxAge = personList.stream()
                        .filter((person) -> (person.getLastName().equals("lname1")))
                        .mapToInt(Person::getAge)
                        .max();
        System.out.println("maxAge = " + (maxAge.isPresent() ? maxAge.getAsInt() : null));

        // Get first name of person with max age
        Optional<String> fname = personList.stream()
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getFirstName);
        System.out.println("fname = " + (fname.isPresent() ? fname.get() : null));

        /*
        Intermediate operations
                - zero or more intermediate operations are allowed
                - Order matters for large datasets: filter first then sort or map

                - Stream<T>	filter(Predicate<? super T> predicate) - Returns a stream consisting of the elements of this stream that match the given predicate.
                - boolean anyMatch(Predicate<? super T> predicate) - Returns whether any elements of this stream match the provided predicate.

                - Optional<T> findFirst() Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.
                - Stream<T>	distinct() Returns a stream consisting of the distinct elements (according to Object.equals(Object)) of this stream.
                - Optional<T> max(Comparator<? super T> comparator) Returns the maximum element of this stream according to the provided Comparator.

                - Stream<T>	sorted() Returns a stream consisting of the elements of this stream, sorted according to natural order.
                - <R> Stream<R>	map(Function<? super T,? extends R> mapper) Returns a stream consisting of the results of applying the given function to the elements of this stream.

         Terminal operations
            - one terminal operation is allowed
            - forEach - applies same function to to each element
            - collect - saves the elements into a collection
            - Other options reduce stream into a single element
                - count()
                - max()
                - min()
                - reduce() -
                - summaryStatistics() -
         */


        // This was sequential stream. Sequential streams use a single thread to process the pipeline:

        /*
            Parallel Streams:
                It's also very easy to create streams that execute in parallel and make use of multiple processor cores.
                We might think that it's always faster to divide the work on more cores. But that is often not the case.

                Parallel streams enable us to execute code in parallel on separate cores. The final result is the
                combination of each individual outcome.

         */

        names.parallelStream()
                .filter(startsWithLetterH)
                .forEach(printStringWithThread);
        /*
            Performance Implications:
                Parallel processing may be beneficial to fully utilize multiple cores. But we also need to consider the overhead of managing multiple threads, memory locality, splitting the source and merging the results.

            A large amount of data and many computations done per element indicate that parallelism could be a good option.

            On the other hand, a small amount of data, unevenly splitting sources, expensive merge operations and poor
            memory locality indicate a potential problem for parallel execution.

            More read: https://www.baeldung.com/java-when-to-use-parallel-stream
         */
    }
}

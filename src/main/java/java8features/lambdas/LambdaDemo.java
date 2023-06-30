package java8features.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaDemo {
    public static void execute() {
        System.out.println("\n---- Lambda expressions, Method references, Functional interfaces ----");

        /*
            Lambda expressions
                - Fundamentally, a lambda expression is just a shorter way of writing an implementation of a method
                    for later execution.
                - It enables functional programming in java.
                - Lambdas are another way of writing anonymous inner class.
                - Lambdas can be used to create readable and concise code.
                - Enables us to write better APIs and libraries
                - Enables support for parallel processing

            Why functional programming?
                - Write more readable and maintainable code. Elegant in some situations.

         */



        /* We want greeter to take input for the greeter and do different things based on input.
            - One possible way is to give argument to greet() method and create a switch statement with all possible
                combinations of all it can do. This is not elegant
            - What we want is that the behaviour itself should be passed as an argument.
                So we created a greeting interface with perform method.
                Now the greet method doesn't contain any behaviour. It takes input as behaviour and performs it.
         */

        Greeting g1 = new HelloWorldGreeting();
        Greeting g2 = new WelcomeGreeting();

        Greeter greeter = new Greeter();
        greeter.greet(g1);

        /*
            - We just want an action to be passed as a parameter, rather than a class that implements an action
            - Lambdas lets us create functions that don't belong to a class. They exist in isolation.
            - Function now can be used as values i.e. functions can be assigned to a variable
            - For a lambda expression we
                - dont need access modifier - public/private/protected etc.. makes sense when method is part of a class
                - dont need return type - java compiler can automatically identify that
                - dont need name - the variable name is used to access function
            - All that is left is
                    () {
                        System.out.println("Hello world");
                    }

                we just need to add ->
                    () -> {
                        System.out.println("Hello world");
                    }

                if our lambda expression is just one line, we can shorten it to:
                    () -> System.out.println("Hello world");

                    Eg:
                    <InterfaceName> helloWorldGreeting = () -> System.out.println("Hello World");
                    <InterfaceName> addNumbers = (int a, int b) -> (a + b);
                - we can also remove argument type
                    <InterfaceName> stringLengthCount = (s) -> s.length();
         */

        Greeting helloWorldGreeting = () -> System.out.println("Hello World");
        Greeting welcomeGreeting = () -> System.out.println("Welcome !!");

        greeter.greet(helloWorldGreeting);
        greeter.greet(welcomeGreeting);

        /*
            Note that Lambda(Greeting) interface contains perform method who's return type and argument type is same as
            that of the lambda.
            - there cannot be more than one method in lambda interface
            - lambdas are another way of writing anonynous inner class
         */

        Greeting myNewGreeting = new Greeting() {
            @Override
            public void perform() {
                System.out.println("My New Greeting");
            }
        };

        greeter.greet(myNewGreeting);


        /*
        Runnable using lambda
         */

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with anonymous inner class");
            }
        });

        thread.run();

        Thread lambdaThread = new Thread(() -> System.out.println("lambda thread"));
        lambdaThread.run();

        /*
         Functional Interface:
                - A Functional Interface is an interface that has exactly one abstract method. This is a type of interface
                that we can use to declare a lambda.
                - To designate an interface as a Functional Interface, we don’t need to use the @FunctionalInterface annotation.
                - The @FunctionalInterface annotation prevents abstract methods from being accidentally added to
                    functional interfaces. It’s similar to a @Override annotation, and it’s recommended that you use it.
                - java.lang.Runnable is a fantastic example of a functional interface since it has one abstract method, run ().
         */

        /*
            Given a list of person.
            - Sort them by last name
            - create a method that prints all elements in the list
            - create a method that prints all person that have name beginning with h
         */

        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("harry", "potter", 10),
                new Person("ron", "weaselly", 10),
                new Person("tom", "riddle", 10),
                new Person("hagrid", "", 30)
        ));

        System.out.println("--");
        sortByLastName(personList);
        System.out.println("--");
        printPerson(personList);
        System.out.println("--");
        printPersonWithLetterH(personList);
        System.out.println("--");

        // We can use lambdas to pass a condition as a parameter to print person with letter h
        Condition startsWithLetterH = (Person p) -> p.getFirstName().startsWith("h");
        printPerson(personList, startsWithLetterH);

        /*
            Standard Interfaces
                - Creating a separate interface Condition seems a little overhead.
                - Java 8 provides out of the box, interfaces in Package java.util.function
                Eg:
                    - Predicate<T>	Represents a predicate (boolean-valued function) of one argument.
                    - Consumer<T>	Represents an operation that accepts a single input argument and returns no result.
         */

        /*
            We can also pass behaviour as a parameter
         */

        System.out.println("--");
        Predicate<Person> startsWithLetterHpredicate = (Person p) -> p.getFirstName().startsWith("h");
        printPersonConditionally(personList, startsWithLetterHpredicate);

        Consumer<Person> printPersonConsumer = (Person p) -> { System.out.println(p); };
        Consumer<Person> printAgeConsumer = (Person p) -> { System.out.println(p.getAge()); };

        performPersonConditionally(personList, startsWithLetterHpredicate, printPersonConsumer);
        performPersonConditionally(personList, startsWithLetterHpredicate, printAgeConsumer);

        /*
            Closures:
                A lambda expression becomes a closure when it is able to access the variables that
                are outside of this scope. It means a lambda can access its outer scope.
         */

        int x = 20;
        Operation addOperation = (a) -> {
            // we are able to access x inside lambda
            System.out.println("add x and a : " + (x + a));
        };

        /*
            This reference in lambda:
            the meaning of names and the this and super keywords appearing in a lambda body, along with the
            accessibility of referenced declarations, are the same as in the surrounding context.

            So if the context of a lambda expression is a static method then we cannot use this keyword inside lambda
         */

        Consumer<Person> testConsumer = (Person p) -> {
            // System.out.println(this);   => 'this' will give an error because we are inside execute method which is static
            System.out.println(p);
        };

        /*
            Method reference:
                Method reference is used to refer method of functional interface. It is compact and easy form of lambda
                expression. Each time when you are using lambda expression to just referring a method, you can replace your
                lambda expression with method reference.

                Consumer<Person> test = (Person p) -> {
                    System.out.println(p);
                };

                This is same as :
         */
        Consumer<Person> test = System.out::println;


        addOperation.operate(10);


    }

    private static void sortByLastName(List<Person> personList) {
        personList.sort((a, b) -> a.getLastName().compareTo(b.getLastName())) ;
    }

    private static void printPerson(List<Person> personList) {
        personList.forEach((person) -> System.out.println(person));
    }

    private static void printPersonWithLetterH(List<Person> personList) {
        personList.forEach((person -> {
            if (person.getFirstName().startsWith("h")) {
                System.out.println(person);
            }
        }));
    }

    private static void printPerson(List<Person> personList, Condition condition) {
        personList.forEach((person -> {
            if (condition.satisfy(person)) {
                System.out.println(person);
            }
        }));
    }

    private static void printPersonConditionally(List<Person> personList, Predicate<Person> predicate) {
        personList.forEach(person -> {
            if (predicate.test(person)) {
                System.out.println(person);
            }
        });
    }

    private static void performPersonConditionally(List<Person> personList, Predicate<Person> predicate, Consumer<Person> consumer) {
        personList.forEach(person -> {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        });
    }

    private static void process(List<Integer> list, Integer key, BiConsumer<Integer, Integer> consumer) {
        list.forEach(element -> consumer.accept(element, key));
    }



}

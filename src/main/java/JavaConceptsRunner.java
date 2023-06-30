import concurrency.JavaConcurrencyDemo;
import java8features.streams.StreamDemo;
import java8features.lambdas.LambdaDemo;
import logging.LoggingDemo;

public class JavaConceptsRunner {
    public static void main(String[] args) {
        System.out.println("---- Java 8 Concepts ----");

        LambdaDemo.execute();

        StreamDemo.execute();

        new JavaConcurrencyDemo().execute();

        new LoggingDemo().execute();

    }
}

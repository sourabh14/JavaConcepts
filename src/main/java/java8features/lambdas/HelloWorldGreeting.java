package java8features.lambdas;

public class HelloWorldGreeting implements Greeting {

    @Override
    public void perform() {
        System.out.println("Hello World !!");
    }

}

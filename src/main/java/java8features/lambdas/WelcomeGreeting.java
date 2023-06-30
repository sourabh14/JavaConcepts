package java8features.lambdas;

public class WelcomeGreeting implements Greeting {
    @Override
    public void perform() {
        System.out.println("Welcome !!");
    }
}

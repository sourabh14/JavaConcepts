package junit5basics;

public class Junit5Demo {
    public void execute() {
        /*
            Why we do testing?
                - to verify on an ongoing basis that the code continues to work in the future.

            Need for a testing framework:
                - The test run has several steps (if we don't use testing framework):
                    - preparation - create test cases and create object of the target class
                    - test inputs - provide test inputs
                    - provide expected outputs
                    - verify the result (framework)
                    - run the tests (framework)
                    - do something to alert the dev that the test has failed (framework)

                - a testing framework will provide cases for the last 3 steps that we'd have to do
                    for every test.

            Junit 5 Architecture
                - JUnit 5 is composed of several different modules from three different sub-projects.
                    JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
                - Junit platform - this is the test engine. It serves as a foundation for launching testing frameworks
                    on the JVM. It also defines the TestEngine API.  We don't directly interact with it.
                - JUnit Jupiter - is the combination of the programming model and extension model for writing tests and extensions in JUnit 5.
                    We interact with this - junit api (jupiter)
                - JUnit Vintage - provides a TestEngine for running JUnit 3 and JUnit 4 based tests on the platform.
         */
    }
}

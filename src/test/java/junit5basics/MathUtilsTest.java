package junit5basics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    MathUtils mathUtils;

    /*
        @Test marks a method as test. It informs junit engine what methods need to run.

        Junit hack:
            - It can be used as a runner.
            - Let's say we have a codebase that we need to run every time.
            - One way is to write a main method and then call those methods.
            - We can put @Test method on top of those methods and then we can run them.

        Steps to write tests:
            - Create an instance of class under test
            - Set up inputs
            - Execute the code
            - Verify the result

        Test driven development:
            - Write the tests first
            - You write your code till the test turns green

        Maven surefire plugin:
            - The Surefire Plugin is used during the test phase of the build lifecycle to execute the unit tests of an
                application. It generates reports in two different file formats:
            - Plain text files (*.txt)
            - XML files (*.xml)
            - By default, these files are generated in ${basedir}/target/surefire-reports/TEST-*.xml.
            - Surefire Plugin can be invoked by calling the test phase of the build lifecycle.
                    mvn test

        Annotations to scale your tests:
            - @DisplayName
            - @Disabled - to skip tests
            -

     */

    @BeforeAll
    static void globalInit() {
        System.out.println("Starting tests");
    }

    @BeforeEach
    void init() {
        mathUtils = new MathUtils();
    }

    @Test
    void add() {
        int expected = 5;
        int actual = mathUtils.add(2, 3);
        assertEquals(expected, actual, "The add method should add two numbers");
        // To fail the test use fail()
        // fail("Failing this test.. ")
    }


    @Test
    void computeCircleArea() {
        int radius = 4;
        double expected = 50.24;
        double actual = mathUtils.computeCircleArea(radius);
        assertEquals(expected, actual);
    }

    /*
        Asserting exceptions
     */
    @Test
    @DisplayName("Testing divide method")
    void divide() {
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(5, 0));
    }

    /*
        Tag - we can execute all tests under a particular tag name separately.

        - Add config in surefire plugin pom - to exclude/include
     */
    @Test
    @Tag("mytag")
    void multiply() {
        assertEquals(0, mathUtils.multiply(5, 0));
    }

    @Test
    @Disabled
    void disabledTest() {
        fail("Disabled test");
    }




}
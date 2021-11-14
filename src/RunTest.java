import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import static org.hamcrest.CoreMatchers.containsString;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Purdue CS
 * @version November 12, 2021
 */
public class RunTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }


    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Fall 2021</p>
     *
     * @author Purdue CS
     * @version November 12, 2021
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @Test(timeout = 1000)
        public void testQuiz() {
            TestUtil.main(new String[0]);

            String stuOut = getOutput();
            String expectedFull = "Quiz name: Quiz 1\n"
                    + "Attempt: 1\n"
                    + "1. What is your name?; Grade: 100\n"
                    + "Selected: John\n"
                    + "2. What is your age?; Grade: -1\n"
                    + "Selected: Not Answered\n\n" +
                    "Course1\n" +
                    "null\n";
            stuOut = stuOut.replace("\r\n", "\n");
            assertEquals("Ensure your output is correct",
                    stuOut, expectedFull);

        }

        /**
         * UTILITY METHODS BELOW
         */



        private String getOutput() {
            return testOut.toString();
        }

        @After
        public void restoreInputAndOutput() {
            System.setOut(originalOutput);
        }
    }
}


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author Peter Olsen
 * @version 11/14/2021
 */
import static org.junit.Assert.assertEquals;
public class QuizToolTest {
    private final PrintStream originalOutput = System.out;
    private ByteArrayOutputStream testOut;
    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    @Test
    public void testQuizTool() {
        String input = "2\nTeacherUser21\npa55w0rd\nY\n2\nMyCourse\n1\nMyCourse\n1\nmyQuiz\n1\nQuestion Example\n" +
                "2\nTrue\nFalse\n4\n5";
        String expectedOut = "Welcome to the Learning Management System Quiz Tool!\n1. Login\n2. Create Account\n" +
                "3. Exit\nEnter your username:\nEnter your password:\nAre you a teacher? [Y/N]:\n1. Select Course\n" +
                "2. Add Course\n3. Remove Course\n4. Grade Student Submission\n5. Exit\nEnter the course name:\n" +
                "1. Select Course\n2. Add Course\n3. Remove Course\n4. Grade Student Submission\n5. Exit\nMyCourse\n" +
                "Enter the course name:\n1. Create New Quiz\n2. Remove Quiz\n3. Modify Quiz\n4. Close Quiz Menu\n" +
                "Enter the quiz name:\nHow many questions will be in the quiz?:\nWill the order be randomized? [Y/N]:\n" +
                "Enter question 1:\nHow many answer choices will this question have?:\nWill the order be randomized? " +
                "[Y/N]:\nEnter answer choice 1:\nEnter answer choice 2:\nQuiz created!\n1. Create New Quiz\n" +
                "2. Remove Quiz\n3. Modify Quiz\n4. Close Quiz Menu\n1. Select Course\n" +
                "2. Add Course\n3. Remove Course\n4. Grade Student Submission\n5. Exit\nClosing Quiz Tool\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        QuizTool.main(new String[0]);
        String actualOutput = getOutput();
        assertEquals(expectedOut, actualOutput);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreInputAndOutput() {
        System.setOut(originalOutput);
    }
}

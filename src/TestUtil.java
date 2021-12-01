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
//Class to test Quiz, Question, and Course.

/**
 * @author Peter Olsen
 * @version 11/14/21
 */
public class TestUtil {
    public static void main(String[] args) {
        Question q1 = new Question("What is your name?");
        String[] choices1 = {"Bill", "John"};
        q1.setChoices(choices1);

        Question q2 = new Question("What is your age?");
        String[] choices2 = {"3", "4"};
        q2.setChoices(choices2);

        Question[] quizList = {q1, q2};

        Course c = new Course("Course1");

        Quiz quiz = new Quiz("Quiz 1", quizList, 1, c);
        quiz.answerQuestion(1, 1);
        //System.out.println(quiz.getQuestion(1));
        quiz.gradeQuestion(1, 100);

        System.out.println(quiz.toStringPostTake());
        System.out.println(quiz.getCourse().getName());
        System.out.println(c.getQuiz("Fake Quiz"));


    }

}

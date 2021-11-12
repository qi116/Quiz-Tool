

import java.util.Arrays;
import java.util.*;
import java.util.Collections;

/**
 * A class that manages Questions.
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Purdue CS
 * @version November 12, 2021
 */
public class Question {
    private String question;
    private String[] choices;
    private int grade;
    private int studentAnswer;
    private String[] originalChoices;

    // saves index of original choices order

    public Question(String question, String[] choices) {
        this.question = question;
        this.choices = choices;
        originalChoices = new String[choices.length];
        for (int i = 0; i < choices.length; i++) {
            originalChoices[i] = choices[i];
        }
        this.grade = -1;
        this.studentAnswer = -1;
    }
    public Question(String question) {
        this.question = question;
        this.grade = -1;
        this.studentAnswer = -1;
    }

    //sets student answer.
    public void setStudentAnswer(int studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    //returns student's answer. is -1 if not answered
    public int getStudentAnswer() {
        return studentAnswer;
    }

    //set choices for the question
    //makes a copy into original choices to maintain student's answer
    public void setChoices(String [] choices) {
        this.choices = choices;
        originalChoices = new String[choices.length];
        for (int i = 0; i < choices.length; i++) {
            originalChoices[i] = choices[i];
        }
    }
    //Gets array of choices in their original order
    public String[] getOriginalChoices() {
        return originalChoices;
    }

    //returns the question string
    public String getQuestion() {
        return question;
    }
    //set the grade. Does not allow negative grades
    public void setGrade(int grade) {
        if (grade < 0) {
            //error message
            return;
        }
        this.grade = grade;
    }
    //returns grade. is -1 if unanswered
    public int getGrade() {
        return grade;
    }

    @Override
    //returns Question as a string with choices
    public String toString() { //make 1 and 2 into a and b (-65)
        String s = "";
        s += question + "\n[\n";
        for (int i = 0; i < choices.length; i++) {
            s += String.format("   %c. %s\n", (char) (i+65), choices[i]);
        }
        return s += "]\n    ";
    }

    //returns array of choices
    public String[] getChoices() {
        return choices;
    }

    //Uses the random Object to randomly move the order of choices around.
    public void randomizeChoices() {
        Random r = new Random();
        for (int i = choices.length - 1; i > 0; i--)
        {
            int num = r.nextInt(i + 1);
            String a = choices[num];
            choices[num] = choices[i];
            choices[i] = a;
        }
    }
}
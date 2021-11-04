package src;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class Question {
    private String question;
    private String answer; //not necessary
    private String[] choices;
    private int grade;

    // saves index of original choices order

    public Question(String question, String[] choices) {
        this.question = question;
        this.choices = choices;
        this.grade = -1;
    }
    public Question(String question) {
        this.question = question;
        this.grade = -1;
    }
    //set choices for the question
    public void setChoices(String [] choices) {
        this.choices = choices;
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

    public String[] getChoices() {
        return choices;
    }
}
package src;
import java.io.Serializable;
import java.util.*;
public class Quiz implements Serializable {
    private ArrayList<Question> quiz;
    private String name;
    private ArrayList<Integer> studentAnswers;
    private int attempt;
    private static final long serialVersionUID = 1L;


    public Quiz(String name, Question[] quiz) {
        this.name = name;
        this.quiz = new ArrayList<Question>();
        this.studentAnswers = new ArrayList<Integer>();

        for (int i = 0; i < quiz.length; i++) {
            this.quiz.add(quiz[i]);
        }
        for (int i = 0; i < this.quiz.size(); i++) {
            this.studentAnswers.add(-1); //Adds default answer of -1.
        }
    }
    public Quiz(String name, Question[] quiz, int attempt) {
        this(name, quiz);
        this.attempt = attempt;
    }

    public String getName() {
        return name;
    }

    //Prints Quiz name and every question
    public String toStringPreTake() {
        String s = String.format("Quiz name: %s\n", name);
        for (int i = 0; i < quiz.size(); i++) {
            s += String.format("%d. %s", i + 1, quiz.get(i).toString());
        }
        return s;
    }

    //Prints Quiz name and every question and selected choices
    public String toStringPostTake() {
        String s = String.format("Quiz name: %s\n", name);
        s += String.format("Attempt: %d\n", attempt);
        for (int i = 0; i < quiz.size(); i++) {
            s += String.format("%d. %s; Grade: %d\n", i + 1, quiz.get(i).getQuestion(), quiz.get(i).getGrade());
            s += String.format("Selected: %s\n", getSelected(i + 1));

        }
        return s;
    }
    //returns string of selected answer for given question number
    public String getSelected(int num) {
        if (studentAnswers.get(num - 1) == - 1) {
            return "Not Answered";
        }
        return quiz.get(num - 1).getChoices()[studentAnswers.get(num - 1)];
    }
    //increments attempts.
    public void addAttempt() {
        attempt++;
    }
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }
    //Takes question number and answer as integers and puts answer into answers ArrayList
    //Answer must be in format of integer corresponding to answer
    //Returns false if improper input
    public boolean answerQuestion(int num, int answer) {
        if (answer <= 0 || answer > quiz.get(num - 1).getChoices().length) {
            //Error
            return false;
        }
        studentAnswers.set(num - 1, answer - 1);
        return true;
    }
    public Question getQuestion(int num) {
        if (num > quiz.size() || num <= 0) {
            return null;
        }
        return quiz.get(num - 1);
    }
    public void gradeQuestion(int num, int grade) {
        getQuestion(num).setGrade(grade);
    }

}
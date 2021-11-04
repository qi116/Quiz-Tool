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

    }
    public Quiz(String name, Question[] quiz, int attempt) {
        this(name, quiz);
        this.attempt = attempt;
    }
    //returns name
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
        if (getQuestion(num).getStudentAnswer() == - 1) {
            return "Not Answered";
        }
        Question q =  getQuestion(num);
        return q.getOriginalChoices()[q.getStudentAnswer()];
    }
    //increments attempts.
    public void addAttempt() {
        attempt++;
    }
    //set number of attempts
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }
    //Takes question number and answer as integers and puts answer into answers ArrayList
    //Answer must be in format of integer corresponding to answer
    //Returns false if improper input
    public boolean answerQuestion(int num, int answer) {
        if (answer <= 0 || answer > getQuestion(num).getChoices().length) {
            //Error
            return false;
        }
        getQuestion(num).setStudentAnswer(answer);
        return true;
    }
    //returns question given number. If the number is outside of the size of the quiz or is negative, return null
    public Question getQuestion(int num) {
        if (num > quiz.size() || num <= 0) {
            return null;
        }
        return quiz.get(num - 1);
    }
    //takes question number and grade and sets the grade 
    public void gradeQuestion(int num, int grade) {
        getQuestion(num).setGrade(grade);
    }
    //Shuffles the order of the questions. Original question order is not maintained.
    public void randomize() {
        Collections.shuffle(quiz);
    }

}
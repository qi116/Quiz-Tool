import java.io.Serializable;
import java.util.*;

/**
 * A class that manages Quizzes.
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Brian Qi
 * @version November 12, 2021
 */
public class Quiz implements Serializable {
    private ArrayList<Question> quiz;
    private String name;
    private int attempt;
    private static final long serialVersionUID = 1L;
    private Course course;
    private boolean isRandomized = false;
    private String timeStamp = "NA";


    public Quiz(String name, Question[] quiz) {
        this.name = name;
        this.quiz = new ArrayList<Question>();

        for (int i = 0; i < quiz.length; i++) {
            this.quiz.add(quiz[i]);
        }

    }
    public Quiz(String name, Question[] quiz, int attempt) {
        this(name, quiz);
        this.attempt = attempt;
    }

    public Quiz(String name, Question[] quiz, int attempt, Course course) {
        this(name, quiz, attempt);
        this.course = course;
        course.addQuiz(this);
    }
    public Quiz(String name, Question[] quiz, Course course) {
        this(name, quiz);
        this.course = course;
        course.addQuiz(this);
    }
    //set time stamp
    public void setTimeStamp(String t) {
        this.timeStamp = t;
    }
    //get time stamp
    public String getTimeStamp() {
        return timeStamp;
    }

    //Returns quiz size
    public int getLength() {
        return quiz.size();
    }
    //Removes question at index and replaces it with given Question.
    public boolean setQuestion(int num, Question q) {

        int index = num - 1;
        if (index > quiz.size() - 1 || index < 0) {
            return false;
        }
        quiz.remove(index);
        quiz.add(index, q);
        return true;
    }

    //returns name
    public String getName() {
        return name;
    }
    //returns course
    public Course getCourse() {
        return course;
    }

    //Sets course for Quiz. Returns false if course doesn't exist.
    //Does not add quiz to course (must do manually)
    //Be sure to remove quiz from course if changing courses
    //Also note. Just this method working does not mean that Quiz doesn't already exist in course
    //This condition is checked in addQuiz() in Course
    public boolean setCourse(String courseName) {
        Course a = Course.getCourse(courseName);
        if (course == null) {
            return false;
        }
        this.course = a;
        return true;
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
        s += String.format("Time taken: %s\n", timeStamp);
        for (int i = 0; i < quiz.size(); i++) {
            s += String.format("%d. %s; Grade: %d\n", i + 1, quiz.get(i).getQuestion(), quiz.get(i).getGrade());
            s += String.format("Selected: %s\n", getSelected(i + 1));

        }
        return s;
    }
    //returns string of selected answer for given question number
    public String getSelected(int num) {
        if (getQuestion(num).getStudentAnswer() == -1) {
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
    //returns number of attempts
    public int getAttempt() {
        return attempt;
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
        isRandomized = true;
    }
    //returns isRandomized
    public boolean isRandomized() {
        return isRandomized;
    }
    //set isRandomized
    public void setIsRandomized(boolean isRandomized) {
        this.isRandomized = isRandomized;
    }


}


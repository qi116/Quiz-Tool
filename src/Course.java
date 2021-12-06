
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that manages Courses.
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Brian Qi
 * @version November 12, 2021
 */

public class Course implements Serializable {


    private String name;
    private static final long serialVersionUID = 1L;
    private ArrayList<Quiz> quizzes; //quizzes in the course
    private static ArrayList<Course> courses;
    static {
        courses = new ArrayList<Course>();
    }
    public Course(String name) {
        this.name = name;
        quizzes = new ArrayList<Quiz>();
        courses.add(this);
    }
    public Course(String name, ArrayList<Quiz> quizzes) {
        this.name = name;
        this.quizzes = quizzes;
    }
    //return course name
    public String getName() {
        return name;
    }
    //set course name
    public void setName(String name) {
        this.name = name;
    }

    //gets course by name from course list
    public static Course getCourse(String name) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getName().equals(name)) {
                return courses.get(i);
            }
        }
        return null; //No course with such name
    }

    //Gets quiz by name from course. If quiz doesn't exist, return null;
    public Quiz getQuiz(String quizName) {
        for (Quiz q : quizzes) {
            if (q.getName().equals(quizName)) {
                return q;
            }
        }
        return null;
    }
    //Adds quiz to course. Returns false if fails. Fails when quiz is null or already exists
    public boolean addQuiz(Quiz quiz) {
        if (quiz == null)
            return false;
        removeQuiz(quiz.getName());
        quizzes.add(quiz);
        return true;
    }
    //removes quiz from list returns if the job was compleated
    public boolean removeQuiz(String quizName) {
        if (contains(quizName) >= 0) {
            quizzes.remove(contains(quizName));
            return true;
        }
        return false;
    }

    public int contains(String quizName) {
        for (int i = 0; i < quizzes.size(); i++) {
            if (quizzes.get(i).getName().equals(quizName))
                return i;
        }
        return -1;
    }

    //returns entire arrayList of quizzes
    public ArrayList<Quiz> getQuizzes() {
        return this.quizzes;
    }

    public String[] getQuizNames() {
        String[] quizNames = new String[quizzes.size()];

        for (int i = 0; i < quizNames.length; i++)
            quizNames[i] = quizzes.get(i).getName();

        return quizNames;
    }
}

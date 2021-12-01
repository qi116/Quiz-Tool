
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
        if (quiz == null) {
            return false;
        }
        if (getQuiz(quiz.getName()) != null) {
            quizzes.add(quiz);
            return true;
        } else 
            return false;
    }
    //removes quiz from list returns if the job was compleated
    public boolean removeQuiz(Quiz quiz) {
        for (Quiz q : quizzes) {
            if (q.equals(quiz)) {
                quizzes.remove(q);
                return true;
            }
        }
        return false;
    }

    //returns entire arrayList of quizzes
    public ArrayList<Quiz> getQuizzes() {
        return this.quizzes;
    }
}

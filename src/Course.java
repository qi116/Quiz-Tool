
import java.io.Serializable;
import java.util.ArrayList;

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
    public Quiz getQuiz(String name) {
        for (Quiz q : quizzes) {
            if (q.getName().equals(name)) {
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
        for (Quiz q : quizzes) { //checks if quiz already exists by name
            if (q.getName().equals(quiz.getName())) {
                return false;
            }
        }
        quizzes.add(quiz);
        return true;
    }
    //removes quiz from list
    public void removeQuiz(Quiz quiz) {
        for (Quiz q : quizzes) {
            if (q.equals(quiz)) {
                quizzes.remove(q);
            }
        }

    }
}

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Nathan Leakeas
 * @version 11.13.2021
 */
public class Teacher extends Account implements Serializable {
    public ArrayList<Course> courses;

    public Teacher(String username, String password) {
        super(username, password);
        this.courses = new ArrayList<Course>();

    }

    /**
     * @return ArrayList of all courses belonging to Teacher
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Adds existing course to list of courses
     * @param course Existing Course
     */
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    /**
     * Removes course from list of courses
     * @param course Course To Remove
     */
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    /**
     * Remove all courses with name of courseName
     * @param courseName Name of course(s) to remove
     */
    public void removeCourse(String courseName) {
        courses.removeIf(c -> c.getName().equals(courseName));
    }

    /**
     * Create new Course with name
     * @param name
     * @return Created course
     */
    public Course createCourse(String name) {
        Course c = new Course(name);
        courses.add(c);
        return c;
    }


    /**
     * Adds quiz to course in teacher's courses with name
     * @param q Quiz to be added
     * @param courseName Name of course
     */
    public void addQuizToCourse(Quiz q, String courseName) {
        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                c.addQuiz(q);
                return;
            }
        }
    }
}

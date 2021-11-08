import java.util.ArrayList;

public class Teacher extends Account{
    public ArrayList<Course> courses;

    public Teacher(String username, String password) {
        super(username, password);
        this.courses = new ArrayList<Course>();

    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    public Course createCourse(String name) {
        Course c = new Course(name);
        courses.add(c);
        return c;
    }
}

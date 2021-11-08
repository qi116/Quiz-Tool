package src;

import java.util.ArrayList;

public class Course {
    private String name;
    private static ArrayList<Course> courses;
    static {
        courses = new ArrayList<Course>();
    }
    public Course(String name) {
        this.name = name;
        courses.add(this);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static Course getCourse(String name) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getName().equals(name)) {
                return courses.get(i);
            }
        }
        return null; //No course with such name
    }
}

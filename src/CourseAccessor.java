/**
 * accessor for courses
 *
 * @author Hawkins Peterson
 * @version 11.30.21
 */
public class CourseAccessor extends ServerDataAccessor {
    public static final String folder = "data/courses/";
    public static final String fileType = ".obj";

    /**
     * creates a CourseAccessor
     */
    public CourseAccessor() {
        super(folder, fileType);
    }
    
    /**
     * Returns a course object given that it exists
     *
     * @param courseName the name of the course
     * @return a course object given that it exists
     */
    public Course get(String courseName) throws NullPointerException {
        return (Course) super.get(courseName.replace(" ", "-"));
    }

    /**
     * checks if the course exists
     *
     * @param course the name of the course
     * @return a boolean if the course exists
     */
    public boolean checkCourseExists(String courseName) {
        return super.checkExists(courseName.replace(" ", "-"));
    }

    /**
     * Returns a list of course names
     * @return a list of course names
     */
    public String[] getCourseList() {
        Object[] ol = super.getListVerbose();
        String[] courseNameList = new String[ol.length];
        for (int i = 0; i < ol.length; i++)
            courseNameList[i] = ((Course) ol[i]).getName();
        return courseNameList;
    } 

    /**
     * adds a course to the data (so long as it doesn't exist)
     * 
     * @param course the course to be added
     */
    public void add(Course course) throws FileAlreadyExistsException {
        super.addData(course, course.getName().replace(" ", "-"));
    }

    /**
     * saves a course to the data (so long as it doesn't exist)
     *
     * @param course the course to be saved into data
     */
    public void modify(Course course) throws NullPointerException {
        super.modifyData(course, course.getName().replace(" ", "-"));
    }

    /**
     * removes a course from data (so long as it exists)
     *
     * @param courseName the name of the course to be deleted
     */
    public void remove(String courseName) throws NullPointerException {
        super.removeData(courseName.replace(" ", "-"));
    }
}

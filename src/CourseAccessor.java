public class CourseAccessor extends ServerDataAccessor {
    public static final String folder = "data/courses/";
    public static final String fileType = ".obj";

    public CourseAccessor() {
        super(folder, fileType);
    }
    
    public Course get(String courseName) throws NullPointerException {
        return (Course) super.get(courseName.replace(" ", "-"));
    }

    public boolean checkCourseExists(String courseName) {
        return super.checkExists(courseName.replace(" ", "-"));
    }

    public String[] getCourseList() {
        Object[] ol = super.getListVerbose();
        String[] courseNameList = new String[ol.length];
        for (int i = 0; i < ol.length; i++)
            courseNameList[i] = ((Course) ol[i]).getName();
        return courseNameList;
    } 

    public void add(Course course) throws FileAlreadyExistsException {
        super.addData(course, course.getName().replace(" ", "-"));
    }

    public void modify(Course course) throws NullPointerException {
        super.modifyData(course, course.getName().replace(" ", "-"));
    }

    public void remove(String courseName) throws NullPointerException {
        super.removeData(courseName).replace(" ", "-");
    }
}

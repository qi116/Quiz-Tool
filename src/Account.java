public abstract class Account {
    protected String username;
    protected String password;
    protected ArrayList<Course> courses;

    public Account(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Courses> getCourses() {
        return courses;
    }


    /*public static Account loadAccount(String username, boolean isTeacher) {
        if (isTeacher) {
            return new Teacher(username);
        } else {
            return new Student(username);
        }
    }*/
}

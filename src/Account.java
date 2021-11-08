public abstract class Account {
    protected String username;
    protected String password;

    public Account(String username, String password) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTeacher() {
        return (this instanceof Teacher);
    }

    public boolean isStudent() {
        return (this instanceof Student);
    }


    /*public static Account loadAccount(String username, boolean isTeacher) {
        if (isTeacher) {
            return new Teacher(username);
        } else {
            return new Student(username);
        }
    }*/
}

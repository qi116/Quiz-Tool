import java.io.Serializable;

/**
 * @author Nathan Leakeas
 * @version 11.13.2021
 */
public abstract class Account implements Serializable {
    protected String username;
    protected String password;
    protected boolean isTeacher;

    public Account(String username, String password, boolean isTeacher) {
        this.username = username;
        this.password = password;
        this.isTeacher = isTeacher;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public boolean isStudent() {
        return !isTeacher;
    }

}

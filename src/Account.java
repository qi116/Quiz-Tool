import java.io.Serializable;

public abstract class Account implements Serializable {
    protected String username;
    protected String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
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

}

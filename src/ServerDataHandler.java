public class ServerDataHandler extends ServerDataAccessor {
    private boolean isTeacher;

    public ServerDataHandler() {
        isTeacher = false;
    }

    public Response processRequest(Request request) {
        return new Response(false, false);
    }

    private Account getAccount(String username, String password) throws NullPointerException {
        try {
            super.setFolderPrefix("data/accounts/");
            Account account = (Account) super.get(username);
            if (password.equals(account.getPassword()))
                return account;
            else
                throw new NullPointerException("an account with that username / password combination was not found");
        } catch (NullPointerException npe) {
            throw new NullPointerException("an account with that username / password combination was not found");
        }
    }

    private boolean accountExists(String accountName) {
        try {
            super.setFolderPrefix("data/accounts/")
            super.checkFileExists(accountName);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private Account getStudentAccount(String username) throws NullPointerException {
        super.setFolderPrefix("data/accounts/");
        Account account = (Account) super.get(username);
        if (account instanceof Student)
            return (Student) account;
        else
            throw new NullPointerException("a student account with that username doesn't exist");
    }

    private String[] listStudents() throws NullPointerException {
        super.setFolderPrefix("data/accounts/");
        Object[] ol = super.getListVerbose();
        ArrayList<Student> asl = new ArrayList<Student>();
        for (int i = 0; i < ol.length; i++)
            if (ol[i] instanceof Student)
                aal.add((Student) ol[i]);
        String[] usernameList = new String[aal.size()];
        for (int i = 0; i < aal.size(); i++)
            usernameList[i] = aal.get(i).getUsername();

        return usernameList;
    }

    private Course getCourse(String courseName) throws NullPointerException {
        super.setFolderPrefix("data/courses/");
        return (Course) super.get(courseName.replace(" ", "-"));
    }

    private boolean courseExists(String courseName) throws NullPointerException {
        super.setFolderPrefix("data/courses/");
        try {
            super.checkFileExists(courseName);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private String[] listCourses() {
        super.setFolderPrefix("data/courses/");
        Object[] ol = super.getListVerbose();
        String[] courseNameList = new String[ol.length];
        for (int i = 0; i < ol.length; i++)
            courseNameList[i] = ((Course) ol[i]).getName();
        return courseNameList;
    }

    private boolean removeCourse(String courseName) {
        super.setFolderPrefix("data/courses/");
        try {
            super.removeData(courseName);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

}

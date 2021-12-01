import java.util.*;

public class ServerDataHandler extends ServerDataAccessor {
    private boolean isTeacher;

    public ServerDataHandler() {
        super(".obj");
        isTeacher = false;
    }

    public Response processRequest(Request request) {
        return new Response(false, false);
    }

    private boolean logIn(String username, String password) {
        try {
            Account account = getAccount(username, password);
            isTeacher = account instanceof Teacher;
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private boolean signUp(Account account) {
        try {
            super.setFolderPrefix("data/accounts/");
            super.addData(account, account.getUsername());
            isTeacher = account instanceof Teacher;
            return true;
        } catch (FileAlreadyExistsException e) {
            return false;
        }
    }

    private Quiz getQuizAttempt(String username, String quizName) throws NullPointerException {
        Student student = getStudentAccount(username);
        int attemptNumber = Integer.parseInt(quizName.substring(
                    quizName.indexOf("#") + 1, quizName.length()
                    ));
        quizName = quizName.substring(0, quizName.indexOf("#"));
        
        Quiz quiz = student.getQuiz(quizName, attemptNumber);
        if (quiz != null)
            return quiz;
        else
            throw new NullPointerException("an account with that username / password combination was not found");
    }

    private boolean saveQuizAttempt(String username, Quiz quiz) {
        try {
            Student student = getStudentAccount(username);
            if (isTeacher) {
                student.overwriteQuizSubmission(quiz);
            } else
                student.addNewQuizSubmission(quiz);
            return true;
        } catch (Exception e) {
            return false;
        } 
    }
    
    private Quiz getQuiz(String courseName, String quizName) throws NullPointerException {
        Course course = getCourse(courseName);
        Quiz quiz = course.getQuiz(quizName);
        if (quiz != null)
            return quiz;
        else
            throw new NullPointerException("a course and or quiz with that name was not found");
    }
    
    private boolean saveQuiz(String courseName, Quiz quiz) {
        try {
            Course course = getCourse(courseName);
            course.addQuiz(quiz);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean removeQuiz(String courseName, Quiz quiz) {
        try {
            Course course = getCourse(courseName);
            return course.removeQuiz(quiz);
        } catch (NullPointerException e) {
            return false;
        }
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
            super.setFolderPrefix("data/accounts/");
            super.checkFileExists(accountName);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private Student getStudentAccount(String username) throws NullPointerException {
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
        ArrayList<Student> sal = new ArrayList<Student>();
        for (int i = 0; i < ol.length; i++)
            if (ol[i] instanceof Student)
                sal.add((Student) ol[i]);
        String[] usernameList = new String[sal.size()];
        for (int i = 0; i < sal.size(); i++)
            usernameList[i] = sal.get(i).getUsername();

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

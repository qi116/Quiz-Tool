import java.util.*;

/**
 * Responsible for all of the server data handling
 *
 * @author Hawkins Peterson
 * @version 12.02.21
 */
public class ServerDataHandler extends ServerDataAccessor {
    private boolean isTeacher;
    private Message lastMessage;
    private static Message update;
    private static String pathToUpdate;
    
    /**
     * creates an empty ServerDataHandler
     */
    public ServerDataHandler() {
        super(".obj");
        isTeacher = false;
    }

    /**
     * proccesses a request, put in the request, out pops a response
     * @param request a request from the client
     * @return a response to be sent to the client
     */
    public Message processRequest(Message request) {
        Object content = request.content;
        String contentPath = request.contentPath;
        lastMessage = request;

        try {
            switch (request.data) {
                case :
                    return new Response(getQuiz(contentPath, (String) content));
                case SAVE_QUIZ:
                    createUpdate(new Response(listQuizzes(contentPath)), contentPath);
                    return new Response(getQuiz(contentPath, (String) content));
                case REMOVE_QUIZ:
                    return new Response(removeQuiz(contentPath, (String) content));
                case GET_QUIZ_ATTEMPT:
                    return new Response(getQuizAttempt(contentPath, (String) content));
                case SAVE_QUIZ_ATTEMPT:
                    createUpdate(new Response(listQuizAttempts(contentPath)), contentPath);
                    return new Response(saveQuizAttempt(contentPath, (Quiz) content));
                case DELETE_COURSE:
                    return new Response(removeCourse(contentPath));
                case LOG_IN:
                    return new Response(logIn(contentPath, (String) content));
                case TEACHER_SIGN_UP:
                    signUp((Account) content);
                    this.isTeacher = true;
                    return new Response(true);
                case STUDENT_SIGN_UP:
                    return new Response(signUp((Account) content));
                case LIST_STUDENTS:
                    return new Response(listStudents());
                case LIST_COURSES:
                    return new Response(listCourses());
                case LIST_QUIZ_ATTEMPTS:
                    return new Response(listQuizAttempts(contentPath));
                case LIST_QUIZZES:
                    return new Response(listQuizzes(contentPath));
                case CHECK_ACCOUNT_EXISTS:
                    return new Response(accountExists(contentPath));
            }
        } catch (NullPointerException e) {
            return new Response(true, false);
        } catch (UnauthorizedException e) {
            return new Response(false, true);
        } catch (Exception e) {
            return new Response(false, true);
        }
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
    
    /**
     * Returns if you have to send an update to the client
     * @return if you have to send an update to the client
     */
    public static boolean requiresUpdate() {
        return update != null; 
    }
    
    /**
     * gets the update to send to the client
     * @return the update to send to the client
     */
    public static Response getUpdateResponse() {
        Response tempUpdate = update;
        update = null;
        return tempUpdate;
    } 

    private static void createUpdate(Response update, String pathToUpdate) {
        ServerDataHandler.update = update;
        ServerDataHandler.pathToUpdate = pathToUpdate;
    }
    
    /**
     * if the client requires an update
     * @return if the client requires an update
     */
    public boolean requiresUpdate(String pathToUpdate) {
        return (lastRequest.getRequestType() == RequestType.LIST_COURSES ||
                lastRequest.getRequestType() == RequestType.LIST_STUDENTS ||
                lastRequest.getRequestType() == RequestType.LIST_QUIZZES) &&
                pathToUpdate.equals(lastRequest.getContentPath());
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

    private String[] listQuizAttempts(String userName) throws NullPointerException{
        Student student = getStudentAccount(userName);
        return student.getQuizIdentifiers();
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
        if (!isTeacher)
            return false;
        try {
            Course course = getCourse(courseName);
            course.addQuiz(quiz);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean removeQuiz(String courseName, String identifier) {
        if (!isTeacher)
            return false;
        try {
            Course course = getCourse(courseName);
            return course.removeQuiz(identifier);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private String[] listQuizzes(String courseName) {
        Course course = getCourse(courseName);
        return course.getQuizNames();
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

    private String[] listStudents() throws NullPointerException, UnauthorizedException {
        if (!isTeacher) {
            throw new UnauthorizedException("Error, only teachers are allowed to access this command");
        }
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
        if (!isTeacher) {
            return false;
        }
        super.setFolderPrefix("data/courses/");
        try {
            super.removeData(courseName);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}

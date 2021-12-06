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
    private Account account;
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
                case ACCOUNT:
                    switch (request.request) {
                        case LOGIN:
                            return new Message(logIn(contentPath, (String) content));
                        case ADD:
                        case MODIFY:
                            isTeacher = content instanceof Teacher;
                            if (!isTeacher)
                                createUpdate(new Message(listStudents()), contentPath);
                            return new Message(signUp((Account) content));
                        case LIST:
                            if (!isTeacher) {
                                return new Message(null);

                            }
                            return new Message(listStudents());
                    }
                    return new Message(null);
                case QUIZ:
                    switch (request.request) {
                        case ADD:
                        case MODIFY:
                            createUpdate(new Message(listQuizzes(contentPath)), contentPath);
                            return new Message(saveQuiz(contentPath, (Quiz) content));
                        case GET:
                            return new Message(getQuiz(contentPath, (String) content));
                        case REMOVE:
                            if (!isTeacher)
                                return new Message(null);
                            return new Message(removeQuiz(contentPath, (String) content));
                        case LIST:
                            return new Message(listQuizzes((String) content));
                    }
                    return new Message(null);
                case SUBMISSION:
                    switch (request.request) {
                        case ADD:
                        case MODIFY:
                            if (!isTeacher)
                                createUpdate(new Message(listQuizAttempts(contentPath)), contentPath);
                            return new Message(saveQuizAttempt(contentPath, (Quiz) content));
                        case GET:
                            return new Message(getQuizAttempt(contentPath, (String) content));
                        case LIST:
                            return new Message(listQuizAttempts((String) content));
                    }
                    return new Message(null);
                case COURSE:
                    switch (request.request) {
                        case ADD:
                        case MODIFY:
                            Course course = new Course((String) content);
                            saveCourse(course);
                            return new Message(true);
                        case REMOVE:
                            if (!isTeacher)
                                return new Message(null);
                            return new Message(removeCourse((String) content));
                        case LIST:
                            return new Message(listCourses());
                    }
                    return new Message(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(null);
        }
        return new Message(null);
    }

    private boolean logIn(String username, String password) {
        try {
            Account account = getAccount(username, password);
            isTeacher = account instanceof Teacher;
            this.account = account;
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private boolean signUp(Account account) {
        super.setFolderPrefix("data/accounts/");
        if (accountExists(account.getUsername()))
            return false;
        super.addData(account, account.getUsername());
        isTeacher = account instanceof Teacher;
        this.account = account;
        return true;
    }

    /**
     * Returns account used to log in or sign up
     * @return account used to log in or sign up
     */
    public Account getAccount() {
        return account;
    }
    
    /**
     * Returns if you have to send an update to the client
     * @return if you have to send an update to the client
     */
    public synchronized static boolean requiresUpdate() {
        return update != null; 
    }
    
    /**
     * gets the update to send to the client
     * @return the update to send to the client
     */
    public synchronized static Message getUpdateResponse() {
        Message tempUpdate = update;
        update = null;
        return tempUpdate;
    } 

    private synchronized static void createUpdate(Message update, String pathToUpdate) {
        ServerDataHandler.update = update;
        ServerDataHandler.pathToUpdate = pathToUpdate;
    }
    
    /**
     * if the client requires an update
     * @return if the client requires an update
     */
    public boolean requiresUpdate(String pathToUpdate) {
        return (pathToUpdate.equals(lastMessage.contentPath) &&
                lastMessage.request == Message.requestType.LIST);
    }

    private Quiz getQuizAttempt(String username, String quizIdentifier) throws NullPointerException {
        Student student = getStudentAccount(username);
        Quiz quiz = student.getQuiz(quizIdentifier);
        if (quiz != null)
            return quiz;
        else
            throw new NullPointerException("an account with that username / password combination was not found");
    }

    private boolean saveQuizAttempt(String username, Quiz quiz) {
        try {
            Student student = getStudentAccount(username);
            if (isTeacher) {
                boolean toReturn = student.overwriteQuizSubmission(quiz);
                saveAccount(student);
                return toReturn;
            } else {
                boolean toReturn = student.addNewQuizSubmission(quiz);
                saveAccount(student);
                return toReturn;
            }
        } catch (Exception e) {
            return false;
        } 
    }

    private String[] listQuizAttempts(String userName) throws NullPointerException {
        Student student = getStudentAccount(userName);
        return (student.getQuizIdentifiers());
    }
    
    private Quiz getQuiz(String courseName, String quizName) {
        try {
            Course course = getCourse(courseName);
            Quiz quiz = course.getQuiz(quizName);
            if (quiz != null)
                return quiz;
        } catch (NullPointerException e) {
            //
        }
        return null;
    }
    
    private boolean saveQuiz(String courseName, Quiz quiz) {
        if (!isTeacher) {
        return false;
        }
        Course course;
        try {
            course = getCourse(courseName);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        if (course != null) {
            boolean toReturn = course.addQuiz(quiz);
            saveCourse(course);
            return toReturn;
        } else
           //stuff n things
        return false;
    }
    
    private boolean removeQuiz(String courseName, String identifier) {
        if (!isTeacher)
            return false;
        boolean toReturn;
        try {
            Course course = getCourse(courseName);
            toReturn = course.removeQuiz(identifier);
            saveCourse(course);
            return toReturn;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private String[] listQuizzes(String courseName) {
        Course course = getCourse(courseName);
        if (course == null) {
            return null;
        } else {
            return course.getQuizNames();
        }
    }
   
    private void saveAccount(Account account) {
        super.setFolderPrefix("data/accounts/");
        super.addData(account, account.getUsername());
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
        super.setFolderPrefix("data/accounts/");
        return super.fileExists(accountName);
    }

    private Student getStudentAccount(String username) throws NullPointerException {
        super.setFolderPrefix("data/accounts/");
        Account account = (Account) super.get(username);
        if (account instanceof Student)
            return (Student) account;
        else
            throw new NullPointerException("a student account with that username doesn't exist");
    }

    private String[] listStudents() {
        super.setFolderPrefix("data/accounts/");
        if (!isTeacher) {
            return null;
        }
        Object[] ol = super.getListVerbose();
        ArrayList<Student> sal = new ArrayList<Student>();
        
        for (int i = 0; i < ol.length; i++) {
            if (ol[i] instanceof Student)
                sal.add((Student) ol[i]);
        }
        
        String[] usernameList = new String[sal.size()];
        for (int i = 0; i < sal.size(); i++) {
            usernameList[i] = sal.get(i).getUsername();
        }
        return usernameList;
    }
    
    private void saveCourse(Course course) {
        super.setFolderPrefix("data/courses/");
        super.addData(course, course.getName().replace(" ", "-"));
    }

    private Course getCourse(String courseName) throws NullPointerException {
        super.setFolderPrefix("data/courses/");
        return (Course) super.get(courseName.replace(" ", "-"));
    }

    private boolean courseExists(String courseName) throws NullPointerException {
        super.setFolderPrefix("data/courses/");
        return super.fileExists(courseName);
    }

    private String[] listCourses() {
        super.setFolderPrefix("data/courses/");
        Object[] ol = super.getListVerbose();
        String[] courseNameList = new String[ol.length];
        for (int i = 0; i < ol.length; i++) {
            courseNameList[i] = ((Course) ol[i]).getName();
        }
        return courseNameList;
    }

    private boolean removeCourse(String courseName) {
        if (!isTeacher) {
            return false;
        }
        super.setFolderPrefix("data/courses/");
        return super.removeData(courseName);
    }
}

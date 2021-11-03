/**
 * @author Hawkins Peterson
 * @version 11.03.21
 * 
 * Anything that stores or gets data in our project
 */
public abstract class AccessData {
    private final String ACCOUNT_FILE_NAME = "data/accounts.txt";
    private final String QUIZZES_FILE_NAME = "data/quizzes.txt";
    //accounts
    /**
     * verifies the username and password of the user and then returns an account object corosponding to that user
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return an account object corosponding to the username and password given 
     */
    public abstract Account getAccountData(String username, String password) throws NullPointerException;
    /**
     * creates a user object using the username, password and if the account is a student or teacher account
     * @param username the username of the account
     * @param password the password of the account
     * @param isStudent if the account is a student account
     */
    public abstract void addUser(String username, String password, boolean isStudent);
    /**
     * saves an account using the account object and which type of account the user is.
     * @param account the account of the user (mainly uses the toString method)
     * @param isStudent if the account is a student account
     */
    public abstract void saveAccountData(Account account, boolean isStudent);

    //quizzes
    /**
     * allows a teacher to create a quiz
     * @param quizName the name of the quiz
     * @param quiz the quiz object (uses the toString method mainly)
     */
    public abstract void addQuiz(String quizName, Quiz quiz) throws NullPointerException;
    /**
     * Modifies an already existing quiz
     * @param quizName the name of the quiz
     * @param quiz the quiz object (uses the toString mainly)
     */
    public abstract void modifyQuiz(String quizName, Quiz quiz) throws NullPointerException;
    /**
     * Removes a quiz with name quizName
     * @param quizName the name of the quiz
     */ 
    public abstract void removeQuiz(String quizName) throws NullPointerException;
    
    //student
    /**
     * saves a quiz the student's taken
     * @param username the username of the student
     * @param quizName the name of the quiz
     * @param answers the student's answers to the quiz (might be changed to int[])
     */
    public abstract void saveTakenQuiz(String username, String quizName, char[] answers);
}

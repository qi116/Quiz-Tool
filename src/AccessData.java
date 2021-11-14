import java.io.*;
import java.util.*;

/**
 * @author Hawkins Peterson
 * @version 11.03.21
 * 
 * Anything that stores or gets data in our project
 */
public class AccessData {
    /**
     * verifies the username and password of the user and then returns an account object corosponding to that user
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return an account object corosponding to the username and password given 
     */
    public static Account getAccountData(String username, String password) throws NullPointerException {
        try {
            Account account = (Account) getObjectFromFile("accounts/" + username + ".obj");
            if (account.getPassword().equals(password))
                return account;
            throw new NullPointerException("No account with that username / password combination");
        } catch (FileNotFoundException e) {
            throw new NullPointerException("No account with that username / password combination");
        }
    }

    /**
     * used for teachers to fetch student's quizes submissions without requiring their password
     * @param username
     * @return an account of a student that a teacher is fetching
     * @throws NullPointerException
     */
    public static Account getAccountData(String username) throws NullPointerException {
        try {
            Account account = (Account) getObjectFromFile("accounts/" + username + ".obj");
            return account;
        } catch (FileNotFoundException e) {
            throw new NullPointerException("No account with that username");
        }
    }
    /**
     * returns if a username already exists (true = yell at user, false = ok)
     * @return if a username already exists (true = yell at user, false = ok)
     * @param username the username to check
     */
    public static boolean usernameExists(String username) {
        try {
            new FileInputStream("data/accounts/" + username + ".obj");
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    /**
     * Creates a new account (throws FileAlreadyExistsException if username taken)
     * I will add a checkUsername(String username) class later
     * @param account an account to be added to the system
     */
    public static void addAccount(Account account) throws FileAlreadyExistsException {
        try {
            new FileInputStream("data/accounts/" + account.getUsername() + ".obj");
            throw new FileAlreadyExistsException("Account already exists");
        } catch (FileNotFoundException e) {
            writeObjectToFile("accounts/" + account.getUsername(), account);
        }
    }

    /**
     * Gets all accounts in /data/accounts/
     * @return Returns a List of all student accounts
     * @throws NullPointerException (if there is no /data/accounts/)
     */
    private static Account[] getAllAccounts() throws NullPointerException {
        Account[] accounts;
        try {
            File folder = new File("data/accounts");
            File[] files = folder.listFiles();
            ArrayList<Account> accountsArrayList= new ArrayList<Account>();
            for (int i = 0; i < files.length; i++) {
                Account a = (Account) getObjectFromFile("/accounts/" + files[i].getName());
                if (a instanceof Student)
                    accountsArrayList.add(a);
            }
            accounts = new Account[accountsArrayList.size()];
            for (int i = 0; i < accountsArrayList.size(); i++) {
                accounts[i] = accountsArrayList.get(i);
            }
        } catch (NullPointerException e) {
            accounts = new Account[0];
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Folders not created! (data/accounts && data/courses required)");
        }
        return accounts;
    }

    /**
     * gets all usernames
     * @return all usernames
     */
    public static String[] getAllUsernames() {
        Account[] accounts = getAllAccounts();
        String[] usernames = new String[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            usernames[i] = accounts[i].getUsername();
        }
        return usernames;
    }

    /**
     * saves a user object using the account name
     * @param account the account to be added
     */
    public static void writeAccountData(Account account) {
        writeObjectToFile("accounts/" + account.getUsername(), account);
    }

    /**
     * Gets an already existing course
     * @param courseName the name of the course to be fetched
     * @return an already existing course
     */
    public static Course getCourse(String courseName) throws NullPointerException {
        try {
            return (Course) getObjectFromFile("courses/" +
                    courseName.replace(" ","-") + ".obj");
        } catch (FileNotFoundException e) {
            throw new NullPointerException("A quiz with that name does not exist");
        }
    }

    /**
     * Returns a list of all courses
     * If no courses are to be found, returns an empty list
     * @return a list of all courses
     */
    private static Course[] getAllCourses() throws NullPointerException {
        Course[] courses;
        try {
            File folder = new File("data/courses");
            File[] files = folder.listFiles();
            courses = new Course[files.length];
            for (int i = 0; i < files.length; i++) {
                courses[i] = (Course) getObjectFromFile("/courses/" + files[i].getName());
            }
        } catch (NullPointerException e) {
            //
            courses = new Course[0];
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Folders not created! (data/accounts && data/courses required)");
        }
        return courses;
    }
    /**
     * Returns a string of all course names
     * @return a string of all course names
     */
    public static String[] getAllCourseNames() {
        Course[] courses = getAllCourses();
        String[] courseNames = new String[courses.length];
        for (int i = 0; i < courses.length; i++) {
            courseNames[i] = courses[i].getName();
        }
        return courseNames;
    }
    /**
     * allows a teacher to create a course
     * @param course the course object
     */
    public static void addCourse(Course course) throws FileAlreadyExistsException {
        try {
            new FileInputStream("data/courses/" + course.getName().replace(" ", "-") + ".obj");
            throw new FileAlreadyExistsException("File already exists");
        } catch (Exception e) {
            writeObjectToFile("courses/" + course.getName().replace(" ", "-"), course);
        }
    }

    /**
     * Modifies an already existing course
     * @param courseName the name of the course
     * @param course the course object
     */
    public static void modifyCourse(String courseName, Course course) throws NullPointerException {
        try {
            new FileInputStream("data/courses/" + courseName.replace(" ", "-") + ".obj");
            new File("data/courses/" + course.getName().replace(" ", "-") + ".obj");
            writeObjectToFile("courses/" + course.getName().replace(" ", "-"), course);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("File does not exists");
        }
    }

     /**
     * Removes a course with name courseName
     * @param courseName the name of the course
     */
    public static void removeCourse(String courseName) throws NullPointerException {
        try {
            new FileInputStream("data/courses/" + courseName + ".obj");
            File f = new File("data/courses/" + courseName + ".obj");
            f.delete();
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Course does not exist");
        }
    }
    
    /**
     * Returns a quiz from within a course (courseName)
     * @param courseName the name of the course the quiz is in
     * @param quizName the name of the quiz to be fetched
     */
    public static Quiz getQuiz(String courseName, String quizName) throws NullPointerException {
        try {
            File f = new File("courses/" + courseName.replace(" ", "-"));
            Course c = (Course) getObjectFromFile(courseName);
            return c.getQuiz(quizName);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Course does not exist");
        }
    }

    private static Object getObjectFromFile(String fileName) throws FileNotFoundException {
        Object toReturn = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File("data/" + fileName)));
            toReturn = ois.readObject();
            ois.close();
        } catch (IOException e) {
            //
        } catch (ClassNotFoundException e) {
            throw new FileNotFoundException("Error, object empty");
        }
        return toReturn;
    }

    private static void writeObjectToFile(String fileName, Object o) {
        try {
            createFolders();
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("data/" + fileName + ".obj")));
            oos.writeObject(o);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            //
        }
    }

    /**
     * ThIs HaS tO bE dOnE cLiEnT sIdE
     */
    private static void createFolders() {
        try {
            new File("/data").mkdir();
            new File("/data/accounts").mkdir();
            new File("data/courses").mkdir();
        } catch (Exception e) {
            //
        }
    }
}

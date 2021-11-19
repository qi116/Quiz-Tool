import java.io.*;
import java.util.*;

/**
 * @author Hawkins Peterson
 * @version 11.03.21
 * 
 * Anything that stores or gets data in our project
 */
public class AccessData {

    public static void readInMessage(Object message) throws NullPointerException, FileAlreadyExistsException 
    {
        if (!true) {
            throw new NullPointerException("I'm an idiot and can't code");
        } 
        if (!true) {
            throw new FileAlreadyExistsException("I'm an idiot who can't code");
        }
        System.out.println("Saving data"); //not really (in this branch at least for right now)
    }

    public static Object createMessage(Account account) {
        System.out.println("Not ready for testing idiot");
        return null;
    }

    public static Object createMessage(Course course) {
        System.out.println("Not ready for testing idiot");
        return null;
    }
    public static Object createMessage(String[] listOfNames, DataType dataType) {
        System.out.println("Not ready for testing idiot");
        return null;
    }
    public static Object createMessage(Boolean exists, DataType dataType) {
        System.out.println("Not ready for testing idiot");
        return null;
    }
    public static Object createMessage(Quiz quiz) {
        System.out.println("Not ready for testing idiot");
        return null;
    }
    /**
     * verifies the username and password of the user and then returns an account object corosponding to that user
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return an account object corosponding to the username and password given 
     */
    public Account getAccountData(String username, String password) throws NullPointerException {
        checkFileExists("accounts/" + username);

        Account account = (Account) getObjectFromFile("accounts/" + username);
        if (account.getPassword().equals(password))
            return account;
        throw new NullPointerException("No account with that username / password combination");
    }

    /**
     * used for teachers to fetch student's quizes submissions without requiring their password
     * @param username
     * @return an account of a student that a teacher is fetching
     * @throws NullPointerException
     */
    public Student getAccountData(String username) throws NullPointerException {
        checkFileExists("accounts/" + username);

        Account account = (Account) getObjectFromFile("accounts/" + username);
        return (Student) account;        
    }
    /**
     * returns if a username already exists (true = yell at user, false = ok)
     * @return if a username already exists (true = yell at user, false = ok)
     * @param username the username to check
     */
    public boolean usernameExists(String username) {
        try {
            checkFileExists("account/" + username);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Creates a new account (throws FileAlreadyExistsException if username taken)
     * I will add a checkUsername(String username) class later
     * @param account an account to be added to the system
     */
    public void addAccount(Account account) throws FileAlreadyExistsException {
        createFolders();
        checkFileDoesNotExist("accounts/" + account.getUsername());
        writeObjectToFile("accounts/" + account.getUsername(), account);
    }

    /**
     * Gets all accounts in /data/accounts/
     * @return Returns a List of all student accounts
     * @throws NullPointerException (if there is no /data/accounts/)
     */
    private synchronized Account[] getAllAccounts() throws NullPointerException {
        Account[] accounts;
        try {
            File folder = new File("data/accounts");
            File[] files = folder.listFiles();
            ArrayList<Account> accountsArrayList = new ArrayList<Account>();
            for (int i = 0; i < files.length; i++) {
                Account a = (Account) getObjectFromFile("/accounts/" + files[i].getName());
                if (a instanceof Student)
                    accountsArrayList.add(a);
            }
            accounts = new Account[accountsArrayList.size()];
            for (int i = 0; i < accountsArrayList.size(); i++) {
            }
            accounts = new Account[accountsArrayList.size()];
            for (int i = 0; i < accountsArrayList.size(); i++) {
                accounts[i] = accountsArrayList.get(i);
            }
        } catch (NullPointerException e) {
            accounts = new Account[0];
        } 
        return accounts;
    }

    /**
     * gets all usernames
     * @return all usernames
     */
    public String[] getAllUsernames() {
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
    public void writeAccountData(Account account) {
        writeObjectToFile("accounts/" + account.getUsername(), account);
    }

    /**
     * Gets an already existing course
     * @param courseName the name of the course to be fetched
     * @return an already existing course
     */
    public Course getCourse(String courseName) throws NullPointerException {
        checkFileExists("courses/" + courseName.replace(" ", "-"));
        return (Course) getObjectFromFile("courses/" + courseName.replace(" ", "-"));
    }

    /**
     * Returns a list of all courses
     * If no courses are to be found, returns an empty list
     * @return a list of all courses
     */
    private synchronized Course[] getAllCourses() throws NullPointerException {
        Course[] courses;
        try {
            File folder = new File("data/courses");
            File[] files = folder.listFiles();
            courses = new Course[files.length];
            for (int i = 0; i < files.length; i++) {
                courses[i] = (Course) getObjectFromFile("courses/" + files[i].getName());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            courses = new Course[0];
        }
        return courses;
    }
    /**
     * Returns a string of all course names
     * @return a string of all course names
     */
    public String[] getAllCourseNames() {
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
    public void addCourse(Course course) throws FileAlreadyExistsException {
        checkFileDoesNotExist("courses/" + course.getName().replace(" ", "-"));
        writeObjectToFile("courses/" + course.getName().replace(" ", "-"), course);
    }

    /**
     * Modifies an already existing course
     * @param courseName the name of the course
     * @param course the course object
     */
    public void modifyCourse(Course course) throws NullPointerException {
        checkFileExists("courses/" + course.getName().replace(" ","-"));
        writeObjectToFile("courses/" + course.getName().replace(" ", "-"), course);
    }

     /**
     * Removes a course with name courseName
     * @param courseName the name of the course
     */
    public synchronized void removeCourse(String courseName) throws NullPointerException {
        checkFileExists("courses/" + courseName);
        File f = new File("data/courses/" + courseName + ".obj");
        f.delete();
    }


    private synchronized Object getObjectFromFile(String fileName) throws NullPointerException {
        Object toReturn = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File("data/" + fileName + ".obj")));
            toReturn = ois.readObject();
            ois.close();
        } catch (IOException e) {
            //
        } catch (ClassNotFoundException e) {
            throw new NullPointerException("Error, object empty");
        } 
        return toReturn;
    }

    private synchronized void writeObjectToFile(String fileName, Object o) {
        try {
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
    private synchronized void createFolders() {
        try {
            new File("data").mkdir();
            new File("data/accounts").mkdir();
            new File("data/courses").mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void checkFileExists(String fileName) throws NullPointerException {
        try {
            new FileInputStream("data/" + fileName + ".obj");
        } catch (FileNotFoundException e) {
            throw new NullPointerException("File Doesn't exist");
        }
    }

    private synchronized void checkFileDoesNotExist(String fileName) throws FileAlreadyExistsException {
        try {
            new FileInputStream("data/" + fileName + ".obj");
            throw new FileAlreadyExistsException("File already exists");
        } catch (FileNotFoundException e) {
            //if the file doesn't exist, Yay!
        }
    }
}

enum DataType {
    ACCOUNT("accounts/"),
    STUDENT("accounts/"),
    COURSE("courses/"),
    STRING(""),
    STRINGL("");


    String folder;
    DataType(String folder) {
        this.folder = folder;
    }
}

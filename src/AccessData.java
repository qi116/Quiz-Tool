import java.io.*;
import java.util.*;

/**
 * @author Hawkins Peterson
 * @version 11.03.21
 * 
 * Anything that stores or gets data in our project
 */
public abstract class AccessData {
    /**
     * verifies the username and password of the user and then returns an account object corosponding to that user
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return an account object corosponding to the username and password given 
     */
    public Account getAccountData(String username, String password) throws NullPointerException {
        try {
            Acount account = (Account) getObjectFromFile("accounts/" + username);
            if (account.getPassword().equals(password))
                return account;
            else
                throw new NullPointerException("No account with that username / password combination");
        } catch (FileNotFoundException e) {
            throw new NullPointerException("No account with that username / password combination");
        }
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
    public Quiz getCourse(String courseName) throws NullPointerException {
        try {
            return (Course) getObjectFromFile("courses/" + courseName.replace(" ","-"));
        } catch (FileNotFoundException e) {
            throw new NullPointerException("A quiz with that name does not exist");
        }
    }
    /**
     * allows a teacher to create a course
     * @param course the course object
     */
    public void addCourse(Course course) throws FileAlreadyExistsException {
        try {
            new File("courses/" + course.getName().replace(" ","-"));
            throw new Exception("File already exists");
        } catch (FileNotFoundException e) {
            writeObjectToFile("course/" + course.getName().replace(" ","-"), quiz);
        }
    }

    /**
     * Modifies an already existing course
     * @param courseName the name of the course
     * @param course the course object
     */
    public void modifyCourse(String courseName, Course course) throws NullPointerException {
        try {
            new File("courses/" + course.getName().replace(" ","-"));
            writeObjectToFile("courses/" + course.getName().replace(" ","-"), course);
        } catch (FileNotFoundException e) {
            throw new Exception("File already exists");
        }
    }

    /**
     * Removes a course with name courseName
     * @param courseName the name of the course
     */ 
    public void removeCourse(String courseName) throws NullPointerException {
        try {
            File f = new File("course/" + course.getName().replace(" ", "-"));
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
    public Quiz getQuiz(String courseName, String quizName) throws NullPointerException {
        try {
            File f = new File("course/" + courseName.replace(" ", "-"));
            Course c = (course) getObjectFromFile(courseName);
            return c.getQuiz(quizName);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Course does not exist");
        }
    }

    private Object getObjectFromFile(String fileName) throws FileNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File("data/" + fileName + ".obj")));
            Object toReturn = ois.readObject();
            ois.close();
            return toReturn;
        } catch (IOException e) {
            System.out.println("error initalizing file output stream");
        }
    }
    
    private void writeObjectToFile(String fileName, Object o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("data/" + fileName + ".obj")));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            System.out.println("error initalizing file output stream");
        }
    }
}

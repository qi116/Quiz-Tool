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
    public Account getAccountData(String username, String password) throws NullPointerException {
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
     * returns if a username already exists (true = yell at user, false = ok)
     * @return if a username already exists (true = yell at user, false = ok)
     * @param username the username to check
     */
    public boolean usernameExists(String username) {
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
    public void addAccount(Account account) throws FileAlreadyExistsException {
        try {
            new FileInputStream("data/accounts/" + account.getUsername() + ".obj");
            throw new FileAlreadyExistsException("Account already exists");
        } catch (FileNotFoundException e) {
            writeObjectToFile("accounts/" + account.getUsername(), account);
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
    public Course getCourse(String courseName) throws NullPointerException {
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
    public Course[] getAllCourses() throws NullPointerException {
        Course[] courses;
        try {
            File folder = new File("data/courses");
            File[] files = folder.listFiles();
            courses = new Course[files.length];
            for (int i = 0; i < files.length; i++) {
                courses[i] = (Course) getObjectFromFile("/courses/" + files[i].getName());
            }
        } catch (NullPointerException e) {
            System.out.println("null error");
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
        try {
            System.out.println(course.getName().replace(" ", "-"));
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
    public void modifyCourse(String courseName, Course course) throws NullPointerException {
        try {
            new FileInputStream("data/course/" + courseName);
            new File("data/courses/" + course.getName().replace(" ","-"));
            writeObjectToFile("courses/" + course.getName().replace(" ","-"), course);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("File does not exists");
        }
    }

    /**
     * Removes a course with name courseName
     * @param courseName the name of the course
     */ 
    public void removeCourse(String courseName) throws NullPointerException {
        try {
            new FileInputStream("data/courses/" + courseName);
            File f = new File("data/courses/" + courseName);
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
            File f = new File("courses/" + courseName.replace(" ", "-"));
            Course c = (Course) getObjectFromFile(courseName);
            return c.getQuiz(quizName);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Course does not exist");
        }
    }

    /**
     * returns a string holding the contents of a file given the file name
     * @return a string holding the contents of a file given the file name
     */
    private String getStringFromFile(String fileName) throws NullPointerException {
        String output = "";
        try {
            new FileInputStream(fileName);
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            String line = "";
            do {
                line = bfr.readLine() + "\n";
                output += line;
            } while (!line.isEmpty());
        } catch (FileNotFoundException e) {
            throw new NullPointerException("File path doesn't exist");
        } catch (IOException e) {
            System.out.println("Error fetching from file");
        }
        return output;
    }


    public Object getObjectFromFile(String fileName) throws FileNotFoundException {
        Object toReturn = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File("data/" + fileName)));
            toReturn = ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("error initalizing file output stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new FileNotFoundException("Error, object empty");
        }
        return toReturn;
    }

    private void writeObjectToFile(String fileName, Object o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("data/" + fileName + ".obj")));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error initalizing file output stream");
        }
    }
}

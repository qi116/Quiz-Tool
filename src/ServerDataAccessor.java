import java.io.*;
import java.util.*;

/**
 * @author Hawkins Peterson
 * @version 11.03.21
 * 
 * Anything that stores or gets data in our project
 */
public class ServerDataAccessor {
    private final String folder; 
    private final String fileType;


    public ServerDataAccessor(String folder, String fileType) {
        this.folder = folder;
        this.fileType = fileType;
    }

    public ServerDataAccessor() {
        folder = "/data";
        fileType = ".obj";
    }
    /**
     * verifies the username and password of the user and then returns an account object corosponding to that user
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return an account object corosponding to the username and password given 
     */
    protected Object get(String fileName) throws NullPointerException {
        checkFileExists(fileName);
        return getObjectFromFile(fileName);
    }

    /**
     * returns if a username already exists (true = yell at user, false = ok)
     * @return if a username already exists (true = yell at user, false = ok)
     * @param username the username to check
     */
    protected boolean checkExists(String fileName) {
        try {
            checkFileExists(fileName);
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
    protected void addData(Object o, String filename) throws FileAlreadyExistsException {
        createFolders();
        checkFileDoesNotExist(filename);
        writeObjectToFile(filename, o);
    }

    /**
     * Gets all accounts in /data/accounts/
     * @return Returns a List of all student accounts
     * @throws NullPointerException (if there is no /data/accounts/)
     */
    protected synchronized Object[] getListVerbose() throws NullPointerException {
        Object[] objects;
        try {
            File folder = new File(this.folder);
            File[] files = folder.listFiles();
            ArrayList<Object> objectsArrayList = new ArrayList<Object>();
            for (int i = 0; i < files.length; i++) {
                Object o = getObjectFromFile(files[i].getName());
                objectsArrayList.add(o);
            }
            objects = new Objects[objectsArrayList.size()];
            for (int i = 0; i < objectsArrayList.size(); i++) {
            }
            objects = new Objects[objectsArrayList.size()];
            for (int i = 0; i < objectsArrayList.size(); i++) {
                objects[i] = objectsArrayList.get(i);
            }
        } catch (NullPointerException e) {
            objects = new Account[0];
        } 
        return objects;
    }

    /**
     * saves a user object using the account name
     * @param account the account to be added
     */
    protected void modifyData(Object o, String filename) throws NullPointerException {
        checkFileExists(filename);
        writeObjectToFile(filename, o);
    }

         /**
     * Removes a course with name courseName
     * @param courseName the name of the course
     */
    protected synchronized void removeData(String fileName) throws NullPointerException {
        checkFileExists(fileName);
        File f = new File(folder + fileName + fileType);
        f.delete();
    }


    private synchronized Object getObjectFromFile(String fileName) throws NullPointerException {
        Object toReturn = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File(folder + fileName + fileType)));
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
                    new FileOutputStream(new File(folder + fileName + fileType)));
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
            new FileInputStream(folder + fileName + fileType);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("File Doesn't exist");
        }
    }

    private synchronized void checkFileDoesNotExist(String fileName) throws FileAlreadyExistsException {
        try {
            new FileInputStream(folder + fileName + fileType);
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

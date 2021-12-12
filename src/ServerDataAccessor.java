import java.io.*;
import java.util.*;

/**
 * @author Hawkins Peterson
 * @version 11.03.21
 *
 * Base class for anything that stores or gets data in our project
 */
public class ServerDataAccessor {
    private String folderPrefix = "data/"; //the prefix for the file you want to access (ie data/accounts/)
    private final String fileType; //the filetype of the file you want to access

    /**
     * Constructs a new data accessor
     *
     * @param folderPrefix the prefix of the file you want to access
     * @param fileType the fileType of the file you want to access
     */
    public ServerDataAccessor(String fileType) {
        this.folderPrefix = folderPrefix;
        this.fileType = fileType;
    }

    /**
     * Sets a new folder prefix
     *
     * @param folderPrefix the new folder prefix
     */
    public void setFolderPrefix(String folderPrefix) {
        this.folderPrefix = folderPrefix;
    }

    /**
     * verifies the username and password of the user and then returns an account object corosponding to that user
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return an account object corosponding to the username and password given (or null if it doesn't exist)
     */
    protected Object get(String fileName) {
        if (fileExists(fileName))
            return getObjectFromFile(fileName);
        return null;
    } 

    /**
     * Writes a new object into data
     * @return if it worked
     * @param o an object to be added to the system
     */
    protected void addData(Object o, String filename) {
        createFolders();
        writeObjectToFile(filename, o);
    }

    /**
     * Gets all objects in the currently selected folder
     *
     * @return Returns a List of all objects in the folder
     * @throws NullPointerException if there is no folder you're trying to access
     */
    protected synchronized Object[] getListVerbose() throws NullPointerException {
        Object[] objects;
        try {
            File folder = new File(this.folderPrefix);
            File[] files = folder.listFiles();
            ArrayList<Object> objectsArrayList = new ArrayList<Object>();
            
            for (int i = 0; i < files.length; i++) {
                Object o = getObjectFromFile(files[i].getName()
                        .substring(0,files[i].getName().length() - 4));
                objectsArrayList.add(o);
            }
            objects = new Object[objectsArrayList.size()];
            for (int i = 0; i < objectsArrayList.size(); i++) {
                objects[i] = objectsArrayList.get(i);
            }

            return objects;
        } catch (NullPointerException e) {
            objects = new Account[0];
        }
        return objects;
    }

    /**
     * saves an object using the file name
     * @return if it worked
     * @param account the object to be added
     */
    protected boolean modifyData(Object o, String filename) {
        return writeObjectToFile(filename, o);
    }

         /**
     * Removes an object with name fileName
     * @return if it worked
     * @param courseName the name of the file to be removed
     */
    protected synchronized boolean removeData(String fileName){
        if (fileExists(fileName)) {
            File toDelete = new File(folderPrefix + fileName + fileType);
            System.out.println(toDelete.delete() ? "deleted!" : "failed to delete file");
            System.out.println("delete successfull " + folderPrefix + fileName + fileType);
            return true;
        }
        System.out.println("error, " + folderPrefix + fileName + fileType + " doesn't exist");
        return false;
    }


    private synchronized Object getObjectFromFile(String fileName) throws NullPointerException {
        Object toReturn = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(folderPrefix + fileName + fileType));
            toReturn = ois.readObject();
            ois.close();
        } catch (IOException e) {
            //asdf
        } catch (ClassNotFoundException e) {
            throw new NullPointerException("Error, object empty");
        }
        return toReturn;
    }
    //returns if it worked
    private synchronized boolean writeObjectToFile(String fileName, Object o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File(folderPrefix + fileName + fileType)));
            oos.writeObject(o);
            oos.flush();
            oos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private synchronized void createFolders() {
        try {
            File f;
            f = new File("data");
            f.mkdir();
            f = new File("data/accounts");
            f.mkdir();
            f = new File("data/courses");
            f.mkdir();
        } catch (Exception e) {
            //
        }
    }
    /**
     * Returns if a file already exists
     * @return if a file already exists
     */
    protected synchronized boolean fileExists(String fileName) throws NullPointerException {
        try {
            FileInputStream f = new FileInputStream(folderPrefix + fileName + fileType);
            f.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}

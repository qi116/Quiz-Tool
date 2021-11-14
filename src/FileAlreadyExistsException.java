/**
 * @author Hawkins Peterson
 * @version 11/14/2021
 */
public class FileAlreadyExistsException extends Exception {
    public FileAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}

import java.io.Serializable;

/**
 * @author Nathan Leakeas
 * @author Hawkins Peterson
 * @version 12/8/2021
 */
public class Message implements Serializable {

    /**
     * @author Nathan Leakeas
     * @version 12/8/2021
     */
    public enum RequestType {
        LOGIN, //response: LOGIN, ACCOUNT, boolean[]: {success, isTeacher}
        LOGOUT, //response: none
        ADD, //response: ADD, dataType, boolean: success
        GET, //response: GET, dataType, Quiz if Quiz, String if any other data type
        MODIFY, //response: MODIFY, dataType, success
        REMOVE, //response: REMOVE, dataType,
        LIST, //response: String[] list of names
        UPDATE //sending update back to client
    };

    /**
     * @author Nathan Leakeas
     * @version 12/8/2021
     */
    public enum DataType {
        ACCOUNT,
        QUIZ,
        SUBMISSION,
        COURSE,
    };

    public Object content;
    public String contentPath;
    public RequestType request;
    public DataType data;

    public Message(RequestType request, DataType data, Object content) {
        this.content = content;
        this.request = request;
        this.data = data;
    }

    public Message(RequestType request, DataType data, String contentPath, Object content) {
        this.request = request;
        this.data = data;
        this.content = content;
        this.contentPath = contentPath;
    }

    public Message(Object object) {
        this.content = object;
    }
} 

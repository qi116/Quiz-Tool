public class Message {

    public enum requestType {
        LOGIN, //response: LOGIN, ACCOUNT, boolean[]: {success, isTeacher}
        LOGOUT, //response: none
        ADD, //response: ADD, dataType, boolean: success
        GET, //response: GET, dataType, Quiz if Quiz, String if any other data type
        MODIFY, //response: MODIFY, dataType, success
        REMOVE, //response: REMOVE, dataType,
        LIST, //response: String[] list of names
        UPDATE //sending update back to client
    };

    public enum dataType {
        ACCOUNT,
        QUIZ,
        SUBMISSION,
        COURSE,
    };

    public Object content;
    public String contentPath;
    public requestType request;
    public dataType data;

    public Message(requestType request, dataType data, Object content) {
        this.content = content;
        this.request = request;
        this.data = data;
    }

    public Message(requestType request, dataType data, String contentPath, Object content) {
        this.request = request;
        this.data = data;
        this.content = content;
        this.contentPath = contentPath;
    }

    public Message(Object object) {
        this.content = object;
    }
}

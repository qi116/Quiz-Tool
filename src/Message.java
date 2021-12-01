public class Message {
    public enum requestType {
        LOGIN,
        LOGOUT,
        ADD,
        GET,
        MODIFY,
        REMOVE
    };

    public enum dataType {
        ACCOUNT,
        QUIZ,
        SUBMISSION,
        COURSE,
        COURSELIST
    };

    public Object content;
    public requestType request;
    public dataType data;

    public Message(requestType request, dataType data, Object content) {
        content = content;
        request = request;
        data = data;
    }

}

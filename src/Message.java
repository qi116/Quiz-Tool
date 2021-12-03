public class Message {

    public enum requestType {
        LOGIN, //response: LOGIN, ACCOUNT, boolean[]: {success, isTeacher}
        LOGOUT, //response: none
        ADD, //response: ADD, dataType, boolean: success
        GET, //response: GET, dataType, Quiz if Quiz, String if any other data type
        MODIFY, //response: MODIFY, dataType, success
        REMOVE //response: REMOVE, dataType, 
    };

    public enum dataType {
        ACCOUNT,
        QUIZ,
        QUIZLIST,
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


    /**
 * the request class (to be created by the client and accessed by the server)
 *
 */
public class Request extends Message {
    private String contentPath; //the course / account holding the content
    private RequestType requestType; //the type of request you are making
    private boolean isTeacher; //if the user is a teacher

    /**
     * constructs a new request given an object, dataType and requestType
     * to be used by add and modify requests
     *
     * @param contentPath the course / account holding the content
     * @param content the object to be sent to the server
     * @param isTeacher if the requesting user is a teacher
     * @param requestType the type of request you are making of the server
     */
    public Request(String contentPath, Object content, RequestType requestType, boolean isTeacher) {
        super(content);
        this.requestType = requestType;
        this.isTeacher = isTeacher
    }

    /**
     * Returns the request type you are making
     * @return the request type you are making
     */
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Returns if the requesting user is a teacher
     * @return if the requesting user is a teacher
     */
    public boolean getTeacherStatus() {
        return isTeacher;
    }
}

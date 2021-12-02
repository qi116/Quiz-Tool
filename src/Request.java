/**
 * the request class (to be created by the client and accessed by the server)
 *
 */
public class Request extends Message {
    private String contentPath; //the course / account holding the content
    private RequestType requestType; //the type of request you are making

    /**
     * constructs a new request given an object, dataType and requestType
     * to be used by add and modify requests
     *
     * @param contentPath the course / account holding the content
     * @param content the object to be sent to the server
     * @param requestType the type of request you are making of the server
     */
    public Request(String contentPath, Object content, RequestType requestType) {
        super(content);
        this.requestType = requestType;
    }

    /**
     * Returns the request type you are making
     * @return the request type you are making
     */
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Returns the content path to the requested object
     * @return the content path to the requested object
     */
    public String getContentPath() {
        return contentPath;
    }
}

/**
 * the request class (to be created by the client and accessed by the server)
 *
 */
public class Request extends Message {
    private RequestType requestType; //the type of request you are making

    /**
     * constructs a new request given an object, dataType and requestType
     * to be used by add and modify requests
     *
     * @param object the object to be sent to the server
     * @param dataType the dataType of the object (Account or Course only)
     * @param requestType the type of request you are making of the server
     */
    public Request(Object object, DataType dataType, RequestType requestType) {
        super(object, dataType);
        this.requestType = requestType;
    }

    /**
     * constructs a new request given a string array of the makeup {username, password}
     * only used by accounts making a get request
     *
     * @param userPass a username password pair
     */
    public Request(String[] userPass) {
        super(userPass, DataType.ACCOUNT);
        requestType = RequestType.GET;
    }

    /**
     * constructs a new array given a string, dataType of requested data and requestType
     * only used by get / check exists / get_String_Lis requests
     *
     * @param fileName the filename of the requested data
     * @param requestedDataType the datatype of the requested data
     * @param requestType the type of request you are making  
     */
    public Request(String fileName, DataType requestedDataType, RequestType requestType) {
        super(fileName, requestedDataType);
        this.requestType = requestType;
    }

    /**
     * Returns the request type you are making
     * @return the request type you are making
     */
    public RequestType getRequestType() {
        return requestType;
    }
}

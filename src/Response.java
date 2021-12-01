/**
 * the response sent from the server to the client
 * if the RequestType was GET, GET_STRING_LIST, CHECK_EXISTS, or REMOVE it means a NullPointerException was thrown
 * if the RequestType was ADD or MODIFY it means a FileAlreadyExistsException was thrown
 *
 *
 * @author Hawkins Peterson
 * @version 11.30.21
 */
public class Response extends Message {
    private boolean clientMessedUp = false; // null pointer exception
    private boolean iMessedUp = false; // I messed up my coding

    /**
     * Creates a new Response object given an object dataType and errors
     * used to respond to a GET request
     *
     * @param object the object to be sent to the client
     * @param dataType the data type of the object to be sent to the client
     * @param clientMessedUp if the client messed up
     * @param iMessedUp if I messed up my programming
     */
    public Response(Object object, DataType dataType) {
        super(object, dataType);
    }
    
    /**
     * Creates a new response object given a string list, and errors
     * used to respond to a GET_STRING_LIST request
     *
     * @param stringL the string list to be returned to the user
     * @param clientMessedUp if the client messed up
     * @param iMessedUp if I messed my programming
     */
    public Response(String[] stringL) {
        super(stringL, DataType.STRINGL);
        }
    
    /**
     * Creates a new resposnse object given a boolean and errors.
     * used to respond to an ADD, MODIFY, CHECK_EXISTS, and REMOVE request
     *
     * @param bool if the request succeded (for any push to the server) or if the requested object exists
     * @param clientMessedUp if the client messed up
     * @param iMessedUp if I messed up my programming
     */
    public Response(boolean bool) {
        super(bool, DataType.BOOLEAN);
    }
    
    /**
     *
     *
     */
    public Response(boolean clientMessedUp, boolean iMessedUp) {
        super(null, null);
        this.clientMessedUp = clientMessedUp;
        this.iMessedUp = iMessedUp;
    }
    /**
     * if there was an error (use wasExpected error generally)
     * @Returns if there was an error
     */
    public boolean wasError() {
        return clientMessedUp || iMessedUp;
    }

    /**
     * if there was an error and I expected it
     * @return if there was an error and I expected it
     */
    public boolean wasExpectedError() {
        return clientMessedUp;
    }
    /**
     * if there was an error and I didn't expected it
     * @return if there was an error and I didn't expected it
     */
    public boolean wasUnexpectedError() {
        return iMessedUp;
    }
}

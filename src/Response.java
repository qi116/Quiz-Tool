/**
 * the response sent from the server to the client
 * if the RequestType was GET, GET_STRING_LIST, CHECK_EXISTS, or REMOVE it means a NullPointerException was thrown
 * if the RequestType was ADD or MODIFY it means a FileAlreadyExistsException was thrown
 *
 *
 * @author Hawkins Peterson
 * @version 12.02.21
 */
public class Response extends Message {
    private boolean clientError = false; // nullPointeriException or fileAlreadyExistsException
    private boolean devError = false; // I messed up my coding
    
    /**
     * constructs an error message to send back to the user
     *
     * @param clientError if the client messed up (nullPointer or fileAlreadyExists)
     * @param devError any other error
     */
    public Response(boolean clientError, boolean devError) {
        super(null);
        this.clientError = clientError;
        this.devError = devError;
    }
    
    /**
     * creates a response in well... response to a client request
     * 
     * @param content the content to be sent back to the client
     */
    public Response(Object content) {
        super(content);
    }
    
    /**
     * if there was an error and I expected it
     * @return if there was an error and I expected it
     */
    public boolean expectedErrorOccured() {
        return clientError;
    }

    /**
     * if there was an error and I didn't expected it
     * @return if there was an error and I didn't expected it
     */
    public boolean unexpectedErrorOccured() {
        return devError;
    }
}

/**
 * Message class to be sent back and forth DO NOT USE. USE THE DAMN REQUEST CLASS FOR CLIENT > SERVER AND RESPONSE FOR SERVER > CLIENT
 *
 * @author Hawkins Peterson
 * @version 11.30.21
 */
public class Message {
    private Object object; //the object to be sent back and forth
    private DataType dataType; //the type of data sent back and forth

    /**
     * creating a message object to 
     *
     * @param object the object to be sent back and forth
     * @param DataType the dataType of the object
     */
    public Message(Object object, DataType dataType) {
        this.object = object;
        this.dataType = dataType;
    }

    /**
     * Returns the object to be sent back and forth
     * @return the object to be sent back and forth
     */
    public Object getObject() {
        return object;
    }

    /**
     * Returns the dataType of the object
     * @return the dataType of the object
     */
    public DataType getDataType() {
        return dataType;
    }
}

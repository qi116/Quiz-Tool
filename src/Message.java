/**
 * Message class to be sent back and forth DO NOT USE. USE THE DAMN REQUEST CLASS FOR CLIENT > SERVER AND RESPONSE FOR SERVER > CLIENT
 *
 * @author Hawkins Peterson
 * @version 11.30.21
 */
public class Message {
    private Object content; //the object to be sent back and forth

    /**
     * creating a message object to 
     *
     * @param content the object to be sent back and forth
     * @param DataType the dataType of the object
     */
    public Message(Object content) {
        this.content = content;
    }

    /**
     * Returns the object to be sent back and forth
     * @return the object to be sent back and forth
     */
    public Object getContent() {
        return content;
    }
}

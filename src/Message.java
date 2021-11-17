import java.io.Serializable;

/**
 * @author Nathan Leakeas
 * @version 11.17.21
 */
public class Message implements Serializable {
    public String protocol;
    public Object o;

    public Message(String protocol, Object o) {
        this.protocol = protocol;
        this.o = o;
    }

}

import java.io.Serializable;

/**
 * @author Nathan Leakeas
 * @version 11.17.21
 */
public class Message implements Serializable {
    public enum Protocol { //TODO - add more protocols
        LOGOUT, LOGIN, DELETE, LIST, COURSE, QUIZ, ACCOUNT
    }

    public Protocol protocol;
    public Object[] o;

    public Message(Protocol protocol, Object[] o) {
        this.protocol = protocol;
        this.o = o;
    }

}

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Nathan Leakeas
 * @version 11.17.21
 */
public class ServerThread implements Runnable {
    private Socket sock;

    public ServerThread(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        
    }

}

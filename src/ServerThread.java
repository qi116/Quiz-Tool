import java.io.*;
import java.net.Socket;

/**
 * @author Nathan Leakeas
 * @version 11.17.21
 */
public class ServerThread implements Runnable {
    private final Socket sock;
    private Account acct;

    public ServerThread(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        try {
            OutputStream sOut = sock.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(sOut);
            InputStream sIn = sock.getInputStream();
            ObjectInputStream in = new ObjectInputStream(sIn);

            boolean cont = true;

            while (cont) {
                Object rec = in.readObject();
                Message msg = (Message) rec;
                switch (msg.request) {
                    case LOGIN:
                        break;
                    case LOGOUT:
                        break;
                    case ADD:
                        break;
                    case GET:
                        break;
                    case MODIFY:
                        break;
                    case REMOVE:
                        break;
                }

                
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

    }

}

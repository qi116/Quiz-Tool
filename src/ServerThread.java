import java.io.*;
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
        try {
            OutputStream sOut = sock.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(sOut);
            InputStream sIn = sock.getInputStream();
            ObjectInputStream in = new ObjectInputStream(sIn);

            boolean cont = true;

            while (cont) {
                Message rec = (Message) in.readObject();
                //logic here
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

    }

}

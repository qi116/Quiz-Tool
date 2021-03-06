import javax.print.attribute.standard.NumberUp;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Nathan Leakeas
 * @version 11.17.21
 */
public class ServerThread implements Runnable {
    private final Socket sock;
    private final ServerDataHandler handler;
    ArrayList<ObjectOutputStream> updateConnections;
    ServerRefreshThread refreshThread;

    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ServerThread(Socket sock, ArrayList<ObjectOutputStream> updateConnections,
                        ServerRefreshThread refreshThread) {
        this.sock = sock;
        this.handler = new ServerDataHandler();
        this.updateConnections = updateConnections;
        this.refreshThread = refreshThread;

        try {
            OutputStream sOut = sock.getOutputStream();
            this.out = new ObjectOutputStream(sOut);
            InputStream sIn = sock.getInputStream();
            this.in = new ObjectInputStream(sIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            boolean cont = true;

            while (cont) {
                Object rec = in.readObject();
                Message msg = (Message) rec;
                if (msg.request == Message.RequestType.UPDATE) { //checks if socket should be an update socket
                    updateConnections.add(out); //adds socket to list of reserved update sockets
                    return;
                }
                Message response = null;
                response = handler.processRequest(msg); //pass message off to message handler
                if (response != null) {
                    synchronized (sock) {
                        out.writeObject(response); //send message handler response to client
                        out.flush();
                    }
                }
                if (this.handler.updateCalled()) { //if incoming message modifies data
                    Thread t = new Thread(this.refreshThread); //start thread responsible for sending out updates
                    t.start();
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException | IOException e) { //close socket if fatal errors occur
            try {
                sock.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

}

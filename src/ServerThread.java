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


    public ServerThread(Socket sock, ArrayList<ObjectOutputStream> updateConnections, ServerRefreshThread refreshThread) {
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

        }
    }

    public void run() {
        try {
            boolean cont = true;

            while (cont) {
                Object rec = in.readObject();
                Message msg = (Message) rec;
                if (msg.request == Message.requestType.UPDATE) {
                    updateConnections.add(out);
                    return;
                }
                Message response = null;
                response = handler.processRequest(msg);
                if (response != null) {
                    synchronized (sock) {
                        out.writeObject(response);
                        out.flush();
                    }
                }
                if (this.handler.updateCalled()) {
                    Thread t = new Thread(this.refreshThread);
                    t.start();
                }
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

    }

}

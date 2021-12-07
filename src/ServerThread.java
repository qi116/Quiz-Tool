import java.io.*;
import java.net.Socket;

/**
 * @author Nathan Leakeas
 * @version 11.17.21
 */
public class ServerThread implements Runnable {
    private final Socket sock;
    private Account acct;
    private ServerDataHandler handler;

    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ServerThread(Socket sock) {
        this.sock = sock;
        handler = new ServerDataHandler();
        
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
                Message response = null;
                if (acct == null && msg.request == Message.requestType.LOGIN) {
                    String[] contents = (String[]) msg.content;

                    //response set here
                } else {
                    response = handler.processRequest(msg);
                }
                if (response != null) {
                    synchronized (sock) {
                        out.writeObject(response);
                        out.flush();
                    }

                }




            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

    }

}

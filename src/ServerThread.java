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


    public ServerThread(Socket sock) {
        this.sock = sock;
        handler = new ServerDataHandler();
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
                Message response = null;
                if (acct == null && msg.request == Message.requestType.LOGIN) {
                    String[] contents = (String[]) msg.content;
                    //load account with method
                    //response set here
                } else {
                    response = handler.processRequest(msg);
                }
                if (response != null) {
                    out.writeObject(response);
                    out.flush();
                }




            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

    }

}

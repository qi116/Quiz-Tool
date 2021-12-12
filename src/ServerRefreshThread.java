import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Nathan Leakeas
 * @version 12/8/2021
 */
public class ServerRefreshThread implements Runnable {

    ArrayList<ObjectOutputStream> connections;

    public ServerRefreshThread(ArrayList<ObjectOutputStream> connections) {
        this.connections = connections;
    }

    public void run() {
        Message updateMessage = new Message(Message.RequestType.UPDATE, null, null);
        for (ObjectOutputStream out : this.connections) { //iterate through all socket output streams
            synchronized (out) {
                try {
                    out.writeObject(updateMessage); //send special update message to current socket
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

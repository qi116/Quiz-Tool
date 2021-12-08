import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerRefreshThread implements Runnable{

    ArrayList<ObjectOutputStream> connections;

    public ServerRefreshThread(ArrayList<ObjectOutputStream> connections) {
        this.connections = connections;
    }

    public void run() {
        Message updateMessage = new Message(Message.requestType.UPDATE, null, null);
        for (ObjectOutputStream out : this.connections) {
            synchronized (out) {
                //send message to refresh here
                try {
                    out.writeObject(updateMessage);
                    out.flush();

                    //send update message from out here
                } catch (Exception e) {

                }
            }

        }
    }
}

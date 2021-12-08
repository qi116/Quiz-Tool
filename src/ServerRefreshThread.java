import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerRefreshThread implements Runnable{

    ArrayList<Socket> connections;

    public ServerRefreshThread(ArrayList<Socket> connections) {
        this.connections = connections;
    }

    public void run() {
        for (Socket s : this.connections) {
            synchronized (s) {
                //send message to refresh here
                try {
                    OutputStream sOut = s.getOutputStream();
                    ObjectOutputStream out = new ObjectOutputStream(sOut);

                    //send update message from out here
                } catch (Exception e) {

                }
            }

        }
    }
}

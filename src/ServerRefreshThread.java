import java.net.Socket;
import java.util.ArrayList;

public class ServerRefreshThread implements Runnable{

    ArrayList<Socket> connections;

    public ServerRefreshThread(ArrayList<Socket> connections) {
        this.connections = connections;
    }

    public void run() {
        for (Socket s : this.connections) {
            //send message to refresh here
        }
    }
}

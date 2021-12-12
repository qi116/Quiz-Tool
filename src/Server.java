import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Nathan Leakeas
 * @version 11.17.21
 */
public class Server {

    public static void main(String[] args) {
        ArrayList<Quiz> lockedQuizzes;
        ArrayList<ObjectOutputStream> updateConnections = new ArrayList<>();
        ServerRefreshThread refreshThread = new ServerRefreshThread(updateConnections);
        System.out.println("Starting server..");
        try (ServerSocket server = new ServerSocket(8080)) {
            while (true) { //accept connection and create new thread to handle it
                Socket sock = server.accept();
                ServerThread serverThread = new ServerThread(sock, updateConnections, refreshThread);
                Thread t = new Thread(serverThread);
                t.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

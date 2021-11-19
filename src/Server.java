import java.io.IOException;
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
        try (ServerSocket server = new ServerSocket(8080)) {
            while (true) {
                Socket sock = server.accept();
                ServerThread serverThread = new ServerThread(sock);
                Thread t = new Thread(serverThread);
                t.start();

            }
        } catch (IOException e) {

        }
    }
}

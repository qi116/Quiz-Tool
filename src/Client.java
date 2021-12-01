import javax.swing.*;
import java.io.*;
import java.net.*;
public class Client {
    private static String host;
    private static int port;
    private static Socket socket;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;
    static {
        host = "localhost";
        port = 8080;
        socket = new Socket();
    }
    public static void main(String[] args) throws IOException {

        boolean fail = false;
        reader = new ObjectInputStream(socket.getInputStream());
        writer = new ObjectOutputStream(socket.getOutputStream());
        try {
            socket = new Socket(host, port);
            if (!socket.isConnected()) fail = true;

        } catch (UnknownHostException e) {
            fail = true;
        } catch (ConnectException e) {
            fail = true;
        }
    }
    // Wait for Response object that will give array that gives whether it worked and what type of user.
    public String[] login(String user, String pass) throws Exception {
        //Message message = new Message(new String[]{user, pass}, DataType.STRINGL);
        //writer.writeObject(message);
        writer.flush();

        Object o = reader.readObject(); //This will sit there till something is received.
        return null;
    }


}

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Update implements Runnable{
    private static final String host;
    private static final int port;
    private static Socket socket;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;

    static {
        host = "localhost";
        port = 8080;
        socket = new Socket();
    }
    public void run () {
        Socket updateSocket = new Socket();
        try {
            updateSocket = new Socket(host, port);
            if (!updateSocket.isConnected()) throw new IOException();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream updateReader = null;
        ObjectOutputStream updateWriter = null;
        try {
            updateReader = new ObjectInputStream(updateSocket.getInputStream());
            updateWriter = new ObjectOutputStream(updateSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            updateWriter.writeObject(new Message(Message.requestType.UPDATE, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Object o = updateReader.readObject();
                System.out.println("here");
                //call Peter's update method;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
            }
    }



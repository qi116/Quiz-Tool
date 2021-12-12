import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client-side update thread
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Brian Qi
 * @version December 8, 2021
 */

public class Update implements Runnable{
    private static final String host;
    private static final int port;
    private static Socket updateSocket;
    private static ObjectInputStream updateReader;
    private static ObjectOutputStream updateWriter;
    private static boolean run = true;
    static {
        host = "localhost";
        port = 8080;
    }
    public Update() {
        try {
            updateSocket = new Socket(host, port);
            if (!updateSocket.isConnected()) throw new IOException();

            updateReader = new ObjectInputStream(updateSocket.getInputStream());
            updateWriter = new ObjectOutputStream(updateSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Run method for Update thread
     */
    public void run () {
        try {

            updateWriter.writeObject(new Message(Message.requestType.UPDATE, null, null));
            while (run) {
                try {
                    Object o = updateReader.readObject();
                    System.out.println("here");
                    MainGUI.update();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }







        }
        public static void stop() {
            run = false;

            try {
                updateReader.close();
                updateWriter.close();
                updateSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



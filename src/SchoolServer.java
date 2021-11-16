import java.io.*;
import java.net.*;
public class SchoolServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(2222);

        while (true) {
            socket = serverSocket.accept()
            System.out.println("COnnection established at " + socket);


        }
    }
}

private class ServerHandler extends Thread {
    Socket socket;
    
    OutputStream output;
    ObjectOutputStream objectOutput;
    
    InputStream input;
    ObjectInputStream objectInput;

    Response response;

    ServerHandler (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            outputStream = socket.getOutputStream();
            objectOutput = new ObjectOutputStream(outputStream);

            inputStream = socket.getInputStream();
            inputOutput = new ObjectInputStream(inputStream);

            while (true) {
                getRequest();

                sendResponse();

                checkUpdate();
            }
        }
    }

    public void getRequest() {
        //stuff and things
    }

    public void sendResponse() {
        //stuff and things
    }
}

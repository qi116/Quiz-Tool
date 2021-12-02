import javax.swing.*;
import java.io.*;
import java.net.*;
/**
 * Client-side program
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Brian Qi
 * @version December 2, 2021
 */
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

        try {
            socket = new Socket(host, port);
            if (!socket.isConnected()) fail = true;

        } catch (UnknownHostException e) {
            fail = true;
        } catch (ConnectException e) {
            fail = true;
        }
        reader = new ObjectInputStream(socket.getInputStream());
        writer = new ObjectOutputStream(socket.getOutputStream());
    }
    // Wait for Response object that will give array that gives whether it worked and what type of user.

    /**
     * Takes in user name and password and sends to Server to log in
     * Waits for String[] response
     *
     * @param user username
     * @param pass password
     * @return String[] showing whether login was success and what type of user
     */
    public String[] login(String user, String pass) {
        Message message = new Message(Message.requestType.LOGIN, Message.dataType.ACCOUNT, new String[]{user, pass});
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return null;
        }
         //This will sit there till something is received.
        Message msg = (Message) o;
        String[] information = (String[]) msg.content;
        return information; //handle msg and cast object inside to what is given.
    }

    /**
     *  Accepts parameters to create account and sends to Server to make account.
     *  Waits for response.
     * @param user username
     * @param pass password
     * @param isTeacher boolean indicating whether user is teacher or student
     * @return
     */
    public boolean createAccount(String user, String pass, boolean isTeacher){
        Message message = new Message(Message.requestType.LOGIN, Message.dataType.ACCOUNT,
                new String[]{user, pass, String.valueOf(isTeacher)});
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return false;
        }
        Message msg = (Message) o;
        Boolean b = (Boolean) msg.content;
        return b;  //make sure to change this to when message is received
    }

    /**
     * Asks server for list of course names
     * @return String[] of course names
     */
    public String[] getCourses() {
        Message message = new Message(Message.requestType.GET, Message.dataType.COURSELIST,
                null);
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return null;
        }
        Message msg = (Message) o;
        String[] courses = (String[]) msg.content;
        return courses;
    }

    /**
     * Sends course name to Server to be added
     * @param name name of course to be added
     * @return boolean indicating if operation was successful
     */
    public boolean addCourse(String name) {
        Message message = new Message(Message.requestType.ADD, Message.dataType.COURSE, name);
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return false;
        }
        Message msg = (Message) o;
        Boolean b = (Boolean) msg.content;
        return b; //make sure to change this to when message is received
    }
    /**
     * Sends course name to Server to be removed
     * @param name name of course to be removed
     * @return boolean indicating if operation was successful
     */
    public boolean removeCourse(String name) {
        Message message = new Message(Message.requestType.REMOVE, Message.dataType.COURSE, name);
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return false;
        }
        Message msg = (Message) o;
        Boolean b = (Boolean) msg.content;
        return b;
    }
    /**
     * Sends course name to Server to receive list of Quiz names
     * @param course name of course
     * @return list of Quiz names in given course
     */
    public String[] getQuizzes(String course) {
        Message message = new Message(Message.requestType.GET, Message.dataType.QUIZLIST, course);
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return null;
        }
        Message msg = (Message) o;
        String[] quizzes = (String[]) msg.content;
        return quizzes;
    }

    /**
     * Requests Quiz from server using Quiz name.
     * Waits for response
     * @param name Quiz name
     * @return Quiz
     */
    public Quiz getQuiz(String name) {
        Message message = new Message(Message.requestType.GET, Message.dataType.QUIZ, name);
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return null;
        }
        Message msg = (Message) o;
        Quiz quiz = (Quiz) msg.content;
        return quiz;
    }

    /**
     * Submits quiz to server
     * @param quiz Quiz to be submitted
     * @return boolean indicating whether or not submission worked
     */
    public boolean submitQuiz(Quiz quiz) {
        Message message = new Message(Message.requestType.ADD, Message.dataType.SUBMISSION, quiz);
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return false;
        }
        Message msg = (Message) o;
        Boolean b = (Boolean) msg.content;
        return b;
    }

    /**
     * Sends quiz to replace previous quiz with same name to Server
     * @param quiz New Quiz
     * @return boolean indicating whether operation was successful.
     */
    public boolean modifyQuiz(Quiz quiz) {
        Message message = new Message(Message.requestType.MODIFY, Message.dataType.QUIZ, quiz);
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return false;
        }
        Message msg = (Message) o;
        Boolean b = (Boolean) msg.content;
        return b;
    }

    /**
     * Sends course name and quiz to server to add
     * @param course course name
     * @param quiz quiz to add
     * @return boolean indicating whether quiz was added
     */
    public boolean addQuiz(String course, Quiz quiz) {
        Message message = new Message(Message.requestType.ADD,
                Message.dataType.QUIZ, new Object[]{course, quiz});
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return false;
        }
        Message msg = (Message) o;
        Boolean b = (Boolean) msg.content;
        return b;
    }
    /**
     * Sends course name and quiz name to server to add
     * Sends only quiz name and indicates wanting to add a new empty quiz
     * @param course course name
     * @param quizName quizName of new quiz
     * @return boolean indicating whether quiz was added
     */
    public boolean addQuiz(String course, String quizName) {
        Message message = new Message(Message.requestType.ADD,
                Message.dataType.QUIZ, new String[]{course, quizName});
        Object o;
        try {
            writer.writeObject(message);
            writer.flush();
            o = reader.readObject();
        } catch (Exception e) {
            return false;
        }
        Message msg = (Message) o;
        Boolean b = (Boolean) msg.content;
        return b;
    }


}

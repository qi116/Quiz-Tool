import java.io.IOException;

public class ClientServerTest {
    public static void main(String[] args) throws IOException {
        Client c = new Client();
        System.out.println(c.login("user", "pass"));

        System.out.println(c.createAccount("user", "pass", true));
        System.out.println(c.login("user", "pass"));

    }
}

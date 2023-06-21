/**
 * <h1>Main</h1>
 * Starting the Server.
 *
 * @author Negar
 * @since 2020-08-06
 * @version 0.0
 */
public class Main {

    /**
     * @param args args Unused.
     */
    public static void main(String[] args) {
        Server server = new Server(5000);
        server.runServer();
    }
}

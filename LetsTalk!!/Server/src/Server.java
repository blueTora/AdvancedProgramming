import java.io.*;
import java.net.*;

/**
 * <h1>Server</h1>
 * Starting the Server.
 *
 * @author Negar
 * @since 2020-08-06
 * @version 0.0
 */
public class Server {

    private int port;
    private ServerSocket server;
    private Socket socket;

    /**
     * Creating the Server.
     * @param portNum port Number
     */
    public Server (int portNum){
        port = portNum;
    }

    /**
     * running the Server.
     * Accepting clients and creating the threads.
     */
    public void runServer(){
        try {
            server = new ServerSocket(port);

            while (true){

                try {
                    System.out.println("Server Started.\nWaiting to connect ...");
                    socket = server.accept();
                    System.out.println("Connected to Client " + socket);

                    Thread t = new Thread(new Handler(socket), "Handler Thread");
                    t.start();

                } catch (Exception e) {
                    closeConnection();
                    e.printStackTrace();
                    return;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
            closeConnection();
            return;
        }
    }

    /**
     * Closing the server.
     */
    private void closeConnection(){

        try {
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Closing Server.");
    }
}

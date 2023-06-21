import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * <h1>Client</h1>
 * Starting the Client.
 *
 * @author Negar
 * @since 2020-08-06
 * @version 0.0
 */
public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    /**
     * Creating the socket.
     * @param address IP address
     * @param port port Number
     */
    public Client(String address, int port){

        try {
            socket = new Socket(address, port);
            System.out.println("Connected to server.");
            getStreams();
            System.out.println("Connection Closed.");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * receiving and sending data.
     */
    private void getStreams(){

        Scanner scan = new Scanner(System.in);
        String input;

        try {
            in = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            while (true){

                input = scan.nextLine();
                out.writeUTF(input);

                if (input.strip().equals("over"))
                    break;

                System.out.println("+ From Server : " + in.readUTF());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

import java.io.*;
import java.net.*;

/**
 * <h1>Handler</h1>
 * implements Runnable.
 * handling the multiple client threads.
 *
 * @author Negar
 * @since 2020-08-06
 * @version 0.0
 */
class Handler implements Runnable {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private StringBuffer text;

    /**
     * Creating the thread.
     * @param socket the created socket
     */
    Handler(Socket socket) {
        this.socket = socket;
    }

    /**
     * over writing the run method from runnable.
     * receiving and sending data with client.
     */
    public void run() {
        text = new StringBuffer();

        try {
            in = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            while (true){
                String input = in.readUTF();
                text.append(input);
                text.append(" ");

                System.out.println("- From Client " + socket + " : " + input);

                if (input.strip().equals("over"))
                    break;

                out.writeUTF(text.toString());
                System.out.println("+ From Server : " + text.toString());
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
            System.out.println("Closing Connection for Client " + socket);
        }
    }
}
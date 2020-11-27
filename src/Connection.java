import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Connection {
    private Socket socket = null;
    private int port = 60000;

    private DataInputStream input;
    private DataOutputStream output;

    public void createServer() throws SocketAlreadyCreated, IOException {
        if (socket != null) {
            throw new SocketAlreadyCreated();
        }
        ServerSocket server = new ServerSocket(port);

        socket = server.accept();

        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

    }

    public void createClient() throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        socket = new Socket(address, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public class SocketAlreadyCreated extends Exception {}

}

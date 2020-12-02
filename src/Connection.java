import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Scanner;

public class Connection {
    private Socket socket = null;
    private int port = 60000;

    private BufferedReader input;
    private BufferedWriter output;

    public void createServer() throws SocketAlreadyCreated, IOException {
        if (socket != null) {
            throw new SocketAlreadyCreated();
        }
        ServerSocket server = new ServerSocket(port);

        socket = server.accept();

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    public void createClient() throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        socket = new Socket(address, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public Message getMessage() throws IOException {

        String str = input.readLine();
        return Message.valueOf(str);
    }

    public Coordinate getCoordinate() throws IOException, ParseException {
        String str = input.readLine();

        return new Coordinate(str);
    }

    public void sendMessage(Message message) throws IOException {
        output.write(String.valueOf(message));
        output.write('\n');
        output.flush();
    }

    public void close() throws IOException {
        output.close();
        input.close();
    }

    public void sendCoordinate(Coordinate coordinate) throws IOException {
        sendMessage(Message.SHOT);
        output.write(coordinate.toString());
        output.write('\n');
        output.flush();
    }

    public class SocketAlreadyCreated extends Exception {}

    enum Message {
        HIT,
        MISS,
        KILL,
        WIN,
        LOSE,
        SHOT
    }

}

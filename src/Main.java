import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4000);
        System.out.println("Server Ready on port 4000");
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());
        while(true)
        {
            Socket client;
            client = serverSocket.accept();
            System.out.println("Client Connected, Authentication Remain");
            ClientHandler clientHandler = new ClientHandler(client);

            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
}
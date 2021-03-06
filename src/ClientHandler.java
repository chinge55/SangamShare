import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable
{
    private Socket client;
    Scanner scanner = new Scanner(System.in);
    private Object Exception;

    public ClientHandler(Socket client)
    {
        this.client = client;
    }

    @Override
    public void run()
    {

        try {

            final DataInputStream datainputstream = new DataInputStream(client.getInputStream());
            final DataOutputStream dataoutputstream = new DataOutputStream(client.getOutputStream());
            dataoutputstream.writeUTF("Enter Your Username and password");
            String username = datainputstream.readUTF();
            String password = datainputstream.readUTF();
            DatabaseConnector databaseConnector = new DatabaseConnector(username, password);
            if(databaseConnector.Connect())
            {
                ClientFrameInstance clientFrameInstance = new ClientFrameInstance(datainputstream, dataoutputstream);

            }
            else
            {
                System.out.println("Not done");
                dataoutputstream.writeUTF("Ask your administrator for username and password");
                Thread.currentThread().interrupt();
                return;

            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                client.close();
                System.out.println("Client Closed");
                Thread.currentThread().interrupt();
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Thread killed");
                Thread.currentThread().interrupt();
                return;
            }
        }

    }
}

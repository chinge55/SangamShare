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
                System.out.println("Done!");
                dataoutputstream.writeUTF("What do you wanna do?");
                dataoutputstream.writeUTF("1. Chat \n 2. Send files\n");
                int numbcheck;
                numbcheck = Integer.valueOf(datainputstream.readUTF());
                System.out.println(databaseConnector.getUsername()+ "Has sent"+numbcheck);

            }
            else
            {
                System.out.println("Not done");
            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                client.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}

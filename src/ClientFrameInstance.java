import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientFrameInstance{
    static JTextField textfield1, textfield2;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    public void chatSelected()
    {
        JFrame f = new JFrame(DatabaseConnector.username);
        f.setSize(390, 300);
        f.setLocation(100, 150);
        //make sure it quits when x is clicked
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set look and feel
        f.setDefaultLookAndFeelDecorated(true);
        JLabel labelM = new JLabel("Message to Send to"+DatabaseConnector.username);
        labelM.setBounds(50, 50, 200, 30);
        JTextField motto1 = new JTextField();
        //set size of the text box
        motto1.setBounds(50, 100, 200, 30);
        JTextField motto2 = new JTextField();
        motto2.setBounds(50, 70, 200, 30);
        //add elements to the frame
        JButton jButton = new JButton("Send TEXT");
        jButton.setBounds(50, 150, 100, 30);
        f.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeUTF(motto1.getText());
                    motto1.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("error in sending text");
                }

            }
        });
        f.add(labelM);
        f.add(motto1);
        f.add(motto2);
        f.setLayout(null);
        f.setVisible(true);
        while(true)
        {
            String inputString = null;
            try {
                inputString = this.dataInputStream.readUTF();
            } catch (IOException e) {
               // e.printStackTrace();
                System.out.println("Client Disconnected");
            }
            if(inputString.equals("logout"))
                break;
            else
                System.out.println(DatabaseConnector.username+ ":"+inputString);
                motto2.setText(inputString);

        }
    }
    public void fileTransferSelected()
    {

    }
    public ClientFrameInstance(DataInputStream dataInputStream, DataOutputStream dataOutputStream)
    {
        try {
            dataOutputStream.writeUTF("What do you wanna do?");
            dataOutputStream.writeUTF("1. Chat \n 2. Send files\n");
            int numbcheck;
            numbcheck = Integer.valueOf(dataInputStream.readUTF());
            this.dataInputStream = dataInputStream;
            this.dataOutputStream = dataOutputStream;
            switch (numbcheck)
            {
                case 1:
                    chatSelected();
                    break;
                case 2:
                    break;
                default:


            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Client Disconnected");
            Thread.currentThread().interrupt();
            return;
        }

    }
}





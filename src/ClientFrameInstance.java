import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientFrameInstance{
    static JTextField textfield1, textfield2;

    public ClientFrameInstance(DataInputStream dataInputStream, DataOutputStream dataOutputStream)
    {
        JFrame f = new JFrame("Text Field Examples");
        f.getContentPane().setLayout(new FlowLayout());
        textfield1 = new JTextField("Sent Message",10);
        textfield2 = new JTextField("Received Message",10);
        //textfield3 = new JTextField("Text field 3",10);
        f.getContentPane().add(textfield1);
        f.getContentPane().add(textfield2);
        try {
            dataOutputStream.writeUTF("What do you wanna do?");
            dataOutputStream.writeUTF("1. Chat \n 2. Send files\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //f.getContentPane().add(textfield3);

        f.pack();
        f.setVisible(true);
    }
}





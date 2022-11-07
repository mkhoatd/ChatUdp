package UdpClient;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Runner extends Thread{
    private final JTextArea textArea;
    private final DatagramSocket socket;

    public Runner(JTextArea chatTextArea, DatagramSocket socket) {
        this.textArea=chatTextArea;
        this.socket=socket;
    }

    public void run() {
        var receiveData=new byte[1024];
        var receivePacket=new DatagramPacket(receiveData, receiveData.length);
        while (true) {
            try {
                this.socket.receive(receivePacket);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            var message=new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
            this.textArea.setText(message+"\n"+this.textArea.getText());
        }
    }
}

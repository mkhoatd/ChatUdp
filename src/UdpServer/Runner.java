package UdpServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class Runner extends Thread{
    private final String Message;
    private final List<User> Users;

    public Runner(String message, List<User> users) {
        this.Message=message;
        this.Users=users;
    }

    public void run() {
        try {
            var socket=new DatagramSocket();
            for(var user:this.Users) {
                var sendData=this.Message.getBytes();
                var sendPacket=new DatagramPacket(sendData, sendData.length, user.IpAddress, user.port);
                socket.send(sendPacket);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

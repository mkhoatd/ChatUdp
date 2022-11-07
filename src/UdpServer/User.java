package UdpServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class User {
    public final String Username;
    public final InetAddress IpAddress;
    public final int port;

    public User(String username, InetAddress ipAddress, int port){
        this.Username=username;
        this.IpAddress=ipAddress;
        this.port=port;
    }

}

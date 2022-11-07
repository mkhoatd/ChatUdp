package UdpServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int Port;
    private final List<User> Users;
    private DatagramSocket Socket;

    public Server(int port) {
        this.Port =port;
        Users =new ArrayList<>();
    }

    public void Start() throws Exception {
        this.Socket =new DatagramSocket(Port);
        System.out.println("Server port: "+this.Socket.getLocalPort());
        var receiveData=new byte[1024];
        var receivePacket=new DatagramPacket(receiveData, receiveData.length);
        while (true){
            this.Socket.receive(receivePacket);
            var request=new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
            if (request.startsWith("/rg")){
                var username=request.substring(3);
                var ipAddress=receivePacket.getAddress();
                var port=receivePacket.getPort();
                var user=new User(username, ipAddress, port);
                var message="success";
                this.Users.add(user);
                var sendData=message.getBytes();
                var sendPacket=new DatagramPacket(sendData, sendData.length, ipAddress, port);
                this.Socket.send(sendPacket);
            }
            else {
                new Runner(request, Users).start();
            }
        }
    }

}

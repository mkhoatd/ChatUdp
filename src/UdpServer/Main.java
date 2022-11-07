package UdpServer;

public class Main {
    public static void main(String[] args) throws Exception{
        var server=new Server(9876);
        server.Start();
    }
}
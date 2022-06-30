import java.io.IOException;
import java.net.ServerSocket;


public class GreetServer implements Runnable{
    private ServerSocket serverSocket;



    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        for(int i = 0; i < 2; i++){
            new EchoClientHandler(serverSocket.accept()).start();
        }

    }

    public static void main(String[] args) throws IOException {
        GreetServer server=new GreetServer();
        server.start(6666);
    }

    @Override
    public void run() {
        GreetServer server = new GreetServer();
        try {
            server.start(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
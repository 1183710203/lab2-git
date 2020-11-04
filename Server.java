package increment4;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while(true){
            Socket client = server.accept();
            new socketThread(client).start();
        }
    }
}

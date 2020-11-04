package increment4;
import increment.Core_Func;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
public class Client {
    public static String  getHomework() throws IOException {
        Socket client = new Socket("192.168.152.1", 9999);
        OutputStream ops = client.getOutputStream();
        String problems = "";
        ops.write(("Student\n").getBytes());
        InputStream is = client.getInputStream();
        byte[] homework = new byte[1024];
        int len = is.read(homework);
        client.close();
        return new String(homework, 8 ,len);
    }
    public  static void  sendHomework(String problems) throws IOException {
        Socket client = new Socket("192.168.152.1", 9999);
        OutputStream ops = client.getOutputStream();
        ops.write(("Teacher\n" + problems).getBytes());
        client.close();
    }

    public static void main(String[] args) throws IOException {
        Client.sendHomework("86980");
    }
}

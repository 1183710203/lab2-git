package increment4;

import java.io.*;
import java.net.Socket;
public  class socketThread extends Thread{
    Socket client;
    public socketThread(Socket client){
        this.client = client;
    }
    @Override
    public void run() {
        try{
            InputStream is = client.getInputStream();
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            String problems = new String(bytes, 0, len);
            String status = problems.split("\n")[0];
            File file = new File("problem.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            if("Teacher".equals(status)){
                FileWriter fw = new FileWriter(file, false);
                fw.write(problems);
                fw.close();
            }
            else
            {
                BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
                String homeWork = "";
                String line = "";
                while((line = bf.readLine()) != null){
                    homeWork += line + "\n";
                }
                OutputStream ops = client.getOutputStream();
                ops.write(homeWork.getBytes());
            }
            client.close();
        }catch (Exception e) {

        }
    }
}

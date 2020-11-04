package increment4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class GUIFunc extends JFrame{
    public  void windowClosing() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public  void setBackground(JFrame f, ImageIcon background) {
        JLabel label = new JLabel(background);        //把背景图片添加到标签里
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        JPanel myPanel = (JPanel) f.getContentPane();        //把我的面板设置为内容面板
        myPanel.setOpaque(false);                    //把我的面板设置为不可视
        myPanel.setLayout(new FlowLayout());
        f.getLayeredPane().setLayout(null);        //把分层面板的布局置空
        f.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
    }
    public ArrayList<User> readObject() throws IOException, ClassNotFoundException {
        ArrayList<User> lst = new ArrayList<>();
        File file = new File("increment4\\UserInfor.txt");
        if(file.length() != 0){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("increment4\\UserInfor.txt"));
            Object o = ois.readObject();
            ois.close();
            lst = (ArrayList<User>) o;
        }
        return lst;
    }
    public void writeObject(ArrayList<User> lst) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("increment4\\UserInfor.txt"));
        oos.writeObject(lst);
        oos.close();
    }
    public String check(boolean teacher, boolean student, String name, String pwd, String ensurePwd){
        String flag = "";
        if(!student  && !teacher ){
            flag =  "选择身份";
        }
        else if ("".equals(name)) {
            flag = "输入用户名";
        }
        else if("".equals(pwd)){
            flag = "输入密码";
        }
        else if("".equals(ensurePwd)){
            flag = "确认密码";
        }
        else if (!pwd.equals(ensurePwd)) {
            flag = "密码不一致";
        }
        return flag;
    }
    public String exist(String pwd, User user, ArrayList<User> lst){
        String flag = "";
        if(!lst.contains(user)) {
            flag = "用户不存在";
        }
        else if(!lst.get(lst.indexOf(user)).getPwd().equals(pwd)){
            flag = "密码错误";
        }
        return flag;
    }
}

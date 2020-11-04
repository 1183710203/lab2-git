package increment4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static increment3.statics.Data.backgroundURL;

public class Login extends JFrame {
    private JLabel nameLabel, pwdLabel;
    private TextField name, pwd;
    private JTextArea infor;
    private JButton register, login;
    private JPanel panel1, panel2, panel;
    private ButtonGroup group;
    private JRadioButton teacher, student;
    private ImageIcon background = new ImageIcon(backgroundURL);
    private GUIFunc func;
    private  String status;
    public void init(){
        setTitle("登录");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        func= new GUIFunc();
        func.setBackground(this, background);
        group = new ButtonGroup();
        teacher = new JRadioButton("教师");
        student = new JRadioButton("学生");
        group.add(teacher);
        group.add(student);
        nameLabel = new JLabel("用户名");
        pwdLabel = new JLabel("密码");
        name = new TextField(10);
        pwd = new TextField(20);
        pwd.setEchoChar('*');
        infor = new JTextArea();
        infor.setBorder(null);
        register = new JButton("注册");
        //制定action命令,传入事件响应
        register.addActionListener(new buttonActionListener()); // 可以实现多个注册监听
        register.setActionCommand("注册");
        login = new JButton("登录");
        //制定action命令,传入事件响应
        login.addActionListener(new buttonActionListener()); // 可以实现多个注册监听
        login.setActionCommand("登录");
        panel = new JPanel();
        panel1 = new JPanel(new GridLayout(5,1));
        panel2 = new JPanel();
        panel.add(teacher);
        panel.add(student);
        panel1.add(nameLabel);
        panel1.add(name);
        panel1.add(pwdLabel);
        panel1.add(pwd);
        panel1.add(infor);
        panel2.add(register);
        panel2.add(login);
        add(panel, BorderLayout.NORTH);
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        setBounds(500, 100, 250, 250);
        setVisible(true);
        setLayout(null);
        func.windowClosing();
    }
    public Login() {
        init();
    }
    class buttonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("注册")){
                dispose();
                new Register();
            }
            if(e.getActionCommand().equals("登录")){
                String flag = func.check(teacher.isSelected(), student.isSelected(),
                        name.getText(), pwd.getText(), pwd.getText());
                infor.setText(flag);
                if("".equals(flag)) {
                    try{
                        if(teacher.isSelected()) status = "Teacher";
                        else status = "Student";
                        User new_user = new User(status,name.getText(), pwd.getText());
                        ArrayList<User> lst = func.readObject();
                        String flag2 = func.exist(pwd.getText(), new_user, lst);
                        infor.setText(flag2);
                        if(flag2.equals("")){
                            dispose();
                            if("Student".equals(status))
                                new MyCaculator().loadFrame(name.getText(), pwd.getText());
                            else
                                new Teacher_Gui(name.getText(), pwd.getText());
                        }
                    }catch (Exception e1){

                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}











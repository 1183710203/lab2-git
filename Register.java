package increment4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static increment3.statics.Data.backgroundURL;


public class Register extends JFrame {
    private JLabel nameLabel, pwdLabel, ensurePwdLabel;
    private TextField name, pwd, ensurePwd;
    private JTextArea infor;
    private JButton back, register;
    private JPanel panel,panel1, panel2;
    private ButtonGroup group;
    private JRadioButton teacher, student;
    private ImageIcon background = new ImageIcon(backgroundURL);
    private GUIFunc func;
    public Register() {
        setTitle("注册");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        func = new GUIFunc();
        panel = new JPanel();
        panel1 = new JPanel(new GridLayout(7, 1));
        panel2 = new JPanel(new GridLayout(1, 2));
        group = new ButtonGroup();
        teacher = new JRadioButton("教师");
        student = new JRadioButton("学生");
        group.add(teacher);
        group.add(student);
        nameLabel = new JLabel("用户名");
        pwdLabel = new JLabel("密码");
        ensurePwdLabel = new JLabel("确认密码");
        name = new TextField(10);
        pwd = new TextField(20);
        pwd.setEchoChar('*');
        ensurePwd = new TextField(20);
        ensurePwd = new TextField(20);
        ensurePwd.setEchoChar('*');
        infor = new JTextArea();
        infor.setBorder(null);
        register = new JButton("注册");

        //制定action命令,传入事件响应
        register.addActionListener(new  buttonActionListener()); // 可以实现多个注册监听
        register.setActionCommand("注册");
        back = new JButton("退出");

        //制定action命令,传入事件响应
        back.addActionListener(new buttonActionListener()); // 可以实现多个注册监听
        back.setActionCommand("退出");
        panel.add(teacher);
        panel.add(student);
        panel1.add(nameLabel);
        panel1.add(name);
        panel1.add(pwdLabel);
        panel1.add(pwd);
        panel1.add(ensurePwdLabel);
        panel1.add(ensurePwd);
        panel1.add(infor);
        panel2.add(back);
        panel2.add(register);
        add(panel);
        add(panel1, BorderLayout.CENTER);
        add(panel2);
        func.setBackground(this, background);
        setBounds(500, 100, 250, 300);
        setVisible(true);
        setLayout(null);
        func.windowClosing();
    }
    class buttonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)  {
            if (e.getActionCommand().equals("注册")) {
                String flag = func.check(teacher.isSelected(), student.isSelected(),
                        name.getText(), pwd.getText(), ensurePwd.getText());
                infor.setText(flag);
                if("".equals(flag)) {
                       try{
                           String status = "";
                           if(teacher.isSelected()) status = "Teacher";
                           else status = "Student";
                           User new_user = new User(status,name.getText(), pwd.getText());
                           ArrayList<User> lst = func.readObject();
                           String flag2 = func.exist(new_user.getPwd(), new_user, lst);
                           if("用户不存在".equals(flag2)){
                               lst.add(new_user);
                               func.writeObject(lst);
                               dispose();
                               new Login();
                           }
                           else
                           infor.setText("用户已存在");
                       }catch (Exception e1){
                       }
                }
            }
           else if (e.getActionCommand().equals("退出")){
                dispose();
                new Login();
            }
        }
    }
    public static void main(String[] args) {
        new Register();
    }
}

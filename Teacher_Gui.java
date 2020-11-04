package increment4;
import increment3.statics.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class Teacher_Gui extends JFrame {
    private JLabel nameLabel;
    private JButton homework, sendHomework, back;
    private JPanel jp;
    JList jl;
    JScrollPane jsp;
    String problems = new String();
    Core_Func coreFunc = new Core_Func();
    private GUIFunc func = new GUIFunc();
    public Teacher_Gui(String name, String pwd){
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        func.setBackground(this, Data.background);
        homework = new JButton("作业");
        homework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                problems = coreFunc.getProblems(10);
                jl.setListData(problems.split("\n"));
            }
        });
        sendHomework = new JButton("发送");
        sendHomework.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Client().sendHomework(problems);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }));
        jp = new JPanel();
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jl=new JList();
        jl.setFont(new Font("宋体", Font.PLAIN, 15));
        jl.setCellRenderer(renderer);
        jl.setVisibleRowCount(4);        //列表框只显示4个信息（必须要有滚动窗格连用才生效）
        jsp = new JScrollPane(jl);
        jp.add(jsp);
        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        back = new JButton("退出");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        add(nameLabel);
        add(homework);
        add(sendHomework);
        add(back);
        add(jp);
        setVisible(true);
        setBounds(500,100,300,180);
    }
    public static void main(String[] args) {
        new Teacher_Gui("张国庆","1");
    }
}

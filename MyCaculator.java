package increment4;
import increment.Core_Func;
import increment3.ShowHistory;
import increment3.statics.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static increment3.statics.Data.RightLabel;
public class MyCaculator extends JFrame {
    private JTextArea timeText;
    private TextField  myAnswer, allNum, num1,sym, num2,rightNum;
    private JButton button2, next, homework, history, timePattarn, normalPattarn;
    private int count_All;
    private int count_Right;
    JLabel label1, label2, headlabel, backgroundLabel, nameLabel, correctanswer;
    private GUIFunc func;
    private Timer time;
    private boolean flag = true;
    Core_Func core_func = new Core_Func();
    public void loadFrame(String name, String pwd) {
        this.setLayout(null);
        setTitle("小学生加减法训练");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        func = new GUIFunc();
        backgroundLabel= new JLabel();
        backgroundLabel.setIcon(Data.background);
        backgroundLabel.setBounds(0,30,275,250);
        num1 = new TextField();
        num1.setEditable(false);
        num1.setBounds(70, 10, 20, 20);
        sym = new TextField();
        sym.setEditable(false);
        sym.setFont(new Font("仿宋", Font.BOLD, 15));
        sym.setBounds(100, 10, 20, 20);
        num2 = new TextField();
        num2.setEditable(false);
        num2.setBounds(120, 10, 20, 20);
        label1 = new JLabel("=");
        label1.setBounds(140, 10, 20, 20);
        myAnswer = new TextField();
        myAnswer.addActionListener(new TextActionListener());
        myAnswer.setBounds(160, 10, 20, 20);
        label2 = new JLabel();
        label2.setBounds(190, 10, 24, 20);
        label2.setIcon(Data.NormalLabel);
        correctanswer = new JLabel();
        correctanswer.setBounds(220, 10, 100, 20);

        //一个按钮用于判断答案是否相等
        allNum = new TextField("All:" + 0);
        allNum.setEditable(false);
        allNum.setBounds(50, 300, 40, 20);
        next = new JButton("Next");
        next.setBounds(100, 300, 60, 20);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag) {
                    init();
                }
            }
        });
        rightNum = new TextField("Right:" + 0);
        rightNum.setEditable(false);
        rightNum.setBounds(170, 300, 50, 20);
        headlabel = new JLabel();
        headlabel.setIcon(Data.HeadLabel);
        headlabel.setBounds(280, 30, 70, 80);
        nameLabel = new JLabel(name);
        nameLabel.setBounds(285, 100, 40, 40);
//        task = new JButton("每日任务");
        history = new JButton("历史");
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> lst = null;
                try {
                    lst = func.readObject();
                    int index = lst.indexOf(new User("Student",name, pwd));
                    System.out.print(index);
                    new ShowHistory(name, pwd, lst.get(index).getHistory());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        history.setBounds(280, 200, 60, 25);
        homework = new JButton("作业");
        homework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DoHomework(Client.getHomework().split("\n"), name, pwd);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        homework.setBounds(280,230,60,25);
        button2 = new JButton("退出");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ArrayList<User> lst = null;
                if(!"All:0".equals(allNum.getText())){
                    try {
                        lst = func.readObject();
                        int index = lst.indexOf(new User("Student",name, pwd));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date date = new Date(System.currentTimeMillis());
                        lst.get(index).addHistory(formatter.format(date) + " " + allNum.getText() + " " + rightNum.getText());
                        func.writeObject(lst);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
                new Login();
            }
        });
        button2.setBounds(280, 295, 60, 25);
        timePattarn = new JButton("计时");
        timePattarn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeText.setText("1:00");
                time.start();
            }
        });
        timePattarn.setBounds(280, 140, 60, 25);
        normalPattarn = new JButton("普通");
        normalPattarn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = true;
                timeText.setText("");
                time.stop();
            }
        });
        normalPattarn.setBounds(280,170, 60,25);
        timeText = new JTextArea();
        timeText.setEditable(false);
        timeText.setFont(new Font("仿宋", Font.BOLD, 20));
        timeText.setForeground(Color.red);
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("0:0".equals(timeText.getText()))
                {
                    time.stop();
                    timeText.setText("时间到!");
                    flag = false;
                }
                else {
                    int min = Integer.parseInt(timeText.getText().split(":")[0]);
                    int sec = Integer.parseInt(timeText.getText().split(":")[1]);
                    int totalTime = min * 60 + sec;
                    totalTime--;
                    min = totalTime / 60;
                    sec = totalTime % 60;
                    timeText.setText(new Integer(min).toString() + ":" + new Integer(sec).toString());
                }
            }
        });
        timeText.setBounds(5,5,80,100);
        init();
        add(backgroundLabel);
        add(num1);
        add(sym);
        add(num2);
        add(label1);
        add(myAnswer);
        add(label2);
        add(correctanswer);
        add(next);
        add(allNum);
        add(rightNum);
        add(nameLabel);
        add(headlabel);
        add(history);
        add(homework);
        add(button2);
        add(timePattarn);
        add(normalPattarn);
        add(timeText);
        setBounds(100, 100, 370, 360);
        setVisible(true);
        func.windowClosing();
    }
    public void init() {
        String problem = core_func.problemAnswer();
        num1.setText(problem.split(" ")[0]);
        sym.setText(problem.split(" ")[1]);
        num2.setText(problem.split(" ")[2]);
        label2.setIcon(Data.NormalLabel);
        myAnswer.setText("");
        correctanswer.setText("");
    }
    class TextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int n1 = Integer.parseInt(num1.getText());
            int n2 = Integer.parseInt(num2.getText());
            //获得用户输入的答案
            String s = myAnswer.getText();
            if(s.length()>2||!s.matches("\\d+")){
                return;
            }
            int n = Integer.parseInt(s);
            count_All++;
            if(!sym.getText().equals("+")){
                n2=-n2;
            }
            if (n == n1 + n2) {
                count_Right++;
                label2.setIcon(RightLabel);
            } else {
                label2.setIcon(Data.WrongLabel);
                correctanswer.setText("正确答案" + new Integer(n1 + n2).toString());
            }
            allNum.setText("All:" + count_All);
            rightNum.setText("Right:" + count_Right);
        }
    }
    public static void main(String[] args) {
        new MyCaculator().loadFrame("张国庆", "1");
    }
}
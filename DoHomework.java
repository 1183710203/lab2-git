package increment4;
import increment.Core_Func;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DoHomework extends JFrame {
    private int problemIndex = 0;
    private JButton next, up, submit, back;
    private String [] myAnswer = new String[10];
    private JTextField answerText;
    private JLabel sym = new JLabel("=");
    private JTextArea problem, correctAnswer;
    private boolean submitFlag = false;
    public DoHomework(String [] problems, String name, String pwd){
        setLayout(null);
        problem = new JTextArea(new Integer(problemIndex + 1).toString() + "." + problems[problemIndex]);
        problem.setEditable(false);
        problem.setFont(new Font("宋体",Font.PLAIN, 20));
        problem.setBounds(10,20, 100,50);
        sym.setBounds(110,20,30,30);
        sym.setFont(new Font("宋体",Font.PLAIN, 20));
        answerText = new JTextField();
        answerText.setFont(new Font("宋体",Font.PLAIN, 20));
        answerText.setBounds(150,20,30,30);
        correctAnswer = new JTextArea();
        correctAnswer.setFont(new Font("宋体",Font.PLAIN, 13));
        correctAnswer.setBorder(null);
        correctAnswer.setBounds(10,80, 160,50);
        up = new JButton("上一题");
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myAnswer[problemIndex] = answerText.getText();
                if(problemIndex > 0)
                   problemIndex--;
                problem.setText(new Integer(problemIndex + 1).toString() + "." + problems[problemIndex]);
                answerText.setText(myAnswer[problemIndex]);
                if(submitFlag) {
                    int correct = Core_Func.getAnswer(problem.getText().split("\\.")[1]);
                    if(answerText.getText().matches("[\\d]+") &&
                            correct == Integer.parseInt(answerText.getText()))
                        correctAnswer.setText("你答对了!");
                    else
                        correctAnswer.setText("你答错了, 正确答案为: " + correct);
                }
            }
        });
        up.setBounds(10,160,80,20);
        next = new JButton("下一题");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myAnswer[problemIndex] = answerText.getText();
                if(problemIndex < 9)
                    problemIndex++;
                problem.setText(new Integer(problemIndex + 1).toString() + "." + problems[problemIndex]);
                answerText.setText(myAnswer[problemIndex]);
                if(submitFlag){
                    int correct = Core_Func.getAnswer(problem.getText().split("\\.")[1]);
                    if(answerText.getText().matches("[\\d]+") &&
                            correct == Integer.parseInt(answerText.getText()))
                    correctAnswer.setText("你答对了!");
                    else
                        correctAnswer.setText("你答错了, 正确答案为: " + correct);
                }
            }
        });
        next.setBounds(100,160,80,20);
        back = new JButton("退出");
        back.setBounds(30,190,60,20);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        submit = new JButton("提交");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerText.setEnabled(false);
                submitFlag = true;
            }
        });
        submit.setBounds(100,190,60,20);
        add(problem);
        add(sym);
        add(answerText);
        add(correctAnswer);
        add(up);
        add(next);
        add(back);
        add(submit);
        setVisible(true);
        setBounds(500,100,210,270);
    }
    public static void main(String[] args) {
        String [] problems = {"1 + 2","2 - 1","3 + 13"};
        new DoHomework(problems,"1","1");
    }
}

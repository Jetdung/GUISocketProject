package com.yzd.form;

import com.yzd.service.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Server {
    private JPanel panel1;
    private JTextArea textArea1;
    private JLabel Label1;
    private JTextArea textArea2;
    private JLabel Label2;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    ServerSend ss=null;

    public Server() {
        ServerThread st=new ServerThread(textArea1,textArea2);
        ss=new ServerSend(st,textField1,textArea1,textArea2);

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==e.VK_ENTER){
                    ss.run();
                }
            }
        });

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ss.run();
            }
        });

        button2.addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(button2.getText().equals("开启服务器")){
                    ServerThread st=new ServerThread(textArea1,textArea2);
                    st.start();
                    button2.setText("关闭服务器");
                }else if(button2.getText().equals("关闭服务器")){
                    st.stop();
                    String tmp= textArea1.getText();
                    textArea1.setText(tmp+"服务器已关闭！\n");
                    button2.setText("开启服务器");
                }
            }
        });

    }


    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame("Server");
        frame.setContentPane(new Server().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}

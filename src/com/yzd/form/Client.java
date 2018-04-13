package com.yzd.form;

import com.yzd.service.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private JPanel panel1;
    private JTextArea textArea1;
    private JLabel Label1;
    private JTextArea textArea2;
    private JLabel Label2;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    Socket socket = null;
    ClientSend cs=null;

    public Client(){

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==e.VK_ENTER){
                    cs.run();
                }
            }
        });

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cs.run();
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(button2.getText().equals("连接")){
                    try {
                        socket=new Socket("localhost",8878);
                        new Thread(new Receive(socket,"服务端",textArea2)).start();
                        cs=new ClientSend(socket,textField1,textArea1,textArea2);
                        textArea1.setText("连接成功！");
                    } catch (IOException e1) {
                        textArea1.setText("连接失败！");
                    }
                    button2.setText("关闭");
                }else if(button2.getText().equals("关闭")){
                    try {
                        if(socket!=null){
                            socket.close();
                        }
                        textArea1.setText("连接关闭！");
                        button2.setText("连接");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

package com.yzd.service;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread{

    JTextArea textArea1;
    JTextArea textArea2;
    List<Socket> list=new ArrayList<>();

    public ServerThread(JTextArea textArea1,JTextArea textArea2){
        this.textArea1=textArea1;
        this.textArea2=textArea2;
    }

    public void run(){
        ServerSocket ss= null;
        Socket socket = null;
        int count = 0;
        try {
            ss = new ServerSocket(8878);
            textArea1.setText("开启服务器成功！\n等待连接…………\n");
            while (true) {
                StringBuffer sb=new StringBuffer();
                socket = ss.accept();
                list.add(socket);
                sb.append(textArea1.getText());
                sb.append("客户端");
                sb.append(count + "连接成功！\n");
                textArea1.setText(sb.toString());
                new Thread(new Receive(socket, ("客户端"+count),textArea2)).start();
                count++;
            }
        } catch(Exception e1){
            textArea1.setText("开启服务器失败！\n");
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Socket> getSockets(){
        return list;
    }
}

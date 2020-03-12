package com.yzd.service;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {

    private JTextArea status;
    private JTextArea allMessage;
    static List<Socket> list = new ArrayList<>();

    public ServerThread(JTextArea status, JTextArea allMessage) {
        this.status = status;
        this.allMessage = allMessage;
    }

    @Override
    public void run() {
        ServerSocket ss = null;
        Socket socket = null;
        int count = 0;
        try {
            ss = new ServerSocket(8878);
            status.setText("开启服务器成功！\n等待连接…………\n");
            while (true) {
                StringBuilder sb = new StringBuilder();
                socket = ss.accept();
                list.add(socket);
                sb.append(status.getText())
                        .append("客户端")
                        .append(count)
                        .append("连接成功！\n");
                status.setText(sb.toString());
                new Thread(new Receive(socket, ("客户端" + count), allMessage)).start();
                count++;
            }
        } catch (Exception e1) {
            status.setText("开启服务器失败！\n");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

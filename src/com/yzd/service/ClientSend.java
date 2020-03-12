package com.yzd.service;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSend {
    private Socket socket = null;
    private JTextField sendMessage;
    private JTextArea status;
    private JTextArea allMessage;

    public ClientSend(Socket socket, JTextField sendMessage, JTextArea status, JTextArea allMessage) {
        this.socket = socket;
        this.sendMessage = sendMessage;
        this.allMessage = allMessage;
    }

    public void run() {
        if (socket != null) {
            String string = sendMessage.getText();
            String sb = allMessage.getText() +
                    "我说：" + string + "\n";
            allMessage.setText(sb);
            sendMessage.setText("");
            PrintWriter os;
            try {
                os = new PrintWriter(socket.getOutputStream());
                os.println(string);
                os.flush();
            } catch (IOException e1) {
                String tmp = status.getText();
                status.setText(tmp + "发送失败！\n");
            }
        } else {
            String tmp = status.getText();
            status.setText(tmp + "未连接！\n");
        }
    }
}

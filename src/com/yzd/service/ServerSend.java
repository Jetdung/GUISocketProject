package com.yzd.service;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerSend {
    private JTextField sendMessage;
    private JTextArea status;
    private JTextArea allMessage;

    public ServerSend(JTextField sendMessage, JTextArea status, JTextArea allMessage) {
        this.sendMessage = sendMessage;
        this.status = status;
        this.allMessage = allMessage;
    }

    public void run() {
        String string = sendMessage.getText();
        StringBuilder sb = new StringBuilder();
        sb.append(allMessage.getText())
                .append("服务端说：")
                .append(string)
                .append("\n");
        allMessage.setText(sb.toString());
        sendMessage.setText("");
        List<Socket> list = ServerThread.list;
        for (Socket s : list) {
            PrintWriter os;
            try {
                os = new PrintWriter(s.getOutputStream());
                os.println(string);
                os.flush();
            } catch (IOException e1) {
                String tmp = status.getText();
                status.setText(tmp + "发送失败！");
            }
        }
    }
}

package com.yzd.service;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSend {
    Socket socket=null;
    JTextField textField1;
    JTextArea textArea1;
    JTextArea textArea2;
    public ClientSend(Socket socket, JTextField textField1, JTextArea textArea1, JTextArea textArea2){
        this.socket=socket;
        this.textField1=textField1;
        this.textArea2=textArea2;
    }
    public void run(){
        if(socket!=null){
            String string=textField1.getText();
            StringBuffer sb=new StringBuffer();
            sb.append(textArea2.getText());
            sb.append("我说："+string+"\n");
            textArea2.setText(sb.toString());
            textField1.setText("");
            PrintWriter os;
            try {
                os=new PrintWriter(socket.getOutputStream());
                os.println(string);
                os.flush();
            } catch (IOException e1) {
                String tmp= textArea1.getText();
                textArea1.setText(tmp+"发送失败！\n");
            }
        }else{
            String tmp= textArea1.getText();
            textArea1.setText(tmp+"未连接！\n");
        }
    }
}

package com.yzd.service;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerSend {
    List<Socket> list=null;
    JTextField textField1;
    JTextArea textArea1;
    JTextArea textArea2;
    ServerThread st;
    public ServerSend(ServerThread st, JTextField textField1, JTextArea textArea1, JTextArea textArea2){
        this.st=st;
        this.textField1=textField1;
        this.textArea2=textArea2;
    }
    public void run(){
        String string=textField1.getText();
        StringBuffer sb=new StringBuffer();
        sb.append(textArea2.getText());
        sb.append("服务端说："+string+"\n");
        textArea2.setText(sb.toString());
        textField1.setText("");
        List<Socket> list=st.getSockets();
        for(Socket s:list){
            PrintWriter os;
            try {
                os=new PrintWriter(s.getOutputStream());
                os.println(string);
                os.flush();
            } catch (IOException e1) {
                String tmp= textArea1.getText();
                textArea1.setText(tmp+"发送失败！");
            }
        }
    }
}

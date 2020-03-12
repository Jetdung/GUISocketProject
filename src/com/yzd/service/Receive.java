package com.yzd.service;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable {
    Socket s;
    String name;
    String string = "";
    JTextArea textArea2 = null;

    public Receive(Socket s, String name, JTextArea tx2) {
        this.s = s;
        this.name = name;
        this.textArea2 = tx2;
    }

    @Override
    public void run() {
        BufferedReader is = null;
        try {

            while (true) {
                is = new BufferedReader(new InputStreamReader(s.getInputStream()));
                string = is.readLine();
                if (string != null) {
                    String sb = textArea2.getText() +
                            name + "说：" + string + "\n";
                    textArea2.setText(sb);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
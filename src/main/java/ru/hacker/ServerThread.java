package ru.hacker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        try {
            in = socket.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inr);

            OutputStream out = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

            BufferedReader fileReader = new BufferedReader(new FileReader("./Response"));

            char[] buf = new char[100];

            //диалог клиента-сервера
            while (inr.read(buf,0, 100) != -1) {//вопрос/сообщение от клиента
                String str  = new String(buf);
                System.out.println(str);
                //север что-то выполнил
                String str2 = "";
                while ((str2 = fileReader.readLine()) != null) {
                    bw.write(str2);
                    bw.flush();//ответ сервера клиенту
                }
                //socket.shutdownOutput();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

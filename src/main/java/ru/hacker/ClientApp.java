package ru.hacker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("Инициализация подключения к серверу");
        try (Socket socket = new Socket("localhost", 10000)) {
            System.out.println("Соединение установлено");

            FileReader fileReader = new FileReader("./httpPackage");
            BufferedReader reader = new BufferedReader(fileReader);

            StringBuilder stringBuilder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                stringBuilder.append(str);
            }

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            OutputStreamWriter writer = new OutputStreamWriter(out);

            writer.write(stringBuilder.toString());
            writer.flush();
            //socket.shutdownOutput();

            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inr);

            while (!bufferedReader.ready()) {
                Thread.sleep(50);
            }

            char[] buf = new char[100];

            while (inr.read(buf, 0, 100) != -1) {
                String answer = new String(buf);
                System.out.println(answer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Соединение разорвано");
    }
}

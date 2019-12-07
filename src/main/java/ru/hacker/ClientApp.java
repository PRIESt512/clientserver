package ru.hacker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {

    public static void main(String[] args) {

        System.out.println("Инициализация подключения к серверу");

        try (Socket socket = new Socket("localhost", 10000);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader fileReader = new BufferedReader(new FileReader("./httpPackage"));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            System.out.println("Соединение установлено");

            PrintWriter pw = new PrintWriter(bw, true);

            String clientMessage = "";
            while ((clientMessage = fileReader.readLine()) != null) {//запрос/сообщение клиенту

                pw.println(clientMessage);

                //клиент что-то выполнил

                String messageServer = "";
                if ((messageServer = br.readLine()) != null) {
                    System.out.println(String.format("messageServer - %s", messageServer));
                } else break;
            }
        } catch (UnknownHostException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Соединение разорвано");
        }
    }
}

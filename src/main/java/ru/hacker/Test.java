package ru.hacker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        System.out.println(File.separator);
        System.out.println(File.pathSeparator);

        try (BufferedReader fileReader = new BufferedReader(new FileReader("." + File.separator +
                "test" + File.separator + "txt"))) {
            System.out.println(fileReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

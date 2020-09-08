package ru.job4j.parserfile;

import java.io.*;

public class ReadContentWithoutUnicode implements StrategyGetContent {

    @Override
    public synchronized String getContent(File file) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) != -1) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (IOException i) {
            System.out.println("File not found");
        }
        return output.toString();
    }
}

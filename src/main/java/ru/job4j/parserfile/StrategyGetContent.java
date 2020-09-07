package ru.job4j.parserfile;

import java.io.*;

public interface StrategyGetContent {
    default String getContent(File file) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) != -1) {
                output.append((char) data);
            }
        } catch (IOException i) {
            System.out.println("File not found");
        }
        return output.toString();
    }
}

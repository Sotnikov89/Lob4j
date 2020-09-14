package ru.job4j.parserfile;

import java.io.*;

public class WriteContent {

    public void saveContent(String content, File file) {
        try (FileWriter fileWriter= new FileWriter(file)) {
            fileWriter.write(content);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

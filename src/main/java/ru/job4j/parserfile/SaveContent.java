package ru.job4j.parserfile;

import java.io.*;

public class SaveContent {

    public void saveContent(File file, String content) {
        try (OutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException i) {
            System.out.println("File not found");
        }
    }
}

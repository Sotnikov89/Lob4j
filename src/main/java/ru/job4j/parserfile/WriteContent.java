package ru.job4j.parserfile;

import java.io.*;

public class WriteContent {

    public void saveContent(File file, String content) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                outputStream.write(content.charAt(i));
                outputStream.close();
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}



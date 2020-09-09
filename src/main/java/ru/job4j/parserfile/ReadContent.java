package ru.job4j.parserfile;

import java.io.*;

public class ReadContent {

    public String getContent(File file) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(file)) {
            int data;
            while ((data = inputStream.read()) > 0) {
                output.append((char) data);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return output.toString();
    }

    public String getContentWithoutUnicode(File file) {
        StringBuilder output = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(file)) {
            int data;
            while ((data = inputStream.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return output.toString();
    }
}

package ru.job4j.parserfile;

import java.io.*;

public class Content implements GetContent {

    private final File file;

    public Content(File file) {
        this.file = file;
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

    @Override
    public String readContent(File file) {
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

    @Override
    public void writeContent(String content) {
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

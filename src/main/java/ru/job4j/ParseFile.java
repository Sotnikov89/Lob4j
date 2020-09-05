package ru.job4j;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() {
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

    public synchronized String getContentWithoutUnicode() throws IOException {
        InputStream i = new BufferedInputStream(new FileInputStream(file));
        StringBuilder output = new StringBuilder();
        int data;
        while ((data = i.read()) != -1) {
            if (data < 0x80) {
                output.append((char) data);
            }
        }
        i.close();
        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new BufferedOutputStream(new FileOutputStream(file));
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
        o.close();
    }
}

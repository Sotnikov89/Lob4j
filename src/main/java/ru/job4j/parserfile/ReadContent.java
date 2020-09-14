package ru.job4j.parserfile;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadContent {

    public String getContent(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            while (in.ready()) {
                stringBuilder.append(in.readLine());
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return stringBuilder.toString();
    }
}

package ru.job4j.parserfile;

import javax.sql.rowset.Predicate;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadContent implements GetContent{

    public String getContent(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            while (in.ready()) {
                stringBuilder.append(in.readLine());
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public String getContent(File file, Predicate filter) {
        return null;
    }
}

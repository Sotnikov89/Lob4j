package ru.job4j.parserfile;

import java.io.File;

public interface GetContent {

    String readContent(File file);

    void writeContent(String content);
}

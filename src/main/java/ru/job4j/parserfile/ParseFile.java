package ru.job4j.parserfile;

import java.io.*;

public class ParseFile {
    private File file;

    private StrategyGetContent getContentStrategy;

    private SaveContent saveContent;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized StrategyGetContent getGetContentStrategy() {
        return getContentStrategy;
    }

    public synchronized void setGetContentStrategy(StrategyGetContent getContentStrategy) {
        this.getContentStrategy = getContentStrategy;
    }

    public synchronized String getContent() {
        return getContentStrategy.getContent(file);
    }

    public synchronized void saveContent(String content) {
        saveContent.saveContent(file, content);
    }
}

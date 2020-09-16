package ru.job4j.parserfile;

import javax.sql.rowset.Predicate;
import java.io.File;

public interface GetContent {
    public String getContent(File file, Predicate filter);
}

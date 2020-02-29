package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchTest {

    @Test
    public void whenFilesDoNotExist() throws NotDirectoryException {
        String path = System.getProperty("java.io.tmpdir") + "test";
        List<String> exts = List.of("tmp");
        Search search = new Search();
        List<File> result = search.files(path, exts);
     //   assertTrue(result.isEmpty());
    }

    @Test (expected = NotDirectoryException.class)
    public void whenDirectoryDosNotExist() throws NotDirectoryException {
        String path = "notExist";
        List<String> exts = List.of("tmp");
        Search search = new Search();
        List<File> result = search.files(path, exts);
    }
}

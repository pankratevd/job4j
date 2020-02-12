package ru.job4j.io;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Search {
    List<File> files(String parent, List<String> exts) throws NotDirectoryException {
        Queue<File> directories = new LinkedList<>();
        List<File> list = new ArrayList<>();
        File directory = new File(parent);

        if (!directory.isDirectory()) {
            throw new NotDirectoryException(directory.getName());
        }
        directories.offer(directory);

        while (!directories.isEmpty()) {
            File[] files = directories.poll().listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    directories.offer(f);
                } else {
                    for (String s : exts) {
                        if (f.getName().endsWith(s)) {
                            list.add(new File(f.getName()));
                        }
                    }
                }
            }
        }
        return list;
    }
}


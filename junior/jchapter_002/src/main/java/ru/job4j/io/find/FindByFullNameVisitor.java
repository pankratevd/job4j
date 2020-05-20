package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FindByFullNameVisitor extends SimpleFileVisitor<Path> {

    private final String fileName;
    private final List<Path> list;

    public FindByFullNameVisitor(String fileName, List<Path> list) {
        this.fileName = fileName;
        this.list = list;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        FileVisitResult result;
        if (exc instanceof AccessDeniedException) {
            result = FileVisitResult.SKIP_SUBTREE;
        } else {
            result = super.visitFileFailed(file, exc);
        }
        return result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.getFileName().toString().equals(fileName)) {
            list.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

}

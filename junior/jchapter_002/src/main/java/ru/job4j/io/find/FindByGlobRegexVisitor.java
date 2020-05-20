package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FindByGlobRegexVisitor extends SimpleFileVisitor<Path> {

    private final List<Path> list;
    private final PathMatcher matcher;

    public FindByGlobRegexVisitor(PathMatcher matcher, List<Path> list) {
        this.list = list;
        this.matcher = matcher;
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
        if (matcher.matches(file.getFileName())) {
            list.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}

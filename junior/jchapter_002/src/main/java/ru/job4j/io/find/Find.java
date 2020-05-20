package ru.job4j.io.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Find {

    private final Args args;
    private final List<Path> list = new ArrayList<>();

    public Find(Args args) {
        this.args = args;
    }

    public void process() throws IOException {
        Files.walkFileTree(Paths.get(args.directory()), fileVisitor());

        try (PrintWriter printWriter = new PrintWriter(args.output())) {
            list.forEach(printWriter::println);
        }
    }

    private FileVisitor<Path> fileVisitor() {
        FileVisitor<Path> result;

        switch (args.specifyingKey()) {
            case "-f":
                result = new FindByFullNameVisitor(args.filePattern(), this.list);
                break;
            case "-r":
                result = new FindByGlobRegexVisitor(FileSystems.getDefault().getPathMatcher("regex:" + args.filePattern()), list);
                break;
            case "-m":
                result = new FindByGlobRegexVisitor(FileSystems.getDefault().getPathMatcher("glob:" + args.filePattern()), list);
                break;
            default:
                result = null;
        }
        return result;
    }

    public static void main(String[] args) {
        Args arguments = new Args(args);
        if (arguments.getHelp()) {
            System.out.println(arguments.printHelp());
            System.exit(0);
        }

        if (!arguments.valid()) {
            System.out.println("Incorrect arguments");
            System.out.println(arguments.printHelp());
            System.exit(0);
        }

        Find findFiles = new Find(arguments);
        try {
            findFiles.process();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}

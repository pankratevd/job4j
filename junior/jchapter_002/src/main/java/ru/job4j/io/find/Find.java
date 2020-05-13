package ru.job4j.io.find;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Find {

    private final Args args;

    public Find(Args args) {
        this.args = args;
    }

    public void process() throws IOException {
        try (PrintWriter outWriter = new PrintWriter(new File(args.output()))) {
            PathMatcher matcher;
            switch (args.specifyingKey()) {
                case "-f":
                    Files.walkFileTree(Paths.get(args.directory()), new SimpleFileVisitor<>() {
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
                            if (file.getFileName().toString().equals(args.filePattern())) {
                                outWriter.println(file);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                    break;

                case "-r":
                    matcher = FileSystems.getDefault().getPathMatcher("regex:" + args.filePattern());
                    Files.walkFileTree(Paths.get(args.directory()), new SimpleFileVisitor<>() {
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
                                outWriter.println(file);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                    break;

                case "-m":
                    matcher = FileSystems.getDefault().getPathMatcher("glob:" + args.filePattern());
                    Files.walkFileTree(Paths.get(args.directory()), new SimpleFileVisitor<>() {
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
                                outWriter.println(file);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                    break;
                default:
            }
        }
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

package ru.job4j.io.pack;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private Args arguments;

    public Zip(Args arguments) {
        this.arguments = arguments;
    }

    private List<File> seekBy(String root, String ext) {

        List<File> result = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + ext);

        final Path directory = Paths.get(root);

        try {
            Files.walk(directory)
                    .filter(f -> Files.isRegularFile(f) && !pathMatcher.matches(f.getFileName()))
                    .forEach(f -> result.add(directory.relativize(f).toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void pack(List<File> sources, File target) throws IOException {

        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            sources.forEach(e -> {
                try {
                    zip.putNextEntry(new ZipEntry(e.getPath()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(arguments.directory() + "/" + e))) {
                    zip.write(out.readAllBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) {

        Args arguments = new Args(args);
        if (!arguments.valid()) {
            System.out.println(arguments.getErrorMessage());
            System.exit(-1);
        }
        Zip zip = new Zip(arguments);

        List<File> files = zip.seekBy(arguments.directory(), arguments.exclude());

        try {
            zip.pack(files, new File(arguments.output()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}

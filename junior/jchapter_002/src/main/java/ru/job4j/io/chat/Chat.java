package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Chat {

    private final String inFile;
    private final String outFile;

    private final static String PAUSE = "стоп";
    private final static String CONTINUE = "продолжить";
    private final static String EXIT = "закончить";

    private List<String> list = new ArrayList<>();

    public Chat(String file, String outFile) {
        this.inFile = file;
        this.outFile = outFile;
    }

    public void chat() throws IOException {
        boolean isPause = false;
        String str;

        try (Stream<String> stream = Files.lines(Paths.get(inFile))) {
            stream.forEach(list::add);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(outFile)) {
            do {
                str = br.readLine();
                writer.println(str);

                if (EXIT.equals(str)) {
                    break;
                }

                if (PAUSE.equals(str)) {
                    isPause = true;
                    continue;
                }

                if (CONTINUE.equals(str)) {
                    isPause = false;
                    continue;
                }

                if (!isPause) {
                    String phrase = getPhrase();
                    writer.println(phrase);
                    System.out.println(phrase);
                }
            } while (!EXIT.equals(str));
        }
    }

    private String getPhrase() {
        int index = (int) (Math.random() * this.list.size());
        return this.list.get(index);
    }
}

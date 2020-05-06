package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Chat {

    private final String inFile;
    private final String outFile;

    private final Set<Character> phraseSplitSymbol = Set.of('.', '?', '!');

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        makePhrasesList();

        try (PrintWriter writer = new PrintWriter(outFile)) {
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

    private void makePhrasesList() throws IOException {
        StringBuilder sb = new StringBuilder();
        String encodingInFile = "UTF-8";
        try (BufferedReader br = new BufferedReader(new FileReader(inFile, Charset.forName(encodingInFile)))) {
            int ch = br.read();
            boolean isPrevSymbol = false;
            while (ch != -1) {
                if (!phraseSplitSymbol.contains((char) ch)) {
                    if (isPrevSymbol) {
                        this.list.add(sb.toString().replace(System.lineSeparator(), "").trim());
                        sb = new StringBuilder();
                        isPrevSymbol = false;
                    }
                    sb.append((char) ch);
                } else {
                    sb.append((char) ch);
                    isPrevSymbol = true;
                }
                ch = br.read();
            }

            if (sb.length() != 0) {
                this.list.add(sb.toString().replace(System.lineSeparator(), "").trim());
            }

        }
    }

    private String getPhrase() {
        int index = (int) (Math.random() * this.list.size());
        return this.list.get(index);
    }
}

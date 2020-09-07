package concurrent.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ParseFileReader {
    private final File file;

    public ParseFileReader(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        String result = "";
        synchronized (file) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                result = br.readLine();
            }
        }
        return result;
    }

    public String getContentWithoutUnicode() throws IOException {
        StringBuilder sb = new StringBuilder();
        synchronized (file) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                int data;
                while ((data = br.read()) > 0) {
                    if (data < 0x80) {
                        sb.append((char) data);
                    }
                }
            }
        }
        return sb.toString();
    }
}

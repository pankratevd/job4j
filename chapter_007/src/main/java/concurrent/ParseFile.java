package concurrent;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        return readContent(ch -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return readContent(ch -> ch < 0x80);
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(content);
        }
    }

    private synchronized String readContent(Predicate<Integer> predicate) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data = br.read();
            while (data != -1) {
                if (predicate.test(data)) {
                    sb.append((char) data);
                }
                data = br.read();
            }
        }
        return sb.toString();
    }
}

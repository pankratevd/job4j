package concurrent;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data = br.read();
            while (data != -1) {
                sb.append((char) data);
                data = br.read();
            }
        }
        return sb.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data = br.read();
            while (data != -1) {
                if (data < 0x80) {
                    sb.append((char) data);
                }
                data = br.read();
            }
        }
        return sb.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(content);
        }
    }
}

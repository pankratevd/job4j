package concurrent;

import java.io.*;

public class ParseFile {
    private volatile File file;
    private volatile boolean flag0 = false;
    private volatile boolean flag1 = false;

    public synchronized void setFile(File f) {
        while (flag0 || flag1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        file = f;
    }

    public File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        while (flag0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        flag0 = true;
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            result = br.readLine();
        }
        flag0 = false;
        return result;
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        while (flag1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        flag1 = true;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = br.read()) > 0) {
                if (data < 0x80) {
                    sb.append((char) data);
                }
            }
        }
        flag1 = false;
        return sb.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        while (flag0 || flag1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        flag0 = true;
        flag1 = true;
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.print(content);
        }
        flag0 = false;
        flag1 = false;
    }
}

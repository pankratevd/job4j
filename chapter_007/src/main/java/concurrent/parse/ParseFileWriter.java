package concurrent.parse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ParseFileWriter {
    private final File file;

    public ParseFileWriter(File file) {
        this.file = file;
    }
    public void saveContent(String content) throws IOException {
        synchronized (file) {
            try (PrintWriter pw = new PrintWriter(file)) {
                pw.print(content);
            }
        }
    }
}

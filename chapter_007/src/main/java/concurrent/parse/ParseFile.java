package concurrent.parse;

import java.io.File;
import java.io.IOException;

public class ParseFile {
    private volatile File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        return new ParseFileReader(this.file).getContent();
    }

    public String getContentWithoutUnicode() throws IOException {
        return new ParseFileReader(this.file).getContentWithoutUnicode();
    }

    public void saveContent(String content) throws IOException {
        new ParseFileWriter(this.file).saveContent(content);
    }
}

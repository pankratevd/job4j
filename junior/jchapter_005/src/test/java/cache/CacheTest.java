package cache;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CacheTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Ignore
    @Test
    public void getFile() throws IOException {
        String fullPathFolder = folder.getRoot().getAbsolutePath();
        Cache cache = new Cache(fullPathFolder);
        long heapSize = Runtime.getRuntime().totalMemory();
        StringBuilder sb = new StringBuilder();
        File file1 = folder.newFile("test1.txt");
        File file2 = folder.newFile("test2.txt");
        Random rnd = new Random();

        for (int i = 0; i < rnd.nextInt(80) + 100; i++) {
            sb.append("fillfilefillfilefillfilefillfilefillfilefillfile" + rnd.nextDouble());
        }

        try (PrintWriter printWriter = new PrintWriter(file1)) {
            while (file1.length() < heapSize * 0.7) {
                printWriter.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int expected = (int) file1.length();


        assertThat(cache.getFile(file1.getName()).length(), is(expected));
        Files.delete(Path.of(file1.getAbsolutePath()));

        assertThat(cache.getFile(file1.getName()).length(), is(expected));

        try (PrintWriter printWriter = new PrintWriter(file2)) {
            while (file2.length() < Runtime.getRuntime().totalMemory() * 0.7) {
                printWriter.println(rnd.nextFloat() + sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        expected = (int) file2.length();
        cache.getFile(file2.getName());

        Files.delete(Path.of(file2.getAbsolutePath()));

        cache.getFile(file2.getName());

        assertNull(cache.getFile(file1.getName()));
        assertThat(cache.getFile(file2.getName()).length(), is(expected));
    }
}

package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AnalizyTestFolder {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenLastCodeIsOK() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n"
                    + "\n"
                    + "500 10:57:01\n"
                    + "\n"
                    + "400 10:58:01\n"
                    + "\n"
                    + "200 10:59:01\n"
                    + "\n"
                    + "500 11:01:02\n"
                    + "\n"
                    + "200 11:02:02");
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.toString(), target.toString());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01;"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02;"));
            assertNull(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenLastCodeIsError() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n"
                    + "\n"
                    + "500 10:57:01\n"
                    + "\n"
                    + "400 10:58:01\n"
                    + "\n"
                    + "200 10:59:01\n"
                    + "\n"
                    + "500 11:01:02\n"
                    + "\n"
                    + "200 11:02:02\n"
                    + "\n"
                    + "400 11:02:03");
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.toString(), target.toString());

        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01;"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02;"));
            assertThat(reader.readLine(), is("11:02:03;"));
            assertNull(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
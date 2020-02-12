package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenLastCodeIsOK() {
        String source = "c:/projects/job4j/data/log.log";
        String target = "c:/projects/job4j/data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01;"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02;"));
            assertNull(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenLastCodeIsError() {
        String source = "c:/projects/job4j/data/log1.log";
        String target = "c:/projects/job4j/data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
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

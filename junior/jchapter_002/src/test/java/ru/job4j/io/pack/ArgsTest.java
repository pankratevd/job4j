package ru.job4j.io.pack;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArgsTest {

    @Test
    public void whenSixArgumentsAllCorrect() {
        String[] inArgs = {"-o", "c:/tmp/output.zip", "-d", "c:/temp", "-e", "*.tmp"};
        Args args = new Args();
        args.initialize(inArgs);
        String expectedDirectory = "c:/temp";
        String expectedOutput = "c:/tmp/output.zip";
        String expectedExclude = "*.tmp";
        assertThat(expectedDirectory, is(args.directory()));
        assertThat(expectedExclude, is(args.exclude()));
        assertThat(expectedOutput, is(args.output()));
    }

    @Test
    public void whenFourArgumentsAllCorrect() {
        String[] inArgs = {"-o", "c:/tmp/output.zip", "-d", "c:/temp"};
        Args args = new Args();
        args.initialize(inArgs);
        String expectedDirectory = "c:/temp";
        String expectedOutput = "c:/tmp/output.zip";
        assertThat(expectedDirectory, is(args.directory()));
        assertThat(expectedOutput, is(args.output()));
    }
    @Test (expected = IllegalArgumentException.class)
    public void whenIllegalArguments() {
        String[] inArgs = {"-o", "c:/tmp/output.zip", "-t", "c:/temp"};
        Args args = new Args();
        args.initialize(inArgs);
    }
}

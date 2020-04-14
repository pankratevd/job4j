package ru.job4j.io.pack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ArgsTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void whenSixArgumentsAllCorrect() throws IOException {
        File inFile = folder.newFolder();
        File outputFile = folder.newFolder();
        String outputFolder = outputFile.toString();
        String inFolder = inFile.toString();
        String[] inArgs = {"-o", outputFolder + "/output.zip", "-d", inFolder, "-e", "*.tmp"};
        Args args = new Args(inArgs);
        String expectedDirectory = inFolder;
        String expectedOutput = outputFolder + "/output.zip";
        String expectedExclude = "*.tmp";
        assertThat(args.valid(), is(true));
        assertThat(args.directory(), is(expectedDirectory));
        assertThat(expectedExclude, is(args.exclude()));
        assertThat(expectedOutput, is(args.output()));
    }

    @Test
    public void whenFourArgumentsAllCorrect() throws IOException {

        File inFile = folder.newFolder();
        File outputFile = folder.newFolder();
        String outputFolder = outputFile.toString();
        String inFolder = inFile.toString();

        String[] inArgs = {"-o", outputFolder + "/output.zip", "-d", inFolder};
        Args args = new Args(inArgs);
        String expectedDirectory = inFolder;
        String expectedOutput = outputFolder + "/output.zip";
        assertThat(args.valid(), is(true));
        assertThat(expectedDirectory, is(args.directory()));
        assertThat(expectedOutput, is(args.output()));
    }

    @Test
    public void whenIllegalArguments() throws IOException {
        File inFile = folder.newFolder();
        File outputFile = folder.newFolder();
        String outputFolder = outputFile.toString();
        String inFolder = inFile.toString();

        String[] inArgs = {"-o", outputFolder + "/output.zip", "-d", inFolder, "-t", "*.tmp"};
        Args args = new Args(inArgs);
        assertThat(args.valid(), is(false));
    }
}

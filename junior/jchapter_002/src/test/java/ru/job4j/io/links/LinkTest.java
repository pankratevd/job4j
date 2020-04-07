package ru.job4j.io.links;


import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LinkTest {

    @Test
    public void processWhenSixGroup() throws IOException {
        String inFile = "./testData/in6.csv";
        String outFile = "./testData/out6.csv";
        String expectedFile = "./testData/out6_expected.csv";
        Link link = new Link(inFile, outFile, 3);
        link.process();
        assertEquals("The files differ!",
                FileUtils.readFileToString(new File(expectedFile), "utf-8"),
                FileUtils.readFileToString(new File(outFile), "utf-8"));
    }

    @Test
    public void processWhenOneGroup() throws IOException {
        String inFile = "./testData/in1.csv";
        String outFile = "./testData/out1.csv";
        String expectedFile = "./testData/out1_expected.csv";
        Link link = new Link(inFile, outFile, 3);
        link.process();
        link.printBadLines();
        assertEquals("The files differ!",
                FileUtils.readFileToString(new File(expectedFile), "utf-8"),
                FileUtils.readFileToString(new File(outFile), "utf-8"));
    }

    @Test
    public void processWhenFourColumnsSixGroup() throws IOException {
        String inFile = "./testData/in4.csv";
        String outFile = "./testData/out4.csv";
        String expectedFile = "./testData/out4_expected.csv";
        Link link = new Link(inFile, outFile, 4);
        link.process();
        link.printBadLines();
        assertEquals("The files differ!",
                FileUtils.readFileToString(new File(expectedFile), "utf-8"),
                FileUtils.readFileToString(new File(outFile), "utf-8"));
    }
}

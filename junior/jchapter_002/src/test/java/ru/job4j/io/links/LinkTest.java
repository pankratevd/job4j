package ru.job4j.io.links;


import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LinkTest {

    @Test
    public void processWhenSixGroup() throws IOException {
        String inFile = "./testData/in6.csv";
        String outFile = "./testData/out6.csv";
        String expectedFile = "./testData/out6_expected.csv";
        Link link = new Link(inFile, outFile);
        List<String> list;
        list = link.makeList();
        List<Set<Integer>> indexList = link.net(list);
        link.printGroups(list, indexList);
        assertEquals("The files differ!",
                FileUtils.readFileToString(new File(expectedFile), "utf-8"),
                FileUtils.readFileToString(new File(outFile), "utf-8"));
    }

    @Test
    public void processWhenOneGroup() throws IOException {
        String inFile = "./testData/in1.csv";
        String outFile = "./testData/out1.csv";
        String expectedFile = "./testData/out1_expected.csv";
        Link link = new Link(inFile, outFile);

        assertEquals("The files differ!",
                FileUtils.readFileToString(new File(expectedFile), "utf-8"),
                FileUtils.readFileToString(new File(outFile), "utf-8"));
    }


}

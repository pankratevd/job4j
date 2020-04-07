package ru.job4j.io.links;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Link {

    static final String DELIMITER = ";";
    private static final String EMPTY_VALUE = "\"\"";
    private int numberColumns = 3;
    private String inFile;
    private String outFile;

    private List<String> badLines = new LinkedList<>();
    private Set<Line> lines = new HashSet<>();
    private List<Group> groups = new LinkedList<>();
    private List<Set<String>> columns = new ArrayList<>(numberColumns);
    private List<Set<String>> duplicate = new ArrayList<>(numberColumns);

    public Link(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public Link(String inFile, String outFile, int columns) {
        this(inFile, outFile);
        this.numberColumns = columns;
    }

    public void process() throws IOException {

        createSetLines();
        removeNonDuplicateLine();
        makeGroups();
        printGroups();

    }

    public void printBadLines() {
        for (String s : badLines) {
            System.out.println(s);
        }
    }

    private void createSetLines() throws IOException {
        for (int i = 0; i < numberColumns; i++) {
            columns.add(i, new HashSet<>());
            duplicate.add(i, new HashSet<>());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {

            String strLine = br.readLine();

            while (strLine != null) {
                String[] arr = strLine.split(DELIMITER);
                if (arr.length < numberColumns) {
                    badLines.add(strLine);
                    strLine = br.readLine();
                    continue;
                }
                Line line = new Line(strLine);

                if (!lines.contains(line)) {
                    lines.add(line);
                } else {
                    strLine = br.readLine();
                    continue;
                }

                for (int i = 0; i < numberColumns; i++) {
                    if (EMPTY_VALUE.equals(arr[i])) {
                        continue;
                    }
                    if (!columns.get(i).add(arr[i])) {
                        duplicate.get(i).add(arr[i]);
                    }
                }
                strLine = br.readLine();
            }
        }
    }

    private void removeNonDuplicateLine() {

        lines.removeIf(e -> {
            boolean isRemove = true;
            for (int i = 0; i < numberColumns; i++) {
                String str = e.getArr()[i];
                if (duplicate.get(i).contains(str)) {
                    isRemove = false;
                    break;
                }
            }
            return isRemove;
        });
    }

    private void makeGroups() {

        List<Line> listLines = new LinkedList<>(lines);
        lines = null;
        Queue<Line> queue = new LinkedList<>();

        while (listLines.size() != 0) {
            queue.offer(listLines.get(0));
            Group newGroup = new Group();

            while (!queue.isEmpty()) {
                Line line = queue.poll();
                listLines.remove(line);
                newGroup.list.add(line);
                for (Line l : listLines) {
                    for (int i = 0; i < numberColumns; i++) {
                        if (!EMPTY_VALUE.equals(l.getArr()[i]) && l.getArr()[i].equals(line.getArr()[i])) {
                            queue.offer(l);
                        }
                    }
                }
            }

            groups.add(newGroup);
        }

        groups.sort((o1, o2) -> o2.list.size() - o1.list.size());
    }

    private void printGroups() throws IOException {

        int count = 1;
        try (PrintWriter printWriter = new PrintWriter(outFile);) {
            printWriter.printf("Всего групп: %d%n", groups.size());
            for (Group gr : groups) {
                printWriter.printf("Группа %d%n", count++);
                for (Line l : gr.list) {
                    printWriter.println(l);
                }
                printWriter.println();
            }
        }
    }
}

package ru.job4j.io.links;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Link {

    private final String delimiter = ";";
    private final String empty = "\"\"";
    private final String regex = "\\S+;\\S+;\\S+";

    private String inFile;
    private String outFile;

    private List<String> badLines = new LinkedList<>();
    private Set<Line> lines = new HashSet<>();
    private List<Group> groups = new LinkedList<>();

    private Map<String, Boolean> c1 = new HashMap<>();
    private Map<String, Boolean> c2 = new HashMap<>();
    private Map<String, Boolean> c3 = new HashMap<>();

    public Link(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void process() throws IOException {
        createSetLines();
        clearSingleLines();
        makeGroups();
        printGroups();
    }

    public void printBadLines() {
        for (String s : badLines) {
            System.out.println(s);
        }
    }

    private void createSetLines() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
            String line = br.readLine();
            while (line != null) {
                if (!line.matches(regex)) {
                    badLines.add(line);
                    line = br.readLine();
                    continue;
                }
                String[] arr = line.split(delimiter, -1);
                String column1 = arr[0];
                String column2 = arr[1];
                String column3 = arr[2];

                if (!lines.add(new Line(column1, column2, column3))) {
                    line = br.readLine();
                    continue;
                }

                if (c1.containsKey(column1) && !empty.equals(column1)) {
                    c1.put(column1, true);
                } else {
                    c1.put(column1, false);
                }

                if (c2.containsKey(column2) && !empty.equals(column2)) {
                    c2.put(column2, true);
                } else {
                    c2.put(column2, false);
                }

                if (c3.containsKey(column3) && !empty.equals(column3)) {
                    c3.put(column3, true);
                } else {
                    c3.put(column3, false);
                }


                line = br.readLine();
            }
        }
    }

    private void clearSingleLines() {
        c1.entrySet().removeIf(e -> !e.getValue());
        c2.entrySet().removeIf(e -> !e.getValue());
        c3.entrySet().removeIf(e -> !e.getValue());
        lines.removeIf(l -> !(c1.containsKey(l.field1) || c2.containsKey(l.field2) || c3.containsKey(l.field3)));
    }


    private void makeGroups() {
        List<Line> listLines = new LinkedList<>(lines);
        lines = null;
        Queue<Line> queue = new LinkedList<>();

        while (listLines.size() != 0) {
            Line ln = listLines.get(0);
            queue.offer(ln);
            Group newGroup = new Group();

            while (!queue.isEmpty()) {
                Line line = queue.poll();
                listLines.remove(line);
                newGroup.list.add(line);
                for (Line l : listLines) {
                    if ((!empty.equals(l.field1) && l.field1.equals(line.field1)) || (!empty.equals(l.field2) && l.field2.equals(line.field2)) || (!empty.equals(l.field3) && l.field3.equals(line.field3))) {
                        queue.offer(l);
                    }
                }
                listLines.removeAll(queue);
            }
            groups.add(newGroup);
        }
        Collections.sort(groups, (o1, o2) -> o2.list.size() - o1.list.size());
    }

    private void printGroups() throws IOException {
        int count = 1;
        try (PrintWriter printWriter = new PrintWriter(outFile);) {
            printWriter.printf("Всего групп: %d\n", groups.size());
            for (Group gr : groups) {
                printWriter.printf("Группа %d\n", count++);
                for (Line l : gr.list) {
                    printWriter.println(l);
                }
                printWriter.println();
            }
        }
    }
}

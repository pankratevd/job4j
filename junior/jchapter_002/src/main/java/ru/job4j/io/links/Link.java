package ru.job4j.io.links;

import java.io.*;
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

    public Link() {
    }

    ;

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

    private List<List<String>> makeList(String file) throws FileNotFoundException {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String str = br.readLine();
            while (str != null) {
                if (str.split(DELIMITER).length != 3) {
                    str = br.readLine();
                    continue;
                }
                    List<String> lineList = new ArrayList<>();
                for (String s : str.split(DELIMITER)) {
                    lineList.add(s);
                }
                list.add(lineList);
                str = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

    private List<String> net(List<List<String>> data) {
        List<List<String>> result = new ArrayList<>();
        List<HashMap<String, Set<String>>> columns = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            columns.add(new HashMap<>());
        }

        for (int i = 0; i < data.size(); i++) {
            List<String> list1 = data.get(i);
            for (int j = 0; j < list1.size(); j++) {
                Map<String, Set<String>> map = columns.get(j);
                if (!map.containsKey(list1.get(j))) {
                    Set<String> set = new HashSet<>();
                    set.add(Integer.toString(i));
                    map.put(list1.get(j), set);
                } else {
                    map.get(list1.get(j)).add(Integer.toString(i));
                }
            }
        }


        Set<Set<String>> set = new HashSet<>();

        for (int i = 0; i < columns.size(); i++) {
            //System.out.println("Column " + i);
            for (Map.Entry<String, Set<String>> e : columns.get(i).entrySet()) {
                if (e.getValue().size() > 1) {
                    set.add(e.getValue());
                }
            }
        }
        System.out.println(set.size());

        List<Set<String>> listSet = new LinkedList<>(set);

        while (listSet.size() != 0) {
            List<String> list = new ArrayList<>();
        }



        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Link link = new Link();
        List<List<String>> list;

        list = link.makeList("c:/temp/lng.csv");
        System.out.println(list.size());
        /*for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
        link.net(list);

    }
}

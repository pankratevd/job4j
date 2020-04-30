package ru.job4j.io.links;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Link {

    static final String DELIMITER = ";";
    static final int NUMBER_COLUMNS = 3;
    static final String EMPTY_LINE = "\"\"";
    private String inFile;
    private String outFile;
    private int countNotSingleGroup;

    public Link(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    List<String> makeList() {
        Set<String> list = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {

            String str = br.readLine();
            while (str != null) {
                if (str.split(DELIMITER).length < NUMBER_COLUMNS) {
                    str = br.readLine();
                    continue;
                }
                list.add(str);
                str = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(list);
    }

    List<Set<Integer>> net(List<String> data) {
        List<Set<Integer>> groups = new ArrayList<>();
        Set<Set<Integer>> same = new HashSet<>();
        List<HashMap<String, Set<Integer>>> columns = new ArrayList<>();
        Set<Integer> checked;
        Set<Integer> singleGroup = new HashSet<>();

        int index = 0;

        for (int i = 0; i < NUMBER_COLUMNS; i++) {
            columns.add(new HashMap<>());
        }
        for (int i = 0; i < data.size(); i++) {
            singleGroup.add(i);
            String[] line = data.get(i).split(DELIMITER);
            for (int j = 0; j < line.length; j++) {
                String key = line[j];
                if (EMPTY_LINE.equals(key)) {
                    continue;
                }
                Map<String, Set<Integer>> map = columns.get(j);
                if (!map.containsKey(key)) {
                    Set<Integer> set = new HashSet<>();
                    set.add(i);
                    map.put(key, set);
                } else {
                    map.get(key).add(i);
                    singleGroup.remove(i);
                }
            }
        }

        for (int i = 0; i < NUMBER_COLUMNS; i++) {
            for (Map.Entry<String, Set<Integer>> e : columns.get(i).entrySet()) {
                if (e.getValue().size() > 1) {
                    same.add(e.getValue());
                }
            }
        }

        List<Set<Integer>> list = new LinkedList<>(same);

        while (list.size() != 0) {
            Set<Integer> added = list.get(0);
            list.remove(0);

            groups.add(index, added);

            for (int j = 0; j < list.size(); j++) {
                checked = list.get(j);
                for (Integer i : checked) {
                    if (added.contains(i)) {
                        groups.get(index).addAll(checked);
                        list.remove(checked);
                        j = -1;
                        break;
                    }
                }
            }
            index++;
        }

        countNotSingleGroup = groups.size();
        groups.sort((o1, o2) -> o2.size() - o1.size());

        for (Integer i : singleGroup) {
            groups.add(Set.of(i));
        }

        return groups;
    }

    void printGroups(List<String> list, List<Set<Integer>> listIndex) throws IOException {

        int count = 1;
        try (PrintWriter printWriter = new PrintWriter(outFile);) {
            printWriter.printf("Всего групп c более, чем 1-м элементом: %d%n", countNotSingleGroup);
            for (Set<Integer> set : listIndex) {
                printWriter.printf("Группа %d%n", count++);
                for (Integer i : set) {
                    printWriter.println(list.get(i));
                }
                printWriter.println();
            }
        }
    }
}

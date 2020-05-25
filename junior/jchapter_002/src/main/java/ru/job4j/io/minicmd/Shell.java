package ru.job4j.io.minicmd;

import java.util.ArrayList;
import java.util.List;

public class Shell {

    private List<String> list = new ArrayList<>();

    public Shell() {
        rootList();
    }

    public Shell cd(final String path) {
        int index;
        String preparedPath = preparePath(path);
        String[] arr;
        if (!"/".equals(preparedPath)) {

            if (preparedPath.startsWith("/")) {
                rootList();
                arr = preparedPath.substring(1).split("/");
                index = 1;
            } else {
                arr = preparedPath.split("/");
                index = list.size();
            }

            for (String s : arr) {
                switch (s) {
                    case ".":
                        break;
                    case "..":
                        if (index > 1) {
                            list.remove(--index);
                        }
                        break;
                    default:
                        list.add(index++, s);
                }
            }
        } else {
            rootList();
        }
        return this;
    }

    public String path() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0 && i != list.size() - 1) {
                sb.append(list.get(i));
                sb.append("/");
            } else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }

    private String preparePath(String str) {
        String result = str.replaceAll("/+", "/");
        return result.length() > 1 && result.endsWith("/") ? result.substring(0, result.length() - 1) : result;
    }

    private void rootList() {
        this.list = new ArrayList<>();
        list.add("/");
    }
}

package ru.job4j.io.minicmd;

import java.util.LinkedList;

public class Shell {

    private final LinkedList<String> list;

    public Shell() {
        this.list = new LinkedList<>();
    }

    public Shell cd(final String path) {
        boolean fromRoot = false;

        if ("/".equals(path)) {
            list.clear();
        } else {
            if (path.startsWith("/")) {
                fromRoot = true;
            }

            String[] arr = preparePath(path).split("/");
            fillStack(arr, fromRoot);
        }
        return this;
    }

    public String path() {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        list.forEach(s -> sb.append(s).append("/"));
        return sb.length() > 1 ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();
    }

    private void fillStack(String[] array, boolean fromRoot) {
        if (fromRoot) {
            list.clear();
        }

        for (String s : array) {
            switch (s) {
                case ".":
                    break;
                case "..":
                    if (list.size() > 0) {
                        list.removeLast();
                    }
                    break;
                default:
                    list.addLast(s);
            }
        }
    }

    private String preparePath(String str) {
        String result = str.replaceAll("/+", "/").replaceFirst("^/", "");
        return result.length() > 1 && result.endsWith("/") ? result.substring(0, result.length() - 1) : result;
    }
}

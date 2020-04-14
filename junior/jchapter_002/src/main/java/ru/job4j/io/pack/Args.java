package ru.job4j.io.pack;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Args {

    private String[] args;

    private final List<String> requeredKeys = List.of("-d", "-o");

    private final List<String> optionalKeys = List.of("-e");

    private String errorMessage = "";

    public Args(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        boolean result = false;
        List<String> list = Arrays.asList(args);

        if (list.containsAll(requeredKeys) && !requeredKeys.contains(args[args.length - 1]) && !optionalKeys.contains(args[args.length - 1])) {
            if (!new File(args[list.indexOf("-d") + 1]).exists()) {
                errorMessage = "Directory for archiving does not exist.";
            } else if ((!new File(new File(args[list.indexOf("-o") + 1]).getParent()).exists())) {
                errorMessage = "Cannot create destination file";
                result = false;
            } else {
                if (!list.stream().filter(s -> s.startsWith("-")).allMatch(s -> (requeredKeys.contains(s)) || optionalKeys.contains(s))) {
                    errorMessage = "Incorrect arguments. Possible arguments: -d, -o, -e";
                    result = false;
                } else {
                    result = true;
                }
            }
        } else {
            errorMessage = "Required parameters not specified: -d, -o are required.";
        }
        return result;
    }

    public String directory() {
        return valid() ? args[Arrays.asList(args).indexOf("-d") + 1] : null;
    }

    public String output() {
        return valid() ? args[Arrays.asList(args).indexOf("-o") + 1] : null;
    }

    public String exclude() {
        return valid() && Arrays.asList(args).containsAll(optionalKeys) ? args[Arrays.asList(args).indexOf("-e") + 1] : "";
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}


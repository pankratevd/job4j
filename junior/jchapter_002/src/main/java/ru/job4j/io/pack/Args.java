package ru.job4j.io.pack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Args {

    private String[] args;

    private final List<String> requiredKeys = List.of("-d", "-o");

    private final List<String> optionalKeys = List.of("-e");

    private final Map<String, String> argumentsMap = new HashMap<>();

    private String errorMessage = "";

    public Args(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        boolean result = true;

        for (int i = 0; i < args.length; i++) {
            if (requiredKeys.contains(args[i]) || optionalKeys.contains(args[i]) && i != args.length - 1) {
                argumentsMap.put(args[i], args[++i]);
            } else {
                errorMessage = "Incorrect arguments. Possible arguments: -d, -o, -e";
                result = false;
                break;
            }
        }

        if (result && !argumentsMap.keySet().containsAll(requiredKeys)) {
            errorMessage = "Required parameters not specified: -d, -o are required.";
            result = false;
        }

        return result;
    }

    public String directory() {
        return argumentsMap.get("-d") != null ? argumentsMap.get("-d") : "";
    }

    public String output() {
        return argumentsMap.get("-o") != null ? argumentsMap.get("-o") : "";
    }

    public String exclude() {
        return argumentsMap.get("-e") != null ? argumentsMap.get("-e") : "";
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}


package ru.job4j.io.find;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class Args {

    private Map<String, String> map = new HashMap<>();
    private final String[] args;
    private final Set<String> requiredKeys = Set.of("-d", "-o", "-n");
    private final Set<String> specifyingKeys = Set.of("-m", "-f", "-r");
    private String specifyingKey = "";
    private boolean isHelp = false;

    public Args(String[] args) {
        this.args = args;
        fillMap();
    }

    public boolean getHelp() {
        if (args.length == 0) {
            isHelp = true;
        }
        return isHelp;
    }

    public boolean valid() {
        return map.size() == 3
                && requiredKeys.stream().allMatch(k -> map.containsKey(k))
                && specifyingKeys.contains(specifyingKey);
    }

    public String directory() {
        return map.get("-d");
    }

    public String filePattern() {
        return map.get("-n");
    }

    public String output() {
        return map.get("-o");
    }

    public String specifyingKey() {
        return specifyingKey;
    }

    public String printHelp() {
        return new StringJoiner(System.lineSeparator())
                .add("-d - start directory")
                .add("-o - output file")
                .add("-n - search pattern")
                .add("[-r, -m, -f] - search option: -r - regex, -m  - mask, -f - full name").toString();
    }

    private void fillMap() {
        for (int i = 0; i < args.length; i++) {
            if (specifyingKeys.contains(args[i])) {
                specifyingKey = args[i];
                continue;
            }
            if (requiredKeys.contains(args[i]) && i < args.length - 1) {
                map.put(args[i], args[++i]);
            }
        }
    }
}

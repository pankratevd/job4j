package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path))) {
            bufferedReader.lines()
                    .filter(l -> !(l.trim().startsWith("#") || l.trim().startsWith("!") || l.isEmpty()))
                    .forEach(l -> {
                        String[] strArray = l.split("=");
                        try {
                            values.put(strArray[0].trim(), strArray[1].trim());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}

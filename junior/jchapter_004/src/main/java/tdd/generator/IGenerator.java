package tdd.generator;

import java.util.Map;

public interface IGenerator {
    String produce(String template, Map<String, String> args);
}

package tdd;

import org.junit.Ignore;
import org.junit.Test;
import tdd.generator.Generator;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleGeneratorTest {

    @Ignore
    @Test
    public void produceWhenAllCorrect() {
        Generator generator = new Generator();
        String template = "I am a ${name}, Who are ${subject}? ";

        Map<String, String> map = new HashMap<>();
        map.put("name ", "Petr");
        map.put("subject", "you");

        String result = generator.produce(template, map);
        String expected = "I am a Petr, Who are you? ";

        assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = Exception.class)
    public void produceWhenExtraKeyThenException() {
        Generator generator = new Generator();
        String template = "I am a ${name}, Who are ${subject}? ";

        Map<String, String> map = new HashMap<>();
        map.put("name ", "Petr");
        map.put("subject", "you");
        map.put("extraKey", "Value");

        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = Exception.class)
    public void produceWhenIncorrectKeyThenException() {
        Generator generator = new Generator();
        String template = "I am a ${name}, Who are ${you}? ${incorrectKey}";

        Map<String, String> map = new HashMap<>();
        map.put("name ", "Petr");
        map.put("subject", "you");

        generator.produce(template, map);
    }
}




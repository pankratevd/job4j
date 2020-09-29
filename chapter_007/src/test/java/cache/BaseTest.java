package cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BaseTest {
    @Test
    public void whenChangeNameThenChangeVersion() {
        Base base = new Base(1, "1");

        for (int i = 1; i < 100; i++) {
            base.setName(String.valueOf(i));
            assertThat(i, is(base.getVersion()));
        }
    }


}
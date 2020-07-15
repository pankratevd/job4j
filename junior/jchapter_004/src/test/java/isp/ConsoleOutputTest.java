package isp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleOutputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void output() {
        IBaseMenu menu = new SimpleMenu(new ConsoleInput(), new ConsoleOutput());

        IMenuItem item1 = new SimpleItem("Задача 1.", System.out::println);
        IMenuItem item11 = new SimpleItem("Задача 1.1.", item1, System.out::println);
        IMenuItem item2 = new SimpleItem("Задача 2.", System.out::println);

        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        menu.addMenuItem(item11);

        ConsoleOutput output = new ConsoleOutput();
        String expected = String.join(System.lineSeparator(), "Задача 1.", "-- Задача 1.1.", "Задача 2.", "");


        output.output(menu);

        assertThat((outContent.toString()), is(expected));


    }
}
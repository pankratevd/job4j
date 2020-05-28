package ru.job4j.io.minicmd;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShellTest {

    @Test
    public void whenCreateShellThenRoot() {
        Shell shell = new Shell();
        String expected = "/";
        assertThat(expected, is(shell.path()));
    }

    @Test
    public void whenUpFromRootThenRoot() {
        Shell shell = new Shell();
        shell.cd("../usr/../var//..");
        String expected = "/";
        assertThat(expected, is(shell.path()));
    }

    @Test
    public void whenUpFromRootWithoutDots() {
        Shell shell = new Shell();
        shell.cd("/usr/log");
        String expected = "/usr/log";
        assertThat(expected, is(shell.path()));
    }

    @Test
    public void whenCdFromCurrentDirectory() {
        Shell shell = new Shell();
        shell.cd("usr");
        shell.cd("bin");
        String expected = "/usr/bin";
        assertThat(expected, is(shell.path()));
    }

    @Test
    public void whenUpFromRootUpWithDotsThenRoot() {
        Shell shell = new Shell();
        shell.cd("/");
        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        String expected = "/usr/local";
        assertThat(expected, is(shell.path()));
    }

    @Test
    public void whenOneStepBack() {
        Shell shell = new Shell();
        shell.cd("/usr/bin/..");
        String expected = "/usr";
        assertThat(expected, is(shell.path()));
    }

    @Test
    public void whenDotInCurrentRootThenRoot() {
        Shell shell = new Shell();
        shell.cd(".");
        String expected = "/";
        assertThat(expected, is(shell.path()));
    }
}

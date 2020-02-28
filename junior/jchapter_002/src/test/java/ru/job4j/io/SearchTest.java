package ru.job4j.io;

public class SearchTest {

 /*   @Test
    public void whenFilesExist() throws NotDirectoryException {
        String path = System.getProperty("java.io.tmpdir") + "test";
        List<String> exts = List.of("exe", "log");
        Search search = new Search();
        List<File> result = search.files(path, exts);
        List<File> expected = List.of(new File("parent.log"), new File("1_double.exe"), new File("1_double.exe"), new File("2_inner.log"));
        assertThat(result, containsInAnyOrder(expected.toArray()));

    }

    @Test
    public void whenFilesDoNotExist() throws NotDirectoryException {
        String path = System.getProperty("java.io.tmpdir") + "test";
        List<String> exts = List.of("tmp");
        Search search = new Search();
        List<File> result = search.files(path, exts);
        assertTrue(result.isEmpty());
    }

    @Test (expected = NotDirectoryException.class)
    public void whenDirectoryDosNotExist() throws NotDirectoryException {
        String path = "notExist";
        List<String> exts = List.of("tmp");
        Search search = new Search();
        List<File> result = search.files(path, exts);
    }*/
}

package ru.job4j.io.find;

public class FindTest {
 /*
    @ClassRule
    public static TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setUp() throws IOException {
        folder.newFolder("dir1");
        folder.newFolder("dir2");

        List<String> list = List.of("/file.txt", "/file1.txt", "/dir1/file1._in_dir1.txt", "/dir2/file_reg_1.txt", "/file_reg_2.txt", "/file_reg_3.txt", "/dir1/file.csv", "/dir1/file.txt");
        list.forEach(f -> {
            try {
                folder.newFile(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void whenFindByMask() throws IOException {
        String[] args = {"-d", folder.getRoot().toString(), "-o", folder.getRoot() + "/out.txt", "-n", "*.csv", "-m"};
        Args args1 = new Args(args);
        assertTrue(args1.valid());
        Find find = new Find(args1);

        try (PrintWriter printWriter = new PrintWriter(new File(folder.getRoot() + "/expected_out.txt"))) {
            printWriter.println(folder.getRoot() + "\\dir1\\file.csv");
        }


        find.process();

        assertEquals("The files is differ", FileUtils.readFileToString(new File(folder.getRoot() + "/expected_out.txt"), "utf-8"),
                FileUtils.readFileToString(new File(folder.getRoot() + "/out.txt"), "utf-8"));


    }

    @Test
    public void whenFindByFullName() throws IOException {
        String[] args = {"-d", folder.getRoot().toString(), "-o", folder.getRoot() + "/out.txt", "-n", "file.txt", "-f"};
        Args args1 = new Args(args);
        assertTrue(args1.valid());
        Find find = new Find(args1);

        try (PrintWriter printWriter = new PrintWriter(new File(folder.getRoot() + "/expected_out.txt"))) {
            printWriter.println(folder.getRoot() + "\\dir1\\file.txt");
            printWriter.println(folder.getRoot() + "\\file.txt");
        }


        find.process();

        assertEquals("The files is differ", FileUtils.readFileToString(new File(folder.getRoot() + "/expected_out.txt"), "utf-8"),
                FileUtils.readFileToString(new File(folder.getRoot() + "/out.txt"), "utf-8"));

    }

    @Test
    public void whenFindByRegEx() throws IOException {
        String[] args = {"-d", folder.getRoot().toString(), "-o", folder.getRoot() + "/out.txt", "-n", "file_reg_[1|2].txt", "-r"};
        Args args1 = new Args(args);
        assertTrue(args1.valid());
        Find find = new Find(args1);

        try (PrintWriter printWriter = new PrintWriter(new File(folder.getRoot() + "/expected_out.txt"))) {
            printWriter.println(folder.getRoot() + "\\dir2\\file_reg_1.txt");
            printWriter.println(folder.getRoot() + "\\file_reg_2.txt");
        }


        find.process();

        assertEquals("The files is differ", FileUtils.readFileToString(new File(folder.getRoot() + "/expected_out.txt"), "utf-8"),
                FileUtils.readFileToString(new File(folder.getRoot() + "/out.txt"), "utf-8"));

    }

  */

}

package ru.job4j.io.chat;

public class ChatTest {
 /*   @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void chatTest() throws IOException {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        InputStream stdin = System.in;

        File expectedFile = folder.newFile(folder + "expectedfile.txt");
        File outFile = folder.newFile(folder + "logfile.txt");
        File phrasesFile = folder.newFile(folder + "phrases.txt");


        try (PrintWriter phrasesFileWriter = new PrintWriter(phrasesFile, StandardCharsets.UTF_8);
             PrintWriter expectedFileWriter = new PrintWriter(expectedFile, StandardCharsets.UTF_8)) {
            phrasesFileWriter.println("Фраза1.");

       *//*    expectedFileWriter.println("старт");
            expectedFileWriter.println("Фраза1."); *//*
            expectedFileWriter.println("стоп");
            expectedFileWriter.println("ввод");
            expectedFileWriter.println("продолжить");
            expectedFileWriter.println("закончить");
        }

        Chat chat = new Chat(phrasesFile.getCanonicalPath(), outFile.getCanonicalPath());

        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator())
//                .add("старт")
                .add("стоп")
                .add("ввод")
                .add("продолжить")
                .add("закончить");

        ByteArrayInputStream in = new ByteArrayInputStream(stringJoiner.toString().getBytes());
        System.setIn(in);
        chat.chat();
        System.setIn(stdin);

        assertEquals(FileUtils.readFileToString(expectedFile, "utf-8"),
                FileUtils.readFileToString(outFile, "utf-8"));

    }*/

}

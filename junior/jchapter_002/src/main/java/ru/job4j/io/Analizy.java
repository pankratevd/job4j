package ru.job4j.io;

import java.io.*;
import java.util.Set;

public class Analizy {
    private final Set<String> errorCodes = Set.of("400", "500");

    public void unavailable(String source, String target) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    line = bufferedReader.readLine();
                    continue;
                }

                String[] lineArray = line.split(" ");

                if (checkErrorCode(lineArray[0])) {
                    out.print(lineArray[1] + ";");
                    while ((line.isEmpty() || checkErrorCode(lineArray[0]))) {
                        line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        lineArray = line.split(" ");
                    }
                    if (line == null) {
                        break;
                    }
                    out.println(lineArray[1] + ";");
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkErrorCode(String s) {
        return errorCodes.contains(s);
    }
}

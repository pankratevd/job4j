package ru.job4j.io.pack;

public class Args {

    private String directory;

    private String output;

    private String exclude = "";

    public void initialize(String[] arr) throws IllegalArgumentException {
        try {
            if (arr.length == 6) {
                for (int i = 0; i != 6; i++) {
                    switch (arr[i]) {
                        case "-o":
                            output = arr[++i];
                            break;

                        case "-e":
                            exclude = arr[++i];
                            break;

                        case "-d":
                            directory = arr[++i];
                            break;

                        default:
                            throw new IllegalArgumentException("Incorrect arguments");
                    }
                }
            } else if (arr.length == 4) {
                    for (int i = 0; i != 4; i++) {
                        switch (arr[i]) {
                            case "-o":
                                output = arr[++i];
                                break;

                            case "-d":
                                directory = arr[++i];
                                break;

                            default:
                                throw new IllegalArgumentException("Incorrect arguments");
                    }
                }
            } else throw new IllegalArgumentException("Incorrect arguments");


        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
    }

    public String directory() {
        return this.directory;
    }

    public String output() {
        return this.output;
    }

    public String exclude() {
        return this.exclude;
    }
}


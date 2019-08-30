package ru.job4j.profession;

public class Work {
    String description;
    Builder builder;
    Construction construction;
    boolean isComplete;

    public String getDescription() {
        return description;
    }

    public Builder getBuilder() {
        return builder;
    }

    public Construction getConstruction() {
        return construction;
    }

    public boolean isComplete() {
        return isComplete;
    }
}

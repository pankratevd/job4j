package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public void put(Task task) {
        var index = 0;
        if (tasks.size() == 0) {
            tasks.add(task);
            return;
        }

        while (index < tasks.size() && tasks.get(index).getPriority() < task.getPriority()) {
            index++;
        }
        tasks.add(index, task);

    }

    public Task take() {
        return this.tasks.poll();
    }
}
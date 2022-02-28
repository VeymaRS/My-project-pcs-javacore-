package ru.netology.javacore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Todos {
    private List<String> tasksList = new ArrayList<>();
    private boolean taskStatus = true;

    public void addTask(String task) {
        tasksList.add(task);
        System.out.println("Addition done");
    }

    public void removeTask(String task) {
            if (tasksList.contains(task)) {
                tasksList.remove(task);
            } else {
                this.taskStatus = false;
                System.out.println("Unknown task");
            }
        }

    public String getAllTasks() {
        return tasksList.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(" "));
    }

    public List<String> getTasksList() {
        return tasksList;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }
}

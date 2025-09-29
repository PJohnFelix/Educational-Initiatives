package com.astronaut.schedule.manager;

import com.astronaut.schedule.model.Task;
import com.astronaut.schedule.observer.TaskConflictObserver;
import com.astronaut.schedule.util.TaskOperationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
    
    private ScheduleManager() {} 
    
    private static class SingletonHelper {
        private static final ScheduleManager INSTANCE = new ScheduleManager();
    }
    
    public static ScheduleManager getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    private final List<Task> tasks = new ArrayList<>();
    private final List<TaskConflictObserver> observers = new ArrayList<>();

    public void addObserver(TaskConflictObserver observer) {
        observers.add(observer);
    }
    
    private void notifyConflict(Task newTask, Task existingTask) {
        for (TaskConflictObserver observer : observers) {
            observer.onConflictDetected(newTask, existingTask);
        }
    }
    
    public void addTask(Task newTask) throws TaskOperationException {
        Task conflictingTask = findConflict(newTask);
        if (conflictingTask != null) {
            notifyConflict(newTask, conflictingTask);
            throw new TaskOperationException("Task conflicts with existing task: \"" + conflictingTask.getDescription() + "\"");
        }
        
        tasks.add(newTask);
        tasks.sort(Comparator.comparing(Task::getStartTime)); 
    }

    private Task findConflict(Task newTask) {
        for (Task existingTask : tasks) {
            if (newTask.overlapsWith(existingTask)) {
                return existingTask;
            }
        }
        return null;
    }

    public void removeTask(String description) throws TaskOperationException {
        boolean removed = tasks.removeIf(task -> task.getDescription().equalsIgnoreCase(description));
        if (!removed) {
            throw new TaskOperationException("Task not found.");
        }
    }

    public List<Task> viewTasks() {
        return Collections.unmodifiableList(tasks); 
    }
}
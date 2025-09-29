package com.astronaut.schedule.factory;

import com.astronaut.schedule.model.Task;
import com.astronaut.schedule.util.InvalidTaskException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static Task createTask(String description, String startTimeStr, String endTimeStr, String priorityStr) 
            throws InvalidTaskException {
        
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidTaskException("Task description cannot be empty.");
        }
        
        LocalTime startTime;
        LocalTime endTime;
        try {
            startTime = LocalTime.parse(startTimeStr.trim(), TIME_FORMATTER);
            endTime = LocalTime.parse(endTimeStr.trim(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("Invalid time format. Please use HH:mm.");
        }
        
        if (!startTime.isBefore(endTime)) {
            throw new InvalidTaskException("Start time must be before end time.");
        }
        
        Task.Priority priority;
        try {
            priority = Task.Priority.valueOf(priorityStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTaskException("Invalid priority level. Use HIGH, MEDIUM, or LOW.");
        }

        return new Task(description.trim(), startTime, endTime, priority);
    }
}
package com.astronaut.schedule.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;

    public enum Priority {
        HIGH, MEDIUM, LOW
    }
    
    public Task(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean overlapsWith(Task other) {
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%s-%s: %s [%s]",
                startTime.format(formatter),
                endTime.format(formatter),
                description,
                priority.name());
    }
}
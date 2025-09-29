// com.astronaut.schedule.observer.TaskConflictObserver.java
package com.astronaut.schedule.observer;

import com.astronaut.schedule.model.Task;

public interface TaskConflictObserver {
    void onConflictDetected(Task newTask, Task existingTask);
}

// com.astronaut.schedule.observer.ConsoleUser.java
package com.astronaut.schedule.observer;

import com.astronaut.schedule.model.Task;
import com.astronaut.schedule.util.AstronautLogger;

public class ConsoleUser implements TaskConflictObserver {
    @Override
    public void onConflictDetected(Task newTask, Task existingTask) {
        AstronautLogger.info("UI ALERT: Conflict detected! " + newTask.getDescription() + 
                            " overlaps with " + existingTask.getDescription());
    }
}
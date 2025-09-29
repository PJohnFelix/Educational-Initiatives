// com.astronaut.schedule.util.AstronautLogger.java
package com.astronaut.schedule.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AstronautLogger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        System.out.println(FORMATTER.format(LocalDateTime.now()) + " [INFO] " + message);
    }

    public static void error(String message) {
        System.err.println(FORMATTER.format(LocalDateTime.now()) + " [ERROR] " + message);
    }
}

// com.astronaut.schedule.util.TaskOperationException.java
package com.astronaut.schedule.util;

public class TaskOperationException extends RuntimeException {
    public TaskOperationException(String message) {
        super(message);
    }
}

// com.astronaut.schedule.util.InvalidTaskException.java
package com.astronaut.schedule.util;

public class InvalidTaskException extends RuntimeException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
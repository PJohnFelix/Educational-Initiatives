package com.astronaut.schedule;

import com.astronaut.schedule.factory.TaskFactory;
import com.astronaut.schedule.manager.ScheduleManager;
import com.astronaut.schedule.model.Task;
import com.astronaut.schedule.observer.ConsoleUser;
import com.astronaut.schedule.util.AstronautLogger;
import com.astronaut.schedule.util.InvalidTaskException;
import com.astronaut.schedule.util.TaskOperationException;

import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConsoleUser());
        
        Scanner scanner = new Scanner(System.in);
        String command = "";
        
        AstronautLogger.info("Astronaut Daily Schedule Organizer started. Type 'MENU' for options.");

        while (!command.equalsIgnoreCase("EXIT")) { 
            try {
                System.out.print("\n> Enter command (e.g., ADD, REMOVE, VIEW, EXIT): ");
                command = scanner.nextLine().trim();
                
                String[] parts = command.split(" ", 2);
                String action = parts[0].toUpperCase();
                String argument = parts.length > 1 ? parts[1] : "";

                switch (action) {
                    case "ADD":
                        handleAdd(manager, argument);
                        break;
                    case "REMOVE":
                        handleRemove(manager, argument);
                        break;
                    case "VIEW":
                        handleView(manager);
                        break;
                    case "MENU":
                        displayMenu();
                        break;
                    case "EXIT":
                        AstronautLogger.info("Application shutting down.");
                        break;
                    default:
                        AstronautLogger.error("Unknown command. Type 'MENU' for options.");
                }
            } catch (Exception e) {
                AstronautLogger.error("An unexpected system error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("ADD [desc],[start HH:mm],[end HH:mm],[priority] - e.g. ADD Morning Exercise,07:00,08:00,HIGH");
        System.out.println("REMOVE [desc] - e.g. REMOVE Morning Exercise");
        System.out.println("VIEW - View all tasks sorted by start time.");
        System.out.println("EXIT - Terminate the application.");
        System.out.println("------------");
    }

    private static void handleAdd(ScheduleManager manager, String argument) {
        try {
            String[] args = argument.split(",");
            if (args.length != 4) {
                AstronautLogger.error("Invalid arguments. Format: Description,07:00,08:00,HIGH");
                return;
            }
            
            Task newTask = TaskFactory.createTask(args[0], args[1], args[2], args[3]);
            manager.addTask(newTask);
            
            AstronautLogger.info("Task added successfully. No conflicts."); 
        } catch (InvalidTaskException | TaskOperationException e) {
            AstronautLogger.error("Operation failed: " + e.getMessage()); 
        }
    }
    
    private static void handleRemove(ScheduleManager manager, String argument) {
        try {
            manager.removeTask(argument);
            AstronautLogger.info("Task removed successfully."); 
        } catch (TaskOperationException e) {
            AstronautLogger.error("Operation failed: " + e.getMessage()); 
        }
    }

    private static void handleView(ScheduleManager manager) {
        List<Task> tasks = manager.viewTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day."); 
            return;
        }
        
        System.out.println("\n--- Daily Schedule (Sorted by Time) ---");
        tasks.forEach(task -> System.out.println("* " + task));
        System.out.println("-------------------------------------");
    }
}
Exercise 2 - Astronaut Daily Schedule Organizer
Project Title: Astronaut Daily Schedule Organizer (CRUD Application)
Overview and Compliance
This project is the solution for 

Exercise 2, Problem Statement 1.


It is implemented as a simple, 

console/terminal based application.

The focus is 

ONLY on logic and code quality, adopting design patterns and SOLID principles.

The program is designed to 

run for a long time gathering inputs from users.

Core Architecture and Pattern Implementation
Singleton Pattern (ScheduleManager):

The 

ScheduleManager is the core state container, implemented as a thread-safe Singleton to ensure a single, consistent schedule instance.


Factory Pattern (TaskFactory):

Used to create 

Task objects, centralizing and validating input arguments (description, time format, priority) before instantiation. This enforces 


validations at all levels.


Observer Pattern (TaskConflictObserver):

Used to 

alert users if a new task conflicts with an existing one. The 


ScheduleManager (Subject) triggers the notification, and the ConsoleUser (Observer) prints the alert.

Mandatory Functional Requirements (Mandated)

CRUD: Allows users to add, remove, and view daily tasks.


Validation: New tasks must not overlap with existing tasks.


Viewing: View all tasks sorted by start time.


Error Handling: Provides appropriate error messages for invalid operations (e.g., conflicts, invalid time format).



Gold Standards Implemented

Logging: A simple AstronautLogger class implements a centralized logging mechanism for tracking application usage and errors.




Exception Handling: Custom runtime exceptions (TaskOperationException, InvalidTaskException) are used to handle business logic failures and input errors gracefully.




Performance: The internal task list is kept sorted by the addTask logic to ensure that task viewing is efficient and the overlap check is optimized.



Program Control: The main loop uses a mutable flag (while (!command.equalsIgnoreCase("EXIT"))) to avoid hard coding of boolean flags like while(true).

How to Run the Application
The application starts by executing the Main class.

Example Commands: Use ADD [description],[start HH:mm],[end HH:mm],[priority], REMOVE [description], VIEW, and EXIT.
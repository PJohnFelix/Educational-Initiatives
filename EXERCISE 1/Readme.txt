Exercise 1 - Design Patterns Demonstration
Project Title: Design Patterns (Behavioral, Creational, Structural) Implementation in Java
Overview and Compliance
This project serves as the solution for 

Exercise 1, demonstrating six distinct design patterns in Java.


The focus is on adherence to 

OOPs programming, SOLID principles, and global best practices (e.g., each class in a separate file).


The application is a simple console-based demonstration executed via Main.java.

Architecture & Design Patterns Implemented
1. Creational Patterns
Singleton Pattern (ConfigurationManager):

Use Case: Centralized Configuration Manager.


Implementation: Uses the Bill Pugh Implementation for guaranteed thread-safe, lazy instantiation, ensuring the single instance is highly optimized for performance.


Factory Pattern (ModelFactory):

Use Case: AI Model Generator for EdTech (creating QuizGeneratorModel, ContentSummarizerModel).


Implementation: Encapsulates object creation to adhere to the Open/Closed Principle (OCP), allowing new model types to be introduced easily.

2. Behavioral Patterns
Command Pattern (CommandInvoker):

Use Case: Batch File Operation Executor (e.g., RenameCommand, DeleteCommand).


Implementation: Stores actions in a history stack, enabling a clear undo/redo functionality (serving as the transient error handling mechanism).



Observer Pattern (TelemetryStation):

Use Case: Satellite Sensor Data Monitoring.


Implementation: The TelemetryStation (Subject) notifies multiple dependents (MissionControl, MaintenanceCrew Observers) upon detecting a CRITICAL state change, ensuring loose coupling.

3. Structural Patterns
Adapter Pattern (LegacySensorAdapter):

Use Case: Legacy Weather Data Integration.

Implementation: Acts as a bridge, converting the incompatible interface of a mock LegacySensorAPI (XML format) into the standard WeatherDataFetcher interface expected by the client.

Composite Pattern (Assembly):

Use Case: Rocket Assembly Structure.


Implementation: Allows components (IndividualPart) and compositions (Assembly) to be treated uniformly under the RocketComponent interface, simplifying recursive operations like total mass calculation.

How to Run the Demonstration
Clone the repository and compile all Java files.

Execute the Main class to run the sequential demonstration and verify the output of all six patterns.

 
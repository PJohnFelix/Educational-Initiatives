// File: src/Main.java
import patterns.behavioral.command.*;
import patterns.behavioral.observer.*;
import patterns.creational.factory.*;
import patterns.creational.singleton.*;
import patterns.structural.adapter.*;
import patterns.structural.composite.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Exercise 1: Design Patterns Demonstration ---");
        
        // 1. CREATIONAL PATTERNS
        System.out.println("\n--- 1. Creational Patterns (Factory, Singleton) ---");
        
        // Singleton Demo: ConfigurationManager
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("Singleton Config URL: " + config1.getDatabaseUrl());
        System.out.println("Are config1 and config2 the same instance? " + (config1 == config2)); 

        // Factory Demo: AI Model Generator
        AIModel quizModel = ModelFactory.createModel("QUIZ");
        AIModel summaryModel = ModelFactory.createModel("SUMMARY");
        System.out.println("Factory 1 Output: " + quizModel.process("Electromagnetism"));
        System.out.println("Factory 2 Output: " + summaryModel.process("Quantum Physics Text"));
        
        // 2. BEHAVIORAL PATTERNS
        System.out.println("\n--- 2. Behavioral Patterns (Command, Observer) ---");
        
        // Command Demo (with Undo)
        FileReceiver receiver = new FileReceiver();
        CommandInvoker invoker = new CommandInvoker();
        FileCommand cmd1 = new RenameCommand(receiver, "Draft.txt", "Final.txt");
        FileCommand cmd2 = new DeleteCommand(receiver, "Logs.log");
        
        invoker.executeCommand(cmd1);
        System.out.println("Command Executed: " + receiver.getLog());
        
        invoker.executeCommand(cmd2);
        System.out.println("Command Executed: " + receiver.getLog());
        
        invoker.undoLast();
        System.out.println("Command Undone (Transient Error Handling): " + receiver.getLog());
        
        // Observer Demo
        TelemetryStation station = new TelemetryStation();
        MissionControl mc = new MissionControl();
        MaintenanceCrew crew = new MaintenanceCrew();
        
        station.registerObserver(mc);
        station.registerObserver(crew);
        
        System.out.println("--- Observer notifications triggered by state change ---");
        station.setSensorData("Temp: 85C, Status: NORMAL"); 
        station.setSensorData("Temp: 150C, Status: CRITICAL OVERHEAT"); 
        
        // 3. STRUCTURAL PATTERNS
        System.out.println("\n--- 3. Structural Patterns (Adapter, Composite) ---");

        // Adapter Demo
        LegacySensorAPI legacyApi = new LegacySensorAPI();
        WeatherDataFetcher adapter = new LegacySensorAdapter(legacyApi);
        System.out.println("Adapter Output: " + adapter.getFormattedWeatherReport("Mars Base"));

        // Composite Demo
        IndividualPart engine = new IndividualPart("Main Engine", 5000.0);
        IndividualPart tank = new IndividualPart("Fuel Tank", 10000.0);
        
        Assembly stage1 = new Assembly("Stage 1");
        stage1.addComponent(engine);
        stage1.addComponent(tank);
        
        IndividualPart payload = new IndividualPart("Satellite Payload", 2000.0);
        
        Assembly rocket = new Assembly("Atlas V Rocket");
        rocket.addComponent(stage1);
        rocket.addComponent(payload);
        
        System.out.println(rocket.preFlightCheck());
        System.out.println("Total Rocket Mass: " + rocket.getMass() + " kg");
    }
}
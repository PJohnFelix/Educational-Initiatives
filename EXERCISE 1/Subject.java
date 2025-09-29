// File: src/patterns/behavioral/observer/Subject.java
package patterns.behavioral.observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// File: src/patterns/behavioral/observer/Observer.java
package patterns.behavioral.observer;

public interface Observer {
    void update(String data);
}

// File: src/patterns/behavioral/observer/TelemetryStation.java
package patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class TelemetryStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String sensorData;
    
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(sensorData);
        }
    }

    public void setSensorData(String data) {
        this.sensorData = data;
        if (data.contains("CRITICAL")) {
            notifyObservers();
        }
    }
}

// File: src/patterns/behavioral/observer/MissionControl.java
package patterns.behavioral.observer;

public class MissionControl implements Observer {
    @Override
    public void update(String data) {
        System.out.println("Mission Control: Received CRITICAL alert! Processing: " + data);
    }
}

// File: src/patterns/behavioral/observer/MaintenanceCrew.java
package patterns.behavioral.observer;

public class MaintenanceCrew implements Observer {
    @Override
    public void update(String data) {
        System.out.println("Maintenance Crew: Logging anomaly: " + data);
    }
}
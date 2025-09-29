// File: src/patterns/structural/composite/RocketComponent.java
package patterns.structural.composite;

public interface RocketComponent {
    double getMass();
    String preFlightCheck();
}

// File: src/patterns/structural/composite/IndividualPart.java
package patterns.structural.composite;

public class IndividualPart implements RocketComponent {
    private String name;
    private double mass;
    
    public IndividualPart(String name, double mass) {
        this.name = name;
        this.mass = mass;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public String preFlightCheck() {
        return name + " check: OK";
    }
}

// File: src/patterns/structural/composite/Assembly.java
package patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Assembly implements RocketComponent {
    private String name;
    private List<RocketComponent> components = new ArrayList<>();
    
    public Assembly(String name) {
        this.name = name;
    }

    public void addComponent(RocketComponent component) {
        components.add(component);
    }

    @Override
    public double getMass() {
        double totalMass = 0;
        for (RocketComponent component : components) {
            totalMass += component.getMass();
        }
        return totalMass;
    }

    @Override
    public String preFlightCheck() {
        StringBuilder report = new StringBuilder(name + " ASSEMBLY CHECK:\n");
        for (RocketComponent component : components) {
            report.append("  - ").append(component.preFlightCheck()).append("\n");
        }
        return report.toString();
    }
}
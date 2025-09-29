// File: src/patterns/structural/adapter/WeatherDataFetcher.java
package patterns.structural.adapter;

public interface WeatherDataFetcher {
    String getFormattedWeatherReport(String location);
}

// File: src/patterns/structural/adapter/LegacySensorAPI.java
package patterns.structural.adapter;

public class LegacySensorAPI {
    public String readRawXmlData(String city) {
        return "<data><city>" + city + "</city><temp>25C</temp><humid>60%</humid></data>";
    }
}

// File: src/patterns/structural/adapter/LegacySensorAdapter.java
package patterns.structural.adapter;

public class LegacySensorAdapter implements WeatherDataFetcher {
    private LegacySensorAPI legacySensor;
    
    public LegacySensorAdapter(LegacySensorAPI legacySensor) {
        this.legacySensor = legacySensor;
    }

    @Override
    public String getFormattedWeatherReport(String location) {
        String rawXml = legacySensor.readRawXmlData(location);
        
        String temp = rawXml.substring(rawXml.indexOf("<temp>") + 6, rawXml.indexOf("</temp>"));
        String humid = rawXml.substring(rawXml.indexOf("<humid>") + 7, rawXml.indexOf("</humid>"));
        
        return "Current Weather for " + location + ": Temperature " + temp + ", Humidity " + humid;
    }
}

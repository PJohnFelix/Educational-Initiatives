// File: src/patterns/creational/singleton/ConfigurationManager.java
package patterns.creational.singleton;

public class ConfigurationManager {
    private String databaseUrl;
    
    private ConfigurationManager() {
        this.databaseUrl = "jdbc:mysql://localhost:3306/prod_db_v1";
    }
    
    private static class SingletonHelper {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }
    
    public static ConfigurationManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}
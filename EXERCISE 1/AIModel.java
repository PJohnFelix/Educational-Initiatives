// File: src/patterns/creational/factory/AIModel.java
package patterns.creational.factory;

public interface AIModel {
    String process(String input);
}

// File: src/patterns/creational/factory/QuizGeneratorModel.java
package patterns.creational.factory;

public class QuizGeneratorModel implements AIModel {
    @Override
    public String process(String input) {
        return "Generating 5 questions based on: " + input;
    }
}

// File: src/patterns/creational/factory/ContentSummarizerModel.java
package patterns.creational.factory;

public class ContentSummarizerModel implements AIModel {
    @Override
    public String process(String input) {
        return "Summarizing content, length reduction by 50%.";
    }
}

// File: src/patterns/creational/factory/ModelFactory.java
package patterns.creational.factory;

public class ModelFactory {
    public static AIModel createModel(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("QUIZ")) {
            return new QuizGeneratorModel();
        } else if (type.equalsIgnoreCase("SUMMARY")) {
            return new ContentSummarizerModel();
        }
        return null;
    }
}
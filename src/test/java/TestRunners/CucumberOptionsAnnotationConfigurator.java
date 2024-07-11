package TestRunners;

import java.util.ArrayList;
import java.util.List;

public class CucumberOptionsAnnotationConfigurator {
    private static List<String> features = new ArrayList<>();
    private static List<String> glue = new ArrayList<>();

    public static void setFeatures(String[] featurePaths) {
        features.clear();
        for (String feature : featurePaths) {
            features.add(feature);
        }
    }

    public static void setGlue(String[] gluePaths) {
        glue.clear();
        for (String g : gluePaths) {
            glue.add(g);
        }
    }

    public static List<String> getFeatures() {
        return features;
    }

    public static List<String> getGlue() {
        return glue;
    }
}

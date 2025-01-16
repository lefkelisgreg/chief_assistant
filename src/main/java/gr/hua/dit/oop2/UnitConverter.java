package gr.hua.dit.oop2;

import java.util.HashMap;
import java.util.Map;

public class UnitConverter {

    private static final Map<String, Integer> weightConversions = new HashMap<>();
    private static final Map<String, Integer> liquidConversions = new HashMap<>();
    private static final Map<String, Integer> timeConversions = new HashMap<>();

    static {
        //  Μετατροπές για βάρη
        weightConversions.put("kg", 1000); // 1 κιλό = 1000 γραμμάρια
        weightConversions.put("gr", 1);   // 1 γραμμάριο = 1 γραμμάριο

        //  Μετατροπές για υγρά
        liquidConversions.put("l", 1000); // 1 λίτρο = 1000 ml
        liquidConversions.put("ml", 1);   // 1 ml = 1 ml

        // Μετατροπές για χρόνο
        timeConversions.put("hours", 60);   // 1 ώρα = 60 λεπτά
        timeConversions.put("minutes", 1); // 1 λεπτό = 1 λεπτό
    }

    // Μετατροπή βάρους
    public static int convertWeight(String fromUnit, String toUnit, int value) {
        if (!weightConversions.containsKey(fromUnit) || !weightConversions.containsKey(toUnit)) {
            throw new IllegalArgumentException("Unsupported weight units: " + fromUnit + ", " + toUnit);
        }
        int fromFactor = weightConversions.get(fromUnit);
        int toFactor = weightConversions.get(toUnit);
        return (value * fromFactor) / toFactor;
    }

    //  Mετατροπή για λίτρα
    public static int convertLiquid(String fromUnit, String toUnit, int value) {
        if (!liquidConversions.containsKey(fromUnit) || !liquidConversions.containsKey(toUnit)) {
            throw new IllegalArgumentException("Unsupported liquid units: " + fromUnit + ", " + toUnit);
        }
        int fromFactor = liquidConversions.get(fromUnit);
        int toFactor = liquidConversions.get(toUnit);
        return (value * fromFactor) / toFactor;
    }

    // Μετατροπή χρόνου
    public static int convertTime(String fromUnit, String toUnit, int value) {
        if (!timeConversions.containsKey(fromUnit) || !timeConversions.containsKey(toUnit)) {
            throw new IllegalArgumentException("Unsupported time units: " + fromUnit + ", " + toUnit);
        }
        int fromFactor = timeConversions.get(fromUnit);
        int toFactor = timeConversions.get(toUnit);
        return (value * fromFactor) / toFactor;
    }
}

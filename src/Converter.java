public class Converter {
    //    в метрах
    private final static double STEP_LENGTH = 0.75;
    //    в каллориях
    private final static double CALORIE_COUNT = 50;

    public static double convertStepsToDistance(int steps) {
        return (steps * STEP_LENGTH / 1000);
    }

    public static double convertStepsToCalories(int steps) {
        return (steps * CALORIE_COUNT / 1000);
    }
}

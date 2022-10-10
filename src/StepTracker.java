import java.util.Arrays;

public class StepTracker {
    private final int[][] stepCount = new int[Constants.MONTH_COUNT][Constants.DAYS_COUNT];

    private int goal;

    private final int[] maxCount = new int[Constants.MONTH_COUNT];
    private final int[] sumCount = new int[Constants.MONTH_COUNT];

    public void init() {
        for (int i = 0; i < Constants.MONTH_COUNT; i++) {
            Arrays.fill(stepCount[i], 0);
        }
        Arrays.fill(maxCount, 0);
        Arrays.fill(sumCount, 0);
        goal = Constants.DEFAULT_GOAL;
    }


    public void setGoal(int goal) {
        this.goal = goal;
    }

    public StepTracker() {
        init();
    }

    public void addSteps(int month, int day, int count) {
        stepCount[month][day] = count;
        sumCount[month] += count;
        if (maxCount[month] < count) {
            maxCount[month] = count;
        }
    }

    private int getMaxSeriesSize(int month) {
        int result = 0;
        int currentSize = 0;
        for (int i = 0; i < Constants.DAYS_COUNT; i++) {
            if (stepCount[month][i] < goal) {
                result = Math.max(currentSize, result);
                currentSize = 0;
            } else {
                currentSize++;
            }
        }
        result = Math.max(currentSize, result);
        return result;
    }

    public String getStat(int month) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Constants.DAYS_COUNT; i++) {
            builder.append(i + 1);
            builder.append(" день: ");
            builder.append(stepCount[month][i]);
            builder.append(", ");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("\n");
        builder.append("Общее количество шагов за месяц: ");
        builder.append(sumCount[month]);
        builder.append("\n");
        builder.append("Максимальное пройденное количество шагов в месяце: ");
        builder.append(maxCount[month]);
        builder.append("\n");
        builder.append("Среднее количество шагов: ");
        builder.append(sumCount[month] / Constants.DAYS_COUNT);
        builder.append("\n");
        builder.append("Пройденная дистанция: ");
        builder.append(Converter.convertStepsToDistance(sumCount[month]));
        builder.append("\n");
        builder.append("Количество сожжённых килокалорий: ");
        builder.append(Converter.convertStepsToCalories(sumCount[month]));
        builder.append("\n");
        builder.append("Лучшая серия: ");
        builder.append(getMaxSeriesSize(month));
        return builder.toString();
    }
}

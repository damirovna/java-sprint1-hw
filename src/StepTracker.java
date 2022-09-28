import java.util.Arrays;

public class StepTracker {
    private int[][] stepCount = new int[12][30];

    private int goal;

    private int[] maxCount = new int[12];
    private int[] sumCount = new int[12];

    public void init(){
        for (int i = 0; i < 12; i++) {
            Arrays.setAll(stepCount[i], (index) -> 0);
        }
        Arrays.setAll(maxCount, (index) -> 0);
        Arrays.setAll(sumCount, (index) -> 0);
        goal = 10000;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public StepTracker() {
        init();
    }

    public void addSteps(int month, int day, int count){
       stepCount[month][day] = count;
       sumCount[month] += count;
       if (maxCount[month] < count){
           maxCount[month] = count;
       }
    }

    private int getMaxSeriesSize(int month){
        int result = 0;
        int currentSize = 0;
        for (int i = 0; i < 30; i++){
            if (stepCount[month][i] < goal){
                result = (currentSize > result)?currentSize:result;
                currentSize = 0;
            }else{
                currentSize++;
            }
        }
        return result;
    }

    public String getStat(int month){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 30; i++){
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
        builder.append(sumCount[month] / 30);
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

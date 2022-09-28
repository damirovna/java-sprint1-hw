import java.util.Scanner;

public enum Menu {
    ADD_STEPS("Ввести количество шагов за определённый день", 1){
        @Override
        public boolean runCommand() {
            Integer monthIndex = getMonthIndex();
            if (monthIndex == null){
                return true;
            }
            Integer day = getDay();
            if (day == null){
                return true;
            }
            System.out.println("Введите количество шагов: ");
            int count = new Scanner(System.in).nextInt();
            if (count < 0){
                System.out.println("Количесво шагов не может быть отрицательным!");
                return true;
            }
            stepTracker.addSteps(monthIndex, day, count);
            return true;
        }
    },
    GET_STAT("Напечатать статистику за определённый месяц;", 2){
        @Override
        public boolean runCommand() {
            Integer monthIndex = getMonthIndex();
            if (monthIndex != null){
                System.out.println(stepTracker.getStat(monthIndex));
            }
            return true;
        }
    },
    CHANGE_GOAL("Изменить цель по количеству шагов в день;", 3){
        @Override
        public boolean runCommand() {
            System.out.println("Введите новую цель: ");
            int newGoal = new Scanner(System.in).nextInt();
            if (newGoal < 0){
                System.out.println("Вы ввели отрицательное количество шагов. Новая цель не установлена");
            }else{
                stepTracker.setGoal(newGoal);
            }
            return true;
        }
    },
    EXIT("Выйти из приложения.", 0){
        @Override
        public boolean runCommand() {
            return false;
        }
    };

    private String commandText;
    private int commandNumber;
    protected StepTracker stepTracker;

    Menu(String text, int commandNumber) {
        this.commandNumber = commandNumber;
        this.commandText = text;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(commandNumber);
        stringBuilder.append(".  ");
        stringBuilder.append(commandText);
        return stringBuilder.toString();
    }

    public abstract boolean runCommand();

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public int getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
    }

    public StepTracker getStepTracker() {
        return stepTracker;
    }

    public void setStepTracker(StepTracker stepTracker) {
        this.stepTracker = stepTracker;
    }

    protected Integer getMonthIndex(){
        System.out.println("Введите название месяца: ");
        String monthName = new Scanner(System.in).next();
        if (Month.getIndex(monthName) == null){
            System.out.println("Ошибка в названии месяца");
        }
        return Month.getIndex(monthName);
    }
    protected Integer getDay(){
        System.out.println("Введите номер дня: ");
        int day = new Scanner(System.in).nextInt();
        if (day < 1){
            System.out.println("Номер дня должен быть неменьше 1");
            return null;
        }
        if (day > 30){
            System.out.println("Номер дня должен быть небольше 30");
            return null;
        }
        return day - 1;
    }

}

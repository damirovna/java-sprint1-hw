import java.util.Scanner;

public enum Menu {
    ADD_STEPS("Ввести количество шагов за определённый день", 1) {
        @Override
        public boolean runCommand() {
            Integer monthIndex = getMonthIndex();
            if (monthIndex == null) {
                return true;
            }
            Integer day = getDay();
            if (day == null) {
                return true;
            }
            System.out.println("Введите количество шагов: ");
            int count = new Scanner(System.in).nextInt();
            if (count < 0) {
                System.out.println("Количесво шагов не может быть отрицательным!");
                return true;
            }
            stepTracker.addSteps(monthIndex, day, count);
            return true;
        }
    },
    GET_STAT("Напечатать статистику за определённый месяц;", 2) {
        @Override
        public boolean runCommand() {
            Integer monthIndex = getMonthIndex();
            if (monthIndex != null) {
                System.out.println(stepTracker.getStat(monthIndex));
            }
            return true;
        }
    },
    CHANGE_GOAL("Изменить цель по количеству шагов в день;", 3) {
        @Override
        public boolean runCommand() {
            System.out.println("Введите новую цель: ");
            int newGoal = new Scanner(System.in).nextInt();
            if (newGoal < 0) {
                System.out.println("Вы ввели отрицательное количество шагов. Новая цель не установлена");
            } else {
                stepTracker.setGoal(newGoal);
            }
            return true;
        }
    },
    EXIT("Выйти из приложения.", 0) {
        @Override
        public boolean runCommand() {
            return false;
        }
    };

    private final String commandText;
    private final int commandNumber;
    protected StepTracker stepTracker;

    Menu(String text, int commandNumber) {
        this.commandNumber = commandNumber;
        this.commandText = text;
    }

    @Override
    public String toString() {
        return commandNumber + ".  " + commandText;
    }

    public abstract boolean runCommand();

    public int getCommandNumber() {
        return commandNumber;
    }

    public void setStepTracker(StepTracker stepTracker) {
        this.stepTracker = stepTracker;
    }

    protected Integer getMonthIndex() {
        System.out.println("Введите индекс месяца: ");
        int month = new Scanner(System.in).nextInt();
        if (month < 1) {
            System.out.println("Номер месяца должен быть натуральным числом");
            return null;
        }
        if (month > Constants.MONTH_COUNT) {
            System.out.println("Номер месяца должен быть небольше " + Constants.MONTH_COUNT);
            return null;
        }
        return month - 1;
    }

    protected Integer getDay() {
        System.out.println("Введите номер дня: ");
        int day = new Scanner(System.in).nextInt();
        if (day < 1) {
            System.out.println("Номер дня должен быть натуральным числом");
            return null;
        }
        if (day > Constants.DAYS_COUNT) {
            System.out.println("Номер дня должен быть небольше " + Constants.DAYS_COUNT);
            return null;
        }
        return day - 1;
    }

}

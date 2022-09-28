import java.util.Scanner;

public class Main {

    public static void printMenu(){
        for(Menu menu: Menu.values()){
            System.out.println(menu.toString());
        }
    }
    public static void main(String[] args) {
        StepTracker tracker = new StepTracker();
        Scanner scanner = new Scanner(System.in);
        boolean isRun = true;
        while (isRun) {
            printMenu();
            int command = scanner.nextInt();
            boolean isCommandExist = false;
            for(Menu menu: Menu.values()){
                if (command == menu.getCommandNumber()){
                    isCommandExist = true;
                    menu.setStepTracker(tracker);
                    isRun = menu.runCommand();
                }
            }
            if(!isCommandExist){
                System.out.println("Команды " + command + " не существует. Пожалуйста, повторите попытку.");
            }
        }
    }
}

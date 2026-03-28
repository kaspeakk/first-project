import managers.AppManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск программы...");
        AppManager appManager = new AppManager();
        appManager.start();
    }
}
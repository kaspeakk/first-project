package commands;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Завершение работы программы...");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Завершить программу";
    }

    @Override
    public String getUsage() {
        return "exit";
    }
}
package commands;

import java.util.Map;

public class HelpCommand implements Command {
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("=== Доступные команды ===");
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.printf("%-15s - %s%n", entry.getKey(), entry.getValue().getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "Показать справку по командам";
    }

    @Override
    public String getUsage() {
        return "";
    }
}
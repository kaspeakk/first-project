package commands;

import java.util.Map;
import java.util.TreeMap;

public class HelpCommand implements Command {
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = new TreeMap<>(commands);
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n Команды бронирования ");
        printCommand("book_create", commands.get("book_create"));
        printCommand("book_list", commands.get("book_list"));
        printCommand("book_cancel", commands.get("book_cancel"));
        printCommand("book_show", commands.get("book_show"));
        printCommand("book_reschedule", commands.get("book_reschedule"));

        System.out.println("\n Команды выдачи ");
        printCommand("checkout_take", commands.get("checkout_take"));
        printCommand("checkout_return", commands.get("checkout_return"));
        printCommand("checkout_list", commands.get("checkout_list"));
        printCommand("checkout_show", commands.get("checkout_show"));

        System.out.println("\n Команды инструментов ");
        printCommand("inst_available", commands.get("inst_available"));

        System.out.println("\n Общие команды ");
        printCommand("help", commands.get("help"));
        printCommand("exit", commands.get("exit"));
        System.out.println();
    }

    private void printCommand(String name, Command cmd) {
        if (cmd != null) {
            String usage = cmd.getUsage();
            if (usage.isEmpty()) {
                System.out.printf("  %-15s - %s%n", name, cmd.getDescription());
            } else {
                System.out.printf("  %-15s - %s (использование: %s %s)%n",
                        name, cmd.getDescription(), name, usage);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Показать справку";
    }

    @Override
    public String getUsage() {
        return "";
    }
}
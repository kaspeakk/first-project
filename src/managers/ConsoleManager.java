package managers;

import commands.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleManager {
    private CollectionManager collectionManager;
    private Scanner scanner;
    private Map<String, Command> commands;

    public ConsoleManager() {
        this.collectionManager = new CollectionManager();
        this.scanner = new Scanner(System.in);
        this.commands = new HashMap<>();

        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("add", new AddCommand(collectionManager, scanner));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand(commands));
    }

    public void start() {
        System.out.println("Добро пожаловать в систему управления транспортными средствами!");
        System.out.println("Введите 'help' для списка команд");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+");
            String commandName = parts[0].toLowerCase();
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, parts.length - 1);

            Command command = commands.get(commandName);

            if (command != null) {
                try {
                    command.execute(args);
                } catch (Exception e) {
                    System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                }
            } else {
                System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
            }
        }
    }
}
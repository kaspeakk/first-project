package managers;

import commands.*;
import commands.book.*;
import commands.checkout.*;
import commands.instrument.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppManager {
    private InstrumentManager instrumentManager;
    private BookingManager bookingManager;
    private CheckoutManager checkoutManager;
    private Scanner scanner;
    private Map<String, Command> commands;

    public AppManager() {
        this.instrumentManager = new InstrumentManager();
        this.bookingManager = new BookingManager();
        this.checkoutManager = new CheckoutManager();
        this.scanner = new Scanner(System.in);  // ОДИН Scanner на всё приложение
        this.commands = new HashMap<>();

        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("inst_available", new InstAvailableCommand(bookingManager, instrumentManager));
        commands.put("book_create", new BookCreateCommand(bookingManager, instrumentManager, scanner));
        commands.put("book_list", new BookListCommand(bookingManager));
        commands.put("book_cancel", new BookCancelCommand(bookingManager));
        commands.put("book_show", new BookShowCommand(bookingManager));
        commands.put("book_reschedule", new BookRescheduleCommand(bookingManager));
        commands.put("checkout_take", new CheckoutTakeCommand(checkoutManager, instrumentManager, scanner));
        commands.put("checkout_return", new CheckoutReturnCommand(checkoutManager, instrumentManager, scanner));
        commands.put("checkout_list", new CheckoutListCommand(checkoutManager));
        commands.put("checkout_show", new CheckoutShowCommand(checkoutManager));
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand());
    }

    public void start() {
        System.out.println("=== Система управления оборудованием ===");
        System.out.println("Введите 'help' для списка команд");

        while (true) {
            System.out.print("\n> ");

            if (!scanner.hasNextLine()) {
                break;
            }

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
                    System.out.println("Ошибка: " + e.getMessage());
                }
            } else {
                System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
            }
        }

        scanner.close();
    }
}
package commands.checkout;

import commands.Command;
import managers.CheckoutManager;
import managers.InstrumentManager;
import java.util.Scanner;

public class CheckoutReturnCommand implements Command {
    private CheckoutManager checkoutManager;
    private InstrumentManager instrumentManager;
    private Scanner scanner;

    public CheckoutReturnCommand(CheckoutManager checkoutManager, InstrumentManager instrumentManager, Scanner scanner) {
        this.checkoutManager = checkoutManager;
        this.instrumentManager = instrumentManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID выдачи");
            System.out.println("Использование: checkout_return <checkout_id>");
            return;
        }

        try {
            Long checkoutId = Long.parseLong(args[0]);
            var checkout = checkoutManager.getCheckout(checkoutId);

            if (checkout == null) {
                System.out.println("Ошибка: выдача с ID " + checkoutId + " не найдена");
                return;
            }

            if (checkout.isReturned()) {
                System.out.println("Ошибка: инструмент уже возвращен");
                return;
            }

            System.out.print("Состояние (OK|DAMAGED): ");
            String condition = scanner.nextLine().toUpperCase();

            if (!"OK".equals(condition) && !"DAMAGED".equals(condition)) {
                System.out.println("Ошибка: состояние должно быть OK или DAMAGED");
                return;
            }

            checkoutManager.returnCheckout(checkoutId, condition);

            // Возвращаем инструмент в доступное состояние
            if ("OK".equals(condition)) {
                instrumentManager.setInstrumentStatus(checkout.getInstrumentId(), "AVAILABLE");
            } else {
                instrumentManager.setInstrumentStatus(checkout.getInstrumentId(), "OUT_OF_SERVICE");
            }

            System.out.println("OK returned");

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Вернуть инструмент";
    }

    @Override
    public String getUsage() {
        return "<checkout_id>";
    }
}
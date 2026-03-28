package commands.checkout;

import commands.Command;
import managers.CheckoutManager;
import managers.InstrumentManager;
import java.util.Scanner;

public class CheckoutTakeCommand implements Command {
    private CheckoutManager checkoutManager;
    private InstrumentManager instrumentManager;
    private Scanner scanner;

    public CheckoutTakeCommand(CheckoutManager checkoutManager, InstrumentManager instrumentManager, Scanner scanner) {
        this.checkoutManager = checkoutManager;
        this.instrumentManager = instrumentManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID инструмента");
            System.out.println("Использование: checkout_take <instrument_id>");
            return;
        }

        try {
            Long instrumentId = Long.parseLong(args[0]);

            var instrument = instrumentManager.getInstrument(instrumentId);
            if (instrument == null) {
                System.out.println("Ошибка: инструмент не найден");
                return;
            }

            if (!"AVAILABLE".equals(instrument.getStatus())) {
                System.out.println("Ошибка: прибор уже выдан или неисправен");
                return;
            }

            System.out.print("Кто берёт (username): ");
            String username = scanner.nextLine();

            System.out.print("Комментарий (можно пусто): ");
            String comment = scanner.nextLine();

            Long checkoutId = checkoutManager.createCheckout(instrumentId, username, comment);
            instrumentManager.setInstrumentStatus(instrumentId, "IN_USE");

            System.out.println("OK checkout_id=" + checkoutId);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Выдать инструмент пользователю";
    }

    @Override
    public String getUsage() {
        return "<instrument_id>";
    }
}
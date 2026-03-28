package commands.checkout;

import commands.Command;
import managers.CheckoutManager;
import models.Checkout;
import java.time.format.DateTimeFormatter;

public class CheckoutShowCommand implements Command {
    private CheckoutManager checkoutManager;

    public CheckoutShowCommand(CheckoutManager checkoutManager) {
        this.checkoutManager = checkoutManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID выдачи");
            System.out.println("Использование: checkout_show <checkout_id>");
            return;
        }

        try {
            Long checkoutId = Long.parseLong(args[0]);
            Checkout checkout = checkoutManager.getCheckout(checkoutId);

            if (checkout == null) {
                System.out.println("Ошибка: выдача с ID " + checkoutId + " не найдена");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            System.out.println("Checkout #" + checkout.getId());
            System.out.println("instrument_id: " + checkout.getInstrumentId());
            System.out.println("user: " + checkout.getUsername());
            System.out.println("takenAt: " + checkout.getTakenAt().format(formatter));

            if (checkout.isReturned()) {
                System.out.println("returnedAt: " + checkout.getReturnedAt().format(formatter));
                System.out.println("condition: " + checkout.getCondition());
            } else {
                System.out.println("returnedAt: -");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Показать детали выдачи";
    }

    @Override
    public String getUsage() {
        return "<checkout_id>";
    }
}
package commands.checkout;

import commands.Command;
import managers.CheckoutManager;
import models.Checkout;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckoutListCommand implements Command {
    private CheckoutManager checkoutManager;

    public CheckoutListCommand(CheckoutManager checkoutManager) {
        this.checkoutManager = checkoutManager;
    }

    @Override
    public void execute(String[] args) {
        boolean openOnly = args.length >= 1 && "--open-only".equals(args[0]);

        List<Checkout> checkouts = openOnly ?
                checkoutManager.getOpenCheckouts() :
                checkoutManager.getAllCheckouts();

        if (checkouts.isEmpty()) {
            System.out.println("Нет выдач" + (openOnly ? " (открытых)" : ""));
            return;
        }

        System.out.println("ID  Instrument User        TakenAt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Checkout c : checkouts) {
            System.out.printf("%-3d %-10d %-10s %s%n",
                    c.getId(),
                    c.getInstrumentId(),
                    c.getUsername(),
                    c.getTakenAt().format(formatter));
        }
    }

    @Override
    public String getDescription() {
        return "Список выдач";
    }

    @Override
    public String getUsage() {
        return "[--open-only]";
    }
}
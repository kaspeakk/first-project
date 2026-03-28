package commands.book;

import commands.Command;
import managers.BookingManager;

public class BookCancelCommand implements Command {
    private BookingManager bookingManager;

    public BookCancelCommand(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID бронирования");
            System.out.println("Использование: book_cancel <booking_id>");
            return;
        }

        try {
            Long bookingId = Long.parseLong(args[0]);

            try {
                boolean cancelled = bookingManager.cancelBooking(bookingId);
                if (cancelled) {
                    System.out.println("OK cancelled");
                } else {
                    System.out.println("Ошибка: бронирование с ID " + bookingId + " не найдено");
                }
            } catch (IllegalStateException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Отменить бронирование";
    }

    @Override
    public String getUsage() {
        return "<booking_id>";
    }
}
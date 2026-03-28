package commands.book;

import commands.Command;
import managers.BookingManager;

public class BookRescheduleCommand implements Command {
    private BookingManager bookingManager;

    public BookRescheduleCommand(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Ошибка: укажите ID бронирования, новое начало и конец");
            System.out.println("Использование: book_reschedule <booking_id> \"<start>\" \"<end>\"");
            return;
        }

        try {
            Long bookingId = Long.parseLong(args[0]);

            // Объединяем аргументы для дат (могут содержать пробелы)
            String startStr = args[1];
            String endStr = args[2];

            boolean rescheduled = bookingManager.rescheduleBooking(bookingId, startStr, endStr);

            if (rescheduled) {
                System.out.println("OK rescheduled");
            } else {
                System.out.println("Ошибка: бронирование с ID " + bookingId + " не найдено");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Перенести бронирование";
    }

    @Override
    public String getUsage() {
        return "<booking_id> \"<start>\" \"<end>\"";
    }
}
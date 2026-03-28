package commands.book;

import commands.Command;
import managers.BookingManager;
import models.Booking;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class BookListCommand implements Command {
    private BookingManager bookingManager;

    public BookListCommand(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID инструмента");
            System.out.println("Использование: book_list <instrument_id> [--from YYYY-MM-DD]");
            return;
        }

        try {
            Long instrumentId = Long.parseLong(args[0]);
            LocalDateTime from = LocalDateTime.MIN;

            // Проверяем наличие --from параметра
            if (args.length >= 3 && "--from".equals(args[1])) {
                try {
                    LocalDate date = LocalDate.parse(args[2]);
                    from = date.atStartOfDay();
                } catch (DateTimeParseException e) {
                    System.out.println("Ошибка: неверный формат даты. Используйте YYYY-MM-DD");
                    return;
                }
            }

            List<Booking> bookings = bookingManager.getBookingsForInstrumentAfter(instrumentId, from);

            if (bookings.isEmpty()) {
                System.out.println("Нет бронирований для этого инструмента");
                return;
            }

            System.out.println("ID  Start                End");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for (Booking b : bookings) {
                System.out.printf("%-3d %s %s%n",
                        b.getId(),
                        b.getStartTime().format(formatter),
                        b.getEndTime().format(formatter));
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Показать бронирования инструмента";
    }

    @Override
    public String getUsage() {
        return "<instrument_id> [--from YYYY-MM-DD]";
    }
}
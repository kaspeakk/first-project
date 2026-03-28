package commands.book;

import commands.Command;
import managers.BookingManager;
import models.Booking;
import java.time.format.DateTimeFormatter;

public class BookShowCommand implements Command {
    private BookingManager bookingManager;

    public BookShowCommand(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID бронирования");
            System.out.println("Использование: book_show <booking_id>");
            return;
        }

        try {
            Long bookingId = Long.parseLong(args[0]);
            Booking booking = bookingManager.getBooking(bookingId);

            if (booking == null) {
                System.out.println("Ошибка: бронирование с ID " + bookingId + " не найдено");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            System.out.println("Booking #" + booking.getId());
            System.out.println("instrument_id: " + booking.getInstrumentId());
            System.out.println("start: " + booking.getStartTime().format(formatter));
            System.out.println("end: " + booking.getEndTime().format(formatter));
            System.out.println("status: " + booking.getStatus());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Показать детали бронирования";
    }

    @Override
    public String getUsage() {
        return "<booking_id>";
    }
}
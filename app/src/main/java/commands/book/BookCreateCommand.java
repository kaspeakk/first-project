package commands.book;

import commands.Command;
import managers.BookingManager;
import managers.InstrumentManager;
import java.util.Scanner;

public class BookCreateCommand implements Command {
    private BookingManager bookingManager;
    private InstrumentManager instrumentManager;
    private Scanner scanner;

    public BookCreateCommand(BookingManager bookingManager, InstrumentManager instrumentManager, Scanner scanner) {
        this.bookingManager = bookingManager;
        this.instrumentManager = instrumentManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID инструмента");
            System.out.println("Использование: book_create <instrument_id>");
            return;
        }

        try {
            Long instrumentId = Long.parseLong(args[0]);

            if (instrumentManager.getInstrument(instrumentId) == null) {
                System.out.println("Ошибка: инструмент с ID " + instrumentId + " не найден");
                return;
            }

            System.out.print("Начало (YYYY-MM-DD HH:MM): ");
            String startStr = scanner.nextLine();

            System.out.print("Конец (YYYY-MM-DD HH:MM): ");
            String endStr = scanner.nextLine();

            Long bookingId = bookingManager.createBooking(instrumentId, startStr, endStr);
            System.out.println("OK booking_id=" + bookingId);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Создать бронирование инструмента";
    }

    @Override
    public String getUsage() {
        return "<instrument_id>";
    }
}
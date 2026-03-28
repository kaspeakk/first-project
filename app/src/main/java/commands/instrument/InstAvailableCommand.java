package commands.instrument;

import commands.Command;
import managers.BookingManager;
import managers.InstrumentManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class InstAvailableCommand implements Command {
    private BookingManager bookingManager;
    private InstrumentManager instrumentManager;

    public InstAvailableCommand(BookingManager bookingManager, InstrumentManager instrumentManager) {
        this.bookingManager = bookingManager;
        this.instrumentManager = instrumentManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Ошибка: укажите тип, начало и конец");
            System.out.println("Использование: inst_available <type> \"<start>\" \"<end>\"");
            return;
        }

        try {
            String type = args[0];
            String startStr = args[1];
            String endStr = args[2];

            LocalDateTime start = parseDateTime(startStr);
            LocalDateTime end = parseDateTime(endStr);

            if (end.isBefore(start)) {
                System.out.println("Ошибка: конец не может быть раньше начала");
                return;
            }

            List<Long> available = bookingManager.getAvailableInstruments(type, start, end, instrumentManager);

            if (available.isEmpty()) {
                System.out.println("Нет доступных инструментов типа " + type);
            } else {
                System.out.print("Available instruments: ");
                for (int i = 0; i < available.size(); i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print(available.get(i));
                }
                System.out.println();
            }

        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: неверный формат времени");
        }
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeStr.replace("\"", ""), formatter);
    }

    @Override
    public String getDescription() {
        return "Показать доступные инструменты по типу на время";
    }

    @Override
    public String getUsage() {
        return "<type> \"<start>\" \"<end>\"";
    }
}
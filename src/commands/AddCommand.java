package commands;

import managers.CollectionManager;
import models.*;
import utils.IdGenerator;
import java.util.Scanner;

public class AddCommand implements Command {
    private CollectionManager collectionManager;
    private Scanner scanner;

    public AddCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        try {
            System.out.println("=== Создание нового транспортного средства ===");

            // Ввод названия
            System.out.print("Введите название: ");
            String name = scanner.nextLine().trim();

            // Ввод координат
            System.out.print("Введите координату X (число): ");
            double x = Double.parseDouble(scanner.nextLine());

            System.out.print("Введите координату Y (число): ");
            Float y = Float.parseFloat(scanner.nextLine());
            Coordinates coordinates = new Coordinates(x, y);

            // Ввод мощности двигателя (опционально)
            System.out.print("Введите мощность двигателя (Enter чтобы пропустить): ");
            String powerInput = scanner.nextLine().trim();
            Double enginePower = null;
            if (!powerInput.isEmpty()) {
                enginePower = Double.parseDouble(powerInput);
            }

            // Ввод типа
            System.out.println("Доступные типы: CAR, TRUCK, MOTORCYCLE, BICYCLE");
            System.out.print("Введите тип: ");
            VehicleType type = VehicleType.valueOf(scanner.nextLine().toUpperCase());

            // Ввод типа топлива (опционально)
            System.out.println("Доступные типы топлива: GASOLINE, DIESEL, ELECTRIC, HYBRID");
            System.out.print("Введите тип топлива (Enter чтобы пропустить): ");
            String fuelInput = scanner.nextLine().toUpperCase();
            FuelType fuelType = null;
            if (!fuelInput.isEmpty()) {
                fuelType = FuelType.valueOf(fuelInput);
            }

            // Создание и добавление объекта
            Long newId = IdGenerator.generateId();
            Vehicle vehicle = new Vehicle(newId, name, coordinates, enginePower, type, fuelType);
            collectionManager.add(vehicle);

            System.out.println("Транспортное средство успешно создано! ID: " + newId);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при создании: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию";
    }

    @Override
    public String getUsage() {
        return "add";
    }
}
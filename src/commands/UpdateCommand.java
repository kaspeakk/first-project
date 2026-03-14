package commands;

import managers.CollectionManager;
import models.*;

public class UpdateCommand implements Command {
    private CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID элемента для обновления");
            System.out.println("Использование: " + getUsage());
            return;
        }

        try {
            Long id = Long.parseLong(args[0]);
            Vehicle existingVehicle = collectionManager.getById(id);

            if (existingVehicle == null) {
                System.out.println("Элемент с ID " + id + " не найден");
                return;
            }

            System.out.println("Обновление элемента с ID: " + id);
            System.out.println("Текущие данные: " + existingVehicle);

            // Здесь можно добавить логику обновления полей
            // Для простоты просто удаляем старый и создаем новый с тем же ID

            // Создаем новый объект с обновленными данными
            // В реальном проекте нужно запрашивать новые значения
            System.out.println("Функция полного обновления будет реализована позже");

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Обновить элемент по ID";
    }

    @Override
    public String getUsage() {
        return "update <id>";
    }
}
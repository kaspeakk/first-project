package commands;

import managers.CollectionManager;

public class RemoveByIdCommand implements Command {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID элемента для удаления");
            System.out.println("Использование: " + getUsage());
            return;
        }

        try {
            Long id = Long.parseLong(args[0]);
            boolean removed = collectionManager.removeById(id);

            if (removed) {
                System.out.println("Элемент с ID " + id + " успешно удален");
            } else {
                System.out.println("Элемент с ID " + id + " не найден");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом");
        }
    }

    @Override
    public String getDescription() {
        return "Удалить элемент по ID";
    }

    @Override
    public String getUsage() {
        return "remove_by_id <id>";
    }
}
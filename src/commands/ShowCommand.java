package commands;

import managers.CollectionManager;
import models.Vehicle;

public class ShowCommand implements Command {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция пуста");
            return;
        }

        System.out.println("=== Все элементы коллекции ===");
        for (Vehicle vehicle : collectionManager.getCollection()) {
            System.out.println(vehicle);
        }
        System.out.println("Всего элементов: " + collectionManager.getCollection().size());
    }

    @Override
    public String getDescription() {
        return "Показать все элементы коллекции";
    }

    @Override
    public String getUsage() {
        return "show";
    }
}
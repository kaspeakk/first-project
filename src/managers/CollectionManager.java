package managers;

import models.Vehicle;
import java.util.*;

public class CollectionManager {
    private LinkedList<Vehicle> collection;
    private Date initializationDate;

    public CollectionManager() {
        this.collection = new LinkedList<>();
        this.initializationDate = new Date();
    }

    public void add(Vehicle vehicle) {
        collection.add(vehicle);
    }

    public boolean removeById(Long id) {
        return collection.removeIf(vehicle -> vehicle.getId().equals(id));
    }

    public Vehicle getById(Long id) {
        return collection.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void clear() {
        collection.clear();
    }

    public LinkedList<Vehicle> getCollection() {
        return collection;
    }

    public Date getInitializationDate() {
        return initializationDate;
    }

    public int getSize() {
        return collection.size();
    }

    public String getCollectionType() {
        return collection.getClass().getSimpleName();
    }
}
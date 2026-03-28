package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vehicle {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Double enginePower;
    private VehicleType type;
    private FuelType fuelType;

    public Vehicle(Long id, String name, Coordinates coordinates, Double enginePower,
                   VehicleType type, FuelType fuelType) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID должен быть положительным числом");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Тип транспортного средства не может быть null");
        }
        if (enginePower != null && enginePower <= 0) {
            throw new IllegalArgumentException("Мощность двигателя должна быть > 0");
        }

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Coordinates getCoordinates() { return coordinates; }
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    public LocalDateTime getCreationDate() { return creationDate; }

    public Double getEnginePower() { return enginePower; }
    public void setEnginePower(Double enginePower) { this.enginePower = enginePower; }

    public VehicleType getType() { return type; }
    public void setType(VehicleType type) { this.type = type; }

    public FuelType getFuelType() { return fuelType; }
    public void setFuelType(FuelType fuelType) { this.fuelType = fuelType; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Vehicle[ID=%d, Name='%s', Coordinates=%s, Created=%s, EnginePower=%s, Type=%s, Fuel=%s]",
                id, name, coordinates, creationDate.format(formatter),
                enginePower != null ? enginePower : "не указана",
                type, fuelType != null ? fuelType : "не указан");
    }
}
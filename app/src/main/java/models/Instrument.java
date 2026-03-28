package models;

public class Instrument {
    private Long id;
    private String name;
    private String type; // PH_METER, SPECTROPHOTOMETER, CENTRIFUGE, etc.
    private String status; // AVAILABLE, IN_USE, OUT_OF_SERVICE

    public Instrument(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = "AVAILABLE";
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Instrument[ID=%d, Name='%s', Type='%s', Status='%s']",
                id, name, type, status);
    }
}
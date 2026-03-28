package models;

import java.time.LocalDateTime;

public class Booking {
    private Long id;
    private Long instrumentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // ACTIVE, CANCELLED, COMPLETED

    public Booking(Long id, Long instrumentId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.instrumentId = instrumentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = "ACTIVE";
    }

    public Long getId() { return id; }
    public Long getInstrumentId() { return instrumentId; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public boolean isActive() {
        return "ACTIVE".equals(status) && LocalDateTime.now().isBefore(startTime);
    }

    public boolean hasStarted() {
        return LocalDateTime.now().isAfter(startTime);
    }

    @Override
    public String toString() {
        return String.format("Booking #%d: Instrument=%d, %s - %s [%s]",
                id, instrumentId, startTime, endTime, status);
    }
}
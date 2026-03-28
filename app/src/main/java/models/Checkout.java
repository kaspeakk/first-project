package models;

import java.time.LocalDateTime;

public class Checkout {
    private Long id;
    private Long instrumentId;
    private String username;
    private String comment;
    private LocalDateTime takenAt;
    private LocalDateTime returnedAt;
    private String condition; // null если не возвращен, OK или DAMAGED если возвращен

    public Checkout(Long id, Long instrumentId, String username, String comment) {
        this.id = id;
        this.instrumentId = instrumentId;
        this.username = username;
        this.comment = comment;
        this.takenAt = LocalDateTime.now();
        this.returnedAt = null;
        this.condition = null;
    }

    public Long getId() { return id; }
    public Long getInstrumentId() { return instrumentId; }
    public String getUsername() { return username; }
    public String getComment() { return comment; }
    public LocalDateTime getTakenAt() { return takenAt; }
    public LocalDateTime getReturnedAt() { return returnedAt; }
    public String getCondition() { return condition; }

    public void returnItem(String condition) {
        this.returnedAt = LocalDateTime.now();
        this.condition = condition;
    }

    public boolean isReturned() {
        return returnedAt != null;
    }

    @Override
    public String toString() {
        String returned = returnedAt != null ? returnedAt.toString() : "-";
        String cond = condition != null ? condition : "-";
        return String.format("Checkout #%d: Instrument=%d, User='%s', Taken=%s, Returned=%s, Cond=%s",
                id, instrumentId, username, takenAt, returned, cond);
    }
}
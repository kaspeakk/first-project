package managers;

import models.Checkout;

import java.util.*;
import java.util.stream.Collectors;

public class CheckoutManager {
    private Map<Long, Checkout> checkouts;
    private long nextId = 1;

    public CheckoutManager() {
        this.checkouts = new HashMap<>();
    }

    public Long createCheckout(Long instrumentId, String username, String comment) {
        Long id = nextId++;
        Checkout checkout = new Checkout(id, instrumentId, username, comment);
        checkouts.put(id, checkout);
        return id;
    }

    public boolean returnCheckout(Long checkoutId, String condition) {
        Checkout checkout = checkouts.get(checkoutId);
        if (checkout == null) {
            return false;
        }

        if (checkout.isReturned()) {
            throw new IllegalStateException("Инструмент уже возвращен");
        }

        checkout.returnItem(condition);
        return true;
    }

    public Checkout getCheckout(Long id) {
        return checkouts.get(id);
    }

    public List<Checkout> getAllCheckouts() {
        return new ArrayList<>(checkouts.values());
    }

    public List<Checkout> getOpenCheckouts() {
        return checkouts.values().stream()
                .filter(c -> !c.isReturned())
                .collect(Collectors.toList());
    }

    public Checkout getActiveCheckoutForInstrument(Long instrumentId) {
        return checkouts.values().stream()
                .filter(c -> c.getInstrumentId().equals(instrumentId))
                .filter(c -> !c.isReturned())
                .findFirst()
                .orElse(null);
    }
}
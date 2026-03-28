package managers;

import models.Instrument;
import java.util.*;
import java.util.stream.Collectors;

public class InstrumentManager {
    private Map<Long, Instrument> instruments;
    private long nextId = 1;

    public InstrumentManager() {
        this.instruments = new HashMap<>();
        initSampleInstruments();
    }

    private void initSampleInstruments() {
        addInstrument(new Instrument(nextId++, "pH Meter Basic", "PH_METER"));
        addInstrument(new Instrument(nextId++, "pH Meter Pro", "PH_METER"));
        addInstrument(new Instrument(nextId++, "Spectrophotometer UV-VIS", "SPECTROPHOTOMETER"));
        addInstrument(new Instrument(nextId++, "Centrifuge 1.5mL", "CENTRIFUGE"));
        addInstrument(new Instrument(nextId++, "Centrifuge 50mL", "CENTRIFUGE"));
        addInstrument(new Instrument(nextId++, "Microscope", "MICROSCOPE"));
        addInstrument(new Instrument(nextId++, "Balance Analytical", "BALANCE"));
    }

    public void addInstrument(Instrument instrument) {
        instruments.put(instrument.getId(), instrument);
    }

    public Instrument getInstrument(Long id) {
        return instruments.get(id);
    }

    public List<Instrument> getAllInstruments() {
        return new ArrayList<>(instruments.values());
    }

    public List<Instrument> getInstrumentsByType(String type) {
        return instruments.values().stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

    public List<Instrument> getAvailableInstruments() {
        return instruments.values().stream()
                .filter(i -> "AVAILABLE".equals(i.getStatus()))
                .collect(Collectors.toList());
    }

    public boolean isAvailable(Long instrumentId) {
        Instrument inst = instruments.get(instrumentId);
        return inst != null && "AVAILABLE".equals(inst.getStatus());
    }

    public void setInstrumentStatus(Long instrumentId, String status) {
        Instrument inst = instruments.get(instrumentId);
        if (inst != null) {
            inst.setStatus(status);
        }
    }
}
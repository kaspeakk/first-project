package utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdGenerator {
    private static Set<Long> usedIds = new HashSet<>();
    private static Random random = new Random();

    public static Long generateId() {
        Long newId;
        do {
            newId = Math.abs(random.nextLong()) % 1000000; // Генерируем ID до 1 миллиона
            if (newId == 0) newId = 1L; // ID не может быть 0
        } while (usedIds.contains(newId));

        usedIds.add(newId);
        return newId;
    }
}
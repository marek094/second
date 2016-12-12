package com.company.NetOrg;

import com.company.DataBase;

import java.util.Arrays;
import java.util.stream.Collectors;


public class Item implements Comparable {
    int priority;
    String title;

    public Item(int priority, String title) {
        this.priority = priority;
        this.title = title;
    }
    private Item() {}

    public static Item parseItem(String line) {
        String[] parts = line.split(":");
        Item it = new Item();
        try {
            it.priority = Integer.parseInt(parts[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Badline", e);
        }
        it.title = Arrays.stream(parts, 1, parts.length)
                .collect(Collectors.joining(":"));
        return it;
    }
    @Override
    public String toString() {
        return priority + ":" + title;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Item) {
            return Integer.compare(priority, ((Item) o).priority);
        }
        return -1;
    }
}

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cernym23 on 5.12.16.
 */
public class DataBase {

    private static final Path filePath = Paths.get(System.getProperty("user.home"),".JavaDatabase");
    private static List<Item> items;

    private static class Item implements Comparable {
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

    public static void main(String[] args) {
        if (args.length < 1) {
            help();
            return;
        }

        readItems();
        switch (args[0]) {
            case "-a": add(args); break;
            case "-l": list(args, false); break;
            case "-r": list(args, true); break;
            case "-d": delete(args); break;
            default: help();
        }
        writeItems();
    }

    public static void help() {
        System.out.println("Help!!!");
    }



    public static void add(String[] args) {
        if (args.length < 3) {
            help(); return;
        }
        int priority = Integer.parseInt(args[1]);
        String title = args[2];

        items.add(new Item(priority, title));

        System.out.println("Added " + priority + " " + title);

    }

    public static void list(String[] args, boolean reverse) {
        Collections.sort(items);
        if (reverse) {
            items.forEach(System.out::println);
        } else {
            for (ListIterator<Item> li = items.listIterator(items.size());
                 li.hasPrevious();
                 System.out.println(li.previous()) );
        }
    }

    public static void delete(String[] args) {
        int i = 0;
        for (Item it : items) {
            i++;
            System.out.println(i + "> " + it);
        }
        Integer num;
        for (;;) {
            System.out.println("Zadej cislo:");
            try (BufferedReader userInp = new BufferedReader(new InputStreamReader(System.in))) {
                num = Integer.parseInt( userInp.readLine() );
                if (num < 1 || num > items.size()) {
                    throw new NumberFormatException("Spatny format");
                }

                break;
            } catch (NumberFormatException | IOException ex ) {
                System.out.println("Try again!");
            } catch (Exception ex) {
                System.out.println(ex);
                System.exit(0);
            }
        }

        items.remove(num-1);
        System.out.println("Smazano!");
    }

    public static void readItems() {
        if (!Files.exists(filePath)) {
            System.out.println("File do not exists!");
            items = new ArrayList<>();
        } else {
            try {
                items = Files.lines(filePath).map(Item::parseItem).collect(Collectors.toList());
            } catch (IOException e) {
                System.out.println("Cannot read");
                System.exit(0);
            }

        }
    }

    public static void writeItems() {
        try {
            List lines = items.stream().map(Item::toString).collect(Collectors.toList());
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Cannot write file");
            System.exit(0);
        }
        filePath.toFile();
    }
}

package com.company;

/**
 * Created by cernym23 on 17.10.16.
 */
public class Logger {
    private MyCollection printers = new MyArray();
    private int level = 0;

    public void addLogger(Printer printer) {
        this.printers.add(printer);
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public void log(int level, String msg) {
        if (this.level > level) return;
        for (int i=0; i<printers.getSize(); i++) {
            ((Printer) (printers.get(i))).print(msg);
        }
    }

}

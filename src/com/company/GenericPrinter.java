package com.company;

/**
 * Created by cernym23 on 17.10.16.
 */
public class GenericPrinter implements Printer {
    public GenericPrinter () {
        System.out.print("new GenericPrinter\n");
    }
    public void print(String msg) {
        System.out.print(msg);
    }

}

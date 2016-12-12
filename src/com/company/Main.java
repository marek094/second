package com.company;


import java.util.Iterator;

interface Printer {
    void print(String msg);
}

public class Main {

    public static void main(String[] args) {
	    /*Printer lp = new LabelPrinter("Label");
        Printer tp = new TimestampPrinter();

        Logger log = new Logger();
        log.addLogger(lp);
        log.addLogger(tp);

        for (int i=0;i<20; i++)
            log.log(10, "Ahojky " + i + "\n");

        */
        System.out.print("Hello\n");

	    MyArray ma = new MyArray();
        for (int i=0;i<20; i++) ma.add(i);

        for (Object i : ma) {
            System.out.print((Integer)i + "\n");
        }

        System.out.print("-----\n");

        ma.remove(0);
        ma.remove((Object)5);
        ma.remove((Object)6);

        for (Object i : ma) {
            System.out.print((Integer)i + "\n");
        }

    }
}

package com.company;

/**
 * Created by cernym23 on 17.10.16.
 */
public class TimestampPrinter extends GenericPrinter {
    public TimestampPrinter() {
        System.out.print("new TimestampPrinter\n");
    }

    public void print(String msg) {
        System.out.println(new java.util.Date());
        super.print(msg);
    }
}

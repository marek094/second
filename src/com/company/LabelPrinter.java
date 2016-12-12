package com.company;

/**
 * Created by cernym23 on 17.10.16.
 */
public class LabelPrinter extends GenericPrinter {
    private String label;
    public LabelPrinter(String label) {
        this.label = label;

        System.out.print("new Labelprinter\n");
    }

    public void print(String msg) {
        System.out.println(label + ": ");
        super.print(msg);
    }

}

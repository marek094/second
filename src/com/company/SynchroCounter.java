package com.company;

/**
 * Created by cernym23 on 28.11.16.
 */
public class SynchroCounter {
    private long counter;

    public SynchroCounter(long counter) {
        this.counter = counter;
    }

    public SynchroCounter() {
        this(0);
    }

    public synchronized long get() {
        return counter;
    }
    public synchronized void inc() {
        counter++;
    }
}

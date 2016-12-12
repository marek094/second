package com.company;

/**
 * Created by cernym23 on 17.10.16.
 */
public interface MyCollection {
    void add(Object o);
    public int getSize();
    Object get(int i);
    void remove(Object o);
    void remove(int i);
}

package com.company;

import java.util.Iterator;

/**
 * Created by cernym23 on 17.10.16.
 */
public class MyArray implements MyCollection, Iterable {
    private int size = 0;
    private int realSize = 4;
    private Object[] arr = new Object[realSize];

    @Override
    public void add(Object o) {
        if (realSize <= size) {
            Object[] arrNew = new Object[2*realSize];
            for (int i=0; i<realSize; i++) {
                arrNew[i] = arr[i];
            }
            arr = arrNew;
            realSize *= 2;
        }
        arr[size] = o;
        size++;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Object get(int i) {
        if (i >= size) return null;
        return arr[i];
    }

    @Override
    public void remove(Object o) {
        int cnt = 0;
        for (int i=0; i+cnt < size; i++) {
            if (arr[i] == o) {
                cnt++;
            }
            arr[i] = arr[i+cnt];
        }
        size -= cnt;
        dealloc();
    }

    @Override
    public void remove(int x) {
        for (int i=x+1; i < size; i++) {
            arr[i-1] = arr[i];
        }
        size--;
        dealloc();
    }

    private void dealloc() {
        if (size*2 < realSize && realSize > 4) {
            Object[] arrNew = new Object[realSize/2];
            for (int i=0; i<realSize/2; i++) {
                arrNew[i] = arr[i];
            }
            arr = arrNew;
            realSize /= 2;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (index < getSize()) return true;
                return false;
            }

            @Override
            public Object next() {
                index++;
                return get(index-1);
            }
        };
    }
}

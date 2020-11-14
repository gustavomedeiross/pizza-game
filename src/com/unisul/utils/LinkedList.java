package com.unisul.utils;

public interface LinkedList<T> {
    public void add(T t);
    public void put(T t, int index);
    public void remove(int index);
    public T first();
    public T last();
    public T get(int index);
    public int indexOf(T t);
    public int size();
    public boolean isEmpty();
}

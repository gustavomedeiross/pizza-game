package com.unisul.utils;

public interface Stack <T> {
    public void push(T t);
    public T pop();
    public T peek();
    public int size();
    public boolean isEmpty();
    public boolean isNotEmpty();
}


package com.example.demo;

public interface Algorithm<T> {
    void run(Instrument instrument);

    T getResult();
}

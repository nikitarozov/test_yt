package com.example.demo.algorithms;

public interface Algorithm<T> {
    void run(Instrument instrument);

    T getResult();
}

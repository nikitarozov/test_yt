package com.example.demo;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class SpecialCaseAlgorithm implements Algorithm<SortedSet<Instrument>> {
    private final int maxCount = 10;
    private NavigableSet<Instrument> instruments = new ConcurrentSkipListSet<>();

    public void run(Instrument instrument) {
        instruments.add(instrument);
        if (instruments.size() > maxCount) {
            instruments.remove(instruments.last());
        }
    }

    public SortedSet<Instrument> getResult() {
        return instruments;
    }
}


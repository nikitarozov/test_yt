package com.example.demo.algorithms;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class SpecialCaseAlgorithm implements Algorithm<SortedSet<Instrument>> {
    private final int maxCount = 10;
    private NavigableSet<Instrument> instruments = new ConcurrentSkipListSet<Instrument>(Comparator.reverseOrder());

    public void run(Instrument instrument) {
        instruments.add(instrument);
        while (instruments.size() > maxCount) {
            instruments.remove(instruments.last());
        }
    }

    public SortedSet<Instrument> getResult() {
        return instruments;
    }
}


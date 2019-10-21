package com.example.demo;

import com.example.demo.algorithms.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class Aggregator {
    private final String DELIMITER = ",";
    private final int INDEX_PRICE = 2;
    private final int INDEX_DATE = 1;
    private final int INDEX_INSTRUEMT = 0;
    private ConcurrentMap<String, Algorithm> mapInstrument = new ConcurrentHashMap<>();

    Aggregator() {
        mapInstrument.put("INSTRUMENT1", new AverageAlgorithm());
        mapInstrument.put("INSTRUMENT2", new AverageAlgorithm(
                new GregorianCalendar(2014, Calendar.FEBRUARY, 0).getTime(),
                new GregorianCalendar(2014, Calendar.DECEMBER, 0).getTime())
        );
        mapInstrument.put("INSTRUMENT3", new MaxPriceAlgorithm());
    }

    public void run(String data) {
        String[] split = data.split(DELIMITER);
        if (split.length < 2) {
            return;
        }
        try {
            Instrument instrument = new Instrument(
                    new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(split[INDEX_DATE]),
                    split[INDEX_INSTRUEMT],
                    Double.parseDouble(split[INDEX_PRICE])
            );
            Algorithm<?> algorithm = mapInstrument.get(instrument.getName());
            if (algorithm == null) {
                algorithm = new SpecialCaseAlgorithm();
                mapInstrument.put(instrument.getName(), algorithm);
            }
            algorithm.run(instrument);
        } catch (ParseException e) {
            return;
        }
    }

    Set<Map.Entry<String, Algorithm>> getResults() {
        return this.mapInstrument.entrySet();
    }

}

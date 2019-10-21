package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Aggregator {
    private final String DELIMITER = ",";
    private final int INDEX_PRICE = 2;
    private final int INDEX_DATE = 1;
    private final int INDEX_INSTRUEMT = 0;
    private ConcurrentMap<String, Algorithm> mapInstrument = new ConcurrentHashMap<>();

    Aggregator() {
        mapInstrument.put("INSTRUMENT1", new AverageAlgorithm());
        mapInstrument.put("INSTRUMENT2", new AverageAlgorithm(
                new GregorianCalendar(1996, Calendar.FEBRUARY, 0).getTime(),
                new GregorianCalendar(1996, Calendar.DECEMBER, 0).getTime())
        );
        mapInstrument.put("INSTRUMENT3", new MaxPriceAlgorithm());
    }

    public void run(String data) {
        String[] split = data.split(DELIMITER);
        if (split.length < 2) {
            return;
        }
        try {
            Date date = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(split[INDEX_DATE]);
            Instrument instrument = new Instrument(date, split[INDEX_INSTRUEMT], Double.parseDouble(split[INDEX_PRICE]));
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

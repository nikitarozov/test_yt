package com.example.demo;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class AverageAlgorithm implements Algorithm<AverageAlgorithm.AverageResult> {
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong sum = new AtomicLong();
    private Date start;
    private Date finish;

    AverageAlgorithm(Date start, Date finish) {
        this.start = start;
        this.finish = finish;
    }

    AverageAlgorithm() {
    }

    public void run(Instrument instrument) {
        if (start != null) {
            if (this.start.after(instrument.getDate())) {
                return;
            }
        }
        if (finish != null) {
            if (this.finish.before(instrument.getDate())) {
                return;
            }
        }
        this.sum.getAndAdd(instrument.getPrice().longValue());
        this.counter.getAndIncrement();
    }

    public AverageResult getResult() {
        double result = 0;
        if (this.counter.longValue() != 0) {
            result = this.sum.longValue() / this.counter.longValue();
        }
        return new AverageResult(result);
    }

    class AverageResult {
        private double average;

        AverageResult(double average) {
            this.average = average;
        }

        public double getAverrage() {
            return average;
        }

        @Override
        public String toString() {
            return "Average result: [average: " + getResult().average + "]";
        }
    }
}

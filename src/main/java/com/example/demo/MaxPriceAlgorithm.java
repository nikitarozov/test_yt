package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

public class MaxPriceAlgorithm implements Algorithm<MaxPriceAlgorithm.MaximumPriceResult> {
    private final AtomicLong count = new AtomicLong(0);

    public void run(Instrument instrument) {
        double current = Double.longBitsToDouble(this.count.longValue());
        while (current < instrument.getPrice()) {
            if (!this.count.compareAndSet(Double.doubleToLongBits(current), Double.doubleToLongBits(instrument.getPrice()))) {
                current = Double.longBitsToDouble(this.count.longValue());
            } else {
                break;
            }
        }
    }

    public MaximumPriceResult getResult() {
        return new MaximumPriceResult(Double.longBitsToDouble(this.count.longValue()));
    }

    class MaximumPriceResult {
        private Double price;

        MaximumPriceResult(Double price) {
            this.price = price;
        }

        public Double getMaxPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Maximal price: [max: " + getResult().price + "]";
        }
    }
}


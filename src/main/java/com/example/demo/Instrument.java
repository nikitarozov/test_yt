package com.example.demo;

import java.util.Date;

class Instrument implements Comparable<Instrument> {
    private Date date;
    private String name;
    private Double price;

    public Instrument(Date date, String name, Double price) {
        this.date = date;
        this.name = name;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Instrument o) {
        return this.date.compareTo(o.date);
    }
}
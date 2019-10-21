package com.example.demo.algorithms;

import java.util.Date;
import java.util.Objects;

public class Instrument implements Comparable<Instrument> {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return date.equals(that.date) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, price);
    }

    @Override
    public String toString() {
        return "[date: " + date + " name: " + name + ", price: " + price + "]";
    }

    @Override
    public int compareTo(Instrument o) {
        return this.date.compareTo(o.date);
    }
}
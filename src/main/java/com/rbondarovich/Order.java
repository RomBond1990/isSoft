package com.rbondarovich;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDateTime;


public class Order {

    @CsvBindByName(column = "ID")
    private String id;

    @CsvBindByName(column = "DATE_TIME")
    private String day;

    public Order() {
    }

    public Order(String id, String day) {
        this.id = id;
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

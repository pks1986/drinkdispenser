package com.example.drinkdispenser.model;

public class Drink {
    private String name;
    private int price; // in cents

    public Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}


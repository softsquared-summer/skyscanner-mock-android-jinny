package com.jeahn.skyscanner.src.main.models;

public class Product {
    private int Picture;
    private String Name;

    public Product(int picture, String name) {
        Picture = picture;
        Name = name;
    }

    public int getPicture() {
        return Picture;
    }

    public String getName() {
        return Name;
    }
}

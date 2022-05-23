package com.automationpractice.Seleniumautomation.model;

public class ProductData {
    private String name;
    private byte[] image;
    private Double priceValue;

    public ProductData(String name, byte[] image, Double priceValue) {
        this.name = name;
        this.image = image;
        this.priceValue = priceValue;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public Double getPriceValue() {
        return priceValue;
    }
}

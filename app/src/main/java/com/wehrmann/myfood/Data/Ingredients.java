package com.wehrmann.myfood.Data;

public class Ingredients {
    private String name;
    private int unit;
    private String quantity;

    public Ingredients(String name, int unit, String quantity) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }
    public Ingredients() {
        this.name = "";
        this.unit = 0;
        this.quantity ="";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getUnit() {
        return unit;
    }

    public String getQuantity() {
        return quantity;
    }
}

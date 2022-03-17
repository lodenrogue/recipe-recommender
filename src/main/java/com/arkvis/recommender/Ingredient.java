package com.arkvis.recommender;

import java.util.Objects;

public class Ingredient {
    private final String name;
    private final int quantity;
    private final Unit unit;

    public Ingredient(String name, int quantity, Unit unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return quantity == that.quantity && name.equals(that.name) && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, unit);
    }
}

package org.restaurant.models;
import lombok.Data;
@Data
public class Meal {
    public String name;
    public int price;
    public Meal() {
    }
    public Meal(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return getName() + "($" + getPrice() + ")";
    }
}
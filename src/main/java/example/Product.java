package example;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private double price;
    private int numInStock;

    public Product(String name, double price, int numInStock) {
        this.name = name;
        this.price = price;
        this.numInStock = numInStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNumInStock() {
        return numInStock;
    }

}
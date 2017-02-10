package com.Znow.OrderingHelper.Model;

/**
 * Created by User on 28.01.2017.
 */
public class Good {

    private String name;
    private double price;
    private int discount, id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setId(int s_id) {
        id = s_id;
    }
}

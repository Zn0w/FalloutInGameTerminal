package com.Znow.OrderingHelper.Model;

/**
 * Created by User on 12.03.2017.
 */
public class ActiveOrder {

    private int id;
    private String custName, custMail, orderContent;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustMail() {
        return custMail;
    }

    public void setCustMail(String custMail) {
        this.custMail = custMail;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package com.Znow.OrderingHelper;

import com.Znow.OrderingHelper.Model.Good;

import java.util.ArrayList;

/**
 * Created by User on 03.02.2017.
 */
public class Order {

    private double totalPrice = 0;
    private ArrayList<Good> orderedGoods = new ArrayList<Good>();

    private boolean recentlyCreated = true;

    public void addGood(Good good, int amount) {
        orderedGoods.add(good);
        totalPrice += good.getPrice() * amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void accept() {

    }

    public void cancel() {

    }

    public void setRecentlyCreated(boolean bool) {
        recentlyCreated = bool;
    }

    public boolean isRecentlyCreated() {
        return recentlyCreated;
    }
}

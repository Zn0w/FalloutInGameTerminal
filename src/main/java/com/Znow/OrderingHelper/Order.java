package com.Znow.OrderingHelper;

import com.Znow.OrderingHelper.Model.Good;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.Znow.OrderingHelper.Main.connector;

/**
 * Created by User on 03.02.2017.
 */
public class Order {

    private double totalPrice = 0;
    private ArrayList<Good> orderedGoods = new ArrayList<Good>();

    private boolean recentlyCreated = true;

    private String name;
    private String mail;

    public void addGood(Good good, int amount) {
        orderedGoods.add(good);
        totalPrice += good.getPrice() * amount;
    }

    public void removeGood(Good good) {
        for (int i = 0; i < orderedGoods.size(); i++) {
            Good listedGood = orderedGoods.get(i);

            if (good == listedGood) {
                orderedGoods.remove(listedGood);
                totalPrice -= good.getPrice() * good.getOrderedAmount();
            }
        }
    }

    public void updateAmount(Good good, int newAmount) {
        totalPrice -= good.getPrice() * good.getOrderedAmount();
        totalPrice += good.getPrice() * newAmount;

        good.setOrderedAmount(newAmount);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void accept(String s_name, String s_mail) {
        name = s_name;
        mail = s_mail;

        try {
            Statement statement = connector.getConnection().createStatement();

            String orderContent = "";
            for (int i = 0; i < orderedGoods.size(); i++) {
                Good good = orderedGoods.get(i);
                orderContent += good.getName() + "(" + good.getOrderedAmount() + "), ";
            }

            statement.executeUpdate("INSERT INTO active_orders(customer_name, customer_mail, order_content, order_price) VALUES('"+name+"', '"+mail+"', '"+orderContent+"', '"+totalPrice+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {

    }

    public void setRecentlyCreated(boolean bool) {
        recentlyCreated = bool;
    }

    public boolean isRecentlyCreated() {
        return recentlyCreated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

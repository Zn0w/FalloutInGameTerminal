package com.Znow.OrderingHelper.Model;

import com.Znow.OrderingHelper.DBConnector;

import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.Znow.OrderingHelper.Main.connector;

/**
 * Created by User on 28.01.2017.
 */
public class Model {

    public static ArrayList<Good> goods = new ArrayList<Good>();
    public static ArrayList<ActiveOrder> orders = new ArrayList<ActiveOrder>();

    // Initialize goods from database
    public void loadGoods() {
        try {
            Statement statement = connector.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from goods_info");

            while (rs.next()) {
                Good good = new Good();
                goods.add(good);
                good.setId(rs.getInt(1));
                good.setName(rs.getString(2));
                good.setPrice(rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadActiveOrders() {
        try {
            Statement statement = connector.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from active_orders");

            while (rs.next()) {
                ActiveOrder activeOrder = new ActiveOrder();
                orders.add(activeOrder);
                activeOrder.setId(rs.getInt(1));
                activeOrder.setCustName(rs.getString(2));
                activeOrder.setCustMail(rs.getString(3));
                activeOrder.setOrderContent(rs.getString(4));
                activeOrder.setPrice(rs.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveNewGood(Good good, String price) {

    }

    public void deleteGood(Good good) {

    }

    public void updateGood(Good good, String newName) {
        String oldName = good.getName();

        try {
            Statement statement = connector.getConnection().createStatement();
            statement.executeUpdate("UPDATE goods_info SET item_name = '"+newName+"' WHERE item_name = '"+oldName+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGood(Good good, int newPrice) {
        String name = good.getName();

        try {
            Statement statement = connector.getConnection().createStatement();
            statement.executeUpdate("UPDATE goods_info SET item_price = '"+newPrice+"' WHERE item_name = '"+name+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

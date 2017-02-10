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

    public void saveNewGood(String name, String price) {
                        
    }

    public void deleteGood(String name) {

    }

    public void updateGood(String name, String newName) {

    }

    public void updateGood(String name, int newPrice) {

    }

}

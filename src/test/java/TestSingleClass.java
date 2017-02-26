import com.Znow.OrderingHelper.DBConnector;
import com.Znow.OrderingHelper.Model.Good;
import com.Znow.OrderingHelper.Model.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.Znow.OrderingHelper.Model.Model.goods;

/**
 * Created by User on 10.02.2017.
 */
public class TestSingleClass {

    public static void main(String[] args) {
        DBConnector connector = new DBConnector();

        try {
            Statement statement = connector.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from goods_info");

            int i = 0;
            while (rs.next()) {
                Good good = new Good();
                goods.add(good);
                good.setId(rs.getInt(1));
                good.setName(rs.getString(2));
                good.setPrice(rs.getDouble(3));
                i += 1;
                System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.javacourse.productDao;

import com.javacourse.productModels.Laptop;
import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import java.sql.*;
import static com.javacourse.App.logger;

public class LaptopDAO {

    public Laptop getLaptopById(int code){
        Laptop resultingItem = null;
        ResultSet rs;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from laptop where code=?")) {
            statement.setInt(1, code);
            rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = new Laptop();
                resultingItem.setCode(rs.getInt(1));
                resultingItem.setModel(rs.getString(2));
                resultingItem.setSpeed(rs.getByte(3));
                resultingItem.setRam(rs.getByte(4));
                resultingItem.setHd(rs.getInt(5));
                resultingItem.setPrice(rs.getBigDecimal(6));
                resultingItem.setScreen(rs.getByte(7));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }
}

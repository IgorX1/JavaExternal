package com.javacourse.productDao;

import com.javacourse.productModels.Laptop;
import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Printer;

import java.sql.*;
import static com.javacourse.App.logger;

public class LaptopDAO {

    public Laptop getLaptopById(int code){
        Laptop resultingItem = null;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from laptop where code=?")) {
            statement.setInt(1, code);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = constructLaptopItem(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }

    private Laptop constructLaptopItem(ResultSet rs) throws SQLException {
        Laptop resultingItem = new Laptop();
        resultingItem.setCode(rs.getInt(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setSpeed(rs.getShort(3));
        resultingItem.setRam(rs.getByte(4));
        resultingItem.setHd(rs.getInt(5));
        resultingItem.setPrice(rs.getBigDecimal(6));
        resultingItem.setScreen(rs.getByte(7));
        return resultingItem;
    }
}

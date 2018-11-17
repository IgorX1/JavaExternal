package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Laptop;
import com.javacourse.productModels.PC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.App.logger;

public class PcDAO {
    public PC getPcById(int code){
        PC resultingItem = null;
        ResultSet rs;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from pc where code=?")) {
            statement.setInt(1, code);
            rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = new PC();
                resultingItem.setCode(rs.getInt(1));
                resultingItem.setModel(rs.getString(2));
                resultingItem.setSpeed(rs.getByte(3));
                resultingItem.setRam(rs.getByte(4));
                resultingItem.setHd(rs.getInt(5));
                resultingItem.setCd(rs.getString(6));
                resultingItem.setPrice(rs.getBigDecimal(7));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }
}

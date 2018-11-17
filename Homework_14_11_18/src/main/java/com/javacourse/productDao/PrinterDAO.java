package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Printer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.App.logger;

public class PrinterDAO {
    public Printer getPrinterById(int code){
        Printer resultingItem = null;
        ResultSet rs;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from printer where code=?")) {
            statement.setInt(1, code);
            rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = new Printer();
                resultingItem.setCode(rs.getInt(1));
                resultingItem.setModel(rs.getString(2));
                resultingItem.setColor(rs.getString(3));
                resultingItem.setType(rs.getString(4));
                resultingItem.setPrice(rs.getBigDecimal(5));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }
}

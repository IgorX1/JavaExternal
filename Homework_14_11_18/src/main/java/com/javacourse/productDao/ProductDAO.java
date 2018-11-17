package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Printer;
import com.javacourse.productModels.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.App.logger;

public class ProductDAO {
    public Product getProductById(int code){
        Product resultingItem = null;
        ResultSet rs;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from product where code=?")) {
            statement.setInt(1, code);
            rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = new Product();
                resultingItem.setMaker(rs.getString(1));
                resultingItem.setModel(rs.getString(2));
                resultingItem.setType(rs.getString(3));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }
}

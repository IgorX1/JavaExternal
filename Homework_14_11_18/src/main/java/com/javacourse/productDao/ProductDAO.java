package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.javacourse.App.logger;

public class ProductDAO extends AbstractDAO<String, Product>{
    @Override
    public Product findById(String id) {
        Product resultingItem = null;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from product where code=?")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = constructProductItem(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }

    private Product constructProductItem(ResultSet rs) throws SQLException {
        Product resultingItem = null;
        resultingItem = new Product();
        resultingItem.setMaker(rs.getString(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setType(rs.getString(3));
        return resultingItem;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean create(Product entity) {
        return false;
    }

    @Override
    public Product update(Product entity) {
        return null;
    }
}

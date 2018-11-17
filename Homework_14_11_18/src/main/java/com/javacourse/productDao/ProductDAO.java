package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.javacourse.App.logger;

public class ProductDAO extends AbstractDAO<String, Product>{
    @Override
    public Product findById(String id) {
        Product resultingItem = null;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from product where model=?")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = constructItem(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }

    @Override
    public List<Product> findAll() {
        List<Product> resultingItems = new LinkedList<>();
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM product order by model ASC;")){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultingItems.add(constructItem(rs));
            }
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return resultingItems;
    }

    @Override
    public boolean delete(String id) {
        int changeNumber = 0;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM product where code=?")){
            statement.setString(1, id);
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public boolean create(Product entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Product update(Product entity) {
        throw new UnsupportedOperationException();
    }

    private Product constructItem(ResultSet rs) throws SQLException {
        Product resultingItem = null;
        resultingItem = new Product();
        resultingItem.setMaker(rs.getString(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setType(rs.getString(3));
        return resultingItem;
    }
}

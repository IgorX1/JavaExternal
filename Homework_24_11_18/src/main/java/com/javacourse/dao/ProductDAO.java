package com.javacourse.dao;

import com.javacourse.dbConnectionPool.DatabaseConnectionPoolResource;
import com.javacourse.model.Product;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO extends AbstractDAO<String, Product>{

    public final Logger logger;

    public ProductDAO() {
        logger = Logger.getLogger(ProductDAO.class);
        //DOMConfigurator.configure();
    }

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
            PreparedStatement statement = con.prepareStatement("DELETE FROM product where model=?")){
            statement.setString(1, id);
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public boolean create(Product entity) {
        int changeNumber = 0;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO product(maker, model, type) VALUE (?,?,?)")){
            statement.setString(2,entity.getMaker());
            statement.setString(1,entity.getModel());
            statement.setString(3,entity.getType());
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public Product update(Product entity) {
        throw new UnsupportedOperationException();
    }

    Product constructItem(ResultSet rs) throws SQLException {
        Product resultingItem;
        resultingItem = new Product();
        resultingItem.setMaker(rs.getString(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setType(rs.getString(3));
        return resultingItem;
    }

    static void checkIfEntityIsAcceptableForInsert(Product product) throws IllegalArgumentException{
        if(!(product.getType().equals("PC") || product.getType().equals("Laptop") || product.getType().equals("Printer")))
        throw new IllegalArgumentException("Wrong data:product");
    }
}

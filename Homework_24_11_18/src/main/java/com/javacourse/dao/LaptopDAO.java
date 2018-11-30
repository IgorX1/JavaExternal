package com.javacourse.dao;

import com.javacourse.model.Laptop;
import com.javacourse.dbConnectionPool.DatabaseConnectionPoolResource;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LaptopDAO extends AbstractDAO<Integer, Laptop> {

    public static final Logger logger;

    static {
        logger = Logger.getLogger(LaptopDAO.class);
    }

    @Override
    public Laptop findById(Integer id) {
        Laptop resultingItem = null;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from laptop where code=?")) {
            statement.setInt(1, id);
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
    public List<Laptop> findAll() {
        List<Laptop> resultingItems = new LinkedList<>();
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM laptop order by price ASC;")){
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
    public boolean delete(Integer id) {
        int changeNumber = 0;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM laptop where code=?")){
            statement.setInt(1, id);
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public boolean create(Laptop entity) {
        int changeNumber = 0;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO laptop(model, speed, ram, hd, price, screen) VALUE (?,?,?,?,?,?)")){
            statement.setString(1,entity.getModel());
            statement.setShort(2,entity.getSpeed());
            statement.setShort(3, entity.getRam());
            statement.setDouble(4, entity.getHd());
            statement.setBigDecimal(5, entity.getPrice());
            statement.setByte(6, entity.getScreen());
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public Laptop update(Laptop entity) {
        throw new UnsupportedOperationException();
    }

    Laptop constructItem(ResultSet rs) throws SQLException {
        Laptop resultingItem = new Laptop();
        resultingItem.setCode(rs.getInt(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setSpeed(rs.getShort(3));
        resultingItem.setRam(rs.getShort(4));
        resultingItem.setHd(rs.getInt(5));
        resultingItem.setPrice(rs.getBigDecimal(6));
        resultingItem.setScreen(rs.getByte(7));
        return resultingItem;
    }

    public List<String> findLaptopMakersWithProcessorLessThanGiven(int speed){
        List<String> resultingItems = new LinkedList<>();
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT distinct maker FROM laptop JOIN product p on laptop.model = p.model where speed < ?")){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultingItems.add(rs.getString(1));
            }
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return resultingItems;
    }

}

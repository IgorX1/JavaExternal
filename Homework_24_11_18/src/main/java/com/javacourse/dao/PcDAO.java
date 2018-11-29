package com.javacourse.dao;

import com.javacourse.dbConnectionPool.DatabaseConnectionPoolResource;
import com.javacourse.model.Pc;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PcDAO extends AbstractDAO<Integer, Pc> {

    public static final Logger logger;

    static {
        logger = Logger.getLogger(PcDAO.class);
    }

    @Override
    public Pc findById(Integer id) {
        Pc resultingItem = null;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from pc where code=?")) {
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
    public List<Pc> findAll() {
        List<Pc> resultingItems = new LinkedList<>();
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM pc order by price ASC;")){
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
            PreparedStatement statement = con.prepareStatement("DELETE FROM pc where code=?")){
            statement.setInt(1, id);
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public boolean create(Pc entity) {
        int changeNumber = 0;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO pc(model, speed, ram, hd, cd, price) VALUE (?,?,?,?,?,?)")){
            statement.setString(1,entity.getModel());
            statement.setShort(2,entity.getSpeed());
            statement.setShort(3, entity.getRam());
            statement.setDouble(4, entity.getHd());
            statement.setString(5, entity.getCd());
            statement.setBigDecimal(6, entity.getPrice());
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public Pc update(Pc entity) {
        throw new UnsupportedOperationException();
    }

    Pc constructItem(ResultSet rs) throws SQLException {
        Pc resultingItem = new Pc();
        resultingItem.setCode(rs.getInt(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setSpeed(rs.getShort(3));
        resultingItem.setRam(rs.getShort(4));
        resultingItem.setHd(rs.getInt(5));
        resultingItem.setCd(rs.getString(6));
        resultingItem.setPrice(rs.getBigDecimal(7));
        return resultingItem;
    }

}

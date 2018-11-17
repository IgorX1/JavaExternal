package com.javacourse.productDao;

import com.javacourse.productModels.Laptop;
import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.javacourse.App.logger;

public class LaptopDAO extends AbstractDAO<Integer, Laptop> {

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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Laptop entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Laptop update(Laptop entity) {
        throw new UnsupportedOperationException();
    }

    private Laptop constructItem(ResultSet rs) throws SQLException {
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

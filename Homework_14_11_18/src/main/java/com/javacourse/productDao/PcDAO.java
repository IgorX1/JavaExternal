package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Pc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.javacourse.App.logger;

public class PcDAO extends AbstractDAO<Integer, Pc> {
    @Override
    public Pc findById(Integer id) {
        Pc resultingItem = null;
        try(Connection con= DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from pc where code=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = constructProductItem(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }

    private Pc constructProductItem(ResultSet rs) throws SQLException {
        Pc resultingItem = new Pc();
        resultingItem.setCode(rs.getInt(1));
        resultingItem.setModel(rs.getString(2));
        resultingItem.setSpeed(rs.getByte(3));
        resultingItem.setRam(rs.getByte(4));
        resultingItem.setHd(rs.getInt(5));
        resultingItem.setCd(rs.getString(6));
        resultingItem.setPrice(rs.getBigDecimal(7));
        return resultingItem;
    }

    @Override
    public List<Pc> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Pc entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pc update(Pc entity) {
        throw new UnsupportedOperationException();
    }
}

package com.javacourse.productDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Printer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.javacourse.App.logger;

public class PrinterDAO extends AbstractDAO<Integer, Printer>{
    @Override
    public Printer findById(Integer id) {
        Printer resultingItem = null;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * from printer where code=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                resultingItem = constructPrinterItem(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultingItem;
    }

    public List<Printer> getAllColorPrintersOrderedByPriceDescending(){
        List<Printer> resultingItems = new LinkedList<>();
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM printer where color='y' order by price;")){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultingItems.add(constructPrinterItem(rs));
            }
        }catch (SQLException e){

        }
        return resultingItems;
    }

    private Printer constructPrinterItem(ResultSet rs) throws SQLException {
        Printer currentPrinter;
        currentPrinter = new Printer();
        currentPrinter.setCode(rs.getInt(1));
        currentPrinter.setModel(rs.getString(2));
        currentPrinter.setColor(rs.getString(3));
        currentPrinter.setType(rs.getString(4));
        currentPrinter.setPrice(rs.getBigDecimal(5));
        return currentPrinter;
    }

    @Override
    public List<Printer> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Printer entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Printer update(Printer entity) {
        throw new UnsupportedOperationException();
    }
}

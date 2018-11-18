package com.javacourse.productsDao;

import com.javacourse.dbInterction.DatabaseConnectionPoolResource;
import com.javacourse.productModels.Printer;
import com.javacourse.productModels.Product;

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
                resultingItem = constructItem(rs);
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
                resultingItems.add(constructItem(rs));
            }
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return resultingItems;
    }

    @Override
    public List<Printer> findAll() {
        List<Printer> resultingItems = new LinkedList<>();
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM printer order by price ASC;")){
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
            PreparedStatement statement = con.prepareStatement("DELETE FROM printer where code=?")){
            statement.setInt(1, id);
            changeNumber = statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    @Override
    public boolean create(Printer entity) {
        int changeNumber = 0;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO printer(model, color, type, price) VALUE (?,?,?,?)")){

            checkIfEntityIsAcceptableForInsert(entity);

            statement.setString(1,entity.getModel());
            statement.setString(2,entity.getColor());
            statement.setString(3,entity.getType());
            statement.setBigDecimal(4, entity.getPrice());
            changeNumber = statement.executeUpdate();
        }catch (SQLException | IllegalArgumentException e){
            logger.error(e.getMessage());
        }
        return changeNumber>0;
    }

    public boolean createAsNewProduct(Printer printer, Product product){
        int changeNumber = 0;
        try(Connection con=DatabaseConnectionPoolResource.getConnection();
            PreparedStatement productInsertStatement = con.prepareStatement("INSERT INTO product(maker, model, type) VALUE (?,?,?)");
            PreparedStatement printerInsertStatement = con.prepareStatement("INSERT INTO printer(model, color, type, price) VALUE (?,?,?,?)")){

            //Check if model values are acceptable for insert operation
            checkIfEntityIsAcceptableForInsert(printer);
            ProductDAO.checkIfEntityIsAcceptableForInsert(product);
            if(!printer.getModel().equals(product.getModel()) || !product.getType().equals("Printer"))
                throw new IllegalArgumentException("Wrong data:product and printer consistency");

            con.setAutoCommit(false);

            productInsertStatement.setString(1, product.getMaker());
            productInsertStatement.setString(2, product.getModel());
            productInsertStatement.setString(3, product.getType());

            printerInsertStatement.setString(1, printer.getModel());
            printerInsertStatement.setString(2, printer.getColor());
            printerInsertStatement.setString(3, printer.getType());
            printerInsertStatement.setBigDecimal(4, printer.getPrice());

            try {
                productInsertStatement.executeUpdate();
                changeNumber = printerInsertStatement.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                con.rollback();
            }

            con.setAutoCommit(true);
        }catch (SQLException | IllegalArgumentException e){
            logger.error(e.getMessage());

        }
        return changeNumber>0;
    }

    @Override
    public Printer update(Printer entity) {
        throw new UnsupportedOperationException();
    }

    private Printer constructItem(ResultSet rs) throws SQLException {
        Printer currentPrinter;
        currentPrinter = new Printer();
        currentPrinter.setCode(rs.getInt(1));
        currentPrinter.setModel(rs.getString(2));
        currentPrinter.setColor(rs.getString(3));
        currentPrinter.setType(rs.getString(4));
        currentPrinter.setPrice(rs.getBigDecimal(5));
        return currentPrinter;
    }

    static void checkIfEntityIsAcceptableForInsert(Printer printer) throws IllegalArgumentException{
        if(!"yYnN".contains(printer.getColor()))
            throw new IllegalArgumentException("Wrong data:printer");
    }

}

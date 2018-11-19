package com.javacourse.productsDao;

import com.javacourse.productModels.Printer;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PrinterDAOTest {
    PrinterDAO printerDAO = new PrinterDAO();

    @Test
    public void constructItem() throws SQLException {
        Printer expected = new Printer(11, "123", "y", "Laser", new BigDecimal(100));
        ResultSet rs = Mockito.mock(ResultSet.class);
        when(rs.getInt(1)).thenReturn(expected.getCode());
        when(rs.getString(2)).thenReturn(expected.getModel());
        when(rs.getString(3)).thenReturn(expected.getColor());
        when(rs.getString(4)).thenReturn(expected.getType());
        when(rs.getBigDecimal(5)).thenReturn(expected.getPrice());

        Printer actual = printerDAO.constructItem(rs);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfEntityIsAcceptableForInsert() {

    }
}
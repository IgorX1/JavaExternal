package com.javacourse.productsDao;

import com.javacourse.productModels.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class PrinterDAOTest {
    PrinterDAO printerDAO = new PrinterDAO();
    Printer printer;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new Printer(1,"", "", "", new BigDecimal(1)) },
                { new Printer(1,"", "yes", "", new BigDecimal(1)) },
                { new Printer(1,"", null, "", new BigDecimal(1)) }
        });
    }

    public PrinterDAOTest(Printer printer) {
        this.printer = printer;
    }

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
        printerDAO.checkIfEntityIsAcceptableForInsert(printer);
    }
}
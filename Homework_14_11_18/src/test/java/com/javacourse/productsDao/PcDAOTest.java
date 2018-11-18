package com.javacourse.productsDao;

import com.javacourse.productModels.Laptop;
import com.javacourse.productModels.Pc;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PcDAOTest {

    PcDAO pcDAO = new PcDAO();

    @Test
    public void constructItem() throws SQLException {
        short speed = 350;
        short ram = 32;
        Pc expected = new Pc(1, "1298", speed, ram, 4, new BigDecimal(400), "10x");
        ResultSet rs = Mockito.mock(ResultSet.class);
        when(rs.getInt(1)).thenReturn(expected.getCode());
        when(rs.getString(2)).thenReturn(expected.getModel());
        when(rs.getShort(3)).thenReturn(expected.getSpeed());
        when(rs.getShort(4)).thenReturn(expected.getRam());
        when(rs.getInt(5)).thenReturn((int) expected.getHd());
        when(rs.getString(6)).thenReturn(expected.getCd());
        when(rs.getBigDecimal(7)).thenReturn(expected.getPrice());

        Pc actual = pcDAO.constructItem(rs);
        assertEquals(expected, actual);
    }
}
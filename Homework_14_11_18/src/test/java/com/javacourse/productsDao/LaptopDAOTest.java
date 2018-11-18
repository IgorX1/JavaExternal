package com.javacourse.productsDao;

import com.javacourse.productModels.Laptop;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LaptopDAOTest {

    LaptopDAO laptopDAO = new LaptopDAO();

    @Test
    public void constructItem() throws SQLException {
        short speed = 350;
        short ram = 32;
        byte screen = 14;
        Laptop expected = new Laptop(1, "1298", speed, ram, 4, new BigDecimal(400), screen);
        ResultSet rs = Mockito.mock(ResultSet.class);
        when(rs.getInt(1)).thenReturn(expected.getCode());
        when(rs.getString(2)).thenReturn(expected.getModel());
        when(rs.getShort(3)).thenReturn(expected.getSpeed());
        when(rs.getShort(4)).thenReturn(expected.getRam());
        when(rs.getInt(5)).thenReturn((int) expected.getHd());
        when(rs.getBigDecimal(6)).thenReturn(expected.getPrice());
        when(rs.getByte(7)).thenReturn(expected.getScreen());

        Laptop actual = laptopDAO.constructItem(rs);
        assertEquals(expected, actual);

    }
}
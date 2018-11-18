package com.javacourse.productsDao;

import com.javacourse.productModels.Product;
import org.junit.Test;
import org.mockito.Mockito;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ProductDAOTest {

    ProductDAO productDAO = new ProductDAO();

    @Test
    public void constructItem() throws SQLException {
        Product expected = new Product("Apple", "Iphone X", "phone");
        ResultSet rs = Mockito.mock(ResultSet.class);
        when(rs.getString(1)).thenReturn(expected.getMaker());
        when(rs.getString(2)).thenReturn(expected.getModel());
        when(rs.getString(3)).thenReturn(expected.getType());

        Product actual = productDAO.constructItem(rs);
        assertEquals(expected, actual);
    }
}
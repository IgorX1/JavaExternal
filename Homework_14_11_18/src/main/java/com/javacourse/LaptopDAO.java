package com.javacourse;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LaptopDAO {
    public List<Laptop> getLaptops(){
        List<Laptop> laptops = new LinkedList<>();
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/labor_sql?serverTimezone=UTC","root", "root");
            Statement statement = cn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * from laptop;");
            Laptop laptop;
            while(res.next()){
                laptop = new Laptop();
                laptop.setCode(res.getInt(1));
                laptop.setModel(res.getString(2));
                laptops.add(laptop);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return laptops;
    }
}

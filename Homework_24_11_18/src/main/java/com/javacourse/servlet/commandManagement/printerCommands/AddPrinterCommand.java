package com.javacourse.servlet.commandManagement.printerCommands;

import com.javacourse.dao.PrinterDAO;
import com.javacourse.model.Printer;
import com.javacourse.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddPrinterCommand implements com.javacourse.servlet.commandManagement.ActionCommand {
    PrinterDAO printerDAO = new PrinterDAO();

    private static final String COLOR_PARAM = "color";
    private static final String MODEL_PARAM = "model";
    private static final String TYPE_PARAM = "type";
    private static final String PRICE_PARAM = "price";
    private static final int ID_STUB = -1;

    @Override
    public String execute(HttpServletRequest request) {
        String color = request.getParameter(COLOR_PARAM);
        String model = request.getParameter(MODEL_PARAM);
        String  type = request.getParameter(TYPE_PARAM);
        BigDecimal price = new BigDecimal(request.getParameter(PRICE_PARAM));

        Printer printer = new Printer(ID_STUB, model, color, type, price);
        if(printerDAO.create(printer)){
            return "/PrinterServlet";
        }else{
            return "/PrinterServlet?status=unsuccessful";
        }
    }
}

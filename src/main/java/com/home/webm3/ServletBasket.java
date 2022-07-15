package com.home.webm3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/basket")
public class ServletBasket extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //String par1 = req.getParameter("par1");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("from basket");
        printWriter.close();

    }
}

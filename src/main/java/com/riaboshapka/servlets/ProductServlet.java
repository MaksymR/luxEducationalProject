package com.riaboshapka.servlets;

import com.riaboshapka.domain.Product;
import com.riaboshapka.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ProductServlet extends HttpServlet {


    private ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getPathInfo();
        if ("/deleteProduct".equals(pathInfo)) {
            doDelete(req, resp);
            return;
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        for (Product product : productService.getAllProducts()) {
            writer.println("<h2>" + product + "</h2>");
            writer.println("<br>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        productService.createProduct(productName, BigDecimal.valueOf(Long.parseLong(price)));
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        productService.modifyProduct(Long.parseLong(id), productName, BigDecimal.valueOf(Long.parseLong(price)));
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        productService.delete(Long.parseLong(id));
    }

}

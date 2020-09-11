package com.riaboshapka.servlets.productsServlets;

import com.riaboshapka.dao.impl.ProductDBDao;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.ProductService;
import com.riaboshapka.services.impl.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(DELETE_PRODUCT_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productsID = req.getParameter("id");

        // check out if data from a browser are "empty"
        if (!productsID.isEmpty()) {
            ProductDBDao productDBDao = new ProductDBDao();
            ProductService productService = new ProductServiceImpl(productDBDao);
            Product productForDelete = productDBDao.findProduct(Long.parseLong(productsID));
            // deleteProduct an existing product
            productService.deleteProduct(Long.parseLong(productsID));
            req.setAttribute("product", productForDelete);
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }
    }


}

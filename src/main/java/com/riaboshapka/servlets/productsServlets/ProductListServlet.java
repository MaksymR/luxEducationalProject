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
import java.util.List;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDBDao productDBDao = new ProductDBDao();
        ProductService productService = new ProductServiceImpl(productDBDao);
        List<Product> productsList = productService.getAllProducts();
        req.setAttribute("productsList", productsList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCTS_LIST_JSP);
        requestDispatcher.forward(req, resp);
    }


}

package com.riaboshapka.servlets.productServlets;

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
import java.math.BigDecimal;
import java.util.List;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class CreateProductServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(CREATE_PRODUCT_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productsName = req.getParameter("name");
        String productsPrice = req.getParameter("price");

        //check out if data from a browser are "empty"
        if (!productsName.isEmpty() &&
                !productsPrice.isEmpty()) {
            ProductDBDao clientDBDao = new ProductDBDao();
            ProductService productService = new ProductServiceImpl(clientDBDao);

            // create a product through data from site
            productService.createProduct(productsName, BigDecimal.valueOf(Long.parseLong(productsPrice)));

            // get the product for request argument of setAttribute()
            List<Product> productsList = productService.getAllProducts();
            Product tempProduct = productsList.get(productsList.size() - 1);
            String tempProductName = tempProduct.getName();
            BigDecimal tempProductPrice = tempProduct.getPrice();
            if ((tempProductName.equals(productsName) &&
                    (tempProductPrice.equals(BigDecimal.valueOf(Long.parseLong(productsPrice)))))) {
                Product productForRequest = new Product(tempProductName, tempProductPrice);
                req.setAttribute("product", productForRequest);
            }
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }
    }


}

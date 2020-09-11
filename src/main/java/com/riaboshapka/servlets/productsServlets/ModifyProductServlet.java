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
import java.math.BigDecimal;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class ModifyProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(MODIFY_PRODUCT_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productsID = req.getParameter("id");
        String productsName = req.getParameter("name");
        String productsPrice = req.getParameter("price");

        // check out if data from a browser are "empty"
        if (!productsID.isEmpty() &&
                !productsName.isEmpty() &&
                !productsPrice.isEmpty()) {
            ProductDBDao productDBDao = new ProductDBDao();
            ProductService productService = new ProductServiceImpl(productDBDao);

            // modify an existing product through data from the site
            productService.modifyProduct(Long.parseLong(productsID),
                    productsName,
                    BigDecimal.valueOf(Long.parseLong(productsPrice)));

            // get the product for request argument of setAttribute()
            Product tempProduct = productDBDao.findProduct(Long.parseLong(productsID));
            req.setAttribute("product", tempProduct);
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }

    }


}

package com.riaboshapka.servlets.ordersServlets;

import com.riaboshapka.dao.impl.OrderDBDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.impl.OrderServiceImpl;
import com.riaboshapka.servlets.iDForChecking.IDForChecking;
import com.riaboshapka.servlets.iDForChecking.impl.IDForCheckingImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class ModifyOrderServlet extends HttpServlet {

    private IDForChecking idForChecking = new IDForCheckingImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(MODIFY_ORDER_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ordersID = req.getParameter("ordersID");
        String clientsID = req.getParameter("clientsID");
        String productsID = req.getParameter("productsID");

        // check out if data from a browser are "empty"
        if (!ordersID.isEmpty() && !clientsID.isEmpty() && !productsID.isEmpty()) {
            OrderDBDao orderDBDao = new OrderDBDao();
            OrderService orderService = new OrderServiceImpl(orderDBDao);

            Client clientForModify = orderDBDao.findClient(Long.parseLong(clientsID));
            List<Product> productListForModify = new ArrayList<>();
            Product productForList = orderDBDao.findProduct(Long.parseLong(productsID));
            productListForModify.add(productForList);

            // prepare for checking client's and product's ID into BD
            List<Long> clientIDList = idForChecking.getClientIDForChecking();
            List<Long> productIDList = idForChecking.getProductIDForChecking();

            // check client's and product's ID into BD
            if ((clientIDList.contains(Long.parseLong(clientsID)))
                    && (productIDList.contains(Long.parseLong(productsID)))) {

                // modify an existing order through data from the site
                orderService.modifyOrder(Long.parseLong(ordersID), clientForModify, productListForModify);

                Order tempOrder = orderDBDao.findOrder(Long.parseLong(ordersID));
                req.setAttribute("order", tempOrder);
                doGet(req, resp);
            } else {
                doGet(req, resp);
            }
        } else {
            doGet(req, resp);
        }
    }

}

package com.riaboshapka.servlets.ordersServlets;

import com.riaboshapka.dao.impl.ClientDBDao;
import com.riaboshapka.dao.impl.OrderDBDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.impl.ClientServiceImpl;
import com.riaboshapka.services.impl.OrderServiceImpl;
import com.riaboshapka.validators.ValidationService;
import com.riaboshapka.validators.impl.ValidationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class DeleteOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(DELETE_ORDER_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ordersID = req.getParameter("ordersID");

        // check out if data from a browser are "empty"
        if (!ordersID.isEmpty()) {
            OrderDBDao orderDBDao = new OrderDBDao();
            OrderService orderService = new OrderServiceImpl(orderDBDao);
            Order orderForDelete = orderDBDao.findOrder(Long.parseLong(ordersID));
            // deleteProduct an existing order
            orderService.delete(Long.parseLong(ordersID));
            req.setAttribute("order", orderForDelete);
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }
    }


}

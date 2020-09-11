package com.riaboshapka.servlets.ordersServlets;

import com.riaboshapka.dao.impl.OrderDBDao;
import com.riaboshapka.domain.Order;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.impl.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class OrdersListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDBDao orderDBDao = new OrderDBDao();
        OrderService orderService = new OrderServiceImpl(orderDBDao);
        List<Order> ordersList = orderService.getAllOrders();
        req.setAttribute("ordersList", ordersList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(ORDERS_LIST_JSP);
        requestDispatcher.forward(req, resp);
    }


}

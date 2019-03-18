package com.riaboshapka.servlets;

import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    public OrderServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getPathInfo();
        if ("/deleteOrder".equals(pathInfo)) {
            doDelete(req, resp);
            return;
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        for (Order order : orderService.getAllOrders()) {
            writer.println("<h2>" + order + "</h2>");
            writer.println("<br>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientName = req.getParameter("clientName");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        Client client = new Client(clientName, surname, Integer.parseInt(age), phone, email);
        Product product = new Product(productName, BigDecimal.valueOf(Long.parseLong(price)));
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        orderService.createOrder(client, productList);
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String clientName = req.getParameter("clientName");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        Client client = new Client(clientName, surname, Integer.parseInt(age), phone, email);
        Product product = new Product(productName, BigDecimal.valueOf(Long.parseLong(price)));
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        orderService.modifyOrder(Long.parseLong(id), client, productList);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        orderService.delete(Long.parseLong(id));
    }

}

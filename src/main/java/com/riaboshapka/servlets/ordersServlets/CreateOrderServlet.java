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

public class CreateOrderServlet extends HttpServlet {

    private IDForChecking idForChecking = new IDForCheckingImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(CREATE_ORDER_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientsID = req.getParameter("clientsID");
        String productsID = req.getParameter("productsID");

        //check out if data from a browser are "empty"
        if (!clientsID.isEmpty() && !productsID.isEmpty()) {

            OrderDBDao orderDBDao = new OrderDBDao();
            OrderService orderService = new OrderServiceImpl(orderDBDao);
            Client clientForCreateOrder = orderDBDao.findClient(Long.parseLong(clientsID));
            List<Product> listProductForCreateOrder = new ArrayList<>();
            Product productForCreateOrder = orderDBDao.findProduct(Long.parseLong(productsID));
            listProductForCreateOrder.add(productForCreateOrder);

            // prepare for checking client's and product's ID into BD
            List<Long> clientIDList = idForChecking.getClientIDForChecking();
            List<Long> productIDList = idForChecking.getProductIDForChecking();

            // check client's and product's ID into BD
            if ((clientIDList.contains(Long.parseLong(clientsID)))
                    && (productIDList.contains(Long.parseLong(productsID)))) {

                // create an order through data from site if client's and product's id are existed
                orderService.createOrder(clientForCreateOrder, listProductForCreateOrder);

                // get the order for request argument of setAttribute()
                List<Order> ordersList = orderService.getAllOrders();
                Order tempOrder = ordersList.get(ordersList.size() - 1);
                Client clientForChecking = tempOrder.getClient();
                String tempClientsID = String.valueOf(clientForChecking.getId());
                List<Product> listOfProductForChecking = tempOrder.getProducts();
                Product productForChecking = listOfProductForChecking.get(listOfProductForChecking.size() - 1);
                String tempProductsID = String.valueOf(productForChecking.getId());
                Client clientForRequest = orderDBDao.findClient(Long.parseLong(tempClientsID));
                Product productForProductListForRequest = orderDBDao.findProduct(Long.parseLong(tempProductsID));
                List<Product> productListForRequest = new ArrayList<>();
                productListForRequest.add(productForProductListForRequest);
                if (tempClientsID.equals(clientsID) && tempProductsID.equals(productsID)) {
                    Order orderForRequest = new Order(clientForRequest, productListForRequest);
                    req.setAttribute("order", orderForRequest);
                }
            }
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }

    }

}

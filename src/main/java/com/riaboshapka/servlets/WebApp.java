package com.riaboshapka.servlets;

import com.riaboshapka.dao.impl.ClientDBDao;
import com.riaboshapka.dao.impl.OrderDBDao;
import com.riaboshapka.dao.impl.ProductDBDao;
import com.riaboshapka.services.impl.ClientServiceImpl;
import com.riaboshapka.services.impl.OrderServiceImpl;
import com.riaboshapka.services.impl.ProductServiceImpl;
import com.riaboshapka.validators.ValidationService;
import com.riaboshapka.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClientDBDao clientDao = new ClientDBDao();
        ValidationService validationService = new ValidationServiceImpl();
        ClientServiceImpl clientService = new ClientServiceImpl(clientDao, validationService);

        ProductDBDao productDBDao = new ProductDBDao();
        ProductServiceImpl productService = new ProductServiceImpl(productDBDao);

        OrderDBDao orderDBDao = new OrderDBDao();
        OrderServiceImpl orderService = new OrderServiceImpl(orderDBDao);

        ServletContext clientServletContext = sce.getServletContext();
        clientServletContext
                .addServlet("ClientServlet", new ClientServlet(clientService))
                .addMapping("/clients/*");

        ServletContext productServletContext = sce.getServletContext();
        productServletContext
                .addServlet("ProductServlet", new ProductServlet(productService))
                .addMapping("/products/*");

        ServletContext orderServletContext = sce.getServletContext();
        orderServletContext
                .addServlet("OrderServlet", new OrderServlet(orderService))
                .addMapping("/orders/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

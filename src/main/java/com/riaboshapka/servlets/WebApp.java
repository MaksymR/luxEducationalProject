package com.riaboshapka.servlets;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.dao.impl.ClientDBDao;
import com.riaboshapka.services.impl.ClientServiceImpl;
import com.riaboshapka.servlets.filters.ClientFilter;
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


        ServletContext servletContext = sce.getServletContext();
        servletContext
                .addServlet("ClientServlet", new ClientServlet(clientService))
                .addMapping("/clients/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

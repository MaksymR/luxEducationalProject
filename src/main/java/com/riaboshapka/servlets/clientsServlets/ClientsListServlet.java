package com.riaboshapka.servlets.clientsServlets;

import com.riaboshapka.dao.impl.ClientDBDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.impl.ClientServiceImpl;
import com.riaboshapka.validators.ValidationService;
import com.riaboshapka.validators.impl.ValidationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class ClientsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientDBDao clientDBDao = new ClientDBDao();
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDBDao, validationService);
        List<Client> clientsList = clientService.getAllClients();
        req.setAttribute("clientsList", clientsList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(CLIENTS_LIST_JSP);
        requestDispatcher.forward(req, resp);
    }

}

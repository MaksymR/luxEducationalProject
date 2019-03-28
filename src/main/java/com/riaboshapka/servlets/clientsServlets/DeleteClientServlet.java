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

import static com.riaboshapka.servlets.viewsPathForServlets.ViewsPathForServlets.*;

public class DeleteClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(DELETE_CLIENT_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientID = req.getParameter("id");

        // check out if data from a browser are "empty"
        if (!clientID.isEmpty()) {
            ClientDBDao clientDBDao = new ClientDBDao();
            ValidationService validationService = new ValidationServiceImpl();
            ClientService clientService = new ClientServiceImpl(clientDBDao, validationService);
            Client clientForDelete = clientDBDao.findClient(Long.parseLong(clientID));
            // delete an existing client
            clientService.deleteClient(Long.parseLong(clientID));
            req.setAttribute("client", clientForDelete);
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }
    }
}

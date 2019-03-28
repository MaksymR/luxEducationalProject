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

public class ModifyClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(MODIFY_CLIENT_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientID = req.getParameter("id");
        String clientsName = req.getParameter("name");
        String clientsSurname = req.getParameter("surname");
        String clientsAge = req.getParameter("age");
        String clientsPhone = req.getParameter("phone");
        String clientsEmail = req.getParameter("email");

        // check out if data from a browser are "empty"
        if (!clientID.isEmpty() &&
                !clientsName.isEmpty() &&
                !clientsSurname.isEmpty() &&
                !clientsAge.isEmpty() &&
                !clientsPhone.isEmpty() &&
                !clientsEmail.isEmpty()) {
            ClientDBDao clientDBDao = new ClientDBDao();
            ValidationService validationService = new ValidationServiceImpl();
            ClientService clientService = new ClientServiceImpl(clientDBDao, validationService);

            // modify an existing client through data from site
            clientService.modifyClient(Long.parseLong(clientID),
                    clientsName,
                    clientsSurname,
                    Integer.parseInt(clientsAge),
                    clientsPhone,
                    clientsEmail);

            // get client for request argument of setAttribute()
            Client tempClient = clientDBDao.findClient(Long.parseLong(clientID));
            req.setAttribute("client", tempClient);
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }

    }
}

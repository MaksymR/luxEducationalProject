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

public class CreateClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(CREATE_CLIENT_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientsName = req.getParameter("name");
        String clientsSurname = req.getParameter("surname");
        String clientsAge = req.getParameter("age");
        String clientsPhone = req.getParameter("phone");
        String clientsEmail = req.getParameter("email");

        //check out if data from a browser are "empty"
        if (!clientsName.isEmpty() &&
                !clientsSurname.isEmpty() &&
                !clientsAge.isEmpty() &&
                !clientsPhone.isEmpty() &&
                !clientsEmail.isEmpty()) {
            ClientDBDao clientDBDao = new ClientDBDao();
            ValidationService validationService = new ValidationServiceImpl();
            ClientService clientService = new ClientServiceImpl(clientDBDao, validationService);

            // create a client through data from site
            clientService.createClient(
                    clientsName,
                    clientsSurname,
                    Integer.parseInt(clientsAge),
                    clientsPhone,
                    clientsEmail);

            // get client for request argument of setAttribute()
            List<Client> clientsList = clientService.getAllClients();
            Client tempClient = clientsList.get(clientsList.size() - 1);
            String tempClientsName = tempClient.getName();
            String tempClientSurname = tempClient.getSurname();
            Integer tempClientsAge = tempClient.getAge();
            String tempClientsPhone = tempClient.getPhone();
            String tempClientsEmail = tempClient.getEmail();
            if ((tempClientsName.equals(clientsName)) &&
                    (tempClientSurname.equals(clientsSurname)) &&
                    (tempClientsAge.equals(Integer.parseInt(clientsAge))) &&
                    (tempClientsPhone.equals(clientsPhone)) &&
                    (tempClientsEmail).equals(clientsEmail)) {
                Client clientForRequest = new Client(tempClientsName,
                        tempClientSurname,
                        tempClientsAge,
                        tempClientsPhone,
                        tempClientsEmail);
                req.setAttribute("client", clientForRequest);
            }
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }
    }
}

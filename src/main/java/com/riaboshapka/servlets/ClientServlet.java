package com.riaboshapka.servlets;

import com.riaboshapka.domain.Client;
import com.riaboshapka.services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientServlet extends HttpServlet {

    private ClientService clientService;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getPathInfo();
        if ("/deleteClient".equals(pathInfo)) {
            doDelete(req, resp);
            return;
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        for (Client client : clientService.getAllClients()) {
            writer.println("<h2>" + client + "</h2>");
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
        clientService.createClient(clientName, surname, Integer.parseInt(age), phone, email);
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
        clientService.modifyClient(Long.parseLong(id), clientName, surname, Integer.parseInt(age), phone, email);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        clientService.deleteClient(Long.parseLong(id));
    }
}

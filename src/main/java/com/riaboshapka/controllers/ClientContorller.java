package com.riaboshapka.controllers;

import com.riaboshapka.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientContorller {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clientsss")
//    @RequestMapping(method = '')
    public String showClients(ModelMap modelMap) {
        modelMap.put("message", clientService.getAllClients());
        return "clients";
    }

}

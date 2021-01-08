package org.unicome.data.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unicome.data.domain.mysql.client.Client;
import org.unicome.data.resource.service.ClientService;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/client/{id}")
    public Client getByClientId(@PathVariable(required = true) int id) {
        Client client = clientService.getById(id);
        return client;
    }

    @GetMapping("/client")
    public Client getByClientId(@RequestParam(defaultValue = "") String clientId) {
        Client client = clientService.getByClientId(clientId);
        return client;
    }
}

package org.unicome.data.domain.mysql.client;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unicome.data.UnicomeDataApplication;
import org.unicome.data.resource.service.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnicomeDataApplication.class)
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void getById() {
//        int id = 1;
//        Client client = clientService.getById(id);
//        System.out.println(client.toString());
    }
}
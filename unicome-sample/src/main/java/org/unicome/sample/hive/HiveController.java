package org.unicome.sample.hive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiveController {

    @Autowired
    private HiveService hiveService;

    @GetMapping("/hive/tables")

    public String hive() {
        return hiveService.sample();
    }


}

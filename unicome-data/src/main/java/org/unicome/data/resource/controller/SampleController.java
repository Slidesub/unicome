package org.unicome.data.resource.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/sample")
    public String sample() {
        logger.debug("sample debug");
        logger.info("sample info");
        return "sample";
    }
}
